package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

abstract class zzdg extends zzch {
    public zzdg(String str) {
        super(str);
    }

    protected boolean zza(zza com_google_android_gms_internal_zzai_zza, zza com_google_android_gms_internal_zzai_zza2, Map<String, zza> map) {
        String zzg = zzdl.zzg(com_google_android_gms_internal_zzai_zza);
        String zzg2 = zzdl.zzg(com_google_android_gms_internal_zzai_zza2);
        return (zzg == zzdl.zzcdt() || zzg2 == zzdl.zzcdt()) ? false : zza(zzg, zzg2, (Map) map);
    }

    protected abstract boolean zza(String str, String str2, Map<String, zza> map);
}
