package com.github.mikephil.charting.utils;

import com.mopub.volley.DefaultRetryPolicy;

public class TransformerHorizontalBarChart extends Transformer {
    public TransformerHorizontalBarChart(ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
    }

    public void prepareMatrixOffset(boolean inverted) {
        this.mMatrixOffset.reset();
        if (inverted) {
            this.mMatrixOffset.setTranslate(-(this.mViewPortHandler.getChartWidth() - this.mViewPortHandler.offsetRight()), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
            this.mMatrixOffset.postScale(-1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            return;
        }
        this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
    }
}
