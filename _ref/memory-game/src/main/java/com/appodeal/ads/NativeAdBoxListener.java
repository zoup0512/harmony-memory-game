package com.appodeal.ads;

public interface NativeAdBoxListener {
    void onNativeAdBoxLoaded(int i);

    void onNativeClicked(NativeAd nativeAd);

    void onNativeShown(NativeAd nativeAd);
}
