package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;

public class zzaf extends zzaa {
    private long amh;
    private final Runnable ami = new Runnable(this) {
        final /* synthetic */ zzaf aml;

        {
            this.aml = r1;
        }

        @MainThread
        public void run() {
            this.aml.zzbsc().zzm(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 amm;

                {
                    this.amm = r1;
                }

                public void run() {
                    this.amm.aml.zzbva();
                }
            });
        }
    };
    private final zzf amj = new zzf(this, this.ahD) {
        final /* synthetic */ zzaf aml;

        @WorkerThread
        public void run() {
            this.aml.zzbvb();
        }
    };
    private final zzf amk = new zzf(this, this.ahD) {
        final /* synthetic */ zzaf aml;

        @WorkerThread
        public void run() {
            this.aml.zzbvc();
        }
    };
    private Handler mHandler;

    zzaf(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    @WorkerThread
    private void zzbm(long j) {
        zzwu();
        zzbuy();
        this.amj.cancel();
        this.amk.cancel();
        zzbsd().zzbtc().zzj("Activity resumed, time", Long.valueOf(j));
        this.amh = j;
        if (zzyw().currentTimeMillis() - zzbse().aki.get() > zzbse().akk.get()) {
            zzbse().akj.set(true);
            zzbse().akl.set(0);
        }
        if (zzbse().akj.get()) {
            this.amj.zzv(Math.max(0, zzbse().akh.get() - zzbse().akl.get()));
        } else {
            this.amk.zzv(Math.max(0, 3600000 - zzbse().akl.get()));
        }
    }

    @WorkerThread
    private void zzbn(long j) {
        zzwu();
        zzbuy();
        this.amj.cancel();
        this.amk.cancel();
        zzbsd().zzbtc().zzj("Activity paused, time", Long.valueOf(j));
        if (this.amh != 0) {
            zzbse().akl.set(zzbse().akl.get() + (j - this.amh));
        }
        zzbse().akk.set(zzyw().currentTimeMillis());
        synchronized (this) {
            if (!zzbse().akj.get()) {
                this.mHandler.postDelayed(this.ami, 1000);
            }
        }
    }

    private void zzbuy() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    @WorkerThread
    private void zzbvb() {
        zzwu();
        zzbsd().zzbtc().zzj("Session started, time", Long.valueOf(zzyw().elapsedRealtime()));
        zzbse().akj.set(false);
        zzbru().zze("auto", "_s", new Bundle());
    }

    @WorkerThread
    private void zzbvc() {
        zzwu();
        long elapsedRealtime = zzyw().elapsedRealtime();
        if (this.amh == 0) {
            this.amh = elapsedRealtime - 3600000;
        }
        long j = zzbse().akl.get() + (elapsedRealtime - this.amh);
        zzbse().akl.set(j);
        zzbsd().zzbtc().zzj("Recording user engagement, ms", Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j);
        zzbru().zze("auto", "_e", bundle);
        zzbse().akl.set(0);
        this.amh = elapsedRealtime;
        this.amk.zzv(Math.max(0, 3600000 - zzbse().akl.get()));
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzbrs() {
        super.zzbrs();
    }

    public /* bridge */ /* synthetic */ zzc zzbrt() {
        return super.zzbrt();
    }

    public /* bridge */ /* synthetic */ zzac zzbru() {
        return super.zzbru();
    }

    public /* bridge */ /* synthetic */ zzn zzbrv() {
        return super.zzbrv();
    }

    public /* bridge */ /* synthetic */ zzg zzbrw() {
        return super.zzbrw();
    }

    public /* bridge */ /* synthetic */ zzad zzbrx() {
        return super.zzbrx();
    }

    public /* bridge */ /* synthetic */ zze zzbry() {
        return super.zzbry();
    }

    public /* bridge */ /* synthetic */ zzal zzbrz() {
        return super.zzbrz();
    }

    public /* bridge */ /* synthetic */ zzv zzbsa() {
        return super.zzbsa();
    }

    public /* bridge */ /* synthetic */ zzaf zzbsb() {
        return super.zzbsb();
    }

    public /* bridge */ /* synthetic */ zzw zzbsc() {
        return super.zzbsc();
    }

    public /* bridge */ /* synthetic */ zzp zzbsd() {
        return super.zzbsd();
    }

    public /* bridge */ /* synthetic */ zzt zzbse() {
        return super.zzbse();
    }

    public /* bridge */ /* synthetic */ zzd zzbsf() {
        return super.zzbsf();
    }

    @MainThread
    protected void zzbux() {
        synchronized (this) {
            zzbuy();
            this.mHandler.removeCallbacks(this.ami);
        }
        final long elapsedRealtime = zzyw().elapsedRealtime();
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzaf aml;

            public void run() {
                this.aml.zzbm(elapsedRealtime);
            }
        });
    }

    @MainThread
    protected void zzbuz() {
        final long elapsedRealtime = zzyw().elapsedRealtime();
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzaf aml;

            public void run() {
                this.aml.zzbn(elapsedRealtime);
            }
        });
    }

    @WorkerThread
    public void zzbva() {
        zzwu();
        zzbsd().zzbtb().log("Application backgrounded. Logging engagement");
        long j = zzbse().akl.get();
        if (j > 0) {
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzbru().zze("auto", "_e", bundle);
            zzbse().akl.set(0);
            return;
        }
        zzbsd().zzbsx().zzj("Not logging non-positive engagement time", Long.valueOf(j));
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    protected void zzwv() {
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}
