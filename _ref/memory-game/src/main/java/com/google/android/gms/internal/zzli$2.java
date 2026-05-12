package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzd;

class zzli$2 implements Runnable {
    final /* synthetic */ zzli zzcoy;

    zzli$2(zzli com_google_android_gms_internal_zzli) {
        this.zzcoy = com_google_android_gms_internal_zzli;
    }

    public void run() {
        this.zzcoy.zzbgf.zzuu();
        zzd zzuh = this.zzcoy.zzbgf.zzuh();
        if (zzuh != null) {
            zzuh.zznx();
        }
        if (zzli.zzd(this.zzcoy) != null) {
            zzli.zzd(this.zzcoy).zzen();
            zzli.zza(this.zzcoy, null);
        }
    }
}
