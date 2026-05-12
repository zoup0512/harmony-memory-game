package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventRewardedVideo.CustomEventRewardedVideoListener;
import java.util.Map;

public class MoPubRewardedVideo extends CustomEventRewardedVideo {
    @NonNull
    private static final String MOPUB_REWARDED_VIDEO_ID = "mopub_rewarded_video_id";
    private boolean mIsLoaded;
    @NonNull
    private RewardedVastVideoInterstitial mRewardedVastVideoInterstitial = new RewardedVastVideoInterstitial();
    private int mRewardedVideoCurrencyAmount;
    @Nullable
    private String mRewardedVideoCurrencyName;

    @Nullable
    protected CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return null;
    }

    @Nullable
    protected LifecycleListener getLifecycleListener() {
        return null;
    }

    @NonNull
    protected String getAdNetworkId() {
        return MOPUB_REWARDED_VIDEO_ID;
    }

    protected void onInvalidate() {
        this.mRewardedVastVideoInterstitial.onInvalidate();
        this.mIsLoaded = false;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        return false;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        Preconditions.checkNotNull(activity, "activity cannot be null");
        Preconditions.checkNotNull(map, "localExtras cannot be null");
        Preconditions.checkNotNull(map2, "serverExtras cannot be null");
        Object obj = map.get(DataKeys.REWARDED_VIDEO_CURRENCY_NAME_KEY);
        if (obj instanceof String) {
            this.mRewardedVideoCurrencyName = (String) obj;
        } else {
            MoPubLog.d("No currency name specified for rewarded video. Using the default name.");
            this.mRewardedVideoCurrencyName = "";
        }
        Object obj2 = map.get(DataKeys.REWARDED_VIDEO_CURRENCY_AMOUNT_STRING_KEY);
        if (obj2 instanceof String) {
            try {
                this.mRewardedVideoCurrencyAmount = Integer.parseInt((String) obj2);
            } catch (NumberFormatException e) {
                MoPubLog.d("Unable to convert currency amount: " + obj2 + ". Using the default reward amount: " + 0);
                this.mRewardedVideoCurrencyAmount = 0;
            }
        } else {
            MoPubLog.d("No currency amount specified for rewarded video. Using the default reward amount: 0");
            this.mRewardedVideoCurrencyAmount = 0;
        }
        if (this.mRewardedVideoCurrencyAmount < 0) {
            MoPubLog.d("Negative currency amount specified for rewarded video. Using the default reward amount: 0");
            this.mRewardedVideoCurrencyAmount = 0;
        }
        this.mRewardedVastVideoInterstitial.loadInterstitial(activity, new MoPubRewardedVideoListener(this, null), map, map2);
    }

    protected boolean hasVideoAvailable() {
        return this.mIsLoaded;
    }

    protected void showVideo() {
        if (hasVideoAvailable()) {
            MoPubLog.d("Showing MoPub rewarded video.");
            this.mRewardedVastVideoInterstitial.showInterstitial();
            return;
        }
        MoPubLog.d("Unable to show MoPub rewarded video");
    }

    @Deprecated
    @VisibleForTesting
    void setRewardedVastVideoInterstitial(@NonNull RewardedVastVideoInterstitial rewardedVastVideoInterstitial) {
        this.mRewardedVastVideoInterstitial = rewardedVastVideoInterstitial;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    String getRewardedVideoCurrencyName() {
        return this.mRewardedVideoCurrencyName;
    }

    @Deprecated
    @VisibleForTesting
    int getRewardedVideoCurrencyAmount() {
        return this.mRewardedVideoCurrencyAmount;
    }

    @Deprecated
    @VisibleForTesting
    void setIsLoaded(boolean z) {
        this.mIsLoaded = z;
    }
}
