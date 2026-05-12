package com.appodeal.ads.e;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.am;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.ap;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.ads.UnityAds.UnityAdsError;

class ab implements IUnityAdsListener {
    private final ap a;
    private final int b;

    ab(ap apVar, int i) {
        this.a = apVar;
        this.b = i;
    }

    public void onUnityAdsReady(String str) {
        aa.b = a.AVAILABLE;
    }

    public void onUnityAdsStart(String str) {
        am.a(this.b, this.a);
    }

    public void onUnityAdsFinish(String str, FinishState finishState) {
        if (finishState == FinishState.COMPLETED) {
            am.b(this.b, this.a);
        }
        am.d(this.b, this.a);
    }

    public void onUnityAdsError(UnityAdsError unityAdsError, String str) {
        Appodeal.a(str);
    }
}
