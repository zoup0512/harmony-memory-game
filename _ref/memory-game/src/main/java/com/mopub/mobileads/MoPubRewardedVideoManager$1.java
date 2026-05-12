package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class MoPubRewardedVideoManager$1 implements Runnable {
    final /* synthetic */ MoPubRewardedVideoManager this$0;
    final /* synthetic */ CustomEventRewardedVideo val$customEvent;

    MoPubRewardedVideoManager$1(MoPubRewardedVideoManager moPubRewardedVideoManager, CustomEventRewardedVideo customEventRewardedVideo) {
        this.this$0 = moPubRewardedVideoManager;
        this.val$customEvent = customEventRewardedVideo;
    }

    public void run() {
        MoPubLog.d("Custom Event failed to load rewarded video in a timely fashion.");
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(this.val$customEvent.getClass(), this.val$customEvent.getAdNetworkId(), MoPubErrorCode.NETWORK_TIMEOUT);
        this.val$customEvent.onInvalidate();
    }
}
