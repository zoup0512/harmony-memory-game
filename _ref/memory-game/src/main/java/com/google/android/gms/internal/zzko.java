package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzab;

@zzin
public class zzko {
    private Handler mHandler = null;
    private final Object zzail = new Object();
    private HandlerThread zzcmm = null;
    private int zzcmn = 0;

    public Looper zztq() {
        Looper looper;
        synchronized (this.zzail) {
            if (this.zzcmn != 0) {
                zzab.zzb(this.zzcmm, "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzcmm == null) {
                zzkd.v("Starting the looper thread.");
                this.zzcmm = new HandlerThread("LooperProvider");
                this.zzcmm.start();
                this.mHandler = new Handler(this.zzcmm.getLooper());
                zzkd.v("Looper thread started.");
            } else {
                zzkd.v("Resuming the looper thread");
                this.zzail.notifyAll();
            }
            this.zzcmn++;
            looper = this.zzcmm.getLooper();
        }
        return looper;
    }

    public void zztr() {
        synchronized (this.zzail) {
            zzab.zzb(this.zzcmn > 0, "Invalid state: release() called more times than expected.");
            int i = this.zzcmn - 1;
            this.zzcmn = i;
            if (i == 0) {
                this.mHandler.post(new 1(this));
            }
        }
    }
}
