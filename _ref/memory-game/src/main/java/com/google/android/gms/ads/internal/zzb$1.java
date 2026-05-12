package com.google.android.gms.ads.internal;

import android.content.Intent;

class zzb$1 implements Runnable {
    final /* synthetic */ Intent zzakb;
    final /* synthetic */ zzb zzakc;

    zzb$1(zzb com_google_android_gms_ads_internal_zzb, Intent intent) {
        this.zzakc = com_google_android_gms_ads_internal_zzb;
        this.zzakb = intent;
    }

    public void run() {
        int zzd = zzu.zzga().zzd(this.zzakb);
        zzu.zzga();
        if (!(zzd != 0 || this.zzakc.zzajs.zzapb == null || this.zzakc.zzajs.zzapb.zzbtm == null || this.zzakc.zzajs.zzapb.zzbtm.zzuh() == null)) {
            this.zzakc.zzajs.zzapb.zzbtm.zzuh().close();
        }
        this.zzakc.zzajs.zzapx = false;
    }
}
