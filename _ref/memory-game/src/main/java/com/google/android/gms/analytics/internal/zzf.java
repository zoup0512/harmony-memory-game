package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.lang.Thread.UncaughtExceptionHandler;

public class zzf {
    private static zzf zzcws;
    private final Context mContext;
    private final zze zzaoc;
    private final Context zzcwt;
    private final zzr zzcwu;
    private final zzaf zzcwv;
    private final zzi zzcww;
    private final zzb zzcwx;
    private final zzv zzcwy;
    private final zzap zzcwz;
    private final zzai zzcxa;
    private final GoogleAnalytics zzcxb;
    private final zzn zzcxc;
    private final zza zzcxd;
    private final zzk zzcxe;
    private final zzu zzcxf;

    protected zzf(zzg com_google_android_gms_analytics_internal_zzg) {
        Object applicationContext = com_google_android_gms_analytics_internal_zzg.getApplicationContext();
        zzab.zzb(applicationContext, (Object) "Application context can't be null");
        Context zzzi = com_google_android_gms_analytics_internal_zzg.zzzi();
        zzab.zzy(zzzi);
        this.mContext = applicationContext;
        this.zzcwt = zzzi;
        this.zzaoc = com_google_android_gms_analytics_internal_zzg.zzh(this);
        this.zzcwu = com_google_android_gms_analytics_internal_zzg.zzg(this);
        zzaf zzf = com_google_android_gms_analytics_internal_zzg.zzf(this);
        zzf.initialize();
        this.zzcwv = zzf;
        String str;
        if (zzyy().zzabc()) {
            zzf = zzyx();
            str = zze.VERSION;
            zzf.zzej(new StringBuilder(String.valueOf(str).length() + 33).append("Google Analytics ").append(str).append(" is starting up.").toString());
        } else {
            zzf = zzyx();
            str = zze.VERSION;
            zzf.zzej(new StringBuilder(String.valueOf(str).length() + 134).append("Google Analytics ").append(str).append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4").toString());
        }
        zzai zzq = com_google_android_gms_analytics_internal_zzg.zzq(this);
        zzq.initialize();
        this.zzcxa = zzq;
        zzap zze = com_google_android_gms_analytics_internal_zzg.zze(this);
        zze.initialize();
        this.zzcwz = zze;
        zzb zzl = com_google_android_gms_analytics_internal_zzg.zzl(this);
        zzn zzd = com_google_android_gms_analytics_internal_zzg.zzd(this);
        zza zzc = com_google_android_gms_analytics_internal_zzg.zzc(this);
        zzk zzb = com_google_android_gms_analytics_internal_zzg.zzb(this);
        zzu zza = com_google_android_gms_analytics_internal_zzg.zza(this);
        zzi zzaz = com_google_android_gms_analytics_internal_zzg.zzaz(applicationContext);
        zzaz.zza(zzzh());
        this.zzcww = zzaz;
        GoogleAnalytics zzi = com_google_android_gms_analytics_internal_zzg.zzi(this);
        zzd.initialize();
        this.zzcxc = zzd;
        zzc.initialize();
        this.zzcxd = zzc;
        zzb.initialize();
        this.zzcxe = zzb;
        zza.initialize();
        this.zzcxf = zza;
        zzv zzp = com_google_android_gms_analytics_internal_zzg.zzp(this);
        zzp.initialize();
        this.zzcwy = zzp;
        zzl.initialize();
        this.zzcwx = zzl;
        if (zzyy().zzabc()) {
            zzyx().zzb("Device AnalyticsService version", zze.VERSION);
        }
        zzi.initialize();
        this.zzcxb = zzi;
        zzl.start();
    }

    private void zza(zzd com_google_android_gms_analytics_internal_zzd) {
        zzab.zzb((Object) com_google_android_gms_analytics_internal_zzd, (Object) "Analytics service not created/initialized");
        zzab.zzb(com_google_android_gms_analytics_internal_zzd.isInitialized(), (Object) "Analytics service not initialized");
    }

    public static zzf zzay(Context context) {
        zzab.zzy(context);
        if (zzcws == null) {
            synchronized (zzf.class) {
                if (zzcws == null) {
                    zze zzavm = zzh.zzavm();
                    long elapsedRealtime = zzavm.elapsedRealtime();
                    zzf com_google_android_gms_analytics_internal_zzf = new zzf(new zzg(context));
                    zzcws = com_google_android_gms_analytics_internal_zzf;
                    GoogleAnalytics.zzwa();
                    elapsedRealtime = zzavm.elapsedRealtime() - elapsedRealtime;
                    long longValue = ((Long) zzy.D.get()).longValue();
                    if (elapsedRealtime > longValue) {
                        com_google_android_gms_analytics_internal_zzf.zzyx().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzcws;
    }

    public Context getContext() {
        return this.mContext;
    }

    public zzb zzwd() {
        zza(this.zzcwx);
        return this.zzcwx;
    }

    public zzap zzwe() {
        zza(this.zzcwz);
        return this.zzcwz;
    }

    public void zzwu() {
        zzi.zzwu();
    }

    public zze zzyw() {
        return this.zzaoc;
    }

    public zzaf zzyx() {
        zza(this.zzcwv);
        return this.zzcwv;
    }

    public zzr zzyy() {
        return this.zzcwu;
    }

    public zzi zzyz() {
        zzab.zzy(this.zzcww);
        return this.zzcww;
    }

    public zzv zzza() {
        zza(this.zzcwy);
        return this.zzcwy;
    }

    public zzai zzzb() {
        zza(this.zzcxa);
        return this.zzcxa;
    }

    public zzk zzze() {
        zza(this.zzcxe);
        return this.zzcxe;
    }

    public zzu zzzf() {
        return this.zzcxf;
    }

    protected UncaughtExceptionHandler zzzh() {
        return new UncaughtExceptionHandler(this) {
            final /* synthetic */ zzf zzcxg;

            {
                this.zzcxg = r1;
            }

            public void uncaughtException(Thread thread, Throwable th) {
                zzaf zzzj = this.zzcxg.zzzj();
                if (zzzj != null) {
                    zzzj.zze("Job execution failed", th);
                }
            }
        };
    }

    public Context zzzi() {
        return this.zzcwt;
    }

    public zzaf zzzj() {
        return this.zzcwv;
    }

    public GoogleAnalytics zzzk() {
        zzab.zzy(this.zzcxb);
        zzab.zzb(this.zzcxb.isInitialized(), (Object) "Analytics instance not initialized");
        return this.zzcxb;
    }

    public zzai zzzl() {
        return (this.zzcxa == null || !this.zzcxa.isInitialized()) ? null : this.zzcxa;
    }

    public zza zzzm() {
        zza(this.zzcxd);
        return this.zzcxd;
    }

    public zzn zzzn() {
        zza(this.zzcxc);
        return this.zzcxc;
    }
}
