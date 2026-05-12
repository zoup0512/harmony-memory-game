package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;

abstract class zzl$zza<T> {
    final /* synthetic */ zzl zzavj;

    private zzl$zza(zzl com_google_android_gms_ads_internal_client_zzl) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
    }

    @Nullable
    protected abstract T zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException;

    @Nullable
    protected abstract T zzin();

    @Nullable
    protected final T zziu() {
        T t = null;
        zzx zza = zzl.zza(this.zzavj);
        if (zza == null) {
            zzb.zzcx("ClientApi class cannot be loaded.");
        } else {
            try {
                t = zzb(zza);
            } catch (Throwable e) {
                zzb.zzd("Cannot invoke local loader using ClientApi class", e);
            }
        }
        return t;
    }
}
