package com.appodeal.ads;

public interface RewardedVideoCallbacks {
    void onRewardedVideoClosed(boolean z);

    void onRewardedVideoFailedToLoad();

    void onRewardedVideoFinished(int i, String str);

    void onRewardedVideoLoaded();

    void onRewardedVideoShown();
}
