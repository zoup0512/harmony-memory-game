package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.dynamic.zze;

@zzin
public class zzje extends zza {
    private final Context mContext;
    private final Object zzail = new Object();
    private final VersionInfoParcel zzalo;
    private final zzjf zzchf;

    public zzje(Context context, zzd com_google_android_gms_ads_internal_zzd, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzalo = versionInfoParcel;
        this.zzchf = new zzjf(context, com_google_android_gms_ads_internal_zzd, AdSizeParcel.zzii(), com_google_android_gms_internal_zzgj, versionInfoParcel);
    }

    public void destroy() {
        zzh(null);
    }

    public boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.zzail) {
            isLoaded = this.zzchf.isLoaded();
        }
        return isLoaded;
    }

    public void pause() {
        zzf(null);
    }

    public void resume() {
        zzg(null);
    }

    public void setUserId(String str) {
        zzb.zzcx("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzail) {
            this.zzchf.zzrq();
        }
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        synchronized (this.zzail) {
            this.zzchf.zza(rewardedVideoAdRequestParcel);
        }
    }

    public void zza(com.google.android.gms.ads.internal.reward.client.zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        synchronized (this.zzail) {
            this.zzchf.zza(com_google_android_gms_ads_internal_reward_client_zzd);
        }
    }

    public void zzf(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzail) {
            this.zzchf.pause();
        }
    }

    public void zzg(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzail) {
            Context context = com_google_android_gms_dynamic_zzd == null ? null : (Context) zze.zzad(com_google_android_gms_dynamic_zzd);
            if (context != null) {
                try {
                    this.zzchf.onContextChanged(context);
                } catch (Throwable e) {
                    zzb.zzd("Unable to extract updated context.", e);
                }
            }
            this.zzchf.resume();
        }
    }

    public void zzh(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzail) {
            this.zzchf.destroy();
        }
    }
}
