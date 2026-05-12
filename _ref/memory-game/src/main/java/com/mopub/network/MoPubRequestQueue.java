package com.mopub.network;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.volley.Cache;
import com.mopub.volley.Network;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.RequestQueue.RequestFilter;
import com.mopub.volley.ResponseDelivery;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MoPubRequestQueue extends RequestQueue {
    private static final int CAPACITY = 10;
    @NonNull
    private final Map<Request<?>, DelayedRequestHelper> mDelayedRequests = new HashMap(10);

    MoPubRequestQueue(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        super(cache, network, i, responseDelivery);
    }

    MoPubRequestQueue(Cache cache, Network network, int i) {
        super(cache, network, i);
    }

    MoPubRequestQueue(Cache cache, Network network) {
        super(cache, network);
    }

    public void addDelayedRequest(@NonNull Request<?> request, int i) {
        Preconditions.checkNotNull(request);
        addDelayedRequest((Request) request, new DelayedRequestHelper(this, request, i));
    }

    @VisibleForTesting
    void addDelayedRequest(@NonNull Request<?> request, @NonNull DelayedRequestHelper delayedRequestHelper) {
        Preconditions.checkNotNull(delayedRequestHelper);
        if (this.mDelayedRequests.containsKey(request)) {
            cancel(request);
        }
        delayedRequestHelper.start();
        this.mDelayedRequests.put(request, delayedRequestHelper);
    }

    public void cancelAll(@NonNull RequestFilter requestFilter) {
        Preconditions.checkNotNull(requestFilter);
        super.cancelAll(requestFilter);
        Iterator it = this.mDelayedRequests.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (requestFilter.apply((Request) entry.getKey())) {
                ((Request) entry.getKey()).cancel();
                ((DelayedRequestHelper) entry.getValue()).cancel();
                it.remove();
            }
        }
    }

    public void cancelAll(@NonNull Object obj) {
        Preconditions.checkNotNull(obj);
        super.cancelAll(obj);
        cancelAll(new 1(this, obj));
    }

    public void cancel(@NonNull Request<?> request) {
        Preconditions.checkNotNull(request);
        cancelAll(new 2(this, request));
    }

    @NonNull
    @Deprecated
    @VisibleForTesting
    Map<Request<?>, DelayedRequestHelper> getDelayedRequests() {
        return this.mDelayedRequests;
    }
}
