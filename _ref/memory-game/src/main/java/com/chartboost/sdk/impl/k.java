package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.widget.LinearLayout;
import com.mopub.volley.DefaultRetryPolicy;

public class k extends LinearLayout {
    private final Paint a;
    private final float b = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private int c = 0;

    public k(Context context) {
        super(context);
        int round = Math.round(context.getResources().getDisplayMetrics().density * 5.0f);
        setPadding(round, round, round, round);
        setBaselineAligned(false);
        this.a = new Paint();
        this.a.setStyle(Style.FILL);
    }

    public void a(int i) {
        this.a.setColor(i);
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = getContext().getResources().getDisplayMetrics().density;
        if ((this.c & 1) > 0) {
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT * f, this.a);
        }
        if ((this.c & 2) > 0) {
            canvas.drawRect(((float) canvas.getWidth()) - (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT * f), 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.a);
        }
        if ((this.c & 4) > 0) {
            canvas.drawRect(0.0f, ((float) canvas.getHeight()) - (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT * f), (float) canvas.getWidth(), (float) canvas.getHeight(), this.a);
        }
        if ((this.c & 8) > 0) {
            canvas.drawRect(0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT * f, (float) canvas.getHeight(), this.a);
        }
    }

    public void b(int i) {
        this.c = i;
    }
}
