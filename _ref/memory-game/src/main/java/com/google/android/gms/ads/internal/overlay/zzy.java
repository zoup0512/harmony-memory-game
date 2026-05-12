package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkh;

@zzin
class zzy implements Runnable {
    private boolean mCancelled = false;
    private zzk zzbwf;

    zzy(zzk com_google_android_gms_ads_internal_overlay_zzk) {
        this.zzbwf = com_google_android_gms_ads_internal_overlay_zzk;
    }

    public void cancel() {
        this.mCancelled = true;
        zzkh.zzclc.removeCallbacks(this);
    }

    public void run() {
        if (!this.mCancelled) {
            this.zzbwf.zzoo();
            zzpk();
        }
    }

    public void zzpk() {
        zzkh.zzclc.postDelayed(this, 250);
    }
}
