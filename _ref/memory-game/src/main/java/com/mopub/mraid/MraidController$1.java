package com.mopub.mraid;

import com.mopub.common.CloseableLayout.OnCloseListener;

class MraidController$1 implements OnCloseListener {
    final /* synthetic */ MraidController this$0;

    MraidController$1(MraidController mraidController) {
        this.this$0 = mraidController;
    }

    public void onClose() {
        this.this$0.handleClose();
    }
}
