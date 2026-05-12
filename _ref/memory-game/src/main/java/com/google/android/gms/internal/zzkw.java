package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

@zzin
public class zzkw<T> implements zzky<T> {
    private final T zzcnn;
    private final zzkz zzcnp = new zzkz();

    public zzkw(T t) {
        this.zzcnn = t;
        this.zzcnp.zztz();
    }

    public boolean cancel(boolean z) {
        return false;
    }

    public T get() {
        return this.zzcnn;
    }

    public T get(long j, TimeUnit timeUnit) {
        return this.zzcnn;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public void zzc(Runnable runnable) {
        this.zzcnp.zzc(runnable);
    }
}
