package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

class zzfq$1 implements Runnable {
    final /* synthetic */ Context zzala;
    final /* synthetic */ VersionInfoParcel zzblf;
    final /* synthetic */ zzfq$zza zzblg;
    final /* synthetic */ zzas zzblh;
    final /* synthetic */ String zzbli;
    final /* synthetic */ zzfq zzblj;

    zzfq$1(zzfq com_google_android_gms_internal_zzfq, Context context, VersionInfoParcel versionInfoParcel, zzfq$zza com_google_android_gms_internal_zzfq_zza, zzas com_google_android_gms_internal_zzas, String str) {
        this.zzblj = com_google_android_gms_internal_zzfq;
        this.zzala = context;
        this.zzblf = versionInfoParcel;
        this.zzblg = com_google_android_gms_internal_zzfq_zza;
        this.zzblh = com_google_android_gms_internal_zzas;
        this.zzbli = str;
    }

    public void run() {
        zzfq.zza(this.zzblj, this.zzala, this.zzblf, this.zzblg, this.zzblh).zzbh(this.zzbli);
    }
}
