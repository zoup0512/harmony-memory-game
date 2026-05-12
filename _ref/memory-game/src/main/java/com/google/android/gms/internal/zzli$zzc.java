package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzg;

class zzli$zzc implements zzg {
    private zzg zzcom;
    private zzlh zzcoz;

    public zzli$zzc(zzlh com_google_android_gms_internal_zzlh, zzg com_google_android_gms_ads_internal_overlay_zzg) {
        this.zzcoz = com_google_android_gms_internal_zzlh;
        this.zzcom = com_google_android_gms_ads_internal_overlay_zzg;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void zzdx() {
        this.zzcom.zzdx();
        this.zzcoz.zzuc();
    }

    public void zzdy() {
        this.zzcom.zzdy();
        this.zzcoz.zzoa();
    }
}
