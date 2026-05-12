package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.zzl.zza;

class zzl$zza$1 implements Runnable {
    final /* synthetic */ AdOverlayInfoParcel zzamc;
    final /* synthetic */ zza zzamd;

    zzl$zza$1(zza com_google_android_gms_ads_internal_zzl_zza, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzamd = com_google_android_gms_ads_internal_zzl_zza;
        this.zzamc = adOverlayInfoParcel;
    }

    public void run() {
        zzu.zzfo().zza(this.zzamd.zzamb.zzajs.zzagf, this.zzamc);
    }
}
