package com.google.android.gms.internal;

import java.util.concurrent.Callable;

class zzdb$1 implements Callable<T> {
    final /* synthetic */ zzcy zzaxv;
    final /* synthetic */ zzdb zzaxw;

    zzdb$1(zzdb com_google_android_gms_internal_zzdb, zzcy com_google_android_gms_internal_zzcy) {
        this.zzaxw = com_google_android_gms_internal_zzdb;
        this.zzaxv = com_google_android_gms_internal_zzcy;
    }

    public T call() {
        return this.zzaxv.zza(zzdb.zza(this.zzaxw));
    }
}
