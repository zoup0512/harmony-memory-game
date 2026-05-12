package com.cmcm.utils;

import android.text.TextUtils;
import com.cmcm.picks.loader.g;
import com.mopub.common.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* compiled from: HttpUtil */
public class f {

    /* compiled from: HttpUtil */
    public interface a {
        void a(InputStream inputStream);
    }

    public static HttpResponse a(HttpClient httpClient, String str, boolean z) {
        HttpResponse httpResponse = null;
        if (httpClient == null) {
            try {
                httpClient = a();
            } catch (Exception e) {
                if (httpClient != null) {
                    httpClient.getConnectionManager().shutdown();
                }
            }
        }
        String str2 = "";
        HttpUriRequest httpGet = new HttpGet(str);
        if (z) {
            httpGet.setHeader(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
        }
        httpResponse = httpClient.execute(httpGet);
        return httpResponse;
    }

    public static void a(String str, String str2, a aVar) {
        try {
            if (!TextUtils.isEmpty(str2)) {
                HttpClient a = a();
                HttpUriRequest httpPost = new HttpPost(str);
                if (httpPost != null) {
                    httpPost.setEntity(new StringEntity(str2));
                    HttpResponse execute = a.execute(httpPost);
                    if (execute != null && aVar != null) {
                        InputStream content = execute.getEntity().getContent();
                        if (aVar != null) {
                            aVar.a(content);
                        }
                        if (content != null) {
                            content.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
    }

    public static String b(HttpClient httpClient, String str, boolean z) {
        String str2 = null;
        try {
            HttpResponse a = a(httpClient, str, z);
            if (a.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream;
                InputStream content = a.getEntity().getContent();
                Header firstHeader = a.getFirstHeader("Content-Encoding");
                if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase(HttpRequest.ENCODING_GZIP)) {
                    inputStream = content;
                } else {
                    inputStream = new GZIPInputStream(new BufferedInputStream(content));
                }
                str2 = a(inputStream);
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    protected static HttpClient a() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 1);
        ConnManagerParams.setTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        HttpClient defaultHttpClient = new DefaultHttpClient(a(basicHttpParams), basicHttpParams);
        defaultHttpClient.getParams().setParameter("http.useragent", g.b("uer_agent", ""));
        return defaultHttpClient;
    }

    public static String a(InputStream inputStream) {
        Exception e;
        Throwable th;
        char[] cArr = new char[2048];
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            try {
                for (int read = inputStreamReader.read(cArr, 0, cArr.length); read > 0; read = inputStreamReader.read(cArr)) {
                    stringBuilder.append(cArr, 0, read);
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    if (g.a) {
                        e.printStackTrace();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    return stringBuilder.toString();
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e4) {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                return stringBuilder.toString();
            }
        } catch (Exception e5) {
            e = e5;
            inputStreamReader = null;
            if (g.a) {
                e.printStackTrace();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            return stringBuilder.toString();
        } catch (OutOfMemoryError e6) {
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            return stringBuilder.toString();
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        return stringBuilder.toString();
    }

    private static void a(SchemeRegistry schemeRegistry) {
        schemeRegistry.register(new Scheme(Constants.HTTPS, new LayeredSocketFactory() {
            SSLSocketFactory a = SSLSocketFactory.getSocketFactory();

            public Socket createSocket() throws IOException {
                return this.a.createSocket();
            }

            public Socket connectSocket(Socket sock, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws IOException {
                return this.a.connectSocket(sock, host, port, localAddress, localPort, params);
            }

            public boolean isSecure(Socket sock) throws IllegalArgumentException {
                return this.a.isSecure(sock);
            }

            public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
                a(socket, host);
                return this.a.createSocket(socket, host, port, autoClose);
            }

            private void a(Socket socket, String str) {
                try {
                    Field declaredField = InetAddress.class.getDeclaredField("hostName");
                    declaredField.setAccessible(true);
                    declaredField.set(socket.getInetAddress(), str);
                } catch (Exception e) {
                }
            }
        }, 443));
    }

    public static ClientConnectionManager a(HttpParams httpParams) {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(Constants.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        a(schemeRegistry);
        return new SingleClientConnManager(httpParams, schemeRegistry);
    }
}
