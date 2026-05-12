package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;

@zzin
public class zzkr {
    private Object zzail = new Object();
    private long zzcms;
    private long zzcmt = Long.MIN_VALUE;

    public zzkr(long j) {
        this.zzcms = j;
    }

    public boolean tryAcquire() {
        boolean z;
        synchronized (this.zzail) {
            long elapsedRealtime = zzu.zzfu().elapsedRealtime();
            if (this.zzcmt + this.zzcms > elapsedRealtime) {
                z = false;
            } else {
                this.zzcmt = elapsedRealtime;
                z = true;
            }
        }
        return z;
    }
}
