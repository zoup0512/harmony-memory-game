package com.appodeal.ads;

import java.util.List;

public interface NativeCallbacks {
    void onNativeClicked(NativeAd nativeAd);

    void onNativeFailedToLoad();

    void onNativeLoaded(List<NativeAd> list);

    void onNativeShown(NativeAd nativeAd);
}
