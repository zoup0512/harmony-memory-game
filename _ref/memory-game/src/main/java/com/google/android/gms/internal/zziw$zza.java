package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;

class zziw$zza {
    public final long zzchb = zzu.zzfu().currentTimeMillis();
    public final zziv zzchc;
    final /* synthetic */ zziw zzchd;

    public zziw$zza(zziw com_google_android_gms_internal_zziw, zziv com_google_android_gms_internal_zziv) {
        this.zzchd = com_google_android_gms_internal_zziw;
        this.zzchc = com_google_android_gms_internal_zziv;
    }

    public boolean hasExpired() {
        return ((Long) zzdc.zzbat.get()).longValue() + this.zzchb < zzu.zzfu().currentTimeMillis();
    }
}
