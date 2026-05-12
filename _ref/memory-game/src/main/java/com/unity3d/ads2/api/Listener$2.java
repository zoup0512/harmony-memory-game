package com.unity3d.ads2.api;

import com.unity3d.ads2.UnityAds;

class Listener$2 implements Runnable {
    final /* synthetic */ String val$placementId;

    Listener$2(String str) {
        this.val$placementId = str;
    }

    public void run() {
        UnityAds.getListener().onUnityAdsStart(this.val$placementId);
    }
}
