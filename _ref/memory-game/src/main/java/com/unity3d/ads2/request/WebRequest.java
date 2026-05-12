package com.unity3d.ads2.request;

import com.unity3d.ads2.log.DeviceLog;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class WebRequest {
    private String _body;
    private boolean _canceled;
    private int _connectTimeout;
    private long _contentLength;
    private Map<String, List<String>> _headers;
    private IWebRequestProgressListener _progressListener;
    private int _readTimeout;
    private String _requestType;
    private int _responseCode;
    private Map<String, List<String>> _responseHeaders;
    private URL _url;

    public enum RequestType {
        POST,
        GET,
        HEAD
    }

    public WebRequest(String str, String str2, Map<String, List<String>> map) {
        this(str, str2, map, 30000, 30000);
    }

    public WebRequest(String str, String str2, Map<String, List<String>> map, int i, int i2) {
        this._requestType = RequestType.GET.name();
        this._responseCode = -1;
        this._contentLength = -1;
        this._canceled = false;
        this._url = new URL(str);
        this._requestType = str2;
        this._headers = map;
        this._connectTimeout = i;
        this._readTimeout = i2;
    }

    public void cancel() {
        this._canceled = true;
    }

    public boolean isCanceled() {
        return this._canceled;
    }

    public URL getUrl() {
        return this._url;
    }

    public String getRequestType() {
        return this._requestType;
    }

    public String getBody() {
        return this._body;
    }

    public void setBody(String str) {
        this._body = str;
    }

    public String getQuery() {
        if (this._url != null) {
            return this._url.getQuery();
        }
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this._responseHeaders;
    }

    public Map<String, List<String>> getHeaders() {
        return this._headers;
    }

    public int getResponseCode() {
        return this._responseCode;
    }

    public long getContentLength() {
        return this._contentLength;
    }

    public int getConnectTimeout() {
        return this._connectTimeout;
    }

    public void setConnectTimeout(int i) {
        this._connectTimeout = i;
    }

    public int getReadTimeout() {
        return this._readTimeout;
    }

    public void setReadTimeout(int i) {
        this._readTimeout = i;
    }

    public void setProgressListener(IWebRequestProgressListener iWebRequestProgressListener) {
        this._progressListener = iWebRequestProgressListener;
    }

    public long makeStreamRequest(OutputStream outputStream) {
        Exception e;
        Throwable th;
        InputStream inputStream;
        HttpURLConnection httpUrlConnectionWithHeaders = getHttpUrlConnectionWithHeaders();
        httpUrlConnectionWithHeaders.setDoInput(true);
        if (getRequestType().equals(RequestType.POST.name())) {
            httpUrlConnectionWithHeaders.setDoOutput(true);
            PrintWriter printWriter;
            try {
                printWriter = new PrintWriter(new OutputStreamWriter(httpUrlConnectionWithHeaders.getOutputStream(), "UTF-8"), true);
                try {
                    if (getBody() == null) {
                        printWriter.print(getQuery());
                    } else {
                        printWriter.print(getBody());
                    }
                    printWriter.flush();
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e2) {
                            DeviceLog.exception("Error closing writer", e2);
                            throw e2;
                        }
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        DeviceLog.exception("Error while writing POST params", e2);
                        throw e2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Exception e22) {
                                DeviceLog.exception("Error closing writer", e22);
                                throw e22;
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e22 = e4;
                printWriter = null;
                DeviceLog.exception("Error while writing POST params", e22);
                throw e22;
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th;
            }
        }
        this._responseCode = httpUrlConnectionWithHeaders.getResponseCode();
        this._contentLength = (long) httpUrlConnectionWithHeaders.getContentLength();
        if (httpUrlConnectionWithHeaders.getHeaderFields() != null) {
            this._responseHeaders = httpUrlConnectionWithHeaders.getHeaderFields();
        }
        try {
            inputStream = httpUrlConnectionWithHeaders.getInputStream();
        } catch (IOException e5) {
            InputStream errorStream = httpUrlConnectionWithHeaders.getErrorStream();
            if (errorStream == null) {
                throw new IOException("Can't open error stream");
            }
            inputStream = errorStream;
        }
        if (this._progressListener != null) {
            this._progressListener.onRequestStart(getUrl().toString(), this._contentLength, this._responseCode, this._responseHeaders);
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        long j = 0;
        byte[] bArr = new byte[4096];
        while (!isCanceled()) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
            if (this._progressListener != null) {
                this._progressListener.onRequestProgress(getUrl().toString(), j, this._contentLength);
            }
        }
        httpUrlConnectionWithHeaders.disconnect();
        outputStream.flush();
        return j;
    }

    public String makeRequest() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        makeStreamRequest(byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }

    private HttpURLConnection getHttpUrlConnectionWithHeaders() {
        HttpURLConnection httpURLConnection;
        if (getUrl().toString().startsWith("https://")) {
            httpURLConnection = (HttpsURLConnection) getUrl().openConnection();
        } else {
            httpURLConnection = (HttpURLConnection) getUrl().openConnection();
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(getConnectTimeout());
        httpURLConnection.setReadTimeout(getReadTimeout());
        httpURLConnection.setRequestMethod(getRequestType());
        if (getHeaders() != null && getHeaders().size() > 0) {
            for (String str : getHeaders().keySet()) {
                for (String str2 : (List) getHeaders().get(str)) {
                    DeviceLog.debug("Setting header: " + str + "=" + str2);
                    httpURLConnection.setRequestProperty(str, str2);
                }
            }
        }
        return httpURLConnection;
    }
}
