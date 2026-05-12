package com.google.android.gms.internal;

import java.util.concurrent.Callable;

class zzkg$1 implements Callable<Void> {
    final /* synthetic */ Runnable zzckw;

    zzkg$1(Runnable runnable) {
        this.zzckw = runnable;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzcx();
    }

    public Void zzcx() {
        this.zzckw.run();
        return null;
    }
}
