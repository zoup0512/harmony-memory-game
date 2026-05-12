package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import java.io.Serializable;
import java.util.Map;

public class VastVideoViewController extends BaseVideoViewController {
    static final String CURRENT_POSITION = "current_position";
    static final int DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON = 5000;
    static final int MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON = 16000;
    static final String RESUMED_VAST_CONFIG = "resumed_vast_config";
    private static final int SEEKER_POSITION_NOT_INITIALIZED = -1;
    static final String VAST_VIDEO_CONFIG = "vast_video_config";
    private static final long VIDEO_COUNTDOWN_UPDATE_INTERVAL = 250;
    private static final long VIDEO_PROGRESS_TIMER_CHECKER_DELAY = 50;
    public static final int WEBVIEW_PADDING = 16;
    public static boolean mShowingSkippable = true;
    @NonNull
    private View mAdsByView;
    @NonNull
    private ImageView mBlurredLastVideoFrameImageView;
    @NonNull
    private VastVideoGradientStripWidget mBottomGradientStripWidget;
    @NonNull
    private final OnTouchListener mClickThroughListener;
    @NonNull
    private VastVideoCloseButtonWidget mCloseButtonWidget;
    @NonNull
    private final VastVideoViewCountdownRunnable mCountdownRunnable;
    @NonNull
    private VastVideoCtaButtonWidget mCtaButtonWidget;
    private int mDuration;
    private boolean mHasSkipOffset = false;
    private boolean mHasSocialActions = false;
    @NonNull
    private final View mIconView;
    private boolean mIsCalibrationDone = false;
    private boolean mIsClosing = false;
    private boolean mIsVideoFinishedPlaying;
    @NonNull
    private final View mLandscapeCompanionAdView;
    @NonNull
    private final View mPortraitCompanionAdView;
    @NonNull
    private VastVideoProgressBarWidget mProgressBarWidget;
    @NonNull
    private final VastVideoViewProgressRunnable mProgressCheckerRunnable;
    @NonNull
    private VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private int mSeekerPositionOnPause = -1;
    private int mShowCloseButtonDelay = DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON;
    private boolean mShowCloseButtonEventFired;
    @NonNull
    private final Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds;
    @NonNull
    private final View mSocialActionsView;
    @NonNull
    private VastVideoGradientStripWidget mTopGradientStripWidget;
    @Nullable
    private VastCompanionAdConfig mVastCompanionAdConfig;
    @Nullable
    private final VastIconConfig mVastIconConfig;
    private final VastVideoConfig mVastVideoConfig;
    private boolean mVideoError;
    @NonNull
    private final VastVideoView mVideoView;

    VastVideoViewController(Activity activity, Bundle bundle, @Nullable Bundle bundle2, long j, BaseVideoViewControllerListener baseVideoViewControllerListener) {
        super(activity, Long.valueOf(j), baseVideoViewControllerListener);
        Serializable serializable = null;
        if (bundle2 != null) {
            serializable = bundle2.getSerializable(RESUMED_VAST_CONFIG);
        }
        Serializable serializable2 = bundle.getSerializable(VAST_VIDEO_CONFIG);
        if (serializable != null && (serializable instanceof VastVideoConfig)) {
            this.mVastVideoConfig = (VastVideoConfig) serializable;
            this.mSeekerPositionOnPause = bundle2.getInt(CURRENT_POSITION, -1);
        } else if (serializable2 == null || !(serializable2 instanceof VastVideoConfig)) {
            throw new IllegalStateException("VastVideoConfig is invalid");
        } else {
            this.mVastVideoConfig = (VastVideoConfig) serializable2;
        }
        if (this.mVastVideoConfig.getDiskMediaFileUrl() == null) {
            throw new IllegalStateException("VastVideoConfig does not have a video disk path");
        }
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(activity.getResources().getConfiguration().orientation);
        this.mSocialActionsCompanionAds = this.mVastVideoConfig.getSocialActionsCompanionAds();
        this.mVastIconConfig = this.mVastVideoConfig.getVastIconConfig();
        this.mClickThroughListener = new 1(this, activity);
        getLayout().setBackgroundColor(-16777216);
        addBlurredLastVideoFrameImageView(activity, 4);
        this.mVideoView = createVideoView(activity, 0);
        this.mVideoView.requestFocus();
        this.mLandscapeCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(2), 4);
        this.mPortraitCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(1), 4);
        addTopGradientStripWidget(activity);
        addProgressBarWidget(activity, 4);
        addBottomGradientStripWidget(activity);
        addRadialCountdownWidget(activity, 4);
        this.mIconView = createIconView(activity, this.mVastIconConfig, 4);
        this.mIconView.getViewTreeObserver().addOnGlobalLayoutListener(new 2(this, activity));
        addCtaButtonWidget(activity);
        Context context = activity;
        this.mSocialActionsView = createSocialActionsView(context, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.SOCIAL_ACTIONS_AD_SLOT_ID), Dips.dipsToIntPixels(38.0f, activity), 6, this.mCtaButtonWidget, 4, 16);
        addCloseButtonWidget(activity, 8);
        Handler handler = new Handler(Looper.getMainLooper());
        this.mProgressCheckerRunnable = new VastVideoViewProgressRunnable(this, this.mVastVideoConfig, handler);
        this.mCountdownRunnable = new VastVideoViewCountdownRunnable(this, handler);
    }

    @VisibleForTesting
    View createAdsByView(Activity activity) {
        return createSocialActionsView(activity, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.ADS_BY_AD_SLOT_ID), this.mIconView.getHeight(), 1, this.mIconView, 0, 6);
    }

    @Deprecated
    @VisibleForTesting
    boolean getHasSocialActions() {
        return this.mHasSocialActions;
    }

    @Deprecated
    @VisibleForTesting
    View getSocialActionsView() {
        return this.mSocialActionsView;
    }

    protected VideoView getVideoView() {
        return this.mVideoView;
    }

    protected void onCreate() {
        super.onCreate();
        switch (11.$SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[this.mVastVideoConfig.getCustomForceOrientation().ordinal()]) {
            case 1:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(1);
                break;
            case 2:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(0);
                break;
        }
        this.mVastVideoConfig.handleImpression(getContext(), getCurrentPosition());
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
    }

    protected void onResume() {
        startRunnables();
        if (this.mSeekerPositionOnPause > 0) {
            this.mVideoView.seekTo(this.mSeekerPositionOnPause);
        }
        if (!this.mIsVideoFinishedPlaying) {
            this.mVideoView.start();
        }
        if (this.mSeekerPositionOnPause != -1) {
            this.mVastVideoConfig.handleResume(getContext(), this.mSeekerPositionOnPause);
        }
    }

    protected void onPause() {
        stopRunnables();
        this.mSeekerPositionOnPause = getCurrentPosition();
        this.mVideoView.pause();
        if (!this.mIsVideoFinishedPlaying && !this.mIsClosing) {
            this.mVastVideoConfig.handlePause(getContext(), this.mSeekerPositionOnPause);
        }
    }

    protected void onDestroy() {
        stopRunnables();
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
        this.mVideoView.onDestroy();
    }

    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putInt(CURRENT_POSITION, this.mSeekerPositionOnPause);
        bundle.putSerializable(RESUMED_VAST_CONFIG, this.mVastVideoConfig);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        int i = getContext().getResources().getConfiguration().orientation;
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(i);
        if (this.mLandscapeCompanionAdView.getVisibility() == 0 || this.mPortraitCompanionAdView.getVisibility() == 0) {
            if (i == 1) {
                this.mLandscapeCompanionAdView.setVisibility(4);
                this.mPortraitCompanionAdView.setVisibility(0);
            } else {
                this.mPortraitCompanionAdView.setVisibility(4);
                this.mLandscapeCompanionAdView.setVisibility(0);
            }
            if (this.mVastCompanionAdConfig != null) {
                this.mVastCompanionAdConfig.handleImpression(getContext(), this.mDuration);
            }
        }
    }

    protected void onBackPressed() {
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    private void adjustSkipOffset() {
        int duration = getDuration();
        if (this.mVastVideoConfig.isRewardedVideo()) {
            this.mShowCloseButtonDelay = duration;
            return;
        }
        if (duration < MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON) {
            this.mShowCloseButtonDelay = duration;
        }
        Integer skipOffsetMillis = this.mVastVideoConfig.getSkipOffsetMillis(duration);
        if (skipOffsetMillis != null) {
            this.mShowCloseButtonDelay = skipOffsetMillis.intValue();
            this.mHasSkipOffset = true;
        }
        if (mShowingSkippable) {
            this.mShowCloseButtonDelay = DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON;
            this.mHasSkipOffset = true;
            return;
        }
        this.mShowCloseButtonDelay = duration;
        this.mHasSkipOffset = false;
    }

    private VastVideoView createVideoView(@NonNull Context context, int i) {
        if (this.mVastVideoConfig.getDiskMediaFileUrl() == null) {
            throw new IllegalStateException("VastVideoConfig does not have a video disk path");
        }
        VastVideoView vastVideoView = new VastVideoView(context);
        vastVideoView.setId((int) Utils.generateUniqueId());
        vastVideoView.setOnPreparedListener(new 3(this, vastVideoView));
        vastVideoView.setOnTouchListener(this.mClickThroughListener);
        vastVideoView.setOnCompletionListener(new 4(this, vastVideoView, context));
        vastVideoView.setOnErrorListener(new 5(this, vastVideoView));
        vastVideoView.setVideoPath(this.mVastVideoConfig.getDiskMediaFileUrl());
        vastVideoView.setVisibility(i);
        return vastVideoView;
    }

    private void addTopGradientStripWidget(@NonNull Context context) {
        this.mTopGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.TOP_BOTTOM, this.mVastVideoConfig.getCustomForceOrientation(), this.mVastCompanionAdConfig != null, 0, 6, getLayout().getId());
        getLayout().addView(this.mTopGradientStripWidget);
    }

    private void addBottomGradientStripWidget(@NonNull Context context) {
        this.mBottomGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.BOTTOM_TOP, this.mVastVideoConfig.getCustomForceOrientation(), this.mVastCompanionAdConfig != null, 8, 2, this.mProgressBarWidget.getId());
        getLayout().addView(this.mBottomGradientStripWidget);
    }

    private void addProgressBarWidget(@NonNull Context context, int i) {
        this.mProgressBarWidget = new VastVideoProgressBarWidget(context);
        this.mProgressBarWidget.setAnchorId(this.mVideoView.getId());
        this.mProgressBarWidget.setVisibility(i);
        getLayout().addView(this.mProgressBarWidget);
    }

    private void addRadialCountdownWidget(@NonNull Context context, int i) {
        this.mRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget.setVisibility(i);
        getLayout().addView(this.mRadialCountdownWidget);
    }

    private void addCtaButtonWidget(@NonNull Context context) {
        boolean z = true;
        boolean z2 = this.mVastCompanionAdConfig != null;
        if (TextUtils.isEmpty(this.mVastVideoConfig.getClickThroughUrl())) {
            z = false;
        }
        this.mCtaButtonWidget = new VastVideoCtaButtonWidget(context, this.mVideoView.getId(), z2, z);
        getLayout().addView(this.mCtaButtonWidget);
        this.mCtaButtonWidget.setOnTouchListener(this.mClickThroughListener);
        String customCtaText = this.mVastVideoConfig.getCustomCtaText();
        if (customCtaText != null) {
            this.mCtaButtonWidget.updateCtaText(customCtaText);
        }
    }

    private void addCloseButtonWidget(@NonNull Context context, int i) {
        this.mCloseButtonWidget = new VastVideoCloseButtonWidget(context);
        this.mCloseButtonWidget.setVisibility(i);
        getLayout().addView(this.mCloseButtonWidget);
        this.mCloseButtonWidget.setOnTouchListenerToContent(new 6(this));
        String customSkipText = this.mVastVideoConfig.getCustomSkipText();
        if (customSkipText != null) {
            this.mCloseButtonWidget.updateCloseButtonText(customSkipText);
        }
        customSkipText = this.mVastVideoConfig.getCustomCloseIconUrl();
        if (customSkipText != null) {
            this.mCloseButtonWidget.updateCloseButtonIcon(customSkipText);
        }
    }

    private void addBlurredLastVideoFrameImageView(@NonNull Context context, int i) {
        this.mBlurredLastVideoFrameImageView = new ImageView(context);
        this.mBlurredLastVideoFrameImageView.setVisibility(i);
        getLayout().addView(this.mBlurredLastVideoFrameImageView, new LayoutParams(-1, -1));
    }

    @NonNull
    @VisibleForTesting
    View createCompanionAdView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int i) {
        Preconditions.checkNotNull(context);
        if (vastCompanionAdConfig == null) {
            View view = new View(context);
            view.setVisibility(4);
            return view;
        }
        View relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        getLayout().addView(relativeLayout, new LayoutParams(-1, -1));
        view = createCompanionVastWebView(context, vastCompanionAdConfig);
        view.setVisibility(i);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getWidth() + 16), context), Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getHeight() + 16), context));
        layoutParams.addRule(13, -1);
        relativeLayout.addView(view, layoutParams);
        return view;
    }

    @NonNull
    @VisibleForTesting
    View createSocialActionsView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int i, int i2, @NonNull View view, int i3, int i4) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(view);
        if (vastCompanionAdConfig == null) {
            View view2 = new View(context);
            view2.setVisibility(4);
            return view2;
        }
        this.mHasSocialActions = true;
        this.mCtaButtonWidget.setHasSocialActions(this.mHasSocialActions);
        view2 = createCompanionVastWebView(context, vastCompanionAdConfig);
        int dipsToIntPixels = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getWidth(), context);
        int dipsToIntPixels2 = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getHeight(), context);
        int i5 = (i - dipsToIntPixels2) / 2;
        int dipsToIntPixels3 = Dips.dipsToIntPixels((float) i4, context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(dipsToIntPixels, dipsToIntPixels2);
        layoutParams.addRule(i2, view.getId());
        layoutParams.addRule(6, view.getId());
        layoutParams.setMargins(dipsToIntPixels3, i5, 0, 0);
        View relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(16);
        relativeLayout.addView(view2, new LayoutParams(-2, -2));
        getLayout().addView(relativeLayout, layoutParams);
        view2.setVisibility(i3);
        return view2;
    }

    @NonNull
    @VisibleForTesting
    View createIconView(@NonNull Context context, @Nullable VastIconConfig vastIconConfig, int i) {
        Preconditions.checkNotNull(context);
        if (vastIconConfig == null) {
            return new View(context);
        }
        View createView = VastWebView.createView(context, vastIconConfig.getVastResource());
        createView.setVastWebViewClickListener(new 7(this, vastIconConfig, context));
        createView.setWebViewClient(new 8(this, vastIconConfig));
        createView.setVisibility(i);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(Dips.asIntPixels((float) vastIconConfig.getWidth(), context), Dips.asIntPixels((float) vastIconConfig.getHeight(), context));
        layoutParams.setMargins(Dips.dipsToIntPixels(12.0f, context), Dips.dipsToIntPixels(12.0f, context), 0, 0);
        getLayout().addView(createView, layoutParams);
        return createView;
    }

    int getDuration() {
        return this.mVideoView.getDuration();
    }

    int getCurrentPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    void makeVideoInteractable() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseButtonWidget.setVisibility(0);
        this.mCtaButtonWidget.notifyVideoSkippable();
        this.mSocialActionsView.setVisibility(0);
    }

    boolean shouldBeInteractable() {
        return !this.mShowCloseButtonEventFired && getCurrentPosition() >= this.mShowCloseButtonDelay;
    }

    void updateCountdown() {
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, getCurrentPosition());
        }
    }

    void updateProgressBar() {
        this.mProgressBarWidget.updateProgress(getCurrentPosition());
    }

    String getNetworkMediaFileUrl() {
        if (this.mVastVideoConfig == null) {
            return null;
        }
        return this.mVastVideoConfig.getNetworkMediaFileUrl();
    }

    void handleIconDisplay(int i) {
        if (this.mVastIconConfig != null && i >= this.mVastIconConfig.getOffsetMS()) {
            this.mIconView.setVisibility(0);
            this.mVastIconConfig.handleImpression(getContext(), i, getNetworkMediaFileUrl());
            if (this.mVastIconConfig.getDurationMS() != null && i >= this.mVastIconConfig.getOffsetMS() + this.mVastIconConfig.getDurationMS().intValue()) {
                this.mIconView.setVisibility(8);
            }
        }
    }

    private boolean shouldAllowClickThrough() {
        return this.mShowCloseButtonEventFired;
    }

    private void startRunnables() {
        this.mProgressCheckerRunnable.startRepeating(VIDEO_PROGRESS_TIMER_CHECKER_DELAY);
        this.mCountdownRunnable.startRepeating(VIDEO_COUNTDOWN_UPDATE_INTERVAL);
    }

    private void stopRunnables() {
        this.mProgressCheckerRunnable.stop();
        this.mCountdownRunnable.stop();
    }

    @NonNull
    private VastWebView createCompanionVastWebView(@NonNull Context context, @NonNull VastCompanionAdConfig vastCompanionAdConfig) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastCompanionAdConfig);
        VastWebView createView = VastWebView.createView(context, vastCompanionAdConfig.getVastResource());
        createView.setVastWebViewClickListener(new 9(this, vastCompanionAdConfig, context));
        createView.setWebViewClient(new 10(this, vastCompanionAdConfig, context));
        return createView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoViewProgressRunnable getProgressCheckerRunnable() {
        return this.mProgressCheckerRunnable;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoViewCountdownRunnable getCountdownRunnable() {
        return this.mCountdownRunnable;
    }

    @Deprecated
    @VisibleForTesting
    boolean getHasSkipOffset() {
        return this.mHasSkipOffset;
    }

    @Deprecated
    @VisibleForTesting
    int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    @Deprecated
    @VisibleForTesting
    boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    @Deprecated
    @VisibleForTesting
    void setCloseButtonVisible(boolean z) {
        this.mShowCloseButtonEventFired = z;
    }

    @Deprecated
    @VisibleForTesting
    boolean isVideoFinishedPlaying() {
        return this.mIsVideoFinishedPlaying;
    }

    @Deprecated
    @VisibleForTesting
    boolean isCalibrationDone() {
        return this.mIsCalibrationDone;
    }

    @Deprecated
    @VisibleForTesting
    View getLandscapeCompanionAdView() {
        return this.mLandscapeCompanionAdView;
    }

    @Deprecated
    @VisibleForTesting
    View getPortraitCompanionAdView() {
        return this.mPortraitCompanionAdView;
    }

    @Deprecated
    @VisibleForTesting
    boolean getVideoError() {
        return this.mVideoError;
    }

    @Deprecated
    @VisibleForTesting
    void setVideoError() {
        this.mVideoError = true;
    }

    @Deprecated
    @VisibleForTesting
    View getIconView() {
        return this.mIconView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoGradientStripWidget getTopGradientStripWidget() {
        return this.mTopGradientStripWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoGradientStripWidget getBottomGradientStripWidget() {
        return this.mBottomGradientStripWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoProgressBarWidget getProgressBarWidget() {
        return this.mProgressBarWidget;
    }

    @Deprecated
    @VisibleForTesting
    void setProgressBarWidget(@NonNull VastVideoProgressBarWidget vastVideoProgressBarWidget) {
        this.mProgressBarWidget = vastVideoProgressBarWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        return this.mRadialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    void setRadialCountdownWidget(@NonNull VastVideoRadialCountdownWidget vastVideoRadialCountdownWidget) {
        this.mRadialCountdownWidget = vastVideoRadialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoCtaButtonWidget getCtaButtonWidget() {
        return this.mCtaButtonWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoCloseButtonWidget getCloseButtonWidget() {
        return this.mCloseButtonWidget;
    }

    @Deprecated
    @VisibleForTesting
    ImageView getBlurredLastVideoFrameImageView() {
        return this.mBlurredLastVideoFrameImageView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoView getVastVideoView() {
        return this.mVideoView;
    }

    @Deprecated
    @VisibleForTesting
    void setIsClosing(boolean z) {
        this.mIsClosing = z;
    }
}
