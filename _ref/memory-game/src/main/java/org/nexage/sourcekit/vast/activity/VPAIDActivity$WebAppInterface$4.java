package org.nexage.sourcekit.vast.activity;

import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.activity.VPAIDActivity.WebAppInterface;

class VPAIDActivity$WebAppInterface$4 implements Runnable {
    final /* synthetic */ WebAppInterface this$1;

    VPAIDActivity$WebAppInterface$4(WebAppInterface webAppInterface) {
        this.this$1 = webAppInterface;
    }

    public void run() {
        VASTLog.d(VPAIDActivity.TAG, "adsManager loaded");
        this.this$1.this$0.mWebView.loadUrl("javascript:startAd()");
    }
}
