package com.mopub.mobileads;

import com.mopub.common.MoPubReward;
import java.util.HashSet;
import java.util.Set;

class MoPubRewardedVideoManager$12 implements Runnable {
    final /* synthetic */ Class val$customEventClass;
    final /* synthetic */ MoPubReward val$moPubReward;
    final /* synthetic */ String val$thirdPartyId;

    MoPubRewardedVideoManager$12(Class cls, MoPubReward moPubReward, String str) {
        this.val$customEventClass = cls;
        this.val$moPubReward = moPubReward;
        this.val$thirdPartyId = str;
    }

    public void run() {
        MoPubReward chooseReward = MoPubRewardedVideoManager.chooseReward(MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getLastShownMoPubReward(this.val$customEventClass), this.val$moPubReward);
        Set hashSet = new HashSet(MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getMoPubIdsForAdNetwork(this.val$customEventClass, this.val$thirdPartyId));
        if (MoPubRewardedVideoManager.access$400(MoPubRewardedVideoManager.access$200()) != null) {
            MoPubRewardedVideoManager.access$400(MoPubRewardedVideoManager.access$200()).onRewardedVideoCompleted(hashSet, chooseReward);
        }
    }
}
