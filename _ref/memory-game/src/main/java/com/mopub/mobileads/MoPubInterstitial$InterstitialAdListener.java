package com.mopub.mobileads;

public interface MoPubInterstitial$InterstitialAdListener {
    void onInterstitialClicked(MoPubInterstitial moPubInterstitial);

    void onInterstitialDismissed(MoPubInterstitial moPubInterstitial);

    void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode);

    void onInterstitialFinished(MoPubInterstitial moPubInterstitial);

    void onInterstitialLoaded(MoPubInterstitial moPubInterstitial);

    void onInterstitialShown(MoPubInterstitial moPubInterstitial);
}
