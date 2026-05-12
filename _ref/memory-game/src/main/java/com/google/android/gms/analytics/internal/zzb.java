package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzb extends zzd {
    private final zzl zzcwh;

    public zzb(zzf com_google_android_gms_analytics_internal_zzf, zzg com_google_android_gms_analytics_internal_zzg) {
        super(com_google_android_gms_analytics_internal_zzf);
        zzab.zzy(com_google_android_gms_analytics_internal_zzg);
        this.zzcwh = com_google_android_gms_analytics_internal_zzg.zzj(com_google_android_gms_analytics_internal_zzf);
    }

    void onServiceConnected() {
        zzwu();
        this.zzcwh.onServiceConnected();
    }

    public void setLocalDispatchPeriod(final int i) {
        zzzg();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzb zzcwj;

            public void run() {
                this.zzcwj.zzcwh.zzu(((long) i) * 1000);
            }
        });
    }

    public void start() {
        this.zzcwh.start();
    }

    public long zza(zzh com_google_android_gms_analytics_internal_zzh) {
        zzzg();
        zzab.zzy(com_google_android_gms_analytics_internal_zzh);
        zzwu();
        long zza = this.zzcwh.zza(com_google_android_gms_analytics_internal_zzh, true);
        if (zza == 0) {
            this.zzcwh.zzc(com_google_android_gms_analytics_internal_zzh);
        }
        return zza;
    }

    public void zza(final zzab com_google_android_gms_analytics_internal_zzab) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzab);
        zzzg();
        zzb("Hit delivery requested", com_google_android_gms_analytics_internal_zzab);
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzb zzcwj;

            public void run() {
                this.zzcwj.zzcwh.zza(com_google_android_gms_analytics_internal_zzab);
            }
        });
    }

    public void zza(final zzw com_google_android_gms_analytics_internal_zzw) {
        zzzg();
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzb zzcwj;

            public void run() {
                this.zzcwj.zzcwh.zzb(com_google_android_gms_analytics_internal_zzw);
            }
        });
    }

    public void zza(final String str, final Runnable runnable) {
        zzab.zzh(str, "campaign param can't be empty");
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzb zzcwj;

            public void run() {
                this.zzcwj.zzcwh.zzep(str);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public void zzas(final boolean z) {
        zza("Network connectivity status changed", Boolean.valueOf(z));
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzb zzcwj;

            public void run() {
                this.zzcwj.zzcwh.zzas(z);
            }
        });
    }

    protected void zzwv() {
        this.zzcwh.initialize();
    }

    public void zzyo() {
        zzzg();
        zzyv();
        zzyz().zzg(new Runnable(this) {
            final /* synthetic */ zzb zzcwj;

            {
                this.zzcwj = r1;
            }

            public void run() {
                this.zzcwj.zzcwh.zzyo();
            }
        });
    }

    public void zzyp() {
        zzzg();
        Context context = getContext();
        if (zzaj.zzav(context) && zzak.zzaw(context)) {
            Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            context.startService(intent);
            return;
        }
        zza(null);
    }

    public boolean zzyq() {
        zzzg();
        try {
            zzyz().zzc(new Callable<Void>(this) {
                final /* synthetic */ zzb zzcwj;

                {
                    this.zzcwj = r1;
                }

                public /* synthetic */ Object call() throws Exception {
                    return zzcx();
                }

                public Void zzcx() throws Exception {
                    this.zzcwj.zzcwh.zzaal();
                    return null;
                }
            }).get(4, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public void zzyr() {
        zzzg();
        zzi.zzwu();
        this.zzcwh.zzyr();
    }

    public void zzys() {
        zzeh("Radio powered up");
        zzyp();
    }

    void zzyt() {
        zzwu();
        this.zzcwh.zzyt();
    }
}
