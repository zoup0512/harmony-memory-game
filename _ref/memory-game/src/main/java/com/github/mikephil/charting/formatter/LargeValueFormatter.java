package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache.PrimDouble;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class LargeValueFormatter implements ValueFormatter, AxisValueFormatter {
    private static final int MAX_LENGTH = 5;
    private static String[] SUFFIX = new String[]{"", "k", "m", "b", "t"};
    protected PrimDouble mFormattedStringCache;
    private String mText;

    public LargeValueFormatter() {
        this.mText = "";
        this.mFormattedStringCache = new PrimDouble(new DecimalFormat("###E00"));
    }

    public LargeValueFormatter(String appendix) {
        this();
        this.mText = appendix;
    }

    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return makePretty((double) value) + this.mText;
    }

    public String getFormattedValue(float value, AxisBase axis) {
        return makePretty((double) value) + this.mText;
    }

    public void setAppendix(String appendix) {
        this.mText = appendix;
    }

    public void setSuffix(String[] suff) {
        SUFFIX = suff;
    }

    private String makePretty(double number) {
        String r = this.mFormattedStringCache.getFormattedValue(number);
        r = r.replaceAll("E[0-9][0-9]", SUFFIX[Integer.valueOf(Character.getNumericValue(r.charAt(r.length() - 2)) + "" + Character.getNumericValue(r.charAt(r.length() - 1))).intValue() / 3]);
        while (true) {
            if (r.length() <= 5 && !r.matches("[0-9]+\\.[a-z]")) {
                return r;
            }
            r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
        }
    }

    public int getDecimalDigits() {
        return 0;
    }
}
