package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private OnCompleteListener<TResult> aDl;
    private final Executor avv;
    private final Object zzail = new Object();

    public zzc(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.avv = executor;
        this.aDl = onCompleteListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDl = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzail) {
            if (this.aDl == null) {
                return;
            }
            this.avv.execute(new Runnable(this) {
                final /* synthetic */ zzc aDm;

                public void run() {
                    synchronized (this.aDm.zzail) {
                        if (this.aDm.aDl != null) {
                            this.aDm.aDl.onComplete(task);
                        }
                    }
                }
            });
        }
    }
}
