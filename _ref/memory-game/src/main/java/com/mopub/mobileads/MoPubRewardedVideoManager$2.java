package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$2 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    MoPubRewardedVideoManager$2(Class cls, String str) {
        super(cls, str);
    }

    protected void forEach(@NonNull String str) {
        MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200(), str);
        if (MoPubRewardedVideoManager.access$400(MoPubRewardedVideoManager.access$200()) != null) {
            MoPubRewardedVideoManager.access$400(MoPubRewardedVideoManager.access$200()).onRewardedVideoLoadSuccess(str);
        }
    }
}
