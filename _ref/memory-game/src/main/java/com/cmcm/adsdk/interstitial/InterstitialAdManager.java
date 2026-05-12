package com.cmcm.adsdk.interstitial;

import android.content.Context;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;

public class InterstitialAdManager implements INativeAdLoaderListener {
    private static boolean mIsReport = true;
    private static InterstitialAdCallBack sCallBack;
    private InterstitialRequestInternal interstitialRequest;
    private Context mContext;
    private String posId;

    public InterstitialAdManager(Context context, String posId) {
        this.mContext = context;
        this.posId = posId;
    }

    public void loadAd() {
        if (this.interstitialRequest == null) {
            this.interstitialRequest = new InterstitialRequestInternal(this.mContext, this.posId);
        }
        this.interstitialRequest.setAdListener(this);
        this.interstitialRequest.loadAd();
    }

    public void showAd() {
        if (this.interstitialRequest != null) {
            this.interstitialRequest.showAd();
        }
    }

    public void setInterstitialCallBack(InterstitialAdCallBack callBack) {
        sCallBack = callBack;
        PicksInterstitialActivity.setInterstitialAdCallBack(callBack);
    }

    public static InterstitialAdCallBack getInterstitialCallBack() {
        return sCallBack;
    }

    public void adLoaded() {
        if (sCallBack != null) {
            sCallBack.onAdLoaded();
        }
    }

    public void adFailedToLoad(int errorcode) {
        if (sCallBack != null) {
            sCallBack.onAdLoadFailed(errorcode);
        }
    }

    public void adClicked(INativeAd nativeAd) {
        if (sCallBack != null) {
            sCallBack.onAdClicked();
        }
    }

    public void setInterstialOverClickEnable(boolean enable) {
        PicksInterstitialActivity.setOverClickEnable(enable);
    }

    public void destroy() {
        if (this.interstitialRequest != null) {
            this.interstitialRequest = null;
            sCallBack = null;
        }
    }

    public void setOfferReport(boolean isReport) {
        mIsReport = isReport;
    }

    public static boolean getOfferReport() {
        return mIsReport;
    }
}
