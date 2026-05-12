package com.my.target.core.async.commands;

import android.content.Context;
import com.applovin.sdk.AppLovinErrorCodes;
import com.my.target.SDKVersion;
import com.my.target.Tracer;
import com.my.target.core.models.d;
import com.my.target.core.parsers.b;
import com.my.target.core.utils.h;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: LoadAdCommand */
public class c extends a<com.my.target.core.models.c> {
    protected d e;
    protected com.my.target.core.a f;

    /* compiled from: LoadAdCommand */
    static final class a {
        boolean a;
        int b;
        String c;
        String d;

        a() {
        }

        final void a(String str) {
            Throwable th;
            Throwable th2;
            HttpURLConnection httpURLConnection = null;
            this.a = true;
            this.b = -1;
            this.c = null;
            this.d = null;
            try {
                Tracer.d("send ad request: " + str);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setReadTimeout(10000);
                    httpURLConnection2.setConnectTimeout(10000);
                    httpURLConnection2.setInstanceFollowRedirects(true);
                    httpURLConnection2.setRequestProperty("connection", "close");
                    httpURLConnection2.connect();
                    this.b = httpURLConnection2.getResponseCode();
                    if (this.b == 200) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                        }
                        bufferedReader.close();
                        this.c = stringBuilder.toString();
                    } else if (this.b != AppLovinErrorCodes.NO_FILL) {
                        this.a = false;
                        Tracer.d("ad request error: response code " + this.b);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection = httpURLConnection2;
                    th2 = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                th2 = th4;
                this.a = false;
                Tracer.d("ad request error: " + th2.getMessage());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
        }
    }

    public final /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public c(d dVar, com.my.target.core.a aVar, Context context) {
        super(context);
        this.e = dVar;
        this.f = aVar;
    }

    protected void c() {
        String d = d();
        a aVar = new a();
        for (int i = 0; i < 3; i++) {
            aVar.a(d);
            if (aVar.a) {
                break;
            }
        }
        if (aVar.a) {
            this.c = new com.my.target.core.models.c(this.f.a());
            ((com.my.target.core.models.c) this.c).a(d);
            b.a(aVar.c, (com.my.target.core.models.c) this.c, this.f, this.e, this.b);
            return;
        }
        this.d = aVar.d;
    }

    private String d() {
        try {
            com.my.target.core.providers.d.a().c().a(this.f.f());
            com.my.target.core.providers.d.a().c().b(this.f.g());
            com.my.target.core.providers.d.a().a(this.b);
            if (!h.a()) {
                h.a(com.my.target.core.providers.d.a().b().getData());
            }
        } catch (Throwable th) {
            Tracer.d("Error collecting data: " + th);
        }
        Map hashMap = new HashMap();
        com.my.target.core.providers.d.a().putDataTo(hashMap);
        if (this.f.b() != null) {
            this.f.b().putDataTo(hashMap);
        }
        hashMap.put("formats", this.f.c());
        hashMap.put("adman_ver", SDKVersion.VERSION);
        if (this.f.e()) {
            a(hashMap);
        }
        String a = this.e.a();
        Object obj = 1;
        String str = a;
        for (Entry entry : hashMap.entrySet()) {
            String str2;
            a = (String) entry.getValue();
            if (a != null) {
                str2 = (String) entry.getKey();
                try {
                    a = URLEncoder.encode(a, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Tracer.d(e.getMessage());
                }
                if (obj != null) {
                    obj = null;
                    str = str + "?" + str2 + "=" + a;
                } else {
                    str2 = str + "&" + str2 + "=" + a;
                }
            } else {
                str2 = str;
            }
            str = str2;
        }
        return str;
    }

    private static void a(Map<String, String> map) {
        Set<String> a = com.my.target.core.models.c.a().a();
        if (a != null && a.size() > 0) {
            String str = "";
            for (String str2 : a) {
                str = str + str2 + ",";
            }
            String str22 = str.substring(0, str.length() - 1);
            map.put("exb", str22);
            Tracer.d("Exclude list: " + str22);
        }
    }
}
