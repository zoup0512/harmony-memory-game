package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private OnSuccessListener<? super TResult> aDp;
    private final Executor avv;
    private final Object zzail = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.avv = executor;
        this.aDp = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDp = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzail) {
                if (this.aDp == null) {
                    return;
                }
                this.avv.execute(new Runnable(this) {
                    final /* synthetic */ zze aDq;

                    public void run() {
                        synchronized (this.aDq.zzail) {
                            if (this.aDq.aDp != null) {
                                this.aDq.aDp.onSuccess(task.getResult());
                            }
                        }
                    }
                });
            }
        }
    }
}
