package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public class AppodealCustomEventInterstitial extends CustomEventInterstitial implements InterstitialCallbacks {
    private static final String API_KEY = "appKey";
    private Activity activity;
    private CustomEventInterstitialListener mInterstitialListener;

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        this.mInterstitialListener = customEventInterstitialListener;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
        if (this.activity == null) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.UNSPECIFIED);
        } else if (extrasAreValid(map2)) {
            String str = (String) map2.get(API_KEY);
            Appodeal.setInterstitialCallbacks(this);
            Appodeal.setAutoCache(1, false);
            Appodeal.initialize(this.activity, str, 1);
            Appodeal.cache(this.activity, 1);
        } else {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    protected void onInvalidate() {
    }

    protected void showInterstitial() {
        if (this.activity != null) {
            Appodeal.show(this.activity, 1);
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(API_KEY);
    }

    public void onInterstitialClicked() {
        this.mInterstitialListener.onInterstitialClicked();
    }

    public void onInterstitialClosed() {
        this.mInterstitialListener.onInterstitialDismissed();
    }

    public void onInterstitialFailedToLoad() {
        this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
    }

    public void onInterstitialShown() {
        this.mInterstitialListener.onInterstitialShown();
    }

    public void onInterstitialLoaded(boolean z) {
        this.mInterstitialListener.onInterstitialLoaded();
    }
}
