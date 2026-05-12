package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.view.CropImageView;

public class ao extends u {
    private float c = 30.0f;
    private float d = 2.0f;
    private float e = CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER;
    private float f = 3.0f;
    private float g = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    public ao(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    protected float a() {
        return this.c * this.g;
    }

    public void a(float f) {
        this.g = f;
    }

    public void a(int i) {
        a(((float) i) / this.c);
    }

    protected float b() {
        return this.e * this.g;
    }

    protected float c() {
        return this.f * this.g;
    }

    protected float d() {
        return a() / 2.0f;
    }

    protected float e() {
        return this.d * this.g;
    }

    protected float f() {
        return d() - e();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = d();
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-16777216);
        canvas.drawCircle(d, d, f(), paint2);
        Paint paint3 = new Paint(paint);
        paint3.setStyle(Style.STROKE);
        paint3.setStrokeWidth(c());
        float b = b();
        float a = a() - b;
        canvas.drawLine(b, b, a, a, paint3);
        canvas.drawLine(b, a, a, b, paint3);
    }
}
