package com.appodeal.ads.networks.spotx;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.JavascriptInterface;
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
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nexage.sourcekit.util.VASTLog;

@SuppressLint({"ViewConstructor"})
public class SpotXVPAIDView extends RelativeLayout {
    private static String b = "VPAIDView";
    String a = "video/.*(?i)(mp4|3gpp|webm|matroska)";
    private WebView c;
    private TextView d;
    private Context e;
    private c f;
    private a g;
    private int h = 5;
    private int i;
    private Timer j;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private Uri n;
    private String o;
    private String p;
    private boolean q = false;
    private boolean r = false;
    private boolean s;
    private int t = 0;
    private int u = 0;
    private String v;
    private String w;
    private File x;
    private boolean y;
    private boolean z;

    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public interface c {
        void a();
    }

    public class b {
        Context a;
        final /* synthetic */ SpotXVPAIDView b;

        b(SpotXVPAIDView spotXVPAIDView, Context context) {
            this.b = spotXVPAIDView;
            this.a = context;
        }

        @JavascriptInterface
        public void complete() {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad complete");
                    if (this.a.b.g != null) {
                        this.a.b.g.d();
                    }
                }
            });
        }

        @JavascriptInterface
        public void close() {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad closed");
                    if (this.a.b.g != null) {
                        this.a.b.g.c();
                    }
                    this.a.b.l();
                }
            });
        }

        @JavascriptInterface
        public void error(final String str) {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad error: " + str);
                    if (this.b.b.g != null) {
                        if (this.b.b.l) {
                            this.b.b.g.c();
                        } else {
                            this.b.b.g.b();
                        }
                    }
                    this.b.b.l();
                }
            });
        }

        @JavascriptInterface
        public void loaded() {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad loaded");
                    this.a.b.r = true;
                    this.a.b.a("if(typeof vpaid !== 'undefined' && vpaid != null) Android.duration(vpaid.getAdDuration())");
                }
            });
        }

        @JavascriptInterface
        public void start() {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad started " + String.valueOf(this.a.b.l));
                    if (!this.a.b.l) {
                        this.a.b.l = true;
                        this.a.b.a("if(typeof vpaid !== 'undefined' && vpaid != null) vpaid.pauseAd();");
                    }
                }
            });
        }

        @JavascriptInterface
        public void pause() {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad paused");
                    if (this.a.b.l && !this.a.b.m) {
                        this.a.b.a("if(typeof vpaid !== 'undefined' && vpaid != null) vpaid.resumeAd();");
                    }
                }
            });
        }

        @JavascriptInterface
        public void showHTML(final String str) {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "HTML: " + str);
                }
            });
        }

        @JavascriptInterface
        public void log(final String str) {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "log: " + str);
                }
            });
        }

        @JavascriptInterface
        public void playing() {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "Ad playing: " + String.valueOf(System.currentTimeMillis()));
                    if (this.a.b.l && !this.a.b.m) {
                        this.a.b.i();
                    }
                }
            });
        }

        @JavascriptInterface
        public void duration(final int i) {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "duration: " + String.valueOf(i));
                    if (i > 0 || !this.b.b.y) {
                        this.b.b.u = i;
                        this.b.b.t = i;
                        if (this.b.b.q && this.b.b.r && this.b.b.g != null) {
                            this.b.b.g.a();
                            return;
                        }
                        return;
                    }
                    this.b.b.g.b();
                }
            });
        }

        @JavascriptInterface
        public void checkTimer(final int i) {
            ((Activity) this.b.e).runOnUiThread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    VASTLog.d(SpotXVPAIDView.b, "remainingTime: " + String.valueOf(i));
                    this.b.b.t = i;
                }
            });
        }
    }

    public SpotXVPAIDView(Context context, Pair<String, String> pair, a aVar, boolean z, boolean z2) {
        boolean z3 = false;
        super(context);
        this.e = context;
        this.g = aVar;
        this.s = z;
        this.y = z2;
        this.v = (String) pair.first;
        this.w = (String) pair.second;
        a(new LayoutParams(-1, -1));
        h();
        this.i = this.h;
        if (this.y) {
            int i;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
            int i2;
            int i3;
            if (point.x > point.y) {
                i = point.x;
                i2 = point.y;
                i3 = i;
            } else {
                i = point.y;
                i2 = point.x;
                i3 = i;
            }
            try {
                final JSONObject jSONObject = new JSONObject(this.w);
                if (jSONObject.has(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_MEDIA);
                    if (jSONObject2.has(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                        JSONArray optJSONArray = jSONObject2.optJSONArray(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                        String optString;
                        if (optJSONArray == null) {
                            JSONObject optJSONObject = jSONObject2.optJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                            if (optJSONObject != null) {
                                Iterator keys = optJSONObject.keys();
                                boolean z4 = false;
                                while (keys.hasNext()) {
                                    boolean optInt;
                                    JSONObject optJSONObject2 = optJSONObject.optJSONObject((String) keys.next());
                                    if (optJSONObject2 != null) {
                                        if (this.o == null || this.o.isEmpty()) {
                                            this.o = optJSONObject2.optString("source_uri") != null ? optJSONObject2.optString("source_uri") : optJSONObject2.optString("media_url");
                                            this.p = optJSONObject2.optString("mime_type");
                                            z4 = optJSONObject2.optInt("width", 0);
                                            optInt = optJSONObject2.optInt("height", 0);
                                            z3 = z4;
                                        } else if (z4 >= optJSONObject2.optInt("width", 0) || optJSONObject2.optInt("width", 0) >= r4 || z3 >= optJSONObject2.optInt("height", 0) || optJSONObject2.optInt("height", 0) >= r3) {
                                            optInt = z3;
                                            z3 = z4;
                                        } else {
                                            if (optJSONObject2.optString("source_uri") != null) {
                                                optString = optJSONObject2.optString("source_uri");
                                            } else {
                                                optString = optJSONObject2.optString("media_url");
                                            }
                                            this.o = optString;
                                            this.p = optJSONObject2.optString("mime_type");
                                            z4 = optJSONObject2.optInt("width", 0);
                                            optInt = optJSONObject2.optInt("height", 0);
                                            z3 = z4;
                                        }
                                        if (optJSONObject2.has("source_uri")) {
                                            optJSONObject2.put("source_uri", "REPLACE_ME");
                                        }
                                        if (optJSONObject2.has("media_url")) {
                                            optJSONObject2.put("media_url", "REPLACE_ME");
                                        }
                                        if (optJSONObject2.has("mime_type")) {
                                            optJSONObject2.put("mime_type", "REPLACE_MIME_TYPE");
                                        }
                                    } else {
                                        optInt = z3;
                                        z3 = z4;
                                    }
                                    z4 = z3;
                                    z3 = optInt;
                                }
                            }
                        } else if (optJSONArray.length() > 0) {
                            int i4 = 0;
                            for (i = 0; i < optJSONArray.length(); i++) {
                                JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                                int optInt2;
                                if (this.o == null || this.o.isEmpty()) {
                                    this.o = jSONObject3.optString("source_uri") != null ? jSONObject3.optString("source_uri") : jSONObject3.optString("media_url");
                                    this.p = jSONObject3.optString("mime_type");
                                    optInt2 = jSONObject3.optInt("width", 0);
                                    i4 = jSONObject3.optInt("height", 0);
                                } else if (optInt2 < jSONObject3.optInt("width", 0) && jSONObject3.optInt("width", 0) < r4 && r0 < jSONObject3.optInt("height", 0) && jSONObject3.optInt("height", 0) < r3) {
                                    if (jSONObject3.optString("source_uri") != null) {
                                        optString = jSONObject3.optString("source_uri");
                                    } else {
                                        optString = jSONObject3.optString("media_url");
                                    }
                                    this.o = optString;
                                    this.p = jSONObject3.optString("mime_type");
                                    optInt2 = jSONObject3.optInt("width", 0);
                                    i4 = jSONObject3.optInt("height", 0);
                                }
                                if (jSONObject3.has("source_uri")) {
                                    jSONObject3.put("source_uri", "REPLACE_ME");
                                }
                                if (jSONObject3.has("media_url")) {
                                    jSONObject3.put("media_url", "REPLACE_ME");
                                }
                                if (jSONObject3.has("mime_type")) {
                                    jSONObject3.put("mime_type", "REPLACE_MIME_TYPE");
                                }
                            }
                        }
                    }
                }
                VASTLog.d(b, "source :" + this.o);
                if (!this.o.isEmpty() && !this.o.equals("") && b(this.p)) {
                    new Thread(new Runnable(this) {
                        final /* synthetic */ SpotXVPAIDView b;

                        public void run() {
                            boolean z = false;
                            try {
                                z = this.b.c(this.b.o);
                            } catch (Exception e) {
                            }
                            if (z) {
                                this.b.q = true;
                                VASTLog.d(SpotXVPAIDView.b, "File uploaded :" + this.b.n.getPath());
                                this.b.w = jSONObject.toString();
                                this.b.f();
                            } else if (this.b.g != null) {
                                this.b.g.b();
                            }
                        }
                    }).start();
                    return;
                } else if (this.g != null) {
                    this.g.b();
                    return;
                } else {
                    return;
                }
            } catch (Exception e) {
                VASTLog.d(b, "source not found");
                if (this.g != null) {
                    this.g.b();
                    return;
                }
                return;
            }
        }
        this.q = true;
        g();
    }

    private boolean b(String str) {
        return str.matches(this.a);
    }

    private boolean c(String str) {
        String str2 = "temp" + System.currentTimeMillis();
        File file = new File(this.e.getExternalFilesDir(null).getPath() + "/vast_rtb_cache/");
        if (!file.exists()) {
            file.mkdirs();
        }
        this.x = new File(file, str2);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(this.x);
        long contentLength = (long) httpURLConnection.getContentLength();
        long j = 0;
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            fileOutputStream.write(bArr, 0, read);
            j += (long) read;
        }
        fileOutputStream.close();
        if (contentLength == j) {
            this.n = Uri.fromFile(this.x);
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(this.n.getPath(), 1);
            if (createVideoThumbnail == null) {
                VASTLog.d(b, "video file not supported");
            } else if (!createVideoThumbnail.equals(Bitmap.createBitmap(createVideoThumbnail.getWidth(), createVideoThumbnail.getHeight(), createVideoThumbnail.getConfig()))) {
                return true;
            } else {
                VASTLog.d(b, "empty thumbnail");
            }
        }
        return false;
    }

    public boolean a() {
        if (this.x != null) {
            return this.x.exists();
        }
        return false;
    }

    private void f() {
        this.w = this.w.replace("REPLACE_ME", this.n.toString()).replace("REPLACE_MIME_TYPE", this.p);
        ((Activity) this.e).runOnUiThread(new Runnable(this) {
            final /* synthetic */ SpotXVPAIDView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g();
            }
        });
    }

    private void g() {
        this.c.loadDataWithBaseURL("http://localhost", "<html>\n<head>\n    <style>\n        .video_controls {\n            visibility: hidden !important;\n        }\n        iframe {\n            top: 0;\n            left: 0;\n        }\n    </style>\n    <script src=\"" + this.v + "\" type=\"application/javascript\"></script>\n    <script type=\"application/javascript\">\n        var creativeData = " + this.w + "\n\n            var testConnection = function(url) {\n                var xmlhttp = new XMLHttpRequest();\n                xmlhttp.onload = function() { Android.checkState(true); }\n                xmlhttp.onerror = function() { Android.checkState(false); }\n                xmlhttp.open(\"GET\",url,true);\n                xmlhttp.send();\n            }\n\n        document.addEventListener('DOMContentLoaded', function() {\n            var vpaid = window.vpaid = getVPAIDAd && getVPAIDAd();\n            if (!vpaid) {\n                return;\n            }\n\n            vpaid.initAd(0, 0, \"fullscreen\", 0, JSON.stringify(creativeData), {\n                slot: document.body,\n                https: 0,\n                autoplay: true\n            });\n\n            function loaded() {\n                Android.loaded();\n            }\n            function stopped() {\n                Android.close();\n            }\n            function error(str) {\n                Android.error(str);\n            }\n            function start() {\n                Android.start();\n            }\n            function pause() {\n                Android.pause();\n            }\n            function playing() {\n                Android.playing();\n            }\n            function complete() {\n                Android.complete();\n            }\n            function log(str) {\n                Android.log(str);\n            }\n            function sendError(str) {\n                Android.error(str);\n            }\n            vpaid.subscribe(loaded, 'AdLoaded', null);\n            vpaid.subscribe(stopped, 'AdStopped', null);\n            vpaid.subscribe(error, 'AdError', null);\n            vpaid.subscribe(start, 'AdStarted', null);\n            vpaid.subscribe(pause, 'AdPaused', null);\n            vpaid.subscribe(playing, 'AdPlaying', null);\n            vpaid.subscribe(complete, 'AdVideoComplete', null);\n\n        });\n\n    </script>\n</head>\n<body style='margin: 0; padding: 0; background-color: black; position: fixed;'></body>\n</html>", "text/html", "utf-8", null);
        VASTLog.d(b, "WebView started");
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void a(LayoutParams layoutParams) {
        this.c = new WebView(this, this.e) {
            final /* synthetic */ SpotXVPAIDView a;

            protected void onWindowVisibilityChanged(int i) {
                super.onWindowVisibilityChanged(i);
                if (i != 0) {
                    this.a.b();
                    return;
                }
                this.a.c();
                this.a.a("if(typeof vpaid !== 'undefined' && vpaid != null) vpaid.startAd();");
            }
        };
        this.c.setLayoutParams(layoutParams);
        this.c.getSettings().setLoadsImagesAutomatically(true);
        this.c.getSettings().setPluginState(PluginState.ON);
        this.c.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            this.c.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 16) {
            this.c.getSettings().setAllowFileAccessFromFileURLs(true);
            this.c.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        this.c.addJavascriptInterface(new b(this, this.e), "Android");
        this.c.getSettings().setAppCacheEnabled(true);
        this.c.getSettings().setDomStorageEnabled(true);
        this.c.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ SpotXVPAIDView a;

            {
                this.a = r1;
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                VASTLog.d(SpotXVPAIDView.b, consoleMessage.messageLevel().toString() + ": " + (consoleMessage.message() + " in " + consoleMessage.sourceId() + " (Line: " + consoleMessage.lineNumber() + ")"));
                if (!(consoleMessage.messageLevel() != MessageLevel.ERROR || consoleMessage.sourceId() == null || consoleMessage.lineNumber() == 0)) {
                    if (this.a.g != null) {
                        if (this.a.l) {
                            this.a.g.c();
                        } else {
                            this.a.g.b();
                        }
                    }
                    this.a.l();
                }
                return true;
            }

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                VASTLog.d("JS alert", str2);
                return a(jsResult);
            }

            public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
                VASTLog.d("JS confirm", str2);
                return a(jsResult);
            }

            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                VASTLog.d("JS prompt", str2);
                return a(jsPromptResult);
            }

            private boolean a(JsResult jsResult) {
                jsResult.cancel();
                return true;
            }
        });
        this.c.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ SpotXVPAIDView a;

            {
                this.a = r1;
            }

            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        this.a.z = true;
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        this.c.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ SpotXVPAIDView a;

            {
                this.a = r1;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                VASTLog.d(SpotXVPAIDView.b, "onReceivedError");
            }

            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                VASTLog.d(SpotXVPAIDView.b, "onReceivedHttpError");
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (webResourceRequest.hasGesture()) {
                    this.a.z = true;
                }
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!Uri.parse(str).getScheme().equals("vpaid") && this.a.z) {
                    this.a.e.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return true;
            }
        });
        addView(this.c);
    }

    private void h() {
        this.d = new TextView(this.e);
        this.d.setVisibility(4);
        this.d.setTextColor(-1);
        this.d.setPadding(5, 5, 5, 5);
        this.d.setBackgroundColor(Color.parseColor("#6b000000"));
        addView(this.d);
    }

    private void i() {
        if (this.i != 0 && !this.s && !this.k) {
            this.j = new Timer();
            this.j.scheduleAtFixedRate(new TimerTask(this) {
                final /* synthetic */ SpotXVPAIDView a;

                {
                    this.a = r1;
                }

                public void run() {
                    ((Activity) this.a.e).runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a("if(typeof vpaid !== 'undefined' && vpaid != null) Android.checkTimer(vpaid.getAdRemainingTime());");
                        }
                    });
                    if (this.a.t <= 0 || this.a.u <= 0 || this.a.u - this.a.t <= 0 || this.a.i <= this.a.h - (this.a.u - this.a.t)) {
                        this.a.i = this.a.i - 1;
                    } else {
                        this.a.i = this.a.h - (this.a.u - this.a.t);
                    }
                    if (this.a.i <= 0) {
                        ((Activity) this.a.e).runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.k();
                            }
                        });
                        cancel();
                        return;
                    }
                    ((Activity) this.a.e).runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            CharSequence charSequence = "You can skip this video in " + String.valueOf(this.a.a.i) + " seconds";
                            this.a.a.d.setVisibility(0);
                            this.a.a.d.setText(charSequence);
                        }
                    });
                }
            }, 0, 500);
        }
    }

    private void j() {
        VASTLog.d(b, "entered stopSkipTimer");
        if (this.j != null) {
            this.j.cancel();
            this.j = null;
        }
    }

    private void k() {
        if (!this.k) {
            this.d.setText("Skip video");
            this.k = true;
            this.d.setVisibility(0);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SpotXVPAIDView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.g != null) {
                        this.a.g.c();
                    }
                    this.a.l();
                }
            });
        }
    }

    public void setListener(c cVar) {
        this.f = cVar;
    }

    @SuppressLint({"NewApi"})
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (VERSION.SDK_INT >= 19) {
                this.c.evaluateJavascript(str, new ValueCallback<String>(this) {
                    final /* synthetic */ SpotXVPAIDView a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onReceiveValue(Object obj) {
                        a((String) obj);
                    }

                    public void a(String str) {
                    }
                });
                return;
            }
            VASTLog.d(b, "loading url: " + str);
            if (this.c != null) {
                this.c.loadUrl("javascript:" + str);
            }
        }
    }

    public void b() {
        if (this.l) {
            this.m = true;
            a("if(typeof vpaid !== 'undefined' && vpaid != null) vpaid.pauseAd();");
            j();
        }
    }

    @TargetApi(11)
    private void a(WebView webView) {
        VASTLog.d(b, "pauseWebView " + webView.toString());
        if (VERSION.SDK_INT >= 11) {
            webView.onPause();
            return;
        }
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webView, (Object[]) null);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void c() {
        if (this.l) {
            this.m = false;
            a("if(typeof vpaid !== 'undefined' && vpaid != null) vpaid.resumeAd();");
            i();
        }
    }

    private void l() {
        VASTLog.d(b, "closeView");
        this.c.loadUrl("about:blank");
        a(this.c);
        try {
            this.x.delete();
        } catch (Exception e) {
        }
        if (this.f != null) {
            this.f.a();
        }
    }

    public boolean d() {
        return this.k;
    }

    public a getListener() {
        return this.g;
    }
}
