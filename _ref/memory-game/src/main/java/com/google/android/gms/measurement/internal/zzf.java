package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzab;

abstract class zzf {
    private static volatile Handler zzczf;
    private final zzx ahD;
    private boolean aiu = true;
    private volatile long zzczg;
    private final Runnable zzw = new Runnable(this) {
        final /* synthetic */ zzf aiv;

        {
            this.aiv = r1;
        }

        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.aiv.ahD.zzbsc().zzm(this);
                return;
            }
            boolean zzfc = this.aiv.zzfc();
            this.aiv.zzczg = 0;
            if (zzfc && this.aiv.aiu) {
                this.aiv.run();
            }
        }
    };

    zzf(zzx com_google_android_gms_measurement_internal_zzx) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzx);
        this.ahD = com_google_android_gms_measurement_internal_zzx;
    }

    private Handler getHandler() {
        if (zzczf != null) {
            return zzczf;
        }
        Handler handler;
        synchronized (zzf.class) {
            if (zzczf == null) {
                zzczf = new Handler(this.ahD.getContext().getMainLooper());
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

    public boolean zzfc() {
        return this.zzczg != 0;
    }

    public void zzv(long j) {
        cancel();
        if (j >= 0) {
            this.zzczg = this.ahD.zzyw().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzw, j)) {
                this.ahD.zzbsd().zzbsv().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
