package com.mopub.mobileads;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Streams;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;

public class VastVideoView extends VideoView {
    private static final int MAX_VIDEO_RETRIES = 1;
    private static final int VIDEO_VIEW_FILE_PERMISSION_ERROR = Integer.MIN_VALUE;
    @Nullable
    private VastVideoBlurLastVideoFrameTask mBlurLastVideoFrameTask;
    @Nullable
    private MediaMetadataRetriever mMediaMetadataRetriever = createMediaMetadataRetriever();
    private int mVideoRetries;

    public VastVideoView(@NonNull Context context) {
        super(context);
        Preconditions.checkNotNull(context, "context cannot be null");
    }

    public void prepareBlurredLastVideoFrame(@NonNull ImageView imageView, @NonNull String str) {
        if (this.mMediaMetadataRetriever != null) {
            this.mBlurLastVideoFrameTask = new VastVideoBlurLastVideoFrameTask(this.mMediaMetadataRetriever, imageView, getDuration());
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mBlurLastVideoFrameTask, new String[]{str});
            } catch (Throwable e) {
                MoPubLog.d("Failed to blur last video frame", e);
            }
        }
    }

    public void onDestroy() {
        if (this.mBlurLastVideoFrameTask != null && this.mBlurLastVideoFrameTask.getStatus() != Status.FINISHED) {
            this.mBlurLastVideoFrameTask.cancel(true);
        }
    }

    boolean retryMediaPlayer(MediaPlayer mediaPlayer, int i, int i2, @NonNull String str) {
        Closeable closeable;
        Throwable th;
        if (VERSION.SDK_INT >= 16 || i != 1 || i2 != Integer.MIN_VALUE || this.mVideoRetries >= 1) {
            return false;
        }
        Closeable closeable2 = null;
        try {
            mediaPlayer.reset();
            Closeable fileInputStream = new FileInputStream(new File(str));
            try {
                mediaPlayer.setDataSource(fileInputStream.getFD());
                mediaPlayer.prepareAsync();
                start();
                Streams.closeStream(fileInputStream);
                this.mVideoRetries++;
                return true;
            } catch (Exception e) {
                closeable = fileInputStream;
                Streams.closeStream(closeable);
                this.mVideoRetries++;
                return false;
            } catch (Throwable th2) {
                th = th2;
                closeable2 = fileInputStream;
                Streams.closeStream(closeable2);
                this.mVideoRetries++;
                throw th;
            }
        } catch (Exception e2) {
            closeable = null;
            Streams.closeStream(closeable);
            this.mVideoRetries++;
            return false;
        } catch (Throwable th3) {
            th = th3;
            Streams.closeStream(closeable2);
            this.mVideoRetries++;
            throw th;
        }
    }

    public void onResume() {
        this.mVideoRetries = 0;
    }

    @Nullable
    @VisibleForTesting
    MediaMetadataRetriever createMediaMetadataRetriever() {
        if (VERSION.SDK_INT >= 10) {
            return new MediaMetadataRetriever();
        }
        return null;
    }

    @Deprecated
    @VisibleForTesting
    void setMediaMetadataRetriever(@NonNull MediaMetadataRetriever mediaMetadataRetriever) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    VastVideoBlurLastVideoFrameTask getBlurLastVideoFrameTask() {
        return this.mBlurLastVideoFrameTask;
    }

    @Deprecated
    @VisibleForTesting
    void setBlurLastVideoFrameTask(@NonNull VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask) {
        this.mBlurLastVideoFrameTask = vastVideoBlurLastVideoFrameTask;
    }

    @Deprecated
    @VisibleForTesting
    int getVideoRetries() {
        return this.mVideoRetries;
    }
}
