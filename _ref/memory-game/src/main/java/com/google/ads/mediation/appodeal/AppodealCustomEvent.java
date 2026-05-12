package com.google.ads.mediation.appodeal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;

public class AppodealCustomEvent implements CustomEventBanner, CustomEventInterstitial {
    private Activity activity;

    private class AppodealBannerListener implements BannerCallbacks {
        private final CustomEventBannerListener bannerListener;

        public AppodealBannerListener(CustomEventBannerListener customEventBannerListener) {
            this.bannerListener = customEventBannerListener;
        }

        public void onBannerLoaded(int i, boolean z) {
            this.bannerListener.onAdLoaded(Appodeal.getBannerView(AppodealCustomEvent.this.activity));
        }

        public void onBannerFailedToLoad() {
            this.bannerListener.onAdFailedToLoad(1);
        }

        public void onBannerShown() {
            this.bannerListener.onAdOpened();
        }

        public void onBannerClicked() {
            this.bannerListener.onAdLeftApplication();
        }
    }

    private class AppodealInterstitialListener implements InterstitialCallbacks {
        private final CustomEventInterstitialListener interstitialListener;

        public AppodealInterstitialListener(CustomEventInterstitialListener customEventInterstitialListener) {
            this.interstitialListener = customEventInterstitialListener;
        }

        public void onInterstitialClosed() {
            this.interstitialListener.onAdClosed();
        }

        public void onInterstitialClicked() {
            this.interstitialListener.onAdLeftApplication();
        }

        public void onInterstitialFailedToLoad() {
        }

        public void onInterstitialLoaded(boolean z) {
        }

        public void onInterstitialShown() {
            this.interstitialListener.onAdOpened();
        }
    }

    public void requestBannerAd(Context context, CustomEventBannerListener customEventBannerListener, String str, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle) {
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
        if (this.activity != null) {
            Appodeal.initialize(this.activity, str, 4);
            Appodeal.setBannerCallbacks(new AppodealBannerListener(customEventBannerListener));
            Appodeal.getBannerView(this.activity);
            Appodeal.show(this.activity, 64);
        }
    }

    public void requestInterstitialAd(Context context, final CustomEventInterstitialListener customEventInterstitialListener, String str, MediationAdRequest mediationAdRequest, Bundle bundle) {
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
        if (this.activity != null) {
            Appodeal.initialize(this.activity, str, 1);
            Appodeal.setInterstitialCallbacks(new AppodealInterstitialListener(customEventInterstitialListener));
            if (Appodeal.isLoaded(1)) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        customEventInterstitialListener.onAdLoaded();
                    }
                });
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        if (Appodeal.isLoaded(1)) {
                            customEventInterstitialListener.onAdLoaded();
                        } else {
                            customEventInterstitialListener.onAdFailedToLoad(3);
                        }
                    }
                }, 4000);
            }
        }
    }

    public void showInterstitial() {
        if (this.activity != null) {
            Appodeal.show(this.activity, 1);
        }
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
