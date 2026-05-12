package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzin
public class zzjj extends zza {
    private zzjk zzchn;
    private zzjh zzchu;
    private zzji zzchv;

    public zzjj(zzji com_google_android_gms_internal_zzji) {
        this.zzchv = com_google_android_gms_internal_zzji;
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, RewardItemParcel rewardItemParcel) {
        if (this.zzchv != null) {
            this.zzchv.zzc(rewardItemParcel);
        }
    }

    public void zza(zzjh com_google_android_gms_internal_zzjh) {
        this.zzchu = com_google_android_gms_internal_zzjh;
    }

    public void zza(zzjk com_google_android_gms_internal_zzjk) {
        this.zzchn = com_google_android_gms_internal_zzjk;
    }

    public void zzb(zzd com_google_android_gms_dynamic_zzd, int i) {
        if (this.zzchu != null) {
            this.zzchu.zzaw(i);
        }
    }

    public void zzc(zzd com_google_android_gms_dynamic_zzd, int i) {
        if (this.zzchn != null) {
            this.zzchn.zza(zze.zzad(com_google_android_gms_dynamic_zzd).getClass().getName(), i);
        }
    }

    public void zzp(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchu != null) {
            this.zzchu.zzrs();
        }
    }

    public void zzq(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchn != null) {
            this.zzchn.zzcg(zze.zzad(com_google_android_gms_dynamic_zzd).getClass().getName());
        }
    }

    public void zzr(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchv != null) {
            this.zzchv.onRewardedVideoAdOpened();
        }
    }

    public void zzs(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchv != null) {
            this.zzchv.onRewardedVideoStarted();
        }
    }

    public void zzt(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchv != null) {
            this.zzchv.onRewardedVideoAdClosed();
        }
    }

    public void zzu(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchv != null) {
            this.zzchv.zzrr();
        }
    }

    public void zzv(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzchv != null) {
            this.zzchv.onRewardedVideoAdLeftApplication();
        }
    }
}
