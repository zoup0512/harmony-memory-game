package com.unity3d.ads.api;

class VideoPlayer$4 implements Runnable {
    VideoPlayer$4() {
    }

    public void run() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().pause();
        }
    }
}
