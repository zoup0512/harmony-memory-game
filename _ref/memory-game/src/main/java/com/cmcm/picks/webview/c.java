package com.cmcm.picks.webview;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* compiled from: WebViews */
public class c {
    @TargetApi(11)
    public static void a(@NonNull WebView webView) {
        if (VERSION.SDK_INT >= 11) {
            webView.onResume();
            return;
        }
        try {
            b.a(webView, "onResume");
        } catch (Exception e) {
        }
    }

    @TargetApi(11)
    public static void a(@NonNull WebView webView, boolean z) {
        if (z) {
            webView.stopLoading();
            webView.loadUrl("");
        }
        if (VERSION.SDK_INT >= 11) {
            webView.onPause();
            return;
        }
        try {
            b.a(webView, "onPause");
        } catch (Exception e) {
        }
    }

    public static void b(WebView webView) {
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return true;
            }

            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return true;
            }

            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                result.confirm();
                return true;
            }

            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return true;
            }
        });
    }
}
