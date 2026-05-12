package com.cmcm.adsdk.interstitial;

import android.content.Context;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.nativead.NativeAdManagerInternal;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAdLoader;
import com.cmcm.utils.g;

public class InterstitialRequestInternal extends NativeAdManagerInternal {
    private INativeAd mCachedAd;

    public InterstitialRequestInternal(Context context, String posId) {
        super(context, posId);
    }

    public void loadAd() {
        g.a(TAG, this.mPositionId + " loadAd");
        if (this.mCachedAd == null || this.mCachedAd.hasExpired()) {
            this.mIsOpenPriority = false;
            this.mIsPreload = true;
            this.mOptimizeEnabled = false;
            super.loadAd();
            return;
        }
        notifyAdLoaded();
    }

    protected int getLoadAdTypeSize() {
        return 1;
    }

    public void adLoaded(String adTypeName) {
        INativeAdLoader adLoader = this.mLoaderMap.getAdLoader(adTypeName);
        if (adLoader != null) {
            INativeAd ad = adLoader.getAd();
            if (!(ad == null || ad.getAdObject() == null)) {
                this.mCachedAd = ad;
            }
        }
        super.adLoaded(adTypeName);
    }

    protected void checkIfAllfinished() {
        g.a(Const.TAG, "check finish");
        if (this.mIsFinished) {
            g.c(Const.TAG, "already finished");
        } else if (this.mCachedAd != null) {
            notifyAdLoaded();
        } else if (isAllLoaderFinished()) {
            notifyAdFailed(CMAdError.NO_FILL_ERROR);
        }
    }

    public void showAd() {
        if (this.mCachedAd != null) {
            this.mCachedAd.registerViewForInteraction(null);
            this.mCachedAd = null;
        }
    }
}
