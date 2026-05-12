package com.mopub.nativeads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.CtaButtonDrawable;
import com.mopub.mobileads.resource.DrawableConstants;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;
import com.yalantis.ucrop.view.CropImageView;

@TargetApi(16)
public class NativeFullScreenVideoView extends RelativeLayout {
    @NonNull
    private final ImageView mBottomGradient;
    @NonNull
    private final ImageView mCachedVideoFrameView;
    @NonNull
    private final ImageView mCloseControl;
    @VisibleForTesting
    final int mCloseControlSizePx;
    @VisibleForTesting
    final int mClosePaddingPx;
    @NonNull
    private final ImageView mCtaButton;
    @VisibleForTesting
    final int mCtaHeightPx;
    @VisibleForTesting
    final int mCtaMarginPx;
    @VisibleForTesting
    final int mCtaWidthPx;
    @VisibleForTesting
    final int mGradientStripHeightPx;
    @NonNull
    private final ProgressBar mLoadingSpinner;
    @NonNull
    @VisibleForTesting
    Mode mMode;
    private int mOrientation;
    @NonNull
    private final View mOverlay;
    @NonNull
    private final ImageView mPlayButton;
    @VisibleForTesting
    final int mPlayControlSizePx;
    @NonNull
    private final ImageView mPrivacyInformationIcon;
    @VisibleForTesting
    final int mPrivacyInformationIconSizePx;
    @NonNull
    private final ImageView mTopGradient;
    @NonNull
    private final VastVideoProgressBarWidget mVideoProgress;
    @NonNull
    private final TextureView mVideoTexture;

    public NativeFullScreenVideoView(@NonNull Context context, int i, @Nullable String str) {
        this(context, i, str, new ImageView(context), new TextureView(context), new ProgressBar(context), new ImageView(context), new ImageView(context), new VastVideoProgressBarWidget(context), new View(context), new ImageView(context), new ImageView(context), new ImageView(context), new ImageView(context));
    }

    @VisibleForTesting
    NativeFullScreenVideoView(@NonNull Context context, int i, @Nullable String str, @NonNull ImageView imageView, @NonNull TextureView textureView, @NonNull ProgressBar progressBar, @NonNull ImageView imageView2, @NonNull ImageView imageView3, @NonNull VastVideoProgressBarWidget vastVideoProgressBarWidget, @NonNull View view, @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull ImageView imageView6, @NonNull ImageView imageView7) {
        super(context);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(imageView);
        Preconditions.checkNotNull(textureView);
        Preconditions.checkNotNull(progressBar);
        Preconditions.checkNotNull(imageView2);
        Preconditions.checkNotNull(imageView3);
        Preconditions.checkNotNull(vastVideoProgressBarWidget);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(imageView4);
        Preconditions.checkNotNull(imageView5);
        Preconditions.checkNotNull(imageView6);
        Preconditions.checkNotNull(imageView7);
        this.mOrientation = i;
        this.mMode = Mode.LOADING;
        this.mCtaWidthPx = Dips.asIntPixels(200.0f, context);
        this.mCtaHeightPx = Dips.asIntPixels(42.0f, context);
        this.mCtaMarginPx = Dips.asIntPixels(CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER, context);
        this.mCloseControlSizePx = Dips.asIntPixels(50.0f, context);
        this.mClosePaddingPx = Dips.asIntPixels(CloseButton.STROKE_WIDTH, context);
        this.mPrivacyInformationIconSizePx = Dips.asIntPixels(44.0f, context);
        this.mPlayControlSizePx = Dips.asIntPixels(50.0f, context);
        this.mGradientStripHeightPx = Dips.asIntPixels(45.0f, context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mVideoTexture = textureView;
        this.mVideoTexture.setId((int) Utils.generateUniqueId());
        this.mVideoTexture.setLayoutParams(layoutParams);
        addView(this.mVideoTexture);
        this.mCachedVideoFrameView = imageView;
        this.mCachedVideoFrameView.setId((int) Utils.generateUniqueId());
        this.mCachedVideoFrameView.setLayoutParams(layoutParams);
        this.mCachedVideoFrameView.setBackgroundColor(0);
        addView(this.mCachedVideoFrameView);
        layoutParams = new RelativeLayout.LayoutParams(this.mPlayControlSizePx, this.mPlayControlSizePx);
        layoutParams.addRule(13);
        this.mLoadingSpinner = progressBar;
        this.mLoadingSpinner.setId((int) Utils.generateUniqueId());
        this.mLoadingSpinner.setBackground(new LoadingBackground(context));
        this.mLoadingSpinner.setLayoutParams(layoutParams);
        this.mLoadingSpinner.setIndeterminate(true);
        addView(this.mLoadingSpinner);
        layoutParams = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
        layoutParams.addRule(8, this.mVideoTexture.getId());
        this.mBottomGradient = imageView2;
        this.mBottomGradient.setId((int) Utils.generateUniqueId());
        this.mBottomGradient.setLayoutParams(layoutParams);
        this.mBottomGradient.setImageDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        addView(this.mBottomGradient);
        layoutParams = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
        layoutParams.addRule(10);
        this.mTopGradient = imageView3;
        this.mTopGradient.setId((int) Utils.generateUniqueId());
        this.mTopGradient.setLayoutParams(layoutParams);
        this.mTopGradient.setImageDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        addView(this.mTopGradient);
        this.mVideoProgress = vastVideoProgressBarWidget;
        this.mVideoProgress.setId((int) Utils.generateUniqueId());
        this.mVideoProgress.setAnchorId(this.mVideoTexture.getId());
        this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
        addView(this.mVideoProgress);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mOverlay = view;
        this.mOverlay.setId((int) Utils.generateUniqueId());
        this.mOverlay.setLayoutParams(layoutParams);
        this.mOverlay.setBackgroundColor(DrawableConstants.TRANSPARENT_GRAY);
        addView(this.mOverlay);
        layoutParams = new RelativeLayout.LayoutParams(this.mPlayControlSizePx, this.mPlayControlSizePx);
        layoutParams.addRule(13);
        this.mPlayButton = imageView4;
        this.mPlayButton.setId((int) Utils.generateUniqueId());
        this.mPlayButton.setLayoutParams(layoutParams);
        this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(context));
        addView(this.mPlayButton);
        this.mPrivacyInformationIcon = imageView5;
        this.mPrivacyInformationIcon.setId((int) Utils.generateUniqueId());
        this.mPrivacyInformationIcon.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(context));
        this.mPrivacyInformationIcon.setPadding(this.mClosePaddingPx, this.mClosePaddingPx, this.mClosePaddingPx * 2, this.mClosePaddingPx * 2);
        addView(this.mPrivacyInformationIcon);
        Drawable ctaButtonDrawable = new CtaButtonDrawable(context);
        if (!TextUtils.isEmpty(str)) {
            ctaButtonDrawable.setCtaText(str);
        }
        this.mCtaButton = imageView6;
        this.mCtaButton.setId((int) Utils.generateUniqueId());
        this.mCtaButton.setImageDrawable(ctaButtonDrawable);
        addView(this.mCtaButton);
        this.mCloseControl = imageView7;
        this.mCloseControl.setId((int) Utils.generateUniqueId());
        this.mCloseControl.setImageDrawable(new CloseButtonDrawable());
        this.mCloseControl.setPadding(this.mClosePaddingPx * 3, this.mClosePaddingPx, this.mClosePaddingPx, this.mClosePaddingPx * 3);
        addView(this.mCloseControl);
        updateViewState();
    }

    public void resetProgress() {
        this.mVideoProgress.reset();
    }

    public void setMode(@NonNull Mode mode) {
        Preconditions.checkNotNull(mode);
        if (this.mMode != mode) {
            this.mMode = mode;
            updateViewState();
        }
    }

    @NonNull
    public TextureView getTextureView() {
        return this.mVideoTexture;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            updateViewState();
        }
    }

    public void setSurfaceTextureListener(@Nullable SurfaceTextureListener surfaceTextureListener) {
        this.mVideoTexture.setSurfaceTextureListener(surfaceTextureListener);
        SurfaceTexture surfaceTexture = this.mVideoTexture.getSurfaceTexture();
        if (surfaceTexture != null && surfaceTextureListener != null) {
            surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mVideoTexture.getWidth(), this.mVideoTexture.getHeight());
        }
    }

    public void setCloseControlListener(@Nullable OnClickListener onClickListener) {
        this.mCloseControl.setOnClickListener(onClickListener);
    }

    public void setPrivacyInformationClickListener(@Nullable OnClickListener onClickListener) {
        this.mPrivacyInformationIcon.setOnClickListener(onClickListener);
    }

    public void setCtaClickListener(@Nullable OnClickListener onClickListener) {
        this.mCtaButton.setOnClickListener(onClickListener);
    }

    public void setPlayControlClickListener(@Nullable OnClickListener onClickListener) {
        this.mPlayButton.setOnClickListener(onClickListener);
        this.mOverlay.setOnClickListener(onClickListener);
    }

    public void updateProgress(int i) {
        this.mVideoProgress.updateProgress(i);
    }

    public void setCachedVideoFrame(@Nullable Bitmap bitmap) {
        this.mCachedVideoFrameView.setImageBitmap(bitmap);
    }

    private void updateViewState() {
        switch (1.$SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode[this.mMode.ordinal()]) {
            case 1:
                setCachedImageVisibility(0);
                setLoadingSpinnerVisibility(0);
                setVideoProgressVisibility(4);
                setPlayButtonVisibility(4);
                break;
            case 2:
                setCachedImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoProgressVisibility(0);
                setPlayButtonVisibility(4);
                break;
            case 3:
                setCachedImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoProgressVisibility(0);
                setPlayButtonVisibility(0);
                break;
            case 4:
                setCachedImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoProgressVisibility(4);
                setPlayButtonVisibility(0);
                break;
        }
        updateVideoTextureLayout();
        updateControlLayouts();
    }

    private void setCachedImageVisibility(int i) {
        this.mCachedVideoFrameView.setVisibility(i);
    }

    private void setLoadingSpinnerVisibility(int i) {
        this.mLoadingSpinner.setVisibility(i);
    }

    private void setVideoProgressVisibility(int i) {
        this.mVideoProgress.setVisibility(i);
    }

    private void setPlayButtonVisibility(int i) {
        this.mPlayButton.setVisibility(i);
        this.mOverlay.setVisibility(i);
    }

    private void updateVideoTextureLayout() {
        Configuration configuration = getContext().getResources().getConfiguration();
        LayoutParams layoutParams = this.mVideoTexture.getLayoutParams();
        int dipsToIntPixels = Dips.dipsToIntPixels((float) configuration.screenWidthDp, getContext());
        if (dipsToIntPixels != layoutParams.width) {
            layoutParams.width = dipsToIntPixels;
        }
        int dipsToIntPixels2 = Dips.dipsToIntPixels((((float) configuration.screenWidthDp) * 9.0f) / 16.0f, getContext());
        if (dipsToIntPixels2 != layoutParams.height) {
            layoutParams.height = dipsToIntPixels2;
        }
    }

    private void updateControlLayouts() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mCtaWidthPx, this.mCtaHeightPx);
        layoutParams.setMargins(this.mCtaMarginPx, this.mCtaMarginPx, this.mCtaMarginPx, this.mCtaMarginPx);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.mPrivacyInformationIconSizePx, this.mPrivacyInformationIconSizePx);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.mCloseControlSizePx, this.mCloseControlSizePx);
        switch (this.mOrientation) {
            case 1:
                layoutParams.addRule(3, this.mVideoTexture.getId());
                layoutParams.addRule(14);
                layoutParams2.addRule(10);
                layoutParams2.addRule(9);
                layoutParams3.addRule(10);
                layoutParams3.addRule(11);
                break;
            case 2:
                layoutParams.addRule(2, this.mVideoProgress.getId());
                layoutParams.addRule(11);
                layoutParams2.addRule(6, this.mVideoTexture.getId());
                layoutParams2.addRule(5, this.mVideoTexture.getId());
                layoutParams3.addRule(6, this.mVideoTexture.getId());
                layoutParams3.addRule(7, this.mVideoTexture.getId());
                break;
        }
        this.mCtaButton.setLayoutParams(layoutParams);
        this.mPrivacyInformationIcon.setLayoutParams(layoutParams2);
        this.mCloseControl.setLayoutParams(layoutParams3);
    }

    @Deprecated
    @VisibleForTesting
    ImageView getCtaButton() {
        return this.mCtaButton;
    }
}
