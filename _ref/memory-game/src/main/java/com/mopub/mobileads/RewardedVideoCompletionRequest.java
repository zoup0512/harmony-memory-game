package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.RetryPolicy;
import com.mopub.volley.toolbox.HttpHeaderParser;

public class RewardedVideoCompletionRequest extends Request<Integer> {
    @NonNull
    final RewardedVideoCompletionRequestListener mListener;

    public interface RewardedVideoCompletionRequestListener extends ErrorListener {
        void onResponse(Integer num);
    }

    public RewardedVideoCompletionRequest(@NonNull String str, @NonNull RetryPolicy retryPolicy, @NonNull RewardedVideoCompletionRequestListener rewardedVideoCompletionRequestListener) {
        super(0, str, rewardedVideoCompletionRequestListener);
        setShouldCache(false);
        setRetryPolicy(retryPolicy);
        this.mListener = rewardedVideoCompletionRequestListener;
    }

    protected Response<Integer> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(Integer.valueOf(networkResponse.statusCode), HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    protected void deliverResponse(Integer num) {
        this.mListener.onResponse(num);
    }
}
