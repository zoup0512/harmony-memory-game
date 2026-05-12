package com.appodeal.ads.d;

import android.support.annotation.NonNull;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class d implements Runnable {
    private final String a;
    private BlockingQueue<Serializable> b;
    private JSONObject c;
    private e d;

    public d(@NonNull BlockingQueue<Serializable> blockingQueue, @NonNull JSONObject jSONObject, @NonNull e eVar, String str) {
        this.b = blockingQueue;
        this.c = jSONObject;
        this.d = eVar;
        this.a = str;
    }

    public void run() {
        try {
            c a = a(this.d, this.c);
            if (a != null && !Thread.currentThread().isInterrupted()) {
                this.b.offer(a);
            }
        } catch (f e) {
            this.b.offer(e);
        }
    }

    private c a(e eVar, JSONObject jSONObject) {
        try {
            String str = "";
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(eVar.e()).openConnection();
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestProperty("Content-type", "application/json");
                httpURLConnection.setConnectTimeout(2000);
                httpURLConnection.setReadTimeout(2000);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                httpURLConnection.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(jSONObject.toString().getBytes(Charset.forName("UTF-8")));
                dataOutputStream.flush();
                dataOutputStream.close();
                switch (httpURLConnection.getResponseCode()) {
                    case 200:
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        String str2 = str;
                        while (true) {
                            str = bufferedReader.readLine();
                            if (str == null) {
                                return new c(str2, this.a, eVar);
                            }
                            str2 = str2 + str;
                        }
                    default:
                        throw new f("response_code " + httpURLConnection.getResponseCode());
                }
            } catch (JSONException e) {
                throw new f("json_parse " + e.getMessage());
            } catch (Exception e2) {
                throw new f("connection_exception");
            }
            throw new f("connection_exception");
        } catch (MalformedURLException e3) {
            throw new f("wrong_url");
        }
    }
}
