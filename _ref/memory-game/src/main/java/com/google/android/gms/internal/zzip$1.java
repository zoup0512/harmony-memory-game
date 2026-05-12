package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.concurrent.Callable;

class zzip$1 implements Callable<Void> {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzio zzceb;
    final /* synthetic */ AdRequestInfoParcel zzcec;
    final /* synthetic */ Bundle zzced;

    zzip$1(zzio com_google_android_gms_internal_zzio, Context context, AdRequestInfoParcel adRequestInfoParcel, Bundle bundle) {
        this.zzceb = com_google_android_gms_internal_zzio;
        this.zzala = context;
        this.zzcec = adRequestInfoParcel;
        this.zzced = bundle;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzcx();
    }

    public Void zzcx() throws Exception {
        this.zzceb.zzcdw.zza(this.zzala, this.zzcec.zzcas.packageName, this.zzced);
        return null;
    }
}
