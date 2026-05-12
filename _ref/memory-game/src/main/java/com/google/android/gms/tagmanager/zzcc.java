package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

abstract class zzcc extends zzch {
    public zzcc(String str) {
        super(str);
    }

    protected boolean zza(zza com_google_android_gms_internal_zzai_zza, zza com_google_android_gms_internal_zzai_zza2, Map<String, zza> map) {
        zzdk zzh = zzdl.zzh(com_google_android_gms_internal_zzai_zza);
        zzdk zzh2 = zzdl.zzh(com_google_android_gms_internal_zzai_zza2);
        return (zzh == zzdl.zzcds() || zzh2 == zzdl.zzcds()) ? false : zza(zzh, zzh2, (Map) map);
    }

    protected abstract boolean zza(zzdk com_google_android_gms_tagmanager_zzdk, zzdk com_google_android_gms_tagmanager_zzdk2, Map<String, zza> map);
}
