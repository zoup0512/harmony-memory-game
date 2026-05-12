package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class zzt extends zzal {
    private static final String ID = zzaf.FUNCTION_CALL.toString();
    private static final String auy = zzag.ADDITIONAL_PARAMS.toString();
    private static final String avi = zzag.FUNCTION_CALL_NAME.toString();
    private final zza avj;

    public interface zza {
        Object zze(String str, Map<String, Object> map);
    }

    public zzt(zza com_google_android_gms_tagmanager_zzt_zza) {
        super(ID, avi);
        this.avj = com_google_android_gms_tagmanager_zzt_zza;
    }

    public com.google.android.gms.internal.zzai.zza zzav(Map<String, com.google.android.gms.internal.zzai.zza> map) {
        String zzg = zzdl.zzg((com.google.android.gms.internal.zzai.zza) map.get(avi));
        Map hashMap = new HashMap();
        com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza = (com.google.android.gms.internal.zzai.zza) map.get(auy);
        if (com_google_android_gms_internal_zzai_zza != null) {
            Object zzl = zzdl.zzl(com_google_android_gms_internal_zzai_zza);
            if (zzl instanceof Map) {
                for (Entry entry : ((Map) zzl).entrySet()) {
                    hashMap.put(entry.getKey().toString(), entry.getValue());
                }
            } else {
                zzbn.zzcx("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzdl.zzcdu();
            }
        }
        try {
            return zzdl.zzap(this.avj.zze(zzg, hashMap));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.zzcx(new StringBuilder((String.valueOf(zzg).length() + 34) + String.valueOf(valueOf).length()).append("Custom macro/tag ").append(zzg).append(" threw exception ").append(valueOf).toString());
            return zzdl.zzcdu();
        }
    }

    public boolean zzcag() {
        return false;
    }
}
