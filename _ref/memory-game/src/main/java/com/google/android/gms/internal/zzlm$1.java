package com.google.android.gms.internal;

import java.util.Map;

class zzlm$1 implements Runnable {
    final /* synthetic */ Map zzcqe;
    final /* synthetic */ zzlm zzcqf;

    zzlm$1(zzlm com_google_android_gms_internal_zzlm, Map map) {
        this.zzcqf = com_google_android_gms_internal_zzlm;
        this.zzcqe = map;
    }

    public void run() {
        zzlm.zzb(this.zzcqf).zza("pubVideoCmd", this.zzcqe);
    }
}
