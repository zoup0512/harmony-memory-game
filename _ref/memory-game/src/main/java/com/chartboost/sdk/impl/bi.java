package com.chartboost.sdk.impl;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.f;
import com.chartboost.sdk.g;
import com.chartboost.sdk.h;
import com.chartboost.sdk.i;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class bi extends h {
    public String l = "UNKNOWN";
    protected int m = 1;
    private String n = null;
    private String o = null;
    private com.chartboost.sdk.Libraries.e.a p;
    private float q = 0.0f;
    private float r = 0.0f;
    private boolean s = false;
    private long t = 0;
    private long u = 0;
    private boolean v = false;
    private b w = b.NONE;

    private class a extends WebViewClient {
        final /* synthetic */ bi a;

        private a(bi biVar) {
            this.a = biVar;
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            this.a.v = true;
            this.a.u = System.currentTimeMillis();
            CBLogging.a("CBWebViewProtocol", "Total web view load response time " + ((this.a.u - this.a.t) / 1000));
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            this.a.a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            this.a.v = true;
            g l = f.l();
            CBLogging.a("CBWebViewProtocol", "#### Error happened loading webview");
            if (l != null) {
                l.d(this.a.g);
            }
            String str = "Webview seems to have some issues loading html, onRecievedError callback triggered";
            CBLogging.a("CBWebViewProtocol", str);
            f.k().a(this.a.g.q().e(), this.a.g.e, this.a.g.p(), str, true);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }

    public enum b {
        NONE,
        IDLE,
        PLAYING,
        PAUSED
    }

    public class c extends com.chartboost.sdk.h.a {
        public bh b;
        public bg c;
        public RelativeLayout d;
        public RelativeLayout e;
        final /* synthetic */ bi f;

        public c(final bi biVar, Context context, String str) {
            this.f = biVar;
            super(biVar, context);
            setFocusable(false);
            i a = i.a();
            this.d = a.c(context);
            this.e = a.c(context);
            this.b = a.b(context);
            this.b.setWebViewClient(new a());
            this.c = a.a(this.d, this.e, null, this.b, biVar);
            this.b.setWebChromeClient(this.c);
            if (a.a().a(19)) {
                bh bhVar = this.b;
                bh.setWebContentsDebuggingEnabled(true);
            }
            this.b.loadDataWithBaseURL(biVar.o, str, "text/html", "utf-8", null);
            this.d.addView(this.b);
            this.b.getSettings().setSupportZoom(false);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            this.d.setLayoutParams(layoutParams);
            this.b.setLayoutParams(layoutParams);
            this.b.setBackgroundColor(0);
            this.e.setVisibility(8);
            this.e.setLayoutParams(layoutParams);
            addView(this.d);
            addView(this.e);
            biVar.t = System.currentTimeMillis();
            CBUtility.c().postDelayed(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    if (!this.b.f.v) {
                        String str = "Webview seems to be taking more time loading the html content, so closing the view.";
                        CBLogging.a("CBWebViewProtocol", str);
                        f.k().a(this.b.f.g.q().e(), this.b.f.g.e, this.b.f.g.p(), str, true);
                        this.b.f.a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
                        g l = f.l();
                        if (l != null) {
                            l.d(this.b.f.g);
                        }
                    }
                }
            }, 3000);
        }

        protected void a(int i, int i2) {
        }

        public void b() {
            if (this.b != null) {
                CBLogging.a("CBWebViewProtocol", "Destroying the webview object");
                this.b.destroy();
            }
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            super.b();
        }
    }

    public /* synthetic */ com.chartboost.sdk.h.a e() {
        return q();
    }

    public bi(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
    }

    protected com.chartboost.sdk.h.a b(Context context) {
        return new c(this, context, this.n);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        File a = com.chartboost.sdk.Libraries.h.a();
        this.p = aVar.a("events");
        if (a == null) {
            CBLogging.b("CBWebViewProtocol", "External Storage path is unavailable or media not mounted");
            a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.o = "file://" + a.getAbsolutePath() + "/";
        if (a.a().a(this.g.i)) {
            CBLogging.b("CBWebViewProtocol", "Invalid adId being passed in th response");
            a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            return false;
        }
        ConcurrentHashMap c = f.n().c();
        if (c == null || c.isEmpty() || !c.containsKey(this.g.i)) {
            CBLogging.b("CBWebViewProtocol", "No html data found in memory");
            a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.n = (String) c.get(this.g.i);
        b();
        return true;
    }

    public void h() {
        super.h();
    }

    public void b(String str) {
        if (this.p != null && this.p.c() && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = (ArrayList) this.p.a(str).h();
            z g = f.g();
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    g.a(str2);
                    CBLogging.a("CBWebViewProtocol", "###### Sending VAST Tracking Event: " + str2);
                }
            }
        }
    }

    public void c(String str) {
        f.k().a(this.g.q().e(), this.g.e, this.g.p(), str);
    }

    public void d(String str) {
        String str2 = a.a().a((CharSequence) str) ? "Unknown Webview error" : str;
        f.k().a(this.g.q().e(), this.g.e, this.g.p(), str2, true);
        CBLogging.b("CBWebViewProtocol", "Webview error occurred closing the webview" + str2);
        a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
        h();
    }

    public void e(String str) {
        if (a.a().a((CharSequence) str)) {
            str = "Unknown Webview warning message";
        }
        f.k().b(this.g.q().e(), this.g.e, this.g.p(), str);
        CBLogging.d("CBWebViewProtocol", "Webview warning occurred closing the webview" + str);
    }

    public boolean l() {
        if (this.w != b.PLAYING) {
            c q = q();
            if (q != null) {
                q.c.onHideCustomView();
            }
            h();
        }
        return true;
    }

    public void m() {
        super.m();
        final c q = q();
        if (q != null) {
            CBUtility.c().post(new Runnable(this) {
                final /* synthetic */ bi b;

                public void run() {
                    String str = "javascript:Chartboost.EventHandler.handleNativeEvent(\"onForeground\", \"\")";
                    CBLogging.a("CBWebViewProtocol", "Calling native to javascript: " + str);
                    q.b.loadUrl(str);
                }
            });
            f.k().d(this.l, this.g.p());
        }
    }

    public void n() {
        super.n();
        final c q = q();
        if (q != null) {
            CBUtility.c().post(new Runnable(this) {
                final /* synthetic */ bi b;

                public void run() {
                    String str = "javascript:Chartboost.EventHandler.handleNativeEvent(\"onBackground\", \"\")";
                    CBLogging.a("CBWebViewProtocol", "Calling native to javascript: " + str);
                    q.b.loadUrl(str);
                }
            });
            f.k().e(this.l, this.g.p());
        }
    }

    public void o() {
        if (this.m <= 1) {
            this.g.f();
            this.m++;
        }
    }

    public void d() {
        super.d();
    }

    public void p() {
        f.k().c(this.l, this.g.p());
    }

    public void a(b bVar) {
        this.w = bVar;
    }

    public c q() {
        return (c) super.e();
    }

    public void a(float f) {
        this.r = f;
    }

    public void b(float f) {
        this.q = f;
    }

    public float j() {
        return this.q;
    }

    public float k() {
        return this.r;
    }

    public void r() {
        if (!this.s) {
            f.k().d("", this.g.p());
            this.g.r();
            this.s = true;
        }
    }
}
