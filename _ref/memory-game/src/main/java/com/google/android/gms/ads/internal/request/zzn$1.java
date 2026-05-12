package com.google.android.gms.ads.internal.request;

import com.google.android.gms.internal.zzju.zza;

class zzn$1 implements Runnable {
    final /* synthetic */ zza zzake;
    final /* synthetic */ zzn zzcdl;

    zzn$1(zzn com_google_android_gms_ads_internal_request_zzn, zza com_google_android_gms_internal_zzju_zza) {
        this.zzcdl = com_google_android_gms_ads_internal_request_zzn;
        this.zzake = com_google_android_gms_internal_zzju_zza;
    }

    public void run() {
        zzn.zza(this.zzcdl).zza(this.zzake);
        if (zzn.zzb(this.zzcdl) != null) {
            zzn.zzb(this.zzcdl).release();
            zzn.zza(this.zzcdl, null);
        }
    }
}
