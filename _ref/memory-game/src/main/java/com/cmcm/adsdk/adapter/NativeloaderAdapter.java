package com.cmcm.adsdk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.utils.ThreadHelper;
import java.util.List;
import java.util.Map;

public abstract class NativeloaderAdapter {
    public static final int DEFAULT_LOAD_SIZE = 1;
    private final String ADLOAD_AD = "adload_ad";
    private final String ADLOAD_ADS = "adload_ads";
    private final String FAILED = "failed";
    private NativeAdapterListener mListener;

    private class CallBackRunnable implements Runnable {
        private String errorInfo;
        private List<INativeAd> list;
        private INativeAd nativeAd;
        private String type;

        public CallBackRunnable(String type, INativeAd nativeAd, List<INativeAd> list, String errorInfo) {
            this.type = type;
            this.nativeAd = nativeAd;
            this.list = list;
            this.errorInfo = errorInfo;
        }

        public void run() {
            if (NativeloaderAdapter.this.mListener == null) {
                return;
            }
            if ("adload_ads".equals(this.type)) {
                NativeloaderAdapter.this.mListener.onNativeAdLoaded(this.list);
            } else if ("adload_ad".equals(this.type)) {
                NativeloaderAdapter.this.mListener.onNativeAdLoaded(this.nativeAd);
            } else if ("failed".equals(this.type)) {
                NativeloaderAdapter.this.mListener.onNativeAdFailed(this.errorInfo);
            }
        }
    }

    public interface NativeAdapterListener {
        void onNativeAdFailed(String str);

        void onNativeAdLoaded(INativeAd iNativeAd);

        void onNativeAdLoaded(List<INativeAd> list);
    }

    public abstract String getAdKeyType();

    public abstract long getDefaultCacheTime();

    public abstract String getReportPkgName(String str);

    public abstract int getReportRes();

    public abstract void loadNativeAd(@NonNull Context context, @NonNull Map<String, Object> map);

    public int getDefaultLoadNum() {
        return 1;
    }

    public void setAdapterListener(NativeAdapterListener listener) {
        this.mListener = listener;
    }

    protected void notifyNativeAdLoaded(INativeAd nativeAd) {
        callBack("adload_ad", nativeAd, null, "");
    }

    protected void notifyNativeAdLoaded(List<INativeAd> list) {
        callBack("adload_ads", null, list, "");
    }

    protected void notifyNativeAdFailed(String errorInfo) {
        callBack("failed", null, null, errorInfo);
    }

    private void callBack(String type, INativeAd nativeAd, List<INativeAd> list, String errorInfo) {
        ThreadHelper.runOnUiThread(new CallBackRunnable(type, nativeAd, list, errorInfo));
    }
}
