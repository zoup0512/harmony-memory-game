package com.mopub.common;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

class MoPubBrowser$1 extends WebChromeClient {
    final /* synthetic */ MoPubBrowser this$0;

    MoPubBrowser$1(MoPubBrowser moPubBrowser) {
        this.this$0 = moPubBrowser;
    }

    public void onProgressChanged(WebView webView, int i) {
        try {
            this.this$0.setTitle("Loading...");
            this.this$0.setProgress(i * 100);
            if (i == 100) {
                this.this$0.setTitle(webView.getUrl());
            }
        } catch (Exception e) {
        }
    }
}
