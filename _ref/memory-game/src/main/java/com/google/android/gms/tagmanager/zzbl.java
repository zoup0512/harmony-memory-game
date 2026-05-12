package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;

class zzbl implements zzck {
    private final long Q;
    private final int R;
    private double S;
    private long T;
    private final Object U = new Object();
    private final long aws;
    private final zze zzaoc;
    private final String zzcvc;

    public zzbl(int i, long j, long j2, String str, zze com_google_android_gms_common_util_zze) {
        this.R = i;
        this.S = (double) this.R;
        this.Q = j;
        this.aws = j2;
        this.zzcvc = str;
        this.zzaoc = com_google_android_gms_common_util_zze;
    }

    public boolean zzade() {
        boolean z = false;
        synchronized (this.U) {
            long currentTimeMillis = this.zzaoc.currentTimeMillis();
            String str;
            if (currentTimeMillis - this.T < this.aws) {
                str = this.zzcvc;
                zzbn.zzcx(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
            } else {
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
                    str = this.zzcvc;
                    zzbn.zzcx(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                }
            }
        }
        return z;
    }
}
