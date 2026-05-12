package com.cmcm.adsdk.config;

import com.cmcm.adsdk.config.RequestTask.ResultListener;
import com.cmcm.utils.g;

public class RequestAction implements ResultListener {
    private static final String TAG = "RequestAction";
    private RequestListener mListener;

    public interface RequestListener {
        void onFailed(String str);

        void onSuccess(String str);
    }

    public void requestConfig(String baseUrl, String params, RequestListener listener) {
        if (listener != null) {
            this.mListener = listener;
            new RequestTask(baseUrl, params, this).execute(new Void[0]);
        }
    }

    public void destory() {
        if (this.mListener != null) {
            this.mListener = null;
        }
    }

    public void result(byte[] result) {
        if (this.mListener == null) {
            return;
        }
        if (result != null) {
            this.mListener.onSuccess(new String(result));
            g.b(TAG, "onSuccess...");
            return;
        }
        this.mListener.onFailed(null);
        g.b(TAG, "failed...");
    }
}
