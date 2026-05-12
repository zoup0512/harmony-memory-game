package com.appodeal.ads;

public interface BannerCallbacks {
    void onBannerClicked();

    void onBannerFailedToLoad();

    void onBannerLoaded(int i, boolean z);

    void onBannerShown();
}
