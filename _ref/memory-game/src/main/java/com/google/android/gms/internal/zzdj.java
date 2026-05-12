package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zzin
public class zzdj {
    @Nullable
    private final zzdk zzajn;
    private final Map<String, zzdi> zzbee = new HashMap();

    public zzdj(@Nullable zzdk com_google_android_gms_internal_zzdk) {
        this.zzajn = com_google_android_gms_internal_zzdk;
    }

    public void zza(String str, zzdi com_google_android_gms_internal_zzdi) {
        this.zzbee.put(str, com_google_android_gms_internal_zzdi);
    }

    public void zza(String str, String str2, long j) {
        zzdg.zza(this.zzajn, (zzdi) this.zzbee.get(str2), j, str);
        this.zzbee.put(str, zzdg.zza(this.zzajn, j));
    }

    @Nullable
    public zzdk zzkf() {
        return this.zzajn;
    }
}
