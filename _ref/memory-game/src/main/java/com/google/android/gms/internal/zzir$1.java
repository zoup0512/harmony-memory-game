package com.google.android.gms.internal;

import java.util.Map;

class zzir$1 implements zzep {
    final /* synthetic */ zzir zzces;

    zzir$1(zzir com_google_android_gms_internal_zzir) {
        this.zzces = com_google_android_gms_internal_zzir;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        synchronized (zzir.zza(this.zzces)) {
            if (zzir.zzb(this.zzces).isDone()) {
            } else if (zzir.zzc(this.zzces).equals(map.get("request_id"))) {
                zziu com_google_android_gms_internal_zziu = new zziu(1, map);
                String valueOf = String.valueOf(com_google_android_gms_internal_zziu.getType());
                String valueOf2 = String.valueOf(com_google_android_gms_internal_zziu.zzrj());
                zzkd.zzcx(new StringBuilder((String.valueOf(valueOf).length() + 24) + String.valueOf(valueOf2).length()).append("Invalid ").append(valueOf).append(" request error: ").append(valueOf2).toString());
                zzir.zzb(this.zzces).zzh(com_google_android_gms_internal_zziu);
            }
        }
    }
}
