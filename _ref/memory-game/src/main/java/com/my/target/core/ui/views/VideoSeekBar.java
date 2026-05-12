package com.my.target.core.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public class VideoSeekBar extends View {
    Paint a = new Paint();
    private int b;
    private int c;
    private int d = 20;

    public VideoSeekBar(Context context) {
        super(context);
        setBackgroundColor(-1996488705);
        this.a.setColor(-16733198);
        this.a.setStrokeWidth(CloseButton.TEXT_SIZE_SP);
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeJoin(Join.ROUND);
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ VideoSeekBar a;

            {
                this.a = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(i, MeasureSpec.makeMeasureSpec(this.d, 1073741824));
    }

    protected void onDraw(Canvas canvas) {
        if (getWidth() != 0 && getHeight() != 0 && this.b != 0 && this.c != 0) {
            Canvas canvas2 = canvas;
            canvas2.drawLine(0.0f, (float) (getHeight() / 2), (((float) getWidth()) / ((float) this.b)) * ((float) this.c), (float) (getHeight() / 2), this.a);
        }
    }

    public void setHeight(int i) {
        this.d = i;
        this.a.setStrokeWidth((float) i);
    }

    public void setMax(int i) {
        this.b = i;
    }

    public void setProgress(int i) {
        this.c = i;
        invalidate();
    }
}
