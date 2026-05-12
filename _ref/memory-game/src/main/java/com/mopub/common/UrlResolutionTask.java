package com.mopub.common;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.mopub.common.util.AsyncTasks;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@VisibleForTesting
public class UrlResolutionTask extends AsyncTask<String, Void, String> {
    private static final int REDIRECT_LIMIT = 10;
    @NonNull
    private final UrlResolutionListener mListener;

    interface UrlResolutionListener {
        void onFailure(@NonNull String str, @Nullable Throwable th);

        void onSuccess(@NonNull String str);
    }

    public static void getResolvedUrl(@NonNull String str, @NonNull UrlResolutionListener urlResolutionListener) {
        try {
            AsyncTasks.safeExecuteOnExecutor(new UrlResolutionTask(urlResolutionListener), str);
        } catch (Throwable e) {
            urlResolutionListener.onFailure("Failed to resolve url", e);
        }
    }

    UrlResolutionTask(@NonNull UrlResolutionListener urlResolutionListener) {
        this.mListener = urlResolutionListener;
    }

    @Nullable
    protected String doInBackground(@Nullable String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        try {
            int i = 0;
            String str = strArr[0];
            String str2 = null;
            while (str != null && i < 10) {
                if (!UrlAction.OPEN_IN_APP_BROWSER.shouldTryHandlingUrl(Uri.parse(str))) {
                    return str;
                }
                i++;
                str2 = str;
                str = getRedirectLocation(str);
            }
            return str2;
        } catch (IOException e) {
            return null;
        } catch (URISyntaxException e2) {
            return null;
        }
    }

    @Nullable
    private String getRedirectLocation(@NonNull String str) {
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setInstanceFollowRedirects(false);
                String resolveRedirectLocation = resolveRedirectLocation(str, httpURLConnection2);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return resolveRedirectLocation;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    @Nullable
    @VisibleForTesting
    static String resolveRedirectLocation(@NonNull String str, @NonNull HttpURLConnection httpURLConnection) {
        URI uri = new URI(str);
        int responseCode = httpURLConnection.getResponseCode();
        String headerField = httpURLConnection.getHeaderField("Location");
        String str2 = null;
        if (responseCode >= 300 && responseCode < Game1MemoryGridActivity.START_ANIMATION_DURATION) {
            try {
                str2 = uri.resolve(headerField).toString();
            } catch (IllegalArgumentException e) {
                throw new URISyntaxException(headerField, "Unable to parse invalid URL");
            }
        }
        return str2;
    }

    protected void onPostExecute(@Nullable String str) {
        super.onPostExecute(str);
        if (isCancelled() || str == null) {
            onCancelled();
        } else {
            this.mListener.onSuccess(str);
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        this.mListener.onFailure("Task for resolving url was cancelled", null);
    }
}
