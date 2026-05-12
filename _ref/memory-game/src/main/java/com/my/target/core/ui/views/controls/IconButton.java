package com.my.target.core.ui.views.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.view.CropImageView;

public class IconButton extends View {
    private Rect a;
    private Bitmap b;
    private Paint c = new Paint();
    private ColorFilter d = new LightingColorFilter(-3355444, 1);
    private float e;
    private int f;
    private int g;
    private int h;

    public IconButton(Context context) {
        super(context);
        this.c.setFilterBitmap(true);
        this.e = getContext().getResources().getDisplayMetrics().density;
        this.f = (int) ((CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER * this.e) + 0.5f);
        this.a = new Rect();
    }

    public void setBitmap(Bitmap bitmap, Boolean bool) {
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.b = bitmap;
        if (this.b == null) {
            this.h = 0;
            this.g = 0;
        } else if (bool.booleanValue()) {
            if (this.e > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                f = 2.0f;
            }
            this.h = (int) ((((float) this.b.getHeight()) / f) * this.e);
            this.g = (int) ((((float) this.b.getWidth()) / f) * this.e);
        } else {
            this.g = this.b.getWidth();
            this.h = this.b.getHeight();
        }
        setMeasuredDimension(this.g + (this.f * 2), this.h + (this.f * 2));
        requestLayout();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.c.setColorFilter(this.d);
                invalidate();
                return true;
            case 1:
                if (motionEvent.getX() >= 0.0f && motionEvent.getX() <= ((float) getMeasuredWidth()) && motionEvent.getY() >= 0.0f && motionEvent.getY() <= ((float) getMeasuredHeight())) {
                    performClick();
                    break;
                }
            case 3:
                break;
            default:
                return super.onTouchEvent(motionEvent);
        }
        this.c.setColorFilter(null);
        invalidate();
        return true;
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b != null) {
            this.a.left = this.f;
            this.a.top = this.f;
            this.a.right = this.g + this.f;
            this.a.bottom = this.h + this.f;
            canvas.drawBitmap(this.b, null, this.a, this.c);
        }
    }
}
