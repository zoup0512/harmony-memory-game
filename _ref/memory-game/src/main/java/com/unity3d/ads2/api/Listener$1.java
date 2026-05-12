package com.unity3d.ads2.api;

import com.unity3d.ads2.UnityAds;

class Listener$1 implements Runnable {
    final /* synthetic */ String val$placementId;

    Listener$1(String str) {
        this.val$placementId = str;
    }

    public void run() {
        UnityAds.getListener().onUnityAdsReady(this.val$placementId);
    }
}
