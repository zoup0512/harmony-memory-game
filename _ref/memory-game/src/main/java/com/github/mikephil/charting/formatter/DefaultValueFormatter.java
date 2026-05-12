package com.github.mikephil.charting.formatter;

import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache.Generic;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.yalantis.ucrop.util.FileUtils;
import java.text.DecimalFormat;

public class DefaultValueFormatter implements ValueFormatter {
    protected int mDecimalDigits;
    protected Generic<Integer, Float> mFormattedStringCache;

    public DefaultValueFormatter(int digits) {
        setup(digits);
    }

    public void setup(int digits) {
        this.mDecimalDigits = digits;
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < digits; i++) {
            if (i == 0) {
                b.append(FileUtils.HIDDEN_PREFIX);
            }
            b.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.mFormattedStringCache = new Generic(new DecimalFormat("###,###,###,##0" + b.toString()));
    }

    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return this.mFormattedStringCache.getFormattedValue(Float.valueOf(value), Integer.valueOf(dataSetIndex));
    }

    public int getDecimalDigits() {
        return this.mDecimalDigits;
    }
}
