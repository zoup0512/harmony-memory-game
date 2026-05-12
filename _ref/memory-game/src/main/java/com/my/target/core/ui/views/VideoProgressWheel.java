package com.my.target.core.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import android.view.View.MeasureSpec;
import com.mopub.mobileads.resource.DrawableConstants;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.core.utils.l;

public class VideoProgressWheel extends View {
    private final Paint a = new Paint();
    private final Paint b = new Paint();
    private final Paint c = new Paint();
    private final l d;
    private RectF e = new RectF();
    private long f = 0;
    private float g = 0.0f;
    private float h = 0.0f;
    private float i = 230.0f;
    private boolean j = false;
    private int k;

    public VideoProgressWheel(Context context) {
        super(context);
        this.d = new l(context);
    }

    protected void onMeasure(int i, int i2) {
        int a = (this.d.a(26) + getPaddingLeft()) + getPaddingRight();
        int a2 = (this.d.a(26) + getPaddingTop()) + getPaddingBottom();
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            a = size;
        } else if (mode == Integer.MIN_VALUE) {
            a = Math.min(a, size);
        }
        if (mode2 == 1073741824 || mode == 1073741824) {
            a2 = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            a2 = Math.min(a2, size2);
        }
        setMeasuredDimension(a, a2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        this.e = new RectF((float) (getPaddingLeft() + this.d.a(1)), (float) (paddingTop + this.d.a(1)), (float) ((i - getPaddingRight()) - this.d.a(1)), (float) ((i2 - paddingBottom) - this.d.a(1)));
        this.a.setColor(-1);
        this.a.setAntiAlias(true);
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeWidth((float) this.d.a(1));
        this.b.setColor(DrawableConstants.TRANSPARENT_GRAY);
        this.b.setAntiAlias(true);
        this.b.setStyle(Style.FILL);
        this.b.setStrokeWidth((float) this.d.a(4));
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        canvas.drawOval(this.e, this.b);
        if (this.g != this.h) {
            this.g = Math.min(((((float) (SystemClock.uptimeMillis() - this.f)) / 1000.0f) * this.i) + this.g, this.h);
            this.f = SystemClock.uptimeMillis();
            z = true;
        } else {
            z = false;
        }
        float f = this.g;
        if (isInEditMode()) {
            f = 360.0f;
        }
        canvas.drawArc(this.e, RadialCountdown.START_ANGLE, f, false, this.a);
        this.c.setColor(-1);
        this.c.setTextSize((float) this.d.a(12));
        this.c.setTextAlign(Align.CENTER);
        this.c.setAntiAlias(true);
        canvas.drawText(String.valueOf(this.k), (float) ((int) this.e.centerX()), (float) ((int) (this.e.centerY() - ((this.c.descent() + this.c.ascent()) / 2.0f))), this.c);
        if (z) {
            invalidate();
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            this.f = SystemClock.uptimeMillis();
        }
    }

    public void setDigit(int i) {
        this.k = i;
    }

    public void setProgress(float f) {
        if (this.j) {
            this.g = 0.0f;
            this.j = false;
        }
        if (f > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        if (f != this.h) {
            if (this.g == this.h) {
                this.f = SystemClock.uptimeMillis();
            }
            this.h = Math.min(f * 360.0f, 360.0f);
            invalidate();
        }
    }

    public void setMax(float f) {
        if (f > 0.0f) {
            this.i = 360.0f / f;
        }
    }
}
