package com.amazonaws;

import com.amazonaws.http.HttpMethodName;
import com.amazonaws.util.AWSRequestMetrics;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultRequest<T> implements Request<T> {
    private InputStream content;
    private URI endpoint;
    private Map<String, String> headers;
    private HttpMethodName httpMethod;
    private AWSRequestMetrics metrics;
    private final AmazonWebServiceRequest originalRequest;
    private Map<String, String> parameters;
    private String resourcePath;
    private String serviceName;
    private int timeOffset;

    public DefaultRequest(AmazonWebServiceRequest originalRequest, String serviceName) {
        this.parameters = new LinkedHashMap();
        this.headers = new HashMap();
        this.httpMethod = HttpMethodName.POST;
        this.serviceName = serviceName;
        this.originalRequest = originalRequest;
    }

    public DefaultRequest(String serviceName) {
        this(null, serviceName);
    }

    public AmazonWebServiceRequest getOriginalRequest() {
        return this.originalRequest;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void addParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public Request<T> withParameter(String name, String value) {
        addParameter(name, value);
        return this;
    }

    public HttpMethodName getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(HttpMethodName httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    public URI getEndpoint() {
        return this.endpoint;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public InputStream getContent() {
        return this.content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    public int getTimeOffset() {
        return this.timeOffset;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public Request<T> withTimeOffset(int timeOffset) {
        setTimeOffset(timeOffset);
        return this;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getHttpMethod()).append(" ");
        builder.append(getEndpoint()).append(" ");
        String resourcePath = getResourcePath();
        if (resourcePath == null) {
            builder.append("/");
        } else {
            if (!resourcePath.startsWith("/")) {
                builder.append("/");
            }
            builder.append(resourcePath);
        }
        builder.append(" ");
        if (!getParameters().isEmpty()) {
            builder.append("Parameters: (");
            for (String key : getParameters().keySet()) {
                builder.append(key).append(": ").append((String) getParameters().get(key)).append(", ");
            }
            builder.append(") ");
        }
        if (!getHeaders().isEmpty()) {
            builder.append("Headers: (");
            for (String key2 : getHeaders().keySet()) {
                builder.append(key2).append(": ").append((String) getHeaders().get(key2)).append(", ");
            }
            builder.append(") ");
        }
        return builder.toString();
    }

    @Deprecated
    public AWSRequestMetrics getAWSRequestMetrics() {
        return this.metrics;
    }

    @Deprecated
    public void setAWSRequestMetrics(AWSRequestMetrics metrics) {
        if (this.metrics == null) {
            this.metrics = metrics;
            return;
        }
        throw new IllegalStateException("AWSRequestMetrics has already been set on this request");
    }
}
