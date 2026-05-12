package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

class zzcz implements zzck {
    private final long Q;
    private final int R;
    private double S;
    private final Object U;
    private long axM;
    private final zze zzaoc;

    public zzcz() {
        this(60, 2000);
    }

    public zzcz(int i, long j) {
        this.U = new Object();
        this.R = i;
        this.S = (double) this.R;
        this.Q = j;
        this.zzaoc = zzh.zzavm();
    }

    public boolean zzade() {
        boolean z;
        synchronized (this.U) {
            long currentTimeMillis = this.zzaoc.currentTimeMillis();
            if (this.S < ((double) this.R)) {
                double d = ((double) (currentTimeMillis - this.axM)) / ((double) this.Q);
                if (d > 0.0d) {
                    this.S = Math.min((double) this.R, d + this.S);
                }
            }
            this.axM = currentTimeMillis;
            if (this.S >= 1.0d) {
                this.S -= 1.0d;
                z = true;
            } else {
                zzbn.zzcx("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
