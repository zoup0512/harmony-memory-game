package com.mopub.mobileads;

class MoPubRewardedVideoManager$7 implements Runnable {
    final /* synthetic */ String val$currentAdUnitId;
    final /* synthetic */ MoPubErrorCode val$errorCode;

    MoPubRewardedVideoManager$7(String str, MoPubErrorCode moPubErrorCode) {
        this.val$currentAdUnitId = str;
        this.val$errorCode = moPubErrorCode;
    }

    public void run() {
        MoPubRewardedVideoManager.access$700(this.val$currentAdUnitId, this.val$errorCode);
    }
}
