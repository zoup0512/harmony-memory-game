package com.appodeal.ads;

public interface MrecCallbacks {
    void onMrecClicked();

    void onMrecFailedToLoad();

    void onMrecLoaded(boolean z);

    void onMrecShown();
}
