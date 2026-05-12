package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;

class zzl$5 extends zzl$zza<zzz> {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzl zzavj;

    zzl$5(zzl com_google_android_gms_ads_internal_client_zzl, Context context) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.zzala = context;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zzd(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzz zzd(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.getMobileAdsSettingsManagerWithClientJarVersion(zze.zzac(this.zzala), com.google.android.gms.common.internal.zze.xM);
    }

    public /* synthetic */ Object zzin() {
        return zzip();
    }

    public zzz zzip() {
        zzz zzm = zzl.zzd(this.zzavj).zzm(this.zzala);
        if (zzm != null) {
            return zzm;
        }
        zzl.zza(this.zzavj, this.zzala, "mobile_ads_settings");
        return new zzal();
    }
}
