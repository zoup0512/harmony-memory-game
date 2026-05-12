package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.stats.zzb;
import java.util.Collections;

public class zzi extends zzd {
    private final zza zzcxm = new zza(this);
    private zzac zzcxn;
    private final zzt zzcxo;
    private zzal zzcxp;

    protected class zza implements ServiceConnection {
        final /* synthetic */ zzi zzcxq;
        private volatile zzac zzcxr;
        private volatile boolean zzcxs;

        protected zza(zzi com_google_android_gms_analytics_internal_zzi) {
            this.zzcxq = com_google_android_gms_analytics_internal_zzi;
        }

        public void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.analytics.internal.zzi.zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void. bs: [B:3:0x0008, B:9:0x0015]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
            /*
            r4 = this;
            r0 = "AnalyticsServiceConnection.onServiceConnected";
            com.google.android.gms.common.internal.zzab.zzhi(r0);
            monitor-enter(r4);
            if (r6 != 0) goto L_0x0014;
        L_0x0008:
            r0 = r4.zzcxq;	 Catch:{ all -> 0x005a }
            r1 = "Service connected with null binder";	 Catch:{ all -> 0x005a }
            r0.zzel(r1);	 Catch:{ all -> 0x005a }
            r4.notifyAll();	 Catch:{ all -> 0x0046 }
            monitor-exit(r4);	 Catch:{ all -> 0x0046 }
        L_0x0013:
            return;
        L_0x0014:
            r0 = 0;
            r1 = r6.getInterfaceDescriptor();	 Catch:{ RemoteException -> 0x0051 }
            r2 = "com.google.android.gms.analytics.internal.IAnalyticsService";	 Catch:{ RemoteException -> 0x0051 }
            r2 = r2.equals(r1);	 Catch:{ RemoteException -> 0x0051 }
            if (r2 == 0) goto L_0x0049;	 Catch:{ RemoteException -> 0x0051 }
        L_0x0021:
            r0 = com.google.android.gms.analytics.internal.zzac.zza.zzbk(r6);	 Catch:{ RemoteException -> 0x0051 }
            r1 = r4.zzcxq;	 Catch:{ RemoteException -> 0x0051 }
            r2 = "Bound to IAnalyticsService interface";	 Catch:{ RemoteException -> 0x0051 }
            r1.zzeh(r2);	 Catch:{ RemoteException -> 0x0051 }
        L_0x002c:
            if (r0 != 0) goto L_0x005f;
        L_0x002e:
            r0 = com.google.android.gms.common.stats.zzb.zzaux();	 Catch:{ IllegalArgumentException -> 0x007c }
            r1 = r4.zzcxq;	 Catch:{ IllegalArgumentException -> 0x007c }
            r1 = r1.getContext();	 Catch:{ IllegalArgumentException -> 0x007c }
            r2 = r4.zzcxq;	 Catch:{ IllegalArgumentException -> 0x007c }
            r2 = r2.zzcxm;	 Catch:{ IllegalArgumentException -> 0x007c }
            r0.zza(r1, r2);	 Catch:{ IllegalArgumentException -> 0x007c }
        L_0x0041:
            r4.notifyAll();	 Catch:{ all -> 0x0046 }
            monitor-exit(r4);	 Catch:{ all -> 0x0046 }
            goto L_0x0013;	 Catch:{ all -> 0x0046 }
        L_0x0046:
            r0 = move-exception;	 Catch:{ all -> 0x0046 }
            monitor-exit(r4);	 Catch:{ all -> 0x0046 }
            throw r0;
        L_0x0049:
            r2 = r4.zzcxq;	 Catch:{ RemoteException -> 0x0051 }
            r3 = "Got binder with a wrong descriptor";	 Catch:{ RemoteException -> 0x0051 }
            r2.zze(r3, r1);	 Catch:{ RemoteException -> 0x0051 }
            goto L_0x002c;
        L_0x0051:
            r1 = move-exception;
            r1 = r4.zzcxq;	 Catch:{ all -> 0x005a }
            r2 = "Service connect failed to get IAnalyticsService";	 Catch:{ all -> 0x005a }
            r1.zzel(r2);	 Catch:{ all -> 0x005a }
            goto L_0x002c;
        L_0x005a:
            r0 = move-exception;
            r4.notifyAll();	 Catch:{ all -> 0x0046 }
            throw r0;	 Catch:{ all -> 0x0046 }
        L_0x005f:
            r1 = r4.zzcxs;	 Catch:{ all -> 0x005a }
            if (r1 != 0) goto L_0x0079;	 Catch:{ all -> 0x005a }
        L_0x0063:
            r1 = r4.zzcxq;	 Catch:{ all -> 0x005a }
            r2 = "onServiceConnected received after the timeout limit";	 Catch:{ all -> 0x005a }
            r1.zzek(r2);	 Catch:{ all -> 0x005a }
            r1 = r4.zzcxq;	 Catch:{ all -> 0x005a }
            r1 = r1.zzyz();	 Catch:{ all -> 0x005a }
            r2 = new com.google.android.gms.analytics.internal.zzi$zza$1;	 Catch:{ all -> 0x005a }
            r2.<init>(r4, r0);	 Catch:{ all -> 0x005a }
            r1.zzg(r2);	 Catch:{ all -> 0x005a }
            goto L_0x0041;	 Catch:{ all -> 0x005a }
        L_0x0079:
            r4.zzcxr = r0;	 Catch:{ all -> 0x005a }
            goto L_0x0041;
        L_0x007c:
            r0 = move-exception;
            goto L_0x0041;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzi.zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(final ComponentName componentName) {
            zzab.zzhi("AnalyticsServiceConnection.onServiceDisconnected");
            this.zzcxq.zzyz().zzg(new Runnable(this) {
                final /* synthetic */ zza zzcxu;

                public void run() {
                    this.zzcxu.zzcxq.onServiceDisconnected(componentName);
                }
            });
        }

        public zzac zzzv() {
            zzac com_google_android_gms_analytics_internal_zzac = null;
            this.zzcxq.zzwu();
            Intent intent = new Intent("com.google.android.gms.analytics.service.START");
            intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
            Context context = this.zzcxq.getContext();
            intent.putExtra("app_package_name", context.getPackageName());
            zzb zzaux = zzb.zzaux();
            synchronized (this) {
                this.zzcxr = null;
                this.zzcxs = true;
                boolean zza = zzaux.zza(context, intent, this.zzcxq.zzcxm, 129);
                this.zzcxq.zza("Bind to service requested", Boolean.valueOf(zza));
                if (zza) {
                    try {
                        wait(this.zzcxq.zzyy().zzaby());
                    } catch (InterruptedException e) {
                        this.zzcxq.zzek("Wait for service connect was interrupted");
                    }
                    this.zzcxs = false;
                    com_google_android_gms_analytics_internal_zzac = this.zzcxr;
                    this.zzcxr = null;
                    if (com_google_android_gms_analytics_internal_zzac == null) {
                        this.zzcxq.zzel("Successfully bound to service but never got onServiceConnected callback");
                    }
                } else {
                    this.zzcxs = false;
                }
            }
            return com_google_android_gms_analytics_internal_zzac;
        }
    }

    protected zzi(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
        this.zzcxp = new zzal(com_google_android_gms_analytics_internal_zzf.zzyw());
        this.zzcxo = new zzt(this, com_google_android_gms_analytics_internal_zzf) {
            final /* synthetic */ zzi zzcxq;

            public void run() {
                this.zzcxq.zzzu();
            }
        };
    }

    private void onDisconnect() {
        zzwd().zzyr();
    }

    private void onServiceDisconnected(ComponentName componentName) {
        zzwu();
        if (this.zzcxn != null) {
            this.zzcxn = null;
            zza("Disconnected from device AnalyticsService", componentName);
            onDisconnect();
        }
    }

    private void zza(zzac com_google_android_gms_analytics_internal_zzac) {
        zzwu();
        this.zzcxn = com_google_android_gms_analytics_internal_zzac;
        zzzt();
        zzwd().onServiceConnected();
    }

    private void zzzt() {
        this.zzcxp.start();
        this.zzcxo.zzv(zzyy().zzabx());
    }

    private void zzzu() {
        zzwu();
        if (isConnected()) {
            zzeh("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public boolean connect() {
        zzwu();
        zzzg();
        if (this.zzcxn != null) {
            return true;
        }
        zzac zzzv = this.zzcxm.zzzv();
        if (zzzv == null) {
            return false;
        }
        this.zzcxn = zzzv;
        zzzt();
        return true;
    }

    public void disconnect() {
        zzwu();
        zzzg();
        try {
            zzb.zzaux().zza(getContext(), this.zzcxm);
        } catch (IllegalStateException e) {
        } catch (IllegalArgumentException e2) {
        }
        if (this.zzcxn != null) {
            this.zzcxn = null;
            onDisconnect();
        }
    }

    public boolean isConnected() {
        zzwu();
        zzzg();
        return this.zzcxn != null;
    }

    public boolean zzb(zzab com_google_android_gms_analytics_internal_zzab) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzab);
        zzwu();
        zzzg();
        zzac com_google_android_gms_analytics_internal_zzac = this.zzcxn;
        if (com_google_android_gms_analytics_internal_zzac == null) {
            return false;
        }
        try {
            com_google_android_gms_analytics_internal_zzac.zza(com_google_android_gms_analytics_internal_zzab.zzm(), com_google_android_gms_analytics_internal_zzab.zzacz(), com_google_android_gms_analytics_internal_zzab.zzadb() ? zzyy().zzabq() : zzyy().zzabr(), Collections.emptyList());
            zzzt();
            return true;
        } catch (RemoteException e) {
            zzeh("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    protected void zzwv() {
    }

    public boolean zzzs() {
        zzwu();
        zzzg();
        zzac com_google_android_gms_analytics_internal_zzac = this.zzcxn;
        if (com_google_android_gms_analytics_internal_zzac == null) {
            return false;
        }
        try {
            com_google_android_gms_analytics_internal_zzac.zzyo();
            zzzt();
            return true;
        } catch (RemoteException e) {
            zzeh("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
