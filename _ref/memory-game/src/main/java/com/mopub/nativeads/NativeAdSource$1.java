package com.mopub.nativeads;

class NativeAdSource$1 implements Runnable {
    final /* synthetic */ NativeAdSource this$0;

    NativeAdSource$1(NativeAdSource nativeAdSource) {
        this.this$0 = nativeAdSource;
    }

    public void run() {
        this.this$0.mRetryInFlight = false;
        this.this$0.replenishCache();
    }
}
