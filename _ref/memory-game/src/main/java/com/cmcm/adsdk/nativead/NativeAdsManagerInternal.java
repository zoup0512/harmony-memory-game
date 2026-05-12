package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAdLoader;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NativeAdsManagerInternal extends NativeAdManagerInternal implements IAdOnClickListener {
    INativeAdListListener mAdListListener = null;
    private List<INativeAd> mAdPool = new ArrayList();
    private int mExpectedSize = 0;
    private List<String> mTitlePool = new ArrayList();

    public NativeAdsManagerInternal(Context context, String posId) {
        super(context, posId);
    }

    public void loadAds(int num) {
        g.a(TAG, this.mPositionId + " loadAds num:" + num);
        this.mOptimizeEnabled = false;
        this.mTitlePool.clear();
        this.mAdPool.clear();
        this.mExpectedSize = num;
        loadAd();
    }

    public void setAdListener(INativeAdListListener adListener) {
        super.setAdListener(adListener);
        this.mAdListListener = adListener;
    }

    public List<INativeAd> getAdList() {
        return this.mAdPool;
    }

    protected int getLoadAdTypeSize() {
        if (!this.mIsOpenPriority) {
            return 1;
        }
        g.a(TAG, "is open priority, all load");
        return this.mConfigBeans.size();
    }

    protected boolean requestBean(PosBean bean) {
        int size = this.mExpectedSize - this.mAdPool.size();
        if (size <= 0) {
            asyncCheckIfAllFinished();
            return false;
        }
        String adName = bean.getAdName();
        g.a(Const.TAG, "to load " + adName);
        this.mRequestLogger.requestBegin(adName);
        CMNativeAdLoader adLoader = this.mLoaderMap.getAdLoader(this.mContext, bean, this);
        if (adLoader != null) {
            if (this.mRequestParams != null) {
                adLoader.setRequestParams(this.mRequestParams);
            }
            adLoader.setLoadCallBack(this);
            adLoader.loadAds(size);
            return true;
        }
        adFailedToLoad(adName, String.valueOf(CMAdError.NO_AD_TYPE_EROOR));
        return false;
    }

    private void pushAdsToPool(List<INativeAd> list) {
        if (list != null && !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                INativeAd iNativeAd = (INativeAd) it.next();
                if (iNativeAd == null || checkPoolHasAd(iNativeAd)) {
                    it.remove();
                }
            }
            this.mAdPool.addAll(list);
        }
    }

    private boolean checkPoolHasAd(INativeAd ad) {
        for (String str : this.mTitlePool) {
            if (!TextUtils.isEmpty(str) && str.equals(ad.getAdTitle())) {
                g.a("ad :" + ad.getAdTitle() + " has in pool list");
                return true;
            }
        }
        this.mTitlePool.add(ad.getAdTitle());
        return false;
    }

    public void adLoaded(String adTypeName) {
        super.adLoaded(adTypeName);
        if (!this.mIsOpenPriority) {
            int size = this.mAdPool.size();
            INativeAdLoader adLoader = this.mLoaderMap.getAdLoader(adTypeName);
            if (adLoader != null) {
                int size2 = this.mExpectedSize - this.mAdPool.size();
                if (size2 > 0) {
                    List adList = adLoader.getAdList(size2);
                    if (!(adList == null || adList.isEmpty())) {
                        pushAdsToPool(adList);
                    }
                }
            }
            g.b(Const.TAG, "adLoaded pool size: " + size + " -> " + this.mAdPool.size() + " expect:" + this.mExpectedSize);
            if (size != this.mAdPool.size()) {
                notifyLoadProgress();
            }
        }
    }

    protected void checkIfAllfinished() {
        g.a(Const.TAG, "check finish");
        if (this.mIsFinished) {
            g.c(Const.TAG, "already finished");
            return;
        }
        if (this.mIsOpenPriority) {
            if (isAllLoaderFinished()) {
                pushAdsToPool(super.getAdList(this.mExpectedSize));
            } else {
                return;
            }
        }
        if (this.mAdPool.size() >= this.mExpectedSize) {
            notifyAdLoaded();
        }
        if (!this.mIsFinished && isAllLoaderFinished()) {
            if (this.mAdPool.isEmpty()) {
                notifyAdFailed(CMAdError.NO_FILL_ERROR);
            } else {
                notifyAdLoaded();
            }
        }
    }

    protected void notifyLoadProgress() {
        ThreadHelper.runOnUiThread(new Runnable() {
            public void run() {
                if (NativeAdsManagerInternal.this.mAdListListener != null) {
                    NativeAdsManagerInternal.this.mAdListListener.onLoadProcess();
                }
            }
        });
    }
}
