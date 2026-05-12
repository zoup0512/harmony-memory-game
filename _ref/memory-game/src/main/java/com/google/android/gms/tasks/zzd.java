package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult> {
    private OnFailureListener aDn;
    private final Executor avv;
    private final Object zzail = new Object();

    public zzd(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.avv = executor;
        this.aDn = onFailureListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDn = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzail) {
                if (this.aDn == null) {
                    return;
                }
                this.avv.execute(new Runnable(this) {
                    final /* synthetic */ zzd aDo;

                    public void run() {
                        synchronized (this.aDo.zzail) {
                            if (this.aDo.aDn != null) {
                                this.aDo.aDn.onFailure(task.getException());
                            }
                        }
                    }
                });
            }
        }
    }
}
