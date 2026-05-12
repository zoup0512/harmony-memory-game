package com.google.android.gms.ads.internal.overlay;

import android.os.Looper;

class zzv$1 implements Runnable {
    final /* synthetic */ zzv zzbuq;

    zzv$1(zzv com_google_android_gms_ads_internal_overlay_zzv) {
        this.zzbuq = com_google_android_gms_ads_internal_overlay_zzv;
    }

    public void run() {
        Looper.myLooper().quit();
    }
}
