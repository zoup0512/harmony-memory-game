package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.zzu;
import java.util.concurrent.Callable;

class zzkg$3 implements Runnable {
    final /* synthetic */ zzkv zzckx;
    final /* synthetic */ Callable zzcky;

    zzkg$3(zzkv com_google_android_gms_internal_zzkv, Callable callable) {
        this.zzckx = com_google_android_gms_internal_zzkv;
        this.zzcky = callable;
    }

    public void run() {
        try {
            Process.setThreadPriority(10);
            this.zzckx.zzh(this.zzcky.call());
        } catch (Throwable e) {
            zzu.zzft().zzb(e, true);
            this.zzckx.cancel(true);
        }
    }
}
