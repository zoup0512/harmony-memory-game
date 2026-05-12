package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

class zzkx$1 implements Runnable {
    final /* synthetic */ zzkv zzcnq;
    final /* synthetic */ zzkx$zza zzcnr;
    final /* synthetic */ zzky zzcns;

    zzkx$1(zzkv com_google_android_gms_internal_zzkv, zzkx$zza com_google_android_gms_internal_zzkx_zza, zzky com_google_android_gms_internal_zzky) {
        this.zzcnq = com_google_android_gms_internal_zzkv;
        this.zzcnr = com_google_android_gms_internal_zzkx_zza;
        this.zzcns = com_google_android_gms_internal_zzky;
    }

    public void run() {
        try {
            this.zzcnq.zzh(this.zzcnr.apply(this.zzcns.get()));
            return;
        } catch (CancellationException e) {
        } catch (InterruptedException e2) {
        } catch (ExecutionException e3) {
        }
        this.zzcnq.cancel(true);
    }
}
