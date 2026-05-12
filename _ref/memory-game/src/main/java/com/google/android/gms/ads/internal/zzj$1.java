package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import java.lang.ref.WeakReference;

class zzj$1 implements Runnable {
    final /* synthetic */ AdRequestParcel zzalq;
    final /* synthetic */ zzj zzalr;

    zzj$1(zzj com_google_android_gms_ads_internal_zzj, AdRequestParcel adRequestParcel) {
        this.zzalr = com_google_android_gms_ads_internal_zzj;
        this.zzalq = adRequestParcel;
    }

    public void run() {
        synchronized (zzj.zza(this.zzalr)) {
            zzq zzer = this.zzalr.zzer();
            zzj.zza(this.zzalr, new WeakReference(zzer));
            zzer.zzb(zzj.zzb(this.zzalr));
            zzer.zzb(zzj.zzc(this.zzalr));
            zzer.zza(zzj.zzd(this.zzalr));
            zzer.zza(zzj.zze(this.zzalr));
            zzer.zzb(zzj.zzf(this.zzalr));
            zzer.zzb(zzj.zzg(this.zzalr));
            zzer.zzb(zzj.zzh(this.zzalr));
            zzer.zza(zzj.zzi(this.zzalr));
            zzer.zzb(this.zzalq);
        }
    }
}
