package com.my.target.core.ui.views;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class AdView extends RelativeLayout {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private float d = 0.0f;

    public AdView(Context context) {
        super(context);
    }

    public void setDesiredSize(int i, int i2) {
        this.a = i;
        this.c = i2;
        if (i <= 0 || i2 <= 0) {
            this.d = 0.0f;
        } else {
            this.d = ((float) i) / ((float) i2);
        }
    }

    public void setMaxWidth(int i) {
        this.b = i;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.d > 0.0f && size > 0 && size2 > 0) {
            float f = ((float) size) / ((float) size2);
            int i3 = this.a;
            int i4 = this.c;
            if (i3 <= size && i4 <= size2) {
                size2 = i3;
            } else if (f > this.d) {
                int i5 = size2;
                size2 = (int) (((float) size2) * this.d);
                i4 = i5;
            } else {
                i4 = (int) (((float) size) / this.d);
                size2 = size;
            }
            super.onMeasure(MeasureSpec.makeMeasureSpec(size2, 1073741824), MeasureSpec.makeMeasureSpec(i4, 1073741824));
        } else if (size <= 0 || size2 <= 0) {
            super.onMeasure(i, i2);
        } else {
            if (this.b > 0 && size > this.b) {
                size = this.b;
            }
            super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
        }
    }
}
