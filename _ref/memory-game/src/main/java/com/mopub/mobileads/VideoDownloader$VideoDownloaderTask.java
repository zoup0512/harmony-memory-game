package com.mopub.mobileads;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.mopub.common.CacheService;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Streams;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

@VisibleForTesting
class VideoDownloader$VideoDownloaderTask extends AsyncTask<String, Void, Boolean> {
    @NonNull
    private final VideoDownloader$VideoDownloaderListener mListener;
    @NonNull
    private final WeakReference<VideoDownloader$VideoDownloaderTask> mWeakSelf = new WeakReference(this);

    @VisibleForTesting
    VideoDownloader$VideoDownloaderTask(@NonNull VideoDownloader$VideoDownloaderListener videoDownloader$VideoDownloaderListener) {
        this.mListener = videoDownloader$VideoDownloaderListener;
        VideoDownloader.access$000().add(this.mWeakSelf);
    }

    protected Boolean doInBackground(String... strArr) {
        Boolean valueOf;
        Throwable e;
        HttpURLConnection httpURLConnection = null;
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            MoPubLog.d("VideoDownloader task tried to execute null or empty url.");
            return Boolean.valueOf(false);
        }
        String str = strArr[0];
        HttpURLConnection httpUrlConnection;
        Closeable bufferedInputStream;
        try {
            httpUrlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(str);
            try {
                bufferedInputStream = new BufferedInputStream(httpUrlConnection.getInputStream());
                try {
                    int responseCode = httpUrlConnection.getResponseCode();
                    if (responseCode < 200 || responseCode >= 300) {
                        MoPubLog.d("VideoDownloader encountered unexpected statusCode: " + responseCode);
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(bufferedInputStream);
                        if (httpUrlConnection == null) {
                            return valueOf;
                        }
                        httpUrlConnection.disconnect();
                        return valueOf;
                    }
                    if (httpUrlConnection.getContentLength() > 26214400) {
                        MoPubLog.d(String.format("VideoDownloader encountered video larger than disk cap. (%d bytes / %d maximum).", new Object[]{Integer.valueOf(httpUrlConnection.getContentLength()), Integer.valueOf(26214400)}));
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(bufferedInputStream);
                        if (httpUrlConnection == null) {
                            return valueOf;
                        }
                        httpUrlConnection.disconnect();
                        return valueOf;
                    }
                    valueOf = Boolean.valueOf(CacheService.putToDiskCache(str, bufferedInputStream));
                    Streams.closeStream(bufferedInputStream);
                    if (httpUrlConnection == null) {
                        return valueOf;
                    }
                    httpUrlConnection.disconnect();
                    return valueOf;
                } catch (Exception e2) {
                    e = e2;
                    httpURLConnection = httpUrlConnection;
                    try {
                        MoPubLog.d("VideoDownloader task threw an internal exception.", e);
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(bufferedInputStream);
                        if (httpURLConnection != null) {
                            return valueOf;
                        }
                        httpURLConnection.disconnect();
                        return valueOf;
                    } catch (Throwable th) {
                        e = th;
                        httpUrlConnection = httpURLConnection;
                        Streams.closeStream(bufferedInputStream);
                        if (httpUrlConnection != null) {
                            httpUrlConnection.disconnect();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    Streams.closeStream(bufferedInputStream);
                    if (httpUrlConnection != null) {
                        httpUrlConnection.disconnect();
                    }
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedInputStream = null;
                httpURLConnection = httpUrlConnection;
                MoPubLog.d("VideoDownloader task threw an internal exception.", e);
                valueOf = Boolean.valueOf(false);
                Streams.closeStream(bufferedInputStream);
                if (httpURLConnection != null) {
                    return valueOf;
                }
                httpURLConnection.disconnect();
                return valueOf;
            } catch (Throwable th3) {
                e = th3;
                bufferedInputStream = null;
                Streams.closeStream(bufferedInputStream);
                if (httpUrlConnection != null) {
                    httpUrlConnection.disconnect();
                }
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedInputStream = null;
            MoPubLog.d("VideoDownloader task threw an internal exception.", e);
            valueOf = Boolean.valueOf(false);
            Streams.closeStream(bufferedInputStream);
            if (httpURLConnection != null) {
                return valueOf;
            }
            httpURLConnection.disconnect();
            return valueOf;
        } catch (Throwable th4) {
            e = th4;
            bufferedInputStream = null;
            httpUrlConnection = null;
            Streams.closeStream(bufferedInputStream);
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
            throw e;
        }
    }

    protected void onPostExecute(Boolean bool) {
        if (isCancelled()) {
            onCancelled();
            return;
        }
        VideoDownloader.access$000().remove(this.mWeakSelf);
        if (bool == null) {
            this.mListener.onComplete(false);
        } else {
            this.mListener.onComplete(bool.booleanValue());
        }
    }

    protected void onCancelled() {
        MoPubLog.d("VideoDownloader task was cancelled.");
        VideoDownloader.access$000().remove(this.mWeakSelf);
        this.mListener.onComplete(false);
    }
}
