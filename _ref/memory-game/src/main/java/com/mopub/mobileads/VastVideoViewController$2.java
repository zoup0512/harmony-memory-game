package com.mopub.mobileads;

import android.app.Activity;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class VastVideoViewController$2 implements OnGlobalLayoutListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Activity val$activity;

    VastVideoViewController$2(VastVideoViewController vastVideoViewController, Activity activity) {
        this.this$0 = vastVideoViewController;
        this.val$activity = activity;
    }

    public void onGlobalLayout() {
        VastVideoViewController.access$502(this.this$0, this.this$0.createAdsByView(this.val$activity));
        VastVideoViewController.access$600(this.this$0).getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}
