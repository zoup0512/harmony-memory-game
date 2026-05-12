package com.cmcm.adsdk.interstitial;

public interface InterstitialAdCallBack {
    void onAdClicked();

    void onAdDismissed();

    void onAdDisplayed();

    void onAdLoadFailed(int i);

    void onAdLoaded();
}
