package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.util.RectUtils;

public class OverlayView extends View {
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final boolean DEFAULT_FREESTYLE_CROP_ENABLED = false;
    public static final boolean DEFAULT_OVAL_DIMMED_LAYER = false;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    private OverlayViewChangeListener mCallback;
    private Path mCircularPath;
    private Paint mCropFrameCornersPaint;
    private Paint mCropFramePaint;
    private int mCropGridColumnCount;
    protected float[] mCropGridCorners;
    private Paint mCropGridPaint;
    private int mCropGridRowCount;
    private int mCropRectCornerTouchAreaLineLength;
    private int mCropRectMinSize;
    private final RectF mCropViewRect;
    private int mCurrentTouchCornerIndex;
    private int mDimmedColor;
    private Paint mDimmedStrokePaint;
    private float[] mGridPoints;
    private boolean mIsFreestyleCropEnabled;
    private boolean mOvalDimmedLayer;
    private boolean mShowCropFrame;
    private boolean mShowCropGrid;
    private float mTargetAspectRatio;
    private final RectF mTempRect;
    protected int mThisHeight;
    protected int mThisWidth;
    private int mTouchPointThreshold;

    public OverlayView(Context context) {
        this(context, null);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCropViewRect = new RectF();
        this.mTempRect = new RectF();
        this.mGridPoints = null;
        this.mCircularPath = new Path();
        this.mDimmedStrokePaint = new Paint(1);
        this.mCropGridPaint = new Paint(1);
        this.mCropFramePaint = new Paint(1);
        this.mCropFrameCornersPaint = new Paint(1);
        this.mIsFreestyleCropEnabled = false;
        this.mCurrentTouchCornerIndex = -1;
        this.mTouchPointThreshold = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_threshold);
        this.mCropRectMinSize = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_min_size);
        this.mCropRectCornerTouchAreaLineLength = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_area_line_length);
        init();
    }

    public OverlayViewChangeListener getOverlayViewChangeListener() {
        return this.mCallback;
    }

    public void setOverlayViewChangeListener(OverlayViewChangeListener callback) {
        this.mCallback = callback;
    }

    @NonNull
    public RectF getCropViewRect() {
        return this.mCropViewRect;
    }

    public boolean isFreestyleCropEnabled() {
        return this.mIsFreestyleCropEnabled;
    }

    public void setFreestyleCropEnabled(boolean freestyleCropEnabled) {
        this.mIsFreestyleCropEnabled = freestyleCropEnabled;
    }

    public void setOvalDimmedLayer(boolean ovalDimmedLayer) {
        this.mOvalDimmedLayer = ovalDimmedLayer;
    }

    public void setCropGridRowCount(@IntRange(from = 0) int cropGridRowCount) {
        this.mCropGridRowCount = cropGridRowCount;
        this.mGridPoints = null;
    }

    public void setCropGridColumnCount(@IntRange(from = 0) int cropGridColumnCount) {
        this.mCropGridColumnCount = cropGridColumnCount;
        this.mGridPoints = null;
    }

    public void setShowCropFrame(boolean showCropFrame) {
        this.mShowCropFrame = showCropFrame;
    }

    public void setShowCropGrid(boolean showCropGrid) {
        this.mShowCropGrid = showCropGrid;
    }

    public void setDimmedColor(@ColorInt int dimmedColor) {
        this.mDimmedColor = dimmedColor;
    }

    public void setCropFrameStrokeWidth(@IntRange(from = 0) int width) {
        this.mCropFramePaint.setStrokeWidth((float) width);
    }

    public void setCropGridStrokeWidth(@IntRange(from = 0) int width) {
        this.mCropGridPaint.setStrokeWidth((float) width);
    }

    public void setCropFrameColor(@ColorInt int color) {
        this.mCropFramePaint.setColor(color);
    }

    public void setCropGridColor(@ColorInt int color) {
        this.mCropGridPaint.setColor(color);
    }

    public void setTargetAspectRatio(float targetAspectRatio) {
        this.mTargetAspectRatio = targetAspectRatio;
        setupCropBounds();
        postInvalidate();
    }

    public void setupCropBounds() {
        int height = (int) (((float) this.mThisWidth) / this.mTargetAspectRatio);
        int halfDiff;
        if (height > this.mThisHeight) {
            int width = (int) (((float) this.mThisHeight) * this.mTargetAspectRatio);
            halfDiff = (this.mThisWidth - width) / 2;
            this.mCropViewRect.set((float) (getPaddingLeft() + halfDiff), (float) getPaddingTop(), (float) ((getPaddingLeft() + width) + halfDiff), (float) (getPaddingTop() + this.mThisHeight));
        } else {
            halfDiff = (this.mThisHeight - height) / 2;
            this.mCropViewRect.set((float) getPaddingLeft(), (float) (getPaddingTop() + halfDiff), (float) (getPaddingLeft() + this.mThisWidth), (float) ((getPaddingTop() + height) + halfDiff));
        }
        if (this.mCallback != null) {
            this.mCallback.onCropRectUpdated(this.mCropViewRect);
        }
        updateGridPoints();
    }

    private void updateGridPoints() {
        this.mCropGridCorners = RectUtils.getCornersFromRect(this.mCropViewRect);
        this.mGridPoints = null;
        this.mCircularPath.reset();
        this.mCircularPath.addOval(this.mCropViewRect, Direction.CW);
    }

    protected void init() {
        if (VERSION.SDK_INT < 18 && VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            left = getPaddingLeft();
            top = getPaddingTop();
            bottom = getHeight() - getPaddingBottom();
            this.mThisWidth = (getWidth() - getPaddingRight()) - left;
            this.mThisHeight = bottom - top;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDimmedLayer(canvas);
        drawCropGrid(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mCropViewRect.isEmpty() || !this.mIsFreestyleCropEnabled) {
            return false;
        }
        float x = event.getX();
        float y = event.getY();
        if ((event.getAction() & 255) == 0) {
            this.mCurrentTouchCornerIndex = getCurrentTouchCorner(x, y);
            if (this.mCurrentTouchCornerIndex == -1) {
                return false;
            }
            return true;
        } else if ((event.getAction() & 255) == 2 && event.getPointerCount() == 1 && this.mCurrentTouchCornerIndex != -1) {
            resizeCropViewRect(Math.min(Math.max(x, (float) getPaddingLeft()), (float) (getWidth() - getPaddingRight())), Math.min(Math.max(y, (float) getPaddingTop()), (float) (getHeight() - getPaddingBottom())));
            return true;
        } else {
            if ((event.getAction() & 255) == 1) {
                this.mCurrentTouchCornerIndex = -1;
                if (this.mCallback != null) {
                    this.mCallback.onCropRectUpdated(this.mCropViewRect);
                }
            }
            return false;
        }
    }

    private void resizeCropViewRect(float touchX, float touchY) {
        boolean changeHeight;
        boolean changeWidth;
        float f;
        float f2;
        float f3;
        float f4;
        this.mTempRect.set(this.mCropViewRect);
        switch (this.mCurrentTouchCornerIndex) {
            case 0:
                this.mTempRect.set(touchX, touchY, this.mCropViewRect.right, this.mCropViewRect.bottom);
                break;
            case 1:
                this.mTempRect.set(this.mCropViewRect.left, touchY, touchX, this.mCropViewRect.bottom);
                break;
            case 2:
                this.mTempRect.set(this.mCropViewRect.left, this.mCropViewRect.top, touchX, touchY);
                break;
            case 3:
                this.mTempRect.set(touchX, this.mCropViewRect.top, this.mCropViewRect.right, touchY);
                break;
        }
        if (this.mTempRect.height() >= ((float) this.mCropRectMinSize)) {
            changeHeight = true;
        } else {
            changeHeight = false;
        }
        if (this.mTempRect.width() >= ((float) this.mCropRectMinSize)) {
            changeWidth = true;
        } else {
            changeWidth = false;
        }
        RectF rectF = this.mCropViewRect;
        if (changeWidth) {
            f = this.mTempRect.left;
        } else {
            f = this.mCropViewRect.left;
        }
        if (changeHeight) {
            f2 = this.mTempRect.top;
        } else {
            f2 = this.mCropViewRect.top;
        }
        if (changeWidth) {
            f3 = this.mTempRect.right;
        } else {
            f3 = this.mCropViewRect.right;
        }
        if (changeHeight) {
            f4 = this.mTempRect.bottom;
        } else {
            f4 = this.mCropViewRect.bottom;
        }
        rectF.set(f, f2, f3, f4);
        if (changeHeight || changeWidth) {
            updateGridPoints();
            postInvalidate();
        }
    }

    private int getCurrentTouchCorner(float touchX, float touchY) {
        int closestPointIndex = -1;
        double closestPointDistance = (double) this.mTouchPointThreshold;
        for (int i = 0; i < 8; i += 2) {
            double distanceToCorner = Math.sqrt(Math.pow((double) (touchX - this.mCropGridCorners[i]), 2.0d) + Math.pow((double) (touchY - this.mCropGridCorners[i + 1]), 2.0d));
            if (distanceToCorner < closestPointDistance) {
                closestPointDistance = distanceToCorner;
                closestPointIndex = i / 2;
            }
        }
        return closestPointIndex;
    }

    protected void drawDimmedLayer(@NonNull Canvas canvas) {
        canvas.save();
        if (this.mOvalDimmedLayer) {
            canvas.clipPath(this.mCircularPath, Op.DIFFERENCE);
        } else {
            canvas.clipRect(this.mCropViewRect, Op.DIFFERENCE);
        }
        canvas.drawColor(this.mDimmedColor);
        canvas.restore();
        if (this.mOvalDimmedLayer) {
            canvas.drawOval(this.mCropViewRect, this.mDimmedStrokePaint);
        }
    }

    protected void drawCropGrid(@NonNull Canvas canvas) {
        if (this.mShowCropGrid) {
            if (this.mGridPoints == null && !this.mCropViewRect.isEmpty()) {
                int i;
                int i2;
                this.mGridPoints = new float[((this.mCropGridRowCount * 4) + (this.mCropGridColumnCount * 4))];
                int index = 0;
                for (i = 0; i < this.mCropGridRowCount; i++) {
                    i2 = index + 1;
                    this.mGridPoints[index] = this.mCropViewRect.left;
                    index = i2 + 1;
                    this.mGridPoints[i2] = (this.mCropViewRect.height() * ((((float) i) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / ((float) (this.mCropGridRowCount + 1)))) + this.mCropViewRect.top;
                    i2 = index + 1;
                    this.mGridPoints[index] = this.mCropViewRect.right;
                    index = i2 + 1;
                    this.mGridPoints[i2] = (this.mCropViewRect.height() * ((((float) i) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / ((float) (this.mCropGridRowCount + 1)))) + this.mCropViewRect.top;
                }
                for (i = 0; i < this.mCropGridColumnCount; i++) {
                    i2 = index + 1;
                    this.mGridPoints[index] = (this.mCropViewRect.width() * ((((float) i) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / ((float) (this.mCropGridColumnCount + 1)))) + this.mCropViewRect.left;
                    index = i2 + 1;
                    this.mGridPoints[i2] = this.mCropViewRect.top;
                    i2 = index + 1;
                    this.mGridPoints[index] = (this.mCropViewRect.width() * ((((float) i) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / ((float) (this.mCropGridColumnCount + 1)))) + this.mCropViewRect.left;
                    index = i2 + 1;
                    this.mGridPoints[i2] = this.mCropViewRect.bottom;
                }
            }
            if (this.mGridPoints != null) {
                canvas.drawLines(this.mGridPoints, this.mCropGridPaint);
            }
        }
        if (this.mShowCropFrame) {
            canvas.drawRect(this.mCropViewRect, this.mCropFramePaint);
        }
        if (this.mIsFreestyleCropEnabled) {
            canvas.save();
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset((float) this.mCropRectCornerTouchAreaLineLength, (float) (-this.mCropRectCornerTouchAreaLineLength));
            canvas.clipRect(this.mTempRect, Op.DIFFERENCE);
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset((float) (-this.mCropRectCornerTouchAreaLineLength), (float) this.mCropRectCornerTouchAreaLineLength);
            canvas.clipRect(this.mTempRect, Op.DIFFERENCE);
            canvas.drawRect(this.mCropViewRect, this.mCropFrameCornersPaint);
            canvas.restore();
        }
    }

    protected void processStyledAttributes(@NonNull TypedArray a) {
        this.mOvalDimmedLayer = a.getBoolean(R.styleable.ucrop_UCropView_ucrop_oval_dimmed_layer, false);
        this.mDimmedColor = a.getColor(R.styleable.ucrop_UCropView_ucrop_dimmed_color, getResources().getColor(R.color.ucrop_color_default_dimmed));
        this.mDimmedStrokePaint.setColor(this.mDimmedColor);
        this.mDimmedStrokePaint.setStyle(Style.STROKE);
        this.mDimmedStrokePaint.setStrokeWidth(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        initCropFrameStyle(a);
        this.mShowCropFrame = a.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_frame, true);
        initCropGridStyle(a);
        this.mShowCropGrid = a.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_grid, true);
    }

    private void initCropFrameStyle(@NonNull TypedArray a) {
        int cropFrameStrokeSize = a.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_frame_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width));
        int cropFrameColor = a.getColor(R.styleable.ucrop_UCropView_ucrop_frame_color, getResources().getColor(R.color.ucrop_color_default_crop_frame));
        this.mCropFramePaint.setStrokeWidth((float) cropFrameStrokeSize);
        this.mCropFramePaint.setColor(cropFrameColor);
        this.mCropFramePaint.setStyle(Style.STROKE);
        this.mCropFrameCornersPaint.setStrokeWidth((float) (cropFrameStrokeSize * 3));
        this.mCropFrameCornersPaint.setColor(cropFrameColor);
        this.mCropFrameCornersPaint.setStyle(Style.STROKE);
    }

    private void initCropGridStyle(@NonNull TypedArray a) {
        int cropGridStrokeSize = a.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_grid_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width));
        int cropGridColor = a.getColor(R.styleable.ucrop_UCropView_ucrop_grid_color, getResources().getColor(R.color.ucrop_color_default_crop_grid));
        this.mCropGridPaint.setStrokeWidth((float) cropGridStrokeSize);
        this.mCropGridPaint.setColor(cropGridColor);
        this.mCropGridRowCount = a.getInt(R.styleable.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.mCropGridColumnCount = a.getInt(R.styleable.ucrop_UCropView_ucrop_grid_column_count, 2);
    }
}
