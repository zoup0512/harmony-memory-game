package com.roughike.bottombar;

import android.os.Build.VERSION;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class BottomBar$3 implements OnGlobalLayoutListener {
    final /* synthetic */ BottomBar this$0;
    final /* synthetic */ OnSizeDeterminedListener val$listener;

    BottomBar$3(BottomBar this$0, OnSizeDeterminedListener onSizeDeterminedListener) {
        this.this$0 = this$0;
        this.val$listener = onSizeDeterminedListener;
    }

    public void onGlobalLayout() {
        this.val$listener.onSizeReady(BottomBar.access$200(this.this$0) ? BottomBar.access$300(this.this$0).getWidth() : BottomBar.access$300(this.this$0).getHeight());
        ViewTreeObserver obs = BottomBar.access$300(this.this$0).getViewTreeObserver();
        if (VERSION.SDK_INT >= 16) {
            obs.removeOnGlobalLayoutListener(this);
        } else {
            obs.removeGlobalOnLayoutListener(this);
        }
    }
}
