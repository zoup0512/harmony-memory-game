package com.amazonaws;

import com.amazonaws.auth.RegionAwareSigner;
import com.amazonaws.auth.Signer;
import com.amazonaws.auth.SignerFactory;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.http.AmazonHttpClient;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.metrics.AwsSdkMetrics;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.regions.Region;
import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.AWSRequestMetrics.Field;
import com.amazonaws.util.AwsHostNameUtils;
import com.amazonaws.util.Classes;
import com.amazonaws.util.StringUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AmazonWebServiceClient {
    private static final String AMAZON = "Amazon";
    private static final String AWS = "AWS";
    public static final boolean LOGGING_AWS_REQUEST_METRIC = true;
    private static final Log log = LogFactory.getLog(AmazonWebServiceClient.class);
    protected AmazonHttpClient client;
    protected ClientConfiguration clientConfiguration;
    protected volatile URI endpoint;
    protected final List<RequestHandler2> requestHandler2s;
    private volatile String serviceName;
    private volatile Signer signer;
    private volatile String signerRegionOverride;
    protected int timeOffset;

    protected AmazonWebServiceClient(ClientConfiguration clientConfiguration) {
        this(clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    protected AmazonWebServiceClient(ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        this(clientConfiguration, new UrlHttpClient(clientConfiguration), null);
    }

    protected AmazonWebServiceClient(ClientConfiguration clientConfiguration, HttpClient httpClient) {
        this.clientConfiguration = clientConfiguration;
        this.client = new AmazonHttpClient(clientConfiguration, httpClient);
        this.requestHandler2s = new CopyOnWriteArrayList();
    }

    @Deprecated
    protected AmazonWebServiceClient(ClientConfiguration clientConfiguration, HttpClient httpClient, RequestMetricCollector requestMetricCollector) {
        this.clientConfiguration = clientConfiguration;
        this.client = new AmazonHttpClient(clientConfiguration, httpClient, requestMetricCollector);
        this.requestHandler2s = new CopyOnWriteArrayList();
    }

    protected Signer getSigner() {
        return this.signer;
    }

    public void setEndpoint(String endpoint) throws IllegalArgumentException {
        URI uri = toURI(endpoint);
        Signer signer = computeSignerByURI(uri, this.signerRegionOverride, false);
        synchronized (this) {
            this.endpoint = uri;
            this.signer = signer;
        }
    }

    private URI toURI(String endpoint) throws IllegalArgumentException {
        if (!endpoint.contains("://")) {
            endpoint = this.clientConfiguration.getProtocol().toString() + "://" + endpoint;
        }
        try {
            return new URI(endpoint);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    public void setEndpoint(String endpoint, String serviceName, String regionId) {
        URI uri = toURI(endpoint);
        Signer signer = computeSignerByServiceRegion(serviceName, regionId, regionId, true);
        synchronized (this) {
            this.signer = signer;
            this.endpoint = uri;
            this.signerRegionOverride = regionId;
        }
    }

    @Deprecated
    protected void configSigner(URI uri) {
    }

    @Deprecated
    protected void configSigner(String serviceName, String regionId) {
    }

    public Signer getSignerByURI(URI uri) {
        return computeSignerByURI(uri, this.signerRegionOverride, true);
    }

    private Signer computeSignerByURI(URI uri, String signerRegionOverride, boolean isRegionIdAsSignerParam) {
        if (uri == null) {
            throw new IllegalArgumentException("Endpoint is not set. Use setEndpoint to set an endpoint before performing any request.");
        }
        String service = getServiceNameIntern();
        return computeSignerByServiceRegion(service, AwsHostNameUtils.parseRegionName(uri.getHost(), service), signerRegionOverride, isRegionIdAsSignerParam);
    }

    private Signer computeSignerByServiceRegion(String serviceName, String regionId, String signerRegionOverride, boolean isRegionIdAsSignerParam) {
        Signer signer;
        String signerType = this.clientConfiguration.getSignerOverride();
        if (signerType == null) {
            signer = SignerFactory.getSigner(serviceName, regionId);
        } else {
            signer = SignerFactory.getSignerByTypeAndService(signerType, serviceName);
        }
        if (signer instanceof RegionAwareSigner) {
            RegionAwareSigner regionAwareSigner = (RegionAwareSigner) signer;
            if (signerRegionOverride != null) {
                regionAwareSigner.setRegionName(signerRegionOverride);
            } else if (regionId != null && isRegionIdAsSignerParam) {
                regionAwareSigner.setRegionName(regionId);
            }
        }
        return signer;
    }

    public void setRegion(Region region) throws IllegalArgumentException {
        if (region == null) {
            throw new IllegalArgumentException("No region provided");
        }
        String serviceEndpoint;
        String serviceName = getServiceNameIntern();
        if (region.isServiceSupported(serviceName)) {
            serviceEndpoint = region.getServiceEndpoint(serviceName);
            int protocolIdx = serviceEndpoint.indexOf("://");
            if (protocolIdx >= 0) {
                serviceEndpoint = serviceEndpoint.substring("://".length() + protocolIdx);
            }
        } else {
            serviceEndpoint = String.format("%s.%s.%s", new Object[]{serviceName, region.getName(), region.getDomain()});
            log.info("{" + serviceName + ", " + region.getName() + "} was not found in region metadata, trying to construct an endpoint using the standard pattern for this region: '" + serviceEndpoint + "'.");
        }
        URI uri = toURI(serviceEndpoint);
        Signer signer = computeSignerByServiceRegion(serviceName, region.getName(), this.signerRegionOverride, false);
        synchronized (this) {
            this.endpoint = uri;
            this.signer = signer;
        }
    }

    @Deprecated
    public void setConfiguration(ClientConfiguration clientConfiguration) {
        AmazonHttpClient existingClient = this.client;
        RequestMetricCollector requestMetricCollector = null;
        if (existingClient != null) {
            requestMetricCollector = existingClient.getRequestMetricCollector();
            existingClient.shutdown();
        }
        this.clientConfiguration = clientConfiguration;
        this.client = new AmazonHttpClient(clientConfiguration, requestMetricCollector);
    }

    public void shutdown() {
        this.client.shutdown();
    }

    @Deprecated
    public void addRequestHandler(RequestHandler requestHandler) {
        this.requestHandler2s.add(RequestHandler2.adapt(requestHandler));
    }

    public void addRequestHandler(RequestHandler2 requestHandler2) {
        this.requestHandler2s.add(requestHandler2);
    }

    @Deprecated
    public void removeRequestHandler(RequestHandler requestHandler) {
        this.requestHandler2s.remove(RequestHandler2.adapt(requestHandler));
    }

    public void removeRequestHandler(RequestHandler2 requestHandler2) {
        this.requestHandler2s.remove(requestHandler2);
    }

    protected ExecutionContext createExecutionContext(AmazonWebServiceRequest req) {
        boolean isMetricsEnabled = isRequestMetricsEnabled(req) || isProfilingEnabled();
        return new ExecutionContext(this.requestHandler2s, isMetricsEnabled, this);
    }

    protected final ExecutionContext createExecutionContext(Request<?> req) {
        return createExecutionContext(req.getOriginalRequest());
    }

    @Deprecated
    protected final ExecutionContext createExecutionContext() {
        boolean isMetricsEnabled = isRMCEnabledAtClientOrSdkLevel() || isProfilingEnabled();
        return new ExecutionContext(this.requestHandler2s, isMetricsEnabled, this);
    }

    @Deprecated
    protected static boolean isProfilingEnabled() {
        return System.getProperty(SDKGlobalConfiguration.PROFILING_SYSTEM_PROPERTY) != null;
    }

    @Deprecated
    protected final boolean isRequestMetricsEnabled(AmazonWebServiceRequest req) {
        RequestMetricCollector c = req.getRequestMetricCollector();
        if (c == null || !c.isEnabled()) {
            return isRMCEnabledAtClientOrSdkLevel();
        }
        return true;
    }

    @Deprecated
    private boolean isRMCEnabledAtClientOrSdkLevel() {
        RequestMetricCollector c = requestMetricCollector();
        return c != null && c.isEnabled();
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public AmazonWebServiceClient withTimeOffset(int timeOffset) {
        setTimeOffset(timeOffset);
        return this;
    }

    public int getTimeOffset() {
        return this.timeOffset;
    }

    @Deprecated
    public RequestMetricCollector getRequestMetricsCollector() {
        return this.client.getRequestMetricCollector();
    }

    @Deprecated
    protected RequestMetricCollector requestMetricCollector() {
        RequestMetricCollector mc = this.client.getRequestMetricCollector();
        return mc == null ? AwsSdkMetrics.getRequestMetricCollector() : mc;
    }

    @Deprecated
    protected final RequestMetricCollector findRequestMetricCollector(Request<?> req) {
        RequestMetricCollector mc = req.getOriginalRequest().getRequestMetricCollector();
        if (mc != null) {
            return mc;
        }
        mc = getRequestMetricsCollector();
        return mc == null ? AwsSdkMetrics.getRequestMetricCollector() : mc;
    }

    @Deprecated
    protected final void endClientExecution(AWSRequestMetrics awsRequestMetrics, Request<?> request, Response<?> response) {
        endClientExecution(awsRequestMetrics, request, response, false);
    }

    @Deprecated
    protected final void endClientExecution(AWSRequestMetrics awsRequestMetrics, Request<?> request, Response<?> response, boolean loggingAwsRequestMetrics) {
        if (request != null) {
            awsRequestMetrics.endEvent(Field.ClientExecuteTime);
            awsRequestMetrics.getTimingInfo().endTiming();
            findRequestMetricCollector(request).collectMetrics(request, response);
        }
        if (loggingAwsRequestMetrics) {
            awsRequestMetrics.log();
        }
    }

    @Deprecated
    protected String getServiceAbbreviation() {
        return getServiceNameIntern();
    }

    public String getServiceName() {
        return getServiceNameIntern();
    }

    protected String getServiceNameIntern() {
        if (this.serviceName == null) {
            synchronized (this) {
                if (this.serviceName == null) {
                    String computeServiceName = computeServiceName();
                    this.serviceName = computeServiceName;
                    return computeServiceName;
                }
            }
        }
        return this.serviceName;
    }

    public final void setServiceNameIntern(String serviceName) {
        this.serviceName = serviceName;
    }

    private String computeServiceName() {
        String httpClientName = Classes.childClassOf(AmazonWebServiceClient.class, this).getSimpleName();
        String service = ServiceNameFactory.getServiceName(httpClientName);
        if (service != null) {
            return service;
        }
        int len;
        int j = httpClientName.indexOf("JavaClient");
        if (j == -1) {
            j = httpClientName.indexOf("Client");
            if (j == -1) {
                throw new IllegalStateException("Unrecognized suffix for the AWS http client class name " + httpClientName);
            }
        }
        int i = httpClientName.indexOf(AMAZON);
        if (i == -1) {
            i = httpClientName.indexOf(AWS);
            if (i == -1) {
                throw new IllegalStateException("Unrecognized prefix for the AWS http client class name " + httpClientName);
            }
            len = AWS.length();
        } else {
            len = AMAZON.length();
        }
        if (i < j) {
            return StringUtils.lowerCase(httpClientName.substring(i + len, j));
        }
        throw new IllegalStateException("Unrecognized AWS http client class name " + httpClientName);
    }

    public final String getSignerRegionOverride() {
        return this.signerRegionOverride;
    }

    public final void setSignerRegionOverride(String signerRegionOverride) {
        Signer signer = computeSignerByURI(this.endpoint, signerRegionOverride, true);
        synchronized (this) {
            this.signer = signer;
            this.signerRegionOverride = signerRegionOverride;
        }
    }
}
