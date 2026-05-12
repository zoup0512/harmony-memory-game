package com.google.android.gms.internal;

class zzib$2 implements Runnable {
    final /* synthetic */ zzju zzakt;
    final /* synthetic */ zzib zzbyh;

    zzib$2(zzib com_google_android_gms_internal_zzib, zzju com_google_android_gms_internal_zzju) {
        this.zzbyh = com_google_android_gms_internal_zzib;
        this.zzakt = com_google_android_gms_internal_zzju;
    }

    public void run() {
        synchronized (this.zzbyh.zzail) {
            this.zzbyh.zzm(this.zzakt);
        }
    }
}
