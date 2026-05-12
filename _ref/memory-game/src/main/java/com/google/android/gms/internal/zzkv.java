package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzin
public class zzkv<T> implements zzky<T> {
    private final Object zzail = new Object();
    private boolean zzbox;
    private T zzcnn;
    private boolean zzcno;
    private final zzkz zzcnp = new zzkz();

    private boolean zzty() {
        return null != null || this.zzcno;
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.zzail) {
            if (zzty()) {
                return false;
            }
            this.zzbox = true;
            this.zzcno = true;
            this.zzail.notifyAll();
            this.zzcnp.zztz();
            return true;
        }
    }

    public T get() throws CancellationException, ExecutionException, InterruptedException {
        T t;
        synchronized (this.zzail) {
            if (!zzty()) {
                try {
                    this.zzail.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzbox) {
                throw new CancellationException("CallbackFuture was cancelled.");
            }
            t = this.zzcnn;
        }
        return t;
    }

    public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        T t;
        synchronized (this.zzail) {
            if (!zzty()) {
                try {
                    long toMillis = timeUnit.toMillis(j);
                    if (toMillis != 0) {
                        this.zzail.wait(toMillis);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (!this.zzcno) {
                throw new TimeoutException("CallbackFuture timed out.");
            } else if (this.zzbox) {
                throw new CancellationException("CallbackFuture was cancelled.");
            } else {
                t = this.zzcnn;
            }
        }
        return t;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzbox;
        }
        return z;
    }

    public boolean isDone() {
        boolean zzty;
        synchronized (this.zzail) {
            zzty = zzty();
        }
        return zzty;
    }

    public void zzc(Runnable runnable) {
        this.zzcnp.zzc(runnable);
    }

    public void zzd(Runnable runnable) {
        this.zzcnp.zzd(runnable);
    }

    public void zzh(T t) {
        synchronized (this.zzail) {
            if (this.zzbox) {
            } else if (zzty()) {
                zzu.zzft().zzb(new IllegalStateException("Provided CallbackFuture with multiple values."), true);
            } else {
                this.zzcno = true;
                this.zzcnn = t;
                this.zzail.notifyAll();
                this.zzcnp.zztz();
            }
        }
    }
}
