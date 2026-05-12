package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonServiceException.ErrorType;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.RequestClientOptions;
import com.amazonaws.RequestClientOptions.Marker;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.Signer;
import com.amazonaws.handlers.CredentialsRequestHandler;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.internal.CRC32MismatchException;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.retry.RetryUtils;
import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.AWSRequestMetrics.Field;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.TimingInfo;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.internal.http.StatusLine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AmazonHttpClient {
    private static final String HEADER_SDK_RETRY_INFO = "aws-sdk-retry";
    private static final String HEADER_SDK_TRANSACTION_ID = "aws-sdk-invocation-id";
    private static final String HEADER_USER_AGENT = "User-Agent";
    static final Log log = LogFactory.getLog(AmazonHttpClient.class);
    private static final Log requestLog = LogFactory.getLog("com.amazonaws.request");
    final ClientConfiguration config;
    final HttpClient httpClient;
    private final HttpRequestFactory requestFactory;
    private final RequestMetricCollector requestMetricCollector;

    public AmazonHttpClient(ClientConfiguration config) {
        this(config, new UrlHttpClient(config));
    }

    @Deprecated
    public AmazonHttpClient(ClientConfiguration config, RequestMetricCollector requestMetricCollector) {
        this(config, new UrlHttpClient(config), requestMetricCollector);
    }

    public AmazonHttpClient(ClientConfiguration config, HttpClient httpClient) {
        this.requestFactory = new HttpRequestFactory();
        this.config = config;
        this.httpClient = httpClient;
        this.requestMetricCollector = null;
    }

    @Deprecated
    public AmazonHttpClient(ClientConfiguration config, HttpClient httpClient, RequestMetricCollector requestMetricCollector) {
        this.requestFactory = new HttpRequestFactory();
        this.config = config;
        this.httpClient = httpClient;
        this.requestMetricCollector = requestMetricCollector;
    }

    @Deprecated
    public ResponseMetadata getResponseMetadataForRequest(AmazonWebServiceRequest request) {
        return null;
    }

    public <T> Response<T> execute(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> responseHandler, HttpResponseHandler<AmazonServiceException> errorResponseHandler, ExecutionContext executionContext) throws AmazonClientException, AmazonServiceException {
        if (executionContext == null) {
            throw new AmazonClientException("Internal SDK Error: No execution context parameter specified.");
        }
        List<RequestHandler2> requestHandler2s = requestHandler2s(request, executionContext);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        Response<T> response = null;
        try {
            response = executeHelper(request, responseHandler, errorResponseHandler, executionContext);
            afterResponse(request, requestHandler2s, response, awsRequestMetrics.getTimingInfo().endTiming());
            return response;
        } catch (AmazonClientException e) {
            afterError(request, response, requestHandler2s, e);
            throw e;
        }
    }

    void afterError(Request<?> request, Response<?> response, List<RequestHandler2> requestHandler2s, AmazonClientException e) {
        for (RequestHandler2 handler2 : requestHandler2s) {
            handler2.afterError(request, response, e);
        }
    }

    <T> void afterResponse(Request<?> request, List<RequestHandler2> requestHandler2s, Response<T> response, TimingInfo timingInfo) {
        for (RequestHandler2 handler2 : requestHandler2s) {
            handler2.afterResponse(request, response);
        }
    }

    List<RequestHandler2> requestHandler2s(Request<?> request, ExecutionContext executionContext) {
        List<RequestHandler2> requestHandler2s = executionContext.getRequestHandler2s();
        if (requestHandler2s == null) {
            return Collections.emptyList();
        }
        for (RequestHandler2 requestHandler2 : requestHandler2s) {
            if (requestHandler2 instanceof CredentialsRequestHandler) {
                ((CredentialsRequestHandler) requestHandler2).setCredentials(executionContext.getCredentials());
            }
            requestHandler2.beforeRequest(request);
        }
        return requestHandler2s;
    }

    <T> Response<T> executeHelper(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> responseHandler, HttpResponseHandler<AmazonServiceException> errorResponseHandler, ExecutionContext executionContext) throws AmazonClientException, AmazonServiceException {
        boolean leaveHttpConnectionOpen = false;
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.addProperty(Field.ServiceName, request.getServiceName());
        awsRequestMetrics.addProperty(Field.ServiceEndpoint, request.getEndpoint());
        setUserAgent(request);
        request.addHeader(HEADER_SDK_TRANSACTION_ID, UUID.randomUUID().toString());
        int requestCount = 0;
        long lastBackoffDelay = 0;
        URI redirectedURI = null;
        AmazonClientException retriedException = null;
        Map<String, String> linkedHashMap = new LinkedHashMap(request.getParameters());
        linkedHashMap = new HashMap(request.getHeaders());
        InputStream originalContent = request.getContent();
        if (originalContent != null && originalContent.markSupported()) {
            originalContent.mark(-1);
        }
        AWSCredentials credentials = executionContext.getCredentials();
        Signer signer = null;
        HttpResponse httpResponse = null;
        HttpRequest httpRequest = null;
        while (true) {
            requestCount++;
            awsRequestMetrics.setCounter(Field.RequestCount, (long) requestCount);
            if (requestCount > 1) {
                request.setParameters(linkedHashMap);
                request.setHeaders(linkedHashMap);
                request.setContent(originalContent);
            }
            if (redirectedURI != null) {
                request.setEndpoint(URI.create(redirectedURI.getScheme() + "://" + redirectedURI.getAuthority()));
                request.setResourcePath(redirectedURI.getPath());
            }
            if (requestCount > 1) {
                try {
                    awsRequestMetrics.startEvent(Field.RetryPauseTime);
                    lastBackoffDelay = pauseBeforeNextRetry(request.getOriginalRequest(), retriedException, requestCount, this.config.getRetryPolicy());
                    awsRequestMetrics.endEvent(Field.RetryPauseTime);
                    InputStream content = request.getContent();
                    if (content != null && content.markSupported()) {
                        content.reset();
                    }
                } catch (Throwable ioe) {
                    if (log.isDebugEnabled()) {
                        log.debug("Unable to execute HTTP request: " + ioe.getMessage(), ioe);
                    }
                    awsRequestMetrics.incrementCounter(Field.Exception);
                    awsRequestMetrics.addProperty(Field.Exception, (Object) ioe);
                    awsRequestMetrics.addProperty(Field.AWSRequestID, null);
                    ace = new AmazonClientException("Unable to execute HTTP request: " + ioe.getMessage(), ioe);
                    AmazonClientException ace;
                    if (shouldRetry(request.getOriginalRequest(), httpRequest.getContent(), ace, requestCount, this.config.getRetryPolicy())) {
                        retriedException = ace;
                        try {
                            resetRequestAfterError(request, ioe);
                            if (!(leaveHttpConnectionOpen || httpResponse == null)) {
                                try {
                                    if (httpResponse.getRawContent() != null) {
                                        httpResponse.getRawContent().close();
                                    }
                                } catch (Throwable e) {
                                    log.warn("Cannot close the response content.", e);
                                }
                            }
                        } catch (Throwable th) {
                            if (!(leaveHttpConnectionOpen || httpResponse == null)) {
                                try {
                                    if (httpResponse.getRawContent() != null) {
                                        httpResponse.getRawContent().close();
                                    }
                                } catch (Throwable e2) {
                                    log.warn("Cannot close the response content.", e2);
                                }
                            }
                        }
                    } else {
                        throw ace;
                    }
                } catch (Throwable e22) {
                    throw ((RuntimeException) handleUnexpectedFailure(e22, awsRequestMetrics));
                } catch (Throwable e222) {
                    throw ((Error) handleUnexpectedFailure(e222, awsRequestMetrics));
                } catch (Throwable th2) {
                    awsRequestMetrics.endEvent(Field.RetryPauseTime);
                }
            }
            request.addHeader(HEADER_SDK_RETRY_INFO, (requestCount - 1) + "/" + lastBackoffDelay);
            if (signer == null) {
                signer = executionContext.getSignerByURI(request.getEndpoint());
            }
            if (!(signer == null || credentials == null)) {
                awsRequestMetrics.startEvent(Field.RequestSigningTime);
                signer.sign(request, credentials);
                awsRequestMetrics.endEvent(Field.RequestSigningTime);
            }
            if (requestLog.isDebugEnabled()) {
                requestLog.debug("Sending Request: " + request.toString());
            }
            httpRequest = this.requestFactory.createHttpRequest(request, this.config, executionContext);
            retriedException = null;
            awsRequestMetrics.startEvent(Field.HttpRequestTime);
            httpResponse = this.httpClient.execute(httpRequest);
            awsRequestMetrics.endEvent(Field.HttpRequestTime);
            if (isRequestSuccessful(httpResponse)) {
                break;
            }
            if (isTemporaryRedirect(httpResponse)) {
                String redirectedLocation = (String) httpResponse.getHeaders().get("Location");
                log.debug("Redirecting to: " + redirectedLocation);
                redirectedURI = URI.create(redirectedLocation);
                awsRequestMetrics.addProperty(Field.StatusCode, Integer.valueOf(httpResponse.getStatusCode()));
                awsRequestMetrics.addProperty(Field.RedirectLocation, (Object) redirectedLocation);
                awsRequestMetrics.addProperty(Field.AWSRequestID, null);
            } else {
                leaveHttpConnectionOpen = errorResponseHandler.needsConnectionLeftOpen();
                AmazonClientException ase = handleErrorResponse(request, errorResponseHandler, httpResponse);
                awsRequestMetrics.addProperty(Field.AWSRequestID, ase.getRequestId());
                awsRequestMetrics.addProperty(Field.AWSErrorCode, ase.getErrorCode());
                awsRequestMetrics.addProperty(Field.StatusCode, Integer.valueOf(ase.getStatusCode()));
                if (shouldRetry(request.getOriginalRequest(), httpRequest.getContent(), ase, requestCount, this.config.getRetryPolicy())) {
                    retriedException = ase;
                    if (RetryUtils.isClockSkewError(ase)) {
                        SDKGlobalConfiguration.setGlobalTimeOffset(parseClockSkewOffset(httpResponse, ase));
                    }
                    resetRequestAfterError(request, ase);
                } else {
                    throw ase;
                }
            }
            if (!(leaveHttpConnectionOpen || httpResponse == null)) {
                try {
                    if (httpResponse.getRawContent() != null) {
                        httpResponse.getRawContent().close();
                    }
                } catch (Throwable e2222) {
                    log.warn("Cannot close the response content.", e2222);
                }
            }
        }
        awsRequestMetrics.addProperty(Field.StatusCode, Integer.valueOf(httpResponse.getStatusCode()));
        leaveHttpConnectionOpen = responseHandler.needsConnectionLeftOpen();
        Response<T> response = new Response(handleResponse(request, responseHandler, httpResponse, executionContext), httpResponse);
        if (!(leaveHttpConnectionOpen || httpResponse == null)) {
            try {
                if (httpResponse.getRawContent() != null) {
                    httpResponse.getRawContent().close();
                }
            } catch (Throwable e22222) {
                log.warn("Cannot close the response content.", e22222);
            }
        }
        return response;
    }

    private <T extends Throwable> T handleUnexpectedFailure(T t, AWSRequestMetrics awsRequestMetrics) {
        awsRequestMetrics.incrementCounter(Field.Exception);
        awsRequestMetrics.addProperty(Field.Exception, (Object) t);
        return t;
    }

    void resetRequestAfterError(Request<?> request, Exception cause) throws AmazonClientException {
        if (request.getContent() != null) {
            if (request.getContent().markSupported()) {
                try {
                    request.getContent().reset();
                    return;
                } catch (IOException e) {
                    throw new AmazonClientException("Encountered an exception and couldn't reset the stream to retry", cause);
                }
            }
            throw new AmazonClientException("Encountered an exception and stream is not resettable", cause);
        }
    }

    void setUserAgent(Request<?> request) {
        String userAgent = ClientConfiguration.DEFAULT_USER_AGENT;
        AmazonWebServiceRequest awsreq = request.getOriginalRequest();
        if (awsreq != null) {
            RequestClientOptions opts = awsreq.getRequestClientOptions();
            if (opts != null) {
                String userAgentMarker = opts.getClientMarker(Marker.USER_AGENT);
                if (userAgentMarker != null) {
                    userAgent = createUserAgentString(userAgent, userAgentMarker);
                }
            }
        }
        if (!ClientConfiguration.DEFAULT_USER_AGENT.equals(this.config.getUserAgent())) {
            userAgent = createUserAgentString(userAgent, this.config.getUserAgent());
        }
        request.addHeader("User-Agent", userAgent);
    }

    static String createUserAgentString(String existingUserAgentString, String userAgent) {
        return existingUserAgentString.contains(userAgent) ? existingUserAgentString : existingUserAgentString.trim() + " " + userAgent.trim();
    }

    public void shutdown() {
        this.httpClient.shutdown();
    }

    private boolean shouldRetry(AmazonWebServiceRequest originalRequest, InputStream inputStream, AmazonClientException exception, int requestCount, RetryPolicy retryPolicy) {
        int retries = requestCount - 1;
        int maxErrorRetry = this.config.getMaxErrorRetry();
        if (maxErrorRetry < 0 || !retryPolicy.isMaxErrorRetryInClientConfigHonored()) {
            maxErrorRetry = retryPolicy.getMaxErrorRetry();
        }
        if (retries >= maxErrorRetry) {
            return false;
        }
        if (inputStream == null || inputStream.markSupported()) {
            return retryPolicy.getRetryCondition().shouldRetry(originalRequest, exception, retries);
        }
        if (!log.isDebugEnabled()) {
            return false;
        }
        log.debug("Content not repeatable");
        return false;
    }

    private static boolean isTemporaryRedirect(HttpResponse response) {
        String location = (String) response.getHeaders().get("Location");
        return (response.getStatusCode() != StatusLine.HTTP_TEMP_REDIRECT || location == null || location.isEmpty()) ? false : true;
    }

    private boolean isRequestSuccessful(HttpResponse response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 200 && statusCode < 300;
    }

    <T> T handleResponse(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> responseHandler, HttpResponse response, ExecutionContext executionContext) throws IOException {
        AWSRequestMetrics awsRequestMetrics;
        try {
            awsRequestMetrics = executionContext.getAwsRequestMetrics();
            awsRequestMetrics.startEvent(Field.ResponseProcessingTime);
            AmazonWebServiceResponse<? extends T> awsResponse = (AmazonWebServiceResponse) responseHandler.handle(response);
            awsRequestMetrics.endEvent(Field.ResponseProcessingTime);
            if (awsResponse == null) {
                throw new RuntimeException("Unable to unmarshall response metadata. Response Code: " + response.getStatusCode() + ", Response Text: " + response.getStatusText());
            }
            if (requestLog.isDebugEnabled()) {
                requestLog.debug("Received successful response: " + response.getStatusCode() + ", AWS Request ID: " + awsResponse.getRequestId());
            }
            awsRequestMetrics.addProperty(Field.AWSRequestID, awsResponse.getRequestId());
            return awsResponse.getResult();
        } catch (CRC32MismatchException e) {
            throw e;
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new AmazonClientException("Unable to unmarshall response (" + e3.getMessage() + "). Response Code: " + response.getStatusCode() + ", Response Text: " + response.getStatusText(), e3);
        } catch (Throwable th) {
            awsRequestMetrics.endEvent(Field.ResponseProcessingTime);
        }
    }

    AmazonServiceException handleErrorResponse(Request<?> request, HttpResponseHandler<AmazonServiceException> errorResponseHandler, HttpResponse response) throws IOException {
        AmazonServiceException exception;
        int status = response.getStatusCode();
        try {
            exception = (AmazonServiceException) errorResponseHandler.handle(response);
            requestLog.debug("Received error response: " + exception.toString());
        } catch (Exception e) {
            if (status == 413) {
                exception = new AmazonServiceException("Request entity too large");
                exception.setServiceName(request.getServiceName());
                exception.setStatusCode(413);
                exception.setErrorType(ErrorType.Client);
                exception.setErrorCode("Request entity too large");
            } else if (status == 503 && "Service Unavailable".equalsIgnoreCase(response.getStatusText())) {
                exception = new AmazonServiceException("Service unavailable");
                exception.setServiceName(request.getServiceName());
                exception.setStatusCode(503);
                exception.setErrorType(ErrorType.Service);
                exception.setErrorCode("Service unavailable");
            } else if (e instanceof IOException) {
                throw ((IOException) e);
            } else {
                throw new AmazonClientException("Unable to unmarshall error response (" + e.getMessage() + "). Response Code: " + status + ", Response Text: " + response.getStatusText(), e);
            }
        }
        exception.setStatusCode(status);
        exception.setServiceName(request.getServiceName());
        exception.fillInStackTrace();
        return exception;
    }

    private long pauseBeforeNextRetry(AmazonWebServiceRequest originalRequest, AmazonClientException previousException, int requestCount, RetryPolicy retryPolicy) {
        int retries = (requestCount - 1) - 1;
        long delay = retryPolicy.getBackoffStrategy().delayBeforeNextRetry(originalRequest, previousException, retries);
        if (log.isDebugEnabled()) {
            log.debug("Retriable error detected, will retry in " + delay + "ms, attempt number: " + retries);
        }
        try {
            Thread.sleep(delay);
            return delay;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AmazonClientException(e.getMessage(), e);
        }
    }

    private String getServerDateFromException(String body) {
        int endPos;
        int startPos = body.indexOf("(");
        if (body.contains(" + 15")) {
            endPos = body.indexOf(" + 15");
        } else {
            endPos = body.indexOf(" - 15");
        }
        return body.substring(startPos + 1, endPos);
    }

    int parseClockSkewOffset(HttpResponse response, AmazonServiceException exception) {
        Date serverDate;
        Date deviceDate = new Date();
        String serverDateStr = null;
        String responseDateHeader = (String) response.getHeaders().get("Date");
        if (responseDateHeader != null) {
            try {
                if (!responseDateHeader.isEmpty()) {
                    serverDateStr = responseDateHeader;
                    serverDate = DateUtils.parseRFC822Date(serverDateStr);
                    return (int) ((deviceDate.getTime() - serverDate.getTime()) / 1000);
                }
            } catch (RuntimeException e) {
                log.warn("Unable to parse clock skew offset from response: " + serverDateStr, e);
                return 0;
            }
        }
        serverDate = DateUtils.parseCompressedISO8601Date(getServerDateFromException(exception.getMessage()));
        return (int) ((deviceDate.getTime() - serverDate.getTime()) / 1000);
    }

    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }

    public RequestMetricCollector getRequestMetricCollector() {
        return this.requestMetricCollector;
    }
}
