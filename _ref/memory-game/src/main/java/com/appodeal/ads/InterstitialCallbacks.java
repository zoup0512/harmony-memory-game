package com.appodeal.ads;

public interface InterstitialCallbacks {
    void onInterstitialClicked();

    void onInterstitialClosed();

    void onInterstitialFailedToLoad();

    void onInterstitialLoaded(boolean z);

    void onInterstitialShown();
}
