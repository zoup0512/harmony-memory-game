package org.nexage.sourcekit.vast.activity;

import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.activity.VPAIDActivity.WebAppInterface;

class VPAIDActivity$WebAppInterface$1 implements Runnable {
    final /* synthetic */ WebAppInterface this$1;

    VPAIDActivity$WebAppInterface$1(WebAppInterface webAppInterface) {
        this.this$1 = webAppInterface;
    }

    public void run() {
        VASTLog.d(VPAIDActivity.TAG, "finish");
        this.this$1.this$0.finishVPAID();
    }
}
