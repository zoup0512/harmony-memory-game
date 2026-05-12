package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.ArrayList;
import java.util.List;

@zzin
public class zzgh implements zzfy {
    private final Context mContext;
    private final Object zzail = new Object();
    private final zzdk zzajn;
    private final zzgj zzajz;
    private final boolean zzarl;
    private final boolean zzawn;
    private final zzga zzboe;
    private final AdRequestInfoParcel zzbot;
    private final long zzbou;
    private final long zzbov;
    private boolean zzbox = false;
    private List<zzge> zzboz = new ArrayList();
    private zzgd zzbpd;

    public zzgh(Context context, AdRequestInfoParcel adRequestInfoParcel, zzgj com_google_android_gms_internal_zzgj, zzga com_google_android_gms_internal_zzga, boolean z, boolean z2, long j, long j2, zzdk com_google_android_gms_internal_zzdk) {
        this.mContext = context;
        this.zzbot = adRequestInfoParcel;
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzboe = com_google_android_gms_internal_zzga;
        this.zzarl = z;
        this.zzawn = z2;
        this.zzbou = j;
        this.zzbov = j2;
        this.zzajn = com_google_android_gms_internal_zzdk;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.zzbox = true;
            if (this.zzbpd != null) {
                this.zzbpd.cancel();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzge zzd(java.util.List<com.google.android.gms.internal.zzfz> r22) {
        /*
        r21 = this;
        r2 = "Starting mediation.";
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r2);
        r15 = new java.util.ArrayList;
        r15.<init>();
        r0 = r21;
        r2 = r0.zzajn;
        r16 = r2.zzkg();
        r17 = r22.iterator();
    L_0x0016:
        r2 = r17.hasNext();
        if (r2 == 0) goto L_0x0133;
    L_0x001c:
        r7 = r17.next();
        r7 = (com.google.android.gms.internal.zzfz) r7;
        r3 = "Trying mediation network: ";
        r2 = r7.zzbmv;
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0066;
    L_0x0030:
        r2 = r3.concat(r2);
    L_0x0034:
        com.google.android.gms.ads.internal.util.client.zzb.zzcw(r2);
        r2 = r7.zzbmw;
        r18 = r2.iterator();
    L_0x003d:
        r2 = r18.hasNext();
        if (r2 == 0) goto L_0x0016;
    L_0x0043:
        r4 = r18.next();
        r4 = (java.lang.String) r4;
        r0 = r21;
        r2 = r0.zzajn;
        r19 = r2.zzkg();
        r0 = r21;
        r0 = r0.zzail;
        r20 = r0;
        monitor-enter(r20);
        r0 = r21;
        r2 = r0.zzbox;	 Catch:{ all -> 0x010a }
        if (r2 == 0) goto L_0x006c;
    L_0x005e:
        r2 = new com.google.android.gms.internal.zzge;	 Catch:{ all -> 0x010a }
        r3 = -1;
        r2.<init>(r3);	 Catch:{ all -> 0x010a }
        monitor-exit(r20);	 Catch:{ all -> 0x010a }
    L_0x0065:
        return r2;
    L_0x0066:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0034;
    L_0x006c:
        r2 = new com.google.android.gms.internal.zzgd;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r3 = r0.mContext;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r5 = r0.zzajz;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r6 = r0.zzboe;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r8 = r0.zzbot;	 Catch:{ all -> 0x010a }
        r8 = r8.zzcar;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r9 = r0.zzbot;	 Catch:{ all -> 0x010a }
        r9 = r9.zzapa;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r10 = r0.zzbot;	 Catch:{ all -> 0x010a }
        r10 = r10.zzaow;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r11 = r0.zzarl;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r12 = r0.zzawn;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r13 = r0.zzbot;	 Catch:{ all -> 0x010a }
        r13 = r13.zzapo;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r14 = r0.zzbot;	 Catch:{ all -> 0x010a }
        r14 = r14.zzaps;	 Catch:{ all -> 0x010a }
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);	 Catch:{ all -> 0x010a }
        r0 = r21;
        r0.zzbpd = r2;	 Catch:{ all -> 0x010a }
        monitor-exit(r20);	 Catch:{ all -> 0x010a }
        r0 = r21;
        r2 = r0.zzbpd;
        r0 = r21;
        r8 = r0.zzbou;
        r0 = r21;
        r10 = r0.zzbov;
        r2 = r2.zza(r8, r10);
        r0 = r21;
        r3 = r0.zzboz;
        r3.add(r2);
        r3 = r2.zzbom;
        if (r3 != 0) goto L_0x010d;
    L_0x00c3:
        r3 = "Adapter succeeded.";
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r3);
        r0 = r21;
        r3 = r0.zzajn;
        r5 = "mediation_network_succeed";
        r3.zzh(r5, r4);
        r3 = r15.isEmpty();
        if (r3 != 0) goto L_0x00e6;
    L_0x00d7:
        r0 = r21;
        r3 = r0.zzajn;
        r4 = "mediation_networks_fail";
        r5 = ",";
        r5 = android.text.TextUtils.join(r5, r15);
        r3.zzh(r4, r5);
    L_0x00e6:
        r0 = r21;
        r3 = r0.zzajn;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mls";
        r4[r5] = r6;
        r0 = r19;
        r3.zza(r0, r4);
        r0 = r21;
        r3 = r0.zzajn;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "ttm";
        r4[r5] = r6;
        r0 = r16;
        r3.zza(r0, r4);
        goto L_0x0065;
    L_0x010a:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x010a }
        throw r2;
    L_0x010d:
        r15.add(r4);
        r0 = r21;
        r3 = r0.zzajn;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mlf";
        r4[r5] = r6;
        r0 = r19;
        r3.zza(r0, r4);
        r3 = r2.zzboo;
        if (r3 == 0) goto L_0x003d;
    L_0x0125:
        r3 = com.google.android.gms.internal.zzkh.zzclc;
        r4 = new com.google.android.gms.internal.zzgh$1;
        r0 = r21;
        r4.<init>(r0, r2);
        r3.post(r4);
        goto L_0x003d;
    L_0x0133:
        r2 = r15.isEmpty();
        if (r2 != 0) goto L_0x0148;
    L_0x0139:
        r0 = r21;
        r2 = r0.zzajn;
        r3 = "mediation_networks_fail";
        r4 = ",";
        r4 = android.text.TextUtils.join(r4, r15);
        r2.zzh(r3, r4);
    L_0x0148:
        r2 = new com.google.android.gms.internal.zzge;
        r3 = 1;
        r2.<init>(r3);
        goto L_0x0065;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgh.zzd(java.util.List):com.google.android.gms.internal.zzge");
    }

    public List<zzge> zzmg() {
        return this.zzboz;
    }
}
