package org.nexage.sourcekit.vast.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class VastLinearCountdown extends View {
    private int lineColor;
    private float lineLength;
    private float lineWidth;
    Paint paint = new Paint(7);
    private float percent;
    public Handler uiThread = new Handler();

    public VastLinearCountdown(Context context) {
        super(context);
        initializeAttributes();
    }

    public VastLinearCountdown(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initializeAttributes();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.lineLength = (float) i;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(this.lineWidth);
        this.paint.setColor(this.lineColor);
        drawLineLoading(this.paint, canvas);
    }

    private void initializeAttributes() {
        this.lineColor = -1;
        this.lineWidth = CtaButton.TEXT_SIZE_SP;
        this.percent = 0.0f;
    }

    private void drawLineLoading(Paint paint, Canvas canvas) {
        canvas.drawLine(0.0f, 0.0f, (this.lineLength * this.percent) / 100.0f, 0.0f, paint);
    }

    public void changePercentage(float f) {
        this.percent = f;
        this.uiThread.post(new Runnable() {
            public void run() {
                VastLinearCountdown.this.invalidate();
            }
        });
    }
}
