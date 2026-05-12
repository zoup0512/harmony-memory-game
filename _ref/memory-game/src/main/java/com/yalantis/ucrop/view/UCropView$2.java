package com.yalantis.ucrop.view;

import android.graphics.RectF;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;

class UCropView$2 implements OverlayViewChangeListener {
    final /* synthetic */ UCropView this$0;

    UCropView$2(UCropView this$0) {
        this.this$0 = this$0;
    }

    public void onCropRectUpdated(RectF cropRect) {
        UCropView.access$100(this.this$0).setCropRect(cropRect);
    }
}
