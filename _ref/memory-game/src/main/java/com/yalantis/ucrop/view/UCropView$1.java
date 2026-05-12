package com.yalantis.ucrop.view;

import com.yalantis.ucrop.callback.CropBoundsChangeListener;

class UCropView$1 implements CropBoundsChangeListener {
    final /* synthetic */ UCropView this$0;

    UCropView$1(UCropView this$0) {
        this.this$0 = this$0;
    }

    public void onCropAspectRatioChanged(float cropRatio) {
        UCropView.access$000(this.this$0).setTargetAspectRatio(cropRatio);
    }
}
