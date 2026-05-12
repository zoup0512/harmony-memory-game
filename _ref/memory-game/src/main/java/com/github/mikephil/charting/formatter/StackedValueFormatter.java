package com.github.mikephil.charting.formatter;

import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache.Generic;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.yalantis.ucrop.util.FileUtils;
import java.text.DecimalFormat;

public class StackedValueFormatter implements ValueFormatter {
    private String mAppendix;
    private boolean mDrawWholeStack;
    private DecimalFormat mFormat;
    private Generic mFormattedStringCache;
    private Generic mFormattedStringCacheWholeStack;

    public StackedValueFormatter(boolean drawWholeStack, String appendix, int decimals) {
        this.mDrawWholeStack = drawWholeStack;
        this.mAppendix = appendix;
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < decimals; i++) {
            if (i == 0) {
                b.append(FileUtils.HIDDEN_PREFIX);
            }
            b.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.mFormattedStringCache = new Generic(new DecimalFormat("###,###,###,##0" + b.toString()));
        this.mFormattedStringCacheWholeStack = new Generic(new DecimalFormat("###,###,###,##0" + b.toString()));
    }

    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        Generic chosenCache = this.mFormattedStringCache;
        int chosenIndex = dataSetIndex;
        float chosenValue = value;
        if (!this.mDrawWholeStack && (entry instanceof BarEntry)) {
            BarEntry barEntry = (BarEntry) entry;
            float[] vals = barEntry.getYVals();
            if (vals != null) {
                if (vals[vals.length - 1] == value) {
                    chosenCache = this.mFormattedStringCacheWholeStack;
                    chosenValue = barEntry.getY();
                } else {
                    chosenCache = null;
                }
            }
        }
        if (chosenCache == null) {
            return "";
        }
        return chosenCache.getFormattedValue(Float.valueOf(chosenValue), Integer.valueOf(chosenIndex)) + this.mAppendix;
    }
}
