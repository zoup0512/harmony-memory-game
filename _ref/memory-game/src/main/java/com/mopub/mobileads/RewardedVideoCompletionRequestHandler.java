package com.mopub.mobileads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RewardedVideoCompletionRequest.RewardedVideoCompletionRequestListener;
import com.mopub.network.Networking;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.VolleyError;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

public class RewardedVideoCompletionRequestHandler implements RewardedVideoCompletionRequestListener {
    private static final String API_VERSION_KEY = "&v=";
    private static final String CUSTOMER_ID_KEY = "&customer_id=";
    static final int MAX_RETRIES = 17;
    static final int REQUEST_TIMEOUT_DELAY = 1000;
    static final int[] RETRY_TIMES = new int[]{5000, 10000, 20000, 40000, 60000};
    private static final String SDK_VERSION_KEY = "&nv=";
    @NonNull
    private final Handler mHandler;
    @NonNull
    private final RequestQueue mRequestQueue;
    private int mRetryCount;
    private volatile boolean mShouldStop;
    @NonNull
    private final String mUrl;

    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String str, @Nullable String str2) {
        this(context, str, str2, new Handler());
    }

    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String str, @Nullable String str2, @NonNull Handler handler) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        this.mUrl = appendParameters(str, str2);
        this.mRetryCount = 0;
        this.mHandler = handler;
        this.mRequestQueue = Networking.getRequestQueue(context);
    }

    void makeRewardedVideoCompletionRequest() {
        if (this.mShouldStop) {
            this.mRequestQueue.cancelAll(this.mUrl);
            return;
        }
        Request rewardedVideoCompletionRequest = new RewardedVideoCompletionRequest(this.mUrl, new DefaultRetryPolicy(getTimeout(this.mRetryCount) - 1000, 0, 0.0f), this);
        rewardedVideoCompletionRequest.setTag(this.mUrl);
        this.mRequestQueue.add(rewardedVideoCompletionRequest);
        if (this.mRetryCount >= 17) {
            MoPubLog.d("Exceeded number of retries for rewarded video completion request.");
            return;
        }
        this.mHandler.postDelayed(new 1(this), (long) getTimeout(this.mRetryCount));
        this.mRetryCount++;
    }

    public void onResponse(Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 500 || num.intValue() >= SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT) {
            this.mShouldStop = true;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null && volleyError.networkResponse != null) {
            if (volleyError.networkResponse.statusCode < 500 || volleyError.networkResponse.statusCode >= SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT) {
                this.mShouldStop = true;
            }
        }
    }

    public static void makeRewardedVideoCompletionRequest(@Nullable Context context, @Nullable String str, @Nullable String str2) {
        if (!TextUtils.isEmpty(str) && context != null) {
            new RewardedVideoCompletionRequestHandler(context, str, str2).makeRewardedVideoCompletionRequest();
        }
    }

    static int getTimeout(int i) {
        if (i < 0 || i >= RETRY_TIMES.length) {
            return RETRY_TIMES[RETRY_TIMES.length - 1];
        }
        return RETRY_TIMES[i];
    }

    private static String appendParameters(@NonNull String str, @Nullable String str2) {
        String str3;
        Preconditions.checkNotNull(str);
        StringBuilder append = new StringBuilder().append(str).append(CUSTOMER_ID_KEY);
        if (str2 == null) {
            str3 = "";
        } else {
            str3 = Uri.encode(str2);
        }
        return append.append(str3).append(SDK_VERSION_KEY).append(Uri.encode(MoPub.SDK_VERSION)).append(API_VERSION_KEY).append(1).toString();
    }

    @Deprecated
    @VisibleForTesting
    boolean getShouldStop() {
        return this.mShouldStop;
    }

    @Deprecated
    @VisibleForTesting
    int getRetryCount() {
        return this.mRetryCount;
    }

    @Deprecated
    @VisibleForTesting
    void setRetryCount(int i) {
        this.mRetryCount = i;
    }
}
