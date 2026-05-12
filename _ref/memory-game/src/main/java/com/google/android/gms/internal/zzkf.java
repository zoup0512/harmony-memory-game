package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Future;

@zzin
public final class zzkf {
    public static Future zza(Context context, zzb com_google_android_gms_internal_zzkf_zzb) {
        return (Future) new 5(context, com_google_android_gms_internal_zzkf_zzb).zzpy();
    }

    public static Future zza(Context context, String str, long j) {
        return (Future) new 3(context, str, j).zzpy();
    }

    public static Future zzb(Context context, zzb com_google_android_gms_internal_zzkf_zzb) {
        return (Future) new 6(context, com_google_android_gms_internal_zzkf_zzb).zzpy();
    }

    public static Future zzc(Context context, zzb com_google_android_gms_internal_zzkf_zzb) {
        return (Future) new 8(context, com_google_android_gms_internal_zzkf_zzb).zzpy();
    }

    public static Future zzc(Context context, boolean z) {
        return (Future) new 1(context, z).zzpy();
    }

    public static Future zzd(Context context, zzb com_google_android_gms_internal_zzkf_zzb) {
        return (Future) new 10(context, com_google_android_gms_internal_zzkf_zzb).zzpy();
    }

    public static Future zze(Context context, zzb com_google_android_gms_internal_zzkf_zzb) {
        return (Future) new 2(context, com_google_android_gms_internal_zzkf_zzb).zzpy();
    }

    public static Future zze(Context context, boolean z) {
        return (Future) new 7(context, z).zzpy();
    }

    public static Future zzf(Context context, zzb com_google_android_gms_internal_zzkf_zzb) {
        return (Future) new 4(context, com_google_android_gms_internal_zzkf_zzb).zzpy();
    }

    public static Future zzf(Context context, String str) {
        return (Future) new 9(context, str).zzpy();
    }

    public static Future zzf(Context context, boolean z) {
        return (Future) new 11(context, z).zzpy();
    }

    public static SharedPreferences zzn(Context context) {
        return context.getSharedPreferences("admob", 0);
    }
}
