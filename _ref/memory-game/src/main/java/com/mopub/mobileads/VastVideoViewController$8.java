package com.mopub.mobileads;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class VastVideoViewController$8 extends WebViewClient {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ VastIconConfig val$vastIconConfig;

    VastVideoViewController$8(VastVideoViewController vastVideoViewController, VastIconConfig vastIconConfig) {
        this.this$0 = vastVideoViewController;
        this.val$vastIconConfig = vastIconConfig;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        this.val$vastIconConfig.handleClick(this.this$0.getContext(), str, VastVideoViewController.access$400(this.this$0).getDspCreativeId());
        return true;
    }
}
