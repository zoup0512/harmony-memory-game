package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$3 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    final /* synthetic */ MoPubErrorCode val$errorCode;

    MoPubRewardedVideoManager$3(Class cls, String str, MoPubErrorCode moPubErrorCode) {
        this.val$errorCode = moPubErrorCode;
        super(cls, str);
    }

    protected void forEach(@NonNull String str) {
        MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200(), str);
        MoPubRewardedVideoManager.access$500(MoPubRewardedVideoManager.access$200(), str, this.val$errorCode);
    }
}
