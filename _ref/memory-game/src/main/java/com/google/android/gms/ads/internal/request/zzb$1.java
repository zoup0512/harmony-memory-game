package com.google.android.gms.ads.internal.request;

class zzb$1 implements Runnable {
    final /* synthetic */ zzb zzcah;

    zzb$1(zzb com_google_android_gms_ads_internal_request_zzb) {
        this.zzcah = com_google_android_gms_ads_internal_request_zzb;
    }

    public void run() {
        synchronized (zzb.zza(this.zzcah)) {
            if (this.zzcah.zzcag == null) {
                return;
            }
            this.zzcah.onStop();
            zzb.zza(this.zzcah, 2, "Timed out waiting for ad response.");
        }
    }
}
