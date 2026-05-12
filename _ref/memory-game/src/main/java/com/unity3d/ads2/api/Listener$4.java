package com.unity3d.ads2.api;

import com.unity3d.ads2.UnityAds;
import com.unity3d.ads2.UnityAds.UnityAdsError;

class Listener$4 implements Runnable {
    final /* synthetic */ String val$error;
    final /* synthetic */ String val$message;

    Listener$4(String str, String str2) {
        this.val$error = str;
        this.val$message = str2;
    }

    public void run() {
        UnityAds.getListener().onUnityAdsError(UnityAdsError.valueOf(this.val$error), this.val$message);
    }
}
