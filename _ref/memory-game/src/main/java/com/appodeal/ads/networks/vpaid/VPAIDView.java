package com.appodeal.ads.networks.vpaid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.utils.Log.LogLevel;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.nexage.sourcekit.mraid.rtb.ReportButton;
import org.nexage.sourcekit.mraid.rtb.ReportView;
import org.nexage.sourcekit.mraid.rtb.ReportView.ComplainedCallback;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;
import org.nexage.sourcekit.util.HttpTools;
import org.nexage.sourcekit.vast.model.TRACKING_EVENTS_TYPE;
import org.nexage.sourcekit.vast.model.VASTModel;

@SuppressLint({"ViewConstructor"})
public class VPAIDView extends RelativeLayout implements ComplainedCallback {
    private Context a;
    private a b;
    private boolean c;
    private String d;
    private String e;
    private String f;
    private WebView g;
    private boolean h = false;
    private int i = 5;
    private int j;
    private Timer k;
    private TextView l;
    private float m = 0.0f;
    private float n = 0.0f;
    private a o;
    private int p;
    private boolean q = true;
    private VASTModel r;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> s;
    private RtbInfo t;
    private boolean u = false;
    private boolean v = false;

    public interface a {
        void a();
    }

    public VPAIDView(Context context, a aVar, boolean z, String str, VASTModel vASTModel, RtbInfo rtbInfo) {
        super(context);
        if (Appodeal.getLogLevel() == LogLevel.verbose) {
            c.a(com.appodeal.ads.networks.vpaid.c.a.verbose);
        } else {
            c.a(com.appodeal.ads.networks.vpaid.c.a.error);
        }
        this.a = context;
        this.b = aVar;
        this.c = z;
        this.d = str;
        this.r = vASTModel;
        this.s = this.r.getTrackingUrls();
        this.e = vASTModel.getPickedMediaFileURL();
        this.f = vASTModel.getAdParameterms();
        this.t = rtbInfo;
        a(new LayoutParams(-1, -1));
        l();
        this.j = this.i;
        if (rtbInfo != null) {
            View f = f();
            addView(f);
            f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VPAIDView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.u) {
                        Appodeal.a("Ad was complained before");
                        return;
                    }
                    this.a.addView(this.a.g());
                }
            });
        }
        h();
        this.p = 0;
    }

    private View f() {
        View reportButton = new ReportButton(this.a);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(50, 50);
        layoutParams.addRule(12, 1);
        layoutParams.addRule(9, 1);
        reportButton.setLayoutParams(layoutParams);
        return reportButton;
    }

    private View g() {
        View reportView = new ReportView(this.a, this.g);
        reportView.registerCallback(this);
        reportView.setBackgroundColor(-1073741824);
        reportView.setInfo(this.t);
        try {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, Math.round((getResources().getDisplayMetrics().xdpi / 160.0f) * 50.0f));
            layoutParams.addRule(12);
            reportView.setLayoutParams(layoutParams);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return reportView;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(LayoutParams layoutParams) {
        this.g = new WebView(this, this.a) {
            final /* synthetic */ VPAIDView a;

            protected void onWindowVisibilityChanged(int i) {
                super.onWindowVisibilityChanged(i);
                if (i != 0) {
                    this.a.b();
                    this.a.p();
                    return;
                }
                this.a.a();
                this.a.d();
            }
        };
        this.g.setLayoutParams(layoutParams);
        this.g.getSettings().setLoadsImagesAutomatically(true);
        this.g.getSettings().setPluginState(PluginState.ON);
        this.g.getSettings().setJavaScriptEnabled(true);
        this.g.getSettings().setAppCacheEnabled(true);
        this.g.getSettings().setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            this.g.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.g.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ VPAIDView a;

            {
                this.a = r1;
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                String str = consoleMessage.message() + " in " + consoleMessage.sourceId() + " (Line: " + consoleMessage.lineNumber() + ")";
                c.a("VPAIDView", str);
                if (!(consoleMessage.messageLevel() != MessageLevel.ERROR || consoleMessage.sourceId() == null || consoleMessage.lineNumber() == 0)) {
                    if (this.a.b != null && this.a.p == 0) {
                        this.a.c(str);
                    }
                    this.a.k();
                }
                return true;
            }

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                c.a("JS alert", str2);
                return a(jsResult);
            }

            public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
                c.a("JS confirm", str2);
                return a(jsResult);
            }

            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                c.a("JS prompt", str2);
                return a(jsPromptResult);
            }

            private boolean a(JsResult jsResult) {
                jsResult.cancel();
                return true;
            }
        });
        this.g.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ VPAIDView a;

            {
                this.a = r1;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }

            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (webResourceRequest.hasGesture()) {
                    this.a.v = true;
                }
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("vpaid://")) {
                    this.a.b(str);
                } else if (this.a.v) {
                    this.a.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                if (this.a.p == 0) {
                    c.a("VPAIDView", "onPageFinished: " + str);
                    this.a.i();
                }
            }
        });
        this.g.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ VPAIDView a;

            {
                this.a = r1;
            }

            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        this.a.v = true;
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        addView(this.g);
    }

    private void h() {
        this.g.loadDataWithBaseURL("http://localhost", this.d.replace("{VPAID_JS_URL}", this.e), "text/html", "utf-8", null);
    }

    private void i() {
        if (this.f != null) {
            a("vpaid.setCreativeData(" + this.f + ")");
        }
        j();
    }

    private void j() {
        a("vpaid.loadAd()");
    }

    @SuppressLint({"NewApi"})
    public void a(String str) {
        c.a("VPAIDView", "injecting js: " + str);
        if (!TextUtils.isEmpty(str)) {
            if (VERSION.SDK_INT >= 19) {
                this.g.evaluateJavascript(str, new ValueCallback<String>(this) {
                    final /* synthetic */ VPAIDView a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onReceiveValue(Object obj) {
                        a((String) obj);
                    }

                    public void a(String str) {
                    }
                });
            } else if (this.g != null) {
                this.g.loadUrl("javascript:" + str);
            }
        }
    }

    public void a() {
        if (this.p == 1) {
            m();
        }
    }

    public void b() {
        if (this.p == 1) {
            n();
        }
    }

    @TargetApi(11)
    private void a(WebView webView) {
        try {
            if (VERSION.SDK_INT >= 11) {
                webView.onPause();
            } else {
                Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webView, (Object[]) null);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private void k() {
        c.a("VPAIDView", "closeView");
        if (this.p != 2) {
            this.p = 2;
            this.g.loadUrl("about:blank");
            a(this.g);
            e();
            if (this.o != null) {
                this.o.a();
            }
        }
    }

    public boolean c() {
        return this.h;
    }

    private void l() {
        this.l = new TextView(this.a);
        if (this.q) {
            this.l.setVisibility(4);
        } else {
            this.l.setVisibility(8);
        }
        this.l.setTextColor(-1);
        this.l.setPadding(5, 5, 5, 5);
        this.l.setBackgroundColor(Color.parseColor("#6b000000"));
        addView(this.l);
    }

    private void m() {
        if (this.j != 0 && !this.c && !this.h && this.q) {
            this.k = new Timer();
            this.k.scheduleAtFixedRate(new TimerTask(this) {
                final /* synthetic */ VPAIDView a;

                {
                    this.a = r1;
                }

                public void run() {
                    ((Activity) this.a.a).runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a("vpaid.getAdRemainingTime()");
                        }
                    });
                    if (this.a.m <= 0.0f || this.a.n <= 0.0f || this.a.n - this.a.m <= 0.0f || ((float) this.a.j) <= ((float) this.a.i) - (this.a.n - this.a.m)) {
                        this.a.j = this.a.j - 1;
                    } else {
                        this.a.j = Math.round(((float) this.a.i) - (this.a.n - this.a.m));
                    }
                    if (this.a.j <= 0) {
                        ((Activity) this.a.a).runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.o();
                            }
                        });
                        cancel();
                        return;
                    }
                    ((Activity) this.a.a).runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            CharSequence charSequence = "You can skip this video in " + String.valueOf(this.a.a.j) + " seconds";
                            this.a.a.l.setVisibility(0);
                            this.a.a.l.setText(charSequence);
                        }
                    });
                }
            }, 0, 500);
        }
    }

    private void n() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
    }

    private void o() {
        if (!this.h) {
            this.l.setText("Skip video");
            this.h = true;
            this.l.setVisibility(0);
            this.l.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VPAIDView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.b != null) {
                        this.a.b.a();
                    }
                    this.a.k();
                }
            });
        }
    }

    public void setListener(a aVar) {
        this.o = aVar;
    }

    public a getListener() {
        return this.b;
    }

    public void d() {
        if (this.p == 0) {
            this.p = 1;
            a("vpaid.fireStartAdEvent()");
            return;
        }
        a("vpaid.fireAdResumeEvent()");
    }

    private void p() {
        if (this.p == 1) {
            a("vpaid.fireAdPauseEvent()");
        }
    }

    private void b(String str) {
        String str2 = (String) new d().a(str).get("command");
        String[] strArr = new String[]{"AdSkippableStateChange", "AdDurationChange", "AdVolumeChange", "AdClickThru", "AdError", "AdLog", "AdRemainingTime", "useCustomClose"};
        try {
            if (Arrays.asList(new String[]{"AdStarted", "AdStopped", "AdSkipped", "AdLoaded", "AdLinearChange", "AdSizeChange", "AdExpandedChange", "AdImpression", "AdInteraction", "AdVideoStart", "AdVideoFirstQuartile", "AdVideoMidpoint", "AdVideoThirdQuartile", "AdVideoComplete", "AdUserAcceptInvitation", "AdUserMinimize", "AdUserClose", "AdPaused", "AdPlaying"}).contains(str2)) {
                getClass().getDeclaredMethod(str2, new Class[0]).invoke(this, new Object[0]);
            } else if (Arrays.asList(strArr).contains(str2)) {
                Object obj;
                Method declaredMethod = getClass().getDeclaredMethod(str2, new Class[]{String.class});
                if (str2.equals("AdSkippableStateChange") || str2.equals("AdDurationChange") || str2.equals("AdVolumeChange")) {
                    obj = "state";
                } else if (str2.equals("AdError") || str2.equals("AdLog")) {
                    obj = "msg";
                } else if (str2.equals("AdClickThru")) {
                    obj = "url";
                } else if (str2.equals("AdRemainingTime")) {
                    obj = Model.KEY_loadtime;
                } else if (str2.equals("useCustomClose")) {
                    obj = "useCustomClose";
                } else {
                    obj = "url";
                }
                declaredMethod.invoke(this, new Object[]{(String) r1.get(obj)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void e() {
        if (this.b != null) {
            this.b.a();
        }
        if (this.r != null) {
            a((List) this.s.get(TRACKING_EVENTS_TYPE.close));
        }
    }

    private void c(String str) {
        if (this.p == 0) {
            if (this.b != null) {
                this.b.a(str);
                if (this.r != null) {
                    this.r.sendError(900);
                }
            }
        } else if (this.p == 1) {
            k();
            if (this.r != null) {
                this.r.sendError(VASTModel.ERROR_CODE_ERROR_SHOWING);
            }
        }
    }

    private void a(List<String> list) {
        c.a("VPAIDView", "entered fireUrls");
        if (list != null) {
            for (String str : list) {
                c.b("VPAIDView", "\tfiring url:" + str);
                HttpTools.httpGetURL(str);
            }
            return;
        }
        c.a("VPAIDView", "\turl list is null");
    }

    public void wasComplained() {
        this.u = true;
    }
}
