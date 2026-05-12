package com.cmcm.adsdk.nativead;

import android.app.Activity;
import android.content.Context;
import com.cmcm.adsdk.CMRequestParams;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import com.cmcm.utils.ThreadHelper;
import java.util.List;
import java.util.concurrent.Callable;

public class NativeAdManager {
    private Context mContext;
    NativeAdManagerInternal requestAd = null;
    public CMRequestParams requestParams;

    public NativeAdManager(Context context, String posid) {
        if (context instanceof Activity) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.requestAd = new NativeAdManagerInternal(this.mContext, posid);
    }

    public void setRequestParams(CMRequestParams params) {
        this.requestParams = params;
    }

    public void setNativeAdListener(INativeAdLoaderListener listener) {
        if (this.requestAd != null) {
            this.requestAd.setAdListener(listener);
        }
    }

    public void preloadAd() {
        requestAd(true);
    }

    public void loadAd() {
        requestAd(false);
    }

    protected void requestAd(boolean isPreload) {
        if (this.requestParams != null) {
            this.requestAd.setRequestParams(this.requestParams);
        }
        this.requestAd.setPreload(isPreload);
        this.requestAd.loadAd();
    }

    public INativeAd getAd() {
        return (INativeAd) ThreadHelper.runOnUiThreadBlockingNoException(new Callable<INativeAd>() {
            public INativeAd call() throws Exception {
                if (NativeAdManager.this.requestAd != null) {
                    return NativeAdManager.this.requestAd.getAd();
                }
                return null;
            }
        });
    }

    public List<PosBean> getPosBeans() {
        return this.requestAd.getPosBeans();
    }

    public String getRequestLastError() {
        if (this.requestAd != null) {
            return this.requestAd.mRequestLogger.getLastResult();
        }
        return null;
    }

    public String getRequestErrorInfo() {
        if (this.requestAd != null) {
            return this.requestAd.mRequestLogger.getRequestErrorInfo();
        }
        return null;
    }

    public void setOpenPriority(boolean openPriority) {
        this.requestAd.setOpenPriority(openPriority);
    }
}
