package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdt;

class zzl$6 extends zzl$zza<zzdt> {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzl zzavj;
    final /* synthetic */ FrameLayout zzavk;
    final /* synthetic */ FrameLayout zzavl;

    zzl$6(zzl com_google_android_gms_ads_internal_client_zzl, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.zzavk = frameLayout;
        this.zzavl = frameLayout2;
        this.zzala = context;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zze(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzdt zze(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.createNativeAdViewDelegate(zze.zzac(this.zzavk), zze.zzac(this.zzavl));
    }

    public /* synthetic */ Object zzin() {
        return zziq();
    }

    public zzdt zziq() {
        zzdt zzb = zzl.zze(this.zzavj).zzb(this.zzala, this.zzavk, this.zzavl);
        if (zzb != null) {
            return zzb;
        }
        zzl.zza(this.zzavj, this.zzala, "native_ad_view_delegate");
        return new zzam();
    }
}
