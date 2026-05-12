package com.unity3d.ads.api;

class VideoPlayer$6 implements Runnable {
    final /* synthetic */ Integer val$time;

    VideoPlayer$6(Integer num) {
        this.val$time = num;
    }

    public void run() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().seekTo(this.val$time.intValue());
        }
    }
}
