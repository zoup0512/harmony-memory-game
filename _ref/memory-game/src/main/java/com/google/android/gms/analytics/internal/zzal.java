package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

class zzal {
    private final zze zzaoc;
    private long zzbou;

    public zzal(zze com_google_android_gms_common_util_zze) {
        zzab.zzy(com_google_android_gms_common_util_zze);
        this.zzaoc = com_google_android_gms_common_util_zze;
    }

    public zzal(zze com_google_android_gms_common_util_zze, long j) {
        zzab.zzy(com_google_android_gms_common_util_zze);
        this.zzaoc = com_google_android_gms_common_util_zze;
        this.zzbou = j;
    }

    public void clear() {
        this.zzbou = 0;
    }

    public void start() {
        this.zzbou = this.zzaoc.elapsedRealtime();
    }

    public boolean zzx(long j) {
        return this.zzbou == 0 || this.zzaoc.elapsedRealtime() - this.zzbou > j;
    }
}
