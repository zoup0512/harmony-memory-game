package org.nexage.sourcekit.vast.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.my.target.ads.MyTargetVideoView;

public class VastCountdown extends View {
    private int arcLoadingBackgroundColor;
    private int arcLoadingColor;
    private float arcLoadingStartAngle;
    private float arcLoadingStrokeWidth;
    private float circleCenterPointX;
    private float circleCenterPointY;
    private boolean finish;
    private int paddingInContainer;
    Paint paint = new Paint(7);
    private int percent;
    private int remainingTime;
    private int textColor;
    private float textSize;
    public Handler uiThread = new Handler();

    public VastCountdown(Context context) {
        super(context);
        initializeAttributes();
    }

    public VastCountdown(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initializeAttributes();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.circleCenterPointX = (float) (i / 2);
        this.circleCenterPointY = (float) (i2 / 2);
        this.paddingInContainer = Math.max(i, i2) / 4;
        this.textSize = (float) (Math.min(i, i2) / 3);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArcLoading(this.paint, canvas);
        drawPercents(canvas);
    }

    private void initializeAttributes() {
        this.circleCenterPointX = 54.0f;
        this.circleCenterPointY = 54.0f;
        this.arcLoadingBackgroundColor = Color.parseColor("#6b000000");
        this.arcLoadingColor = -1;
        this.arcLoadingStrokeWidth = 5.0f;
        this.percent = 100;
        this.arcLoadingStartAngle = 270.0f;
        this.textColor = Color.parseColor("#ffffff");
    }

    private void drawArcLoading(Paint paint, Canvas canvas) {
        paint.setColor(this.arcLoadingColor);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(this.arcLoadingStrokeWidth);
        RectF rectF = new RectF((float) this.paddingInContainer, (float) this.paddingInContainer, (this.circleCenterPointX * 2.0f) - ((float) this.paddingInContainer), (this.circleCenterPointX * 2.0f) - ((float) this.paddingInContainer));
        Canvas canvas2 = canvas;
        canvas2.drawArc(rectF, this.arcLoadingStartAngle, ((float) (this.percent * MyTargetVideoView.DEFAULT_VIDEO_QUALITY)) * 0.01f, false, paint);
        paint.setColor(this.arcLoadingBackgroundColor);
        paint.setStyle(Style.FILL);
        canvas.drawArc(rectF, 0.0f, 360.0f, false, paint);
    }

    private void drawPercents(Canvas canvas) {
        String str;
        if (this.finish) {
            str = "×";
        } else if (this.remainingTime == 0) {
            str = "";
        } else {
            str = String.valueOf(this.remainingTime);
        }
        Paint paint = new Paint();
        paint.setColor(this.textColor);
        paint.setTextSize(this.textSize);
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.CENTER);
        canvas.drawText(str, (float) (getMeasuredWidth() / 2), (float) ((int) (((float) (getMeasuredHeight() / 2)) - ((paint.descent() + paint.ascent()) / 2.0f))), paint);
    }

    public void changePercentage(int i, int i2) {
        this.percent = i;
        this.remainingTime = i2;
        if (i >= 100) {
            this.finish = true;
        }
        this.uiThread.post(new Runnable() {
            public void run() {
                VastCountdown.this.invalidate();
            }
        });
    }
}
