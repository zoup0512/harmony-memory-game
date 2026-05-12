package io.branch.referral;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.LinkProperties;
import org.json.JSONObject;

class BranchUniversalReferralInitWrapper implements Branch$BranchReferralInitListener {
    private final Branch$BranchUniversalReferralInitListener universalReferralInitListener_;

    public BranchUniversalReferralInitWrapper(Branch$BranchUniversalReferralInitListener universalReferralInitListener) {
        this.universalReferralInitListener_ = universalReferralInitListener;
    }

    public void onInitFinished(JSONObject referringParams, BranchError error) {
        if (this.universalReferralInitListener_ == null) {
            return;
        }
        if (error != null) {
            this.universalReferralInitListener_.onInitFinished(null, null, error);
            return;
        }
        this.universalReferralInitListener_.onInitFinished(BranchUniversalObject.getReferredBranchUniversalObject(), LinkProperties.getReferredLinkProperties(), error);
    }
}
