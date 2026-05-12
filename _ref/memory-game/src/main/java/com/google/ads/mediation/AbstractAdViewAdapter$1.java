package com.google.ads.mediation;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

class AbstractAdViewAdapter$1 implements RewardedVideoAdListener {
    final /* synthetic */ AbstractAdViewAdapter zzfi;

    AbstractAdViewAdapter$1(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzfi = abstractAdViewAdapter;
    }

    public void onRewarded(RewardItem rewardItem) {
        AbstractAdViewAdapter.zza(this.zzfi).onRewarded(this.zzfi, rewardItem);
    }

    public void onRewardedVideoAdClosed() {
        AbstractAdViewAdapter.zza(this.zzfi).onAdClosed(this.zzfi);
        AbstractAdViewAdapter.zza(this.zzfi, null);
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        AbstractAdViewAdapter.zza(this.zzfi).onAdFailedToLoad(this.zzfi, i);
    }

    public void onRewardedVideoAdLeftApplication() {
        AbstractAdViewAdapter.zza(this.zzfi).onAdLeftApplication(this.zzfi);
    }

    public void onRewardedVideoAdLoaded() {
        AbstractAdViewAdapter.zza(this.zzfi).onAdLoaded(this.zzfi);
    }

    public void onRewardedVideoAdOpened() {
        AbstractAdViewAdapter.zza(this.zzfi).onAdOpened(this.zzfi);
    }

    public void onRewardedVideoStarted() {
        AbstractAdViewAdapter.zza(this.zzfi).onVideoStarted(this.zzfi);
    }
}
