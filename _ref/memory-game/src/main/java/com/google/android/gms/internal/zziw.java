package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zziv.zza;
import java.util.WeakHashMap;

@zzin
public final class zziw {
    private WeakHashMap<Context, zza> zzcha = new WeakHashMap();

    public zziv zzy(Context context) {
        zza com_google_android_gms_internal_zziw_zza = (zza) this.zzcha.get(context);
        zziv zzrn = (com_google_android_gms_internal_zziw_zza == null || com_google_android_gms_internal_zziw_zza.hasExpired() || !((Boolean) zzdc.zzbas.get()).booleanValue()) ? new zza(context).zzrn() : new zza(context, com_google_android_gms_internal_zziw_zza.zzchc).zzrn();
        this.zzcha.put(context, new zza(this, zzrn));
        return zzrn;
    }
}
