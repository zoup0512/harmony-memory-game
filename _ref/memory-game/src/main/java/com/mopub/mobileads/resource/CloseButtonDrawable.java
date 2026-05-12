package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public class CloseButtonDrawable extends BaseWidgetDrawable {
    private final Paint closeButtonPaint;
    private final float halfStrokeWidth;

    public CloseButtonDrawable() {
        this(CloseButton.STROKE_WIDTH);
    }

    public CloseButtonDrawable(float f) {
        this.halfStrokeWidth = f / 2.0f;
        this.closeButtonPaint = new Paint();
        this.closeButtonPaint.setColor(-1);
        this.closeButtonPaint.setStrokeWidth(f);
        this.closeButtonPaint.setStrokeCap(CloseButton.STROKE_CAP);
    }

    public void draw(Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        canvas.drawLine(0.0f + this.halfStrokeWidth, ((float) height) - this.halfStrokeWidth, ((float) width) - this.halfStrokeWidth, 0.0f + this.halfStrokeWidth, this.closeButtonPaint);
        canvas.drawLine(0.0f + this.halfStrokeWidth, 0.0f + this.halfStrokeWidth, ((float) width) - this.halfStrokeWidth, ((float) height) - this.halfStrokeWidth, this.closeButtonPaint);
    }
}
