package com.google.android.gms.internal;

class zzko$1 implements Runnable {
    final /* synthetic */ zzko zzcmo;

    zzko$1(zzko com_google_android_gms_internal_zzko) {
        this.zzcmo = com_google_android_gms_internal_zzko;
    }

    public void run() {
        synchronized (zzko.zza(this.zzcmo)) {
            zzkd.v("Suspending the looper thread");
            while (zzko.zzb(this.zzcmo) == 0) {
                try {
                    zzko.zza(this.zzcmo).wait();
                    zzkd.v("Looper thread resumed");
                } catch (InterruptedException e) {
                    zzkd.v("Looper thread interrupted.");
                }
            }
        }
    }
}
