package io.branch.referral;

import android.net.Uri;
import android.text.TextUtils;
import io.branch.referral.DeferredAppLinkDataHandler.AppLinkFetchEvents;
import io.branch.referral.Defines.Jsonkey;

class Branch$2 implements AppLinkFetchEvents {
    final /* synthetic */ Branch this$0;

    Branch$2(Branch this$0) {
        this.this$0 = this$0;
    }

    public void onAppLinkFetchFinished(String nativeAppLinkUrl) {
        Branch.access$200(this.this$0).setIsAppLinkTriggeredInit(Boolean.valueOf(true));
        if (nativeAppLinkUrl != null) {
            String bncLinkClickId = Uri.parse(nativeAppLinkUrl).getQueryParameter(Jsonkey.LinkClickID.getKey());
            if (!TextUtils.isEmpty(bncLinkClickId)) {
                Branch.access$200(this.this$0).setLinkClickIdentifier(bncLinkClickId);
            }
        }
        Branch.access$300(this.this$0).unlockProcessWait(PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        Branch.access$400(this.this$0);
    }
}
