package com.my.target.core.async.commands;

import android.content.Context;
import com.my.target.Tracer;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: SendLogCommand */
public final class g extends a<Void> {
    private String e;
    private String f;
    private String g;

    public final /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public g(String str, String str2, String str3, Context context) {
        super(context, (byte) 0);
        this.e = str;
        this.f = str2;
        this.g = str3;
    }

    protected final void c() {
        Throwable th;
        Tracer.d("send log");
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.e).openConnection();
            try {
                httpURLConnection2.setRequestMethod(HttpRequest.METHOD_POST);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setConnectTimeout(5000);
                httpURLConnection2.setReadTimeout(5000);
                httpURLConnection2.setRequestProperty("X-Sentry-Auth", this.f);
                httpURLConnection2.setRequestProperty("connection", "close");
                httpURLConnection2.connect();
                OutputStream outputStream = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(this.g);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                httpURLConnection2.getInputStream().close();
                httpURLConnection2.disconnect();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            Tracer.d(th.toString());
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
