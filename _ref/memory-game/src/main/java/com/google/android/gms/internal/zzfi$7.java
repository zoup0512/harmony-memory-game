package com.google.android.gms.internal;

class zzfi$7 implements Runnable {
    final /* synthetic */ zzfi zzbjt;
    final /* synthetic */ zzfi$zza zzbkf;
    final /* synthetic */ zzfj zzbkg;

    zzfi$7(zzfi com_google_android_gms_internal_zzfi, zzfi$zza com_google_android_gms_internal_zzfi_zza, zzfj com_google_android_gms_internal_zzfj) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
        this.zzbkf = com_google_android_gms_internal_zzfi_zza;
        this.zzbkg = com_google_android_gms_internal_zzfj;
    }

    public void run() {
        try {
            this.zzbkf.zzb(this.zzbkg);
        } catch (Throwable e) {
            zzkd.zzd("Could not propagate interstitial ad event.", e);
        }
    }
}
