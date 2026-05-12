package com.google.android.gms.ads.internal.request;

import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzla;

class zzb$2 implements Runnable {
    final /* synthetic */ zzb zzcah;
    final /* synthetic */ zzla zzcai;

    zzb$2(zzb com_google_android_gms_ads_internal_request_zzb, zzla com_google_android_gms_internal_zzla) {
        this.zzcah = com_google_android_gms_ads_internal_request_zzb;
        this.zzcai = com_google_android_gms_internal_zzla;
    }

    public void run() {
        synchronized (zzb.zza(this.zzcah)) {
            this.zzcah.zzcag = this.zzcah.zza(zzb.zzb(this.zzcah).zzaow, this.zzcai);
            if (this.zzcah.zzcag == null) {
                zzb.zza(this.zzcah, 0, "Could not start the ad request service.");
                zzkh.zzclc.removeCallbacks(zzb.zzc(this.zzcah));
            }
        }
    }
}
