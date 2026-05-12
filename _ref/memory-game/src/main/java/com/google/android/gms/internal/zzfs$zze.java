package com.google.android.gms.internal;

import com.google.android.gms.internal.zzla.zza;
import com.google.android.gms.internal.zzla.zzc;

public class zzfs$zze extends zzlb<zzft> {
    private zzfs$zzc zzbmr;

    public zzfs$zze(zzfs$zzc com_google_android_gms_internal_zzfs_zzc) {
        this.zzbmr = com_google_android_gms_internal_zzfs_zzc;
    }

    public void finalize() {
        this.zzbmr.release();
        this.zzbmr = null;
    }

    public int getStatus() {
        return this.zzbmr.getStatus();
    }

    public void reject() {
        this.zzbmr.reject();
    }

    public void zza(zzc<zzft> com_google_android_gms_internal_zzla_zzc_com_google_android_gms_internal_zzft, zza com_google_android_gms_internal_zzla_zza) {
        this.zzbmr.zza(com_google_android_gms_internal_zzla_zzc_com_google_android_gms_internal_zzft, com_google_android_gms_internal_zzla_zza);
    }

    public void zzf(zzft com_google_android_gms_internal_zzft) {
        this.zzbmr.zzg(com_google_android_gms_internal_zzft);
    }

    public /* synthetic */ void zzg(Object obj) {
        zzf((zzft) obj);
    }
}
