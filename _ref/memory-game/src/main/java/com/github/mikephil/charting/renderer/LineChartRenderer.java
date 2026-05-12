package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet$Mode;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.mopub.volley.DefaultRetryPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class LineChartRenderer extends LineRadarRenderer {
    protected Path cubicFillPath = new Path();
    protected Path cubicPath = new Path();
    protected Canvas mBitmapCanvas;
    protected Config mBitmapConfig = Config.ARGB_8888;
    protected LineDataProvider mChart;
    protected Paint mCirclePaintInner;
    private Path mCirclePathBuffer = new Path();
    private float[] mCirclesBuffer = new float[2];
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mGenerateFilledPathBuffer = new Path();
    private HashMap<IDataSet, DataSetImageCache> mImageCaches = new HashMap();
    private float[] mLineBuffer = new float[4];

    private class DataSetImageCache {
        private Bitmap[] circleBitmaps;
        private int[] circleColors;

        private DataSetImageCache() {
        }

        private void ensureCircleCache(int size) {
            int i;
            if (this.circleBitmaps == null) {
                this.circleBitmaps = new Bitmap[size];
            } else if (this.circleBitmaps.length < size) {
                Bitmap[] tmp = new Bitmap[size];
                for (i = 0; i < this.circleBitmaps.length; i++) {
                    tmp[i] = this.circleBitmaps[size];
                }
                this.circleBitmaps = tmp;
            }
            if (this.circleColors == null) {
                this.circleColors = new int[size];
            } else if (this.circleColors.length < size) {
                int[] tmp2 = new int[size];
                for (i = 0; i < this.circleColors.length; i++) {
                    tmp2[i] = this.circleColors[size];
                }
                this.circleColors = tmp2;
            }
        }
    }

    public LineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mCirclePaintInner = new Paint(1);
        this.mCirclePaintInner.setStyle(Style.FILL);
        this.mCirclePaintInner.setColor(-1);
    }

    public void initBuffers() {
    }

    public void drawData(Canvas c) {
        int width = (int) this.mViewPortHandler.getChartWidth();
        int height = (int) this.mViewPortHandler.getChartHeight();
        if (!(this.mDrawBitmap != null && ((Bitmap) this.mDrawBitmap.get()).getWidth() == width && ((Bitmap) this.mDrawBitmap.get()).getHeight() == height)) {
            if (width > 0 && height > 0) {
                this.mDrawBitmap = new WeakReference(Bitmap.createBitmap(width, height, this.mBitmapConfig));
                this.mBitmapCanvas = new Canvas((Bitmap) this.mDrawBitmap.get());
            } else {
                return;
            }
        }
        ((Bitmap) this.mDrawBitmap.get()).eraseColor(0);
        LineData lineData = this.mChart.getLineData();
        int setCount = lineData.getDataSets().size();
        for (int i = 0; i < setCount; i++) {
            ILineDataSet set = (ILineDataSet) lineData.getDataSets().get(i);
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
        c.drawBitmap((Bitmap) this.mDrawBitmap.get(), 0.0f, 0.0f, this.mRenderPaint);
    }

    protected void drawDataSet(Canvas c, ILineDataSet dataSet) {
        if (dataSet.getEntryCount() >= 1) {
            this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
            this.mRenderPaint.setPathEffect(dataSet.getDashPathEffect());
            switch (dataSet.getMode()) {
                case CUBIC_BEZIER:
                    drawCubicBezier(dataSet);
                    break;
                case HORIZONTAL_BEZIER:
                    drawHorizontalBezier(dataSet);
                    break;
                default:
                    drawLinear(c, dataSet);
                    break;
            }
            this.mRenderPaint.setPathEffect(null);
        }
    }

    protected void drawHorizontalBezier(ILineDataSet dataSet) {
        float phaseY = this.mAnimator.getPhaseY();
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, dataSet);
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            Entry cur = dataSet.getEntryForIndex(this.mXBounds.min);
            this.cubicPath.moveTo(cur.getX(), cur.getY() * phaseY);
            for (int j = this.mXBounds.min + 1; j <= this.mXBounds.range + this.mXBounds.min; j++) {
                Entry prev = dataSet.getEntryForIndex(j - 1);
                cur = dataSet.getEntryForIndex(j);
                float cpx = prev.getX() + ((cur.getX() - prev.getX()) / 2.0f);
                this.cubicPath.cubicTo(cpx, prev.getY() * phaseY, cpx, cur.getY() * phaseY, cur.getX(), cur.getY() * phaseY);
            }
        }
        if (dataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, this.mXBounds);
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        this.mRenderPaint.setStyle(Style.STROKE);
        trans.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawCubicBezier(ILineDataSet dataSet) {
        float phaseX = Math.max(0.0f, Math.min(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, this.mAnimator.getPhaseX()));
        float phaseY = this.mAnimator.getPhaseY();
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, dataSet);
        float intensity = dataSet.getCubicIntensity();
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            Entry cur = dataSet.getEntryForIndex(this.mXBounds.min);
            Entry next = dataSet.getEntryForIndex(this.mXBounds.min + 1);
            this.cubicPath.moveTo(cur.getX(), cur.getY() * phaseY);
            int j = this.mXBounds.min + 1;
            while (j <= this.mXBounds.range + this.mXBounds.min) {
                Entry prevPrev = dataSet.getEntryForIndex(j == 1 ? 0 : j - 2);
                Entry prev = dataSet.getEntryForIndex(j - 1);
                cur = dataSet.getEntryForIndex(j);
                if (this.mXBounds.max > j + 1) {
                    next = dataSet.getEntryForIndex(j + 1);
                } else {
                    next = cur;
                }
                this.cubicPath.cubicTo(prev.getX() + ((cur.getX() - prevPrev.getX()) * intensity), (prev.getY() + ((cur.getY() - prevPrev.getY()) * intensity)) * phaseY, cur.getX() - ((next.getX() - prev.getX()) * intensity), (cur.getY() - ((next.getY() - prev.getY()) * intensity)) * phaseY, cur.getX(), cur.getY() * phaseY);
                j++;
            }
        }
        if (dataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, this.mXBounds);
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        this.mRenderPaint.setStyle(Style.STROKE);
        trans.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawCubicFill(Canvas c, ILineDataSet dataSet, Path spline, Transformer trans, XBounds bounds) {
        float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart);
        spline.lineTo((float) (bounds.min + bounds.range), fillMin);
        spline.lineTo((float) bounds.min, fillMin);
        spline.close();
        trans.pathValueToPixel(spline);
        Drawable drawable = dataSet.getFillDrawable();
        if (drawable != null) {
            drawFilledPath(c, spline, drawable);
        } else {
            drawFilledPath(c, spline, dataSet.getFillColor(), dataSet.getFillAlpha());
        }
    }

    protected void drawLinear(Canvas c, ILineDataSet dataSet) {
        Canvas canvas;
        int entryCount = dataSet.getEntryCount();
        boolean isDrawSteppedEnabled = dataSet.isDrawSteppedEnabled();
        int pointsPerEntryPair = isDrawSteppedEnabled ? 4 : 2;
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        this.mRenderPaint.setStyle(Style.STROKE);
        if (dataSet.isDashedLineEnabled()) {
            canvas = this.mBitmapCanvas;
        } else {
            canvas = c;
        }
        this.mXBounds.set(this.mChart, dataSet);
        int j;
        if (dataSet.getColors().size() > 1) {
            if (this.mLineBuffer.length <= pointsPerEntryPair * 2) {
                this.mLineBuffer = new float[(pointsPerEntryPair * 4)];
            }
            for (j = this.mXBounds.min; j <= this.mXBounds.range + this.mXBounds.min; j++) {
                Entry e = dataSet.getEntryForIndex(j);
                if (e != null) {
                    this.mLineBuffer[0] = e.getX();
                    this.mLineBuffer[1] = e.getY() * phaseY;
                    if (j < this.mXBounds.max) {
                        e = dataSet.getEntryForIndex(j + 1);
                        if (e == null) {
                            break;
                        } else if (isDrawSteppedEnabled) {
                            this.mLineBuffer[2] = e.getX();
                            this.mLineBuffer[3] = this.mLineBuffer[1];
                            this.mLineBuffer[4] = this.mLineBuffer[2];
                            this.mLineBuffer[5] = this.mLineBuffer[3];
                            this.mLineBuffer[6] = e.getX();
                            this.mLineBuffer[7] = e.getY() * phaseY;
                        } else {
                            this.mLineBuffer[2] = e.getX();
                            this.mLineBuffer[3] = e.getY() * phaseY;
                        }
                    } else {
                        this.mLineBuffer[2] = this.mLineBuffer[0];
                        this.mLineBuffer[3] = this.mLineBuffer[1];
                    }
                    trans.pointValuesToPixel(this.mLineBuffer);
                    if (!this.mViewPortHandler.isInBoundsRight(this.mLineBuffer[0])) {
                        break;
                    } else if (this.mViewPortHandler.isInBoundsLeft(this.mLineBuffer[2]) && ((this.mViewPortHandler.isInBoundsTop(this.mLineBuffer[1]) || this.mViewPortHandler.isInBoundsBottom(this.mLineBuffer[3])) && (this.mViewPortHandler.isInBoundsTop(this.mLineBuffer[1]) || this.mViewPortHandler.isInBoundsBottom(this.mLineBuffer[3])))) {
                        this.mRenderPaint.setColor(dataSet.getColor(j));
                        canvas.drawLines(this.mLineBuffer, 0, pointsPerEntryPair * 2, this.mRenderPaint);
                    }
                }
            }
        } else {
            if (this.mLineBuffer.length < Math.max(entryCount * pointsPerEntryPair, pointsPerEntryPair) * 2) {
                this.mLineBuffer = new float[(Math.max(entryCount * pointsPerEntryPair, pointsPerEntryPair) * 4)];
            }
            if (dataSet.getEntryForIndex(this.mXBounds.min) != null) {
                j = 0;
                int x = this.mXBounds.min;
                while (x <= this.mXBounds.range + this.mXBounds.min) {
                    Entry e1 = dataSet.getEntryForIndex(x == 0 ? 0 : x - 1);
                    Entry e2 = dataSet.getEntryForIndex(x);
                    if (!(e1 == null || e2 == null)) {
                        int i = j + 1;
                        this.mLineBuffer[j] = e1.getX();
                        j = i + 1;
                        this.mLineBuffer[i] = e1.getY() * phaseY;
                        if (isDrawSteppedEnabled) {
                            i = j + 1;
                            this.mLineBuffer[j] = e2.getX();
                            j = i + 1;
                            this.mLineBuffer[i] = e1.getY() * phaseY;
                            i = j + 1;
                            this.mLineBuffer[j] = e2.getX();
                            j = i + 1;
                            this.mLineBuffer[i] = e1.getY() * phaseY;
                        }
                        i = j + 1;
                        this.mLineBuffer[j] = e2.getX();
                        j = i + 1;
                        this.mLineBuffer[i] = e2.getY() * phaseY;
                    }
                    x++;
                }
                if (j > 0) {
                    trans.pointValuesToPixel(this.mLineBuffer);
                    int size = Math.max((this.mXBounds.range + 1) * pointsPerEntryPair, pointsPerEntryPair) * 2;
                    this.mRenderPaint.setColor(dataSet.getColor());
                    canvas.drawLines(this.mLineBuffer, 0, size, this.mRenderPaint);
                }
            }
        }
        this.mRenderPaint.setPathEffect(null);
        if (dataSet.isDrawFilledEnabled() && entryCount > 0) {
            drawLinearFill(c, dataSet, trans, this.mXBounds);
        }
    }

    protected void drawLinearFill(Canvas c, ILineDataSet dataSet, Transformer trans, XBounds bounds) {
        Path filled = this.mGenerateFilledPathBuffer;
        int startingIndex = bounds.min;
        int endingIndex = bounds.range + bounds.min;
        int iterations = 0;
        int currentStartIndex;
        int currentEndIndex;
        do {
            currentStartIndex = startingIndex + (iterations * 128);
            currentEndIndex = currentStartIndex + 128;
            if (currentEndIndex > endingIndex) {
                currentEndIndex = endingIndex;
            }
            if (currentStartIndex <= currentEndIndex) {
                generateFilledPath(dataSet, currentStartIndex, currentEndIndex, filled);
                trans.pathValueToPixel(filled);
                Drawable drawable = dataSet.getFillDrawable();
                if (drawable != null) {
                    drawFilledPath(c, filled, drawable);
                } else {
                    drawFilledPath(c, filled, dataSet.getFillColor(), dataSet.getFillAlpha());
                }
            }
            iterations++;
        } while (currentStartIndex <= currentEndIndex);
    }

    private void generateFilledPath(ILineDataSet dataSet, int startIndex, int endIndex, Path outputPath) {
        float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart);
        float phaseY = this.mAnimator.getPhaseY();
        boolean isDrawSteppedEnabled = dataSet.getMode() == LineDataSet$Mode.STEPPED;
        Path filled = outputPath;
        filled.reset();
        Entry entry = dataSet.getEntryForIndex(startIndex);
        filled.moveTo(entry.getX(), fillMin);
        filled.lineTo(entry.getX(), entry.getY() * phaseY);
        Entry currentEntry = null;
        Entry previousEntry = null;
        for (int x = startIndex + 1; x <= endIndex; x++) {
            currentEntry = dataSet.getEntryForIndex(x);
            if (isDrawSteppedEnabled && previousEntry != null) {
                filled.lineTo(currentEntry.getX(), previousEntry.getY() * phaseY);
            }
            filled.lineTo(currentEntry.getX(), currentEntry.getY() * phaseY);
            previousEntry = currentEntry;
        }
        if (currentEntry != null) {
            filled.lineTo(currentEntry.getX(), fillMin);
        }
        filled.close();
    }

    public void drawValues(Canvas c) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List<ILineDataSet> dataSets = this.mChart.getLineData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                ILineDataSet dataSet = (ILineDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    applyValueTextStyle(dataSet);
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    int valOffset = (int) (dataSet.getCircleRadius() * 1.75f);
                    if (!dataSet.isDrawCirclesEnabled()) {
                        valOffset /= 2;
                    }
                    this.mXBounds.set(this.mChart, dataSet);
                    float[] positions = trans.generateTransformedValuesLine(dataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    for (int j = 0; j < positions.length; j += 2) {
                        float x = positions[j];
                        float y = positions[j + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(x)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
                            Entry entry = dataSet.getEntryForIndex((j / 2) + this.mXBounds.min);
                            drawValue(c, dataSet.getValueFormatter(), entry.getY(), entry, i, x, y - ((float) valOffset), dataSet.getValueTextColor(j / 2));
                        }
                    }
                }
            }
        }
    }

    public void drawExtras(Canvas c) {
        drawCircles(c);
    }

    protected void drawCircles(Canvas c) {
        this.mRenderPaint.setStyle(Style.FILL);
        float phaseY = this.mAnimator.getPhaseY();
        this.mCirclesBuffer[0] = 0.0f;
        this.mCirclesBuffer[1] = 0.0f;
        List<ILineDataSet> dataSets = this.mChart.getLineData().getDataSets();
        for (int i = 0; i < dataSets.size(); i++) {
            DataSetImageCache imageCache;
            ILineDataSet dataSet = (ILineDataSet) dataSets.get(i);
            if (this.mImageCaches.containsKey(dataSet)) {
                imageCache = (DataSetImageCache) this.mImageCaches.get(dataSet);
            } else {
                DataSetImageCache dataSetImageCache = new DataSetImageCache();
                this.mImageCaches.put(dataSet, dataSetImageCache);
            }
            imageCache.ensureCircleCache(dataSet.getCircleColorCount());
            if (dataSet.isVisible() && dataSet.isDrawCirclesEnabled() && dataSet.getEntryCount() != 0) {
                this.mCirclePaintInner.setColor(dataSet.getCircleHoleColor());
                Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                this.mXBounds.set(this.mChart, dataSet);
                float circleRadius = dataSet.getCircleRadius();
                float circleHoleRadius = dataSet.getCircleHoleRadius();
                boolean drawCircleHole = dataSet.isDrawCircleHoleEnabled() && circleHoleRadius < circleRadius && circleHoleRadius > 0.0f;
                boolean drawTransparentCircleHole = drawCircleHole && dataSet.getCircleHoleColor() == 1122867;
                int boundsRangeCount = this.mXBounds.range + this.mXBounds.min;
                for (int j = this.mXBounds.min; j <= boundsRangeCount; j++) {
                    Entry e = dataSet.getEntryForIndex(j);
                    if (e == null) {
                        break;
                    }
                    this.mCirclesBuffer[0] = e.getX();
                    this.mCirclesBuffer[1] = e.getY() * phaseY;
                    trans.pointValuesToPixel(this.mCirclesBuffer);
                    if (!this.mViewPortHandler.isInBoundsRight(this.mCirclesBuffer[0])) {
                        break;
                    }
                    if (this.mViewPortHandler.isInBoundsLeft(this.mCirclesBuffer[0]) && this.mViewPortHandler.isInBoundsY(this.mCirclesBuffer[1])) {
                        int circleColor = dataSet.getCircleColor(j);
                        this.mRenderPaint.setColor(circleColor);
                        Bitmap circleBitmap = null;
                        int colorIndex = 0;
                        while (colorIndex < imageCache.circleColors.length) {
                            int tempColor = imageCache.circleColors[colorIndex];
                            Bitmap tempBitmap = imageCache.circleBitmaps[colorIndex];
                            if (tempColor != circleColor) {
                                if (tempBitmap == null) {
                                    break;
                                }
                                colorIndex++;
                            } else {
                                circleBitmap = tempBitmap;
                                break;
                            }
                        }
                        if (circleBitmap == null) {
                            circleBitmap = Bitmap.createBitmap((int) (((double) circleRadius) * 2.1d), (int) (((double) circleRadius) * 2.1d), Config.ARGB_8888);
                            Canvas canvas = new Canvas(circleBitmap);
                            imageCache.circleBitmaps[colorIndex] = circleBitmap;
                            imageCache.circleColors[colorIndex] = circleColor;
                            if (drawTransparentCircleHole) {
                                this.mCirclePathBuffer.reset();
                                this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleRadius, Direction.CW);
                                this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleHoleRadius, Direction.CCW);
                                canvas.drawPath(this.mCirclePathBuffer, this.mRenderPaint);
                            } else {
                                canvas.drawCircle(circleRadius, circleRadius, circleRadius, this.mRenderPaint);
                                if (drawCircleHole) {
                                    canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, this.mCirclePaintInner);
                                }
                            }
                        }
                        if (circleBitmap != null) {
                            c.drawBitmap(circleBitmap, this.mCirclesBuffer[0] - circleRadius, this.mCirclesBuffer[1] - circleRadius, this.mRenderPaint);
                        }
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        LineData lineData = this.mChart.getLineData();
        for (Highlight high : indices) {
            ILineDataSet set = (ILineDataSet) lineData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                Entry e = set.getEntryForXPos(high.getX());
                if (isInBoundsX(e, set)) {
                    MPPointD pix = this.mChart.getTransformer(set.getAxisDependency()).getPixelsForValues(e.getX(), e.getY() * this.mAnimator.getPhaseY());
                    high.setDraw((float) pix.x, (float) pix.y);
                    drawHighlightLines(c, (float) pix.x, (float) pix.y, set);
                }
            }
        }
    }

    public void setBitmapConfig(Config config) {
        this.mBitmapConfig = config;
        releaseBitmap();
    }

    public Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public void releaseBitmap() {
        if (this.mBitmapCanvas != null) {
            this.mBitmapCanvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        if (this.mDrawBitmap != null) {
            ((Bitmap) this.mDrawBitmap.get()).recycle();
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
