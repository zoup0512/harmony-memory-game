package com.mopub.mobileads;

class MoPubRewardedVideoManager$9 implements Runnable {
    final /* synthetic */ String val$currentAdUnitId;

    MoPubRewardedVideoManager$9(String str) {
        this.val$currentAdUnitId = str;
    }

    public void run() {
        MoPubRewardedVideoManager.access$800(this.val$currentAdUnitId);
    }
}
