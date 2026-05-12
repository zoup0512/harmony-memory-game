package com.unity3d.ads2.api;

class VideoPlayer$5 implements Runnable {
    VideoPlayer$5() {
    }

    public void run() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().stop();
        }
    }
}
