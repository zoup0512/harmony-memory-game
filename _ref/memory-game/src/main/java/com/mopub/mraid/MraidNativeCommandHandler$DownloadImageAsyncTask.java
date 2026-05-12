package com.mopub.mraid;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.ResponseHeader;
import com.mopub.common.util.Streams;
import com.mopub.mraid.MraidNativeCommandHandler.MoPubMediaScannerConnectionClient;
import com.yalantis.ucrop.util.FileUtils;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
import java.util.Map;

@VisibleForTesting
class MraidNativeCommandHandler$DownloadImageAsyncTask extends AsyncTask<String, Void, Boolean> {
    private final Context mContext;
    private final DownloadImageAsyncTaskListener mListener;

    public MraidNativeCommandHandler$DownloadImageAsyncTask(@NonNull Context context, @NonNull DownloadImageAsyncTaskListener downloadImageAsyncTaskListener) {
        this.mContext = context.getApplicationContext();
        this.mListener = downloadImageAsyncTaskListener;
    }

    protected Boolean doInBackground(@NonNull String[] strArr) {
        Throwable th;
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            return Boolean.valueOf(false);
        }
        File pictureStoragePath = getPictureStoragePath();
        pictureStoragePath.mkdirs();
        String str = strArr[0];
        URI create = URI.create(str);
        Closeable bufferedInputStream;
        Closeable fileOutputStream;
        Boolean valueOf;
        try {
            File file;
            HttpURLConnection httpUrlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(str);
            bufferedInputStream = new BufferedInputStream(httpUrlConnection.getInputStream());
            try {
                Object headerField = httpUrlConnection.getHeaderField(ResponseHeader.LOCATION.getKey());
                if (!TextUtils.isEmpty(headerField)) {
                    create = URI.create(headerField);
                }
                file = new File(pictureStoragePath, getFileNameForUriAndHeaders(create, httpUrlConnection.getHeaderFields()));
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e) {
                fileOutputStream = null;
                try {
                    valueOf = Boolean.valueOf(false);
                    Streams.closeStream(bufferedInputStream);
                    Streams.closeStream(fileOutputStream);
                    return valueOf;
                } catch (Throwable th2) {
                    th = th2;
                    Streams.closeStream(bufferedInputStream);
                    Streams.closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream = null;
                th = th4;
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(fileOutputStream);
                throw th;
            }
            try {
                Streams.copyContent(bufferedInputStream, fileOutputStream);
                loadPictureIntoGalleryApp(file.toString());
                valueOf = Boolean.valueOf(true);
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(fileOutputStream);
                return valueOf;
            } catch (Exception e2) {
                valueOf = Boolean.valueOf(false);
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(fileOutputStream);
                return valueOf;
            }
        } catch (Exception e3) {
            fileOutputStream = null;
            bufferedInputStream = null;
            valueOf = Boolean.valueOf(false);
            Streams.closeStream(bufferedInputStream);
            Streams.closeStream(fileOutputStream);
            return valueOf;
        } catch (Throwable th32) {
            bufferedInputStream = null;
            th = th32;
            fileOutputStream = null;
            Streams.closeStream(bufferedInputStream);
            Streams.closeStream(fileOutputStream);
            throw th;
        }
    }

    protected void onPostExecute(Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            this.mListener.onFailure();
        } else {
            this.mListener.onSuccess();
        }
    }

    @Nullable
    private String getFileNameForUriAndHeaders(@NonNull URI uri, @Nullable Map<String, List<String>> map) {
        Preconditions.checkNotNull(uri);
        String path = uri.getPath();
        if (path == null || map == null) {
            return null;
        }
        String name = new File(path).getName();
        List list = (List) map.get("Content-Type");
        if (list == null || list.isEmpty() || list.get(0) == null) {
            return name;
        }
        for (String str : ((String) list.get(0)).split(";")) {
            if (str.contains("image/")) {
                path = FileUtils.HIDDEN_PREFIX + str.split("/")[1];
                if (!name.endsWith(path)) {
                    return name + path;
                }
                return name;
            }
        }
        return name;
    }

    private File getPictureStoragePath() {
        return new File(Environment.getExternalStorageDirectory(), "Pictures");
    }

    private void loadPictureIntoGalleryApp(String str) {
        Object moPubMediaScannerConnectionClient = new MoPubMediaScannerConnectionClient(str, null, null);
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.mContext, moPubMediaScannerConnectionClient);
        MoPubMediaScannerConnectionClient.access$100(moPubMediaScannerConnectionClient, mediaScannerConnection);
        mediaScannerConnection.connect();
    }

    @Deprecated
    @VisibleForTesting
    DownloadImageAsyncTaskListener getListener() {
        return this.mListener;
    }
}
