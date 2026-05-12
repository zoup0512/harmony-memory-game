package com.mopub.mobileads;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class VastVideoViewController$1 implements OnTouchListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Activity val$activity;

    VastVideoViewController$1(VastVideoViewController vastVideoViewController, Activity activity) {
        this.this$0 = vastVideoViewController;
        this.val$activity = activity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && VastVideoViewController.access$000(this.this$0)) {
            VastVideoViewController.access$102(this.this$0, true);
            this.this$0.broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
            VastVideoViewController.access$400(this.this$0).handleClickForResult(this.val$activity, VastVideoViewController.access$200(this.this$0) ? VastVideoViewController.access$300(this.this$0) : this.this$0.getCurrentPosition(), 1);
        }
        return true;
    }
}
