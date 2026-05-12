package com.mopub.nativeads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;
import com.yalantis.ucrop.view.CropImageView;

@TargetApi(16)
public class MediaLayout extends RelativeLayout {
    private static final float ASPECT_MULTIPLIER_HEIGHT_TO_WIDTH = 1.7777778f;
    private static final float ASPECT_MULTIPLIER_WIDTH_TO_HEIGHT = 0.5625f;
    private static final int CONTROL_SIZE_DIPS = 40;
    private static final int GRADIENT_STRIP_HEIGHT_DIPS = 35;
    private static final int MUTE_SIZE_DIPS = 36;
    private static final int PINNER_PADDING_DIPS = 10;
    @Nullable
    private ImageView mBottomGradient;
    private final int mControlSizePx;
    private final int mGradientStripHeightPx;
    private boolean mIsInitialized;
    @Nullable
    private ProgressBar mLoadingSpinner;
    @NonNull
    private ImageView mMainImageView;
    @NonNull
    private volatile Mode mMode;
    @Nullable
    private ImageView mMuteControl;
    private final int mMuteSizePx;
    @NonNull
    private MuteState mMuteState;
    @Nullable
    private Drawable mMutedDrawable;
    @Nullable
    private View mOverlay;
    private final int mPaddingPx;
    @Nullable
    private ImageView mPlayButton;
    @Nullable
    private ImageView mTopGradient;
    @Nullable
    private Drawable mUnmutedDrawable;
    @Nullable
    private VastVideoProgressBarWidget mVideoProgress;
    @Nullable
    private TextureView mVideoTextureView;

    public enum Mode {
        IMAGE,
        PLAYING,
        LOADING,
        BUFFERING,
        PAUSED,
        FINISHED
    }

    public enum MuteState {
        MUTED,
        UNMUTED
    }

    public MediaLayout(@NonNull Context context) {
        this(context, null);
    }

    public MediaLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMode = Mode.IMAGE;
        Preconditions.checkNotNull(context);
        this.mMuteState = MuteState.MUTED;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mMainImageView = new ImageView(context);
        this.mMainImageView.setLayoutParams(layoutParams);
        this.mMainImageView.setScaleType(ScaleType.CENTER_CROP);
        addView(this.mMainImageView);
        this.mControlSizePx = Dips.asIntPixels(40.0f, context);
        this.mGradientStripHeightPx = Dips.asIntPixels(35.0f, context);
        this.mMuteSizePx = Dips.asIntPixels(36.0f, context);
        this.mPaddingPx = Dips.asIntPixels(CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER, context);
    }

    public void setSurfaceTextureListener(@Nullable SurfaceTextureListener surfaceTextureListener) {
        if (this.mVideoTextureView != null) {
            this.mVideoTextureView.setSurfaceTextureListener(surfaceTextureListener);
            SurfaceTexture surfaceTexture = this.mVideoTextureView.getSurfaceTexture();
            if (surfaceTexture != null && surfaceTextureListener != null) {
                surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mVideoTextureView.getWidth(), this.mVideoTextureView.getHeight());
            }
        }
    }

    public void initForVideo() {
        if (!this.mIsInitialized) {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.mVideoTextureView = new TextureView(getContext());
            this.mVideoTextureView.setLayoutParams(layoutParams);
            this.mVideoTextureView.setId((int) Utils.generateUniqueId());
            addView(this.mVideoTextureView);
            this.mMainImageView.bringToFront();
            layoutParams = new RelativeLayout.LayoutParams(this.mControlSizePx, this.mControlSizePx);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.mLoadingSpinner = new ProgressBar(getContext());
            this.mLoadingSpinner.setLayoutParams(layoutParams);
            this.mLoadingSpinner.setPadding(0, this.mPaddingPx, this.mPaddingPx, 0);
            this.mLoadingSpinner.setIndeterminate(true);
            addView(this.mLoadingSpinner);
            layoutParams = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
            layoutParams.addRule(8, this.mVideoTextureView.getId());
            this.mBottomGradient = new ImageView(getContext());
            this.mBottomGradient.setLayoutParams(layoutParams);
            this.mBottomGradient.setImageDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
            addView(this.mBottomGradient);
            layoutParams = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
            layoutParams.addRule(6, this.mVideoTextureView.getId());
            this.mTopGradient = new ImageView(getContext());
            this.mTopGradient.setLayoutParams(layoutParams);
            this.mTopGradient.setImageDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
            addView(this.mTopGradient);
            this.mVideoProgress = new VastVideoProgressBarWidget(getContext());
            this.mVideoProgress.setAnchorId(this.mVideoTextureView.getId());
            this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
            addView(this.mVideoProgress);
            this.mMutedDrawable = Drawables.NATIVE_MUTED.createDrawable(getContext());
            this.mUnmutedDrawable = Drawables.NATIVE_UNMUTED.createDrawable(getContext());
            layoutParams = new RelativeLayout.LayoutParams(this.mMuteSizePx, this.mMuteSizePx);
            layoutParams.addRule(9);
            layoutParams.addRule(2, this.mVideoProgress.getId());
            this.mMuteControl = new ImageView(getContext());
            this.mMuteControl.setLayoutParams(layoutParams);
            this.mMuteControl.setScaleType(ScaleType.CENTER_INSIDE);
            this.mMuteControl.setPadding(this.mPaddingPx, this.mPaddingPx, this.mPaddingPx, this.mPaddingPx);
            this.mMuteControl.setImageDrawable(this.mMutedDrawable);
            addView(this.mMuteControl);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.mOverlay = new View(getContext());
            this.mOverlay.setLayoutParams(layoutParams);
            this.mOverlay.setBackgroundColor(0);
            addView(this.mOverlay);
            layoutParams = new RelativeLayout.LayoutParams(this.mControlSizePx, this.mControlSizePx);
            layoutParams.addRule(13);
            this.mPlayButton = new ImageView(getContext());
            this.mPlayButton.setLayoutParams(layoutParams);
            this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(getContext()));
            addView(this.mPlayButton);
            this.mIsInitialized = true;
            updateViewState();
        }
    }

    public void reset() {
        setMode(Mode.IMAGE);
        setPlayButtonClickListener(null);
        setMuteControlClickListener(null);
        setVideoClickListener(null);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(size, measuredWidth) : measuredWidth;
        }
        mode = (int) (ASPECT_MULTIPLIER_WIDTH_TO_HEIGHT * ((float) size));
        if (mode2 != 1073741824 || size2 >= mode) {
            size2 = mode;
            mode = size;
        } else {
            mode = (int) (ASPECT_MULTIPLIER_HEIGHT_TO_WIDTH * ((float) size2));
        }
        if (Math.abs(size2 - measuredHeight) >= 2 || Math.abs(mode - measuredWidth) >= 2) {
            MoPubLog.v(String.format("Resetting mediaLayout size to w: %d h: %d", new Object[]{Integer.valueOf(mode), Integer.valueOf(size2)}));
            getLayoutParams().width = mode;
            getLayoutParams().height = size2;
        }
        super.onMeasure(i, i2);
    }

    public void setMainImageDrawable(@NonNull Drawable drawable) {
        Preconditions.checkNotNull(drawable);
        this.mMainImageView.setImageDrawable(drawable);
    }

    public void resetProgress() {
        if (this.mVideoProgress != null) {
            this.mVideoProgress.reset();
        }
    }

    public void updateProgress(int i) {
        if (this.mVideoProgress != null) {
            this.mVideoProgress.updateProgress(i);
        }
    }

    public TextureView getTextureView() {
        return this.mVideoTextureView;
    }

    public void setMode(@NonNull Mode mode) {
        Preconditions.checkNotNull(mode);
        this.mMode = mode;
        post(new Runnable() {
            public void run() {
                MediaLayout.this.updateViewState();
            }
        });
    }

    @Nullable
    public ImageView getMainImageView() {
        return this.mMainImageView;
    }

    public void setMuteControlClickListener(@Nullable OnClickListener onClickListener) {
        if (this.mMuteControl != null) {
            this.mMuteControl.setOnClickListener(onClickListener);
        }
    }

    public void setPlayButtonClickListener(@Nullable OnClickListener onClickListener) {
        if (this.mPlayButton != null && this.mOverlay != null) {
            this.mOverlay.setOnClickListener(onClickListener);
            this.mPlayButton.setOnClickListener(onClickListener);
        }
    }

    public void setVideoClickListener(@Nullable OnClickListener onClickListener) {
        if (this.mVideoTextureView != null) {
            this.mVideoTextureView.setOnClickListener(onClickListener);
        }
    }

    public void setMuteState(@NonNull MuteState muteState) {
        Preconditions.checkNotNull(muteState);
        if (muteState != this.mMuteState) {
            this.mMuteState = muteState;
            if (this.mMuteControl != null) {
                switch (this.mMuteState) {
                    case MUTED:
                        this.mMuteControl.setImageDrawable(this.mMutedDrawable);
                        return;
                    default:
                        this.mMuteControl.setImageDrawable(this.mUnmutedDrawable);
                        return;
                }
            }
        }
    }

    private void updateViewState() {
        switch (this.mMode) {
            case IMAGE:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case LOADING:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case BUFFERING:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(4);
                break;
            case PLAYING:
                break;
            case PAUSED:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(0);
                return;
            case FINISHED:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(0);
                return;
            default:
                return;
        }
        setMainImageVisibility(4);
        setLoadingSpinnerVisibility(4);
        setVideoControlVisibility(0);
        setPlayButtonVisibility(4);
    }

    private void setMainImageVisibility(int i) {
        this.mMainImageView.setVisibility(i);
    }

    private void setLoadingSpinnerVisibility(int i) {
        if (this.mLoadingSpinner != null) {
            this.mLoadingSpinner.setVisibility(i);
        }
        if (this.mTopGradient != null) {
            this.mTopGradient.setVisibility(i);
        }
    }

    private void setVideoControlVisibility(int i) {
        if (this.mBottomGradient != null) {
            this.mBottomGradient.setVisibility(i);
        }
        if (this.mVideoProgress != null) {
            this.mVideoProgress.setVisibility(i);
        }
        if (this.mMuteControl != null) {
            this.mMuteControl.setVisibility(i);
        }
    }

    private void setPlayButtonVisibility(int i) {
        if (this.mPlayButton != null && this.mOverlay != null) {
            this.mPlayButton.setVisibility(i);
            this.mOverlay.setVisibility(i);
        }
    }
}
