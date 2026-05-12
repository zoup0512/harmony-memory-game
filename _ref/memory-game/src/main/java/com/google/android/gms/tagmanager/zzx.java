package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.List;
import java.util.Map;

class zzx extends zzdj {
    private static final String ID = zzaf.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzag.VALUE.toString();
    private static final String avE = zzag.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer auG;

    public zzx(DataLayer dataLayer) {
        super(ID, VALUE);
        this.auG = dataLayer;
    }

    private void zza(zza com_google_android_gms_internal_zzai_zza) {
        if (com_google_android_gms_internal_zzai_zza != null && com_google_android_gms_internal_zzai_zza != zzdl.zzcdo()) {
            String zzg = zzdl.zzg(com_google_android_gms_internal_zzai_zza);
            if (zzg != zzdl.zzcdt()) {
                this.auG.zzom(zzg);
            }
        }
    }

    private void zzb(zza com_google_android_gms_internal_zzai_zza) {
        if (com_google_android_gms_internal_zzai_zza != null && com_google_android_gms_internal_zzai_zza != zzdl.zzcdo()) {
            Object zzl = zzdl.zzl(com_google_android_gms_internal_zzai_zza);
            if (zzl instanceof List) {
                for (Object zzl2 : (List) zzl2) {
                    if (zzl2 instanceof Map) {
                        this.auG.push((Map) zzl2);
                    }
                }
            }
        }
    }

    public void zzax(Map<String, zza> map) {
        zzb((zza) map.get(VALUE));
        zza((zza) map.get(avE));
    }
}
