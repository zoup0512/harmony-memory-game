package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgj;
import com.mopub.common.AdType;

class zzl$7 extends zzl$zza<zzb> {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzgj zzavi;
    final /* synthetic */ zzl zzavj;

    zzl$7(zzl com_google_android_gms_ads_internal_client_zzl, Context context, zzgj com_google_android_gms_internal_zzgj) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.zzala = context;
        this.zzavi = com_google_android_gms_internal_zzgj;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zzf(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzb zzf(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.createRewardedVideoAd(zze.zzac(this.zzala), this.zzavi, com.google.android.gms.common.internal.zze.xM);
    }

    public /* synthetic */ Object zzin() {
        return zzir();
    }

    public zzb zzir() {
        zzb zzb = zzl.zzf(this.zzavj).zzb(this.zzala, this.zzavi);
        if (zzb != null) {
            return zzb;
        }
        zzl.zza(this.zzavj, this.zzala, AdType.REWARDED_VIDEO);
        return new zzan();
    }
}
