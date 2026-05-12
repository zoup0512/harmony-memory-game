package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.zzu;

class zzgy$3 implements Runnable {
    final /* synthetic */ AdOverlayInfoParcel zzamc;
    final /* synthetic */ zzgy zzbpx;

    zzgy$3(zzgy com_google_android_gms_internal_zzgy, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzbpx = com_google_android_gms_internal_zzgy;
        this.zzamc = adOverlayInfoParcel;
    }

    public void run() {
        zzu.zzfo().zza(zzgy.zzb(this.zzbpx), this.zzamc);
    }
}
