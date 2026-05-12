package com.cmcm.adsdk.nativead;

import android.app.Activity;
import android.content.Context;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.CMRequestParams;
import com.cmcm.adsdk.adapter.NativeloaderAdapter.NativeAdapterListener;
import com.cmcm.adsdk.adapter.PicksNativeAdapter;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NativeAdManagerMini implements NativeAdapterListener, IAdOnClickListener {
    private List<INativeAd> mAdPool;
    private PicksNativeAdapter mAdapter;
    private Context mContext;
    private INativeAdLoaderListener mListener;
    private String mPosid;
    public CMRequestParams requestParams;

    public NativeAdManagerMini(Context context, String posid) {
        if (context instanceof Activity) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.mAdPool = new ArrayList();
        this.mAdapter = new PicksNativeAdapter();
        this.mPosid = posid;
    }

    public void setNativeAdListener(INativeAdLoaderListener listener) {
        this.mListener = listener;
    }

    private Map<String, Object> getLoadExtras(int num, String placementId) {
        boolean z = true;
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(CMBaseNativeAd.KEY_JUHE_POSID, this.mPosid);
        hashMap.put(CMBaseNativeAd.KEY_PLACEMENT_ID, placementId);
        hashMap.put(CMBaseNativeAd.KEY_LOAD_SIZE, Integer.valueOf(num));
        if (this.requestParams != null) {
            String str = CMBaseNativeAd.KEY_CHECK_VIEW;
            if (this.requestParams.getReportShowIgnoreView()) {
                z = false;
            }
            hashMap.put(str, Boolean.valueOf(z));
        } else {
            hashMap.put(CMBaseNativeAd.KEY_CHECK_VIEW, Boolean.valueOf(true));
        }
        return hashMap;
    }

    public INativeAd getAd() {
        filter();
        synchronized (this.mAdPool) {
            if (this.mAdPool.isEmpty()) {
                return null;
            }
            INativeAd iNativeAd = (INativeAd) this.mAdPool.remove(0);
            return iNativeAd;
        }
    }

    private void filter() {
        Iterator it = this.mAdPool.iterator();
        while (it.hasNext()) {
            if (((INativeAd) it.next()).hasExpired()) {
                it.remove();
            }
        }
    }

    public void onNativeAdLoaded(INativeAd nativeAd) {
        appendAd(nativeAd);
        if (this.mListener != null) {
            this.mListener.adLoaded();
        }
    }

    public void onNativeAdFailed(String errorCode) {
        if (this.mListener != null) {
            this.mListener.adFailedToLoad(CMAdError.NO_FILL_ERROR);
        }
    }

    public void onNativeAdLoaded(List<INativeAd> list) {
        appendAd((List) list);
        if (this.mListener != null) {
            this.mListener.adLoaded();
        }
    }

    public void onAdClick(INativeAd nativeAd) {
        if (this.mListener != null) {
            this.mListener.adClicked(nativeAd);
        }
    }

    void appendAd(List<INativeAd> adList) {
        if (adList != null) {
            for (INativeAd appendAd : adList) {
                appendAd(appendAd);
            }
        }
    }

    void appendAd(INativeAd ad) {
        synchronized (this.mAdPool) {
            this.mAdPool.add(new CMNativeAd(this.mContext, this, getLoadExtras(10, this.mPosid), (CMBaseNativeAd) ad));
        }
    }

    public void loadAd() {
        filter();
        if (this.mAdPool.isEmpty()) {
            this.mAdapter.setAdapterListener(this);
            this.mAdapter.loadNativeAd(this.mContext, getLoadExtras(10, this.mPosid));
        } else if (this.mListener != null) {
            this.mListener.adLoaded();
        }
    }
}
