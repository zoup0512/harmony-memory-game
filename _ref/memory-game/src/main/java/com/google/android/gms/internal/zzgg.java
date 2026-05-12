package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzin
public class zzgg implements zzfy {
    private final Context mContext;
    private final Object zzail = new Object();
    private final zzgj zzajz;
    private final boolean zzarl;
    private final boolean zzawn;
    private final zzga zzboe;
    private final AdRequestInfoParcel zzbot;
    private final long zzbou;
    private final long zzbov;
    private final int zzbow;
    private boolean zzbox = false;
    private final Map<zzky<zzge>, zzgd> zzboy = new HashMap();
    private List<zzge> zzboz = new ArrayList();

    public zzgg(Context context, AdRequestInfoParcel adRequestInfoParcel, zzgj com_google_android_gms_internal_zzgj, zzga com_google_android_gms_internal_zzga, boolean z, boolean z2, long j, long j2, int i) {
        this.mContext = context;
        this.zzbot = adRequestInfoParcel;
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzboe = com_google_android_gms_internal_zzga;
        this.zzarl = z;
        this.zzawn = z2;
        this.zzbou = j;
        this.zzbov = j2;
        this.zzbow = i;
    }

    private void zza(zzky<zzge> com_google_android_gms_internal_zzky_com_google_android_gms_internal_zzge) {
        zzkh.zzclc.post(new 2(this, com_google_android_gms_internal_zzky_com_google_android_gms_internal_zzge));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzge zze(java.util.List<com.google.android.gms.internal.zzky<com.google.android.gms.internal.zzge>> r5) {
        /*
        r4 = this;
        r2 = r4.zzail;
        monitor-enter(r2);
        r0 = r4.zzbox;	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r1 = new com.google.android.gms.internal.zzge;	 Catch:{ all -> 0x003c }
        r0 = -1;
        r1.<init>(r0);	 Catch:{ all -> 0x003c }
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
    L_0x000e:
        return r1;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        r2 = r5.iterator();
    L_0x0014:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x003f;
    L_0x001a:
        r0 = r2.next();
        r0 = (com.google.android.gms.internal.zzky) r0;
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r1 = (com.google.android.gms.internal.zzge) r1;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r3 = r4.zzboz;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r3.add(r1);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        if (r1 == 0) goto L_0x0014;
    L_0x002d:
        r3 = r1.zzbom;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        if (r3 != 0) goto L_0x0014;
    L_0x0031:
        r4.zza(r0);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        goto L_0x000e;
    L_0x0035:
        r0 = move-exception;
    L_0x0036:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r1, r0);
        goto L_0x0014;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        throw r0;
    L_0x003f:
        r0 = 0;
        r4.zza(r0);
        r1 = new com.google.android.gms.internal.zzge;
        r0 = 1;
        r1.<init>(r0);
        goto L_0x000e;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgg.zze(java.util.List):com.google.android.gms.internal.zzge");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzge zzf(java.util.List<com.google.android.gms.internal.zzky<com.google.android.gms.internal.zzge>> r16) {
        /*
        r15 = this;
        r1 = r15.zzail;
        monitor-enter(r1);
        r0 = r15.zzbox;	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r2 = new com.google.android.gms.internal.zzge;	 Catch:{ all -> 0x0080 }
        r0 = -1;
        r2.<init>(r0);	 Catch:{ all -> 0x0080 }
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
    L_0x000e:
        return r2;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
        r4 = -1;
        r3 = 0;
        r2 = 0;
        r0 = r15.zzboe;
        r0 = r0.zzbnw;
        r6 = -1;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 == 0) goto L_0x0083;
    L_0x001d:
        r0 = r15.zzboe;
        r0 = r0.zzbnw;
    L_0x0021:
        r8 = r16.iterator();
        r6 = r0;
    L_0x0026:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00b9;
    L_0x002c:
        r0 = r8.next();
        r0 = (com.google.android.gms.internal.zzky) r0;
        r1 = com.google.android.gms.ads.internal.zzu.zzfu();
        r10 = r1.currentTimeMillis();
        r12 = 0;
        r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r1 != 0) goto L_0x0086;
    L_0x0040:
        r1 = r0.isDone();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r1 == 0) goto L_0x0086;
    L_0x0046:
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r1 = (com.google.android.gms.internal.zzge) r1;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
    L_0x004c:
        r5 = r15.zzboz;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r5.add(r1);	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r1 == 0) goto L_0x00cc;
    L_0x0053:
        r5 = r1.zzbom;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r5 != 0) goto L_0x00cc;
    L_0x0057:
        r5 = r1.zzbor;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r5 == 0) goto L_0x00cc;
    L_0x005b:
        r9 = r5.zzmm();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r9 <= r4) goto L_0x00cc;
    L_0x0061:
        r2 = r5.zzmm();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r14 = r1;
        r1 = r0;
        r0 = r14;
    L_0x0068:
        r3 = com.google.android.gms.ads.internal.zzu.zzfu();
        r4 = r3.currentTimeMillis();
        r4 = r4 - r10;
        r4 = r6 - r4;
        r6 = 0;
        r4 = java.lang.Math.max(r4, r6);
        r3 = r1;
        r14 = r0;
        r0 = r4;
        r4 = r2;
        r2 = r14;
    L_0x007e:
        r6 = r0;
        goto L_0x0026;
    L_0x0080:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
        throw r0;
    L_0x0083:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        goto L_0x0021;
    L_0x0086:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r1 = r0.get(r6, r1);	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r1 = (com.google.android.gms.internal.zzge) r1;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        goto L_0x004c;
    L_0x008f:
        r0 = move-exception;
    L_0x0090:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r1, r0);	 Catch:{ all -> 0x00a7 }
        r0 = com.google.android.gms.ads.internal.zzu.zzfu();
        r0 = r0.currentTimeMillis();
        r0 = r0 - r10;
        r0 = r6 - r0;
        r6 = 0;
        r0 = java.lang.Math.max(r0, r6);
        goto L_0x007e;
    L_0x00a7:
        r0 = move-exception;
        r1 = com.google.android.gms.ads.internal.zzu.zzfu();
        r2 = r1.currentTimeMillis();
        r2 = r2 - r10;
        r2 = r6 - r2;
        r4 = 0;
        java.lang.Math.max(r2, r4);
        throw r0;
    L_0x00b9:
        r15.zza(r3);
        if (r2 != 0) goto L_0x000e;
    L_0x00be:
        r2 = new com.google.android.gms.internal.zzge;
        r0 = 1;
        r2.<init>(r0);
        goto L_0x000e;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00cc:
        r0 = r2;
        r1 = r3;
        r2 = r4;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgg.zzf(java.util.List):com.google.android.gms.internal.zzge");
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.zzbox = true;
            for (zzgd cancel : this.zzboy.values()) {
                cancel.cancel();
            }
        }
    }

    public zzge zzd(List<zzfz> list) {
        zzb.zzcv("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        List arrayList = new ArrayList();
        for (zzfz com_google_android_gms_internal_zzfz : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(com_google_android_gms_internal_zzfz.zzbmv);
            zzb.zzcw(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String com_google_android_gms_internal_zzgd : com_google_android_gms_internal_zzfz.zzbmw) {
                zzgd com_google_android_gms_internal_zzgd2 = new zzgd(this.mContext, com_google_android_gms_internal_zzgd, this.zzajz, this.zzboe, com_google_android_gms_internal_zzfz, this.zzbot.zzcar, this.zzbot.zzapa, this.zzbot.zzaow, this.zzarl, this.zzawn, this.zzbot.zzapo, this.zzbot.zzaps);
                zzky zza = zzkg.zza(newCachedThreadPool, new 1(this, com_google_android_gms_internal_zzgd2));
                this.zzboy.put(zza, com_google_android_gms_internal_zzgd2);
                arrayList.add(zza);
            }
        }
        switch (this.zzbow) {
            case 2:
                return zzf(arrayList);
            default:
                return zze(arrayList);
        }
    }

    public List<zzge> zzmg() {
        return this.zzboz;
    }
}
