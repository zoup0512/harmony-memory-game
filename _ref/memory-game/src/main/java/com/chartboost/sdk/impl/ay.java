package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.k;

public class ay extends ImageView {
    protected TextView a = null;
    private k b = null;

    public ay(Context context) {
        super(context);
    }

    public void a(k kVar) {
        if (this.b != kVar) {
            this.b = kVar;
            setImageDrawable(new BitmapDrawable(kVar.f()));
        }
    }

    public void setImageBitmap(Bitmap adImage) {
        this.b = null;
        setImageDrawable(new BitmapDrawable(adImage));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    protected void a(Canvas canvas) {
        if (this.a != null) {
            this.a.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            this.a.setEnabled(isEnabled());
            this.a.setSelected(isSelected());
            if (isFocused()) {
                this.a.requestFocus();
            } else {
                this.a.clearFocus();
            }
            this.a.setPressed(isPressed());
            this.a.draw(canvas);
        }
    }
}
