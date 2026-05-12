package com.google.android.gms.ads.internal;

import android.os.Handler;

public class zzr$zza {
    private final Handler mHandler;

    public zzr$zza(Handler handler) {
        this.mHandler = handler;
    }

    public boolean postDelayed(Runnable runnable, long j) {
        return this.mHandler.postDelayed(runnable, j);
    }

    public void removeCallbacks(Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }
}
