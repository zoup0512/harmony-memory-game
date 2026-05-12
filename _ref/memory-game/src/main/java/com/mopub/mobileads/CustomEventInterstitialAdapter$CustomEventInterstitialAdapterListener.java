package com.mopub.mobileads;

interface CustomEventInterstitialAdapter$CustomEventInterstitialAdapterListener {
    void onCustomEventInterstitialClicked();

    void onCustomEventInterstitialDismissed();

    void onCustomEventInterstitialFailed(MoPubErrorCode moPubErrorCode);

    void onCustomEventInterstitialFinished();

    void onCustomEventInterstitialLoaded();

    void onCustomEventInterstitialShown();
}
