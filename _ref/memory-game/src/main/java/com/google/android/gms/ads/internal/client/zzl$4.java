package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgj;

class zzl$4 extends zzl$zza<zzs> {
    final /* synthetic */ String zzaky;
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzgj zzavi;
    final /* synthetic */ zzl zzavj;

    zzl$4(zzl com_google_android_gms_ads_internal_client_zzl, Context context, String str, zzgj com_google_android_gms_internal_zzgj) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.zzala = context;
        this.zzaky = str;
        this.zzavi = com_google_android_gms_internal_zzgj;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zzc(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzs zzc(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.createAdLoaderBuilder(zze.zzac(this.zzala), this.zzaky, this.zzavi, com.google.android.gms.common.internal.zze.xM);
    }

    public /* synthetic */ Object zzin() {
        return zzio();
    }

    public zzs zzio() {
        zzs zza = zzl.zzc(this.zzavj).zza(this.zzala, this.zzaky, this.zzavi);
        if (zza != null) {
            return zza;
        }
        zzl.zza(this.zzavj, this.zzala, "native_ad");
        return new zzaj();
    }
}
