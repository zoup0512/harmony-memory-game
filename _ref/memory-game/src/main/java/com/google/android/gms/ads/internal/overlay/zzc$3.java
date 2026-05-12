package com.google.android.gms.ads.internal.overlay;

class zzc$3 implements Runnable {
    final /* synthetic */ zzc zzbsk;
    final /* synthetic */ String zzbsl;
    final /* synthetic */ String zzbsm;

    zzc$3(zzc com_google_android_gms_ads_internal_overlay_zzc, String str, String str2) {
        this.zzbsk = com_google_android_gms_ads_internal_overlay_zzc;
        this.zzbsl = str;
        this.zzbsm = str2;
    }

    public void run() {
        if (zzc.zza(this.zzbsk) != null) {
            zzc.zza(this.zzbsk).zzl(this.zzbsl, this.zzbsm);
        }
    }
}
