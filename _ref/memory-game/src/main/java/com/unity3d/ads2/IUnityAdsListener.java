package com.unity3d.ads2;

import com.unity3d.ads2.UnityAds.FinishState;
import com.unity3d.ads2.UnityAds.UnityAdsError;

public interface IUnityAdsListener {
    void onUnityAdsError(UnityAdsError unityAdsError, String str);

    void onUnityAdsFinish(String str, FinishState finishState);

    void onUnityAdsReady(String str);

    void onUnityAdsStart(String str);
}
