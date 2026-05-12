package com.mopub.mobileads;

class MoPubRewardedVideoManager$13 implements Runnable {
    final /* synthetic */ String val$serverCompletionUrl;

    MoPubRewardedVideoManager$13(String str) {
        this.val$serverCompletionUrl = str;
    }

    public void run() {
        RewardedVideoCompletionRequestHandler.makeRewardedVideoCompletionRequest(MoPubRewardedVideoManager.access$1100(MoPubRewardedVideoManager.access$200()), this.val$serverCompletionUrl, MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getCustomerId());
    }
}
