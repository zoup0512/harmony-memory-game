package com.google.android.gms.internal;

import java.util.Map;

class zzir$3 implements zzep {
    final /* synthetic */ zzir zzces;

    zzir$3(zzir com_google_android_gms_internal_zzir) {
        this.zzces = com_google_android_gms_internal_zzir;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        synchronized (zzir.zza(this.zzces)) {
            if (zzir.zzb(this.zzces).isDone()) {
                return;
            }
            zziu com_google_android_gms_internal_zziu = new zziu(-2, map);
            if (zzir.zzc(this.zzces).equals(com_google_android_gms_internal_zziu.getRequestId())) {
                com_google_android_gms_internal_zziu.zzrm();
                zzir.zzb(this.zzces).zzh(com_google_android_gms_internal_zziu);
                return;
            }
        }
    }
}
