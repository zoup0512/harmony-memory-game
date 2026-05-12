package com.appodeal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class AppodealMediaView extends RelativeLayout {
    public AppodealMediaView(Context context) {
        super(context);
    }

    public AppodealMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AppodealMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public AppodealMediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(size, measuredWidth) : measuredWidth;
        }
        mode = (int) (0.5625f * ((float) size));
        if (mode2 != 1073741824 || size2 >= mode) {
            size2 = mode;
            mode = size;
        } else {
            mode = (int) (1.7777778f * ((float) size2));
        }
        if (Math.abs(size2 - measuredHeight) >= 2 || Math.abs(mode - measuredWidth) >= 2) {
            getLayoutParams().width = mode;
            getLayoutParams().height = size2;
        }
        super.onMeasure(i, i2);
    }
}
