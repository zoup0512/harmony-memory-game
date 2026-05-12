package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis$AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.jobs.AnimatedMoveViewJob;
import com.github.mikephil.charting.jobs.AnimatedZoomJob;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;

@SuppressLint({"RtlHardcoded"})
public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> extends Chart<T> implements BarLineScatterCandleBubbleDataProvider {
    private long drawCycles;
    private Float mAutoScaleLastHighestVisibleXIndex;
    private Float mAutoScaleLastLowestVisibleXIndex;
    protected boolean mAutoScaleMinMaxEnabled;
    protected YAxis mAxisLeft;
    protected YAxisRenderer mAxisRendererLeft;
    protected YAxisRenderer mAxisRendererRight;
    protected YAxis mAxisRight;
    protected Paint mBorderPaint;
    private boolean mCustomViewPortEnabled;
    protected boolean mDoubleTapToZoomEnabled;
    private boolean mDragEnabled;
    protected boolean mDrawBorders;
    protected boolean mDrawGridBackground;
    protected OnDrawListener mDrawListener;
    protected Matrix mFitScreenMatrixBuffer;
    protected float[] mGetPositionBuffer;
    protected Paint mGridBackgroundPaint;
    protected boolean mHighlightPerDragEnabled;
    protected boolean mKeepPositionOnRotation;
    protected Transformer mLeftAxisTransformer;
    protected int mMaxVisibleCount;
    protected float mMinOffset;
    private RectF mOffsetsBuffer;
    protected float[] mOnSizeChangedBuffer;
    protected boolean mPinchZoomEnabled;
    protected Transformer mRightAxisTransformer;
    private boolean mScaleXEnabled;
    private boolean mScaleYEnabled;
    protected XAxisRenderer mXAxisRenderer;
    protected Matrix mZoomInMatrixBuffer;
    protected Matrix mZoomMatrixBuffer;
    protected Matrix mZoomOutMatrixBuffer;
    MPPointD pointForGetYValueByTouchPoint;
    protected MPPointD posForGetHighestVisibleX;
    protected MPPointD posForGetLowestVisibleX;
    private long totalTime;

    public /* bridge */ /* synthetic */ BarLineScatterCandleBubbleData getData() {
        return (BarLineScatterCandleBubbleData) super.getData();
    }

    public BarLineChartBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mMaxVisibleCount = 100;
        this.mAutoScaleMinMaxEnabled = false;
        this.mAutoScaleLastLowestVisibleXIndex = null;
        this.mAutoScaleLastHighestVisibleXIndex = null;
        this.mPinchZoomEnabled = false;
        this.mDoubleTapToZoomEnabled = true;
        this.mHighlightPerDragEnabled = true;
        this.mDragEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.mDrawGridBackground = false;
        this.mDrawBorders = false;
        this.mMinOffset = CtaButton.TEXT_SIZE_SP;
        this.mKeepPositionOnRotation = false;
        this.totalTime = 0;
        this.drawCycles = 0;
        this.mOffsetsBuffer = new RectF();
        this.mZoomInMatrixBuffer = new Matrix();
        this.mZoomOutMatrixBuffer = new Matrix();
        this.mZoomMatrixBuffer = new Matrix();
        this.mFitScreenMatrixBuffer = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.mGetPositionBuffer = new float[2];
        this.pointForGetYValueByTouchPoint = MPPointD.getInstance(0.0d, 0.0d);
        this.posForGetLowestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
        this.posForGetHighestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
        this.mOnSizeChangedBuffer = new float[2];
    }

    public BarLineChartBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMaxVisibleCount = 100;
        this.mAutoScaleMinMaxEnabled = false;
        this.mAutoScaleLastLowestVisibleXIndex = null;
        this.mAutoScaleLastHighestVisibleXIndex = null;
        this.mPinchZoomEnabled = false;
        this.mDoubleTapToZoomEnabled = true;
        this.mHighlightPerDragEnabled = true;
        this.mDragEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.mDrawGridBackground = false;
        this.mDrawBorders = false;
        this.mMinOffset = CtaButton.TEXT_SIZE_SP;
        this.mKeepPositionOnRotation = false;
        this.totalTime = 0;
        this.drawCycles = 0;
        this.mOffsetsBuffer = new RectF();
        this.mZoomInMatrixBuffer = new Matrix();
        this.mZoomOutMatrixBuffer = new Matrix();
        this.mZoomMatrixBuffer = new Matrix();
        this.mFitScreenMatrixBuffer = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.mGetPositionBuffer = new float[2];
        this.pointForGetYValueByTouchPoint = MPPointD.getInstance(0.0d, 0.0d);
        this.posForGetLowestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
        this.posForGetHighestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
        this.mOnSizeChangedBuffer = new float[2];
    }

    public BarLineChartBase(Context context) {
        super(context);
        this.mMaxVisibleCount = 100;
        this.mAutoScaleMinMaxEnabled = false;
        this.mAutoScaleLastLowestVisibleXIndex = null;
        this.mAutoScaleLastHighestVisibleXIndex = null;
        this.mPinchZoomEnabled = false;
        this.mDoubleTapToZoomEnabled = true;
        this.mHighlightPerDragEnabled = true;
        this.mDragEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.mDrawGridBackground = false;
        this.mDrawBorders = false;
        this.mMinOffset = CtaButton.TEXT_SIZE_SP;
        this.mKeepPositionOnRotation = false;
        this.totalTime = 0;
        this.drawCycles = 0;
        this.mOffsetsBuffer = new RectF();
        this.mZoomInMatrixBuffer = new Matrix();
        this.mZoomOutMatrixBuffer = new Matrix();
        this.mZoomMatrixBuffer = new Matrix();
        this.mFitScreenMatrixBuffer = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.mGetPositionBuffer = new float[2];
        this.pointForGetYValueByTouchPoint = MPPointD.getInstance(0.0d, 0.0d);
        this.posForGetLowestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
        this.posForGetHighestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
        this.mOnSizeChangedBuffer = new float[2];
    }

    protected void init() {
        super.init();
        this.mAxisLeft = new YAxis(YAxis$AxisDependency.LEFT);
        this.mAxisRight = new YAxis(YAxis$AxisDependency.RIGHT);
        this.mLeftAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mRightAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mAxisRendererLeft = new YAxisRenderer(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
        this.mAxisRendererRight = new YAxisRenderer(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
        this.mXAxisRenderer = new XAxisRenderer(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer);
        setHighlighter(new ChartHighlighter(this));
        this.mChartTouchListener = new BarLineChartTouchListener(this, this.mViewPortHandler.getMatrixTouch(), 3.0f);
        this.mGridBackgroundPaint = new Paint();
        this.mGridBackgroundPaint.setStyle(Style.FILL);
        this.mGridBackgroundPaint.setColor(Color.rgb(240, 240, 240));
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setColor(-16777216);
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            long starttime = System.currentTimeMillis();
            drawGridBackground(canvas);
            if (this.mAxisLeft.isEnabled()) {
                this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted());
            }
            if (this.mAxisRight.isEnabled()) {
                this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted());
            }
            if (this.mXAxis.isEnabled()) {
                this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
            }
            this.mXAxisRenderer.renderAxisLine(canvas);
            this.mAxisRendererLeft.renderAxisLine(canvas);
            this.mAxisRendererRight.renderAxisLine(canvas);
            if (this.mAutoScaleMinMaxEnabled) {
                float lowestVisibleXIndex = getLowestVisibleX();
                float highestVisibleXIndex = getHighestVisibleX();
                if (this.mAutoScaleLastLowestVisibleXIndex == null || this.mAutoScaleLastLowestVisibleXIndex.floatValue() != lowestVisibleXIndex || this.mAutoScaleLastHighestVisibleXIndex == null || this.mAutoScaleLastHighestVisibleXIndex.floatValue() != highestVisibleXIndex) {
                    calcMinMax();
                    calculateOffsets();
                    this.mAutoScaleLastLowestVisibleXIndex = Float.valueOf(lowestVisibleXIndex);
                    this.mAutoScaleLastHighestVisibleXIndex = Float.valueOf(highestVisibleXIndex);
                }
            }
            int clipRestoreCount = canvas.save();
            canvas.clipRect(this.mViewPortHandler.getContentRect());
            this.mXAxisRenderer.renderGridLines(canvas);
            this.mAxisRendererLeft.renderGridLines(canvas);
            this.mAxisRendererRight.renderGridLines(canvas);
            if (this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            canvas.restoreToCount(clipRestoreCount);
            this.mRenderer.drawExtras(canvas);
            clipRestoreCount = canvas.save();
            canvas.clipRect(this.mViewPortHandler.getContentRect());
            if (!this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (!this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (!this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            canvas.restoreToCount(clipRestoreCount);
            this.mXAxisRenderer.renderAxisLabels(canvas);
            this.mAxisRendererLeft.renderAxisLabels(canvas);
            this.mAxisRendererRight.renderAxisLabels(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawMarkers(canvas);
            drawDescription(canvas);
            if (this.mLogEnabled) {
                long drawtime = System.currentTimeMillis() - starttime;
                this.totalTime += drawtime;
                this.drawCycles++;
                Log.i(Chart.LOG_TAG, "Drawtime: " + drawtime + " ms, average: " + (this.totalTime / this.drawCycles) + " ms, cycles: " + this.drawCycles);
            }
        }
    }

    public void resetTracking() {
        this.totalTime = 0;
        this.drawCycles = 0;
    }

    protected void prepareValuePxMatrix() {
        if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing Value-Px Matrix, xmin: " + this.mXAxis.mAxisMinimum + ", xmax: " + this.mXAxis.mAxisMaximum + ", xdelta: " + this.mXAxis.mAxisRange);
        }
        this.mRightAxisTransformer.prepareMatrixValuePx(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisRange, this.mAxisRight.mAxisRange, this.mAxisRight.mAxisMinimum);
        this.mLeftAxisTransformer.prepareMatrixValuePx(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisRange, this.mAxisLeft.mAxisRange, this.mAxisLeft.mAxisMinimum);
    }

    protected void prepareOffsetMatrix() {
        this.mRightAxisTransformer.prepareMatrixOffset(this.mAxisRight.isInverted());
        this.mLeftAxisTransformer.prepareMatrixOffset(this.mAxisLeft.isInverted());
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "Preparing...");
            }
            if (this.mRenderer != null) {
                this.mRenderer.initBuffers();
            }
            calcMinMax();
            this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted());
            this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted());
            this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        } else if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing... DATA NOT SET.");
        }
    }

    protected void calcMinMax() {
        if (this.mAutoScaleMinMaxEnabled) {
            ((BarLineScatterCandleBubbleData) this.mData).calcMinMax();
        }
        this.mXAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getXMin(), ((BarLineScatterCandleBubbleData) this.mData).getXMax());
        this.mAxisLeft.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(YAxis$AxisDependency.LEFT), ((BarLineScatterCandleBubbleData) this.mData).getYMax(YAxis$AxisDependency.LEFT));
        this.mAxisRight.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(YAxis$AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData) this.mData).getYMax(YAxis$AxisDependency.RIGHT));
    }

    protected void calculateLegendOffsets(RectF offsets) {
        offsets.left = 0.0f;
        offsets.right = 0.0f;
        offsets.top = 0.0f;
        offsets.bottom = 0.0f;
        if (this.mLegend != null && this.mLegend.isEnabled() && !this.mLegend.isDrawInsideEnabled()) {
            switch (this.mLegend.getOrientation()) {
                case VERTICAL:
                    switch (this.mLegend.getHorizontalAlignment()) {
                        case LEFT:
                            offsets.left += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
                            return;
                        case RIGHT:
                            offsets.right += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
                            return;
                        case CENTER:
                            switch (this.mLegend.getVerticalAlignment()) {
                                case TOP:
                                    offsets.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                                    if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled()) {
                                        offsets.top += (float) getXAxis().mLabelRotatedHeight;
                                        return;
                                    }
                                    return;
                                case BOTTOM:
                                    offsets.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                                    if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled()) {
                                        offsets.bottom += (float) getXAxis().mLabelRotatedHeight;
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        default:
                            return;
                    }
                case HORIZONTAL:
                    switch (this.mLegend.getVerticalAlignment()) {
                        case TOP:
                            offsets.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                            if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled()) {
                                offsets.top += (float) getXAxis().mLabelRotatedHeight;
                                return;
                            }
                            return;
                        case BOTTOM:
                            offsets.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                            if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled()) {
                                offsets.bottom += (float) getXAxis().mLabelRotatedHeight;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    public void calculateOffsets() {
        if (!this.mCustomViewPortEnabled) {
            calculateLegendOffsets(this.mOffsetsBuffer);
            float offsetLeft = 0.0f + this.mOffsetsBuffer.left;
            float offsetTop = 0.0f + this.mOffsetsBuffer.top;
            float offsetRight = 0.0f + this.mOffsetsBuffer.right;
            float offsetBottom = 0.0f + this.mOffsetsBuffer.bottom;
            if (this.mAxisLeft.needsOffset()) {
                offsetLeft += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels());
            }
            if (this.mAxisRight.needsOffset()) {
                offsetRight += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels());
            }
            if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
                float xlabelheight = ((float) this.mXAxis.mLabelRotatedHeight) + this.mXAxis.getYOffset();
                if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                    offsetBottom += xlabelheight;
                } else if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                    offsetTop += xlabelheight;
                } else if (this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                    offsetBottom += xlabelheight;
                    offsetTop += xlabelheight;
                }
            }
            offsetTop += getExtraTopOffset();
            offsetRight += getExtraRightOffset();
            offsetBottom += getExtraBottomOffset();
            offsetLeft += getExtraLeftOffset();
            float minOffset = Utils.convertDpToPixel(this.mMinOffset);
            this.mViewPortHandler.restrainViewPort(Math.max(minOffset, offsetLeft), Math.max(minOffset, offsetTop), Math.max(minOffset, offsetRight), Math.max(minOffset, offsetBottom));
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " + offsetRight + ", offsetBottom: " + offsetBottom);
                Log.i(Chart.LOG_TAG, "Content: " + this.mViewPortHandler.getContentRect().toString());
            }
        }
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    protected void drawGridBackground(Canvas c) {
        if (this.mDrawGridBackground) {
            c.drawRect(this.mViewPortHandler.getContentRect(), this.mGridBackgroundPaint);
        }
        if (this.mDrawBorders) {
            c.drawRect(this.mViewPortHandler.getContentRect(), this.mBorderPaint);
        }
    }

    public Transformer getTransformer(YAxis$AxisDependency which) {
        if (which == YAxis$AxisDependency.LEFT) {
            return this.mLeftAxisTransformer;
        }
        return this.mRightAxisTransformer;
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.mChartTouchListener == null || this.mData == null || !this.mTouchEnabled) {
            return false;
        }
        return this.mChartTouchListener.onTouch(this, event);
    }

    public void computeScroll() {
        if (this.mChartTouchListener instanceof BarLineChartTouchListener) {
            ((BarLineChartTouchListener) this.mChartTouchListener).computeScroll();
        }
    }

    public void zoomIn() {
        MPPointF center = this.mViewPortHandler.getContentCenter();
        this.mViewPortHandler.zoomIn(center.x, -center.y, this.mZoomInMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomInMatrixBuffer, this, false);
        MPPointF.recycleInstance(center);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomOut() {
        MPPointF center = this.mViewPortHandler.getContentCenter();
        this.mViewPortHandler.zoomOut(center.x, -center.y, this.mZoomOutMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomOutMatrixBuffer, this, false);
        MPPointF.recycleInstance(center);
        calculateOffsets();
        postInvalidate();
    }

    public void zoom(float scaleX, float scaleY, float x, float y) {
        Matrix save = this.mZoomMatrixBuffer;
        this.mViewPortHandler.zoom(scaleX, scaleY, x, y, save);
        this.mViewPortHandler.refresh(save, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomAndCenter(float scaleX, float scaleY, float xValue, float yValue, YAxis$AxisDependency axis) {
        addViewportJob(ZoomJob.getInstance(this.mViewPortHandler, scaleX, scaleY, xValue, yValue, getTransformer(axis), axis, this));
    }

    @TargetApi(11)
    public void zoomAndCenterAnimated(float scaleX, float scaleY, float xValue, float yValue, YAxis$AxisDependency axis, long duration) {
        if (VERSION.SDK_INT >= 11) {
            MPPointD origin = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axis);
            addViewportJob(AnimatedZoomJob.getInstance(this.mViewPortHandler, this, getTransformer(axis), getAxis(axis), this.mXAxis.mAxisRange, scaleX, scaleY, this.mViewPortHandler.getScaleX(), this.mViewPortHandler.getScaleY(), xValue, yValue, (float) origin.x, (float) origin.y, duration));
            MPPointD.recycleInstance(origin);
            return;
        }
        Log.e(Chart.LOG_TAG, "Unable to execute zoomAndCenterAnimated(...) on API level < 11");
    }

    public void fitScreen() {
        Matrix save = this.mFitScreenMatrixBuffer;
        this.mViewPortHandler.fitScreen(save);
        this.mViewPortHandler.refresh(save, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void setScaleMinima(float scaleX, float scaleY) {
        this.mViewPortHandler.setMinimumScaleX(scaleX);
        this.mViewPortHandler.setMinimumScaleY(scaleY);
    }

    public void setVisibleXRangeMaximum(float maxXRange) {
        this.mViewPortHandler.setMinimumScaleX(this.mXAxis.mAxisRange / maxXRange);
    }

    public void setVisibleXRangeMinimum(float minXRange) {
        this.mViewPortHandler.setMaximumScaleX(this.mXAxis.mAxisRange / minXRange);
    }

    public void setVisibleXRange(float minXRange, float maxXRange) {
        this.mViewPortHandler.setMinMaxScaleX(this.mXAxis.mAxisRange / minXRange, this.mXAxis.mAxisRange / maxXRange);
    }

    public void setVisibleYRangeMaximum(float maxYRange, YAxis$AxisDependency axis) {
        this.mViewPortHandler.setMinimumScaleY(getDeltaY(axis) / maxYRange);
    }

    public void setVisibleYRangeMinimum(float minYRange, YAxis$AxisDependency axis) {
        this.mViewPortHandler.setMaximumScaleY(getDeltaY(axis) / minYRange);
    }

    public void setVisibleYRange(float minYRange, float maxYRange, YAxis$AxisDependency axis) {
        this.mViewPortHandler.setMinMaxScaleY(getDeltaY(axis) / minYRange, getDeltaY(axis) / maxYRange);
    }

    public void moveViewToX(float xValue) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, xValue, 0.0f, getTransformer(YAxis$AxisDependency.LEFT), this));
    }

    public void moveViewTo(float xValue, float yValue, YAxis$AxisDependency axis) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, xValue, ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f) + yValue, getTransformer(axis), this));
    }

    @TargetApi(11)
    public void moveViewToAnimated(float xValue, float yValue, YAxis$AxisDependency axis, long duration) {
        if (VERSION.SDK_INT >= 11) {
            MPPointD bounds = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axis);
            float f = xValue;
            addViewportJob(AnimatedMoveViewJob.getInstance(this.mViewPortHandler, f, yValue + ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f), getTransformer(axis), this, (float) bounds.x, (float) bounds.y, duration));
            MPPointD.recycleInstance(bounds);
            return;
        }
        Log.e(Chart.LOG_TAG, "Unable to execute moveViewToAnimated(...) on API level < 11");
    }

    public void centerViewToY(float yValue, YAxis$AxisDependency axis) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, 0.0f, ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f) + yValue, getTransformer(axis), this));
    }

    public void centerViewTo(float xValue, float yValue, YAxis$AxisDependency axis) {
        float xInView = getXAxis().mAxisRange / this.mViewPortHandler.getScaleX();
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, xValue - (xInView / 2.0f), ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f) + yValue, getTransformer(axis), this));
    }

    @TargetApi(11)
    public void centerViewToAnimated(float xValue, float yValue, YAxis$AxisDependency axis, long duration) {
        if (VERSION.SDK_INT >= 11) {
            MPPointD bounds = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axis);
            float xInView = getXAxis().mAxisRange / this.mViewPortHandler.getScaleX();
            addViewportJob(AnimatedMoveViewJob.getInstance(this.mViewPortHandler, xValue - (xInView / 2.0f), ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f) + yValue, getTransformer(axis), this, (float) bounds.x, (float) bounds.y, duration));
            MPPointD.recycleInstance(bounds);
            return;
        }
        Log.e(Chart.LOG_TAG, "Unable to execute centerViewToAnimated(...) on API level < 11");
    }

    public void setViewPortOffsets(float left, float top, float right, float bottom) {
        this.mCustomViewPortEnabled = true;
        final float f = left;
        final float f2 = top;
        final float f3 = right;
        final float f4 = bottom;
        post(new Runnable() {
            public void run() {
                BarLineChartBase.this.mViewPortHandler.restrainViewPort(f, f2, f3, f4);
                BarLineChartBase.this.prepareOffsetMatrix();
                BarLineChartBase.this.prepareValuePxMatrix();
            }
        });
    }

    public void resetViewPortOffsets() {
        this.mCustomViewPortEnabled = false;
        calculateOffsets();
    }

    public float getDeltaY(YAxis$AxisDependency axis) {
        if (axis == YAxis$AxisDependency.LEFT) {
            return this.mAxisLeft.mAxisRange;
        }
        return this.mAxisRight.mAxisRange;
    }

    public void setOnDrawListener(OnDrawListener drawListener) {
        this.mDrawListener = drawListener;
    }

    public OnDrawListener getDrawListener() {
        return this.mDrawListener;
    }

    public MPPointF getPosition(Entry e, YAxis$AxisDependency axis) {
        if (e == null) {
            return null;
        }
        float[] vals = this.mGetPositionBuffer;
        vals[0] = e.getX();
        vals[1] = e.getY();
        getTransformer(axis).pointValuesToPixel(vals);
        return MPPointF.getInstance(vals[0], vals[1]);
    }

    public void setMaxVisibleValueCount(int count) {
        this.mMaxVisibleCount = count;
    }

    public int getMaxVisibleCount() {
        return this.mMaxVisibleCount;
    }

    public void setHighlightPerDragEnabled(boolean enabled) {
        this.mHighlightPerDragEnabled = enabled;
    }

    public boolean isHighlightPerDragEnabled() {
        return this.mHighlightPerDragEnabled;
    }

    public void setGridBackgroundColor(int color) {
        this.mGridBackgroundPaint.setColor(color);
    }

    public void setDragEnabled(boolean enabled) {
        this.mDragEnabled = enabled;
    }

    public boolean isDragEnabled() {
        return this.mDragEnabled;
    }

    public void setScaleEnabled(boolean enabled) {
        this.mScaleXEnabled = enabled;
        this.mScaleYEnabled = enabled;
    }

    public void setScaleXEnabled(boolean enabled) {
        this.mScaleXEnabled = enabled;
    }

    public void setScaleYEnabled(boolean enabled) {
        this.mScaleYEnabled = enabled;
    }

    public boolean isScaleXEnabled() {
        return this.mScaleXEnabled;
    }

    public boolean isScaleYEnabled() {
        return this.mScaleYEnabled;
    }

    public void setDoubleTapToZoomEnabled(boolean enabled) {
        this.mDoubleTapToZoomEnabled = enabled;
    }

    public boolean isDoubleTapToZoomEnabled() {
        return this.mDoubleTapToZoomEnabled;
    }

    public void setDrawGridBackground(boolean enabled) {
        this.mDrawGridBackground = enabled;
    }

    public void setDrawBorders(boolean enabled) {
        this.mDrawBorders = enabled;
    }

    public void setBorderWidth(float width) {
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(width));
    }

    public void setBorderColor(int color) {
        this.mBorderPaint.setColor(color);
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public void setMinOffset(float minOffset) {
        this.mMinOffset = minOffset;
    }

    public boolean isKeepPositionOnRotation() {
        return this.mKeepPositionOnRotation;
    }

    public void setKeepPositionOnRotation(boolean keepPositionOnRotation) {
        this.mKeepPositionOnRotation = keepPositionOnRotation;
    }

    public MPPointD getValuesByTouchPoint(float x, float y, YAxis$AxisDependency axis) {
        MPPointD result = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(x, y, axis, result);
        return result;
    }

    public void getValuesByTouchPoint(float x, float y, YAxis$AxisDependency axis, MPPointD outputPoint) {
        getTransformer(axis).getValuesByTouchPoint(x, y, outputPoint);
    }

    public MPPointD getPixelsForValues(float x, float y, YAxis$AxisDependency axis) {
        return getTransformer(axis).getPixelsForValues(x, y);
    }

    public float getYValueByTouchPoint(float x, float y, YAxis$AxisDependency axis) {
        getValuesByTouchPoint(x, y, axis, this.pointForGetYValueByTouchPoint);
        return (float) this.pointForGetYValueByTouchPoint.y;
    }

    public Entry getEntryByTouchPoint(float x, float y) {
        Highlight h = getHighlightByTouchPoint(x, y);
        if (h != null) {
            return ((BarLineScatterCandleBubbleData) this.mData).getEntryForHighlight(h);
        }
        return null;
    }

    public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(float x, float y) {
        Highlight h = getHighlightByTouchPoint(x, y);
        if (h != null) {
            return (IBarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex(h.getDataSetIndex());
        }
        return null;
    }

    public float getLowestVisibleX() {
        getTransformer(YAxis$AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.posForGetLowestVisibleX);
        return (float) Math.max((double) this.mXAxis.mAxisMinimum, this.posForGetLowestVisibleX.x);
    }

    public float getHighestVisibleX() {
        getTransformer(YAxis$AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.posForGetHighestVisibleX);
        return (float) Math.min((double) this.mXAxis.mAxisMaximum, this.posForGetHighestVisibleX.x);
    }

    public float getVisibleXRange() {
        return Math.abs(getHighestVisibleX() - getLowestVisibleX());
    }

    public float getScaleX() {
        if (this.mViewPortHandler == null) {
            return DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        return this.mViewPortHandler.getScaleX();
    }

    public float getScaleY() {
        if (this.mViewPortHandler == null) {
            return DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        return this.mViewPortHandler.getScaleY();
    }

    public boolean isFullyZoomedOut() {
        return this.mViewPortHandler.isFullyZoomedOut();
    }

    public YAxis getAxisLeft() {
        return this.mAxisLeft;
    }

    public YAxis getAxisRight() {
        return this.mAxisRight;
    }

    public YAxis getAxis(YAxis$AxisDependency axis) {
        if (axis == YAxis$AxisDependency.LEFT) {
            return this.mAxisLeft;
        }
        return this.mAxisRight;
    }

    public boolean isInverted(YAxis$AxisDependency axis) {
        return getAxis(axis).isInverted();
    }

    public void setPinchZoom(boolean enabled) {
        this.mPinchZoomEnabled = enabled;
    }

    public boolean isPinchZoomEnabled() {
        return this.mPinchZoomEnabled;
    }

    public void setDragOffsetX(float offset) {
        this.mViewPortHandler.setDragOffsetX(offset);
    }

    public void setDragOffsetY(float offset) {
        this.mViewPortHandler.setDragOffsetY(offset);
    }

    public boolean hasNoDragOffset() {
        return this.mViewPortHandler.hasNoDragOffset();
    }

    public XAxisRenderer getRendererXAxis() {
        return this.mXAxisRenderer;
    }

    public void setXAxisRenderer(XAxisRenderer xAxisRenderer) {
        this.mXAxisRenderer = xAxisRenderer;
    }

    public YAxisRenderer getRendererLeftYAxis() {
        return this.mAxisRendererLeft;
    }

    public void setRendererLeftYAxis(YAxisRenderer rendererLeftYAxis) {
        this.mAxisRendererLeft = rendererLeftYAxis;
    }

    public YAxisRenderer getRendererRightYAxis() {
        return this.mAxisRendererRight;
    }

    public void setRendererRightYAxis(YAxisRenderer rendererRightYAxis) {
        this.mAxisRendererRight = rendererRightYAxis;
    }

    public float getYChartMax() {
        return Math.max(this.mAxisLeft.mAxisMaximum, this.mAxisRight.mAxisMaximum);
    }

    public float getYChartMin() {
        return Math.min(this.mAxisLeft.mAxisMinimum, this.mAxisRight.mAxisMinimum);
    }

    public boolean isAnyAxisInverted() {
        if (this.mAxisLeft.isInverted() || this.mAxisRight.isInverted()) {
            return true;
        }
        return false;
    }

    public void setAutoScaleMinMaxEnabled(boolean enabled) {
        this.mAutoScaleMinMaxEnabled = enabled;
    }

    public boolean isAutoScaleMinMaxEnabled() {
        return this.mAutoScaleMinMaxEnabled;
    }

    public void setPaint(Paint p, int which) {
        super.setPaint(p, which);
        switch (which) {
            case 4:
                this.mGridBackgroundPaint = p;
                return;
            default:
                return;
        }
    }

    public Paint getPaint(int which) {
        Paint p = super.getPaint(which);
        if (p != null) {
            return p;
        }
        switch (which) {
            case 4:
                return this.mGridBackgroundPaint;
            default:
                return null;
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float[] pts = this.mOnSizeChangedBuffer;
        pts[1] = 0.0f;
        pts[0] = 0.0f;
        if (this.mKeepPositionOnRotation) {
            pts[0] = this.mViewPortHandler.contentLeft();
            pts[1] = this.mViewPortHandler.contentTop();
            getTransformer(YAxis$AxisDependency.LEFT).pixelsToValue(pts);
        }
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mKeepPositionOnRotation) {
            getTransformer(YAxis$AxisDependency.LEFT).pointValuesToPixel(pts);
            this.mViewPortHandler.centerViewPort(pts, this);
            return;
        }
        this.mViewPortHandler.refresh(this.mViewPortHandler.getMatrixTouch(), this, true);
    }
}
