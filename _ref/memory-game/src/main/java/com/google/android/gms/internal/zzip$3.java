package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;

class zzip$3 implements Runnable {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzio zzceb;
    final /* synthetic */ AdRequestInfoParcel zzcec;
    final /* synthetic */ zzir zzcee;

    zzip$3(zzio com_google_android_gms_internal_zzio, Context context, zzir com_google_android_gms_internal_zzir, AdRequestInfoParcel adRequestInfoParcel) {
        this.zzceb = com_google_android_gms_internal_zzio;
        this.zzala = context;
        this.zzcee = com_google_android_gms_internal_zzir;
        this.zzcec = adRequestInfoParcel;
    }

    public void run() {
        this.zzceb.zzcdr.zza(this.zzala, this.zzcee, this.zzcec.zzaow);
    }
}
