package com.cmcm.picks.webview;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cmcm.adsdk.R;

/* compiled from: BrowserWebViewClient */
class a extends WebViewClient {
    @NonNull
    private PicksBrowser a;

    public a(@NonNull PicksBrowser picksBrowser) {
        this.a = picksBrowser;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        this.a.c().loadUrl(url);
        return true;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.a.b().setImageDrawable(this.a.getResources().getDrawable(R.drawable.browser_unright_arrow));
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.a.a().setImageDrawable(view.canGoBack() ? this.a.getResources().getDrawable(R.drawable.browser_left_arrow) : this.a.getResources().getDrawable(R.drawable.browser_unleft_arrow));
        this.a.b().setImageDrawable(view.canGoForward() ? this.a.getResources().getDrawable(R.drawable.browser_right_arrow) : this.a.getResources().getDrawable(R.drawable.browser_unright_arrow));
    }
}
