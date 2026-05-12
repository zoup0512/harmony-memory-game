package com.google.android.gms.internal;

class zzfr$5 implements Runnable {
    final /* synthetic */ String zzbli;
    final /* synthetic */ zzfr zzbln;

    zzfr$5(zzfr com_google_android_gms_internal_zzfr, String str) {
        this.zzbln = com_google_android_gms_internal_zzfr;
        this.zzbli = str;
    }

    public void run() {
        zzfr.zza(this.zzbln).loadUrl(this.zzbli);
    }
}
