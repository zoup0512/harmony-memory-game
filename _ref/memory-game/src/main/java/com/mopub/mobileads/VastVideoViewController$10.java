package com.mopub.mobileads;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class VastVideoViewController$10 extends WebViewClient {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ VastCompanionAdConfig val$vastCompanionAdConfig;

    VastVideoViewController$10(VastVideoViewController vastVideoViewController, VastCompanionAdConfig vastCompanionAdConfig, Context context) {
        this.this$0 = vastVideoViewController;
        this.val$vastCompanionAdConfig = vastCompanionAdConfig;
        this.val$context = context;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        this.val$vastCompanionAdConfig.handleClick(this.val$context, 1, str, VastVideoViewController.access$400(this.this$0).getDspCreativeId());
        return true;
    }
}
