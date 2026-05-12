package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.dynamic.zze;

class zzjg$2 implements Runnable {
    final /* synthetic */ AdRequestParcel zzalq;
    final /* synthetic */ zzgk zzchr;
    final /* synthetic */ zzjg zzchs;
    final /* synthetic */ zzjj zzcht;

    zzjg$2(zzjg com_google_android_gms_internal_zzjg, zzgk com_google_android_gms_internal_zzgk, AdRequestParcel adRequestParcel, zzjj com_google_android_gms_internal_zzjj) {
        this.zzchs = com_google_android_gms_internal_zzjg;
        this.zzchr = com_google_android_gms_internal_zzgk;
        this.zzalq = adRequestParcel;
        this.zzcht = com_google_android_gms_internal_zzjj;
    }

    public void run() {
        try {
            this.zzchr.zza(zze.zzac(zzjg.zza(this.zzchs)), this.zzalq, null, this.zzcht, zzjg.zzb(this.zzchs));
        } catch (Throwable e) {
            Throwable th = e;
            String str = "Fail to initialize adapter ";
            String valueOf = String.valueOf(zzjg.zzc(this.zzchs));
            zzkd.zzd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            this.zzchs.zza(zzjg.zzc(this.zzchs), 0);
        }
    }
}
