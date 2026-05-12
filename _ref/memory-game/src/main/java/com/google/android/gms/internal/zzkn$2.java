package com.google.android.gms.internal;

import com.google.android.gms.internal.zzm.zza;

class zzkn$2 implements zza {
    final /* synthetic */ String zzbjh;
    final /* synthetic */ zzkn$zzc zzcmf;
    final /* synthetic */ zzkn zzcmg;

    zzkn$2(zzkn com_google_android_gms_internal_zzkn, String str, zzkn$zzc com_google_android_gms_internal_zzkn_zzc) {
        this.zzcmg = com_google_android_gms_internal_zzkn;
        this.zzbjh = str;
        this.zzcmf = com_google_android_gms_internal_zzkn_zzc;
    }

    public void zze(zzr com_google_android_gms_internal_zzr) {
        String str = this.zzbjh;
        String valueOf = String.valueOf(com_google_android_gms_internal_zzr.toString());
        zzkd.zzcx(new StringBuilder((String.valueOf(str).length() + 21) + String.valueOf(valueOf).length()).append("Failed to load URL: ").append(str).append("\n").append(valueOf).toString());
        this.zzcmf.zzb(null);
    }
}
