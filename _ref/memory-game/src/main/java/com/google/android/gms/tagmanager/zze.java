package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zze extends zzal {
    private static final String ID = zzaf.ADWORDS_CLICK_REFERRER.toString();
    private static final String auw = zzag.COMPONENT.toString();
    private static final String aux = zzag.CONVERSION_ID.toString();
    private final Context zzagf;

    public zze(Context context) {
        super(ID, aux);
        this.zzagf = context;
    }

    public zza zzav(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(aux);
        if (com_google_android_gms_internal_zzai_zza == null) {
            return zzdl.zzcdu();
        }
        String zzg = zzdl.zzg(com_google_android_gms_internal_zzai_zza);
        com_google_android_gms_internal_zzai_zza = (zza) map.get(auw);
        String zzh = zzbe.zzh(this.zzagf, zzg, com_google_android_gms_internal_zzai_zza != null ? zzdl.zzg(com_google_android_gms_internal_zzai_zza) : null);
        return zzh != null ? zzdl.zzap(zzh) : zzdl.zzcdu();
    }

    public boolean zzcag() {
        return true;
    }
}
