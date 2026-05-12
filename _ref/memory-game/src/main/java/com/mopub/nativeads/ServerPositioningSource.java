package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Constants;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.PositioningSource.PositioningListener;
import com.mopub.network.Networking;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.Response.Listener;

class ServerPositioningSource implements PositioningSource {
    private static final double DEFAULT_RETRY_TIME_MILLISECONDS = 1000.0d;
    private static final double EXPONENTIAL_BACKOFF_FACTOR = 2.0d;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    @NonNull
    private final Context mContext;
    private final ErrorListener mErrorListener;
    @Nullable
    private PositioningListener mListener;
    private int mMaximumRetryTimeMillis = MAXIMUM_RETRY_TIME_MILLISECONDS;
    private final Listener<MoPubClientPositioning> mPositioningListener;
    @Nullable
    private PositioningRequest mRequest;
    private int mRetryCount;
    @NonNull
    private final Handler mRetryHandler;
    @NonNull
    private final Runnable mRetryRunnable;
    @Nullable
    private String mRetryUrl;

    ServerPositioningSource(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
        this.mRetryHandler = new Handler();
        this.mRetryRunnable = new 1(this);
        this.mPositioningListener = new 2(this);
        this.mErrorListener = new 3(this);
    }

    public void loadPositions(@NonNull String str, @NonNull PositioningListener positioningListener) {
        if (this.mRequest != null) {
            this.mRequest.cancel();
            this.mRequest = null;
        }
        if (this.mRetryCount > 0) {
            this.mRetryHandler.removeCallbacks(this.mRetryRunnable);
            this.mRetryCount = 0;
        }
        this.mListener = positioningListener;
        this.mRetryUrl = new PositioningUrlGenerator(this.mContext).withAdUnitId(str).generateUrlString(Constants.HOST);
        requestPositioningInternal();
    }

    private void requestPositioningInternal() {
        MoPubLog.d("Loading positioning from: " + this.mRetryUrl);
        this.mRequest = new PositioningRequest(this.mRetryUrl, this.mPositioningListener, this.mErrorListener);
        Networking.getRequestQueue(this.mContext).add(this.mRequest);
    }

    private void handleSuccess(@NonNull MoPubClientPositioning moPubClientPositioning) {
        if (this.mListener != null) {
            this.mListener.onLoad(moPubClientPositioning);
        }
        this.mListener = null;
        this.mRetryCount = 0;
    }

    private void handleFailure() {
        int pow = (int) (Math.pow(EXPONENTIAL_BACKOFF_FACTOR, (double) (this.mRetryCount + 1)) * DEFAULT_RETRY_TIME_MILLISECONDS);
        if (pow >= this.mMaximumRetryTimeMillis) {
            MoPubLog.d("Error downloading positioning information");
            if (this.mListener != null) {
                this.mListener.onFailed();
            }
            this.mListener = null;
            return;
        }
        this.mRetryCount++;
        this.mRetryHandler.postDelayed(this.mRetryRunnable, (long) pow);
    }

    @Deprecated
    @VisibleForTesting
    void setMaximumRetryTimeMilliseconds(int i) {
        this.mMaximumRetryTimeMillis = i;
    }
}
