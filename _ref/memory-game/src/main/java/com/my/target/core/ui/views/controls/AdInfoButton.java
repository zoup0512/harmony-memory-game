package com.my.target.core.ui.views.controls;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import com.my.target.Tracer;
import com.yalantis.ucrop.view.CropImageView;

public class AdInfoButton extends View implements OnClickListener {
    private static String a = "#99333333";
    private static String b = "debug";
    private Paint c = new Paint();
    private float d;
    private float e;
    private float f;
    private float g;
    private String h;
    private int i;

    public void setUrl(String str) {
        this.h = str;
    }

    public AdInfoButton(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor(a));
        float f = context.getResources().getDisplayMetrics().density;
        this.i = (int) ((60.0f * f) + 0.5f);
        this.c.setTextSize((float) ((int) ((f * CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER) + 0.5f)));
        this.c.setColor(-1);
        this.c.setAntiAlias(true);
        this.d = this.c.measureText(b);
        this.e = this.c.getTextSize();
        setOnClickListener(this);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE && size > this.i) {
            size = this.i;
        }
        if (mode2 == Integer.MIN_VALUE && size2 > this.i) {
            size2 = this.i;
        }
        setMeasuredDimension(size, size2);
        this.f = (((float) size) - this.d) / 2.0f;
        this.g = (((float) size2) + this.e) / 2.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(b, this.f, this.g, this.c);
    }

    public void onClick(View view) {
        if (this.h != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.h));
                intent.addFlags(268435456);
                getContext().startActivity(intent);
            } catch (Throwable th) {
                Tracer.d(th.getMessage());
            }
        }
    }
}
