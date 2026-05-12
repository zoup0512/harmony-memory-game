package com.my.target.core.engines;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.my.target.Tracer;
import com.my.target.ads.MyTargetView;
import com.my.target.core.communication.js.b;
import com.my.target.core.communication.js.c;
import com.my.target.core.communication.js.calls.d;
import com.my.target.core.communication.js.events.e;
import com.my.target.core.communication.js.events.f;
import com.my.target.core.communication.js.events.i;
import com.my.target.core.facades.h;
import com.my.target.core.ui.views.AdView;
import com.my.target.core.ui.views.controls.AdInfoButton;
import org.json.JSONObject;

/* compiled from: StandardAdEngine */
public final class g extends a {
    private h c;
    private h d;
    private MyTargetView e;
    private AdView f;
    private WebView g;
    private AdInfoButton h;
    private boolean i;
    private boolean j;
    private c k = new c();
    private a l = new a();
    private WebChromeClient m = new WebChromeClient(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            Tracer.d("js console message: " + message + " at line: " + consoleMessage.lineNumber());
            f a = com.my.target.core.communication.js.a.a(consoleMessage);
            if (a == null) {
                return false;
            }
            this.a.k.a(a);
            return true;
        }
    };
    private WebViewClient n = new WebViewClient(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            Tracer.d("load failed. error: " + i + " description: " + str + " url: " + str2);
            super.onReceivedError(webView, i, str, str2);
            if (this.a.e.getListener() != null) {
                this.a.e.getListener().onNoAd(str, this.a.e);
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Tracer.d("load page started");
            super.onPageStarted(webView, str, bitmap);
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.a.i) {
                this.a.i = true;
                Tracer.d("page loaded");
                super.onPageFinished(webView, str);
                if (this.a.c != null) {
                    JSONObject c = this.a.c.c();
                    if (c != null) {
                        this.a.a(new d(c));
                    }
                }
            }
        }

        public final void onScaleChanged(WebView webView, float f, float f2) {
            super.onScaleChanged(webView, f, f2);
            Tracer.d("scale new: " + f2 + " old: " + f);
        }
    };
    private b o = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            this.a.l.d();
            if (this.a.e.getListener() != null) {
                this.a.e.getListener().onLoad(this.a.e);
            }
        }
    };
    private b p = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            String str;
            e eVar = (e) fVar;
            String str2 = "JS error";
            if (eVar == null || eVar.b() == null) {
                str = str2;
            } else {
                str = str2 + ": " + eVar.b();
            }
            String str3 = "";
            if (this.a.c != null) {
                str3 = this.a.c.f();
            }
            if (fVar.a().equals("onError")) {
                com.my.target.core.async.a.a(str, getClass().getName(), 40, "JSError", str3, this.a.b);
                if (this.a.e.getListener() == null) {
                    return;
                }
                if (this.a.l.c()) {
                    this.a.e.getListener().onNoAd("JS error", this.a.e);
                    return;
                } else {
                    this.a.e.getListener().onNoAd("JS init error", this.a.e);
                    return;
                }
            }
            com.my.target.core.async.a.a(str, getClass().getName(), 30, "JSError", str3, this.a.b);
        }
    };
    private b q = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            this.a.f.setVisibility(4);
            this.a.l.a(false);
            this.a.l.b(false);
            if (this.a.e.getListener() != null) {
                this.a.e.getListener().onNoAd("Ad completed", this.a.e);
            }
        }
    };
    private b r = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            if (this.a.l.c()) {
                this.a.l.a(false);
                if (this.a.e.getListener() != null) {
                    this.a.e.getListener().onNoAd("No ad", this.a.e);
                    return;
                }
                return;
            }
            this.a.l.e();
            if (this.a.e.getListener() != null) {
                this.a.e.getListener().onNoAd("JS init error", this.a.e);
            }
        }
    };
    private b s = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            com.my.target.core.communication.js.events.d dVar = (com.my.target.core.communication.js.events.d) fVar;
            h e = this.a.c;
            for (String b : dVar.b()) {
                e.b(b);
            }
        }
    };
    private b t = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            this.a.c.a(((com.my.target.core.communication.js.events.c) fVar).b());
            if (this.a.e.getListener() != null) {
                this.a.e.getListener().onClick(this.a.e);
            }
        }
    };
    private b u = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            com.my.target.core.async.a.a(((i) fVar).b(), this.a.b);
        }
    };
    private b v = new b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void a(f fVar) {
            if (this.a.c != null && this.a.c.a() && this.a.d == null) {
                this.a.d = this.a.c.h();
                this.a.d.a(this.a.w);
                this.a.d.load();
            }
        }
    };
    private com.my.target.core.facades.h.a w = new com.my.target.core.facades.h.a(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final void onLoad(h hVar) {
            if (hVar == this.a.d) {
                this.a.d.a(null);
                this.a.d = null;
                g.b(this.a, hVar);
            }
        }

        public final void onNoAd(String str, h hVar) {
            if (this.a.d == hVar) {
                hVar.a(null);
                this.a.d = null;
            }
        }
    };

    /* compiled from: StandardAdEngine */
    private static class a {
        private boolean a;
        private boolean b;
        private boolean c;

        public final boolean a() {
            return this.a;
        }

        public final void a(boolean z) {
            this.a = z;
        }

        public final boolean b() {
            return this.b;
        }

        public final void b(boolean z) {
            this.b = z;
        }

        public final boolean c() {
            return this.c;
        }

        public final void d() {
            this.c = true;
        }

        public final void e() {
            this.c = false;
            this.b = false;
            this.a = false;
        }
    }

    public g(MyTargetView myTargetView, Context context) {
        super(myTargetView, context);
        this.e = myTargetView;
        this.g = new WebView(this.b);
        this.g.setHorizontalScrollBarEnabled(false);
        this.g.setVerticalScrollBarEnabled(false);
        this.g.getSettings().setJavaScriptEnabled(true);
        this.g.getSettings().setSupportZoom(false);
        this.g.setWebViewClient(this.n);
        this.g.setWebChromeClient(this.m);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        float f = this.b.getResources().getDisplayMetrics().density;
        this.f = new AdView(this.b);
        this.f.setMaxWidth((int) (640.0f * f));
        this.f.addView(this.g, layoutParams);
        this.f.setVisibility(4);
        layoutParams = new RelativeLayout.LayoutParams(-1, (int) (f * 50.0f));
        layoutParams.addRule(13);
        this.f.setLayoutParams(layoutParams);
        this.a.addView(this.f);
        this.k.a("onReady", this.o);
        this.k.a("onError", this.p);
        this.k.a("onAdError", this.p);
        this.k.a("onComplete", this.q);
        this.k.a("onNoAd", this.r);
        this.k.a("onAdStart", this.s);
        this.k.a("onStat", this.u);
        this.k.a("onAdClick", this.t);
        this.k.a("onRequestNewAds", this.v);
    }

    public final void a(com.my.target.core.facades.g gVar) {
        if (gVar instanceof h) {
            this.l.e();
            this.c = (h) gVar;
            this.g.stopLoading();
            this.i = false;
            if (this.c.b() != null) {
                a(this.c.d());
                Tracer.d("load page");
                this.g.loadData(this.c.b(), "text/html", "utf-8");
                return;
            } else if (this.e.getListener() != null) {
                this.e.getListener().onNoAd("No ad", this.e);
                return;
            } else {
                return;
            }
        }
        Tracer.d("StandardAdEngine: incorrect ad type");
    }

    public final void d() {
        super.d();
        if (!this.l.c()) {
            Tracer.d("not ready");
        } else if (this.l.a()) {
            Tracer.d("already started");
        } else {
            this.l.a(true);
            this.f.setDesiredSize(0, 0);
            this.f.setVisibility(0);
            a(new com.my.target.core.communication.js.calls.e("standard_320x50", this.f.getContext().getResources().getConfiguration().orientation));
        }
    }

    public final void a() {
        super.a();
        if (!this.l.a()) {
            Tracer.d("not started");
        } else if (this.l.b()) {
            Tracer.d("already paused");
        } else {
            this.l.b(true);
            a(new com.my.target.core.communication.js.calls.b("pause"));
        }
    }

    public final void b() {
        super.b();
        if (!this.l.a()) {
            Tracer.d("not started");
        } else if (this.l.b()) {
            this.l.b(false);
            a(new com.my.target.core.communication.js.calls.b("resume"));
        } else {
            Tracer.d("already started");
        }
    }

    public final void c() {
        super.c();
        if (this.l.a()) {
            this.l.b(false);
            this.l.a(false);
            this.f.setVisibility(4);
            if (this.d != null) {
                this.d.a(null);
                this.d = null;
            }
            a(new com.my.target.core.communication.js.calls.b("stop"));
            return;
        }
        Tracer.d("not started");
    }

    public final void f() {
        super.f();
        if (!this.j) {
            this.j = true;
            this.k.a();
            this.k = null;
            if (this.f.getParent() != null) {
                ((ViewGroup) this.f.getParent()).removeView(this.f);
            }
            this.f.removeAllViews();
            this.f = null;
            this.g.setWebChromeClient(null);
            this.g.setWebViewClient(null);
            this.g.destroy();
            this.g = null;
            this.c = null;
            this.e = null;
            this.l = null;
            if (this.d != null) {
                this.d.a(null);
                this.d = null;
            }
        }
    }

    public final void a(com.my.target.core.engines.b.a aVar) {
    }

    private void a(String str) {
        if (str != null) {
            if (this.h == null) {
                this.h = new AdInfoButton(this.b);
                this.f.addView(this.h, -2, -2);
            }
            this.h.setUrl(str);
        } else if (this.h != null) {
            ViewGroup viewGroup = (ViewGroup) this.h.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.h);
            }
            this.h = null;
        }
    }

    private void a(com.my.target.core.communication.js.calls.c cVar) {
        if (this.g != null) {
            try {
                String str = "javascript:AdmanJS.execute(" + cVar.b().toString() + ")";
                Tracer.d(str);
                this.g.loadUrl(str);
            } catch (Throwable th) {
                Tracer.d("fail to execute js call: " + th.getMessage());
                Context context = this.b;
                Tracer.d("add log message level: 50");
                com.my.target.core.factories.b.a("Internal error: fail to execute JSCall " + cVar.a(), getClass().getName(), 50, null, th, "", context).b();
            }
        }
    }

    static /* synthetic */ void b(g gVar, h hVar) {
        gVar.c = hVar;
        gVar.a(hVar.d());
        JSONObject c = hVar.c();
        if (c != null) {
            gVar.a(new com.my.target.core.communication.js.calls.f(c));
        }
    }
}
