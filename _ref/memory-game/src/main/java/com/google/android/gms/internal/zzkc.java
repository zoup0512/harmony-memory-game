package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzin
public abstract class zzkc implements zzkj<Future> {
    private volatile Thread zzckk;
    private boolean zzckl;
    private final Runnable zzw;

    public zzkc() {
        this.zzw = new 1(this);
        this.zzckl = false;
    }

    public zzkc(boolean z) {
        this.zzw = new 1(this);
        this.zzckl = z;
    }

    public final void cancel() {
        onStop();
        if (this.zzckk != null) {
            this.zzckk.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzew();

    public /* synthetic */ Object zzpy() {
        return zzsz();
    }

    public final Future zzsz() {
        return this.zzckl ? zzkg.zza(1, this.zzw) : zzkg.zza(this.zzw);
    }
}
