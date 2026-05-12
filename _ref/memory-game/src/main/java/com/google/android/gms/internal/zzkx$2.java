package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

class zzkx$2 implements Runnable {
    final /* synthetic */ AtomicInteger zzcnt;
    final /* synthetic */ int zzcnu;
    final /* synthetic */ zzkv zzcnv;
    final /* synthetic */ List zzcnw;

    zzkx$2(AtomicInteger atomicInteger, int i, zzkv com_google_android_gms_internal_zzkv, List list) {
        this.zzcnt = atomicInteger;
        this.zzcnu = i;
        this.zzcnv = com_google_android_gms_internal_zzkv;
        this.zzcnw = list;
    }

    public void run() {
        Throwable e;
        if (this.zzcnt.incrementAndGet() >= this.zzcnu) {
            try {
                this.zzcnv.zzh(zzkx.zzp(this.zzcnw));
                return;
            } catch (ExecutionException e2) {
                e = e2;
            } catch (InterruptedException e3) {
                e = e3;
            }
        } else {
            return;
        }
        zzkd.zzd("Unable to convert list of futures to a future of list", e);
    }
}
