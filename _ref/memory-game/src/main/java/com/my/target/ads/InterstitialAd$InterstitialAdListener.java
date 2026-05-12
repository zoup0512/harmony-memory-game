package com.my.target.ads;

public interface InterstitialAd$InterstitialAdListener {
    void onClick(InterstitialAd interstitialAd);

    void onDismiss(InterstitialAd interstitialAd);

    void onDisplay(InterstitialAd interstitialAd);

    void onLoad(InterstitialAd interstitialAd);

    void onNoAd(String str, InterstitialAd interstitialAd);

    void onVideoCompleted(InterstitialAd interstitialAd);
}
