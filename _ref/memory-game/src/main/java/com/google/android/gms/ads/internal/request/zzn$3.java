package com.google.android.gms.ads.internal.request;

class zzn$3 implements Runnable {
    final /* synthetic */ zzn zzcdl;

    zzn$3(zzn com_google_android_gms_ads_internal_request_zzn) {
        this.zzcdl = com_google_android_gms_ads_internal_request_zzn;
    }

    public void run() {
        if (zzn.zzb(this.zzcdl) != null) {
            zzn.zzb(this.zzcdl).release();
            zzn.zza(this.zzcdl, null);
        }
    }
}
