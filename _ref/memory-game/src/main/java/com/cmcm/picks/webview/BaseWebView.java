package com.cmcm.picks.webview;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

public class BaseWebView extends WebView {
    private static boolean b = false;
    protected boolean a;

    public BaseWebView(Context context) {
        super(context.getApplicationContext());
        a();
        c.b(this);
        if (!b) {
            a(getContext());
            b = true;
        }
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
        a();
        c.b(this);
        if (!b) {
            a(getContext());
            b = true;
        }
    }

    protected void a() {
        if (VERSION.SDK_INT < 18) {
            getSettings().setPluginState(PluginState.OFF);
        }
    }

    public void destroy() {
        this.a = true;
        a((View) this);
        removeAllViews();
        super.destroy();
    }

    private void a(@NonNull Context context) {
        if (VERSION.SDK_INT == 19) {
            View webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL(null, "", "text/html", "UTF-8", null);
            LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.type = 2005;
            layoutParams.flags = 16777240;
            layoutParams.format = -2;
            layoutParams.gravity = 8388659;
            ((WindowManager) context.getSystemService("window")).addView(webView, layoutParams);
        }
    }

    public void a(View view) {
        if (view != null && view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
