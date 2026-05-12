package com.unity3d.ads.api;

import android.os.Build.VERSION;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.video.VideoPlayerError;
import com.unity3d.ads.video.VideoPlayerEvent;
import com.unity3d.ads.video.VideoPlayerView;
import com.unity3d.ads.webview.WebViewEventCategory;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;

public class VideoPlayer {
    private static VideoPlayerView _videoPlayerView;

    public static void setVideoPlayerView(VideoPlayerView videoPlayerView) {
        _videoPlayerView = videoPlayerView;
    }

    public static VideoPlayerView getVideoPlayerView() {
        return _videoPlayerView;
    }

    @WebViewExposed
    public static void setProgressEventInterval(Integer num, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new 1(num));
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getProgressEventInterval(WebViewCallback webViewCallback) {
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[]{Integer.valueOf(getVideoPlayerView().getProgressEventInterval())});
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void prepare(String str, Double d, WebViewCallback webViewCallback) {
        DeviceLog.debug("Preparing video for playback: " + str);
        Utilities.runOnUiThread(new 2(str, d));
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[]{str});
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void play(WebViewCallback webViewCallback) {
        DeviceLog.debug("Starting playback of prepared video");
        Utilities.runOnUiThread(new 3());
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void pause(WebViewCallback webViewCallback) {
        DeviceLog.debug("Pausing current video");
        Utilities.runOnUiThread(new 4());
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void stop(WebViewCallback webViewCallback) {
        DeviceLog.debug("Stopping current video");
        Utilities.runOnUiThread(new 5());
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void seekTo(Integer num, WebViewCallback webViewCallback) {
        DeviceLog.debug("Seeking video to time: " + num);
        Utilities.runOnUiThread(new 6(num));
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getCurrentPosition(WebViewCallback webViewCallback) {
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[]{Integer.valueOf(getVideoPlayerView().getCurrentPosition())});
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void getVolume(WebViewCallback webViewCallback) {
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[]{Float.valueOf(getVideoPlayerView().getVolume())});
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setVolume(Double d, WebViewCallback webViewCallback) {
        DeviceLog.debug("Setting video volume: " + d);
        if (getVideoPlayerView() != null) {
            getVideoPlayerView().setVolume(Float.valueOf(d.floatValue()));
            webViewCallback.invoke(new Object[]{d});
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setInfoListenerEnabled(boolean z, WebViewCallback webViewCallback) {
        if (VERSION.SDK_INT <= 16) {
            webViewCallback.error(VideoPlayerError.API_LEVEL_ERROR, new Object[]{Integer.valueOf(VERSION.SDK_INT), Integer.valueOf(17), Boolean.valueOf(z)});
        } else if (getVideoPlayerView() != null) {
            getVideoPlayerView().setInfoListenerEnabled(z);
            webViewCallback.invoke(new Object[]{WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, Boolean.valueOf(z)});
        } else {
            webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }
}
