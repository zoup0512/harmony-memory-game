package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzhi;

class zzl$9 extends zzl$zza<zzhi> {
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ zzl zzavj;

    zzl$9(zzl com_google_android_gms_ads_internal_client_zzl, Activity activity) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.val$activity = activity;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zzh(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzhi zzh(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.createAdOverlay(zze.zzac(this.val$activity));
    }

    public /* synthetic */ Object zzin() {
        return zzit();
    }

    public zzhi zzit() {
        zzhi zzf = zzl.zzh(this.zzavj).zzf(this.val$activity);
        if (zzf != null) {
            return zzf;
        }
        zzl.zza(this.zzavj, this.val$activity, "ad_overlay");
        return null;
    }
}
