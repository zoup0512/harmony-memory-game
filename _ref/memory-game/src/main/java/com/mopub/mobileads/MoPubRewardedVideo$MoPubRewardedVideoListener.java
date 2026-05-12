package com.mopub.mobileads;

import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;

class MoPubRewardedVideo$MoPubRewardedVideoListener implements CustomEventInterstitialListener, RewardedVastVideoInterstitial$CustomEventRewardedVideoInterstitialListener {
    final /* synthetic */ MoPubRewardedVideo this$0;

    private MoPubRewardedVideo$MoPubRewardedVideoListener(MoPubRewardedVideo moPubRewardedVideo) {
        this.this$0 = moPubRewardedVideo;
    }

    public void onInterstitialLoaded() {
        MoPubRewardedVideo.access$102(this.this$0, true);
        MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(MoPubRewardedVideo.class, "mopub_rewarded_video_id");
    }

    public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
        switch (MoPubRewardedVideo$1.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[moPubErrorCode.ordinal()]) {
            case 1:
                MoPubRewardedVideoManager.onRewardedVideoPlaybackError(MoPubRewardedVideo.class, "mopub_rewarded_video_id", moPubErrorCode);
                return;
            default:
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(MoPubRewardedVideo.class, "mopub_rewarded_video_id", moPubErrorCode);
                return;
        }
    }

    public void onInterstitialShown() {
        MoPubRewardedVideoManager.onRewardedVideoStarted(MoPubRewardedVideo.class, "mopub_rewarded_video_id");
    }

    public void onInterstitialClicked() {
        MoPubRewardedVideoManager.onRewardedVideoClicked(MoPubRewardedVideo.class, "mopub_rewarded_video_id");
    }

    public void onInterstitialFinished() {
    }

    public void onLeaveApplication() {
    }

    public void onInterstitialDismissed() {
        MoPubRewardedVideoManager.onRewardedVideoClosed(MoPubRewardedVideo.class, "mopub_rewarded_video_id");
    }

    public void onVideoComplete() {
        if (MoPubRewardedVideo.access$200(this.this$0) == null) {
            MoPubLog.d("No rewarded video was loaded, so no reward is possible");
        } else {
            MoPubRewardedVideoManager.onRewardedVideoCompleted(MoPubRewardedVideo.class, "mopub_rewarded_video_id", MoPubReward.success(MoPubRewardedVideo.access$200(this.this$0), MoPubRewardedVideo.access$300(this.this$0)));
        }
    }
}
