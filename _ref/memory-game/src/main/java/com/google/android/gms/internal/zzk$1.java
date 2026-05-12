package com.google.android.gms.internal;

class zzk$1 implements Runnable {
    final /* synthetic */ String zzap;
    final /* synthetic */ long zzaq;
    final /* synthetic */ zzk zzar;

    zzk$1(zzk com_google_android_gms_internal_zzk, String str, long j) {
        this.zzar = com_google_android_gms_internal_zzk;
        this.zzap = str;
        this.zzaq = j;
    }

    public void run() {
        zzk.zzd(this.zzar).zza(this.zzap, this.zzaq);
        zzk.zzd(this.zzar).zzd(toString());
    }
}
