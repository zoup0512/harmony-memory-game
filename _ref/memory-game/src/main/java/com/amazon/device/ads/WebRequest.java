package com.amazon.device.ads;

import com.mopub.common.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

abstract class WebRequest {
    private static final String CHARSET_KEY = "charset";
    public static final String CHARSET_UTF_16 = "UTF-16";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE_CSS = "text/css";
    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final String CONTENT_TYPE_JAVASCRIPT = "application/javascript";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_PLAIN_TEXT = "text/plain";
    public static final int DEFAULT_PORT = -1;
    public static final int DEFAULT_TIMEOUT = 20000;
    private static final String HEADER_ACCEPT_KEY = "Accept";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String LOGTAG = WebRequest.class.getSimpleName();
    String acceptContentType;
    String charset;
    String contentType;
    private boolean disconnectEnabled;
    protected final HashMap<String, String> headers;
    private HttpMethod httpMethod;
    boolean logRequestBodyEnabled;
    boolean logResponseEnabled;
    boolean logSessionIdEnabled;
    private String logTag;
    protected boolean logUrlEnabled;
    private final MobileAdsLogger logger;
    private MetricsCollector metricsCollector;
    private String nonSecureHost;
    private String path;
    private int port;
    protected HashMap<String, String> postParameters;
    protected QueryStringParameters queryStringParameters;
    String requestBody;
    protected boolean secure;
    private String secureHost;
    protected MetricType serviceCallLatencyMetric;
    private int timeout;
    private String urlString;

    public enum HttpMethod {
        GET(HttpRequest.METHOD_GET),
        POST(HttpRequest.METHOD_POST);
        
        private final String methodString;

        private HttpMethod(String str) {
            this.methodString = str;
        }

        public String toString() {
            return this.methodString;
        }
    }

    static class QueryStringParameters {
        private final HashMap<String, String> params = new HashMap();
        private String rawAppendage;

        QueryStringParameters() {
        }

        int size() {
            return this.params.size();
        }

        void setRawAppendage(String str) {
            this.rawAppendage = str;
        }

        void putUrlEncoded(String str, boolean z) {
            putUrlEncoded(str, Boolean.toString(z));
        }

        void putUrlEncodedIfNotNullOrEmpty(String str, String str2) {
            putUrlEncodedIfTrue(str, str2, !StringUtils.isNullOrEmpty(str2));
        }

        void putUrlEncodedIfTrue(String str, String str2, boolean z) {
            if (z) {
                putUrlEncoded(str, str2);
            }
        }

        String get(String str) {
            if (!StringUtils.isNullOrWhiteSpace(str)) {
                return (String) this.params.get(str);
            }
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }

        void putUrlEncoded(String str, String str2) {
            if (StringUtils.isNullOrWhiteSpace(str)) {
                throw new IllegalArgumentException("The name must not be null or empty string.");
            } else if (str2 == null) {
                this.params.remove(str);
            } else {
                this.params.put(str, str2);
            }
        }

        String putUnencoded(String str, String str2) {
            WebUtils2 webUtils2 = new WebUtils2();
            String uRLEncodedString = webUtils2.getURLEncodedString(str);
            putUrlEncoded(uRLEncodedString, webUtils2.getURLEncodedString(str2));
            return uRLEncodedString;
        }

        void append(StringBuilder stringBuilder) {
            if (size() != 0 || !StringUtils.isNullOrEmpty(this.rawAppendage)) {
                stringBuilder.append("?");
                Object obj = 1;
                for (Entry entry : this.params.entrySet()) {
                    Object obj2;
                    if (obj != null) {
                        obj2 = null;
                    } else {
                        stringBuilder.append("&");
                        obj2 = obj;
                    }
                    stringBuilder.append((String) entry.getKey());
                    stringBuilder.append("=");
                    stringBuilder.append((String) entry.getValue());
                    obj = obj2;
                }
                if (this.rawAppendage != null && !this.rawAppendage.equals("")) {
                    if (size() != 0) {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(this.rawAppendage);
                }
            }
        }
    }

    public class WebRequestException extends Exception {
        private static final long serialVersionUID = -4980265484926465548L;
        private final WebRequestStatus status;

        protected WebRequestException(WebRequestStatus webRequestStatus, String str, Throwable th) {
            super(str, th);
            this.status = webRequestStatus;
        }

        protected WebRequestException(WebRequest webRequest, WebRequestStatus webRequestStatus, String str) {
            this(webRequestStatus, str, null);
        }

        public WebRequestStatus getStatus() {
            return this.status;
        }
    }

    public static class WebRequestFactory {
        public WebRequest createWebRequest() {
            return new HttpURLConnectionWebRequest();
        }

        public WebRequest createJSONGetWebRequest() {
            WebRequest createWebRequest = createWebRequest();
            createWebRequest.setHttpMethod(HttpMethod.GET);
            createWebRequest.putHeader("Accept", "application/json");
            return createWebRequest;
        }

        public WebRequest createJSONPostWebRequest() {
            WebRequest createWebRequest = createWebRequest();
            createWebRequest.convertToJSONPostRequest();
            return createWebRequest;
        }
    }

    class WebRequestInputStream extends InputStream {
        private final InputStream decoratedStream;

        public WebRequestInputStream(InputStream inputStream) {
            this.decoratedStream = inputStream;
        }

        public int read() {
            return this.decoratedStream.read();
        }

        public void close() {
            this.decoratedStream.close();
            if (WebRequest.this.disconnectEnabled) {
                WebRequest.this.closeConnection();
            }
        }
    }

    public enum WebRequestStatus {
        NETWORK_FAILURE,
        NETWORK_TIMEOUT,
        MALFORMED_URL,
        INVALID_CLIENT_PROTOCOL,
        UNSUPPORTED_ENCODING
    }

    public class WebResponse {
        private String httpStatus;
        private int httpStatusCode;
        private WebRequestInputStream inputStream;

        protected WebResponse() {
        }

        protected void setInputStream(InputStream inputStream) {
            this.inputStream = new WebRequestInputStream(inputStream);
        }

        public ResponseReader getResponseReader() {
            ResponseReader responseReader = new ResponseReader(this.inputStream);
            responseReader.enableLog(WebRequest.this.logResponseEnabled);
            responseReader.setExternalLogTag(WebRequest.this.getLogTag());
            return responseReader;
        }

        public int getHttpStatusCode() {
            return this.httpStatusCode;
        }

        protected void setHttpStatusCode(int i) {
            this.httpStatusCode = i;
        }

        public boolean isHttpStatusCodeOK() {
            return getHttpStatusCode() == 200;
        }

        public String getHttpStatus() {
            return this.httpStatus;
        }

        protected void setHttpStatus(String str) {
            this.httpStatus = str;
        }
    }

    protected abstract void closeConnection();

    protected abstract WebResponse doHttpNetworkCall(URL url);

    protected abstract String getSubLogTag();

    WebRequest() {
        this.requestBody = null;
        this.acceptContentType = null;
        this.contentType = null;
        this.charset = null;
        this.urlString = null;
        this.secureHost = null;
        this.nonSecureHost = null;
        this.path = null;
        this.port = -1;
        this.httpMethod = HttpMethod.GET;
        this.timeout = 20000;
        this.logRequestBodyEnabled = false;
        this.logResponseEnabled = false;
        this.logUrlEnabled = false;
        this.logSessionIdEnabled = false;
        this.secure = false;
        this.logTag = LOGTAG;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(this.logTag);
        this.queryStringParameters = new QueryStringParameters();
        this.headers = new HashMap();
        this.postParameters = new HashMap();
        this.secure = Settings.getInstance().getBoolean("tlsEnabled", false);
        this.disconnectEnabled = true;
    }

    public void convertToJSONPostRequest() {
        setHttpMethod(HttpMethod.POST);
        putHeader("Accept", "application/json");
        putHeader("Content-Type", "application/json; charset=UTF-8");
    }

    public WebResponse makeCall() {
        if (ThreadUtils.isOnMainThread()) {
            this.logger.e("The network request should not be performed on the main thread.");
        }
        setContentTypeHeaders();
        String url = getUrl();
        try {
            URL createURL = createURL(url);
            writeMetricStart(this.serviceCallLatencyMetric);
            try {
                WebResponse doHttpNetworkCall = doHttpNetworkCall(createURL);
                writeMetricStop(this.serviceCallLatencyMetric);
                if (this.logResponseEnabled) {
                    this.logger.d("Response: %s %s", Integer.valueOf(doHttpNetworkCall.getHttpStatusCode()), doHttpNetworkCall.getHttpStatus());
                }
                return doHttpNetworkCall;
            } catch (WebRequestException e) {
                throw e;
            } catch (Throwable th) {
                writeMetricStop(this.serviceCallLatencyMetric);
            }
        } catch (Throwable e2) {
            this.logger.e("Problem with URI syntax: %s", e2.getMessage());
            throw new WebRequestException(WebRequestStatus.MALFORMED_URL, "Could not construct URL from String " + url, e2);
        }
    }

    public void enableLogUrl(boolean z) {
        this.logUrlEnabled = z;
    }

    public void enableLogRequestBody(boolean z) {
        this.logRequestBodyEnabled = z;
    }

    public void enableLogResponse(boolean z) {
        this.logResponseEnabled = z;
    }

    public void enableLogSessionID(boolean z) {
        this.logSessionIdEnabled = z;
    }

    public void enableLog(boolean z) {
        enableLogUrl(z);
        enableLogRequestBody(z);
        enableLogResponse(z);
    }

    protected void logUrl(String str) {
        if (this.logUrlEnabled) {
            this.logger.d("%s %s", getHttpMethod(), str);
        }
    }

    public String getQueryParameter(String str) {
        return this.queryStringParameters.get(str);
    }

    public void putUrlEncodedQueryParameter(String str, String str2) {
        this.queryStringParameters.putUrlEncoded(str, str2);
    }

    public String putUnencodedQueryParameter(String str, String str2) {
        return this.queryStringParameters.putUnencoded(str, str2);
    }

    public void setQueryStringParameters(QueryStringParameters queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public String getPostParameter(String str) {
        if (!StringUtils.isNullOrWhiteSpace(str)) {
            return (String) this.postParameters.get(str);
        }
        throw new IllegalArgumentException("The name must not be null or empty string.");
    }

    public void putPostParameter(String str, String str2) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        } else if (str2 == null) {
            this.postParameters.remove(str);
        } else {
            this.postParameters.put(str, str2);
        }
    }

    public String getHeader(String str) {
        if (!StringUtils.isNullOrWhiteSpace(str)) {
            return (String) this.headers.get(str);
        }
        throw new IllegalArgumentException("The name must not be null or empty string.");
    }

    public void putHeader(String str, String str2) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }
        this.headers.put(str, str2);
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        if (httpMethod == null) {
            throw new IllegalArgumentException("The httpMethod must not be null.");
        }
        this.httpMethod = httpMethod;
    }

    public String getHost() {
        return getUseSecure() ? this.secureHost : this.nonSecureHost;
    }

    public void setHost(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.secureHost = str;
        this.nonSecureHost = str;
    }

    public void setSecureHost(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.secureHost = str;
    }

    public void setNonSecureHost(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.nonSecureHost = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        if (str.charAt(0) != '/') {
            this.path = '/' + str;
        } else {
            this.path = str;
        }
    }

    public boolean getUseSecure() {
        return DebugProperties.getInstance().getDebugPropertyAsBoolean(DebugProperties.DEBUG_USESECURE, Boolean.valueOf(this.secure)).booleanValue();
    }

    public void setUseSecure(boolean z) {
        this.secure = z;
    }

    public void setUrlString(String str) {
        if (str != null && getUseSecure() && str.startsWith("http:")) {
            str = str.replaceFirst(Constants.HTTP, Constants.HTTPS);
        }
        this.urlString = str;
    }

    public String getUrlString() {
        return this.urlString;
    }

    public void setRequestBodyString(String str) {
        this.requestBody = str;
    }

    public String getRequestBodyString() {
        return this.requestBody;
    }

    public String getRequestBody() {
        if (getRequestBodyString() != null) {
            return getRequestBodyString();
        }
        if (this.postParameters.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.postParameters.entrySet()) {
            stringBuilder.append((String) entry.getKey()).append('=').append((String) entry.getValue()).append(";\n");
        }
        return stringBuilder.toString();
    }

    public void setAcceptContentType(String str) {
        this.acceptContentType = this.contentType;
    }

    public String getAcceptContentType() {
        return this.acceptContentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public String getCharset() {
        return this.charset;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void setMetricsCollector(MetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    public void setServiceCallLatencyMetric(MetricType metricType) {
        this.serviceCallLatencyMetric = metricType;
    }

    public void setAdditionalQueryParamsString(String str) {
        this.queryStringParameters.setRawAppendage(str);
    }

    public void setExternalLogTag(String str) {
        if (str == null) {
            this.logTag = LOGTAG + " " + getSubLogTag();
        } else {
            this.logTag = str + " " + LOGTAG + " " + getSubLogTag();
        }
        this.logger.withLogTag(this.logTag);
    }

    public boolean getDisconnectEnabled() {
        return this.disconnectEnabled;
    }

    public void setDisconnectEnabled(boolean z) {
        this.disconnectEnabled = z;
    }

    protected MobileAdsLogger getLogger() {
        return this.logger;
    }

    private String getLogTag() {
        return this.logTag;
    }

    protected void setContentTypeHeaders() {
        if (this.acceptContentType != null) {
            putHeader("Accept", this.contentType);
        }
        if (this.contentType != null) {
            String str = this.contentType;
            if (this.charset != null) {
                str = str + "; charset=" + this.charset;
            }
            putHeader("Content-Type", str);
        }
    }

    protected void writeMetricStart(MetricType metricType) {
        if (metricType != null && this.metricsCollector != null) {
            this.metricsCollector.startMetric(metricType);
        }
    }

    protected void writeMetricStop(MetricType metricType) {
        if (metricType != null && this.metricsCollector != null) {
            this.metricsCollector.stopMetric(metricType);
        }
    }

    protected URI createUri() {
        return new URL(getUrlString()).toURI();
    }

    protected URI createURI(String str) {
        return createURI(createURL(str));
    }

    protected URI createURI(URL url) {
        return url.toURI();
    }

    protected URL createURL(String str) {
        return new URL(str);
    }

    protected void appendQuery(StringBuilder stringBuilder) {
        this.queryStringParameters.append(stringBuilder);
    }

    protected String getScheme() {
        if (getUseSecure()) {
            return Constants.HTTPS;
        }
        return Constants.HTTP;
    }

    public String toString() {
        return getUrl();
    }

    protected String getUrl() {
        if (this.urlString != null) {
            return this.urlString;
        }
        StringBuilder stringBuilder = new StringBuilder(getScheme());
        stringBuilder.append("://");
        stringBuilder.append(getHost());
        if (getPort() != -1) {
            stringBuilder.append(":");
            stringBuilder.append(getPort());
        }
        stringBuilder.append(getPath());
        appendQuery(stringBuilder);
        return stringBuilder.toString();
    }
}
