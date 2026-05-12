package com.yandex.metrica.impl.ob;

import com.mopub.volley.toolbox.HttpClientStack.HttpPatch;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class ee implements ef {
    protected final HttpClient a;

    public static final class a extends HttpEntityEnclosingRequestBase {
        public a(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return HttpPatch.METHOD_NAME;
        }
    }

    public ee(HttpClient httpClient) {
        this.a = httpClient;
    }

    private static void a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    private static void a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, en<?> enVar) throws ek {
        byte[] c = enVar.c();
        if (c != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(c));
        }
    }

    public HttpResponse a(en<?> enVar) throws IOException, ek {
        HttpUriRequest httpGet;
        HttpEntityEnclosingRequestBase httpPost;
        switch (enVar.d()) {
            case -1:
                byte[] j = enVar.j();
                if (j == null) {
                    httpGet = new HttpGet(enVar.a());
                    break;
                }
                httpGet = new HttpPost(enVar.a());
                httpGet.addHeader("Content-Type", enVar.i());
                httpGet.setEntity(new ByteArrayEntity(j));
                break;
            case 0:
                httpGet = new HttpGet(enVar.a());
                break;
            case 1:
                httpPost = new HttpPost(enVar.a());
                httpPost.addHeader("Content-Type", enVar.m());
                a(httpPost, (en) enVar);
                break;
            case 2:
                httpPost = new HttpPut(enVar.a());
                httpPost.addHeader("Content-Type", enVar.m());
                a(httpPost, (en) enVar);
                break;
            case 3:
                httpGet = new HttpDelete(enVar.a());
                break;
            case 4:
                httpGet = new HttpHead(enVar.a());
                break;
            case 5:
                httpGet = new HttpOptions(enVar.a());
                break;
            case 6:
                httpGet = new HttpTrace(enVar.a());
                break;
            case 7:
                httpPost = new a(enVar.a());
                httpPost.addHeader("Content-Type", enVar.m());
                a(httpPost, (en) enVar);
                break;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
        a(httpGet, enVar.b());
        HttpParams params = httpGet.getParams();
        int n = enVar.n();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, n);
        return this.a.execute(httpGet);
    }
}
