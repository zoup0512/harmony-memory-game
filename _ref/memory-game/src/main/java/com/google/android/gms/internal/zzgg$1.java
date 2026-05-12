package com.google.android.gms.internal;

import java.util.concurrent.Callable;

class zzgg$1 implements Callable<zzge> {
    final /* synthetic */ zzgd zzbpa;
    final /* synthetic */ zzgg zzbpb;

    zzgg$1(zzgg com_google_android_gms_internal_zzgg, zzgd com_google_android_gms_internal_zzgd) {
        this.zzbpb = com_google_android_gms_internal_zzgg;
        this.zzbpa = com_google_android_gms_internal_zzgd;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzmn();
    }

    public zzge zzmn() throws Exception {
        synchronized (zzgg.zza(this.zzbpb)) {
            if (zzgg.zzb(this.zzbpb)) {
                return null;
            }
            return this.zzbpa.zza(zzgg.zzc(this.zzbpb), zzgg.zzd(this.zzbpb));
        }
    }
}
