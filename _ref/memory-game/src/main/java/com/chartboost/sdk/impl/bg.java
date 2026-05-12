package com.chartboost.sdk.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.bi.b;
import com.mopub.mobileads.VastIconXmlManager;
import org.json.JSONException;
import org.json.JSONObject;

public class bg extends WebChromeClient {
    private static final String a = bg.class.getSimpleName();
    private View b;
    private ViewGroup c;
    private View d;
    private bh e;
    private boolean f;
    private FrameLayout g;
    private CustomViewCallback h;
    private a i;
    private bi j;
    private final Handler k = CBUtility.c();

    public interface a {
        void a(boolean z);
    }

    public bg(View view, ViewGroup viewGroup, View view2, bh bhVar, bi biVar) {
        this.b = view;
        this.c = viewGroup;
        this.d = view2;
        this.e = bhVar;
        this.f = false;
        this.j = biVar;
    }

    public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d(bg.class.getSimpleName(), "Chartboost Webview:" + cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        try {
            JSONObject a = a.a().a(message);
            result.confirm(a(a.getJSONObject("eventArgs"), a.getString("eventType")));
        } catch (JSONException e) {
            CBLogging.b(a, "Exception caught parsing the function name from js to native");
        }
        return true;
    }

    public String a(JSONObject jSONObject, String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -1554056650:
                if (str.equals("currentVideoDuration")) {
                    obj = 6;
                    break;
                }
                break;
            case -1263203643:
                if (str.equals("openUrl")) {
                    obj = 13;
                    break;
                }
                break;
            case -1086137328:
                if (str.equals("videoCompleted")) {
                    obj = 2;
                    break;
                }
                break;
            case -640720077:
                if (str.equals("videoPlaying")) {
                    obj = 3;
                    break;
                }
                break;
            case 3529469:
                if (str.equals("show")) {
                    obj = 8;
                    break;
                }
                break;
            case 94750088:
                if (str.equals("click")) {
                    obj = null;
                    break;
                }
                break;
            case 94756344:
                if (str.equals("close")) {
                    obj = 1;
                    break;
                }
                break;
            case 95458899:
                if (str.equals("debug")) {
                    obj = 11;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    obj = 9;
                    break;
                }
                break;
            case 939594121:
                if (str.equals("videoPaused")) {
                    obj = 4;
                    break;
                }
                break;
            case 1000390722:
                if (str.equals("videoReplay")) {
                    obj = 5;
                    break;
                }
                break;
            case 1082777163:
                if (str.equals("totalVideoDuration")) {
                    obj = 7;
                    break;
                }
                break;
            case 1124446108:
                if (str.equals("warning")) {
                    obj = 10;
                    break;
                }
                break;
            case 1270488759:
                if (str.equals("tracking")) {
                    obj = 12;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                m(jSONObject);
                break;
            case 1:
                Log.d(a, "JavaScript to native close callback triggered");
                n(jSONObject);
                break;
            case 2:
                Log.d(a, "JavaScript to native video complete callback triggered");
                d(jSONObject);
                break;
            case 3:
                Log.d(a, "JavaScript to native video playing callback triggered");
                j(jSONObject);
                break;
            case 4:
                Log.d(a, "JavaScript to native video pause callback triggered");
                i(jSONObject);
                break;
            case 5:
                Log.d(a, "JavaScript to native video replay callback triggered");
                h(jSONObject);
                break;
            case 6:
                k(jSONObject);
                break;
            case 7:
                Log.d(a, "JavaScript to native total duration callback triggered");
                l(jSONObject);
                break;
            case 8:
                Log.d(a, "JavaScript to native show callback triggered");
                g(jSONObject);
                break;
            case 9:
                Log.d(a, "JavaScript to native error callback triggered");
                e(jSONObject);
                break;
            case 10:
                Log.d(a, "JavaScript to native warning callback triggered");
                f(jSONObject);
                break;
            case 11:
                Log.d(a, "JavaScript to native webview debug event callback triggered");
                c(jSONObject);
                break;
            case 12:
                Log.d(a, "JavaScript to native webview vast tracking event callback triggered");
                b(jSONObject);
                break;
            case 13:
                Log.d(a, "JavaScript to native webview openUrl event callback triggered");
                a(jSONObject);
                break;
            default:
                return "Function name not recognized.";
        }
        return "Native function successfully called.";
    }

    public void a(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    String string = jSONObject.getString("url");
                    if (!(string.startsWith("http://") || string.startsWith("https://"))) {
                        string = "http://" + string;
                    }
                    this.b.j.q().getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
                    CBLogging.a(bh.class.getName(), "JS->Native Track MRAID openUrl: " + string);
                } catch (Exception e) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "ActivityNotFoundException occured when opening a url in a browser", e);
                    CBLogging.b(bg.a, "ActivityNotFoundException occured when opening a url in a browser");
                } catch (Exception e2) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "Exception while opening a browser view with MRAID url", e2);
                    CBLogging.b(bg.a, "Exception while opening a browser view with MRAID url");
                }
            }
        });
    }

    public void b(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    String string = jSONObject.getString("event");
                    this.b.j.b(string);
                    Log.d(bh.class.getName(), "JS->Native Track VAST event message: " + string);
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Exception occured while parsing the message for webview tracking VAST events");
                }
            }
        });
    }

    public void c(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    String string = jSONObject.getString("message");
                    Log.d(bh.class.getName(), "JS->Native Debug message: " + string);
                    this.b.j.c(string);
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Exception occured while parsing the message for webview debug track event");
                    this.b.j.c("Exception occured while parsing the message for webview debug track event");
                }
            }
        });
    }

    public void d(JSONObject jSONObject) {
        Log.d(bh.class.getName(), "Video is Completed");
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.onHideCustomView();
                this.a.j.a(b.IDLE);
                this.a.j.o();
            }
        });
    }

    public void e(final JSONObject jSONObject) {
        Log.d(bh.class.getName(), "Javascript Error occured");
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    String string = jSONObject.getString("message");
                    Log.d(bh.class.getName(), "JS->Native Error message: " + string);
                    this.b.j.d(string);
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Error message is empty");
                    this.b.j.d("");
                }
            }
        });
    }

    public void f(final JSONObject jSONObject) {
        Log.d(bh.class.getName(), "Javascript warning occurred");
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    String string = jSONObject.getString("message");
                    Log.d(bh.class.getName(), "JS->Native Warning message: " + string);
                    this.b.j.e(string);
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Warning message is empty");
                    this.b.j.e("");
                }
            }
        });
    }

    public void g(JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.r();
            }
        });
    }

    public void h(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    CharSequence string = jSONObject.getString("name");
                    if (!a.a().a(string)) {
                        this.b.j.l = string;
                    }
                    this.b.j.p();
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Cannot find video file name");
                    this.b.j.e("Parsing exception unknown field for video replay");
                }
            }
        });
    }

    public void i(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    CharSequence string = jSONObject.getString("name");
                    if (!a.a().a(string)) {
                        this.b.j.l = string;
                    }
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Cannot find video file name");
                    this.b.j.e("Parsing exception unknown field for video pause");
                }
                this.b.j.a(b.PAUSED);
            }
        });
    }

    public void j(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    CharSequence string = jSONObject.getString("name");
                    if (!a.a().a(string)) {
                        this.b.j.l = string;
                    }
                } catch (Exception e) {
                    CBLogging.b(bg.a, "Cannot find video file name");
                    this.b.j.e("Parsing exception unknown field for video play");
                }
                this.b.j.a(b.PLAYING);
            }
        });
    }

    public void k(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    float f = (float) jSONObject.getDouble(VastIconXmlManager.DURATION);
                    CBLogging.a(bg.a, "######### JS->Native Video current player duration" + (f * 1000.0f));
                    this.b.j.a(f * 1000.0f);
                } catch (Exception e) {
                    this.b.j.e("Parsing exception unknown field for current player duration");
                    CBLogging.b(bg.a, "Cannot find duration parameter for the video");
                }
            }
        });
    }

    public void l(final JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg b;

            public void run() {
                try {
                    float f = (float) jSONObject.getDouble(VastIconXmlManager.DURATION);
                    CBLogging.a(bg.a, "######### JS->Native Video total player duration" + (f * 1000.0f));
                    this.b.j.b(f * 1000.0f);
                } catch (Exception e) {
                    this.b.j.e("Parsing exception unknown field for total player duration");
                    CBLogging.b(bg.a, "Cannot find duration parameter for the video");
                }
            }
        });
    }

    public void m(JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.a(null, null);
            }
        });
    }

    public void n(JSONObject jSONObject) {
        this.k.post(new Runnable(this) {
            final /* synthetic */ bg a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.h();
            }
        });
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            this.f = true;
            this.g = frameLayout;
            this.h = callback;
            this.b.setVisibility(4);
            this.c.addView(this.g, new LayoutParams(-1, -1));
            this.c.setVisibility(0);
            if (this.i != null) {
                this.i.a(true);
            }
        }
    }

    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        onShowCustomView(view, callback);
    }

    public void onHideCustomView() {
        if (this.f) {
            this.c.setVisibility(4);
            this.c.removeView(this.g);
            this.b.setVisibility(0);
            if (!(this.h == null || this.h.getClass().getName().contains(".chromium."))) {
                this.h.onCustomViewHidden();
            }
            this.f = false;
            this.g = null;
            this.h = null;
            if (this.i != null) {
                this.i.a(false);
            }
        }
    }
}
