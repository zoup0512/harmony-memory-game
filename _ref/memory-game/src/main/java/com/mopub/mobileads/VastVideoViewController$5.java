package com.mopub.mobileads;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class VastVideoViewController$5 implements OnErrorListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ VastVideoView val$videoView;

    VastVideoViewController$5(VastVideoViewController vastVideoViewController, VastVideoView vastVideoView) {
        this.this$0 = vastVideoViewController;
        this.val$videoView = vastVideoView;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.val$videoView.retryMediaPlayer(mediaPlayer, i, i2, VastVideoViewController.access$400(this.this$0).getDiskMediaFileUrl())) {
            return true;
        }
        VastVideoViewController.access$1600(this.this$0);
        this.this$0.makeVideoInteractable();
        this.this$0.videoError(false);
        VastVideoViewController.access$1802(this.this$0, true);
        VastVideoViewController.access$400(this.this$0).handleError(this.this$0.getContext(), VastErrorCode.GENERAL_LINEAR_AD_ERROR, this.this$0.getCurrentPosition());
        return false;
    }
}
