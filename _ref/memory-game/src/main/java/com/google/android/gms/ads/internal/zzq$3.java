package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.internal.zzkd;

class zzq$3 implements Runnable {
    final /* synthetic */ zzq zzamx;
    final /* synthetic */ zze zzamz;

    zzq$3(zzq com_google_android_gms_ads_internal_zzq, zze com_google_android_gms_ads_internal_formats_zze) {
        this.zzamx = com_google_android_gms_ads_internal_zzq;
        this.zzamz = com_google_android_gms_ads_internal_formats_zze;
    }

    public void run() {
        try {
            if (this.zzamx.zzajs.zzapl != null) {
                this.zzamx.zzajs.zzapl.zza(this.zzamz);
            }
        } catch (Throwable e) {
            zzkd.zzd("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
        }
    }
}
