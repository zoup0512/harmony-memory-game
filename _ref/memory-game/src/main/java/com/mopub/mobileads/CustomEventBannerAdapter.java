package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.factories.CustomEventBannerFactory;
import com.yalantis.ucrop.util.FileUtils;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventBannerAdapter implements CustomEventBannerListener {
    public static final int DEFAULT_BANNER_TIMEOUT_DELAY = 10000;
    private Context mContext;
    private CustomEventBanner mCustomEventBanner;
    private final Handler mHandler = new Handler();
    private boolean mInvalidated;
    private Map<String, Object> mLocalExtras;
    private MoPubView mMoPubView;
    private Map<String, String> mServerExtras;
    private boolean mStoredAutorefresh;
    private final Runnable mTimeout;

    public CustomEventBannerAdapter(@NonNull MoPubView moPubView, @NonNull String str, @NonNull Map<String, String> map, long j, @Nullable AdReport adReport) {
        Preconditions.checkNotNull(map);
        this.mMoPubView = moPubView;
        this.mContext = moPubView.getContext();
        this.mTimeout = new Runnable() {
            public void run() {
                MoPubLog.d("Third-party network timed out.");
                CustomEventBannerAdapter.this.onBannerFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventBannerAdapter.this.invalidate();
            }
        };
        MoPubLog.d("Attempting to invoke custom event: " + str);
        try {
            this.mCustomEventBanner = CustomEventBannerFactory.create(str);
            this.mServerExtras = new TreeMap(map);
            this.mLocalExtras = this.mMoPubView.getLocalExtras();
            if (this.mMoPubView.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubView.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(j));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
            this.mLocalExtras.put(DataKeys.AD_WIDTH, Integer.valueOf(this.mMoPubView.getAdWidth()));
            this.mLocalExtras.put(DataKeys.AD_HEIGHT, Integer.valueOf(this.mMoPubView.getAdHeight()));
        } catch (Exception e) {
            MoPubLog.d("Couldn't locate or instantiate custom event: " + str + FileUtils.HIDDEN_PREFIX);
            this.mMoPubView.loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    void loadAd() {
        if (!isInvalidated() && this.mCustomEventBanner != null) {
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventBanner.loadBanner(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Throwable e) {
                MoPubLog.d("Loading a custom event banner threw an exception.", e);
                onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    void invalidate() {
        if (this.mCustomEventBanner != null) {
            try {
                this.mCustomEventBanner.onInvalidate();
            } catch (Throwable e) {
                MoPubLog.d("Invalidating a custom event banner threw an exception", e);
            }
        }
        this.mContext = null;
        this.mCustomEventBanner = null;
        this.mLocalExtras = null;
        this.mServerExtras = null;
        this.mInvalidated = true;
    }

    boolean isInvalidated() {
        return this.mInvalidated;
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        if (this.mMoPubView == null || this.mMoPubView.getAdTimeoutDelay() == null || this.mMoPubView.getAdTimeoutDelay().intValue() < 0) {
            return 10000;
        }
        return this.mMoPubView.getAdTimeoutDelay().intValue() * 1000;
    }

    public void onBannerLoaded(View view) {
        if (!isInvalidated()) {
            cancelTimeout();
            if (this.mMoPubView != null) {
                this.mMoPubView.nativeAdLoaded();
                this.mMoPubView.setAdContentView(view);
                if (!(view instanceof HtmlBannerWebView)) {
                    this.mMoPubView.trackNativeImpression();
                }
            }
        }
    }

    public void onBannerFailed(MoPubErrorCode moPubErrorCode) {
        if (!isInvalidated() && this.mMoPubView != null) {
            if (moPubErrorCode == null) {
                moPubErrorCode = MoPubErrorCode.UNSPECIFIED;
            }
            cancelTimeout();
            this.mMoPubView.loadFailUrl(moPubErrorCode);
        }
    }

    public void onBannerExpanded() {
        if (!isInvalidated()) {
            this.mStoredAutorefresh = this.mMoPubView.getAutorefreshEnabled();
            this.mMoPubView.setAutorefreshEnabled(false);
            this.mMoPubView.adPresentedOverlay();
        }
    }

    public void onBannerCollapsed() {
        if (!isInvalidated()) {
            this.mMoPubView.setAutorefreshEnabled(this.mStoredAutorefresh);
            this.mMoPubView.adClosed();
        }
    }

    public void onBannerClicked() {
        if (!isInvalidated() && this.mMoPubView != null) {
            this.mMoPubView.registerClick();
        }
    }

    public void onLeaveApplication() {
        onBannerClicked();
    }
}
