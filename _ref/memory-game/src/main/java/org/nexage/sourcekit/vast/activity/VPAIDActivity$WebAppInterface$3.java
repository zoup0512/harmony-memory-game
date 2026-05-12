package org.nexage.sourcekit.vast.activity;

import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.activity.VPAIDActivity.WebAppInterface;

class VPAIDActivity$WebAppInterface$3 implements Runnable {
    final /* synthetic */ WebAppInterface this$1;

    VPAIDActivity$WebAppInterface$3(WebAppInterface webAppInterface) {
        this.this$1 = webAppInterface;
    }

    public void run() {
        VASTLog.d(VPAIDActivity.TAG, "ad started");
        this.this$1.this$0.videoStarted = true;
        this.this$1.this$0.hideProgressBar();
    }
}
