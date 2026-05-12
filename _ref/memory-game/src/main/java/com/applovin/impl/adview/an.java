package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.volley.DefaultRetryPolicy;

public class an extends u {
    private float c = 30.0f;
    private float d = 2.0f;
    private float e = CloseButton.STROKE_WIDTH;
    private float f = 2.0f;
    private float g = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    public an(AppLovinSdk appLovinSdk, Context context) {
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

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = d();
        Paint paint = new Paint(1);
        paint.setARGB(80, 0, 0, 0);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(Style.STROKE);
        paint2.setStrokeWidth(c());
        float b = b();
        float a = a() - b;
        canvas.drawLine(b, b, a, a, paint2);
        canvas.drawLine(b, a, a, b, paint2);
    }
}
