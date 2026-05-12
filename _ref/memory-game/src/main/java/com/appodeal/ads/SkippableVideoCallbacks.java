package com.appodeal.ads;

public interface SkippableVideoCallbacks {
    void onSkippableVideoClosed(boolean z);

    void onSkippableVideoFailedToLoad();

    void onSkippableVideoFinished();

    void onSkippableVideoLoaded();

    void onSkippableVideoShown();
}
