package com.unity3d.ads2.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Build.VERSION;
import android.widget.VideoView;
import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.webview.WebViewApp;
import com.unity3d.ads2.webview.WebViewEventCategory;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayerView extends VideoView {
    private boolean _infoListenerEnabled = true;
    private MediaPlayer _mediaPlayer = null;
    private int _progressEventInterval = 500;
    private Timer _videoTimer;
    private String _videoUrl;
    private Float _volume = null;

    public VideoPlayerView(Context context) {
        super(context);
    }

    private void startVideoProgressTimer() {
        this._videoTimer = new Timer();
        this._videoTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                boolean isPlaying;
                Exception e;
                try {
                    isPlaying = VideoPlayerView.this.isPlaying();
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PROGRESS, Integer.valueOf(VideoPlayerView.this.getCurrentPosition()));
                    } catch (IllegalStateException e2) {
                        e = e2;
                    }
                } catch (IllegalStateException e3) {
                    e = e3;
                    isPlaying = false;
                    DeviceLog.exception("Exception while sending current position to webapp", e);
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.ILLEGAL_STATE, VideoPlayerEvent.PROGRESS, Boolean.valueOf(isPlaying));
                }
            }
        }, (long) this._progressEventInterval, (long) this._progressEventInterval);
    }

    public void stopVideoProgressTimer() {
        if (this._videoTimer != null) {
            this._videoTimer.cancel();
            this._videoTimer.purge();
            this._videoTimer = null;
        }
    }

    public boolean prepare(String str, final Float f) {
        DeviceLog.entered();
        this._videoUrl = str;
        setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (mediaPlayer != null) {
                    VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                VideoPlayerView.this.setVolume(f);
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARED, Integer.valueOf(mediaPlayer.getDuration()), Integer.valueOf(mediaPlayer.getVideoWidth()), Integer.valueOf(mediaPlayer.getVideoHeight()), VideoPlayerView.this._videoUrl);
            }
        });
        setOnErrorListener(new OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                if (mediaPlayer != null) {
                    VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.GENERIC_ERROR, Integer.valueOf(i), Integer.valueOf(i2), VideoPlayerView.this._videoUrl);
                VideoPlayerView.this.stopVideoProgressTimer();
                return true;
            }
        });
        setInfoListenerEnabled(this._infoListenerEnabled);
        try {
            setVideoPath(this._videoUrl);
            return true;
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_ERROR, this._videoUrl);
            DeviceLog.exception("Error preparing video: " + this._videoUrl, e);
            return false;
        }
    }

    public void play() {
        DeviceLog.entered();
        setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mediaPlayer != null) {
                    VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.COMPLETED, VideoPlayerView.this._videoUrl);
                VideoPlayerView.this.stopVideoProgressTimer();
            }
        });
        start();
        stopVideoProgressTimer();
        startVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PLAY, this._videoUrl);
    }

    public void setInfoListenerEnabled(boolean z) {
        this._infoListenerEnabled = z;
        if (VERSION.SDK_INT <= 16) {
            return;
        }
        if (this._infoListenerEnabled) {
            setOnInfoListener(new OnInfoListener() {
                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, Integer.valueOf(i), Integer.valueOf(i2), VideoPlayerView.this._videoUrl);
                    return true;
                }
            });
        } else {
            setOnInfoListener(null);
        }
    }

    public void pause() {
        try {
            super.pause();
            stopVideoProgressTimer();
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE, this._videoUrl);
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE_ERROR, this._videoUrl);
            DeviceLog.exception("Error pausing video", e);
        }
    }

    public void seekTo(int i) {
        try {
            super.seekTo(i);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO, this._videoUrl);
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO_ERROR, this._videoUrl);
            DeviceLog.exception("Error seeking video", e);
        }
    }

    public void stop() {
        stopPlayback();
        stopVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.STOP, this._videoUrl);
    }

    public float getVolume() {
        return this._volume.floatValue();
    }

    public void setVolume(Float f) {
        try {
            this._mediaPlayer.setVolume(f.floatValue(), f.floatValue());
            this._volume = f;
        } catch (Exception e) {
            DeviceLog.exception("MediaPlayer generic error", e);
        }
    }

    public void setProgressEventInterval(int i) {
        this._progressEventInterval = i;
        if (this._videoTimer != null) {
            stopVideoProgressTimer();
            startVideoProgressTimer();
        }
    }

    public int getProgressEventInterval() {
        return this._progressEventInterval;
    }
}
