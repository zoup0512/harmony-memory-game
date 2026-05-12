package com.cmcm.baseapi.ads;

public interface INativeAdLoaderListener {
    void adClicked(INativeAd iNativeAd);

    void adFailedToLoad(int i);

    void adLoaded();
}
