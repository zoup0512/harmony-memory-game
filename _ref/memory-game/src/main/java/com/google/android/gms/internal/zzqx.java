package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzab;
import java.lang.ref.WeakReference;

public class zzqx<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object sT = new Object();
    private final WeakReference<GoogleApiClient> sV;
    private Status vA = null;
    private final zza vB;
    private boolean vC = false;
    private ResultTransform<? super R, ? extends Result> vw = null;
    private zzqx<? extends Result> vx = null;
    private volatile ResultCallbacks<? super R> vy = null;
    private PendingResult<R> vz = null;

    private final class zza extends Handler {
        final /* synthetic */ zzqx vE;

        public zza(zzqx com_google_android_gms_internal_zzqx, Looper looper) {
            this.vE = com_google_android_gms_internal_zzqx;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    PendingResult pendingResult = (PendingResult) message.obj;
                    synchronized (this.vE.sT) {
                        if (pendingResult == null) {
                            this.vE.vx.zzac(new Status(13, "Transform returned null"));
                        } else if (pendingResult instanceof zzqs) {
                            this.vE.vx.zzac(((zzqs) pendingResult).getStatus());
                        } else {
                            this.vE.vx.zza(pendingResult);
                        }
                    }
                    return;
                case 1:
                    RuntimeException runtimeException = (RuntimeException) message.obj;
                    String str = "TransformedResultImpl";
                    String str2 = "Runtime exception on the transformation worker thread: ";
                    String valueOf = String.valueOf(runtimeException.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    throw runtimeException;
                default:
                    Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + message.what);
                    return;
            }
        }
    }

    public zzqx(WeakReference<GoogleApiClient> weakReference) {
        zzab.zzb((Object) weakReference, (Object) "GoogleApiClient reference must not be null");
        this.sV = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.sV.get();
        this.vB = new zza(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private void zzac(Status status) {
        synchronized (this.sT) {
            this.vA = status;
            zzad(this.vA);
        }
    }

    private void zzad(Status status) {
        synchronized (this.sT) {
            if (this.vw != null) {
                Object onFailure = this.vw.onFailure(status);
                zzab.zzb(onFailure, (Object) "onFailure must not return null");
                this.vx.zzac(onFailure);
            } else if (zzaqy()) {
                this.vy.onFailure(status);
            }
        }
    }

    private void zzaqw() {
        if (this.vw != null || this.vy != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.sV.get();
            if (!(this.vC || this.vw == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.vC = true;
            }
            if (this.vA != null) {
                zzad(this.vA);
            } else if (this.vz != null) {
                this.vz.setResultCallback(this);
            }
        }
    }

    private boolean zzaqy() {
        return (this.vy == null || ((GoogleApiClient) this.sV.get()) == null) ? false : true;
    }

    private void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.sT) {
            zzab.zza(this.vy == null, (Object) "Cannot call andFinally() twice.");
            if (this.vw != null) {
                z = false;
            }
            zzab.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.vy = resultCallbacks;
            zzaqw();
        }
    }

    public void onResult(final R r) {
        synchronized (this.sT) {
            if (!r.getStatus().isSuccess()) {
                zzac(r.getStatus());
                zze((Result) r);
            } else if (this.vw != null) {
                zzqr.zzaqc().submit(new Runnable(this) {
                    final /* synthetic */ zzqx vE;

                    @WorkerThread
                    public void run() {
                        GoogleApiClient googleApiClient;
                        try {
                            zzpo.sS.set(Boolean.valueOf(true));
                            this.vE.vB.sendMessage(this.vE.vB.obtainMessage(0, this.vE.vw.onSuccess(r)));
                            zzpo.sS.set(Boolean.valueOf(false));
                            this.vE.zze(r);
                            googleApiClient = (GoogleApiClient) this.vE.sV.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(this.vE);
                            }
                        } catch (RuntimeException e) {
                            this.vE.vB.sendMessage(this.vE.vB.obtainMessage(1, e));
                            zzpo.sS.set(Boolean.valueOf(false));
                            this.vE.zze(r);
                            googleApiClient = (GoogleApiClient) this.vE.sV.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(this.vE);
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            zzpo.sS.set(Boolean.valueOf(false));
                            this.vE.zze(r);
                            googleApiClient = (GoogleApiClient) this.vE.sV.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(this.vE);
                            }
                        }
                    }
                });
            } else if (zzaqy()) {
                this.vy.onSuccess(r);
            }
        }
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult com_google_android_gms_internal_zzqx;
        boolean z = true;
        synchronized (this.sT) {
            zzab.zza(this.vw == null, (Object) "Cannot call then() twice.");
            if (this.vy != null) {
                z = false;
            }
            zzab.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.vw = resultTransform;
            com_google_android_gms_internal_zzqx = new zzqx(this.sV);
            this.vx = com_google_android_gms_internal_zzqx;
            zzaqw();
        }
        return com_google_android_gms_internal_zzqx;
    }

    public void zza(PendingResult<?> pendingResult) {
        synchronized (this.sT) {
            this.vz = pendingResult;
            zzaqw();
        }
    }

    void zzaqx() {
        this.vy = null;
    }
}
