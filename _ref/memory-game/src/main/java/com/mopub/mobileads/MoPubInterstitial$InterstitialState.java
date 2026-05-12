package com.mopub.mobileads;

enum MoPubInterstitial$InterstitialState {
    CUSTOM_EVENT_AD_READY,
    NOT_READY;

    boolean isReady() {
        return this != NOT_READY;
    }
}
