package com.google.android.gms.internal;

import java.util.Map;

class zzli$zze implements zzep {
    final /* synthetic */ zzli zzcoy;

    private zzli$zze(zzli com_google_android_gms_internal_zzli) {
        this.zzcoy = com_google_android_gms_internal_zzli;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        if (map.keySet().contains("start")) {
            zzli.zza(this.zzcoy);
        } else if (map.keySet().contains("stop")) {
            zzli.zzb(this.zzcoy);
        } else if (map.keySet().contains("cancel")) {
            zzli.zzc(this.zzcoy);
        }
    }
}
