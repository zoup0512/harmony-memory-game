package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestStatus;
import com.amazon.device.ads.WebRequest.WebResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

class HttpURLConnectionWebRequest extends WebRequest {
    private static final String LOGTAG = HttpURLConnectionWebRequest.class.getSimpleName();
    private HttpURLConnection connection;

    HttpURLConnectionWebRequest() {
    }

    protected WebResponse doHttpNetworkCall(URL url) {
        if (this.connection != null) {
            closeConnection();
        }
        try {
            this.connection = openConnection(url);
            setupRequestProperties(this.connection);
            try {
                this.connection.connect();
                return prepareResponse(this.connection);
            } catch (Throwable e) {
                getLogger().e("Socket timed out while connecting to URL: %s", e.getMessage());
                throw new WebRequestException(WebRequestStatus.NETWORK_TIMEOUT, "Socket timed out while connecting to URL", e);
            } catch (Throwable e2) {
                getLogger().e("Problem while connecting to URL: %s", e2.getMessage());
                throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "Probem while connecting to URL", e2);
            }
        } catch (Throwable e22) {
            getLogger().e("Problem while opening the URL connection: %s", e22.getMessage());
            throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "Problem while opening the URL connection", e22);
        }
    }

    protected HttpURLConnection openConnection(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    protected void closeConnection() {
        if (this.connection != null) {
            this.connection.disconnect();
            this.connection = null;
        }
    }

    protected void setupRequestProperties(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.setRequestMethod(getHttpMethod().name());
            for (Entry entry : this.headers.entrySet()) {
                if (!(entry.getValue() == null || ((String) entry.getValue()).equals(""))) {
                    httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
            httpURLConnection.setConnectTimeout(getTimeout());
            httpURLConnection.setReadTimeout(getTimeout());
            logUrl(httpURLConnection.getURL().toString());
            switch (getHttpMethod()) {
                case GET:
                    httpURLConnection.setDoOutput(false);
                    return;
                case POST:
                    httpURLConnection.setDoOutput(true);
                    writePostBody(httpURLConnection);
                    return;
                default:
                    return;
            }
        } catch (Throwable e) {
            getLogger().e("Invalid client protocol: %s", e.getMessage());
            throw new WebRequestException(WebRequestStatus.INVALID_CLIENT_PROTOCOL, "Invalid client protocol", e);
        }
    }

    private void writePostBody(HttpURLConnection httpURLConnection) {
        Throwable e;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.requestBody != null) {
            stringBuilder.append(this.requestBody);
        } else if (!(this.postParameters == null || this.postParameters.isEmpty())) {
            for (Entry entry : this.postParameters.entrySet()) {
                stringBuilder.append((String) entry.getKey()).append("=").append(WebUtils.getURLEncodedString((String) entry.getValue())).append("&");
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
        }
        if (this.logRequestBodyEnabled && getRequestBody() != null) {
            String replaceAll = !this.logSessionIdEnabled ? getRequestBody().replaceAll(",\\s*\"\\s*sessionId\\s*\"\\s*:\\s*\".*?\"|\\s*\"\\s*sessionId\\s*\"\\s*:\\s*\".*?\"\\s*,*", "") : getRequestBody();
            getLogger().d("Request Body: %s", replaceAll);
        }
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            try {
                outputStreamWriter.write(stringBuilder.toString());
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (Throwable e2) {
                        getLogger().e("Problem while closing output stream writer for request body: %s", e2.getMessage());
                        throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "Problem while closing output stream writer for request body", e2);
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    getLogger().e("Problem while creating output steam for request body: %s", e2.getMessage());
                    throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "Problem while creating output steam for request body", e2);
                } catch (Throwable th) {
                    e2 = th;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (Throwable e22) {
                            getLogger().e("Problem while closing output stream writer for request body: %s", e22.getMessage());
                            throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "Problem while closing output stream writer for request body", e22);
                        }
                    }
                    throw e22;
                }
            }
        } catch (IOException e4) {
            e22 = e4;
            outputStreamWriter = null;
            getLogger().e("Problem while creating output steam for request body: %s", e22.getMessage());
            throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "Problem while creating output steam for request body", e22);
        } catch (Throwable th2) {
            e22 = th2;
            outputStreamWriter = null;
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            throw e22;
        }
    }

    protected WebResponse prepareResponse(HttpURLConnection httpURLConnection) {
        WebResponse webResponse = new WebResponse();
        try {
            webResponse.setHttpStatusCode(httpURLConnection.getResponseCode());
            webResponse.setHttpStatus(httpURLConnection.getResponseMessage());
            if (webResponse.getHttpStatusCode() == 200) {
                try {
                    webResponse.setInputStream(httpURLConnection.getInputStream());
                } catch (Throwable e) {
                    getLogger().e("IOException while reading the input stream from response: %s", e.getMessage());
                    throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "IOException while reading the input stream from response", e);
                }
            }
            return webResponse;
        } catch (Throwable e2) {
            getLogger().e("Socket Timeout while getting the response status code: %s", e2.getMessage());
            throw new WebRequestException(WebRequestStatus.NETWORK_TIMEOUT, "Socket Timeout while getting the response status code", e2);
        } catch (Throwable e22) {
            getLogger().e("IOException while getting the response status code: %s", e22.getMessage());
            throw new WebRequestException(WebRequestStatus.NETWORK_FAILURE, "IOException while getting the response status code", e22);
        } catch (Throwable e222) {
            getLogger().e("IndexOutOfBoundsException while getting the response status code: %s", e222.getMessage());
            throw new WebRequestException(WebRequestStatus.MALFORMED_URL, "IndexOutOfBoundsException while getting the response status code", e222);
        }
    }

    protected String getSubLogTag() {
        return LOGTAG;
    }
}
