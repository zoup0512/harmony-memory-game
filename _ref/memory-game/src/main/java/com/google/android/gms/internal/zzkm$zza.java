package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;

public class zzkm$zza {
    public final int count;
    public final String name;
    public final double zzclw;
    public final double zzclx;
    public final double zzcly;

    public zzkm$zza(String str, double d, double d2, double d3, int i) {
        this.name = str;
        this.zzclx = d;
        this.zzclw = d2;
        this.zzcly = d3;
        this.count = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzkm$zza)) {
            return false;
        }
        zzkm$zza com_google_android_gms_internal_zzkm_zza = (zzkm$zza) obj;
        return zzaa.equal(this.name, com_google_android_gms_internal_zzkm_zza.name) && this.zzclw == com_google_android_gms_internal_zzkm_zza.zzclw && this.zzclx == com_google_android_gms_internal_zzkm_zza.zzclx && this.count == com_google_android_gms_internal_zzkm_zza.count && Double.compare(this.zzcly, com_google_android_gms_internal_zzkm_zza.zzcly) == 0;
    }

    public int hashCode() {
        return zzaa.hashCode(this.name, Double.valueOf(this.zzclw), Double.valueOf(this.zzclx), Double.valueOf(this.zzcly), Integer.valueOf(this.count));
    }

    public String toString() {
        return zzaa.zzx(this).zzg("name", this.name).zzg("minBound", Double.valueOf(this.zzclx)).zzg("maxBound", Double.valueOf(this.zzclw)).zzg("percent", Double.valueOf(this.zzcly)).zzg("count", Integer.valueOf(this.count)).toString();
    }
}
