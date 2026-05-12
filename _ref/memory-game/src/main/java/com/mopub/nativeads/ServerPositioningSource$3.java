package com.mopub.nativeads;

import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.VolleyError;

class ServerPositioningSource$3 implements ErrorListener {
    final /* synthetic */ ServerPositioningSource this$0;

    ServerPositioningSource$3(ServerPositioningSource serverPositioningSource) {
        this.this$0 = serverPositioningSource;
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (!(volleyError instanceof MoPubNetworkError) || ((MoPubNetworkError) volleyError).getReason().equals(Reason.WARMING_UP)) {
            MoPubLog.e("Failed to load positioning data", volleyError);
            if (volleyError.networkResponse == null && !DeviceUtils.isNetworkAvailable(ServerPositioningSource.access$200(this.this$0))) {
                MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
            }
        }
        ServerPositioningSource.access$300(this.this$0);
    }
}
