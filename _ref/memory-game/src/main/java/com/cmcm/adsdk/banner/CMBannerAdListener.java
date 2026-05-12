package com.cmcm.adsdk.banner;

public interface CMBannerAdListener {
    void adFailedToLoad(CMAdView cMAdView, int i);

    void onAdClicked(CMAdView cMAdView);

    void onAdLoaded(CMAdView cMAdView);
}
