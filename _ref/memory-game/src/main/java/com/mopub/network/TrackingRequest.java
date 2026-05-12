package com.mopub.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastErrorCode;
import com.mopub.mobileads.VastMacroHelper;
import com.mopub.mobileads.VastTracker;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackingRequest extends Request<Void> {
    @Nullable
    private final Listener mListener;

    public interface Listener extends ErrorListener {
        void onResponse(@NonNull String str);
    }

    private TrackingRequest(@NonNull String str, @Nullable Listener listener) {
        super(0, str, listener);
        this.mListener = listener;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(2500, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    protected Response<Void> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.statusCode != 200) {
            return Response.error(new MoPubNetworkError("Failed to log tracking request. Response code: " + networkResponse.statusCode + " for url: " + getUrl(), Reason.TRACKING_FAILURE));
        }
        return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    public void deliverResponse(Void voidR) {
        if (this.mListener != null) {
            this.mListener.onResponse(getUrl());
        }
    }

    public static void makeVastTrackingHttpRequest(@NonNull List<VastTracker> list, @Nullable VastErrorCode vastErrorCode, @Nullable Integer num, @Nullable String str, @Nullable Context context) {
        Preconditions.checkNotNull(list);
        List arrayList = new ArrayList(list.size());
        for (VastTracker vastTracker : list) {
            if (vastTracker != null && (!vastTracker.isTracked() || vastTracker.isRepeatable())) {
                arrayList.add(vastTracker.getTrackingUrl());
                vastTracker.setTracked();
            }
        }
        makeTrackingHttpRequest(new VastMacroHelper(arrayList).withErrorCode(vastErrorCode).withContentPlayHead(num).withAssetUri(str).getUris(), context);
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> iterable, @Nullable Context context, @Nullable final Listener listener, Name name) {
        if (iterable != null && context != null) {
            RequestQueue requestQueue = Networking.getRequestQueue(context);
            for (final String str : iterable) {
                if (!TextUtils.isEmpty(str)) {
                    requestQueue.add(new TrackingRequest(str, new Listener() {
                        public void onResponse(@NonNull String str) {
                            MoPubLog.d("Successfully hit tracking endpoint: " + str);
                            if (listener != null) {
                                listener.onResponse(str);
                            }
                        }

                        public void onErrorResponse(VolleyError volleyError) {
                            MoPubLog.d("Failed to hit tracking endpoint: " + str);
                            if (listener != null) {
                                listener.onErrorResponse(volleyError);
                            }
                        }
                    }));
                }
            }
        }
    }

    public static void makeTrackingHttpRequest(@Nullable String str, @Nullable Context context) {
        makeTrackingHttpRequest(str, context, null, null);
    }

    public static void makeTrackingHttpRequest(@Nullable String str, @Nullable Context context, @Nullable Listener listener) {
        makeTrackingHttpRequest(str, context, listener, null);
    }

    public static void makeTrackingHttpRequest(@Nullable String str, @Nullable Context context, Name name) {
        makeTrackingHttpRequest(str, context, null, name);
    }

    public static void makeTrackingHttpRequest(@Nullable String str, @Nullable Context context, @Nullable Listener listener, Name name) {
        if (str != null) {
            makeTrackingHttpRequest(Arrays.asList(new String[]{str}), context, listener, name);
        }
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> iterable, @Nullable Context context) {
        makeTrackingHttpRequest((Iterable) iterable, context, null, null);
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> iterable, @Nullable Context context, Name name) {
        makeTrackingHttpRequest((Iterable) iterable, context, null, name);
    }
}
