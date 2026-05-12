package com.google.android.gms.internal;

import android.content.Context;
import java.util.Map;

@zzin
public class zzkn {
    private static zzl zzcmc;
    private static final Object zzcmd = new Object();
    public static final zza<Void> zzcme = new 1();

    public zzkn(Context context) {
        zzap(context);
    }

    private static zzl zzap(Context context) {
        zzl com_google_android_gms_internal_zzl;
        synchronized (zzcmd) {
            if (zzcmc == null) {
                zzcmc = zzac.zza(context.getApplicationContext());
            }
            com_google_android_gms_internal_zzl = zzcmc;
        }
        return com_google_android_gms_internal_zzl;
    }

    public zzky<String> zza(int i, String str, Map<String, String> map, byte[] bArr) {
        Object com_google_android_gms_internal_zzkn_zzc = new zzc(this, null);
        zzcmc.zze(new 3(this, i, str, com_google_android_gms_internal_zzkn_zzc, new 2(this, str, com_google_android_gms_internal_zzkn_zzc), bArr, map));
        return com_google_android_gms_internal_zzkn_zzc;
    }

    public <T> zzky<T> zza(String str, zza<T> com_google_android_gms_internal_zzkn_zza_T) {
        Object com_google_android_gms_internal_zzkn_zzc = new zzc(this, null);
        zzcmc.zze(new zzb(str, com_google_android_gms_internal_zzkn_zza_T, com_google_android_gms_internal_zzkn_zzc));
        return com_google_android_gms_internal_zzkn_zzc;
    }

    public zzky<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }
}
