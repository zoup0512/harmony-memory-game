package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import java.util.ArrayList;
import java.util.List;

public class PieDataSet extends DataSet<PieEntry> implements IPieDataSet {
    private float mShift = RadialCountdown.TEXT_SIZE_SP;
    private float mSliceSpace = 0.0f;
    private int mValueLineColor = -16777216;
    private float mValueLinePart1Length = 0.3f;
    private float mValueLinePart1OffsetPercentage = 75.0f;
    private float mValueLinePart2Length = 0.4f;
    private boolean mValueLineVariableLength = true;
    private float mValueLineWidth = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private ValuePosition mXValuePosition = ValuePosition.INSIDE_SLICE;
    private ValuePosition mYValuePosition = ValuePosition.INSIDE_SLICE;

    public enum ValuePosition {
        INSIDE_SLICE,
        OUTSIDE_SLICE
    }

    public PieDataSet(List<PieEntry> yVals, String label) {
        super(yVals, label);
    }

    public DataSet<PieEntry> copy() {
        List<PieEntry> yVals = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            yVals.add(((PieEntry) this.mValues.get(i)).copy());
        }
        PieDataSet copied = new PieDataSet(yVals, getLabel());
        copied.mColors = this.mColors;
        copied.mSliceSpace = this.mSliceSpace;
        copied.mShift = this.mShift;
        return copied;
    }

    public void setSliceSpace(float spaceDp) {
        if (spaceDp > CloseButton.TEXT_SIZE_SP) {
            spaceDp = CloseButton.TEXT_SIZE_SP;
        }
        if (spaceDp < 0.0f) {
            spaceDp = 0.0f;
        }
        this.mSliceSpace = Utils.convertDpToPixel(spaceDp);
    }

    public float getSliceSpace() {
        return this.mSliceSpace;
    }

    public void setSelectionShift(float shift) {
        this.mShift = Utils.convertDpToPixel(shift);
    }

    public float getSelectionShift() {
        return this.mShift;
    }

    public ValuePosition getXValuePosition() {
        return this.mXValuePosition;
    }

    public void setXValuePosition(ValuePosition xValuePosition) {
        this.mXValuePosition = xValuePosition;
    }

    public ValuePosition getYValuePosition() {
        return this.mYValuePosition;
    }

    public void setYValuePosition(ValuePosition yValuePosition) {
        this.mYValuePosition = yValuePosition;
    }

    public int getValueLineColor() {
        return this.mValueLineColor;
    }

    public void setValueLineColor(int valueLineColor) {
        this.mValueLineColor = valueLineColor;
    }

    public float getValueLineWidth() {
        return this.mValueLineWidth;
    }

    public void setValueLineWidth(float valueLineWidth) {
        this.mValueLineWidth = valueLineWidth;
    }

    public float getValueLinePart1OffsetPercentage() {
        return this.mValueLinePart1OffsetPercentage;
    }

    public void setValueLinePart1OffsetPercentage(float valueLinePart1OffsetPercentage) {
        this.mValueLinePart1OffsetPercentage = valueLinePart1OffsetPercentage;
    }

    public float getValueLinePart1Length() {
        return this.mValueLinePart1Length;
    }

    public void setValueLinePart1Length(float valueLinePart1Length) {
        this.mValueLinePart1Length = valueLinePart1Length;
    }

    public float getValueLinePart2Length() {
        return this.mValueLinePart2Length;
    }

    public void setValueLinePart2Length(float valueLinePart2Length) {
        this.mValueLinePart2Length = valueLinePart2Length;
    }

    public boolean isValueLineVariableLength() {
        return this.mValueLineVariableLength;
    }

    public void setValueLineVariableLength(boolean valueLineVariableLength) {
        this.mValueLineVariableLength = valueLineVariableLength;
    }
}
