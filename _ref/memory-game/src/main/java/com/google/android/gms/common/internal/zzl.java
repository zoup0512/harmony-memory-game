package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzl implements Callback {
    private final Handler mHandler;
    private final zza yE;
    private final ArrayList<ConnectionCallbacks> yF = new ArrayList();
    final ArrayList<ConnectionCallbacks> yG = new ArrayList();
    private final ArrayList<OnConnectionFailedListener> yH = new ArrayList();
    private volatile boolean yI = false;
    private final AtomicInteger yJ = new AtomicInteger(0);
    private boolean yK = false;
    private final Object zzail = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzamh();
    }

    public zzl(Looper looper, zza com_google_android_gms_common_internal_zzl_zza) {
        this.yE = com_google_android_gms_common_internal_zzl_zza;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.zzail) {
                if (this.yI && this.yE.isConnected() && this.yF.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.yE.zzamh());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzab.zzy(connectionCallbacks);
        synchronized (this.zzail) {
            contains = this.yF.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzab.zzy(onConnectionFailedListener);
        synchronized (this.zzail) {
            contains = this.yH.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzab.zzy(connectionCallbacks);
        synchronized (this.zzail) {
            if (this.yF.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.yF.add(connectionCallbacks);
            }
        }
        if (this.yE.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzab.zzy(onConnectionFailedListener);
        synchronized (this.zzail) {
            if (this.yH.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.yH.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzab.zzy(connectionCallbacks);
        synchronized (this.zzail) {
            if (!this.yF.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
            } else if (this.yK) {
                this.yG.add(connectionCallbacks);
            }
        }
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzab.zzy(onConnectionFailedListener);
        synchronized (this.zzail) {
            if (!this.yH.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public void zzasw() {
        this.yI = false;
        this.yJ.incrementAndGet();
    }

    public void zzasx() {
        this.yI = true;
    }

    public void zzgf(int i) {
        boolean z = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            z = true;
        }
        zzab.zza(z, (Object) "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzail) {
            this.yK = true;
            ArrayList arrayList = new ArrayList(this.yF);
            int i2 = this.yJ.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.yI || this.yJ.get() != i2) {
                    break;
                } else if (this.yF.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.yG.clear();
            this.yK = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzm(com.google.android.gms.common.ConnectionResult r6) {
        /*
        r5 = this;
        r1 = 1;
        r0 = android.os.Looper.myLooper();
        r2 = r5.mHandler;
        r2 = r2.getLooper();
        if (r0 != r2) goto L_0x0046;
    L_0x000d:
        r0 = r1;
    L_0x000e:
        r2 = "onConnectionFailure must only be called on the Handler thread";
        com.google.android.gms.common.internal.zzab.zza(r0, r2);
        r0 = r5.mHandler;
        r0.removeMessages(r1);
        r1 = r5.zzail;
        monitor-enter(r1);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0054 }
        r2 = r5.yH;	 Catch:{ all -> 0x0054 }
        r0.<init>(r2);	 Catch:{ all -> 0x0054 }
        r2 = r5.yJ;	 Catch:{ all -> 0x0054 }
        r2 = r2.get();	 Catch:{ all -> 0x0054 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0054 }
    L_0x002c:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0054 }
        if (r0 == 0) goto L_0x0057;
    L_0x0032:
        r0 = r3.next();	 Catch:{ all -> 0x0054 }
        r0 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r0;	 Catch:{ all -> 0x0054 }
        r4 = r5.yI;	 Catch:{ all -> 0x0054 }
        if (r4 == 0) goto L_0x0044;
    L_0x003c:
        r4 = r5.yJ;	 Catch:{ all -> 0x0054 }
        r4 = r4.get();	 Catch:{ all -> 0x0054 }
        if (r4 == r2) goto L_0x0048;
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
    L_0x0045:
        return;
    L_0x0046:
        r0 = 0;
        goto L_0x000e;
    L_0x0048:
        r4 = r5.yH;	 Catch:{ all -> 0x0054 }
        r4 = r4.contains(r0);	 Catch:{ all -> 0x0054 }
        if (r4 == 0) goto L_0x002c;
    L_0x0050:
        r0.onConnectionFailed(r6);	 Catch:{ all -> 0x0054 }
        goto L_0x002c;
    L_0x0054:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        throw r0;
    L_0x0057:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzl.zzm(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zzo(Bundle bundle) {
        boolean z = true;
        zzab.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzail) {
            zzab.zzbn(!this.yK);
            this.mHandler.removeMessages(1);
            this.yK = true;
            if (this.yG.size() != 0) {
                z = false;
            }
            zzab.zzbn(z);
            ArrayList arrayList = new ArrayList(this.yF);
            int i = this.yJ.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.yI || !this.yE.isConnected() || this.yJ.get() != i) {
                    break;
                } else if (!this.yG.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.yG.clear();
            this.yK = false;
        }
    }
}
