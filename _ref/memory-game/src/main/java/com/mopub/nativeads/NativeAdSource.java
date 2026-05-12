package com.mopub.nativeads;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cmcm.adsdk.Const.res;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener;
import java.util.ArrayList;
import java.util.List;

class NativeAdSource {
    private static final int CACHE_LIMIT = 1;
    private static final int EXPIRATION_TIME_MILLISECONDS = 900000;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    @VisibleForTesting
    static final int[] RETRY_TIME_ARRAY_MILLISECONDS = new int[]{1000, res.facebook, 5000, 25000, 60000, MAXIMUM_RETRY_TIME_MILLISECONDS};
    @NonNull
    private final AdRendererRegistry mAdRendererRegistry;
    @Nullable
    private AdSourceListener mAdSourceListener;
    @VisibleForTesting
    int mCurrentRetries;
    @Nullable
    private MoPubNative mMoPubNative;
    @NonNull
    private final MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    @NonNull
    private final List<TimestampWrapper<NativeAd>> mNativeAdCache;
    @NonNull
    private final Handler mReplenishCacheHandler;
    @NonNull
    private final Runnable mReplenishCacheRunnable;
    @VisibleForTesting
    boolean mRequestInFlight;
    @Nullable
    private RequestParameters mRequestParameters;
    @VisibleForTesting
    boolean mRetryInFlight;
    @VisibleForTesting
    int mSequenceNumber;

    NativeAdSource() {
        this(new ArrayList(1), new Handler(), new AdRendererRegistry());
    }

    @VisibleForTesting
    NativeAdSource(@NonNull List<TimestampWrapper<NativeAd>> list, @NonNull Handler handler, @NonNull AdRendererRegistry adRendererRegistry) {
        this.mNativeAdCache = list;
        this.mReplenishCacheHandler = handler;
        this.mReplenishCacheRunnable = new 1(this);
        this.mAdRendererRegistry = adRendererRegistry;
        this.mMoPubNativeNetworkListener = new 2(this);
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    int getAdRendererCount() {
        return this.mAdRendererRegistry.getAdRendererCount();
    }

    public int getViewTypeForAd(@NonNull NativeAd nativeAd) {
        return this.mAdRendererRegistry.getViewTypeForAd(nativeAd);
    }

    void registerAdRenderer(@NonNull MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
        if (this.mMoPubNative != null) {
            this.mMoPubNative.registerAdRenderer(moPubAdRenderer);
        }
    }

    @Nullable
    public MoPubAdRenderer getAdRendererForViewType(int i) {
        return this.mAdRendererRegistry.getRendererForViewType(i);
    }

    void setAdSourceListener(@Nullable AdSourceListener adSourceListener) {
        this.mAdSourceListener = adSourceListener;
    }

    void loadAds(@NonNull Activity activity, @NonNull String str, RequestParameters requestParameters) {
        loadAds(requestParameters, new MoPubNative(activity, str, this.mMoPubNativeNetworkListener));
    }

    @VisibleForTesting
    void loadAds(RequestParameters requestParameters, MoPubNative moPubNative) {
        clear();
        for (MoPubAdRenderer registerAdRenderer : this.mAdRendererRegistry.getRendererIterable()) {
            moPubNative.registerAdRenderer(registerAdRenderer);
        }
        this.mRequestParameters = requestParameters;
        this.mMoPubNative = moPubNative;
        replenishCache();
    }

    void clear() {
        if (this.mMoPubNative != null) {
            this.mMoPubNative.destroy();
            this.mMoPubNative = null;
        }
        this.mRequestParameters = null;
        for (TimestampWrapper timestampWrapper : this.mNativeAdCache) {
            ((NativeAd) timestampWrapper.mInstance).destroy();
        }
        this.mNativeAdCache.clear();
        this.mReplenishCacheHandler.removeMessages(0);
        this.mRequestInFlight = false;
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    @Nullable
    NativeAd dequeueAd() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (!(this.mRequestInFlight || this.mRetryInFlight)) {
            this.mReplenishCacheHandler.post(this.mReplenishCacheRunnable);
        }
        while (!this.mNativeAdCache.isEmpty()) {
            TimestampWrapper timestampWrapper = (TimestampWrapper) this.mNativeAdCache.remove(0);
            if (uptimeMillis - timestampWrapper.mCreatedTimestamp < 900000) {
                return (NativeAd) timestampWrapper.mInstance;
            }
        }
        return null;
    }

    @VisibleForTesting
    void updateRetryTime() {
        if (this.mCurrentRetries < RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
            this.mCurrentRetries++;
        }
    }

    @VisibleForTesting
    void resetRetryTime() {
        this.mCurrentRetries = 0;
    }

    @VisibleForTesting
    int getRetryTime() {
        if (this.mCurrentRetries >= RETRY_TIME_ARRAY_MILLISECONDS.length) {
            this.mCurrentRetries = RETRY_TIME_ARRAY_MILLISECONDS.length - 1;
        }
        return RETRY_TIME_ARRAY_MILLISECONDS[this.mCurrentRetries];
    }

    @VisibleForTesting
    void replenishCache() {
        if (!this.mRequestInFlight && this.mMoPubNative != null && this.mNativeAdCache.size() < 1) {
            this.mRequestInFlight = true;
            this.mMoPubNative.makeRequest(this.mRequestParameters, Integer.valueOf(this.mSequenceNumber));
        }
    }

    @Deprecated
    @VisibleForTesting
    void setMoPubNative(MoPubNative moPubNative) {
        this.mMoPubNative = moPubNative;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
