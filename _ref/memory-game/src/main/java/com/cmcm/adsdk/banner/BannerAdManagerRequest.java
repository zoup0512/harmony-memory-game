package com.cmcm.adsdk.banner;

import android.content.Context;
import android.view.View;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.CMRequestParams;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.nativead.NativeAdManagerInternal;
import com.cmcm.adsdk.utils.Assure;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAdLoader;
import com.cmcm.utils.g;

public class BannerAdManagerRequest extends NativeAdManagerInternal {
    private INativeAd mSrcNativeAd;

    public Object getAdObject() {
        if (this.mSrcNativeAd != null) {
            return this.mSrcNativeAd.getAdObject();
        }
        return null;
    }

    public BannerAdManagerRequest(Context context, String posId, CMBannerAdSize adSize) {
        super(context, posId);
        CMRequestParams cMBannerParams = new CMBannerParams();
        cMBannerParams.setBannerViewSize(adSize);
        setRequestParams(cMBannerParams);
    }

    protected int getLoadAdTypeSize() {
        return 1;
    }

    public void loadAd() {
        g.a(TAG, this.mPositionId + " loadAd");
        this.mIsOpenPriority = false;
        this.mIsPreload = true;
        this.mOptimizeEnabled = false;
        this.mSrcNativeAd = null;
        super.loadAd();
    }

    public void adLoaded(String adTypeName) {
        g.a(Const.TAG, "banner loaded type = " + adTypeName);
        INativeAdLoader adLoader = this.mLoaderMap.getAdLoader(adTypeName);
        if (adLoader != null) {
            INativeAd ad = adLoader.getAd();
            if (!(ad == null || ad.getAdObject() == null || !(ad.getAdObject() instanceof View))) {
                if (this.mSrcNativeAd == null) {
                    this.mSrcNativeAd = ad;
                } else {
                    g.a(Const.TAG, "view callbacked," + adTypeName + " to unregister view");
                    ad.unregisterView();
                }
            }
        }
        super.adLoaded(adTypeName);
    }

    protected void checkIfAllfinished() {
        g.a(Const.TAG, "check finish");
        Assure.checkRunningOnUIThread();
        if (this.mIsFinished) {
            g.c(Const.TAG, "already finished");
        } else if (this.mSrcNativeAd != null) {
            notifyAdLoaded();
        } else if (isAllLoaderFinished()) {
            notifyAdFailed(CMAdError.NO_FILL_ERROR);
        }
    }

    public void prepare(View view) {
        if (this.mSrcNativeAd != null && view != null) {
            this.mSrcNativeAd.registerViewForInteraction(view);
        }
    }

    public void destroy() {
        if (this.mSrcNativeAd != null) {
            g.c(Const.TAG, "banner unregister view");
            this.mSrcNativeAd.unregisterView();
        }
    }
}
