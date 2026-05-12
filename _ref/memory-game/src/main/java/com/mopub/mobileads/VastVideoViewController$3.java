package com.mopub.mobileads;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

class VastVideoViewController$3 implements OnPreparedListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ VastVideoView val$videoView;

    VastVideoViewController$3(VastVideoViewController vastVideoViewController, VastVideoView vastVideoView) {
        this.this$0 = vastVideoViewController;
        this.val$videoView = vastVideoView;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        VastVideoViewController.access$302(this.this$0, VastVideoViewController.access$700(this.this$0).getDuration());
        VastVideoViewController.access$800(this.this$0);
        if (VastVideoViewController.access$900(this.this$0) == null || VastVideoViewController.access$1000(this.this$0)) {
            this.val$videoView.prepareBlurredLastVideoFrame(VastVideoViewController.access$1100(this.this$0), VastVideoViewController.access$400(this.this$0).getDiskMediaFileUrl());
        }
        VastVideoViewController.access$1300(this.this$0).calibrateAndMakeVisible(this.this$0.getDuration(), VastVideoViewController.access$1200(this.this$0));
        VastVideoViewController.access$1400(this.this$0).calibrateAndMakeVisible(VastVideoViewController.access$1200(this.this$0));
        VastVideoViewController.access$1502(this.this$0, true);
    }
}
