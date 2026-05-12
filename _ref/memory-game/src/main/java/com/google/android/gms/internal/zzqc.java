package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzh;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.internal.zzd.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class zzqc implements Callback {
    private static zzqc uG;
    private static final Object zzamr = new Object();
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability sh;
    private long uF;
    private int uH;
    private final AtomicInteger uI;
    private final SparseArray<zzc<?>> uJ;
    private final Map<zzpj<?>, zzc<?>> uK;
    private zzpr uL;
    private final Set<zzpj<?>> uM;
    private final ReferenceQueue<com.google.android.gms.common.api.zzc<?>> uN;
    private final SparseArray<zza> uO;
    private zzb uP;
    private long ue;
    private long uf;

    private final class zza extends PhantomReference<com.google.android.gms.common.api.zzc<?>> {
        private final int sx;
        final /* synthetic */ zzqc uQ;

        public zza(zzqc com_google_android_gms_internal_zzqc, com.google.android.gms.common.api.zzc com_google_android_gms_common_api_zzc, int i, ReferenceQueue<com.google.android.gms.common.api.zzc<?>> referenceQueue) {
            this.uQ = com_google_android_gms_internal_zzqc;
            super(com_google_android_gms_common_api_zzc, referenceQueue);
            this.sx = i;
        }

        public void zzaqg() {
            this.uQ.mHandler.sendMessage(this.uQ.mHandler.obtainMessage(2, this.sx, 2));
        }
    }

    private static final class zzb extends Thread {
        private final ReferenceQueue<com.google.android.gms.common.api.zzc<?>> uN;
        private final SparseArray<zza> uO;
        private final AtomicBoolean uR = new AtomicBoolean();

        public zzb(ReferenceQueue<com.google.android.gms.common.api.zzc<?>> referenceQueue, SparseArray<zza> sparseArray) {
            super("GoogleApiCleanup");
            this.uN = referenceQueue;
            this.uO = sparseArray;
        }

        public void run() {
            this.uR.set(true);
            Process.setThreadPriority(10);
            while (this.uR.get()) {
                try {
                    zza com_google_android_gms_internal_zzqc_zza = (zza) this.uN.remove();
                    this.uO.remove(com_google_android_gms_internal_zzqc_zza.sx);
                    com_google_android_gms_internal_zzqc_zza.zzaqg();
                } catch (InterruptedException e) {
                } finally {
                    this.uR.set(false);
                }
            }
        }
    }

    private class zzc<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener {
        private final zzpj<O> rQ;
        final /* synthetic */ zzqc uQ;
        private final Queue<zzpi> uS = new LinkedList();
        private final zze uT;
        private final com.google.android.gms.common.api.Api.zzb uU;
        private final SparseArray<zzqy> uV = new SparseArray();
        private final Set<zzpl> uW = new HashSet();
        private final SparseArray<Map<Object, com.google.android.gms.internal.zzpm.zza>> uX = new SparseArray();
        private ConnectionResult uY = null;
        private boolean ud;

        @WorkerThread
        public zzc(zzqc com_google_android_gms_internal_zzqc, com.google.android.gms.common.api.zzc<O> com_google_android_gms_common_api_zzc_O) {
            this.uQ = com_google_android_gms_internal_zzqc;
            this.uT = zzb((com.google.android.gms.common.api.zzc) com_google_android_gms_common_api_zzc_O);
            if (this.uT instanceof zzah) {
                this.uU = ((zzah) this.uT).zzatn();
            } else {
                this.uU = this.uT;
            }
            this.rQ = com_google_android_gms_common_api_zzc_O.zzaob();
        }

        @WorkerThread
        private void connect() {
            if (!this.uT.isConnected() && !this.uT.isConnecting()) {
                if (this.uT.zzanu() && this.uQ.uH != 0) {
                    this.uQ.uH = this.uQ.sh.isGooglePlayServicesAvailable(this.uQ.mContext);
                    if (this.uQ.uH != 0) {
                        onConnectionFailed(new ConnectionResult(this.uQ.uH, null));
                        return;
                    }
                }
                this.uT.zza(new zzd(this.uQ, this.uT, this.rQ));
            }
        }

        @WorkerThread
        private void resume() {
            if (this.ud) {
                connect();
            }
        }

        @WorkerThread
        private void zzab(Status status) {
            for (zzpi zzx : this.uS) {
                zzx.zzx(status);
            }
            this.uS.clear();
        }

        @WorkerThread
        private void zzapu() {
            if (this.ud) {
                zzaqk();
                zzab(this.uQ.sh.isGooglePlayServicesAvailable(this.uQ.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.uT.disconnect();
            }
        }

        @WorkerThread
        private void zzaqk() {
            if (this.ud) {
                this.uQ.mHandler.removeMessages(9, this.rQ);
                this.uQ.mHandler.removeMessages(8, this.rQ);
                this.ud = false;
            }
        }

        private void zzaql() {
            this.uQ.mHandler.removeMessages(10, this.rQ);
            this.uQ.mHandler.sendMessageDelayed(this.uQ.mHandler.obtainMessage(10, this.rQ), this.uQ.uF);
        }

        private void zzaqm() {
            if (this.uT.isConnected() && this.uX.size() == 0) {
                for (int i = 0; i < this.uV.size(); i++) {
                    if (((zzqy) this.uV.get(this.uV.keyAt(i))).zzara()) {
                        zzaql();
                        return;
                    }
                }
                this.uT.disconnect();
            }
        }

        @WorkerThread
        private zze zzb(com.google.android.gms.common.api.zzc com_google_android_gms_common_api_zzc) {
            Api zzanz = com_google_android_gms_common_api_zzc.zzanz();
            if (!zzanz.zzant()) {
                return com_google_android_gms_common_api_zzc.zzanz().zzanq().zza(com_google_android_gms_common_api_zzc.getApplicationContext(), this.uQ.mHandler.getLooper(), zzg.zzcd(com_google_android_gms_common_api_zzc.getApplicationContext()), com_google_android_gms_common_api_zzc.zzaoa(), this, this);
            }
            zzh zzanr = zzanz.zzanr();
            return new zzah(com_google_android_gms_common_api_zzc.getApplicationContext(), this.uQ.mHandler.getLooper(), zzanr.zzanw(), this, this, zzg.zzcd(com_google_android_gms_common_api_zzc.getApplicationContext()), zzanr.zzr(com_google_android_gms_common_api_zzc.zzaoa()));
        }

        @WorkerThread
        private void zzc(zzpi com_google_android_gms_internal_zzpi) {
            com_google_android_gms_internal_zzpi.zza(this.uV);
            Map map;
            if (com_google_android_gms_internal_zzpi.iq == 3) {
                try {
                    Map map2;
                    map = (Map) this.uX.get(com_google_android_gms_internal_zzpi.sx);
                    if (map == null) {
                        ArrayMap arrayMap = new ArrayMap(1);
                        this.uX.put(com_google_android_gms_internal_zzpi.sx, arrayMap);
                        map2 = arrayMap;
                    } else {
                        map2 = map;
                    }
                    com.google.android.gms.internal.zzpm.zza com_google_android_gms_internal_zzpm_zza = ((com.google.android.gms.internal.zzpi.zza) com_google_android_gms_internal_zzpi).sy;
                    map2.put(((zzqm) com_google_android_gms_internal_zzpm_zza).zzaqu(), com_google_android_gms_internal_zzpm_zza);
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Listener registration methods must implement ListenerApiMethod");
                }
            } else if (com_google_android_gms_internal_zzpi.iq == 4) {
                try {
                    map = (Map) this.uX.get(com_google_android_gms_internal_zzpi.sx);
                    zzqm com_google_android_gms_internal_zzqm = (zzqm) ((com.google.android.gms.internal.zzpi.zza) com_google_android_gms_internal_zzpi).sy;
                    if (map != null) {
                        map.remove(com_google_android_gms_internal_zzqm.zzaqu());
                    } else {
                        Log.w("GoogleApiManager", "Received call to unregister a listener without a matching registration call.");
                    }
                } catch (ClassCastException e2) {
                    throw new IllegalStateException("Listener unregistration methods must implement ListenerApiMethod");
                }
            }
            try {
                com_google_android_gms_internal_zzpi.zzb(this.uU);
            } catch (DeadObjectException e3) {
                this.uT.disconnect();
                onConnectionSuspended(1);
            }
        }

        @WorkerThread
        private void zzj(ConnectionResult connectionResult) {
            for (zzpl zza : this.uW) {
                zza.zza(this.rQ, connectionResult);
            }
            this.uW.clear();
        }

        boolean isConnected() {
            return this.uT.isConnected();
        }

        @WorkerThread
        public void onConnected(@Nullable Bundle bundle) {
            zzaqi();
            zzj(ConnectionResult.rb);
            zzaqk();
            for (int i = 0; i < this.uX.size(); i++) {
                for (com.google.android.gms.internal.zzpm.zza zzb : ((Map) this.uX.get(this.uX.keyAt(i))).values()) {
                    try {
                        zzb.zzb(this.uU);
                    } catch (DeadObjectException e) {
                        this.uT.disconnect();
                        onConnectionSuspended(1);
                    }
                }
            }
            zzaqh();
            zzaql();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        @android.support.annotation.WorkerThread
        public void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
            /*
            r5 = this;
            r5.zzaqi();
            r0 = r5.uQ;
            r1 = -1;
            r0.uH = r1;
            r5.zzj(r6);
            r0 = r5.uV;
            r1 = 0;
            r0 = r0.keyAt(r1);
            r1 = r5.uS;
            r1 = r1.isEmpty();
            if (r1 == 0) goto L_0x001e;
        L_0x001b:
            r5.uY = r6;
        L_0x001d:
            return;
        L_0x001e:
            r1 = com.google.android.gms.internal.zzqc.zzamr;
            monitor-enter(r1);
            r2 = r5.uQ;	 Catch:{ all -> 0x0044 }
            r2 = null;	 Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047;
        L_0x002b:
            r2 = r5.uQ;	 Catch:{ all -> 0x0044 }
            r2 = r2.uM;	 Catch:{ all -> 0x0044 }
            r3 = r5.rQ;	 Catch:{ all -> 0x0044 }
            r2 = r2.contains(r3);	 Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047;
        L_0x0039:
            r2 = r5.uQ;	 Catch:{ all -> 0x0044 }
            r2 = null;	 Catch:{ all -> 0x0044 }
            r2.zzb(r6, r0);	 Catch:{ all -> 0x0044 }
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            goto L_0x001d;
        L_0x0044:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            throw r0;
        L_0x0047:
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            r1 = r5.uQ;
            r0 = r1.zzc(r6, r0);
            if (r0 != 0) goto L_0x001d;
        L_0x0050:
            r0 = r6.getErrorCode();
            r1 = 18;
            if (r0 != r1) goto L_0x005b;
        L_0x0058:
            r0 = 1;
            r5.ud = r0;
        L_0x005b:
            r0 = r5.ud;
            if (r0 == 0) goto L_0x007d;
        L_0x005f:
            r0 = r5.uQ;
            r0 = r0.mHandler;
            r1 = r5.uQ;
            r1 = r1.mHandler;
            r2 = 8;
            r3 = r5.rQ;
            r1 = android.os.Message.obtain(r1, r2, r3);
            r2 = r5.uQ;
            r2 = r2.uf;
            r0.sendMessageDelayed(r1, r2);
            goto L_0x001d;
        L_0x007d:
            r0 = new com.google.android.gms.common.api.Status;
            r1 = 17;
            r2 = r5.rQ;
            r2 = r2.zzaon();
            r2 = java.lang.String.valueOf(r2);
            r3 = new java.lang.StringBuilder;
            r4 = java.lang.String.valueOf(r2);
            r4 = r4.length();
            r4 = r4 + 38;
            r3.<init>(r4);
            r4 = "API: ";
            r3 = r3.append(r4);
            r2 = r3.append(r2);
            r3 = " is not available on this device.";
            r2 = r2.append(r3);
            r2 = r2.toString();
            r0.<init>(r1, r2);
            r5.zzab(r0);
            goto L_0x001d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqc.zzc.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
        }

        @WorkerThread
        public void onConnectionSuspended(int i) {
            zzaqi();
            this.ud = true;
            this.uQ.mHandler.sendMessageDelayed(Message.obtain(this.uQ.mHandler, 8, this.rQ), this.uQ.uf);
            this.uQ.mHandler.sendMessageDelayed(Message.obtain(this.uQ.mHandler, 9, this.rQ), this.uQ.ue);
            this.uQ.uH = -1;
        }

        @WorkerThread
        public void zzaqh() {
            while (this.uT.isConnected() && !this.uS.isEmpty()) {
                zzc((zzpi) this.uS.remove());
            }
        }

        @WorkerThread
        public void zzaqi() {
            this.uY = null;
        }

        ConnectionResult zzaqj() {
            return this.uY;
        }

        @WorkerThread
        public void zzb(zzpi com_google_android_gms_internal_zzpi) {
            if (this.uT.isConnected()) {
                zzc(com_google_android_gms_internal_zzpi);
                zzaql();
                return;
            }
            this.uS.add(com_google_android_gms_internal_zzpi);
            if (this.uY == null || !this.uY.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.uY);
            }
        }

        @WorkerThread
        public void zzb(zzpl com_google_android_gms_internal_zzpl) {
            this.uW.add(com_google_android_gms_internal_zzpl);
        }

        @WorkerThread
        public void zzf(int i, boolean z) {
            Iterator it = this.uS.iterator();
            while (it.hasNext()) {
                zzpi com_google_android_gms_internal_zzpi = (zzpi) it.next();
                if (com_google_android_gms_internal_zzpi.sx == i && com_google_android_gms_internal_zzpi.iq != 1 && com_google_android_gms_internal_zzpi.cancel()) {
                    it.remove();
                }
            }
            ((zzqy) this.uV.get(i)).release();
            this.uX.delete(i);
            if (!z) {
                this.uV.remove(i);
                this.uQ.uO.remove(i);
                if (this.uV.size() == 0 && this.uS.isEmpty()) {
                    zzaqk();
                    this.uT.disconnect();
                    this.uQ.uK.remove(this.rQ);
                    synchronized (zzqc.zzamr) {
                        this.uQ.uM.remove(this.rQ);
                    }
                }
            }
        }

        @WorkerThread
        public void zzfn(int i) {
            this.uV.put(i, new zzqy(this.rQ.zzans(), this.uT));
        }

        @WorkerThread
        public void zzfo(final int i) {
            ((zzqy) this.uV.get(i)).zza(new zzc(this) {
                final /* synthetic */ zzc va;

                public void zzaqn() {
                    if (this.va.uS.isEmpty()) {
                        this.va.zzf(i, false);
                    }
                }
            });
        }
    }

    private class zzd implements zzf {
        private final zzpj<?> rQ;
        final /* synthetic */ zzqc uQ;
        private final zze uT;

        public zzd(zzqc com_google_android_gms_internal_zzqc, zze com_google_android_gms_common_api_Api_zze, zzpj<?> com_google_android_gms_internal_zzpj_) {
            this.uQ = com_google_android_gms_internal_zzqc;
            this.uT = com_google_android_gms_common_api_Api_zze;
            this.rQ = com_google_android_gms_internal_zzpj_;
        }

        @WorkerThread
        public void zzh(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.uT.zza(null, Collections.emptySet());
            } else {
                ((zzc) this.uQ.uK.get(this.rQ)).onConnectionFailed(connectionResult);
            }
        }
    }

    private zzqc(Context context) {
        this(context, GoogleApiAvailability.getInstance());
    }

    private zzqc(Context context, GoogleApiAvailability googleApiAvailability) {
        this.uf = 5000;
        this.ue = 120000;
        this.uF = 10000;
        this.uH = -1;
        this.uI = new AtomicInteger(1);
        this.uJ = new SparseArray();
        this.uK = new ConcurrentHashMap(5, 0.75f, 1);
        this.uL = null;
        this.uM = new com.google.android.gms.common.util.zza();
        this.uN = new ReferenceQueue();
        this.uO = new SparseArray();
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        this.sh = googleApiAvailability;
    }

    private int zza(com.google.android.gms.common.api.zzc<?> com_google_android_gms_common_api_zzc_) {
        int andIncrement = this.uI.getAndIncrement();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, andIncrement, 0, com_google_android_gms_common_api_zzc_));
        return andIncrement;
    }

    public static Pair<zzqc, Integer> zza(Context context, com.google.android.gms.common.api.zzc<?> com_google_android_gms_common_api_zzc_) {
        Pair<zzqc, Integer> create;
        synchronized (zzamr) {
            if (uG == null) {
                uG = new zzqc(context.getApplicationContext());
            }
            create = Pair.create(uG, Integer.valueOf(uG.zza((com.google.android.gms.common.api.zzc) com_google_android_gms_common_api_zzc_)));
        }
        return create;
    }

    @WorkerThread
    private void zza(com.google.android.gms.common.api.zzc<?> com_google_android_gms_common_api_zzc_, int i) {
        zzpj zzaob = com_google_android_gms_common_api_zzc_.zzaob();
        if (!this.uK.containsKey(zzaob)) {
            this.uK.put(zzaob, new zzc(this, com_google_android_gms_common_api_zzc_));
        }
        zzc com_google_android_gms_internal_zzqc_zzc = (zzc) this.uK.get(zzaob);
        com_google_android_gms_internal_zzqc_zzc.zzfn(i);
        this.uJ.put(i, com_google_android_gms_internal_zzqc_zzc);
        com_google_android_gms_internal_zzqc_zzc.connect();
        this.uO.put(i, new zza(this, com_google_android_gms_common_api_zzc_, i, this.uN));
        if (this.uP == null || !this.uP.uR.get()) {
            this.uP = new zzb(this.uN, this.uO);
            this.uP.start();
        }
    }

    @WorkerThread
    private void zza(zzpi com_google_android_gms_internal_zzpi) {
        ((zzc) this.uJ.get(com_google_android_gms_internal_zzpi.sx)).zzb(com_google_android_gms_internal_zzpi);
    }

    public static zzqc zzaqd() {
        zzqc com_google_android_gms_internal_zzqc;
        synchronized (zzamr) {
            com_google_android_gms_internal_zzqc = uG;
        }
        return com_google_android_gms_internal_zzqc;
    }

    @WorkerThread
    private void zzaqe() {
        for (zzc com_google_android_gms_internal_zzqc_zzc : this.uK.values()) {
            com_google_android_gms_internal_zzqc_zzc.zzaqi();
            com_google_android_gms_internal_zzqc_zzc.connect();
        }
    }

    @WorkerThread
    private void zze(int i, boolean z) {
        zzc com_google_android_gms_internal_zzqc_zzc = (zzc) this.uJ.get(i);
        if (com_google_android_gms_internal_zzqc_zzc != null) {
            if (!z) {
                this.uJ.delete(i);
            }
            com_google_android_gms_internal_zzqc_zzc.zzf(i, z);
            return;
        }
        Log.wtf("GoogleApiManager", "onRelease received for unknown instance: " + i, new Exception());
    }

    @WorkerThread
    private void zzfm(int i) {
        zzc com_google_android_gms_internal_zzqc_zzc = (zzc) this.uJ.get(i);
        if (com_google_android_gms_internal_zzqc_zzc != null) {
            this.uJ.delete(i);
            com_google_android_gms_internal_zzqc_zzc.zzfo(i);
            return;
        }
        Log.wtf("GoogleApiManager", "onCleanupLeakInternal received for unknown instance: " + i, new Exception());
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        boolean z = false;
        switch (message.what) {
            case 1:
                zza((zzpl) message.obj);
                break;
            case 2:
                zzfm(message.arg1);
                break;
            case 3:
                zzaqe();
                break;
            case 4:
                zza((zzpi) message.obj);
                break;
            case 5:
                if (this.uJ.get(message.arg1) != null) {
                    ((zzc) this.uJ.get(message.arg1)).zzab(new Status(17, "Error resolution was canceled by the user."));
                    break;
                }
                break;
            case 6:
                zza((com.google.android.gms.common.api.zzc) message.obj, message.arg1);
                break;
            case 7:
                int i = message.arg1;
                if (message.arg2 == 1) {
                    z = true;
                }
                zze(i, z);
                break;
            case 8:
                if (this.uK.containsKey(message.obj)) {
                    ((zzc) this.uK.get(message.obj)).resume();
                    break;
                }
                break;
            case 9:
                if (this.uK.containsKey(message.obj)) {
                    ((zzc) this.uK.get(message.obj)).zzapu();
                    break;
                }
                break;
            case 10:
                if (this.uK.containsKey(message.obj)) {
                    ((zzc) this.uK.get(message.obj)).zzaqm();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0));
        }
    }

    public <O extends ApiOptions> void zza(com.google.android.gms.common.api.zzc<O> com_google_android_gms_common_api_zzc_O, int i, com.google.android.gms.internal.zzpm.zza<? extends Result, com.google.android.gms.common.api.Api.zzb> com_google_android_gms_internal_zzpm_zza__extends_com_google_android_gms_common_api_Result__com_google_android_gms_common_api_Api_zzb) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new com.google.android.gms.internal.zzpi.zza(com_google_android_gms_common_api_zzc_O.getInstanceId(), i, com_google_android_gms_internal_zzpm_zza__extends_com_google_android_gms_common_api_Result__com_google_android_gms_common_api_Api_zzb)));
    }

    public <O extends ApiOptions, TResult> void zza(com.google.android.gms.common.api.zzc<O> com_google_android_gms_common_api_zzc_O, int i, zzqw<com.google.android.gms.common.api.Api.zzb, TResult> com_google_android_gms_internal_zzqw_com_google_android_gms_common_api_Api_zzb__TResult, TaskCompletionSource<TResult> taskCompletionSource) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new com.google.android.gms.internal.zzpi.zzb(com_google_android_gms_common_api_zzc_O.getInstanceId(), i, com_google_android_gms_internal_zzqw_com_google_android_gms_common_api_Api_zzb__TResult, taskCompletionSource)));
    }

    @WorkerThread
    public void zza(zzpl com_google_android_gms_internal_zzpl) {
        for (zzpj com_google_android_gms_internal_zzpj : com_google_android_gms_internal_zzpl.zzaoq()) {
            zzc com_google_android_gms_internal_zzqc_zzc = (zzc) this.uK.get(com_google_android_gms_internal_zzpj);
            if (com_google_android_gms_internal_zzqc_zzc == null) {
                com_google_android_gms_internal_zzpl.cancel();
                return;
            } else if (com_google_android_gms_internal_zzqc_zzc.isConnected()) {
                com_google_android_gms_internal_zzpl.zza(com_google_android_gms_internal_zzpj, ConnectionResult.rb);
            } else if (com_google_android_gms_internal_zzqc_zzc.zzaqj() != null) {
                com_google_android_gms_internal_zzpl.zza(com_google_android_gms_internal_zzpj, com_google_android_gms_internal_zzqc_zzc.zzaqj());
            } else {
                com_google_android_gms_internal_zzqc_zzc.zzb(com_google_android_gms_internal_zzpl);
            }
        }
    }

    public void zza(zzpr com_google_android_gms_internal_zzpr) {
        synchronized (zzamr) {
            if (com_google_android_gms_internal_zzpr == null) {
                this.uL = null;
                this.uM.clear();
            }
        }
    }

    public void zzaoo() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }

    boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.sh.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.sh.zza(this.mContext, connectionResult, i);
        return true;
    }

    public void zzd(int i, boolean z) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i, z ? 1 : 2));
    }
}
