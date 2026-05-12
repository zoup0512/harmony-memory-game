package com.google.android.gms.ads.internal.request;

import com.google.android.gms.internal.zzla.zzc;

class zzd$1 implements zzc<AdRequestInfoParcel> {
    final /* synthetic */ zzk zzcal;
    final /* synthetic */ zzd zzcam;

    zzd$1(zzd com_google_android_gms_ads_internal_request_zzd, zzk com_google_android_gms_ads_internal_request_zzk) {
        this.zzcam = com_google_android_gms_ads_internal_request_zzd;
        this.zzcal = com_google_android_gms_ads_internal_request_zzk;
    }

    public void zzc(AdRequestInfoParcel adRequestInfoParcel) {
        if (!this.zzcam.zza(this.zzcal, adRequestInfoParcel)) {
            this.zzcam.zzqw();
        }
    }

    public /* synthetic */ void zzd(Object obj) {
        zzc((AdRequestInfoParcel) obj);
    }
}
