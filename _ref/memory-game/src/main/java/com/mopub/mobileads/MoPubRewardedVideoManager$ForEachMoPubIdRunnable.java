package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;

abstract class MoPubRewardedVideoManager$ForEachMoPubIdRunnable implements Runnable {
    @NonNull
    private final Class<? extends CustomEventRewardedVideo> mCustomEventClass;
    @NonNull
    private final String mThirdPartyId;

    protected abstract void forEach(@NonNull String str);

    MoPubRewardedVideoManager$ForEachMoPubIdRunnable(@NonNull Class<? extends CustomEventRewardedVideo> cls, @NonNull String str) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(str);
        this.mCustomEventClass = cls;
        this.mThirdPartyId = str;
    }

    public void run() {
        for (String forEach : MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getMoPubIdsForAdNetwork(this.mCustomEventClass, this.mThirdPartyId)) {
            forEach(forEach);
        }
    }
}
