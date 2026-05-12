package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.CacheService;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.mobileads.VastXmlManagerAggregator.VastXmlManagerAggregatorListener;
import com.mopub.volley.DefaultRetryPolicy;

public class VastManager implements VastXmlManagerAggregatorListener {
    @Nullable
    private String mDspCreativeId;
    private int mScreenAreaDp;
    private double mScreenAspectRatio;
    private final boolean mShouldPreCacheVideo;
    @Nullable
    private VastManagerListener mVastManagerListener;
    @Nullable
    private VastXmlManagerAggregator mVastXmlManagerAggregator;

    public VastManager(@NonNull Context context, boolean z) {
        initializeScreenDimensions(context);
        this.mShouldPreCacheVideo = z;
    }

    public void prepareVastVideoConfiguration(@Nullable String str, @NonNull VastManagerListener vastManagerListener, @Nullable String str2, @NonNull Context context) {
        Preconditions.checkNotNull(vastManagerListener, "vastManagerListener cannot be null");
        Preconditions.checkNotNull(context, "context cannot be null");
        if (this.mVastXmlManagerAggregator == null) {
            this.mVastManagerListener = vastManagerListener;
            this.mVastXmlManagerAggregator = new VastXmlManagerAggregator(this, this.mScreenAspectRatio, this.mScreenAreaDp, context.getApplicationContext());
            this.mDspCreativeId = str2;
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mVastXmlManagerAggregator, new String[]{str});
            } catch (Throwable e) {
                MoPubLog.d("Failed to aggregate vast xml", e);
                this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
            }
        }
    }

    public void cancel() {
        if (this.mVastXmlManagerAggregator != null) {
            this.mVastXmlManagerAggregator.cancel(true);
            this.mVastXmlManagerAggregator = null;
        }
    }

    public void onAggregationComplete(@Nullable VastVideoConfig vastVideoConfig) {
        if (this.mVastManagerListener == null) {
            throw new IllegalStateException("mVastManagerListener cannot be null here. Did you call prepareVastVideoConfiguration()?");
        } else if (vastVideoConfig == null) {
            this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
        } else {
            if (!TextUtils.isEmpty(this.mDspCreativeId)) {
                vastVideoConfig.setDspCreativeId(this.mDspCreativeId);
            }
            if (!this.mShouldPreCacheVideo || updateDiskMediaFileUrl(vastVideoConfig)) {
                this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfig);
                return;
            }
            VideoDownloader.cache(vastVideoConfig.getNetworkMediaFileUrl(), new 1(this, vastVideoConfig));
        }
    }

    private boolean updateDiskMediaFileUrl(@NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        String networkMediaFileUrl = vastVideoConfig.getNetworkMediaFileUrl();
        if (!CacheService.containsKeyDiskCache(networkMediaFileUrl)) {
            return false;
        }
        vastVideoConfig.setDiskMediaFileUrl(CacheService.getFilePathDiskCache(networkMediaFileUrl));
        return true;
    }

    private void initializeScreenDimensions(@NonNull Context context) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        float f = context.getResources().getDisplayMetrics().density;
        if (f <= 0.0f) {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        int max = Math.max(width, height);
        width = Math.min(width, height);
        this.mScreenAspectRatio = ((double) max) / ((double) width);
        this.mScreenAreaDp = (int) ((((float) width) / f) * (((float) max) / f));
    }

    @Deprecated
    @VisibleForTesting
    int getScreenAreaDp() {
        return this.mScreenAreaDp;
    }

    @Deprecated
    @VisibleForTesting
    double getScreenAspectRatio() {
        return this.mScreenAspectRatio;
    }
}
