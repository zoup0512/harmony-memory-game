package com.google.android.gms.internal;

class zzfr$4 implements Runnable {
    final /* synthetic */ zzfr zzbln;
    final /* synthetic */ String zzblp;

    zzfr$4(zzfr com_google_android_gms_internal_zzfr, String str) {
        this.zzbln = com_google_android_gms_internal_zzfr;
        this.zzblp = str;
    }

    public void run() {
        zzfr.zza(this.zzbln).loadData(this.zzblp, "text/html", "UTF-8");
    }
}
