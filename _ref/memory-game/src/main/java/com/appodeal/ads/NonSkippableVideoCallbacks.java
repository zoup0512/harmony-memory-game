package com.appodeal.ads;

public interface NonSkippableVideoCallbacks {
    void onNonSkippableVideoClosed(boolean z);

    void onNonSkippableVideoFailedToLoad();

    void onNonSkippableVideoFinished();

    void onNonSkippableVideoLoaded();

    void onNonSkippableVideoShown();
}
