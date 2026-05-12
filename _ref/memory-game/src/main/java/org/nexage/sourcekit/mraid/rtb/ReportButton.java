package org.nexage.sourcekit.mraid.rtb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public class ReportButton extends View {
    private Paint bgPaint;
    private Paint crossPaint;

    public ReportButton(Context context) {
        super(context);
        init();
    }

    public ReportButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ReportButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    void init() {
        this.bgPaint = new Paint(1);
        this.bgPaint.setStyle(Style.FILL);
        this.crossPaint = new Paint(1);
        this.crossPaint.setColor(-16711936);
        this.crossPaint.setStyle(Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int round = Math.round((getContext().getResources().getDisplayMetrics().xdpi / 160.0f) * CloseButton.STROKE_WIDTH);
        int height = getHeight();
        if (round > height) {
            i = height;
        } else {
            i = round;
        }
        canvas.drawRect(0.0f, (float) (height - i), (float) i, (float) height, this.bgPaint);
        canvas.drawLine((float) (i / 4), (float) (height - ((i * 3) / 4)), (float) ((i * 3) / 4), (float) (height - (i / 4)), this.crossPaint);
        canvas.drawLine((float) (i / 4), (float) (height - (i / 4)), (float) ((i * 3) / 4), (float) (height - ((i * 3) / 4)), this.crossPaint);
        super.onDraw(canvas);
    }
}
