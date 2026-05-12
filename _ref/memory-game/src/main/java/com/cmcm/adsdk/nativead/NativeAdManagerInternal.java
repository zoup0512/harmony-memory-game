package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.CMAdErrorManager;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.CMRequestParams;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.base.INativeReqeustCallBack;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.adsdk.config.RequestConfig;
import com.cmcm.adsdk.config.RequestConfig.ICallBack;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAdLoader;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import com.my.target.ads.MyTargetVideoView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NativeAdManagerInternal implements INativeReqeustCallBack, IAdOnClickListener {
    private static final int AD_PRIORITY_PROTECTION_TIME = 8000;
    public static final int DEFAULT_REQUEST_SIZE = 2;
    public static final int PRELOAD_REQUEST_SIZE = 1;
    protected static String TAG = Const.TAG;
    private long ONE_MINTURE = 60000;
    Runnable mAsyncFinishCheckRunnable = new Runnable() {
        public void run() {
            NativeAdManagerInternal.this.asyncCheckIfAllFinished();
        }
    };
    private INativeAdLoaderListener mCallBack;
    protected List<PosBean> mConfigBeans;
    protected final Context mContext;
    Runnable mFinishCheckRunnable = new Runnable() {
        public void run() {
            NativeAdManagerInternal.this.checkIfAllfinished();
        }
    };
    protected boolean mHighPriorityLoaded = false;
    protected volatile boolean mIsFinished = true;
    protected boolean mIsOpenPriority = false;
    protected boolean mIsPreload = false;
    private long mLoadStartTime = 0;
    protected NativeAdLoaderMap mLoaderMap = new NativeAdLoaderMap();
    public RequestLoadingStatus mLoadingStatus = new RequestLoadingStatus();
    protected boolean mOptimizeEnabled = true;
    public final int mPicksProtectTime;
    TimeoutTask mPicksProtectionTimer = null;
    protected final String mPositionId;
    TimeoutTask mPriorityProtectionTimer = null;
    public RequestResultLogger mRequestLogger = new RequestResultLogger();
    protected CMRequestParams mRequestParams;

    public NativeAdManagerInternal(Context context, String posId) {
        this.mContext = context;
        this.mPositionId = posId;
        this.mPicksProtectTime = 1000;
    }

    public void setOpenPriority(boolean openPriority) {
        this.mIsOpenPriority = openPriority;
    }

    public void setPreload(boolean isPreload) {
        this.mIsPreload = isPreload;
    }

    public void setRequestParams(CMRequestParams requestParams) {
        this.mRequestParams = requestParams;
    }

    public void setAdListener(INativeAdLoaderListener adListener) {
        this.mCallBack = adListener;
    }

    public INativeAdLoaderListener getAdListener() {
        return this.mCallBack;
    }

    public void loadAd() {
        if (this.mIsPreload) {
            g.a(Const.TAG, "posid " + this.mPositionId + " preloadAd...");
        } else {
            g.a(Const.TAG, "posid " + this.mPositionId + " loadAd...");
        }
        if (this.mIsFinished || System.currentTimeMillis() - this.mLoadStartTime >= this.ONE_MINTURE) {
            this.mIsFinished = false;
            this.mLoadStartTime = System.currentTimeMillis();
            RequestConfig.getInstance().getBeans(this.mPositionId, new ICallBack() {
                public void onConfigLoaded(String posId, List<PosBean> beans) {
                    NativeAdManagerInternal.this.loadAd(beans);
                }
            });
            return;
        }
        g.a(Const.TAG, "wait and reuse for last result");
    }

    public INativeAd getAd() {
        List adList = getAdList(1);
        return (adList == null || adList.isEmpty()) ? null : (INativeAd) adList.get(0);
    }

    public List<PosBean> getPosBeans() {
        return this.mConfigBeans;
    }

    private void loadAd(List<PosBean> beans) {
        if (beans == null || beans.isEmpty()) {
            g.d(Const.TAG, "the posid:" + this.mPositionId + "no config, may be has closed");
            if (CMAdManager.getMid() == null || CMAdManager.getContext() == null) {
                notifyAdFailed(CMAdError.NOT_INITIALIZE_ERROR);
                return;
            } else if (CMAdManager.getMid().length() != 4) {
                notifyAdFailed(CMAdError.MID_LENGTH_ERROR);
                return;
            } else if (this.mPositionId.length() < 4) {
                notifyAdFailed(CMAdError.POSID_ERROR);
                return;
            } else if (!this.mPositionId.substring(0, 4).contains(CMAdManager.getMid())) {
                notifyAdFailed(CMAdError.MID_NOTMATCH_POSID_ERROR);
                return;
            } else if (!CMAdErrorManager.isCompleteConfig) {
                notifyAdFailed(CMAdError.INIT_NOTCOMPLETE_ERROR);
                return;
            } else if (beans == null) {
                notifyAdFailed(CMAdError.POSID_NOCONFIG_ERROR);
                return;
            } else {
                notifyAdFailed(CMAdError.NO_CONFIG_ERROR);
                return;
            }
        }
        for (PosBean posBean : beans) {
            if (TextUtils.isEmpty(posBean.parameter)) {
                notifyAdFailed(CMAdError.CONFIG_PARAMTER_ERROR);
                return;
            }
        }
        this.mLoaderMap.updateLoaders(this.mContext, beans, this);
        for (String removeInvalidBeans : this.mLoaderMap.mFailedLoaderNames) {
            removeInvalidBeans(beans, removeInvalidBeans);
        }
        this.mHighPriorityLoaded = false;
        this.mConfigBeans = beans;
        loadChildAds();
    }

    private boolean removeInvalidBeans(List<PosBean> beans, String name) {
        if (beans == null || beans.isEmpty() || TextUtils.isEmpty(name)) {
            return false;
        }
        Iterator it = beans.iterator();
        boolean z = false;
        while (it.hasNext()) {
            PosBean posBean = (PosBean) it.next();
            if (posBean != null && name.equalsIgnoreCase(posBean.name)) {
                z = true;
                it.remove();
            }
            z = z;
        }
        return z;
    }

    private void loadChildAds() {
        this.mRequestLogger.reset();
        this.mLoadingStatus.resetLoadingStatus(this.mConfigBeans.size());
        int loadAdTypeSize = getLoadAdTypeSize();
        Object obj = null;
        for (int i = 0; i < loadAdTypeSize; i++) {
            if (issueToLoadNext()) {
                obj = 1;
            }
        }
        if (obj != null) {
            if (this.mIsOpenPriority) {
                int adTypeNameIndex = getAdTypeNameIndex(Const.KEY_CM);
                if (!(adTypeNameIndex == -1 || this.mLoadingStatus.isBeanLoading(adTypeNameIndex))) {
                    requestBean(adTypeNameIndex);
                    if (this.mPicksProtectTime > 0) {
                        this.mPicksProtectionTimer = new TimeoutTask(this.mAsyncFinishCheckRunnable, "PicksProtectionTimer");
                        this.mPicksProtectionTimer.start(this.mPicksProtectTime);
                    }
                }
            }
            if (this.mIsOpenPriority || loadAdTypeSize > 1) {
                this.mPriorityProtectionTimer = new TimeoutTask(this.mAsyncFinishCheckRunnable, "PriorityProtectionTimer");
                this.mPriorityProtectionTimer.start(8000);
            }
        } else if (this.mLoaderMap.mFailedLoaderNames.size() > 0) {
            String str = "";
            for (int i2 = 0; i2 < this.mLoaderMap.mFailedLoaderNames.size(); i2++) {
                str.concat(((String) this.mLoaderMap.mFailedLoaderNames.get(i2)) + ";");
            }
            g.a(Const.TAG, "loadChildAds create loader error,loader names:" + str);
            notifyAdFailed(CMAdError.CREATE_LOADER_ERROR);
        } else {
            g.a(Const.TAG, "loadChildAds no-loader was issued");
            notifyAdFailed(CMAdError.NO_LOADER_ERROR);
        }
    }

    protected int getAdTypeNameIndex(String adtypeName) {
        for (int i = 0; i < this.mConfigBeans.size(); i++) {
            if (((PosBean) this.mConfigBeans.get(i)).getAdName().equalsIgnoreCase(adtypeName)) {
                return i;
            }
        }
        return -1;
    }

    protected int getLoadAdTypeSize() {
        if (this.mConfigBeans == null || this.mConfigBeans.isEmpty()) {
            return 0;
        }
        if (this.mIsPreload) {
            return Math.min(this.mConfigBeans.size(), 1);
        }
        return Math.min(this.mConfigBeans.size(), 2);
    }

    private boolean issueToLoadNext() {
        boolean z;
        int i = false;
        g.a(Const.TAG, "issueToLoadNext index waiting :" + this.mLoadingStatus.getWaitingBeansNumber() + ",config size:" + this.mConfigBeans.size());
        if (!this.mIsFinished) {
            boolean z2 = false;
            while (i < this.mConfigBeans.size()) {
                if (!this.mLoadingStatus.isBeanLoading(i)) {
                    z2 = requestBean(i);
                    if (z2) {
                        z = z2;
                        break;
                    }
                }
                i++;
            }
            z = z2;
            if (!z) {
                g.a(Const.TAG, "the load index is last one,remove no callback task");
            }
        }
        return z;
    }

    private boolean requestBean(int index) {
        if (index < 0 || index >= this.mConfigBeans.size() || !this.mLoadingStatus.setBeanLoading(index, true) || !requestBean((PosBean) this.mConfigBeans.get(index))) {
            return false;
        }
        return true;
    }

    protected boolean requestBean(PosBean bean) {
        String adName = bean.getAdName();
        g.a(Const.TAG, "to load " + adName);
        this.mRequestLogger.requestBegin(adName);
        CMNativeAdLoader adLoader = this.mLoaderMap.getAdLoader(this.mContext, bean, this);
        if (adLoader != null) {
            if (this.mRequestParams != null) {
                adLoader.setRequestParams(this.mRequestParams);
            }
            adLoader.setLoadCallBack(this);
            adLoader.loadAd();
            return true;
        }
        adFailedToLoad(adName, String.valueOf(CMAdError.NO_AD_TYPE_EROOR));
        return false;
    }

    public void adLoaded(String adTypeName) {
        g.a(Const.TAG, adTypeName + " load success");
        this.mRequestLogger.requestEnd(adTypeName, true, null);
        if (checkPreAdIsLoading(getAdTypeNameIndex(adTypeName))) {
            this.mHighPriorityLoaded = true;
        }
        asyncCheckIfAllFinished();
        asyncIssueNext();
    }

    private boolean checkPreAdIsLoading(int index) {
        for (int i = 0; i < index; i++) {
            if (!this.mLoadingStatus.isBeanLoading(i)) {
                return false;
            }
        }
        return true;
    }

    public void adFailedToLoad(String adTypeName, String errorString) {
        g.a(Const.TAG, adTypeName + " load fail :error" + errorString);
        this.mRequestLogger.requestEnd(adTypeName, false, errorString);
        asyncCheckIfAllFinished();
        asyncIssueNext();
    }

    public void onAdClick(final INativeAd nativeAd) {
        ThreadHelper.postOnUiThread(new Runnable() {
            public void run() {
                if (NativeAdManagerInternal.this.mCallBack != null) {
                    NativeAdManagerInternal.this.mCallBack.adClicked(nativeAd);
                }
            }
        });
    }

    protected void asyncCheckIfAllFinished() {
        ThreadHelper.postOnUiThread(this.mFinishCheckRunnable);
    }

    protected void checkIfAllfinished() {
        g.a(Const.TAG, "check finish");
        if (this.mIsFinished) {
            g.c(Const.TAG, "already finished");
            return;
        }
        if (this.mIsOpenPriority) {
            CMNativeAdLoader adLoader = this.mLoaderMap.getAdLoader(Const.KEY_CM);
            if (adLoader != null && adLoader.isLoaded() && adLoader.checkLoadedPriorityAd()) {
                g.a(Const.TAG, "has open priority and priority ad load success");
                notifyAdLoaded();
                return;
            } else if (!(adLoader == null || adLoader.isLoaded() || this.mPicksProtectionTimer == null || this.mPicksProtectionTimer.mTimeout)) {
                g.c(Const.TAG, "wait picks loading");
                return;
            }
        }
        for (PosBean adName : this.mConfigBeans) {
            Model finishedItem = this.mRequestLogger.getFinishedItem(adName.getAdName());
            if (finishedItem != null || this.mPriorityProtectionTimer == null || this.mPriorityProtectionTimer.mTimeout) {
                if (finishedItem != null && finishedItem.isSuccess()) {
                    notifyAdLoaded();
                    break;
                }
            } else {
                g.c(Const.TAG, "is timeout:" + this.mPriorityProtectionTimer.mTimeout + "...wait");
                return;
            }
        }
        if (!this.mIsFinished && isAllLoaderFinished()) {
            notifyAdFailed(CMAdError.NO_FILL_ERROR);
        }
    }

    protected boolean isAllLoaderFinished() {
        if (this.mLoadingStatus.getWaitingBeansNumber() != 0) {
            return false;
        }
        for (PosBean adName : this.mConfigBeans) {
            CMNativeAdLoader adLoader = this.mLoaderMap.getAdLoader(adName.getAdName());
            if (adLoader != null && !adLoader.isLoaded()) {
                return false;
            }
        }
        return true;
    }

    private void asyncIssueNext() {
        ThreadHelper.postOnUiThread(new Runnable() {
            public void run() {
                if (!NativeAdManagerInternal.this.mIsFinished) {
                    if (NativeAdManagerInternal.this.mOptimizeEnabled && NativeAdManagerInternal.this.mHighPriorityLoaded) {
                        g.a(Const.TAG, "optimized skip issueNext");
                    } else {
                        NativeAdManagerInternal.this.issueToLoadNext();
                    }
                }
            }
        });
    }

    void stopTimeOutTask() {
        if (this.mPicksProtectionTimer != null) {
            this.mPicksProtectionTimer.stop();
            this.mPicksProtectionTimer = null;
        }
        if (this.mPriorityProtectionTimer != null) {
            this.mPriorityProtectionTimer.stop();
            this.mPriorityProtectionTimer = null;
        }
    }

    protected void notifyAdLoaded() {
        g.a(Const.TAG, "notifyAdLoaded time(ms): " + (System.currentTimeMillis() - this.mLoadStartTime));
        notifyAdLoadFinished(true, 0);
    }

    protected void notifyAdFailed(int errorCode) {
        g.a(Const.TAG, "notifyAdFailed time(ms): " + (System.currentTimeMillis() - this.mLoadStartTime));
        notifyAdLoadFinished(false, errorCode);
    }

    protected void notifyAdLoadFinished(boolean loaded, int errorCode) {
        String str;
        this.mIsFinished = true;
        RequestResultLogger requestResultLogger = this.mRequestLogger;
        if (loaded) {
            str = MyTargetVideoView.COMPLETE_STATUS_OK;
        } else {
            str = "fail.error:" + errorCode;
        }
        requestResultLogger.setRequestResult(str);
        ThreadHelper.revokeOnUiThread(this.mFinishCheckRunnable);
        ThreadHelper.revokeOnUiThread(this.mAsyncFinishCheckRunnable);
        stopTimeOutTask();
        if (this.mCallBack == null) {
            return;
        }
        if (loaded) {
            this.mCallBack.adLoaded();
        } else {
            this.mCallBack.adFailedToLoad(errorCode);
        }
    }

    public List<INativeAd> getAdList(int num) {
        g.a(Const.TAG, "getAdList");
        List<INativeAd> arrayList = new ArrayList();
        if (this.mConfigBeans == null || this.mConfigBeans.isEmpty() || this.mLoaderMap == null) {
            return arrayList;
        }
        Collection picksPropertyAds;
        if (this.mIsOpenPriority) {
            picksPropertyAds = getPicksPropertyAds(num);
            if (!(picksPropertyAds == null || picksPropertyAds.isEmpty())) {
                arrayList.addAll(picksPropertyAds);
            }
        }
        if (arrayList.size() < num) {
            for (PosBean adName : this.mConfigBeans) {
                INativeAdLoader adLoader = this.mLoaderMap.getAdLoader(adName.getAdName());
                if (adLoader != null) {
                    picksPropertyAds = adLoader.getAdList(num - arrayList.size());
                    if (!(picksPropertyAds == null || picksPropertyAds.isEmpty())) {
                        arrayList.addAll(picksPropertyAds);
                        g.b(Const.TAG, "this mAdList size =" + arrayList.size());
                    }
                    if (arrayList.size() >= num) {
                        break;
                    }
                }
            }
        }
        for (INativeAd iNativeAd : arrayList) {
            ((CMNativeAd) iNativeAd).setReUseAd();
        }
        return arrayList;
    }

    private List<INativeAd> getPicksPropertyAds(int num) {
        CMNativeAdLoader adLoader = this.mLoaderMap.getAdLoader(Const.KEY_CM);
        if (adLoader != null) {
            return adLoader.getPriorityAdList(num);
        }
        return null;
    }
}
