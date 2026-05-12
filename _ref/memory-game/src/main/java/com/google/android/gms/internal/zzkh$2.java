package com.google.android.gms.internal;

import android.content.Context;

class zzkh$2 implements Runnable {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzkh zzclh;

    zzkh$2(zzkh com_google_android_gms_internal_zzkh, Context context) {
        this.zzclh = com_google_android_gms_internal_zzkh;
        this.zzala = context;
    }

    public void run() {
        synchronized (zzkh.zza(this.zzclh)) {
            zzkh.zza(this.zzclh, this.zzclh.zzae(this.zzala));
            zzkh.zza(this.zzclh).notifyAll();
        }
    }
}
