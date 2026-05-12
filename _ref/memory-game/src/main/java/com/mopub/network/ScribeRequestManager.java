package com.mopub.network;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;

public class ScribeRequestManager extends RequestManager<ScribeRequest$ScribeRequestFactory> implements ScribeRequest$Listener {
    public ScribeRequestManager(Looper looper) {
        super(looper);
    }

    @NonNull
    Request<?> createRequest() {
        return ((ScribeRequest$ScribeRequestFactory) this.mRequestFactory).createRequest(this);
    }

    public void onResponse() {
        MoPubLog.d("Successfully scribed events");
        this.mHandler.post(new Runnable() {
            public void run() {
                ScribeRequestManager.this.clearRequest();
            }
        });
    }

    public void onErrorResponse(final VolleyError volleyError) {
        this.mHandler.post(new Runnable() {
            public void run() {
                try {
                    ScribeRequestManager.this.mBackoffPolicy.backoff(volleyError);
                    ScribeRequestManager.this.makeRequestInternal();
                } catch (VolleyError e) {
                    MoPubLog.d("Failed to Scribe events: " + volleyError);
                    ScribeRequestManager.this.clearRequest();
                }
            }
        });
    }
}
