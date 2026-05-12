package com.my.target.core.async.commands;

import android.content.Context;
import com.amazonaws.services.s3.internal.Constants;
import com.applovin.sdk.AppLovinErrorCodes;
import com.my.target.Tracer;
import com.my.target.core.net.cookie.a;
import com.my.target.core.utils.m;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.nexage.sourcekit.vast.model.VASTModel;

/* compiled from: SendStatCommand */
public final class h extends a<String> {
    private String e;
    private List<String> f;
    private int g;

    public final /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public h(String str, Context context) {
        super(context, (byte) 0);
        this.e = str;
        this.c = str;
    }

    protected final void c() {
        if (this.e != null) {
            Tracer.d("send stat: " + this.e);
            this.g = 0;
            a(this.e, this.b);
        } else if (this.f != null) {
            for (String str : this.f) {
                Tracer.d("send stat: " + str);
                this.g = 0;
                a(str, this.b);
            }
        }
    }

    private void a(String str, Context context) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        while (true) {
            try {
                a a = a.a(context);
                URLConnection uRLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    uRLConnection.setReadTimeout(10000);
                    uRLConnection.setConnectTimeout(10000);
                    uRLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                    uRLConnection.addRequestProperty("User-Agent", System.getProperty("http.agent"));
                    uRLConnection.setInstanceFollowRedirects(false);
                    uRLConnection.setRequestProperty("connection", "close");
                    if (a != null) {
                        a.b(uRLConnection);
                    }
                    int responseCode = uRLConnection.getResponseCode();
                    if (responseCode == 200 || responseCode == AppLovinErrorCodes.NO_FILL || responseCode == Constants.NO_SUCH_BUCKET_STATUS_CODE || responseCode == 403) {
                        if (a != null) {
                            a.a(uRLConnection);
                        }
                        this.c = str;
                        str = null;
                    } else if (responseCode == VASTModel.ERROR_CODE_EXCEEDED_WRAPPER_LIMIT || responseCode == 301 || responseCode == 303) {
                        this.g++;
                        str = a(uRLConnection);
                    } else {
                        str = null;
                    }
                    if (uRLConnection != null) {
                        try {
                            uRLConnection.disconnect();
                        } catch (Throwable th2) {
                            Tracer.d(th2.getMessage());
                        }
                    }
                } catch (Throwable th3) {
                    URLConnection uRLConnection2 = uRLConnection;
                    th2 = th3;
                }
            } catch (Throwable th4) {
                th2 = th4;
            }
            if (str == null) {
                Tracer.d("redirected to: " + str);
            } else {
                return;
            }
        }
        throw th2;
        if (httpURLConnection2 != null) {
            try {
                httpURLConnection2.disconnect();
            } catch (Throwable th32) {
                Tracer.d(th32.getMessage());
            }
        }
        throw th2;
    }

    private String a(HttpURLConnection httpURLConnection) {
        if (this.g <= 10) {
            try {
                String uri = httpURLConnection.getURL().toURI().resolve(new URI(httpURLConnection.getHeaderField("Location"))).toString();
                if (!m.a(uri)) {
                    return uri;
                }
                this.c = uri;
            } catch (URISyntaxException e) {
                return null;
            }
        }
        return null;
    }
}
