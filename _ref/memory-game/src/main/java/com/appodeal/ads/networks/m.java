package com.appodeal.ads.networks;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appodeal.ads.Appodeal;

public class m {
    private final Activity a;
    private final a b;
    private final int c;
    private final int d;
    private final String e;
    private final String f;
    private WebView g;

    public interface a {
        void a(int i, int i2);

        void a(String str, int i, int i2);
    }

    class b {
        final /* synthetic */ m a;

        b(m mVar) {
            this.a = mVar;
        }

        @JavascriptInterface
        public void loadHTML(final String str) {
            this.a.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    try {
                        if (this.b.a.b == null) {
                            return;
                        }
                        if (str == null || str.isEmpty()) {
                            this.b.a.b.a(this.b.a.c, this.b.a.d);
                        } else {
                            this.b.a.b.a(str, this.b.a.c, this.b.a.d);
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                }
            });
        }
    }

    public m(Activity activity, a aVar, int i, int i2, String str, String str2) {
        this.a = activity;
        this.b = aVar;
        this.c = i;
        this.d = i2;
        this.e = str;
        this.f = str2;
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(this.a.e, this.a.f);
            }
        });
    }

    public void a(final String str, final String str2) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ m c;

            @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
            public void run() {
                this.c.g = new WebView(this.c.a);
                WebSettings settings = this.c.g.getSettings();
                settings.setDomStorageEnabled(true);
                settings.setJavaScriptEnabled(true);
                this.c.g.addJavascriptInterface(new b(this.c), "HTMLOUT");
                this.c.g.setWebViewClient(new WebViewClient(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void onPageFinished(WebView webView, String str) {
                        webView.loadUrl(str2);
                    }

                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        return true;
                    }

                    @TargetApi(24)
                    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                        return true;
                    }
                });
                this.c.g.loadData(str, "text/html", "utf-8");
            }
        });
    }
}
