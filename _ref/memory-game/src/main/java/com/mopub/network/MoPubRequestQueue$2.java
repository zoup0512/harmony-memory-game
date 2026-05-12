package com.mopub.network;

import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue.RequestFilter;

class MoPubRequestQueue$2 implements RequestFilter {
    final /* synthetic */ MoPubRequestQueue this$0;
    final /* synthetic */ Request val$request;

    MoPubRequestQueue$2(MoPubRequestQueue moPubRequestQueue, Request request) {
        this.this$0 = moPubRequestQueue;
        this.val$request = request;
    }

    public boolean apply(Request<?> request) {
        return this.val$request == request;
    }
}
