package com.appodeal.ads.g;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.aj;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.ap;
import com.unity3d.ads2.IUnityAdsListener;
import com.unity3d.ads2.UnityAds.FinishState;
import com.unity3d.ads2.UnityAds.UnityAdsError;

class x implements IUnityAdsListener {
    private final ap a;
    private final int b;

    x(ap apVar, int i) {
        this.a = apVar;
        this.b = i;
    }

    public void onUnityAdsReady(String str) {
        w.b = a.AVAILABLE;
    }

    public void onUnityAdsStart(String str) {
        aj.a(this.b, this.a);
    }

    public void onUnityAdsFinish(String str, FinishState finishState) {
        if (finishState == FinishState.COMPLETED) {
            aj.b(this.b, this.a);
        }
        aj.d(this.b, this.a);
    }

    public void onUnityAdsError(UnityAdsError unityAdsError, String str) {
        Appodeal.a(str);
    }
}
