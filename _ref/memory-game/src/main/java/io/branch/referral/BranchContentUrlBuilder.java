package io.branch.referral;

import android.content.Context;

public class BranchContentUrlBuilder extends BranchUrlBuilder<BranchContentUrlBuilder> {
    public BranchContentUrlBuilder(Context context, String channel) {
        super(context);
        this.channel_ = channel;
        this.type_ = 0;
        this.feature_ = "share";
    }

    public String getContentUrl() {
        return getUrl();
    }

    public void generateContentUrl(Branch$BranchLinkCreateListener callback) {
        super.generateUrl(callback);
    }
}
