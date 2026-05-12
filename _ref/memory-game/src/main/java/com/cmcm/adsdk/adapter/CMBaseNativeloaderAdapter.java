package com.cmcm.adsdk.adapter;

import android.content.Context;
import com.cmcm.adsdk.CMRequestParams;
import com.cmcm.adsdk.base.INativeReqeustCallBack;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAdLoader;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import java.util.Iterator;
import java.util.List;

public abstract class CMBaseNativeloaderAdapter implements INativeAdLoader {
    protected String mAdTypeName;
    protected Context mContext;
    protected IAdOnClickListener mNativeAdClickListener = null;
    protected INativeReqeustCallBack mNativeAdListener;
    public String mPositionId = null;
    protected CMRequestParams requestParams;

    public abstract void loadAds(int i);

    protected CMBaseNativeloaderAdapter(Context context, String posId, String adTypeName) {
        this.mContext = context;
        this.mPositionId = posId;
        this.mAdTypeName = adTypeName;
    }

    public String getAdTypeName() {
        return this.mAdTypeName;
    }

    public void setRequestParams(CMRequestParams requestParams) {
        this.requestParams = requestParams;
    }

    public CMRequestParams getRequestParams() {
        return this.requestParams;
    }

    public void setLoadCallBack(INativeReqeustCallBack adListener) {
        this.mNativeAdListener = adListener;
    }

    public void setAdListener(INativeAdLoaderListener adListener) {
    }

    public void setAdClickListener(IAdOnClickListener adClickListener) {
        this.mNativeAdClickListener = adClickListener;
    }

    protected void removeExpiredAds(List<INativeAd> list) {
        if (list != null && list.size() != 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                INativeAd iNativeAd = (INativeAd) it.next();
                if (iNativeAd == null || iNativeAd.hasExpired()) {
                    it.remove();
                }
            }
        }
    }
}
