package com.mopub.mobileads;

class MoPubRewardedVideoManager$5 implements Runnable {
    final /* synthetic */ String val$currentAdUnitId;

    MoPubRewardedVideoManager$5(String str) {
        this.val$currentAdUnitId = str;
    }

    public void run() {
        MoPubRewardedVideoManager.access$600(this.val$currentAdUnitId);
    }
}
