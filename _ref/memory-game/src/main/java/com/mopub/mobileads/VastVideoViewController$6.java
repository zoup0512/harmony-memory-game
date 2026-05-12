package com.mopub.mobileads;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class VastVideoViewController$6 implements OnTouchListener {
    final /* synthetic */ VastVideoViewController this$0;

    VastVideoViewController$6(VastVideoViewController vastVideoViewController) {
        this.this$0 = vastVideoViewController;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int access$300;
        if (VastVideoViewController.access$200(this.this$0)) {
            access$300 = VastVideoViewController.access$300(this.this$0);
        } else {
            access$300 = this.this$0.getCurrentPosition();
        }
        if (motionEvent.getAction() == 1) {
            VastVideoViewController.access$102(this.this$0, true);
            VastVideoViewController.access$400(this.this$0).handleClose(this.this$0.getContext(), access$300);
            this.this$0.getBaseVideoViewControllerListener().onFinish();
        }
        return true;
    }
}
