package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzju.zza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zzin
public class zzjl extends zzkc implements zzjk {
    private final Context mContext;
    private final Object zzail = new Object();
    private final zza zzbxr;
    private final ArrayList<Future> zzchw = new ArrayList();
    private final ArrayList<String> zzchx = new ArrayList();
    private final HashSet<String> zzchy = new HashSet();
    private final zzjf zzchz;

    public zzjl(Context context, zza com_google_android_gms_internal_zzju_zza, zzjf com_google_android_gms_internal_zzjf) {
        this.mContext = context;
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzchz = com_google_android_gms_internal_zzjf;
    }

    private zzju zza(int i, @Nullable String str, @Nullable zzfz com_google_android_gms_internal_zzfz) {
        return new zzju(this.zzbxr.zzcip.zzcar, null, this.zzbxr.zzciq.zzbnm, i, this.zzbxr.zzciq.zzbnn, this.zzbxr.zzciq.zzcca, this.zzbxr.zzciq.orientation, this.zzbxr.zzciq.zzbns, this.zzbxr.zzcip.zzcau, this.zzbxr.zzciq.zzcby, com_google_android_gms_internal_zzfz, null, str, this.zzbxr.zzcig, null, this.zzbxr.zzciq.zzcbz, this.zzbxr.zzapa, this.zzbxr.zzciq.zzcbx, this.zzbxr.zzcik, this.zzbxr.zzciq.zzccc, this.zzbxr.zzciq.zzccd, this.zzbxr.zzcie, null, this.zzbxr.zzciq.zzccn, this.zzbxr.zzciq.zzcco, this.zzbxr.zzciq.zzccp, this.zzbxr.zzciq.zzccq, this.zzbxr.zzciq.zzccr, null, this.zzbxr.zzciq.zzbnp);
    }

    private zzju zza(String str, zzfz com_google_android_gms_internal_zzfz) {
        return zza(-2, str, com_google_android_gms_internal_zzfz);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzd(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
        r3 = this;
        r1 = r3.zzail;
        monitor-enter(r1);
        r0 = r3.zzchz;	 Catch:{ all -> 0x002f }
        r0 = r0.zzcf(r4);	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0017;
    L_0x000b:
        r2 = r0.zzrv();	 Catch:{ all -> 0x002f }
        if (r2 == 0) goto L_0x0017;
    L_0x0011:
        r2 = r0.zzru();	 Catch:{ all -> 0x002f }
        if (r2 != 0) goto L_0x0019;
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
    L_0x0018:
        return;
    L_0x0019:
        r0 = r3.zza(r4, r5, r6, r0);	 Catch:{ all -> 0x002f }
        r2 = r3.zzchw;	 Catch:{ all -> 0x002f }
        r0 = r0.zzpy();	 Catch:{ all -> 0x002f }
        r0 = (java.util.concurrent.Future) r0;	 Catch:{ all -> 0x002f }
        r2.add(r0);	 Catch:{ all -> 0x002f }
        r0 = r3.zzchx;	 Catch:{ all -> 0x002f }
        r0.add(r4);	 Catch:{ all -> 0x002f }
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
        goto L_0x0018;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjl.zzd(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private zzju zzrt() {
        return zza(3, null, null);
    }

    public void onStop() {
    }

    protected zzjg zza(String str, String str2, String str3, zzjm com_google_android_gms_internal_zzjm) {
        return new zzjg(this.mContext, str, str2, str3, this.zzbxr, com_google_android_gms_internal_zzjm, this);
    }

    public void zza(String str, int i) {
    }

    public void zzcg(String str) {
        synchronized (this.zzail) {
            this.zzchy.add(str);
        }
    }

    public void zzew() {
        for (zzfz com_google_android_gms_internal_zzfz : this.zzbxr.zzcig.zzbnk) {
            String str = com_google_android_gms_internal_zzfz.zzbnc;
            for (String str2 : com_google_android_gms_internal_zzfz.zzbmw) {
                String str22;
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str22)) {
                    try {
                        str22 = new JSONObject(str).getString("class_name");
                    } catch (Throwable e) {
                        zzb.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                }
                zzd(str22, str, com_google_android_gms_internal_zzfz.zzbmu);
            }
        }
        int i = 0;
        while (i < this.zzchw.size()) {
            try {
                ((Future) this.zzchw.get(i)).get();
                synchronized (this.zzail) {
                    if (this.zzchy.contains(this.zzchx.get(i))) {
                        com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 1(this, zza((String) this.zzchx.get(i), (zzfz) this.zzbxr.zzcig.zzbnk.get(i))));
                        return;
                    }
                }
            } catch (InterruptedException e2) {
            } catch (Exception e3) {
            }
        }
        com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 2(this, zzrt()));
        return;
        i++;
    }
}
