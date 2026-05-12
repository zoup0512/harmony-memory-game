package com.github.mikephil.charting.formatter;

import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.FormattedStringCache.PrimFloat;
import com.yalantis.ucrop.util.FileUtils;
import java.text.DecimalFormat;

public class DefaultAxisValueFormatter implements AxisValueFormatter {
    protected int digits = 0;
    protected PrimFloat mFormattedStringCache;

    public DefaultAxisValueFormatter(int digits) {
        this.digits = digits;
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < digits; i++) {
            if (i == 0) {
                b.append(FileUtils.HIDDEN_PREFIX);
            }
            b.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.mFormattedStringCache = new PrimFloat(new DecimalFormat("###,###,###,##0" + b.toString()));
    }

    public String getFormattedValue(float value, AxisBase axis) {
        return this.mFormattedStringCache.getFormattedValue(value);
    }

    public int getDecimalDigits() {
        return this.digits;
    }
}
