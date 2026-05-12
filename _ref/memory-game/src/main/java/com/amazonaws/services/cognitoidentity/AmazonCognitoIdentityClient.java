package com.amazonaws.services.cognitoidentity;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.http.JsonResponseHandler;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdResult;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import com.amazonaws.services.cognitoidentity.model.transform.ExternalServiceExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetCredentialsForIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetCredentialsForIdentityResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetIdRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetIdResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.InternalErrorExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.InvalidIdentityPoolConfigurationExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.InvalidParameterExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.NotAuthorizedExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ResourceConflictExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.TooManyRequestsExceptionUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.AWSRequestMetrics.Field;
import java.util.ArrayList;
import java.util.List;

public class AmazonCognitoIdentityClient extends AmazonWebServiceClient implements AmazonCognitoIdentity {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    @Deprecated
    public AmazonCognitoIdentityClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AmazonCognitoIdentityClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AmazonCognitoIdentityClient(AWSCredentials awsCredentials) {
        this(awsCredentials, new ClientConfiguration());
    }

    public AmazonCognitoIdentityClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(awsCredentials), clientConfiguration);
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider awsCredentialsProvider) {
        this(awsCredentialsProvider, new ClientConfiguration());
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(awsCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonCognitoIdentityClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = awsCredentialsProvider;
        init();
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = awsCredentialsProvider;
        init();
    }

    private void init() {
        this.jsonErrorUnmarshallers = new ArrayList();
        this.jsonErrorUnmarshallers.add(new ExternalServiceExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalErrorExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidIdentityPoolConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidParameterExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotAuthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TooManyRequestsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("cognito-identity.us-east-1.amazonaws.com");
        HandlerChainFactory chainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(chainFactory.newRequestHandlerChain("/com/amazonaws/services/cognitoidentity/request.handlers"));
        this.requestHandler2s.addAll(chainFactory.newRequestHandler2Chain("/com/amazonaws/services/cognitoidentity/request.handler2s"));
    }

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration orig) {
        return orig;
    }

    public GetCredentialsForIdentityResult getCredentialsForIdentity(GetCredentialsForIdentityRequest getCredentialsForIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext executionContext = createExecutionContext((AmazonWebServiceRequest) getCredentialsForIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<GetCredentialsForIdentityRequest> request = null;
        Response<GetCredentialsForIdentityResult> response = null;
        try {
            awsRequestMetrics.startEvent(Field.RequestMarshallTime);
            request = new GetCredentialsForIdentityRequestMarshaller().marshall(getCredentialsForIdentityRequest);
            request.setAWSRequestMetrics(awsRequestMetrics);
            awsRequestMetrics.endEvent(Field.RequestMarshallTime);
            response = invoke(request, new JsonResponseHandler(new GetCredentialsForIdentityResultJsonUnmarshaller()), executionContext);
            GetCredentialsForIdentityResult getCredentialsForIdentityResult = (GetCredentialsForIdentityResult) response.getAwsResponse();
            endClientExecution(awsRequestMetrics, request, response, true);
            return getCredentialsForIdentityResult;
        } catch (Throwable th) {
            endClientExecution(awsRequestMetrics, request, response, true);
        }
    }

    public GetIdResult getId(GetIdRequest getIdRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext executionContext = createExecutionContext((AmazonWebServiceRequest) getIdRequest);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<GetIdRequest> request = null;
        Response<GetIdResult> response = null;
        try {
            awsRequestMetrics.startEvent(Field.RequestMarshallTime);
            request = new GetIdRequestMarshaller().marshall(getIdRequest);
            request.setAWSRequestMetrics(awsRequestMetrics);
            awsRequestMetrics.endEvent(Field.RequestMarshallTime);
            response = invoke(request, new JsonResponseHandler(new GetIdResultJsonUnmarshaller()), executionContext);
            GetIdResult getIdResult = (GetIdResult) response.getAwsResponse();
            endClientExecution(awsRequestMetrics, request, response, true);
            return getIdResult;
        } catch (Throwable th) {
            endClientExecution(awsRequestMetrics, request, response, true);
        }
    }

    public GetOpenIdTokenResult getOpenIdToken(GetOpenIdTokenRequest getOpenIdTokenRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext executionContext = createExecutionContext((AmazonWebServiceRequest) getOpenIdTokenRequest);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<GetOpenIdTokenRequest> request = null;
        Response<GetOpenIdTokenResult> response = null;
        try {
            awsRequestMetrics.startEvent(Field.RequestMarshallTime);
            request = new GetOpenIdTokenRequestMarshaller().marshall(getOpenIdTokenRequest);
            request.setAWSRequestMetrics(awsRequestMetrics);
            awsRequestMetrics.endEvent(Field.RequestMarshallTime);
            response = invoke(request, new JsonResponseHandler(new GetOpenIdTokenResultJsonUnmarshaller()), executionContext);
            GetOpenIdTokenResult getOpenIdTokenResult = (GetOpenIdTokenResult) response.getAwsResponse();
            endClientExecution(awsRequestMetrics, request, response, true);
            return getOpenIdTokenResult;
        } catch (Throwable th) {
            endClientExecution(awsRequestMetrics, request, response, true);
        }
    }

    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request) {
        return this.client.getResponseMetadataForRequest(request);
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> responseHandler, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(Field.CredentialsRequestTime);
        try {
            AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
            AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
            if (!(originalRequest == null || originalRequest.getRequestCredentials() == null)) {
                credentials = originalRequest.getRequestCredentials();
            }
            executionContext.setCredentials(credentials);
            return this.client.execute(request, responseHandler, new JsonErrorResponseHandler(this.jsonErrorUnmarshallers), executionContext);
        } finally {
            awsRequestMetrics.endEvent(Field.CredentialsRequestTime);
        }
    }
}
