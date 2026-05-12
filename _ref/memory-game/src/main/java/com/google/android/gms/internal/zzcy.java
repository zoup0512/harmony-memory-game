package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzu;

@zzin
public abstract class zzcy<T> {
    private final int zzaxo;
    private final String zzaxp;
    private final T zzaxq;

    private zzcy(int i, String str, T t) {
        this.zzaxo = i;
        this.zzaxp = str;
        this.zzaxq = t;
        zzu.zzfy().zza(this);
    }

    public static zzcy<String> zza(int i, String str) {
        zzcy<String> zza = zza(i, str, null);
        zzu.zzfy().zzb(zza);
        return zza;
    }

    public static zzcy<Integer> zza(int i, String str, int i2) {
        return new 2(i, str, Integer.valueOf(i2));
    }

    public static zzcy<Long> zza(int i, String str, long j) {
        return new 3(i, str, Long.valueOf(j));
    }

    public static zzcy<Boolean> zza(int i, String str, Boolean bool) {
        return new 1(i, str, bool);
    }

    public static zzcy<String> zza(int i, String str, String str2) {
        return new 4(i, str, str2);
    }

    public static zzcy<String> zzb(int i, String str) {
        zzcy<String> zza = zza(i, str, null);
        zzu.zzfy().zzc(zza);
        return zza;
    }

    public T get() {
        return zzu.zzfz().zzd(this);
    }

    public String getKey() {
        return this.zzaxp;
    }

    protected abstract T zza(SharedPreferences sharedPreferences);

    public T zzjw() {
        return this.zzaxq;
    }
}
