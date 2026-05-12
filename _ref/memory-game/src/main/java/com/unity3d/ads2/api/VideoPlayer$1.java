package com.unity3d.ads2.api;

class VideoPlayer$1 implements Runnable {
    final /* synthetic */ Integer val$milliseconds;

    VideoPlayer$1(Integer num) {
        this.val$milliseconds = num;
    }

    public void run() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().setProgressEventInterval(this.val$milliseconds.intValue());
        }
    }
}
