package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzgl.zza;

@zzin
public final class zzgc extends zza {
    private final Object zzail = new Object();
    private zzge.zza zzboa;
    private zzgb zzbob;

    public void onAdClicked() {
        synchronized (this.zzail) {
            if (this.zzbob != null) {
                this.zzbob.zzdz();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.zzail) {
            if (this.zzbob != null) {
                this.zzbob.zzea();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.zzail) {
            if (this.zzboa != null) {
                this.zzboa.zzy(i == 3 ? 1 : 2);
                this.zzboa = null;
            }
        }
    }

    public void onAdImpression() {
        synchronized (this.zzail) {
            if (this.zzbob != null) {
                this.zzbob.zzee();
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.zzail) {
            if (this.zzbob != null) {
                this.zzbob.zzeb();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAdLoaded() {
        /*
        r3 = this;
        r1 = r3.zzail;
        monitor-enter(r1);
        r0 = r3.zzboa;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = r3.zzboa;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.zzy(r2);	 Catch:{ all -> 0x001d }
        r0 = 0;
        r3.zzboa = r0;	 Catch:{ all -> 0x001d }
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r3.zzbob;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r3.zzbob;	 Catch:{ all -> 0x001d }
        r0.zzed();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgc.onAdLoaded():void");
    }

    public void onAdOpened() {
        synchronized (this.zzail) {
            if (this.zzbob != null) {
                this.zzbob.zzec();
            }
        }
    }

    public void zza(@Nullable zzgb com_google_android_gms_internal_zzgb) {
        synchronized (this.zzail) {
            this.zzbob = com_google_android_gms_internal_zzgb;
        }
    }

    public void zza(zzge.zza com_google_android_gms_internal_zzge_zza) {
        synchronized (this.zzail) {
            this.zzboa = com_google_android_gms_internal_zzge_zza;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(com.google.android.gms.internal.zzgm r4) {
        /*
        r3 = this;
        r1 = r3.zzail;
        monitor-enter(r1);
        r0 = r3.zzboa;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = r3.zzboa;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.zza(r2, r4);	 Catch:{ all -> 0x001d }
        r0 = 0;
        r3.zzboa = r0;	 Catch:{ all -> 0x001d }
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r3.zzbob;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r3.zzbob;	 Catch:{ all -> 0x001d }
        r0.zzed();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgc.zza(com.google.android.gms.internal.zzgm):void");
    }
}
