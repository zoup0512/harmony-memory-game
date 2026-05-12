package com.cmcm.baseapi.ads;

import java.util.List;

public interface INativeAdLoader {
    INativeAd getAd();

    List<INativeAd> getAdList(int i);

    void loadAd();

    void setAdListener(INativeAdLoaderListener iNativeAdLoaderListener);
}
