package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.mopub.common.util.Views;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import java.util.Map;

public class AppodealCustomEventBanner extends CustomEventBanner implements BannerCallbacks {
    private static final String APP_ID_KEY = "appKey";
    private Activity activity;
    private boolean appodealInitialized = false;
    private CustomEventBannerListener mBannerListener;

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        String str = null;
        this.mBannerListener = customEventBannerListener;
        this.activity = null;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
        if (extrasAreValid(map2)) {
            str = (String) map2.get(APP_ID_KEY);
        }
        if (this.activity == null || str == null || str.equals("")) {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.UNSPECIFIED);
            return;
        }
        if (!this.appodealInitialized) {
            Appodeal.setAutoCache(4, false);
            Appodeal.initialize(this.activity, str, 4);
            this.appodealInitialized = true;
        }
        Appodeal.setBannerCallbacks(this);
        Appodeal.cache(this.activity, 64);
        Appodeal.show(this.activity, 64);
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(APP_ID_KEY);
    }

    protected void onInvalidate() {
        if (Appodeal.getBannerView(this.activity) != null) {
            Appodeal.setBannerCallbacks(null);
            Views.removeFromParent(Appodeal.getBannerView(this.activity));
        }
    }

    public void onBannerClicked() {
        this.mBannerListener.onBannerClicked();
    }

    public void onBannerFailedToLoad() {
        this.mBannerListener.onBannerFailed(MoPubErrorCode.NO_FILL);
    }

    public void onBannerLoaded(int i, boolean z) {
        this.mBannerListener.onBannerLoaded(Appodeal.getBannerView(this.activity));
    }

    public void onBannerShown() {
        this.mBannerListener.onBannerExpanded();
    }
}
