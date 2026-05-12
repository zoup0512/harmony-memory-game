package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.util.zze;

public class zzad {
    private final long Q;
    private final int R;
    private double S;
    private long T;
    private final Object U;
    private final zze zzaoc;
    private final String zzcvc;

    public zzad(int i, long j, String str, zze com_google_android_gms_common_util_zze) {
        this.U = new Object();
        this.R = i;
        this.S = (double) this.R;
        this.Q = j;
        this.zzcvc = str;
        this.zzaoc = com_google_android_gms_common_util_zze;
    }

    public zzad(String str, zze com_google_android_gms_common_util_zze) {
        this(60, 2000, str, com_google_android_gms_common_util_zze);
    }

    public boolean zzade() {
        boolean z;
        synchronized (this.U) {
            long currentTimeMillis = this.zzaoc.currentTimeMillis();
            if (this.S < ((double) this.R)) {
                double d = ((double) (currentTimeMillis - this.T)) / ((double) this.Q);
                if (d > 0.0d) {
                    this.S = Math.min((double) this.R, d + this.S);
                }
            }
            this.T = currentTimeMillis;
            if (this.S >= 1.0d) {
                this.S -= 1.0d;
                z = true;
            } else {
                String str = this.zzcvc;
                zzae.zzcx(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                z = false;
            }
        }
        return z;
    }
}
