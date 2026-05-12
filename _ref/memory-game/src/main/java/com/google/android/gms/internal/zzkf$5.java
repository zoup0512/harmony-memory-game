package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

class zzkf$5 extends zzkf$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzkf$zzb zzcko;

    zzkf$5(Context context, zzkf$zzb com_google_android_gms_internal_zzkf_zzb) {
        this.zzala = context;
        this.zzcko = com_google_android_gms_internal_zzkf_zzb;
        super();
    }

    public void zzew() {
        SharedPreferences zzn = zzkf.zzn(this.zzala);
        Bundle bundle = new Bundle();
        bundle.putBoolean("use_https", zzn.getBoolean("use_https", true));
        if (this.zzcko != null) {
            this.zzcko.zzg(bundle);
        }
    }
}
