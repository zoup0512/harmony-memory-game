package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.Map;

@zzin
public abstract class zzdh {
    @zzin
    public static final zzdh zzbdy = new 1();
    @zzin
    public static final zzdh zzbdz = new 2();
    @zzin
    public static final zzdh zzbea = new 3();

    public final void zza(Map<String, String> map, String str, String str2) {
        map.put(str, zzg((String) map.get(str), str2));
    }

    public abstract String zzg(@Nullable String str, String str2);
}
