package com.google.android.gms.ads.internal.reward.mediation.client;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzin;

@zzin
public class zzb implements MediationRewardedVideoAdListener {
    private final zza zzcic;

    public zzb(zza com_google_android_gms_ads_internal_reward_mediation_client_zza) {
        this.zzcic = com_google_android_gms_ads_internal_reward_mediation_client_zza;
    }

    public void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onAdClicked must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onAdClicked.");
        try {
            this.zzcic.zzu(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdClicked.", e);
        }
    }

    public void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onAdClosed must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onAdClosed.");
        try {
            this.zzcic.zzt(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdClosed.", e);
        }
    }

    public void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        zzab.zzhi("onAdFailedToLoad must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onAdFailedToLoad.");
        try {
            this.zzcic.zzc(zze.zzac(mediationRewardedVideoAdAdapter), i);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onAdLeftApplication must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onAdLeftApplication.");
        try {
            this.zzcic.zzv(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onAdLoaded must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onAdLoaded.");
        try {
            this.zzcic.zzq(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdLoaded.", e);
        }
    }

    public void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onAdOpened must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onAdOpened.");
        try {
            this.zzcic.zzr(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdOpened.", e);
        }
    }

    public void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        zzab.zzhi("onInitializationFailed must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onInitializationFailed.");
        try {
            this.zzcic.zzb(zze.zzac(mediationRewardedVideoAdAdapter), i);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onInitializationFailed.", e);
        }
    }

    public void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onInitializationSucceeded must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onInitializationSucceeded.");
        try {
            this.zzcic.zzp(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onInitializationSucceeded.", e);
        }
    }

    public void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem) {
        zzab.zzhi("onRewarded must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onRewarded.");
        if (rewardItem != null) {
            try {
                this.zzcic.zza(zze.zzac(mediationRewardedVideoAdAdapter), new RewardItemParcel(rewardItem));
                return;
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onRewarded.", e);
                return;
            }
        }
        this.zzcic.zza(zze.zzac(mediationRewardedVideoAdAdapter), new RewardItemParcel(mediationRewardedVideoAdAdapter.getClass().getName(), 1));
    }

    public void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzab.zzhi("onVideoStarted must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Adapter called onVideoStarted.");
        try {
            this.zzcic.zzs(zze.zzac(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onVideoStarted.", e);
        }
    }
}
