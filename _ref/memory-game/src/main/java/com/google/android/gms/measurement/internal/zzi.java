package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzab;

class zzi {
    final long aiC;
    final long aiD;
    final long aiE;
    final String mName;
    final String zzcjf;

    zzi(String str, String str2, long j, long j2, long j3) {
        boolean z = true;
        zzab.zzhr(str);
        zzab.zzhr(str2);
        zzab.zzbo(j >= 0);
        if (j2 < 0) {
            z = false;
        }
        zzab.zzbo(z);
        this.zzcjf = str;
        this.mName = str2;
        this.aiC = j;
        this.aiD = j2;
        this.aiE = j3;
    }

    zzi zzbj(long j) {
        return new zzi(this.zzcjf, this.mName, this.aiC, this.aiD, j);
    }

    zzi zzbsr() {
        return new zzi(this.zzcjf, this.mName, this.aiC + 1, this.aiD + 1, this.aiE);
    }
}
