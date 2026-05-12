package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.mopub.common.AdType;

@SuppressLint({"ViewConstructor"})
public class q extends m {
    private final WebView a;
    private OnClickListener b = null;

    public q(t tVar, Context context) {
        super(context);
        this.a = new WebView(context);
        addView(this.a, new LayoutParams(-1, -1));
        this.a.setBackgroundColor(0);
        this.a.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return false;
                }
                if (url.contains("chartboost") && url.contains("click") && this.a.b != null) {
                    this.a.b.onClick(this.a);
                }
                return true;
            }
        });
    }

    public void setOnClickListener(OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.b = clickListener;
    }

    public void a(a aVar, int i) {
        String e = aVar.e(AdType.HTML);
        if (e != null) {
            try {
                this.a.loadDataWithBaseURL("file:///android_res/", e, "text/html", "UTF-8", null);
            } catch (Exception e2) {
                CBLogging.b("AppCellWebView", "Exception raised loading data into webview", e2);
                com.chartboost.sdk.Tracking.a.a(getClass(), "prepareWithCellMeta", e2);
            }
        }
    }

    public int a() {
        return CBUtility.a(100, getContext());
    }
}
