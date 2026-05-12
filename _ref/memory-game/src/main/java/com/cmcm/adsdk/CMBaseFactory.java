package com.cmcm.adsdk;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public abstract class CMBaseFactory {
    public final Map<String, String> mNativeAdLoaderClassMap = new HashMap();

    public abstract void clearVastCache(Context context);

    public abstract Object createAdLoader(Context context, Object obj);

    public abstract void initConfig();

    public CMBaseFactory() {
        this.mNativeAdLoaderClassMap.put(Const.KEY_CM, "com.cmcm.adsdk.adapter.PicksNativeAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_CM_BANNER, "com.cmcm.adsdk.adapter.PicksBannerAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_MP_BANNER, "com.cmcm.adsdk.adapter.MopubBannerAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_CM_INTERSTITIAL, "com.cmcm.adsdk.adapter.PicksInterstatialAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_FB_INTERSTITIAL, "com.cmcm.adsdk.adapter.FacebookInterstitialAdapter");
    }

    public boolean addLoaderClass(String loaderKey, String loaderClass) {
        if (this.mNativeAdLoaderClassMap.containsKey(loaderKey)) {
            return false;
        }
        this.mNativeAdLoaderClassMap.put(loaderKey, loaderClass);
        return true;
    }
}
