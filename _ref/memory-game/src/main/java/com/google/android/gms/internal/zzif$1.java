package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzn;
import java.util.concurrent.CountDownLatch;

class zzif$1 implements Runnable {
    final /* synthetic */ CountDownLatch zzajw;
    final /* synthetic */ zzif zzbyp;

    zzif$1(zzif com_google_android_gms_internal_zzif, CountDownLatch countDownLatch) {
        this.zzbyp = com_google_android_gms_internal_zzif;
        this.zzajw = countDownLatch;
    }

    public void run() {
        synchronized (this.zzbyp.zzbxu) {
            zzif.zza(this.zzbyp, zzn.zza(zzif.zza(this.zzbyp), this.zzbyp.zzbyn, this.zzajw));
        }
    }
}
