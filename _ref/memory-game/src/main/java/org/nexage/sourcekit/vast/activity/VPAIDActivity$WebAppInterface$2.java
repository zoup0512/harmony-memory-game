package org.nexage.sourcekit.vast.activity;

import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.activity.VPAIDActivity.WebAppInterface;

class VPAIDActivity$WebAppInterface$2 implements Runnable {
    final /* synthetic */ WebAppInterface this$1;

    VPAIDActivity$WebAppInterface$2(WebAppInterface webAppInterface) {
        this.this$1 = webAppInterface;
    }

    public void run() {
        VASTLog.d(VPAIDActivity.TAG, "close");
        this.this$1.this$0.closeVPAID();
    }
}
