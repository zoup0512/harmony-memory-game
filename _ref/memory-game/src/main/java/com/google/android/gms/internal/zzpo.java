package com.google.android.gms.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class zzpo<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> sS = new 1();
    private final Object sT;
    protected final zza<R> sU;
    protected final WeakReference<GoogleApiClient> sV;
    private final ArrayList<zza> sW;
    private ResultCallback<? super R> sX;
    private zzb sY;
    private volatile boolean sZ;
    private R sm;
    private boolean ta;
    private zzr tb;
    private volatile zzqx<R> tc;
    private boolean td;
    private boolean zzak;
    private final CountDownLatch zzale;

    @Deprecated
    zzpo() {
        this.sT = new Object();
        this.zzale = new CountDownLatch(1);
        this.sW = new ArrayList();
        this.td = false;
        this.sU = new zza(Looper.getMainLooper());
        this.sV = new WeakReference(null);
    }

    @Deprecated
    protected zzpo(Looper looper) {
        this.sT = new Object();
        this.zzale = new CountDownLatch(1);
        this.sW = new ArrayList();
        this.td = false;
        this.sU = new zza(looper);
        this.sV = new WeakReference(null);
    }

    protected zzpo(GoogleApiClient googleApiClient) {
        this.sT = new Object();
        this.zzale = new CountDownLatch(1);
        this.sW = new ArrayList();
        this.td = false;
        this.sU = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.sV = new WeakReference(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.sT) {
            if (this.sZ) {
                z = false;
            }
            zzab.zza(z, "Result has already been consumed.");
            zzab.zza(isReady(), "Result is not ready.");
            r = this.sm;
            this.sm = null;
            this.sX = null;
            this.sZ = true;
        }
        zzaos();
        return r;
    }

    private void zzd(R r) {
        this.sm = r;
        this.tb = null;
        this.zzale.countDown();
        Status status = this.sm.getStatus();
        if (this.zzak) {
            this.sX = null;
        } else if (this.sX != null) {
            this.sU.zzaoz();
            this.sU.zza(this.sX, get());
        } else if (this.sm instanceof Releasable) {
            this.sY = new zzb(this, null);
        }
        Iterator it = this.sW.iterator();
        while (it.hasNext()) {
            ((zza) it.next()).zzv(status);
        }
        this.sW.clear();
    }

    public static void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzab.zza(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
        zzab.zza(!this.sZ, "Result has already been consumed");
        if (this.tc != null) {
            z = false;
        }
        zzab.zza(z, "Cannot await if then() has been called.");
        try {
            this.zzale.await();
        } catch (InterruptedException e) {
            zzaa(Status.sr);
        }
        zzab.zza(isReady(), "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        boolean z2 = j <= 0 || Looper.myLooper() != Looper.getMainLooper();
        zzab.zza(z2, "await must not be called on the UI thread when time is greater than zero.");
        zzab.zza(!this.sZ, "Result has already been consumed.");
        if (this.tc != null) {
            z = false;
        }
        zzab.zza(z, "Cannot await if then() has been called.");
        try {
            if (!this.zzale.await(j, timeUnit)) {
                zzaa(Status.st);
            }
        } catch (InterruptedException e) {
            zzaa(Status.sr);
        }
        zzab.zza(isReady(), "Result is not ready.");
        return get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
        r2 = this;
        r1 = r2.sT;
        monitor-enter(r1);
        r0 = r2.zzak;	 Catch:{ all -> 0x0029 }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.sZ;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.tb;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r2.tb;	 Catch:{ RemoteException -> 0x002c }
        r0.cancel();	 Catch:{ RemoteException -> 0x002c }
    L_0x0016:
        r0 = r2.sm;	 Catch:{ all -> 0x0029 }
        zze(r0);	 Catch:{ all -> 0x0029 }
        r0 = 1;
        r2.zzak = r0;	 Catch:{ all -> 0x0029 }
        r0 = com.google.android.gms.common.api.Status.su;	 Catch:{ all -> 0x0029 }
        r0 = r2.zzc(r0);	 Catch:{ all -> 0x0029 }
        r2.zzd(r0);	 Catch:{ all -> 0x0029 }
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        goto L_0x000c;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpo.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.sT) {
            z = this.zzak;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzale.getCount() == 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r3 = r5.sT;
        monitor-enter(r3);
        if (r6 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r5.sX = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r5.sZ;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzab.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r5.tc;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzab.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r5.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r5.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r5.sU;	 Catch:{ all -> 0x0027 }
        r1 = r5.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r5.sX = r6;	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpo.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r3 = r6.sT;
        monitor-enter(r3);
        if (r7 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r6.sX = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r6.sZ;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzab.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r6.tc;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzab.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r6.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r6.sU;	 Catch:{ all -> 0x0027 }
        r1 = r6.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r7, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r6.sX = r7;	 Catch:{ all -> 0x0027 }
        r0 = r6.sU;	 Catch:{ all -> 0x0027 }
        r4 = r10.toMillis(r8);	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r4);	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpo.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzab.zza(!this.sZ, "Result has already been consumed.");
        synchronized (this.sT) {
            zzab.zza(this.tc == null, "Cannot call then() twice.");
            if (this.sX != null) {
                z = false;
            }
            zzab.zza(z, "Cannot call then() if callbacks are set.");
            this.td = true;
            this.tc = new zzqx(this.sV);
            then = this.tc.then(resultTransform);
            if (isReady()) {
                this.sU.zza(this.tc, get());
            } else {
                this.sX = this.tc;
            }
        }
        return then;
    }

    public final void zza(zza com_google_android_gms_common_api_PendingResult_zza) {
        boolean z = true;
        zzab.zza(!this.sZ, "Result has already been consumed.");
        if (com_google_android_gms_common_api_PendingResult_zza == null) {
            z = false;
        }
        zzab.zzb(z, "Callback cannot be null.");
        synchronized (this.sT) {
            if (isReady()) {
                com_google_android_gms_common_api_PendingResult_zza.zzv(this.sm.getStatus());
            } else {
                this.sW.add(com_google_android_gms_common_api_PendingResult_zza);
            }
        }
    }

    protected final void zza(zzr com_google_android_gms_common_internal_zzr) {
        synchronized (this.sT) {
            this.tb = com_google_android_gms_common_internal_zzr;
        }
    }

    public final void zzaa(Status status) {
        synchronized (this.sT) {
            if (!isReady()) {
                zzc(zzc(status));
                this.ta = true;
            }
        }
    }

    public Integer zzaoj() {
        return null;
    }

    protected void zzaos() {
    }

    public boolean zzaov() {
        boolean isCanceled;
        synchronized (this.sT) {
            if (((GoogleApiClient) this.sV.get()) == null || !this.td) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzaow() {
        boolean z = this.td || ((Boolean) sS.get()).booleanValue();
        this.td = z;
    }

    boolean zzaox() {
        return false;
    }

    protected abstract R zzc(Status status);

    public final void zzc(R r) {
        boolean z = true;
        synchronized (this.sT) {
            if (this.ta || this.zzak || (isReady() && zzaox())) {
                zze(r);
                return;
            }
            zzab.zza(!isReady(), "Results have already been set");
            if (this.sZ) {
                z = false;
            }
            zzab.zza(z, "Result has already been consumed");
            zzd(r);
        }
    }
}
