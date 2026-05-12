package com.unity3d.ads2.api;

import com.unity3d.ads2.UnityAds;
import com.unity3d.ads2.UnityAds.FinishState;

class Listener$3 implements Runnable {
    final /* synthetic */ String val$placementId;
    final /* synthetic */ String val$result;

    Listener$3(String str, String str2) {
        this.val$placementId = str;
        this.val$result = str2;
    }

    public void run() {
        UnityAds.getListener().onUnityAdsFinish(this.val$placementId, FinishState.valueOf(this.val$result));
    }
}
