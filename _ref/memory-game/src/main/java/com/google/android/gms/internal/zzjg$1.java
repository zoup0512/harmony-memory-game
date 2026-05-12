package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;

class zzjg$1 implements Runnable {
    final /* synthetic */ AdRequestParcel zzalq;
    final /* synthetic */ zzgk zzchr;
    final /* synthetic */ zzjg zzchs;

    zzjg$1(zzjg com_google_android_gms_internal_zzjg, AdRequestParcel adRequestParcel, zzgk com_google_android_gms_internal_zzgk) {
        this.zzchs = com_google_android_gms_internal_zzjg;
        this.zzalq = adRequestParcel;
        this.zzchr = com_google_android_gms_internal_zzgk;
    }

    public void run() {
        zzjg.zza(this.zzchs, this.zzalq, this.zzchr);
    }
}
