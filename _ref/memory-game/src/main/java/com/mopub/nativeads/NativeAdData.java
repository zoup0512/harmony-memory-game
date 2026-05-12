package com.mopub.nativeads;

import android.support.annotation.NonNull;

class NativeAdData {
    @NonNull
    private final MoPubAdRenderer adRenderer;
    @NonNull
    private final NativeAd adResponse;
    @NonNull
    private final String adUnitId;

    NativeAdData(@NonNull String str, @NonNull MoPubAdRenderer moPubAdRenderer, @NonNull NativeAd nativeAd) {
        this.adUnitId = str;
        this.adRenderer = moPubAdRenderer;
        this.adResponse = nativeAd;
    }

    @NonNull
    String getAdUnitId() {
        return this.adUnitId;
    }

    @NonNull
    MoPubAdRenderer getAdRenderer() {
        return this.adRenderer;
    }

    @NonNull
    NativeAd getAd() {
        return this.adResponse;
    }
}
