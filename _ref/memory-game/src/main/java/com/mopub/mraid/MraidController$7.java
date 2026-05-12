package com.mopub.mraid;

import android.util.DisplayMetrics;
import android.view.View;

class MraidController$7 implements Runnable {
    final /* synthetic */ MraidController this$0;
    final /* synthetic */ View val$currentWebView;
    final /* synthetic */ Runnable val$successRunnable;

    MraidController$7(MraidController mraidController, View view, Runnable runnable) {
        this.this$0 = mraidController;
        this.val$currentWebView = view;
        this.val$successRunnable = runnable;
    }

    public void run() {
        try {
            DisplayMetrics displayMetrics = MraidController.access$600(this.this$0).getResources().getDisplayMetrics();
            MraidController.access$1100(this.this$0).setScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
            int[] iArr = new int[2];
            View access$1200 = MraidController.access$1200(this.this$0);
            access$1200.getLocationOnScreen(iArr);
            MraidController.access$1100(this.this$0).setRootViewPosition(iArr[0], iArr[1], access$1200.getWidth(), access$1200.getHeight());
            MraidController.access$1300(this.this$0).getLocationOnScreen(iArr);
            MraidController.access$1100(this.this$0).setDefaultAdPosition(iArr[0], iArr[1], MraidController.access$1300(this.this$0).getWidth(), MraidController.access$1300(this.this$0).getHeight());
            this.val$currentWebView.getLocationOnScreen(iArr);
            MraidController.access$1100(this.this$0).setCurrentAdPosition(iArr[0], iArr[1], this.val$currentWebView.getWidth(), this.val$currentWebView.getHeight());
            MraidController.access$200(this.this$0).notifyScreenMetrics(MraidController.access$1100(this.this$0));
            if (MraidController.access$100(this.this$0).isAttached()) {
                MraidController.access$100(this.this$0).notifyScreenMetrics(MraidController.access$1100(this.this$0));
            }
            if (this.val$successRunnable != null) {
                this.val$successRunnable.run();
            }
        } catch (Exception e) {
        }
    }
}
