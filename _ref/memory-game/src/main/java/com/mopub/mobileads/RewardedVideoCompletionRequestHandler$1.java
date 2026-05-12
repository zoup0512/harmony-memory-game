package com.mopub.mobileads;

class RewardedVideoCompletionRequestHandler$1 implements Runnable {
    final /* synthetic */ RewardedVideoCompletionRequestHandler this$0;

    RewardedVideoCompletionRequestHandler$1(RewardedVideoCompletionRequestHandler rewardedVideoCompletionRequestHandler) {
        this.this$0 = rewardedVideoCompletionRequestHandler;
    }

    public void run() {
        this.this$0.makeRewardedVideoCompletionRequest();
    }
}
