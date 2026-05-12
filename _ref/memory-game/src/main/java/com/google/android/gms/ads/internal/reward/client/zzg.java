package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.internal.reward.client.zzd.zza;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzin;

@zzin
public class zzg extends zza {
    private final RewardedVideoAdListener zzfh;

    public zzg(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzfh = rewardedVideoAdListener;
    }

    public void onRewardedVideoAdClosed() {
        if (this.zzfh != null) {
            this.zzfh.onRewardedVideoAdClosed();
        }
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzfh != null) {
            this.zzfh.onRewardedVideoAdFailedToLoad(i);
        }
    }

    public void onRewardedVideoAdLeftApplication() {
        if (this.zzfh != null) {
            this.zzfh.onRewardedVideoAdLeftApplication();
        }
    }

    public void onRewardedVideoAdLoaded() {
        if (this.zzfh != null) {
            this.zzfh.onRewardedVideoAdLoaded();
        }
    }

    public void onRewardedVideoAdOpened() {
        if (this.zzfh != null) {
            this.zzfh.onRewardedVideoAdOpened();
        }
    }

    public void onRewardedVideoStarted() {
        if (this.zzfh != null) {
            this.zzfh.onRewardedVideoStarted();
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_reward_client_zza) {
        if (this.zzfh != null) {
            this.zzfh.onRewarded(new zze(com_google_android_gms_ads_internal_reward_client_zza));
        }
    }
}
