package com.appodeal.ads.utils.b;

import android.util.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class b implements Runnable {
    private final c a;

    public b(c cVar) {
        this.a = cVar;
    }

    public void run() {
        Exception exception;
        Throwable th;
        String str = "ExceptionTask";
        try {
            List<String> a = this.a.a(false);
            JSONArray jSONArray = new JSONArray();
            for (String jSONObject : a) {
                jSONArray.put(new JSONObject(jSONObject));
            }
            HttpURLConnection httpURLConnection = null;
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) a().openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(10000);
                    httpURLConnection2.setReadTimeout(10000);
                    httpURLConnection2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestMethod(HttpRequest.METHOD_POST);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                    dataOutputStream.write(jSONArray.toString().getBytes(Charset.forName("UTF-8")));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    switch (httpURLConnection2.getResponseCode()) {
                        case 200:
                            this.a.a((List) a);
                            break;
                        case 503:
                            String headerField = httpURLConnection2.getHeaderField("Retry-After");
                            if (headerField != null) {
                                this.a.b(headerField);
                                break;
                            }
                            break;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Exception e) {
                    Exception exception2 = e;
                    httpURLConnection = httpURLConnection2;
                    exception = exception2;
                    try {
                        Log.e(str, exception.toString());
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    httpURLConnection = httpURLConnection2;
                    th = th4;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                exception = e2;
                Log.e(str, exception.toString());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
        } catch (Exception exception3) {
            Log.e(str, exception3.toString());
        }
    }

    URL a() {
        return new URL("https://ach.appodeal.com/api/v0/android/crashes");
    }
}
