package com.google.android.gms.ads.internal;

import android.os.Debug;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzkd;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

class zza$1 extends TimerTask {
    final /* synthetic */ CountDownLatch zzajw;
    final /* synthetic */ Timer zzajx;
    final /* synthetic */ zza zzajy;

    zza$1(zza com_google_android_gms_ads_internal_zza, CountDownLatch countDownLatch, Timer timer) {
        this.zzajy = com_google_android_gms_ads_internal_zza;
        this.zzajw = countDownLatch;
        this.zzajx = timer;
    }

    public void run() {
        if (((long) ((Integer) zzdc.zzbcl.get()).intValue()) != this.zzajw.getCount()) {
            zzkd.zzcv("Stopping method tracing");
            Debug.stopMethodTracing();
            if (this.zzajw.getCount() == 0) {
                this.zzajx.cancel();
                return;
            }
        }
        String concat = String.valueOf(this.zzajy.zzajs.zzagf.getPackageName()).concat("_adsTrace_");
        try {
            zzkd.zzcv("Starting method tracing");
            this.zzajw.countDown();
            Debug.startMethodTracing(new StringBuilder(String.valueOf(concat).length() + 20).append(concat).append(zzu.zzfu().currentTimeMillis()).toString(), ((Integer) zzdc.zzbcm.get()).intValue());
        } catch (Throwable e) {
            zzkd.zzd("Exception occurred while starting method tracing.", e);
        }
    }
}
