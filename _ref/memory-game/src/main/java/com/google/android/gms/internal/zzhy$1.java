package com.google.android.gms.internal;

class zzhy$1 implements Runnable {
    final /* synthetic */ zzhy zzbxw;

    zzhy$1(zzhy com_google_android_gms_internal_zzhy) {
        this.zzbxw = com_google_android_gms_internal_zzhy;
    }

    public void run() {
        if (zzhy.zza(this.zzbxw).get()) {
            zzkd.e("Timed out waiting for WebView to finish loading.");
            this.zzbxw.cancel();
        }
    }
}
