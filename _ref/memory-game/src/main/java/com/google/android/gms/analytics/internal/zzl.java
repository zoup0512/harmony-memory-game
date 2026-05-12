package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zze;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzmd;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class zzl extends zzd {
    private boolean mStarted;
    private final zzj zzcyb;
    private final zzah zzcyc;
    private final zzag zzcyd;
    private final zzi zzcye;
    private long zzcyf = Long.MIN_VALUE;
    private final zzt zzcyg;
    private final zzt zzcyh;
    private final zzal zzcyi;
    private long zzcyj;
    private boolean zzcyk;

    protected zzl(zzf com_google_android_gms_analytics_internal_zzf, zzg com_google_android_gms_analytics_internal_zzg) {
        super(com_google_android_gms_analytics_internal_zzf);
        zzab.zzy(com_google_android_gms_analytics_internal_zzg);
        this.zzcyd = com_google_android_gms_analytics_internal_zzg.zzk(com_google_android_gms_analytics_internal_zzf);
        this.zzcyb = com_google_android_gms_analytics_internal_zzg.zzm(com_google_android_gms_analytics_internal_zzf);
        this.zzcyc = com_google_android_gms_analytics_internal_zzg.zzn(com_google_android_gms_analytics_internal_zzf);
        this.zzcye = com_google_android_gms_analytics_internal_zzg.zzo(com_google_android_gms_analytics_internal_zzf);
        this.zzcyi = new zzal(zzyw());
        this.zzcyg = new zzt(this, com_google_android_gms_analytics_internal_zzf) {
            final /* synthetic */ zzl zzcyl;

            public void run() {
                this.zzcyl.zzaag();
            }
        };
        this.zzcyh = new zzt(this, com_google_android_gms_analytics_internal_zzf) {
            final /* synthetic */ zzl zzcyl;

            public void run() {
                this.zzcyl.zzaah();
            }
        };
    }

    private void zza(zzh com_google_android_gms_analytics_internal_zzh, zzlv com_google_android_gms_internal_zzlv) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzh);
        zzab.zzy(com_google_android_gms_internal_zzlv);
        zza com_google_android_gms_analytics_zza = new zza(zzyu());
        com_google_android_gms_analytics_zza.zzdg(com_google_android_gms_analytics_internal_zzh.zzzp());
        com_google_android_gms_analytics_zza.enableAdvertisingIdCollection(com_google_android_gms_analytics_internal_zzh.zzzq());
        zze zzvr = com_google_android_gms_analytics_zza.zzvr();
        zzmd com_google_android_gms_internal_zzmd = (zzmd) zzvr.zzb(zzmd.class);
        com_google_android_gms_internal_zzmd.zzdw(ShareConstants.WEB_DIALOG_PARAM_DATA);
        com_google_android_gms_internal_zzmd.zzap(true);
        zzvr.zza((zzg) com_google_android_gms_internal_zzlv);
        zzly com_google_android_gms_internal_zzly = (zzly) zzvr.zzb(zzly.class);
        zzlu com_google_android_gms_internal_zzlu = (zzlu) zzvr.zzb(zzlu.class);
        for (Entry entry : com_google_android_gms_analytics_internal_zzh.zzm().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                com_google_android_gms_internal_zzlu.setAppName(str2);
            } else if ("av".equals(str)) {
                com_google_android_gms_internal_zzlu.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                com_google_android_gms_internal_zzlu.setAppId(str2);
            } else if ("aiid".equals(str)) {
                com_google_android_gms_internal_zzlu.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                com_google_android_gms_internal_zzmd.setUserId(str2);
            } else {
                com_google_android_gms_internal_zzly.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", com_google_android_gms_analytics_internal_zzh.zzzp(), com_google_android_gms_internal_zzlv);
        zzvr.zzn(zzzb().zzadn());
        zzvr.zzwj();
    }

    private void zzaae() {
        zzwu();
        Context context = zzyu().getContext();
        if (!zzaj.zzav(context)) {
            zzek("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzak.zzaw(context)) {
            zzel("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzav(context)) {
            zzek("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!CampaignTrackingService.zzaw(context)) {
            zzek("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
    }

    private void zzaag() {
        zzb(new zzw(this) {
            final /* synthetic */ zzl zzcyl;

            {
                this.zzcyl = r1;
            }

            public void zzd(Throwable th) {
                this.zzcyl.zzaam();
            }
        });
    }

    private void zzaah() {
        try {
            this.zzcyb.zzzy();
            zzaam();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzcyh.zzv(zzyy().zzace());
    }

    private boolean zzaan() {
        return this.zzcyk ? false : (!zzyy().zzabc() || zzyy().zzabd()) && zzaat() > 0;
    }

    private void zzaao() {
        zzv zzza = zzza();
        if (zzza.zzacm() && !zzza.zzfc()) {
            long zzzz = zzzz();
            if (zzzz != 0 && Math.abs(zzyw().currentTimeMillis() - zzzz) <= zzyy().zzabm()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzyy().zzabl()));
                zzza.schedule();
            }
        }
    }

    private void zzaap() {
        zzaao();
        long zzaat = zzaat();
        long zzadp = zzzb().zzadp();
        if (zzadp != 0) {
            zzadp = zzaat - Math.abs(zzyw().currentTimeMillis() - zzadp);
            if (zzadp <= 0) {
                zzadp = Math.min(zzyy().zzabj(), zzaat);
            }
        } else {
            zzadp = Math.min(zzyy().zzabj(), zzaat);
        }
        zza("Dispatch scheduled (ms)", Long.valueOf(zzadp));
        if (this.zzcyg.zzfc()) {
            this.zzcyg.zzw(Math.max(1, zzadp + this.zzcyg.zzacj()));
            return;
        }
        this.zzcyg.zzv(zzadp);
    }

    private void zzaaq() {
        zzaar();
        zzaas();
    }

    private void zzaar() {
        if (this.zzcyg.zzfc()) {
            zzeh("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzcyg.cancel();
    }

    private void zzaas() {
        zzv zzza = zzza();
        if (zzza.zzfc()) {
            zzza.cancel();
        }
    }

    private boolean zzeo(String str) {
        return getContext().checkCallingOrSelfPermission(str) == 0;
    }

    protected void onServiceConnected() {
        zzwu();
        if (!zzyy().zzabc()) {
            zzaaj();
        }
    }

    void start() {
        zzzg();
        zzab.zza(!this.mStarted, (Object) "Analytics backend already started");
        this.mStarted = true;
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzl zzcyl;

            {
                this.zzcyl = r1;
            }

            public void run() {
                this.zzcyl.zzaaf();
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long zza(com.google.android.gms.analytics.internal.zzh r6, boolean r7) {
        /*
        r5 = this;
        com.google.android.gms.common.internal.zzab.zzy(r6);
        r5.zzzg();
        r5.zzwu();
        r0 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x0049 }
        r0.beginTransaction();	 Catch:{ SQLiteException -> 0x0049 }
        r0 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x0049 }
        r2 = r6.zzzo();	 Catch:{ SQLiteException -> 0x0049 }
        r1 = r6.zzwb();	 Catch:{ SQLiteException -> 0x0049 }
        r0.zza(r2, r1);	 Catch:{ SQLiteException -> 0x0049 }
        r0 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x0049 }
        r2 = r6.zzzo();	 Catch:{ SQLiteException -> 0x0049 }
        r1 = r6.zzwb();	 Catch:{ SQLiteException -> 0x0049 }
        r4 = r6.zzzp();	 Catch:{ SQLiteException -> 0x0049 }
        r0 = r0.zza(r2, r1, r4);	 Catch:{ SQLiteException -> 0x0049 }
        if (r7 != 0) goto L_0x0042;
    L_0x002f:
        r6.zzp(r0);	 Catch:{ SQLiteException -> 0x0049 }
    L_0x0032:
        r2 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x0049 }
        r2.zzb(r6);	 Catch:{ SQLiteException -> 0x0049 }
        r2 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x0049 }
        r2.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0049 }
        r2 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x0057 }
        r2.endTransaction();	 Catch:{ SQLiteException -> 0x0057 }
    L_0x0041:
        return r0;
    L_0x0042:
        r2 = 1;
        r2 = r2 + r0;
        r6.zzp(r2);	 Catch:{ SQLiteException -> 0x0049 }
        goto L_0x0032;
    L_0x0049:
        r0 = move-exception;
        r1 = "Failed to update Analytics property";
        r5.zze(r1, r0);	 Catch:{ all -> 0x0065 }
        r0 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x005e }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x005e }
    L_0x0054:
        r0 = -1;
        goto L_0x0041;
    L_0x0057:
        r2 = move-exception;
        r3 = "Failed to end transaction";
        r5.zze(r3, r2);
        goto L_0x0041;
    L_0x005e:
        r0 = move-exception;
        r1 = "Failed to end transaction";
        r5.zze(r1, r0);
        goto L_0x0054;
    L_0x0065:
        r0 = move-exception;
        r1 = r5.zzcyb;	 Catch:{ SQLiteException -> 0x006c }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x006c }
    L_0x006b:
        throw r0;
    L_0x006c:
        r1 = move-exception;
        r2 = "Failed to end transaction";
        r5.zze(r2, r1);
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzl.zza(com.google.android.gms.analytics.internal.zzh, boolean):long");
    }

    public void zza(zzab com_google_android_gms_analytics_internal_zzab) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzab);
        zzi.zzwu();
        zzzg();
        if (this.zzcyk) {
            zzei("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", com_google_android_gms_analytics_internal_zzab);
        }
        zzab zzf = zzf(com_google_android_gms_analytics_internal_zzab);
        zzaai();
        if (this.zzcye.zzb(zzf)) {
            zzei("Hit sent to the device AnalyticsService for delivery");
        } else if (zzyy().zzabc()) {
            zzyx().zza(zzf, "Service unavailable on package side");
        } else {
            try {
                this.zzcyb.zzc(zzf);
                zzaam();
            } catch (SQLiteException e) {
                zze("Delivery failed to save hit to a database", e);
                zzyx().zza(zzf, "deliver: failed to insert hit to database");
            }
        }
    }

    public void zza(final zzw com_google_android_gms_analytics_internal_zzw, final long j) {
        zzi.zzwu();
        zzzg();
        long j2 = -1;
        long zzadp = zzzb().zzadp();
        if (zzadp != 0) {
            j2 = Math.abs(zzyw().currentTimeMillis() - zzadp);
        }
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(j2));
        if (!zzyy().zzabc()) {
            zzaai();
        }
        try {
            if (zzaak()) {
                zzyz().zzg(new Runnable(this) {
                    final /* synthetic */ zzl zzcyl;

                    public void run() {
                        this.zzcyl.zza(com_google_android_gms_analytics_internal_zzw, j);
                    }
                });
                return;
            }
            zzzb().zzadq();
            zzaam();
            if (com_google_android_gms_analytics_internal_zzw != null) {
                com_google_android_gms_analytics_internal_zzw.zzd(null);
            }
            if (this.zzcyj != j) {
                this.zzcyd.zzadi();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zzzb().zzadq();
            zzaam();
            if (com_google_android_gms_analytics_internal_zzw != null) {
                com_google_android_gms_analytics_internal_zzw.zzd(th);
            }
        }
    }

    protected void zzaaf() {
        zzzg();
        if (!zzyy().zzabc()) {
            zzaae();
        }
        zzzb().zzadn();
        if (!zzeo("android.permission.ACCESS_NETWORK_STATE")) {
            zzel("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzaau();
        }
        if (!zzeo("android.permission.INTERNET")) {
            zzel("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzaau();
        }
        if (zzak.zzaw(getContext())) {
            zzeh("AnalyticsService registered in the app manifest and enabled");
        } else if (zzyy().zzabc()) {
            zzel("Device AnalyticsService not registered! Hits will not be delivered reliably.");
        } else {
            zzek("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!(this.zzcyk || zzyy().zzabc() || this.zzcyb.isEmpty())) {
            zzaai();
        }
        zzaam();
    }

    protected void zzaai() {
        if (!this.zzcyk && zzyy().zzabe() && !this.zzcye.isConnected()) {
            if (this.zzcyi.zzx(zzyy().zzabz())) {
                this.zzcyi.start();
                zzeh("Connecting to service");
                if (this.zzcye.connect()) {
                    zzeh("Connected to service");
                    this.zzcyi.clear();
                    onServiceConnected();
                }
            }
        }
    }

    public void zzaaj() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r6 = this;
        com.google.android.gms.analytics.zzi.zzwu();
        r6.zzzg();
        r6.zzyv();
        r0 = r6.zzyy();
        r0 = r0.zzabe();
        if (r0 != 0) goto L_0x0018;
    L_0x0013:
        r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService";
        r6.zzek(r0);
    L_0x0018:
        r0 = r6.zzcye;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0026;
    L_0x0020:
        r0 = "Service not connected";
        r6.zzeh(r0);
    L_0x0025:
        return;
    L_0x0026:
        r0 = r6.zzcyb;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0025;
    L_0x002e:
        r0 = "Dispatching local hits to device AnalyticsService";
        r6.zzeh(r0);
    L_0x0033:
        r0 = r6.zzcyb;	 Catch:{ SQLiteException -> 0x004c }
        r1 = r6.zzyy();	 Catch:{ SQLiteException -> 0x004c }
        r1 = r1.zzabn();	 Catch:{ SQLiteException -> 0x004c }
        r2 = (long) r1;	 Catch:{ SQLiteException -> 0x004c }
        r1 = r0.zzr(r2);	 Catch:{ SQLiteException -> 0x004c }
        r0 = r1.isEmpty();	 Catch:{ SQLiteException -> 0x004c }
        if (r0 == 0) goto L_0x0062;	 Catch:{ SQLiteException -> 0x004c }
    L_0x0048:
        r6.zzaam();	 Catch:{ SQLiteException -> 0x004c }
        goto L_0x0025;
    L_0x004c:
        r0 = move-exception;
        r1 = "Failed to read hits from store";
        r6.zze(r1, r0);
        r6.zzaaq();
        goto L_0x0025;
    L_0x0056:
        r1.remove(r0);
        r2 = r6.zzcyb;	 Catch:{ SQLiteException -> 0x007b }
        r4 = r0.zzacy();	 Catch:{ SQLiteException -> 0x007b }
        r2.zzs(r4);	 Catch:{ SQLiteException -> 0x007b }
    L_0x0062:
        r0 = r1.isEmpty();
        if (r0 != 0) goto L_0x0033;
    L_0x0068:
        r0 = 0;
        r0 = r1.get(r0);
        r0 = (com.google.android.gms.analytics.internal.zzab) r0;
        r2 = r6.zzcye;
        r2 = r2.zzb(r0);
        if (r2 != 0) goto L_0x0056;
    L_0x0077:
        r6.zzaam();
        goto L_0x0025;
    L_0x007b:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r6.zze(r1, r0);
        r6.zzaaq();
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzl.zzaaj():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean zzaak() {
        /*
        r12 = this;
        r1 = 1;
        r2 = 0;
        com.google.android.gms.analytics.zzi.zzwu();
        r12.zzzg();
        r0 = "Dispatching a batch of local hits";
        r12.zzeh(r0);
        r0 = r12.zzcye;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0032;
    L_0x0015:
        r0 = r12.zzyy();
        r0 = r0.zzabc();
        if (r0 != 0) goto L_0x0032;
    L_0x001f:
        r0 = r1;
    L_0x0020:
        r3 = r12.zzcyc;
        r3 = r3.zzadj();
        if (r3 != 0) goto L_0x0034;
    L_0x0028:
        if (r0 == 0) goto L_0x0036;
    L_0x002a:
        if (r1 == 0) goto L_0x0036;
    L_0x002c:
        r0 = "No network or service available. Will retry later";
        r12.zzeh(r0);
    L_0x0031:
        return r2;
    L_0x0032:
        r0 = r2;
        goto L_0x0020;
    L_0x0034:
        r1 = r2;
        goto L_0x0028;
    L_0x0036:
        r0 = r12.zzyy();
        r0 = r0.zzabn();
        r1 = r12.zzyy();
        r1 = r1.zzabo();
        r0 = java.lang.Math.max(r0, r1);
        r6 = (long) r0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = 0;
    L_0x0052:
        r0 = r12.zzcyb;	 Catch:{ all -> 0x01eb }
        r0.beginTransaction();	 Catch:{ all -> 0x01eb }
        r3.clear();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x00d3 }
        r8 = r0.zzr(r6);	 Catch:{ SQLiteException -> 0x00d3 }
        r0 = r8.isEmpty();	 Catch:{ SQLiteException -> 0x00d3 }
        if (r0 == 0) goto L_0x0083;
    L_0x0066:
        r0 = "Store is empty, nothing to dispatch";
        r12.zzeh(r0);	 Catch:{ SQLiteException -> 0x00d3 }
        r12.zzaaq();	 Catch:{ SQLiteException -> 0x00d3 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x0079 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0079 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x0079 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0079 }
        goto L_0x0031;
    L_0x0079:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x0083:
        r0 = "Hits loaded from store. count";
        r1 = r8.size();	 Catch:{ SQLiteException -> 0x00d3 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x00d3 }
        r12.zza(r0, r1);	 Catch:{ SQLiteException -> 0x00d3 }
        r1 = r8.iterator();	 Catch:{ all -> 0x01eb }
    L_0x0094:
        r0 = r1.hasNext();	 Catch:{ all -> 0x01eb }
        if (r0 == 0) goto L_0x00f3;
    L_0x009a:
        r0 = r1.next();	 Catch:{ all -> 0x01eb }
        r0 = (com.google.android.gms.analytics.internal.zzab) r0;	 Catch:{ all -> 0x01eb }
        r10 = r0.zzacy();	 Catch:{ all -> 0x01eb }
        r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0094;
    L_0x00a8:
        r0 = "Database contains successfully uploaded hit";
        r1 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01eb }
        r3 = r8.size();	 Catch:{ all -> 0x01eb }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x01eb }
        r12.zzd(r0, r1, r3);	 Catch:{ all -> 0x01eb }
        r12.zzaaq();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x00c8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00c8 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x00c8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00c8 }
        goto L_0x0031;
    L_0x00c8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x00d3:
        r0 = move-exception;
        r1 = "Failed to read hits from persisted store";
        r12.zzd(r1, r0);	 Catch:{ all -> 0x01eb }
        r12.zzaaq();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x00e8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00e8 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x00e8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00e8 }
        goto L_0x0031;
    L_0x00e8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x00f3:
        r0 = r12.zzcye;	 Catch:{ all -> 0x01eb }
        r0 = r0.isConnected();	 Catch:{ all -> 0x01eb }
        if (r0 == 0) goto L_0x0202;
    L_0x00fb:
        r0 = r12.zzyy();	 Catch:{ all -> 0x01eb }
        r0 = r0.zzabc();	 Catch:{ all -> 0x01eb }
        if (r0 != 0) goto L_0x0202;
    L_0x0105:
        r0 = "Service connected, sending hits to the service";
        r12.zzeh(r0);	 Catch:{ all -> 0x01eb }
    L_0x010a:
        r0 = r8.isEmpty();	 Catch:{ all -> 0x01eb }
        if (r0 != 0) goto L_0x0202;
    L_0x0110:
        r0 = 0;
        r0 = r8.get(r0);	 Catch:{ all -> 0x01eb }
        r0 = (com.google.android.gms.analytics.internal.zzab) r0;	 Catch:{ all -> 0x01eb }
        r1 = r12.zzcye;	 Catch:{ all -> 0x01eb }
        r1 = r1.zzb(r0);	 Catch:{ all -> 0x01eb }
        if (r1 != 0) goto L_0x0148;
    L_0x011f:
        r0 = r4;
    L_0x0120:
        r4 = r12.zzcyc;	 Catch:{ all -> 0x01eb }
        r4 = r4.zzadj();	 Catch:{ all -> 0x01eb }
        if (r4 == 0) goto L_0x0196;
    L_0x0128:
        r4 = r12.zzcyc;	 Catch:{ all -> 0x01eb }
        r8 = r4.zzs(r8);	 Catch:{ all -> 0x01eb }
        r9 = r8.iterator();	 Catch:{ all -> 0x01eb }
        r4 = r0;
    L_0x0133:
        r0 = r9.hasNext();	 Catch:{ all -> 0x01eb }
        if (r0 == 0) goto L_0x018d;
    L_0x0139:
        r0 = r9.next();	 Catch:{ all -> 0x01eb }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x01eb }
        r0 = r0.longValue();	 Catch:{ all -> 0x01eb }
        r4 = java.lang.Math.max(r4, r0);	 Catch:{ all -> 0x01eb }
        goto L_0x0133;
    L_0x0148:
        r10 = r0.zzacy();	 Catch:{ all -> 0x01eb }
        r4 = java.lang.Math.max(r4, r10);	 Catch:{ all -> 0x01eb }
        r8.remove(r0);	 Catch:{ all -> 0x01eb }
        r1 = "Hit sent do device AnalyticsService for delivery";
        r12.zzb(r1, r0);	 Catch:{ all -> 0x01eb }
        r1 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x016d }
        r10 = r0.zzacy();	 Catch:{ SQLiteException -> 0x016d }
        r1.zzs(r10);	 Catch:{ SQLiteException -> 0x016d }
        r0 = r0.zzacy();	 Catch:{ SQLiteException -> 0x016d }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ SQLiteException -> 0x016d }
        r3.add(r0);	 Catch:{ SQLiteException -> 0x016d }
        goto L_0x010a;
    L_0x016d:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r12.zze(r1, r0);	 Catch:{ all -> 0x01eb }
        r12.zzaaq();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x0182 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0182 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x0182 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0182 }
        goto L_0x0031;
    L_0x0182:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x018d:
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01b3 }
        r0.zzq(r8);	 Catch:{ SQLiteException -> 0x01b3 }
        r3.addAll(r8);	 Catch:{ SQLiteException -> 0x01b3 }
        r0 = r4;
    L_0x0196:
        r4 = r3.isEmpty();	 Catch:{ all -> 0x01eb }
        if (r4 == 0) goto L_0x01d3;
    L_0x019c:
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01a8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01a8 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01a8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x01a8 }
        goto L_0x0031;
    L_0x01a8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x01b3:
        r0 = move-exception;
        r1 = "Failed to remove successfully uploaded hits";
        r12.zze(r1, r0);	 Catch:{ all -> 0x01eb }
        r12.zzaaq();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01c8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01c8 }
        r0 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01c8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x01c8 }
        goto L_0x0031;
    L_0x01c8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x01d3:
        r4 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01e0 }
        r4.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01e0 }
        r4 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01e0 }
        r4.endTransaction();	 Catch:{ SQLiteException -> 0x01e0 }
        r4 = r0;
        goto L_0x0052;
    L_0x01e0:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x01eb:
        r0 = move-exception;
        r1 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01f7 }
        r1.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01f7 }
        r1 = r12.zzcyb;	 Catch:{ SQLiteException -> 0x01f7 }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x01f7 }
        throw r0;
    L_0x01f7:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzaaq();
        goto L_0x0031;
    L_0x0202:
        r0 = r4;
        goto L_0x0120;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzl.zzaak():boolean");
    }

    public void zzaal() {
        zzi.zzwu();
        zzzg();
        zzei("Sync dispatching local hits");
        long j = this.zzcyj;
        if (!zzyy().zzabc()) {
            zzaai();
        }
        do {
            try {
            } catch (Throwable th) {
                zze("Sync local dispatch failed", th);
                zzaam();
                return;
            }
        } while (zzaak());
        zzzb().zzadq();
        zzaam();
        if (this.zzcyj != j) {
            this.zzcyd.zzadi();
        }
    }

    public void zzaam() {
        zzyu().zzwu();
        zzzg();
        if (!zzaan()) {
            this.zzcyd.unregister();
            zzaaq();
        } else if (this.zzcyb.isEmpty()) {
            this.zzcyd.unregister();
            zzaaq();
        } else {
            boolean z;
            if (((Boolean) zzy.u.get()).booleanValue()) {
                z = true;
            } else {
                this.zzcyd.zzadg();
                z = this.zzcyd.isConnected();
            }
            if (z) {
                zzaap();
                return;
            }
            zzaaq();
            zzaao();
        }
    }

    public long zzaat() {
        if (this.zzcyf != Long.MIN_VALUE) {
            return this.zzcyf;
        }
        return zzwe().zzact() ? ((long) zzwe().zzaek()) * 1000 : zzyy().zzabk();
    }

    public void zzaau() {
        zzzg();
        zzwu();
        this.zzcyk = true;
        this.zzcye.disconnect();
        zzaam();
    }

    public void zzas(boolean z) {
        zzaam();
    }

    public void zzb(zzw com_google_android_gms_analytics_internal_zzw) {
        zza(com_google_android_gms_analytics_internal_zzw, this.zzcyj);
    }

    protected void zzc(zzh com_google_android_gms_analytics_internal_zzh) {
        zzwu();
        zzb("Sending first hit to property", com_google_android_gms_analytics_internal_zzh.zzzp());
        if (!zzzb().zzado().zzx(zzyy().zzach())) {
            String zzadr = zzzb().zzadr();
            if (!TextUtils.isEmpty(zzadr)) {
                zzlv zza = zzao.zza(zzyx(), zzadr);
                zzb("Found relevant installation campaign", zza);
                zza(com_google_android_gms_analytics_internal_zzh, zza);
            }
        }
    }

    public void zzep(String str) {
        zzab.zzhr(str);
        zzwu();
        zzyv();
        zzlv zza = zzao.zza(zzyx(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        CharSequence zzadr = zzzb().zzadr();
        if (str.equals(zzadr)) {
            zzek("Ignoring duplicate install campaign");
        } else if (TextUtils.isEmpty(zzadr)) {
            zzzb().zzeu(str);
            if (zzzb().zzado().zzx(zzyy().zzach())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzh zza2 : this.zzcyb.zzt(0)) {
                zza(zza2, zza);
            }
        } else {
            zzd("Ignoring multiple install campaigns. original, new", zzadr, str);
        }
    }

    zzab zzf(zzab com_google_android_gms_analytics_internal_zzab) {
        if (!TextUtils.isEmpty(com_google_android_gms_analytics_internal_zzab.zzadd())) {
            return com_google_android_gms_analytics_internal_zzab;
        }
        Pair zzadv = zzzb().zzads().zzadv();
        if (zzadv == null) {
            return com_google_android_gms_analytics_internal_zzab;
        }
        Long l = (Long) zzadv.second;
        String str = (String) zzadv.first;
        String valueOf = String.valueOf(l);
        valueOf = new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
        Map hashMap = new HashMap(com_google_android_gms_analytics_internal_zzab.zzm());
        hashMap.put("_m", valueOf);
        return zzab.zza(this, com_google_android_gms_analytics_internal_zzab, hashMap);
    }

    public void zzu(long j) {
        zzi.zzwu();
        zzzg();
        if (j < 0) {
            j = 0;
        }
        this.zzcyf = j;
        zzaam();
    }

    protected void zzwv() {
        this.zzcyb.initialize();
        this.zzcyc.initialize();
        this.zzcye.initialize();
    }

    public void zzyo() {
        zzi.zzwu();
        zzzg();
        if (!zzyy().zzabc()) {
            zzeh("Delete all hits from local store");
            try {
                this.zzcyb.zzzw();
                this.zzcyb.zzzx();
                zzaam();
            } catch (SQLiteException e) {
                zzd("Failed to delete hits from store", e);
            }
        }
        zzaai();
        if (this.zzcye.zzzs()) {
            zzeh("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    public void zzyr() {
        zzi.zzwu();
        zzzg();
        zzeh("Service disconnected");
    }

    void zzyt() {
        zzwu();
        this.zzcyj = zzyw().currentTimeMillis();
    }

    public long zzzz() {
        zzi.zzwu();
        zzzg();
        try {
            return this.zzcyb.zzzz();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }
}
