package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Continuation<TResult, Task<TContinuationResult>> aDg;
    private final zzh<TContinuationResult> aDh;
    private final Executor avv;

    public zzb(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzh<TContinuationResult> com_google_android_gms_tasks_zzh_TContinuationResult) {
        this.avv = executor;
        this.aDg = continuation;
        this.aDh = com_google_android_gms_tasks_zzh_TContinuationResult;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.avv.execute(new Runnable(this) {
            final /* synthetic */ zzb aDk;

            public void run() {
                try {
                    Task task = (Task) this.aDk.aDg.then(task);
                    if (task == null) {
                        this.aDk.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.aDu, this.aDk);
                    task.addOnFailureListener(TaskExecutors.aDu, this.aDk);
                } catch (Exception e) {
                    if (e.getCause() instanceof Exception) {
                        this.aDk.aDh.setException((Exception) e.getCause());
                    } else {
                        this.aDk.aDh.setException(e);
                    }
                } catch (Exception e2) {
                    this.aDk.aDh.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exception) {
        this.aDh.setException(exception);
    }

    public void onSuccess(TContinuationResult tContinuationResult) {
        this.aDh.setResult(tContinuationResult);
    }
}
