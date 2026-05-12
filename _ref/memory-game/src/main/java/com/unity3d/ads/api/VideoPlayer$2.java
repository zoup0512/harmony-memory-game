package com.unity3d.ads.api;

class VideoPlayer$2 implements Runnable {
    final /* synthetic */ Double val$initialVolume;
    final /* synthetic */ String val$url;

    VideoPlayer$2(String str, Double d) {
        this.val$url = str;
        this.val$initialVolume = d;
    }

    public void run() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().prepare(this.val$url, Float.valueOf(this.val$initialVolume.floatValue()));
        }
    }
}
