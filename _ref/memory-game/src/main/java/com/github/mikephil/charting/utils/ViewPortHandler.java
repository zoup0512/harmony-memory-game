package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import com.mopub.volley.DefaultRetryPolicy;

public class ViewPortHandler {
    protected Matrix mCenterViewPortMatrixBuffer = new Matrix();
    protected float mChartHeight = 0.0f;
    protected float mChartWidth = 0.0f;
    protected RectF mContentRect = new RectF();
    protected final Matrix mMatrixTouch = new Matrix();
    private float mMaxScaleX = AutoScrollHelper.NO_MAX;
    private float mMaxScaleY = AutoScrollHelper.NO_MAX;
    private float mMinScaleX = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private float mMinScaleY = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private float mScaleX = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private float mScaleY = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private float mTransOffsetX = 0.0f;
    private float mTransOffsetY = 0.0f;
    private float mTransX = 0.0f;
    private float mTransY = 0.0f;
    protected final float[] matrixBuffer = new float[9];
    protected float[] valsBufferForFitScreen = new float[9];

    public void setChartDimens(float width, float height) {
        float offsetLeft = offsetLeft();
        float offsetTop = offsetTop();
        float offsetRight = offsetRight();
        float offsetBottom = offsetBottom();
        this.mChartHeight = height;
        this.mChartWidth = width;
        restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    public boolean hasChartDimens() {
        if (this.mChartHeight <= 0.0f || this.mChartWidth <= 0.0f) {
            return false;
        }
        return true;
    }

    public void restrainViewPort(float offsetLeft, float offsetTop, float offsetRight, float offsetBottom) {
        this.mContentRect.set(offsetLeft, offsetTop, this.mChartWidth - offsetRight, this.mChartHeight - offsetBottom);
    }

    public float offsetLeft() {
        return this.mContentRect.left;
    }

    public float offsetRight() {
        return this.mChartWidth - this.mContentRect.right;
    }

    public float offsetTop() {
        return this.mContentRect.top;
    }

    public float offsetBottom() {
        return this.mChartHeight - this.mContentRect.bottom;
    }

    public float contentTop() {
        return this.mContentRect.top;
    }

    public float contentLeft() {
        return this.mContentRect.left;
    }

    public float contentRight() {
        return this.mContentRect.right;
    }

    public float contentBottom() {
        return this.mContentRect.bottom;
    }

    public float contentWidth() {
        return this.mContentRect.width();
    }

    public float contentHeight() {
        return this.mContentRect.height();
    }

    public RectF getContentRect() {
        return this.mContentRect;
    }

    public MPPointF getContentCenter() {
        return MPPointF.getInstance(this.mContentRect.centerX(), this.mContentRect.centerY());
    }

    public float getChartHeight() {
        return this.mChartHeight;
    }

    public float getChartWidth() {
        return this.mChartWidth;
    }

    public float getSmallestContentExtension() {
        return Math.min(this.mContentRect.width(), this.mContentRect.height());
    }

    public Matrix zoomIn(float x, float y) {
        Matrix save = new Matrix();
        zoomIn(x, y, save);
        return save;
    }

    public void zoomIn(float x, float y, Matrix outputMatrix) {
        outputMatrix.reset();
        outputMatrix.set(this.mMatrixTouch);
        outputMatrix.postScale(1.4f, 1.4f, x, y);
    }

    public Matrix zoomOut(float x, float y) {
        Matrix save = new Matrix();
        zoomOut(x, y, save);
        return save;
    }

    public void zoomOut(float x, float y, Matrix outputMatrix) {
        outputMatrix.reset();
        outputMatrix.set(this.mMatrixTouch);
        outputMatrix.postScale(0.7f, 0.7f, x, y);
    }

    public Matrix zoom(float scaleX, float scaleY) {
        Matrix save = new Matrix();
        zoom(scaleX, scaleY, save);
        return save;
    }

    public void zoom(float scaleX, float scaleY, Matrix outputMatrix) {
        outputMatrix.reset();
        outputMatrix.set(this.mMatrixTouch);
        outputMatrix.postScale(scaleX, scaleY);
    }

    public Matrix zoom(float scaleX, float scaleY, float x, float y) {
        Matrix save = new Matrix();
        zoom(scaleX, scaleY, x, y, save);
        return save;
    }

    public void zoom(float scaleX, float scaleY, float x, float y, Matrix outputMatrix) {
        outputMatrix.reset();
        outputMatrix.set(this.mMatrixTouch);
        outputMatrix.postScale(scaleX, scaleY, x, y);
    }

    public Matrix setZoom(float scaleX, float scaleY) {
        Matrix save = new Matrix();
        setZoom(scaleX, scaleY, save);
        return save;
    }

    public void setZoom(float scaleX, float scaleY, Matrix outputMatrix) {
        outputMatrix.reset();
        outputMatrix.set(this.mMatrixTouch);
        outputMatrix.setScale(scaleX, scaleY);
    }

    public Matrix setZoom(float scaleX, float scaleY, float x, float y) {
        Matrix save = new Matrix();
        save.set(this.mMatrixTouch);
        save.setScale(scaleX, scaleY, x, y);
        return save;
    }

    public Matrix fitScreen() {
        Matrix save = new Matrix();
        fitScreen(save);
        return save;
    }

    public void fitScreen(Matrix outputMatrix) {
        this.mMinScaleX = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.mMinScaleY = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        outputMatrix.set(this.mMatrixTouch);
        float[] vals = this.valsBufferForFitScreen;
        for (int i = 0; i < 9; i++) {
            vals[i] = 0.0f;
        }
        outputMatrix.getValues(vals);
        vals[2] = 0.0f;
        vals[5] = 0.0f;
        vals[0] = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        vals[4] = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        outputMatrix.setValues(vals);
    }

    public Matrix translate(float[] transformedPts) {
        Matrix save = new Matrix();
        translate(transformedPts, save);
        return save;
    }

    public void translate(float[] transformedPts, Matrix outputMatrix) {
        outputMatrix.reset();
        outputMatrix.set(this.mMatrixTouch);
        outputMatrix.postTranslate(-(transformedPts[0] - offsetLeft()), -(transformedPts[1] - offsetTop()));
    }

    public void centerViewPort(float[] transformedPts, View view) {
        Matrix save = this.mCenterViewPortMatrixBuffer;
        save.reset();
        save.set(this.mMatrixTouch);
        save.postTranslate(-(transformedPts[0] - offsetLeft()), -(transformedPts[1] - offsetTop()));
        refresh(save, view, true);
    }

    public Matrix refresh(Matrix newMatrix, View chart, boolean invalidate) {
        this.mMatrixTouch.set(newMatrix);
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
        if (invalidate) {
            chart.invalidate();
        }
        newMatrix.set(this.mMatrixTouch);
        return newMatrix;
    }

    public void limitTransAndScale(Matrix matrix, RectF content) {
        matrix.getValues(this.matrixBuffer);
        float curTransX = this.matrixBuffer[2];
        float curScaleX = this.matrixBuffer[0];
        float curTransY = this.matrixBuffer[5];
        float curScaleY = this.matrixBuffer[4];
        this.mScaleX = Math.min(Math.max(this.mMinScaleX, curScaleX), this.mMaxScaleX);
        this.mScaleY = Math.min(Math.max(this.mMinScaleY, curScaleY), this.mMaxScaleY);
        float width = 0.0f;
        float height = 0.0f;
        if (content != null) {
            width = content.width();
            height = content.height();
        }
        this.mTransX = Math.min(Math.max(curTransX, ((-width) * (this.mScaleX - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)) - this.mTransOffsetX), this.mTransOffsetX);
        this.mTransY = Math.max(Math.min(curTransY, this.mTransOffsetY + (height * (this.mScaleY - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))), -this.mTransOffsetY);
        this.matrixBuffer[2] = this.mTransX;
        this.matrixBuffer[0] = this.mScaleX;
        this.matrixBuffer[5] = this.mTransY;
        this.matrixBuffer[4] = this.mScaleY;
        matrix.setValues(this.matrixBuffer);
    }

    public void setMinimumScaleX(float xScale) {
        if (xScale < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            xScale = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        this.mMinScaleX = xScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMaximumScaleX(float xScale) {
        if (xScale == 0.0f) {
            xScale = AutoScrollHelper.NO_MAX;
        }
        this.mMaxScaleX = xScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleX(float minScaleX, float maxScaleX) {
        if (minScaleX < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            minScaleX = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        if (maxScaleX == 0.0f) {
            maxScaleX = AutoScrollHelper.NO_MAX;
        }
        this.mMinScaleX = minScaleX;
        this.mMaxScaleX = maxScaleX;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinimumScaleY(float yScale) {
        if (yScale < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            yScale = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        this.mMinScaleY = yScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMaximumScaleY(float yScale) {
        if (yScale == 0.0f) {
            yScale = AutoScrollHelper.NO_MAX;
        }
        this.mMaxScaleY = yScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleY(float minScaleY, float maxScaleY) {
        if (minScaleY < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            minScaleY = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        if (maxScaleY == 0.0f) {
            maxScaleY = AutoScrollHelper.NO_MAX;
        }
        this.mMinScaleY = minScaleY;
        this.mMaxScaleY = maxScaleY;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public Matrix getMatrixTouch() {
        return this.mMatrixTouch;
    }

    public boolean isInBoundsX(float x) {
        if (isInBoundsLeft(x) && isInBoundsRight(x)) {
            return true;
        }
        return false;
    }

    public boolean isInBoundsY(float y) {
        if (isInBoundsTop(y) && isInBoundsBottom(y)) {
            return true;
        }
        return false;
    }

    public boolean isInBounds(float x, float y) {
        if (isInBoundsX(x) && isInBoundsY(y)) {
            return true;
        }
        return false;
    }

    public boolean isInBoundsLeft(float x) {
        return this.mContentRect.left <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT + x;
    }

    public boolean isInBoundsRight(float x) {
        return this.mContentRect.right >= (((float) ((int) (x * 100.0f))) / 100.0f) - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    public boolean isInBoundsTop(float y) {
        return this.mContentRect.top <= y;
    }

    public boolean isInBoundsBottom(float y) {
        return this.mContentRect.bottom >= ((float) ((int) (y * 100.0f))) / 100.0f;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public float getMinScaleX() {
        return this.mMinScaleX;
    }

    public float getMaxScaleX() {
        return this.mMaxScaleX;
    }

    public float getMinScaleY() {
        return this.mMinScaleY;
    }

    public float getMaxScaleY() {
        return this.mMaxScaleY;
    }

    public float getTransX() {
        return this.mTransX;
    }

    public float getTransY() {
        return this.mTransY;
    }

    public boolean isFullyZoomedOut() {
        if (isFullyZoomedOutX() && isFullyZoomedOutY()) {
            return true;
        }
        return false;
    }

    public boolean isFullyZoomedOutY() {
        if (this.mScaleY > this.mMinScaleY || this.mMinScaleY > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            return false;
        }
        return true;
    }

    public boolean isFullyZoomedOutX() {
        if (this.mScaleX > this.mMinScaleX || this.mMinScaleX > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            return false;
        }
        return true;
    }

    public void setDragOffsetX(float offset) {
        this.mTransOffsetX = Utils.convertDpToPixel(offset);
    }

    public void setDragOffsetY(float offset) {
        this.mTransOffsetY = Utils.convertDpToPixel(offset);
    }

    public boolean hasNoDragOffset() {
        return this.mTransOffsetX <= 0.0f && this.mTransOffsetY <= 0.0f;
    }

    public boolean canZoomOutMoreX() {
        return this.mScaleX > this.mMinScaleX;
    }

    public boolean canZoomInMoreX() {
        return this.mScaleX < this.mMaxScaleX;
    }

    public boolean canZoomOutMoreY() {
        return this.mScaleY > this.mMinScaleY;
    }

    public boolean canZoomInMoreY() {
        return this.mScaleY < this.mMaxScaleY;
    }
}
