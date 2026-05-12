package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$6 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    final /* synthetic */ MoPubErrorCode val$errorCode;

    MoPubRewardedVideoManager$6(Class cls, String str, MoPubErrorCode moPubErrorCode) {
        this.val$errorCode = moPubErrorCode;
        super(cls, str);
    }

    protected void forEach(@NonNull String str) {
        MoPubRewardedVideoManager.access$700(str, this.val$errorCode);
    }
}
