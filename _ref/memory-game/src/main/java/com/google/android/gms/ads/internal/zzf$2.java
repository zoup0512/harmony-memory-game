package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzli$zzd;

class zzf$2 implements zzli$zzd {
    final /* synthetic */ zzf zzakq;
    final /* synthetic */ zzju zzakr;
    final /* synthetic */ Runnable zzaks;

    zzf$2(zzf com_google_android_gms_ads_internal_zzf, zzju com_google_android_gms_internal_zzju, Runnable runnable) {
        this.zzakq = com_google_android_gms_ads_internal_zzf;
        this.zzakr = com_google_android_gms_internal_zzju;
        this.zzaks = runnable;
    }

    public void zzem() {
        if (!this.zzakr.zzcif) {
            zzu.zzfq();
            zzkh.zzb(this.zzaks);
        }
    }
}
