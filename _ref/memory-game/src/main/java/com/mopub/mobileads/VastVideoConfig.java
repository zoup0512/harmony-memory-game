package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils$ForceOrientation;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Strings;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VastVideoConfig implements Serializable {
    private static final long serialVersionUID = 1;
    @NonNull
    private final ArrayList<VastAbsoluteProgressTracker> mAbsoluteTrackers = new ArrayList();
    @Nullable
    private String mClickThroughUrl;
    @NonNull
    private final ArrayList<VastTracker> mClickTrackers = new ArrayList();
    @NonNull
    private final ArrayList<VastTracker> mCloseTrackers = new ArrayList();
    @NonNull
    private final ArrayList<VastTracker> mCompleteTrackers = new ArrayList();
    @Nullable
    private String mCustomCloseIconUrl;
    @Nullable
    private String mCustomCtaText;
    @NonNull
    private DeviceUtils$ForceOrientation mCustomForceOrientation = DeviceUtils$ForceOrientation.FORCE_LANDSCAPE;
    @Nullable
    private String mCustomSkipText;
    @Nullable
    private String mDiskMediaFileUrl;
    private String mDspCreativeId;
    @NonNull
    private final ArrayList<VastTracker> mErrorTrackers = new ArrayList();
    @NonNull
    private final ArrayList<VastFractionalProgressTracker> mFractionalTrackers = new ArrayList();
    @NonNull
    private final ArrayList<VastTracker> mImpressionTrackers = new ArrayList();
    private boolean mIsForceOrientationSet;
    private boolean mIsRewardedVideo = false;
    @Nullable
    private VastCompanionAdConfig mLandscapeVastCompanionAdConfig;
    @Nullable
    private String mNetworkMediaFileUrl;
    @NonNull
    private final ArrayList<VastTracker> mPauseTrackers = new ArrayList();
    @Nullable
    private VastCompanionAdConfig mPortraitVastCompanionAdConfig;
    @NonNull
    private final ArrayList<VastTracker> mResumeTrackers = new ArrayList();
    @Nullable
    private String mSkipOffset;
    @NonNull
    private final ArrayList<VastTracker> mSkipTrackers = new ArrayList();
    @NonNull
    private Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds = new HashMap();
    @Nullable
    private VastIconConfig mVastIconConfig;
    @Nullable
    private VideoViewabilityTracker mVideoViewabilityTracker;

    public void setDspCreativeId(@NonNull String str) {
        this.mDspCreativeId = str;
    }

    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    public void addImpressionTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "impressionTrackers cannot be null");
        this.mImpressionTrackers.addAll(list);
    }

    public void addFractionalTrackers(@NonNull List<VastFractionalProgressTracker> list) {
        Preconditions.checkNotNull(list, "fractionalTrackers cannot be null");
        this.mFractionalTrackers.addAll(list);
        Collections.sort(this.mFractionalTrackers);
    }

    public void addAbsoluteTrackers(@NonNull List<VastAbsoluteProgressTracker> list) {
        Preconditions.checkNotNull(list, "absoluteTrackers cannot be null");
        this.mAbsoluteTrackers.addAll(list);
        Collections.sort(this.mAbsoluteTrackers);
    }

    public void addCompleteTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "completeTrackers cannot be null");
        this.mCompleteTrackers.addAll(list);
    }

    public void addPauseTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "pauseTrackers cannot be null");
        this.mPauseTrackers.addAll(list);
    }

    public void addResumeTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "resumeTrackers cannot be null");
        this.mResumeTrackers.addAll(list);
    }

    public void addCloseTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "closeTrackers cannot be null");
        this.mCloseTrackers.addAll(list);
    }

    public void addSkipTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "skipTrackers cannot be null");
        this.mSkipTrackers.addAll(list);
    }

    public void addClickTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "clickTrackers cannot be null");
        this.mClickTrackers.addAll(list);
    }

    public void addErrorTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "errorTrackers cannot be null");
        this.mErrorTrackers.addAll(list);
    }

    public void setClickThroughUrl(@Nullable String str) {
        this.mClickThroughUrl = str;
    }

    public void setNetworkMediaFileUrl(@Nullable String str) {
        this.mNetworkMediaFileUrl = str;
    }

    public void setDiskMediaFileUrl(@Nullable String str) {
        this.mDiskMediaFileUrl = str;
    }

    public void setVastCompanionAd(@Nullable VastCompanionAdConfig vastCompanionAdConfig, @Nullable VastCompanionAdConfig vastCompanionAdConfig2) {
        this.mLandscapeVastCompanionAdConfig = vastCompanionAdConfig;
        this.mPortraitVastCompanionAdConfig = vastCompanionAdConfig2;
    }

    public void setSocialActionsCompanionAds(@NonNull Map<String, VastCompanionAdConfig> map) {
        this.mSocialActionsCompanionAds = map;
    }

    public void setVastIconConfig(@Nullable VastIconConfig vastIconConfig) {
        this.mVastIconConfig = vastIconConfig;
    }

    public void setCustomCtaText(@Nullable String str) {
        if (str != null) {
            this.mCustomCtaText = str;
        }
    }

    public void setCustomSkipText(@Nullable String str) {
        if (str != null) {
            this.mCustomSkipText = str;
        }
    }

    public void setCustomCloseIconUrl(@Nullable String str) {
        if (str != null) {
            this.mCustomCloseIconUrl = str;
        }
    }

    public void setCustomForceOrientation(@Nullable DeviceUtils$ForceOrientation deviceUtils$ForceOrientation) {
        if (deviceUtils$ForceOrientation != null && deviceUtils$ForceOrientation != DeviceUtils$ForceOrientation.UNDEFINED) {
            this.mCustomForceOrientation = deviceUtils$ForceOrientation;
            this.mIsForceOrientationSet = true;
        }
    }

    public void setSkipOffset(@Nullable String str) {
        if (str != null) {
            this.mSkipOffset = str;
        }
    }

    public void setVideoViewabilityTracker(@Nullable VideoViewabilityTracker videoViewabilityTracker) {
        if (videoViewabilityTracker != null) {
            this.mVideoViewabilityTracker = videoViewabilityTracker;
        }
    }

    public void setIsRewardedVideo(boolean z) {
        this.mIsRewardedVideo = z;
    }

    @NonNull
    public List<VastTracker> getImpressionTrackers() {
        return this.mImpressionTrackers;
    }

    @NonNull
    public ArrayList<VastAbsoluteProgressTracker> getAbsoluteTrackers() {
        return this.mAbsoluteTrackers;
    }

    @NonNull
    public ArrayList<VastFractionalProgressTracker> getFractionalTrackers() {
        return this.mFractionalTrackers;
    }

    @NonNull
    public List<VastTracker> getPauseTrackers() {
        return this.mPauseTrackers;
    }

    @NonNull
    public List<VastTracker> getResumeTrackers() {
        return this.mResumeTrackers;
    }

    @NonNull
    public List<VastTracker> getCompleteTrackers() {
        return this.mCompleteTrackers;
    }

    @NonNull
    public List<VastTracker> getCloseTrackers() {
        return this.mCloseTrackers;
    }

    @NonNull
    public List<VastTracker> getSkipTrackers() {
        return this.mSkipTrackers;
    }

    @NonNull
    public List<VastTracker> getClickTrackers() {
        return this.mClickTrackers;
    }

    @NonNull
    public List<VastTracker> getErrorTrackers() {
        return this.mErrorTrackers;
    }

    @Nullable
    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    @Nullable
    public String getNetworkMediaFileUrl() {
        return this.mNetworkMediaFileUrl;
    }

    @Nullable
    public String getDiskMediaFileUrl() {
        return this.mDiskMediaFileUrl;
    }

    @Nullable
    public VastCompanionAdConfig getVastCompanionAd(int i) {
        switch (i) {
            case 1:
                return this.mPortraitVastCompanionAdConfig;
            case 2:
                return this.mLandscapeVastCompanionAdConfig;
            default:
                return this.mLandscapeVastCompanionAdConfig;
        }
    }

    @NonNull
    public Map<String, VastCompanionAdConfig> getSocialActionsCompanionAds() {
        return this.mSocialActionsCompanionAds;
    }

    @Nullable
    public VastIconConfig getVastIconConfig() {
        return this.mVastIconConfig;
    }

    @Nullable
    public String getCustomCtaText() {
        return this.mCustomCtaText;
    }

    @Nullable
    public String getCustomSkipText() {
        return this.mCustomSkipText;
    }

    @Nullable
    public String getCustomCloseIconUrl() {
        return this.mCustomCloseIconUrl;
    }

    @Nullable
    public VideoViewabilityTracker getVideoViewabilityTracker() {
        return this.mVideoViewabilityTracker;
    }

    public boolean isCustomForceOrientationSet() {
        return this.mIsForceOrientationSet;
    }

    public boolean hasCompanionAd() {
        return (this.mLandscapeVastCompanionAdConfig == null || this.mPortraitVastCompanionAdConfig == null) ? false : true;
    }

    @NonNull
    public DeviceUtils$ForceOrientation getCustomForceOrientation() {
        return this.mCustomForceOrientation;
    }

    @Nullable
    public String getSkipOffsetString() {
        return this.mSkipOffset;
    }

    public boolean isRewardedVideo() {
        return this.mIsRewardedVideo;
    }

    public void handleImpression(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mImpressionTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleClickForResult(@NonNull Activity activity, int i, int i2) {
        handleClick(activity, i, Integer.valueOf(i2));
    }

    public void handleClickWithoutResult(@NonNull Context context, int i) {
        handleClick(context.getApplicationContext(), i, null);
    }

    private void handleClick(@NonNull final Context context, int i, @Nullable final Integer num) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mClickTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
        if (!TextUtils.isEmpty(this.mClickThroughUrl)) {
            new Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new ResultActions() {
                public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                    if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
                        bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, VastVideoConfig.this.mDspCreativeId);
                        Class cls = MoPubBrowser.class;
                        Intent startActivityIntent = Intents.getStartActivityIntent(context, cls, bundle);
                        try {
                            if (context instanceof Activity) {
                                Preconditions.checkNotNull(num);
                                ((Activity) context).startActivityForResult(startActivityIntent, num.intValue());
                                return;
                            }
                            Intents.startActivity(context, startActivityIntent);
                        } catch (ActivityNotFoundException e) {
                            MoPubLog.d("Activity " + cls.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
                        } catch (IntentNotResolvableException e2) {
                            MoPubLog.d("Activity " + cls.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
                        }
                    }
                }

                public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
                }
            }).withoutMoPubBrowser().build().handleUrl(context, this.mClickThroughUrl);
        }
    }

    public void handleResume(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mResumeTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handlePause(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mPauseTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleClose(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCloseTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
        TrackingRequest.makeVastTrackingHttpRequest(this.mSkipTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleComplete(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCompleteTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleError(@NonNull Context context, @Nullable VastErrorCode vastErrorCode, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mErrorTrackers, vastErrorCode, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    @NonNull
    public List<VastTracker> getUntriggeredTrackersBefore(int i, int i2) {
        int i3 = 0;
        if (!NoThrow.checkArgument(i2 > 0)) {
            return Collections.emptyList();
        }
        float f = ((float) i) / ((float) i2);
        List<VastTracker> arrayList = new ArrayList();
        VastAbsoluteProgressTracker vastAbsoluteProgressTracker = new VastAbsoluteProgressTracker("", i);
        int size = this.mAbsoluteTrackers.size();
        for (int i4 = 0; i4 < size; i4++) {
            VastAbsoluteProgressTracker vastAbsoluteProgressTracker2 = (VastAbsoluteProgressTracker) this.mAbsoluteTrackers.get(i4);
            if (vastAbsoluteProgressTracker2.compareTo(vastAbsoluteProgressTracker) > 0) {
                break;
            }
            if (!vastAbsoluteProgressTracker2.isTracked()) {
                arrayList.add(vastAbsoluteProgressTracker2);
            }
        }
        VastFractionalProgressTracker vastFractionalProgressTracker = new VastFractionalProgressTracker("", f);
        int size2 = this.mFractionalTrackers.size();
        while (i3 < size2) {
            VastFractionalProgressTracker vastFractionalProgressTracker2 = (VastFractionalProgressTracker) this.mFractionalTrackers.get(i3);
            if (vastFractionalProgressTracker2.compareTo(vastFractionalProgressTracker) > 0) {
                break;
            }
            if (!vastFractionalProgressTracker2.isTracked()) {
                arrayList.add(vastFractionalProgressTracker2);
            }
            i3++;
        }
        return arrayList;
    }

    public int getRemainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(Integer.MAX_VALUE, Integer.MAX_VALUE).size();
    }

    @Nullable
    public Integer getSkipOffsetMillis(int i) {
        Integer num = null;
        if (this.mSkipOffset == null) {
            return num;
        }
        try {
            Integer parseAbsoluteOffset;
            if (Strings.isAbsoluteTracker(this.mSkipOffset)) {
                parseAbsoluteOffset = Strings.parseAbsoluteOffset(this.mSkipOffset);
            } else if (Strings.isPercentageTracker(this.mSkipOffset)) {
                parseAbsoluteOffset = Integer.valueOf(Math.round((Float.parseFloat(this.mSkipOffset.replace("%", "")) / 100.0f) * ((float) i)));
            } else {
                MoPubLog.d(String.format("Invalid VAST skipoffset format: %s", new Object[]{this.mSkipOffset}));
                return num;
            }
            if (parseAbsoluteOffset == null) {
                return num;
            }
            if (parseAbsoluteOffset.intValue() < i) {
                return parseAbsoluteOffset;
            }
            return Integer.valueOf(i);
        } catch (NumberFormatException e) {
            MoPubLog.d(String.format("Failed to parse skipoffset %s", new Object[]{this.mSkipOffset}));
            return num;
        }
    }
}
