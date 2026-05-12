package org.nexage.sourcekit.mraid.rtb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public class BackButton extends View {
    private Paint paint;

    public BackButton(Context context) {
        super(context);
        init();
    }

    public BackButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public BackButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    void init() {
        this.paint = new Paint(1);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setColor(-16711936);
    }

    protected void onDraw(Canvas canvas) {
        int round = Math.round((getContext().getResources().getDisplayMetrics().xdpi / 160.0f) * CloseButton.TEXT_SIZE_SP);
        int height = getHeight();
        int i = (height - round) / 2;
        canvas.drawLine((float) (round / 2), (float) (height / 2), (float) round, (float) i, this.paint);
        canvas.drawLine((float) (round / 2), (float) (height / 2), (float) round, (float) (height - i), this.paint);
        super.onDraw(canvas);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(Math.round((getContext().getResources().getDisplayMetrics().xdpi / 160.0f) * 30.0f), MeasureSpec.getMode(i)), i2);
    }
}
