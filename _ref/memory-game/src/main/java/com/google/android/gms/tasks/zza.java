package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Continuation<TResult, TContinuationResult> aDg;
    private final zzh<TContinuationResult> aDh;
    private final Executor avv;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzh<TContinuationResult> com_google_android_gms_tasks_zzh_TContinuationResult) {
        this.avv = executor;
        this.aDg = continuation;
        this.aDh = com_google_android_gms_tasks_zzh_TContinuationResult;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.avv.execute(new Runnable(this) {
            final /* synthetic */ zza aDj;

            public void run() {
                try {
                    this.aDj.aDh.setResult(this.aDj.aDg.then(task));
                } catch (Exception e) {
                    if (e.getCause() instanceof Exception) {
                        this.aDj.aDh.setException((Exception) e.getCause());
                    } else {
                        this.aDj.aDh.setException(e);
                    }
                } catch (Exception e2) {
                    this.aDj.aDh.setException(e2);
                }
            }
        });
    }
}
