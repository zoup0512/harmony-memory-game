package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.adapter.CMBaseNativeloaderAdapter;
import com.cmcm.adsdk.adapter.NativeloaderAdapter;
import com.cmcm.adsdk.adapter.NativeloaderAdapter.NativeAdapterListener;
import com.cmcm.adsdk.banner.CMBannerParams;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.picks.market.MarketUtils;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CMNativeAdLoader extends CMBaseNativeloaderAdapter implements NativeAdapterListener, IAdOnClickListener {
    private static final int DEFAULT_TIMEOUT_TIME = 15000;
    private static final int DEFAULT_TRY_NUMBER = 2;
    private static final String TAG = "CMNativeAdLoader";
    private List<INativeAd> mAdPool;
    private NativeloaderAdapter mInternalNativeLoader;
    private long mLastLoadTime = 0;
    private int mLoadNumber = 1;
    private boolean mLoaded = true;
    private TimeoutTask mLoaderTimerOutTask = null;
    Map<String, Object> mLocalExtras;
    public final String[] mPlacementIds;
    private int mPlacementIndex = 0;
    public final PosBean mPosBean;
    private boolean mSelectAllPriorityAd = true;
    Runnable mTimeoutRun = new Runnable() {
        public void run() {
            ThreadHelper.postOnUiThread(new Runnable() {
                public void run() {
                    g.a(CMNativeAdLoader.TAG, CMNativeAdLoader.this.getAdTypeName() + " 15s no callback timeout");
                    CMNativeAdLoader.this.mTryNumber = 0;
                    CMNativeAdLoader.this.onNativeAdFailed("15timeout");
                }
            });
        }
    };
    private int mTryNumber = 1;

    public CMNativeAdLoader(Context context, String posId, String adTypeName, String params, PosBean posBean, NativeloaderAdapter internalLoader) {
        super(context, posId, adTypeName);
        this.mPosBean = posBean;
        this.mInternalNativeLoader = internalLoader;
        if (TextUtils.isEmpty(params)) {
            this.mPlacementIds = null;
        } else {
            if (Const.KEY_FB.equals(internalLoader.getAdKeyType())) {
                this.mPlacementIds = params.split(",");
            } else {
                this.mPlacementIds = new String[1];
                this.mPlacementIds[0] = params;
            }
        }
        this.mAdPool = new ArrayList();
    }

    public boolean isLoaded() {
        return this.mLoaded;
    }

    public void loadAds(int num) {
        load(num);
    }

    public void loadAd() {
        load(1);
    }

    private void load(int num) {
        int i = 1;
        if (this.mPlacementIds == null || this.mPlacementIds.length == 0) {
            this.mNativeAdListener.adFailedToLoad(getAdTypeName(), CMAdError.ERROR_CONFIG);
            return;
        }
        removeExpiredAds(this.mAdPool);
        if (this.mAdPool.size() >= num) {
            g.a(Const.TAG, "adload has cache , cache size :" + this.mAdPool.size());
            this.mNativeAdListener.adLoaded(getAdTypeName());
        } else if (this.mLoaded) {
            this.mLastLoadTime = System.currentTimeMillis();
            this.mLoadNumber = Math.max(num, this.mInternalNativeLoader.getDefaultLoadNum());
            if (this.mPlacementIds.length > 1) {
                i = 2;
            }
            this.mTryNumber = i;
            this.mLoaded = false;
            if (this.mLoaderTimerOutTask == null) {
                this.mLoaderTimerOutTask = new TimeoutTask(this.mTimeoutRun, "Loader_Timeout");
                this.mLoaderTimerOutTask.start(15000);
            }
            if (this.requestParams != null) {
                this.mSelectAllPriorityAd = this.requestParams.isSelectAllPriorityAd();
            }
            issueNextPlacementId();
        }
    }

    private void issueNextPlacementId() {
        this.mTryNumber--;
        String str = this.mPlacementIds[this.mPlacementIndex % this.mPlacementIds.length];
        this.mPlacementIndex++;
        this.mLocalExtras = getLoadExtras(this.mLoadNumber, str);
        this.mInternalNativeLoader.setAdapterListener(this);
        this.mInternalNativeLoader.loadNativeAd(this.mContext, this.mLocalExtras);
    }

    private Map<String, Object> getLoadExtras(int num, String placementId) {
        long j = 1800000;
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(CMBaseNativeAd.KEY_JUHE_POSID, this.mPositionId);
        hashMap.put(CMBaseNativeAd.KEY_PLACEMENT_ID, placementId);
        hashMap.put(CMBaseNativeAd.KEY_LOAD_SIZE, Integer.valueOf(num));
        hashMap.put(CMBaseNativeAd.KEY_REPORT_RES, Integer.valueOf(this.mInternalNativeLoader.getReportRes()));
        hashMap.put(CMBaseNativeAd.KEY_REPORT_PKGNAME, this.mInternalNativeLoader.getReportPkgName(getAdTypeName()));
        long defaultCacheTime = this.mInternalNativeLoader.getDefaultCacheTime();
        if (defaultCacheTime <= 1800000) {
            g.d(Const.TAG, "default cache time to low: " + defaultCacheTime + " reset to 30min");
        } else {
            j = defaultCacheTime;
        }
        hashMap.put(CMBaseNativeAd.KEY_CACHE_TIME, Long.valueOf(j));
        if (this.requestParams != null) {
            if (this.requestParams instanceof CMBannerParams) {
                hashMap.put(CMBaseNativeAd.KEY_BANNER_VIEW_SIZE, ((CMBannerParams) this.requestParams).getCMBannerAdSize());
            }
            hashMap.put(CMBaseNativeAd.KEY_CHECK_VIEW, Boolean.valueOf(!this.requestParams.getReportShowIgnoreView()));
        } else {
            hashMap.put(CMBaseNativeAd.KEY_CHECK_VIEW, Boolean.valueOf(true));
        }
        return hashMap;
    }

    public INativeAd getAd() {
        removeExpiredAds(this.mAdPool);
        if (this.mAdPool.isEmpty()) {
            return null;
        }
        return (INativeAd) this.mAdPool.remove(0);
    }

    public List<INativeAd> getAdList(int num) {
        return getPriorityAdList(false, num);
    }

    public List<INativeAd> getPriorityAdList(int num) {
        return getPriorityAdList(true, num);
    }

    private List<INativeAd> getPriorityAdList(boolean filterPriority, int num) {
        removeExpiredAds(this.mAdPool);
        Object arrayList = new ArrayList();
        int size = this.mAdPool.size();
        for (int i = 0; i < size; i++) {
            INativeAd iNativeAd = (INativeAd) this.mAdPool.get(i);
            if (filterPriority) {
                if (!iNativeAd.isPriority()) {
                    if (!this.mSelectAllPriorityAd) {
                        break;
                    }
                }
                arrayList.add(iNativeAd);
            } else {
                arrayList.add(iNativeAd);
            }
            if (arrayList.size() >= num) {
                break;
            }
        }
        this.mAdPool.removeAll(arrayList);
        return arrayList;
    }

    public void onNativeAdLoaded(INativeAd nativeAd) {
        this.mLoaded = true;
        appendAd(nativeAd);
        stopTimeOutTask();
        this.mNativeAdListener.adLoaded(getAdTypeName());
    }

    public void onNativeAdFailed(String errorCode) {
        if (this.mTryNumber <= 0) {
            this.mLoaded = true;
            stopTimeOutTask();
            this.mNativeAdListener.adFailedToLoad(getAdTypeName(), errorCode);
            return;
        }
        issueNextPlacementId();
    }

    public void onAdClick(INativeAd nativeAd) {
        if (this.mNativeAdClickListener != null) {
            this.mNativeAdClickListener.onAdClick(nativeAd);
        }
        recordClick(nativeAd);
    }

    public void onNativeAdLoaded(List<INativeAd> list) {
        this.mLoaded = true;
        appendAd((List) list);
        stopTimeOutTask();
        this.mNativeAdListener.adLoaded(getAdTypeName());
    }

    void appendAd(List<INativeAd> adList) {
        if (adList != null) {
            for (INativeAd appendAd : adList) {
                appendAd(appendAd);
            }
        }
    }

    void appendAd(INativeAd ad) {
        this.mLocalExtras.put(CMBaseNativeAd.KEY_AD_TYPE_NAME, getAdTypeName());
        this.mAdPool.add(new CMNativeAd(this.mContext, this, this.mLocalExtras, (CMBaseNativeAd) ad));
    }

    private void recordClick(INativeAd nativeAd) {
        if (nativeAd != null && !getAdTypeName().equalsIgnoreCase(Const.KEY_CM) && !getAdTypeName().equalsIgnoreCase(Const.KEY_CM_BANNER)) {
            Map map = null;
            String str = "";
            try {
                CMBaseNativeAd cMBaseNativeAd = (CMBaseNativeAd) nativeAd;
                map = cMBaseNativeAd.getExtraReportParams();
                str = cMBaseNativeAd.getRawString(2);
            } catch (Exception e) {
            }
            MarketUtils.reportExtra("click", this.mInternalNativeLoader.getReportPkgName(getAdTypeName()), this.mPositionId, this.mInternalNativeLoader.getReportRes(), map, (String) this.mLocalExtras.get(CMBaseNativeAd.KEY_PLACEMENT_ID), nativeAd, str);
        }
    }

    public boolean checkLoadedPriorityAd() {
        if (this.mAdPool.size() > 0) {
            INativeAd iNativeAd;
            if (this.mSelectAllPriorityAd) {
                for (INativeAd iNativeAd2 : this.mAdPool) {
                    if (iNativeAd2 != null && iNativeAd2.isPriority()) {
                        return true;
                    }
                }
            } else {
                iNativeAd2 = (INativeAd) this.mAdPool.get(0);
                boolean z = iNativeAd2 != null && iNativeAd2.isPriority();
                return z;
            }
        }
        return false;
    }

    void stopTimeOutTask() {
        if (this.mLoaderTimerOutTask != null) {
            this.mLoaderTimerOutTask.stop();
            this.mLoaderTimerOutTask = null;
        }
    }
}
