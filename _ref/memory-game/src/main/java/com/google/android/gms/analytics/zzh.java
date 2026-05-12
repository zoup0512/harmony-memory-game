package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;

public abstract class zzh<T extends zzh> {
    private final zzi zzctf;
    protected final zze zzctg;
    private final List<zzf> zzcth = new ArrayList();

    protected zzh(zzi com_google_android_gms_analytics_zzi, zze com_google_android_gms_common_util_zze) {
        zzab.zzy(com_google_android_gms_analytics_zzi);
        this.zzctf = com_google_android_gms_analytics_zzi;
        zze com_google_android_gms_analytics_zze = new zze(this, com_google_android_gms_common_util_zze);
        com_google_android_gms_analytics_zze.zzwp();
        this.zzctg = com_google_android_gms_analytics_zze;
    }

    protected void zza(zze com_google_android_gms_analytics_zze) {
    }

    protected void zzd(zze com_google_android_gms_analytics_zze) {
        for (zzf zza : this.zzcth) {
            zza.zza(this, com_google_android_gms_analytics_zze);
        }
    }

    public zze zzvr() {
        zze zzwf = this.zzctg.zzwf();
        zzd(zzwf);
        return zzwf;
    }

    protected zzi zzwn() {
        return this.zzctf;
    }

    public zze zzwq() {
        return this.zzctg;
    }

    public List<zzk> zzwr() {
        return this.zzctg.zzwh();
    }
}
