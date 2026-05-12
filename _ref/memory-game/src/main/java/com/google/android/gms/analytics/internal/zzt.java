package com.google.android.gms.analytics.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzab;

abstract class zzt {
    private static volatile Handler zzczf;
    private final zzf zzcwp;
    private volatile long zzczg;
    private final Runnable zzw = new Runnable(this) {
        final /* synthetic */ zzt zzczh;

        {
            this.zzczh = r1;
        }

        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.zzczh.zzcwp.zzyz().zzg(this);
                return;
            }
            boolean zzfc = this.zzczh.zzfc();
            this.zzczh.zzczg = 0;
            if (zzfc && !false) {
                this.zzczh.run();
            }
        }
    };

    zzt(zzf com_google_android_gms_analytics_internal_zzf) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzf);
        this.zzcwp = com_google_android_gms_analytics_internal_zzf;
    }

    private Handler getHandler() {
        if (zzczf != null) {
            return zzczf;
        }
        Handler handler;
        synchronized (zzt.class) {
            if (zzczf == null) {
                zzczf = new Handler(this.zzcwp.getContext().getMainLooper());
            }
            handler = zzczf;
        }
        return handler;
    }

    public void cancel() {
        this.zzczg = 0;
        getHandler().removeCallbacks(this.zzw);
    }

    public abstract void run();

    public long zzacj() {
        return this.zzczg == 0 ? 0 : Math.abs(this.zzcwp.zzyw().currentTimeMillis() - this.zzczg);
    }

    public boolean zzfc() {
        return this.zzczg != 0;
    }

    public void zzv(long j) {
        cancel();
        if (j >= 0) {
            this.zzczg = this.zzcwp.zzyw().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzw, j)) {
                this.zzcwp.zzyx().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public void zzw(long j) {
        long j2 = 0;
        if (!zzfc()) {
            return;
        }
        if (j < 0) {
            cancel();
            return;
        }
        long abs = j - Math.abs(this.zzcwp.zzyw().currentTimeMillis() - this.zzczg);
        if (abs >= 0) {
            j2 = abs;
        }
        getHandler().removeCallbacks(this.zzw);
        if (!getHandler().postDelayed(this.zzw, j2)) {
            this.zzcwp.zzyx().zze("Failed to adjust delayed post. time", Long.valueOf(j2));
        }
    }
}
