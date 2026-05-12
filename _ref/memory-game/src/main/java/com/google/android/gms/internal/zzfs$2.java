package com.google.android.gms.internal;

import com.google.android.gms.internal.zzla.zzc;

class zzfs$2 implements zzc<zzfp> {
    final /* synthetic */ zzfs zzbly;
    final /* synthetic */ zzfs$zzd zzbmf;

    zzfs$2(zzfs com_google_android_gms_internal_zzfs, zzfs$zzd com_google_android_gms_internal_zzfs_zzd) {
        this.zzbly = com_google_android_gms_internal_zzfs;
        this.zzbmf = com_google_android_gms_internal_zzfs_zzd;
    }

    public void zza(zzfp com_google_android_gms_internal_zzfp) {
        synchronized (zzfs.zzc(this.zzbly)) {
            zzfs.zza(this.zzbly, 0);
            if (!(zzfs.zzg(this.zzbly) == null || this.zzbmf == zzfs.zzg(this.zzbly))) {
                zzkd.v("New JS engine is loaded, marking previous one as destroyable.");
                zzfs.zzg(this.zzbly).zzmd();
            }
            zzfs.zza(this.zzbly, this.zzbmf);
        }
    }

    public /* synthetic */ void zzd(Object obj) {
        zza((zzfp) obj);
    }
}
