package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzv extends zzal {
    private static final String ID = zzaf.CUSTOM_VAR.toString();
    private static final String NAME = zzag.NAME.toString();
    private static final String avt = zzag.DEFAULT_VALUE.toString();
    private final DataLayer auG;

    public zzv(DataLayer dataLayer) {
        super(ID, NAME);
        this.auG = dataLayer;
    }

    public zza zzav(Map<String, zza> map) {
        Object obj = this.auG.get(zzdl.zzg((zza) map.get(NAME)));
        if (obj != null) {
            return zzdl.zzap(obj);
        }
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(avt);
        return com_google_android_gms_internal_zzai_zza != null ? com_google_android_gms_internal_zzai_zza : zzdl.zzcdu();
    }

    public boolean zzcag() {
        return false;
    }
}
