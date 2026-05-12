package com.google.android.gms.internal;

import com.google.android.gms.internal.zzla.zza;

class zzfs$3 implements zza {
    final /* synthetic */ zzfs zzbly;
    final /* synthetic */ zzfs$zzd zzbmf;

    zzfs$3(zzfs com_google_android_gms_internal_zzfs, zzfs$zzd com_google_android_gms_internal_zzfs_zzd) {
        this.zzbly = com_google_android_gms_internal_zzfs;
        this.zzbmf = com_google_android_gms_internal_zzfs_zzd;
    }

    public void run() {
        synchronized (zzfs.zzc(this.zzbly)) {
            zzfs.zza(this.zzbly, 1);
            zzkd.v("Failed loading new engine. Marking new engine destroyable.");
            this.zzbmf.zzmd();
        }
    }
}
