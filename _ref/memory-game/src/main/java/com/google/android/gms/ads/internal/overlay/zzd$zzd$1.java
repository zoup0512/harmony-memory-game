package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;
import com.google.android.gms.ads.internal.overlay.zzd.zzd;

class zzd$zzd$1 implements Runnable {
    final /* synthetic */ Drawable zzbth;
    final /* synthetic */ zzd zzbti;

    zzd$zzd$1(zzd com_google_android_gms_ads_internal_overlay_zzd_zzd, Drawable drawable) {
        this.zzbti = com_google_android_gms_ads_internal_overlay_zzd_zzd;
        this.zzbth = drawable;
    }

    public void run() {
        zzd.zza(this.zzbti.zzbtd).getWindow().setBackgroundDrawable(this.zzbth);
    }
}
