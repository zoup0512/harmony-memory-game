package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzu;
import java.util.concurrent.Callable;

class zzdc$1 implements Callable<Void> {
    final /* synthetic */ Context zzala;

    zzdc$1(Context context) {
        this.zzala = context;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzcx();
    }

    public Void zzcx() {
        zzu.zzfz().initialize(this.zzala);
        return null;
    }
}
