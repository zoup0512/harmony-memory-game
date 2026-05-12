package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;

class zzl$2 extends zzl$zza<zzu> {
    final /* synthetic */ String zzaky;
    final /* synthetic */ Context zzala;
    final /* synthetic */ AdSizeParcel zzavh;
    final /* synthetic */ zzl zzavj;

    zzl$2(zzl com_google_android_gms_ads_internal_client_zzl, Context context, AdSizeParcel adSizeParcel, String str) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.zzala = context;
        this.zzavh = adSizeParcel;
        this.zzaky = str;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public zzu zza(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.createSearchAdManager(zze.zzac(this.zzala), this.zzavh, this.zzaky, com.google.android.gms.common.internal.zze.xM);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zza(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzu zzim() {
        zzu zza = zzl.zzb(this.zzavj).zza(this.zzala, this.zzavh, this.zzaky, null, 3);
        if (zza != null) {
            return zza;
        }
        zzl.zza(this.zzavj, this.zzala, "search");
        return new zzak();
    }

    public /* synthetic */ Object zzin() {
        return zzim();
    }
}
