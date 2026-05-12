package com.mopub.nativeads;

import android.support.annotation.NonNull;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.VolleyError;

class MoPubNative$2 implements Listener {
    final /* synthetic */ MoPubNative this$0;

    MoPubNative$2(MoPubNative moPubNative) {
        this.this$0 = moPubNative;
    }

    public void onSuccess(@NonNull AdResponse adResponse) {
        MoPubNative.access$000(this.this$0, adResponse);
    }

    public void onErrorResponse(@NonNull VolleyError volleyError) {
        this.this$0.onAdError(volleyError);
    }
}
