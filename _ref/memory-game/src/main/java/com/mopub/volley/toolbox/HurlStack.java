package com.mopub.volley.toolbox;

import com.mopub.common.Constants;
import com.mopub.volley.Request;
import com.mopub.volley.toolbox.HttpClientStack.HttpPatch;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class HurlStack implements HttpStack {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private final SSLSocketFactory mSslSocketFactory;
    private final UrlRewriter mUrlRewriter;

    public interface UrlRewriter {
        String rewriteUrl(String str);
    }

    public HurlStack() {
        this(null);
    }

    public HurlStack(UrlRewriter urlRewriter) {
        this(urlRewriter, null);
    }

    public HurlStack(UrlRewriter urlRewriter, SSLSocketFactory sSLSocketFactory) {
        this.mUrlRewriter = urlRewriter;
        this.mSslSocketFactory = sSLSocketFactory;
    }

    public HttpResponse performRequest(Request<?> request, Map<String, String> map) {
        String rewriteUrl;
        String url = request.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(request.getHeaders());
        hashMap.putAll(map);
        if (this.mUrlRewriter != null) {
            rewriteUrl = this.mUrlRewriter.rewriteUrl(url);
            if (rewriteUrl == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
        }
        rewriteUrl = url;
        HttpURLConnection openConnection = openConnection(new URL(rewriteUrl), request);
        for (String rewriteUrl2 : hashMap.keySet()) {
            openConnection.addRequestProperty(rewriteUrl2, (String) hashMap.get(rewriteUrl2));
        }
        setConnectionParametersForRequest(openConnection, request);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (openConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, openConnection.getResponseCode(), openConnection.getResponseMessage()));
        basicHttpResponse.setEntity(entityFromConnection(openConnection));
        for (Entry entry : openConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity entityFromConnection(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection createConnection(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    private HttpURLConnection openConnection(URL url, Request<?> request) {
        HttpURLConnection createConnection = createConnection(url);
        int timeoutMs = request.getTimeoutMs();
        createConnection.setConnectTimeout(timeoutMs);
        createConnection.setReadTimeout(timeoutMs);
        createConnection.setUseCaches(false);
        createConnection.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && this.mSslSocketFactory != null) {
            ((HttpsURLConnection) createConnection).setSSLSocketFactory(this.mSslSocketFactory);
        }
        return createConnection;
    }

    static void setConnectionParametersForRequest(HttpURLConnection httpURLConnection, Request<?> request) {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                    httpURLConnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                return;
            case 1:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                addBodyIfExists(httpURLConnection, request);
                return;
            case 2:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_PUT);
                addBodyIfExists(httpURLConnection, request);
                return;
            case 3:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_DELETE);
                return;
            case 4:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_HEAD);
                return;
            case 5:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_OPTIONS);
                return;
            case 6:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_TRACE);
                return;
            case 7:
                httpURLConnection.setRequestMethod(HttpPatch.METHOD_NAME);
                addBodyIfExists(httpURLConnection, request);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void addBodyIfExists(HttpURLConnection httpURLConnection, Request<?> request) {
        byte[] body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", request.getBodyContentType());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }
}
