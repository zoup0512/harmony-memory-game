package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class CustomEventInterstitialAdapter$1 implements Runnable {
    final /* synthetic */ CustomEventInterstitialAdapter this$0;

    CustomEventInterstitialAdapter$1(CustomEventInterstitialAdapter customEventInterstitialAdapter) {
        this.this$0 = customEventInterstitialAdapter;
    }

    public void run() {
        MoPubLog.d("Third-party network timed out.");
        this.this$0.onInterstitialFailed(MoPubErrorCode.NETWORK_TIMEOUT);
        this.this$0.invalidate();
    }
}
