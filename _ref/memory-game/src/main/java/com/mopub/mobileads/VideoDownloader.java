package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

public class VideoDownloader {
    private static final int MAX_VIDEO_SIZE = 26214400;
    private static final Deque<WeakReference<VideoDownloaderTask>> sDownloaderTasks = new ArrayDeque();

    private VideoDownloader() {
    }

    public static void cache(@Nullable String str, @NonNull VideoDownloaderListener videoDownloaderListener) {
        Preconditions.checkNotNull(videoDownloaderListener);
        if (str == null) {
            MoPubLog.d("VideoDownloader attempted to cache video with null url.");
            videoDownloaderListener.onComplete(false);
            return;
        }
        try {
            AsyncTasks.safeExecuteOnExecutor(new VideoDownloaderTask(videoDownloaderListener), new String[]{str});
        } catch (Exception e) {
            videoDownloaderListener.onComplete(false);
        }
    }

    public static void cancelAllDownloaderTasks() {
        for (WeakReference cancelOneTask : sDownloaderTasks) {
            cancelOneTask(cancelOneTask);
        }
        sDownloaderTasks.clear();
    }

    public static void cancelLastDownloadTask() {
        if (!sDownloaderTasks.isEmpty()) {
            cancelOneTask((WeakReference) sDownloaderTasks.peekLast());
            sDownloaderTasks.removeLast();
        }
    }

    private static boolean cancelOneTask(@Nullable WeakReference<VideoDownloaderTask> weakReference) {
        if (weakReference == null) {
            return false;
        }
        VideoDownloaderTask videoDownloaderTask = (VideoDownloaderTask) weakReference.get();
        if (videoDownloaderTask == null) {
            return false;
        }
        return videoDownloaderTask.cancel(true);
    }

    @Deprecated
    @VisibleForTesting
    public static Deque<WeakReference<VideoDownloaderTask>> getDownloaderTasks() {
        return sDownloaderTasks;
    }

    @Deprecated
    @VisibleForTesting
    public static void clearDownloaderTasks() {
        sDownloaderTasks.clear();
    }
}
