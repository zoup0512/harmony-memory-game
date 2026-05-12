package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.task.BitmapCropTask;
import com.yalantis.ucrop.util.RectUtils;
import java.util.Arrays;

public class CropImageView extends TransformImageView {
    public static final float DEFAULT_ASPECT_RATIO = 0.0f;
    public static final int DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = 500;
    public static final int DEFAULT_MAX_BITMAP_SIZE = 0;
    public static final float DEFAULT_MAX_SCALE_MULTIPLIER = 10.0f;
    public static final float SOURCE_IMAGE_ASPECT_RATIO = 0.0f;
    private CropBoundsChangeListener mCropBoundsChangeListener;
    private final RectF mCropRect;
    private long mImageToWrapCropBoundsAnimDuration;
    private int mMaxResultImageSizeX;
    private int mMaxResultImageSizeY;
    private float mMaxScale;
    private float mMaxScaleMultiplier;
    private float mMinScale;
    private float mTargetAspectRatio;
    private final Matrix mTempMatrix;
    private Runnable mWrapCropBoundsRunnable;
    private Runnable mZoomImageToPositionRunnable;

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CropImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCropRect = new RectF();
        this.mTempMatrix = new Matrix();
        this.mMaxScaleMultiplier = DEFAULT_MAX_SCALE_MULTIPLIER;
        this.mZoomImageToPositionRunnable = null;
        this.mMaxResultImageSizeX = 0;
        this.mMaxResultImageSizeY = 0;
        this.mImageToWrapCropBoundsAnimDuration = 500;
    }

    public void cropAndSaveImage(@NonNull CompressFormat compressFormat, int compressQuality, @Nullable BitmapCropCallback cropCallback) {
        cancelAllAnimations();
        setImageToWrapCropBounds(false);
        new BitmapCropTask(getViewBitmap(), new ImageState(this.mCropRect, RectUtils.trapToRect(this.mCurrentImageCorners), getCurrentScale(), getCurrentAngle()), new CropParameters(this.mMaxResultImageSizeX, this.mMaxResultImageSizeY, compressFormat, compressQuality, getImageInputPath(), getImageOutputPath(), getExifInfo()), cropCallback).execute(new Void[0]);
    }

    public float getMaxScale() {
        return this.mMaxScale;
    }

    public float getMinScale() {
        return this.mMinScale;
    }

    public float getTargetAspectRatio() {
        return this.mTargetAspectRatio;
    }

    public void setCropRect(RectF cropRect) {
        this.mCropRect.set(cropRect.left - ((float) getPaddingLeft()), cropRect.top - ((float) getPaddingTop()), cropRect.right - ((float) getPaddingRight()), cropRect.bottom - ((float) getPaddingBottom()));
        calculateImageScaleBounds();
        setImageToWrapCropBounds();
    }

    public void setTargetAspectRatio(float targetAspectRatio) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            this.mTargetAspectRatio = targetAspectRatio;
            return;
        }
        if (targetAspectRatio == 0.0f) {
            this.mTargetAspectRatio = ((float) drawable.getIntrinsicWidth()) / ((float) drawable.getIntrinsicHeight());
        } else {
            this.mTargetAspectRatio = targetAspectRatio;
        }
        if (this.mCropBoundsChangeListener != null) {
            this.mCropBoundsChangeListener.onCropAspectRatioChanged(this.mTargetAspectRatio);
        }
    }

    @Nullable
    public CropBoundsChangeListener getCropBoundsChangeListener() {
        return this.mCropBoundsChangeListener;
    }

    public void setCropBoundsChangeListener(@Nullable CropBoundsChangeListener cropBoundsChangeListener) {
        this.mCropBoundsChangeListener = cropBoundsChangeListener;
    }

    public void setMaxResultImageSizeX(@IntRange(from = 10) int maxResultImageSizeX) {
        this.mMaxResultImageSizeX = maxResultImageSizeX;
    }

    public void setMaxResultImageSizeY(@IntRange(from = 10) int maxResultImageSizeY) {
        this.mMaxResultImageSizeY = maxResultImageSizeY;
    }

    public void setImageToWrapCropBoundsAnimDuration(@IntRange(from = 100) long imageToWrapCropBoundsAnimDuration) {
        if (imageToWrapCropBoundsAnimDuration > 0) {
            this.mImageToWrapCropBoundsAnimDuration = imageToWrapCropBoundsAnimDuration;
            return;
        }
        throw new IllegalArgumentException("Animation duration cannot be negative value.");
    }

    public void setMaxScaleMultiplier(float maxScaleMultiplier) {
        this.mMaxScaleMultiplier = maxScaleMultiplier;
    }

    public void zoomOutImage(float deltaScale) {
        zoomOutImage(deltaScale, this.mCropRect.centerX(), this.mCropRect.centerY());
    }

    public void zoomOutImage(float scale, float centerX, float centerY) {
        if (scale >= getMinScale()) {
            postScale(scale / getCurrentScale(), centerX, centerY);
        }
    }

    public void zoomInImage(float deltaScale) {
        zoomInImage(deltaScale, this.mCropRect.centerX(), this.mCropRect.centerY());
    }

    public void zoomInImage(float scale, float centerX, float centerY) {
        if (scale <= getMaxScale()) {
            postScale(scale / getCurrentScale(), centerX, centerY);
        }
    }

    public void postScale(float deltaScale, float px, float py) {
        if (deltaScale > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT && getCurrentScale() * deltaScale <= getMaxScale()) {
            super.postScale(deltaScale, px, py);
        } else if (deltaScale < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT && getCurrentScale() * deltaScale >= getMinScale()) {
            super.postScale(deltaScale, px, py);
        }
    }

    public void postRotate(float deltaAngle) {
        postRotate(deltaAngle, this.mCropRect.centerX(), this.mCropRect.centerY());
    }

    public void cancelAllAnimations() {
        removeCallbacks(this.mWrapCropBoundsRunnable);
        removeCallbacks(this.mZoomImageToPositionRunnable);
    }

    public void setImageToWrapCropBounds() {
        setImageToWrapCropBounds(true);
    }

    public void setImageToWrapCropBounds(boolean animate) {
        if (this.mBitmapLaidOut && !isImageWrapCropBounds()) {
            float currentX = this.mCurrentImageCenter[0];
            float currentY = this.mCurrentImageCenter[1];
            float currentScale = getCurrentScale();
            float deltaX = this.mCropRect.centerX() - currentX;
            float deltaY = this.mCropRect.centerY() - currentY;
            float deltaScale = 0.0f;
            this.mTempMatrix.reset();
            this.mTempMatrix.setTranslate(deltaX, deltaY);
            float[] tempCurrentImageCorners = Arrays.copyOf(this.mCurrentImageCorners, this.mCurrentImageCorners.length);
            this.mTempMatrix.mapPoints(tempCurrentImageCorners);
            boolean willImageWrapCropBoundsAfterTranslate = isImageWrapCropBounds(tempCurrentImageCorners);
            if (willImageWrapCropBoundsAfterTranslate) {
                float[] imageIndents = calculateImageIndents();
                deltaX = -(imageIndents[0] + imageIndents[2]);
                deltaY = -(imageIndents[1] + imageIndents[3]);
            } else {
                RectF tempCropRect = new RectF(this.mCropRect);
                this.mTempMatrix.reset();
                this.mTempMatrix.setRotate(getCurrentAngle());
                this.mTempMatrix.mapRect(tempCropRect);
                float[] currentImageSides = RectUtils.getRectSidesFromCorners(this.mCurrentImageCorners);
                deltaScale = (Math.max(tempCropRect.width() / currentImageSides[0], tempCropRect.height() / currentImageSides[1]) * currentScale) - currentScale;
            }
            if (animate) {
                Runnable wrapCropBoundsRunnable = new WrapCropBoundsRunnable(this, this.mImageToWrapCropBoundsAnimDuration, currentX, currentY, deltaX, deltaY, currentScale, deltaScale, willImageWrapCropBoundsAfterTranslate);
                this.mWrapCropBoundsRunnable = wrapCropBoundsRunnable;
                post(wrapCropBoundsRunnable);
                return;
            }
            postTranslate(deltaX, deltaY);
            if (!willImageWrapCropBoundsAfterTranslate) {
                zoomInImage(currentScale + deltaScale, this.mCropRect.centerX(), this.mCropRect.centerY());
            }
        }
    }

    private float[] calculateImageIndents() {
        this.mTempMatrix.reset();
        this.mTempMatrix.setRotate(-getCurrentAngle());
        float[] unrotatedImageCorners = Arrays.copyOf(this.mCurrentImageCorners, this.mCurrentImageCorners.length);
        float[] unrotatedCropBoundsCorners = RectUtils.getCornersFromRect(this.mCropRect);
        this.mTempMatrix.mapPoints(unrotatedImageCorners);
        this.mTempMatrix.mapPoints(unrotatedCropBoundsCorners);
        RectF unrotatedImageRect = RectUtils.trapToRect(unrotatedImageCorners);
        RectF unrotatedCropRect = RectUtils.trapToRect(unrotatedCropBoundsCorners);
        float deltaLeft = unrotatedImageRect.left - unrotatedCropRect.left;
        float deltaTop = unrotatedImageRect.top - unrotatedCropRect.top;
        float deltaRight = unrotatedImageRect.right - unrotatedCropRect.right;
        float deltaBottom = unrotatedImageRect.bottom - unrotatedCropRect.bottom;
        float[] indents = new float[4];
        if (deltaLeft <= 0.0f) {
            deltaLeft = 0.0f;
        }
        indents[0] = deltaLeft;
        if (deltaTop <= 0.0f) {
            deltaTop = 0.0f;
        }
        indents[1] = deltaTop;
        if (deltaRight >= 0.0f) {
            deltaRight = 0.0f;
        }
        indents[2] = deltaRight;
        if (deltaBottom >= 0.0f) {
            deltaBottom = 0.0f;
        }
        indents[3] = deltaBottom;
        this.mTempMatrix.reset();
        this.mTempMatrix.setRotate(getCurrentAngle());
        this.mTempMatrix.mapPoints(indents);
        return indents;
    }

    protected void onImageLaidOut() {
        super.onImageLaidOut();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            float drawableWidth = (float) drawable.getIntrinsicWidth();
            float drawableHeight = (float) drawable.getIntrinsicHeight();
            if (this.mTargetAspectRatio == 0.0f) {
                this.mTargetAspectRatio = drawableWidth / drawableHeight;
            }
            int height = (int) (((float) this.mThisWidth) / this.mTargetAspectRatio);
            int halfDiff;
            if (height > this.mThisHeight) {
                int width = (int) (((float) this.mThisHeight) * this.mTargetAspectRatio);
                halfDiff = (this.mThisWidth - width) / 2;
                this.mCropRect.set((float) halfDiff, 0.0f, (float) (width + halfDiff), (float) this.mThisHeight);
            } else {
                halfDiff = (this.mThisHeight - height) / 2;
                this.mCropRect.set(0.0f, (float) halfDiff, (float) this.mThisWidth, (float) (height + halfDiff));
            }
            calculateImageScaleBounds(drawableWidth, drawableHeight);
            setupInitialImagePosition(drawableWidth, drawableHeight);
            if (this.mCropBoundsChangeListener != null) {
                this.mCropBoundsChangeListener.onCropAspectRatioChanged(this.mTargetAspectRatio);
            }
            if (this.mTransformImageListener != null) {
                this.mTransformImageListener.onScale(getCurrentScale());
                this.mTransformImageListener.onRotate(getCurrentAngle());
            }
        }
    }

    protected boolean isImageWrapCropBounds() {
        return isImageWrapCropBounds(this.mCurrentImageCorners);
    }

    protected boolean isImageWrapCropBounds(float[] imageCorners) {
        this.mTempMatrix.reset();
        this.mTempMatrix.setRotate(-getCurrentAngle());
        float[] unrotatedImageCorners = Arrays.copyOf(imageCorners, imageCorners.length);
        this.mTempMatrix.mapPoints(unrotatedImageCorners);
        float[] unrotatedCropBoundsCorners = RectUtils.getCornersFromRect(this.mCropRect);
        this.mTempMatrix.mapPoints(unrotatedCropBoundsCorners);
        return RectUtils.trapToRect(unrotatedImageCorners).contains(RectUtils.trapToRect(unrotatedCropBoundsCorners));
    }

    protected void zoomImageToPosition(float scale, float centerX, float centerY, long durationMs) {
        if (scale > getMaxScale()) {
            scale = getMaxScale();
        }
        float oldScale = getCurrentScale();
        Runnable zoomImageToPosition = new ZoomImageToPosition(this, durationMs, oldScale, scale - oldScale, centerX, centerY);
        this.mZoomImageToPositionRunnable = zoomImageToPosition;
        post(zoomImageToPosition);
    }

    private void calculateImageScaleBounds() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            calculateImageScaleBounds((float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        }
    }

    private void calculateImageScaleBounds(float drawableWidth, float drawableHeight) {
        this.mMinScale = Math.max(this.mCropRect.width() / drawableWidth, this.mCropRect.height() / drawableHeight);
        this.mMaxScale = this.mMinScale * this.mMaxScaleMultiplier;
    }

    private void setupInitialImagePosition(float drawableWidth, float drawableHeight) {
        float cropRectWidth = this.mCropRect.width();
        float tw = ((cropRectWidth - (this.mMinScale * drawableWidth)) / 2.0f) + this.mCropRect.left;
        float th = ((this.mCropRect.height() - (this.mMinScale * drawableHeight)) / 2.0f) + this.mCropRect.top;
        this.mCurrentImageMatrix.reset();
        this.mCurrentImageMatrix.postScale(this.mMinScale, this.mMinScale);
        this.mCurrentImageMatrix.postTranslate(tw, th);
        setImageMatrix(this.mCurrentImageMatrix);
    }

    protected void processStyledAttributes(@NonNull TypedArray a) {
        float targetAspectRatioX = Math.abs(a.getFloat(R.styleable.ucrop_UCropView_ucrop_aspect_ratio_x, 0.0f));
        float targetAspectRatioY = Math.abs(a.getFloat(R.styleable.ucrop_UCropView_ucrop_aspect_ratio_y, 0.0f));
        if (targetAspectRatioX == 0.0f || targetAspectRatioY == 0.0f) {
            this.mTargetAspectRatio = 0.0f;
        } else {
            this.mTargetAspectRatio = targetAspectRatioX / targetAspectRatioY;
        }
    }
}
