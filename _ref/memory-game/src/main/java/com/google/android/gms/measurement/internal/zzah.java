package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

class zzah {
    private final zze zzaoc;
    private long zzbou;

    public zzah(zze com_google_android_gms_common_util_zze) {
        zzab.zzy(com_google_android_gms_common_util_zze);
        this.zzaoc = com_google_android_gms_common_util_zze;
    }

    public void clear() {
        this.zzbou = 0;
    }

    public void start() {
        this.zzbou = this.zzaoc.elapsedRealtime();
    }

    public boolean zzx(long j) {
        return this.zzbou == 0 || this.zzaoc.elapsedRealtime() - this.zzbou >= j;
    }
}
