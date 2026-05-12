package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfp.zza;
import java.util.Map;

class zzfs$1 implements Runnable {
    final /* synthetic */ zzas zzblw;
    final /* synthetic */ zzfs$zzd zzblx;
    final /* synthetic */ zzfs zzbly;

    zzfs$1(zzfs com_google_android_gms_internal_zzfs, zzas com_google_android_gms_internal_zzas, zzfs$zzd com_google_android_gms_internal_zzfs_zzd) {
        this.zzbly = com_google_android_gms_internal_zzfs;
        this.zzblw = com_google_android_gms_internal_zzas;
        this.zzblx = com_google_android_gms_internal_zzfs_zzd;
    }

    public void run() {
        final zzfp zza = this.zzbly.zza(zzfs.zza(this.zzbly), zzfs.zzb(this.zzbly), this.zzblw);
        zza.zza(new zza(this) {
            final /* synthetic */ zzfs$1 zzbma;

            public void zzlz() {
                zzkh.zzclc.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 zzbmb;

                    {
                        this.zzbmb = r1;
                    }

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r3 = this;
                        r0 = r3.zzbmb;
                        r0 = r0.zzbma;
                        r0 = r0.zzbly;
                        r1 = com.google.android.gms.internal.zzfs.zzc(r0);
                        monitor-enter(r1);
                        r0 = r3.zzbmb;	 Catch:{ all -> 0x0043 }
                        r0 = r0.zzbma;	 Catch:{ all -> 0x0043 }
                        r0 = r0.zzblx;	 Catch:{ all -> 0x0043 }
                        r0 = r0.getStatus();	 Catch:{ all -> 0x0043 }
                        r2 = -1;
                        if (r0 == r2) goto L_0x0025;
                    L_0x0018:
                        r0 = r3.zzbmb;	 Catch:{ all -> 0x0043 }
                        r0 = r0.zzbma;	 Catch:{ all -> 0x0043 }
                        r0 = r0.zzblx;	 Catch:{ all -> 0x0043 }
                        r0 = r0.getStatus();	 Catch:{ all -> 0x0043 }
                        r2 = 1;
                        if (r0 != r2) goto L_0x0027;
                    L_0x0025:
                        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                    L_0x0026:
                        return;
                    L_0x0027:
                        r0 = r3.zzbmb;	 Catch:{ all -> 0x0043 }
                        r0 = r0.zzbma;	 Catch:{ all -> 0x0043 }
                        r0 = r0.zzblx;	 Catch:{ all -> 0x0043 }
                        r0.reject();	 Catch:{ all -> 0x0043 }
                        r0 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x0043 }
                        r2 = new com.google.android.gms.internal.zzfs$1$1$1$1;	 Catch:{ all -> 0x0043 }
                        r2.<init>(r3);	 Catch:{ all -> 0x0043 }
                        r0.runOnUiThread(r2);	 Catch:{ all -> 0x0043 }
                        r0 = "Could not receive loaded message in a timely manner. Rejecting.";
                        com.google.android.gms.internal.zzkd.v(r0);	 Catch:{ all -> 0x0043 }
                        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                        goto L_0x0026;
                    L_0x0043:
                        r0 = move-exception;
                        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfs.1.1.1.run():void");
                    }
                }, (long) zzfs$zza.zzbmh);
            }
        });
        zza.zza("/jsLoaded", new zzep(this) {
            final /* synthetic */ zzfs$1 zzbma;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void zza(com.google.android.gms.internal.zzlh r4, java.util.Map<java.lang.String, java.lang.String> r5) {
                /*
                r3 = this;
                r0 = r3.zzbma;
                r0 = r0.zzbly;
                r1 = com.google.android.gms.internal.zzfs.zzc(r0);
                monitor-enter(r1);
                r0 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r0 = r0.zzblx;	 Catch:{ all -> 0x0051 }
                r0 = r0.getStatus();	 Catch:{ all -> 0x0051 }
                r2 = -1;
                if (r0 == r2) goto L_0x001f;
            L_0x0014:
                r0 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r0 = r0.zzblx;	 Catch:{ all -> 0x0051 }
                r0 = r0.getStatus();	 Catch:{ all -> 0x0051 }
                r2 = 1;
                if (r0 != r2) goto L_0x0021;
            L_0x001f:
                monitor-exit(r1);	 Catch:{ all -> 0x0051 }
            L_0x0020:
                return;
            L_0x0021:
                r0 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r0 = r0.zzbly;	 Catch:{ all -> 0x0051 }
                r2 = 0;
                com.google.android.gms.internal.zzfs.zza(r0, r2);	 Catch:{ all -> 0x0051 }
                r0 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r0 = r0.zzbly;	 Catch:{ all -> 0x0051 }
                r0 = com.google.android.gms.internal.zzfs.zzd(r0);	 Catch:{ all -> 0x0051 }
                r2 = r0;	 Catch:{ all -> 0x0051 }
                r0.zzd(r2);	 Catch:{ all -> 0x0051 }
                r0 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r0 = r0.zzblx;	 Catch:{ all -> 0x0051 }
                r2 = r0;	 Catch:{ all -> 0x0051 }
                r0.zzg(r2);	 Catch:{ all -> 0x0051 }
                r0 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r0 = r0.zzbly;	 Catch:{ all -> 0x0051 }
                r2 = r3.zzbma;	 Catch:{ all -> 0x0051 }
                r2 = r2.zzblx;	 Catch:{ all -> 0x0051 }
                com.google.android.gms.internal.zzfs.zza(r0, r2);	 Catch:{ all -> 0x0051 }
                r0 = "Successfully loaded JS Engine.";
                com.google.android.gms.internal.zzkd.v(r0);	 Catch:{ all -> 0x0051 }
                monitor-exit(r1);	 Catch:{ all -> 0x0051 }
                goto L_0x0020;
            L_0x0051:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x0051 }
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfs.1.2.zza(com.google.android.gms.internal.zzlh, java.util.Map):void");
            }
        });
        final zzks com_google_android_gms_internal_zzks = new zzks();
        zzep anonymousClass3 = new zzep(this) {
            final /* synthetic */ zzfs$1 zzbma;

            public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
                synchronized (zzfs.zzc(this.zzbma.zzbly)) {
                    zzkd.zzcw("JS Engine is requesting an update");
                    if (zzfs.zze(this.zzbma.zzbly) == 0) {
                        zzkd.zzcw("Starting reload.");
                        zzfs.zza(this.zzbma.zzbly, 2);
                        this.zzbma.zzbly.zzb(this.zzbma.zzblw);
                    }
                    zza.zzb("/requestReload", (zzep) com_google_android_gms_internal_zzks.get());
                }
            }
        };
        com_google_android_gms_internal_zzks.set(anonymousClass3);
        zza.zza("/requestReload", anonymousClass3);
        if (zzfs.zzf(this.zzbly).endsWith(".js")) {
            zza.zzbg(zzfs.zzf(this.zzbly));
        } else if (zzfs.zzf(this.zzbly).startsWith("<html>")) {
            zza.zzbi(zzfs.zzf(this.zzbly));
        } else {
            zza.zzbh(zzfs.zzf(this.zzbly));
        }
        zzkh.zzclc.postDelayed(new Runnable(this) {
            final /* synthetic */ zzfs$1 zzbma;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r3 = this;
                r0 = r3.zzbma;
                r0 = r0.zzbly;
                r1 = com.google.android.gms.internal.zzfs.zzc(r0);
                monitor-enter(r1);
                r0 = r3.zzbma;	 Catch:{ all -> 0x003b }
                r0 = r0.zzblx;	 Catch:{ all -> 0x003b }
                r0 = r0.getStatus();	 Catch:{ all -> 0x003b }
                r2 = -1;
                if (r0 == r2) goto L_0x001f;
            L_0x0014:
                r0 = r3.zzbma;	 Catch:{ all -> 0x003b }
                r0 = r0.zzblx;	 Catch:{ all -> 0x003b }
                r0 = r0.getStatus();	 Catch:{ all -> 0x003b }
                r2 = 1;
                if (r0 != r2) goto L_0x0021;
            L_0x001f:
                monitor-exit(r1);	 Catch:{ all -> 0x003b }
            L_0x0020:
                return;
            L_0x0021:
                r0 = r3.zzbma;	 Catch:{ all -> 0x003b }
                r0 = r0.zzblx;	 Catch:{ all -> 0x003b }
                r0.reject();	 Catch:{ all -> 0x003b }
                r0 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x003b }
                r2 = new com.google.android.gms.internal.zzfs$1$4$1;	 Catch:{ all -> 0x003b }
                r2.<init>(r3);	 Catch:{ all -> 0x003b }
                r0.runOnUiThread(r2);	 Catch:{ all -> 0x003b }
                r0 = "Could not receive loaded message in a timely manner. Rejecting.";
                com.google.android.gms.internal.zzkd.v(r0);	 Catch:{ all -> 0x003b }
                monitor-exit(r1);	 Catch:{ all -> 0x003b }
                goto L_0x0020;
            L_0x003b:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x003b }
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfs.1.4.run():void");
            }
        }, (long) zzfs$zza.zzbmg);
    }
}
