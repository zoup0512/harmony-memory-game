package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

public class NewsFlowAdsLoader implements INativeAdListListener {
    private Vector<INativeAd> mAdPool = new Vector();
    private NewsFlowAdListener mListener;
    private int mMinCacheSize;
    private String mPageId;
    private Vector<String> mTitlePool = new Vector();
    private NativeAdListManager nativeAdListManager;

    public interface NewsFlowAdListener {
        void onAdClick(INativeAd iNativeAd);

        void onAdLoadFailed();

        void onAdLoaded();
    }

    public NewsFlowAdsLoader(Context context, String posId, int minPoolSize) {
        this.mMinCacheSize = minPoolSize;
        this.mPageId = posId;
        this.nativeAdListManager = new NativeAdListManager(context, posId, this);
    }

    public void loadAds(int num) {
        this.mTitlePool.clear();
        this.nativeAdListManager.loadAds(num);
    }

    public INativeAd getAd() {
        return (INativeAd) ThreadHelper.runOnUiThreadBlockingNoException(new Callable<INativeAd>() {
            public INativeAd call() throws Exception {
                NewsFlowAdsLoader.this.removeExpiredAd();
                INativeAd iNativeAd = null;
                if (NewsFlowAdsLoader.this.mAdPool.size() > 0) {
                    iNativeAd = (INativeAd) NewsFlowAdsLoader.this.mAdPool.remove(0);
                }
                NewsFlowAdsLoader.this.refetchAd();
                return iNativeAd;
            }
        });
    }

    private void removeExpiredAd() {
        Iterator it = this.mAdPool.iterator();
        while (it.hasNext()) {
            INativeAd iNativeAd = (INativeAd) it.next();
            if (iNativeAd == null || iNativeAd.hasExpired()) {
                it.remove();
            }
        }
    }

    private void refetchAd() {
        g.a("pageid:" + this.mPageId + "pool size:" + this.mAdPool.size());
        if (this.mAdPool.size() < this.mMinCacheSize) {
            g.a("pageid:" + this.mPageId + "refetchAd");
            this.nativeAdListManager.loadAds(this.mMinCacheSize - this.mAdPool.size());
        }
    }

    public void onLoadProcess() {
        notifyAdLoaded();
    }

    private void notifyAdLoaded() {
        ThreadHelper.postOnUiThread(new Runnable() {
            public void run() {
                Collection adList = NewsFlowAdsLoader.this.nativeAdListManager.getAdList();
                List arrayList = new ArrayList();
                if (adList != null) {
                    arrayList.addAll(adList);
                    NewsFlowAdsLoader.this.pushAdsToPool(arrayList);
                }
                if (NewsFlowAdsLoader.this.mListener != null) {
                    NewsFlowAdsLoader.this.mListener.onAdLoaded();
                }
            }
        });
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
        Iterator it = this.mTitlePool.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!TextUtils.isEmpty(str) && str.equals(ad.getAdTitle())) {
                g.a("pageid:" + this.mPageId + "ad :" + ad.getAdTitle() + " has in pool list");
                return true;
            }
        }
        this.mTitlePool.add(ad.getAdTitle());
        return false;
    }

    public void setNativeListLoaderLisenter(NewsFlowAdListener listener) {
        this.mListener = listener;
    }

    public int getAdPoolSize() {
        refetchAd();
        return this.mAdPool.size();
    }

    public void adLoaded() {
        notifyAdLoaded();
    }

    public void adFailedToLoad(int errorcode) {
        ThreadHelper.postOnUiThread(new Runnable() {
            public void run() {
                if (NewsFlowAdsLoader.this.mListener != null) {
                    NewsFlowAdsLoader.this.mListener.onAdLoadFailed();
                }
            }
        });
    }

    public void adClicked(final INativeAd nativeAd) {
        ThreadHelper.postOnUiThread(new Runnable() {
            public void run() {
                if (NewsFlowAdsLoader.this.mListener != null) {
                    NewsFlowAdsLoader.this.mListener.onAdClick(nativeAd);
                }
            }
        });
    }

    public List<PosBean> getPosBeans() {
        return this.nativeAdListManager.getPosBeans();
    }

    public String getRequestLastError() {
        return this.nativeAdListManager.getRequestLastError();
    }

    public String getRequestErrorInfo() {
        return this.nativeAdListManager.getRequestErrorInfo();
    }

    public int getLoaderPoolSize() {
        if (this.mAdPool.isEmpty()) {
            return 0;
        }
        return this.mAdPool.size();
    }
}
