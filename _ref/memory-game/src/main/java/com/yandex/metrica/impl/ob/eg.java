package com.yandex.metrica.impl.ob;

import com.mopub.common.Constants;
import com.mopub.volley.toolbox.HttpClientStack.HttpPatch;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
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

public class eg implements ef {
    private final SSLSocketFactory a;

    public eg() {
        this(null);
    }

    public eg(SSLSocketFactory sSLSocketFactory) {
        this.a = sSLSocketFactory;
    }

    public HttpResponse a(en<?> enVar) throws IOException, ek {
        String a = enVar.a();
        HashMap hashMap = new HashMap();
        hashMap.putAll(enVar.b());
        URL url = new URL(a);
        HttpURLConnection a2 = a(url);
        int n = enVar.n();
        a2.setConnectTimeout(n);
        a2.setReadTimeout(n);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && this.a != null) {
            ((HttpsURLConnection) a2).setSSLSocketFactory(this.a);
        }
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        switch (enVar.d()) {
            case -1:
                byte[] j = enVar.j();
                if (j != null) {
                    a2.setDoOutput(true);
                    a2.setRequestMethod(HttpRequest.METHOD_POST);
                    a2.addRequestProperty("Content-Type", enVar.i());
                    DataOutputStream dataOutputStream = new DataOutputStream(a2.getOutputStream());
                    dataOutputStream.write(j);
                    dataOutputStream.close();
                    break;
                }
                break;
            case 0:
                a2.setRequestMethod(HttpRequest.METHOD_GET);
                break;
            case 1:
                a2.setRequestMethod(HttpRequest.METHOD_POST);
                a(a2, enVar);
                break;
            case 2:
                a2.setRequestMethod(HttpRequest.METHOD_PUT);
                a(a2, enVar);
                break;
            case 3:
                a2.setRequestMethod(HttpRequest.METHOD_DELETE);
                break;
            case 4:
                a2.setRequestMethod(HttpRequest.METHOD_HEAD);
                break;
            case 5:
                a2.setRequestMethod(HttpRequest.METHOD_OPTIONS);
                break;
            case 6:
                a2.setRequestMethod(HttpRequest.METHOD_TRACE);
                break;
            case 7:
                a2.setRequestMethod(HttpPatch.METHOD_NAME);
                a(a2, enVar);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage()));
        basicHttpResponse.setEntity(a(a2));
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity a(HttpURLConnection httpURLConnection) {
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

    protected HttpURLConnection a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private static void a(HttpURLConnection httpURLConnection, en<?> enVar) throws IOException, ek {
        byte[] c = enVar.c();
        if (c != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", enVar.m());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(c);
            dataOutputStream.close();
        }
    }
}
