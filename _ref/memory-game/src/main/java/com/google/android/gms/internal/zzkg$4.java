package com.google.android.gms.internal;

import java.util.concurrent.Future;

class zzkg$4 implements Runnable {
    final /* synthetic */ zzkv zzckx;
    final /* synthetic */ Future zzckz;

    zzkg$4(zzkv com_google_android_gms_internal_zzkv, Future future) {
        this.zzckx = com_google_android_gms_internal_zzkv;
        this.zzckz = future;
    }

    public void run() {
        if (this.zzckx.isCancelled()) {
            this.zzckz.cancel(true);
        }
    }
}
