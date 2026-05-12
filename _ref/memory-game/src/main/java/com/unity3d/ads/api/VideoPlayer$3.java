package com.unity3d.ads.api;

class VideoPlayer$3 implements Runnable {
    VideoPlayer$3() {
    }

    public void run() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().play();
        }
    }
}
