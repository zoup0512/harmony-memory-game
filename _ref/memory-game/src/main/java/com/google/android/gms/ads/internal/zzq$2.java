package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.internal.zzkd;

class zzq$2 implements Runnable {
    final /* synthetic */ zzq zzamx;
    final /* synthetic */ zzd zzamy;

    zzq$2(zzq com_google_android_gms_ads_internal_zzq, zzd com_google_android_gms_ads_internal_formats_zzd) {
        this.zzamx = com_google_android_gms_ads_internal_zzq;
        this.zzamy = com_google_android_gms_ads_internal_formats_zzd;
    }

    public void run() {
        try {
            if (this.zzamx.zzajs.zzapk != null) {
                this.zzamx.zzajs.zzapk.zza(this.zzamy);
            }
        } catch (Throwable e) {
            zzkd.zzd("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
        }
    }
}
