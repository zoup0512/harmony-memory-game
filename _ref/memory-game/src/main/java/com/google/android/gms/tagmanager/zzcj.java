package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzcj extends zzal {
    private static final String ID = zzaf.RANDOM.toString();
    private static final String awX = zzag.MIN.toString();
    private static final String awY = zzag.MAX.toString();

    public zzcj() {
        super(ID, new String[0]);
    }

    public zza zzav(Map<String, zza> map) {
        double doubleValue;
        double d;
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(awX);
        zza com_google_android_gms_internal_zzai_zza2 = (zza) map.get(awY);
        if (!(com_google_android_gms_internal_zzai_zza == null || com_google_android_gms_internal_zzai_zza == zzdl.zzcdu() || com_google_android_gms_internal_zzai_zza2 == null || com_google_android_gms_internal_zzai_zza2 == zzdl.zzcdu())) {
            zzdk zzh = zzdl.zzh(com_google_android_gms_internal_zzai_zza);
            zzdk zzh2 = zzdl.zzh(com_google_android_gms_internal_zzai_zza2);
            if (!(zzh == zzdl.zzcds() || zzh2 == zzdl.zzcds())) {
                double doubleValue2 = zzh.doubleValue();
                doubleValue = zzh2.doubleValue();
                if (doubleValue2 <= doubleValue) {
                    d = doubleValue2;
                    return zzdl.zzap(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
                }
            }
        }
        doubleValue = 2.147483647E9d;
        d = 0.0d;
        return zzdl.zzap(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
    }

    public boolean zzcag() {
        return false;
    }
}
