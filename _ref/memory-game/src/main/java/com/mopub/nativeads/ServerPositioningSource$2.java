package com.mopub.nativeads;

import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.volley.Response.Listener;

class ServerPositioningSource$2 implements Listener<MoPubClientPositioning> {
    final /* synthetic */ ServerPositioningSource this$0;

    ServerPositioningSource$2(ServerPositioningSource serverPositioningSource) {
        this.this$0 = serverPositioningSource;
    }

    public void onResponse(MoPubClientPositioning moPubClientPositioning) {
        ServerPositioningSource.access$100(this.this$0, moPubClientPositioning);
    }
}
