package com.google.android.gms.ads.internal.util.client;

import android.util.Log;
import com.google.android.gms.internal.zzin;

@zzin
public class zzb {
    public static void e(String str) {
        if (zzaz(6)) {
            Log.e("Ads", str);
        }
    }

    public static void zza(String str, Throwable th) {
        if (zzaz(3)) {
            Log.d("Ads", str, th);
        }
    }

    public static boolean zzaz(int i) {
        return i >= 5 || Log.isLoggable("Ads", i);
    }

    public static void zzb(String str, Throwable th) {
        if (zzaz(6)) {
            Log.e("Ads", str, th);
        }
    }

    public static void zzc(String str, Throwable th) {
        if (zzaz(4)) {
            Log.i("Ads", str, th);
        }
    }

    public static void zzcv(String str) {
        if (zzaz(3)) {
            Log.d("Ads", str);
        }
    }

    public static void zzcw(String str) {
        if (zzaz(4)) {
            Log.i("Ads", str);
        }
    }

    public static void zzcx(String str) {
        if (zzaz(5)) {
            Log.w("Ads", str);
        }
    }

    public static void zzd(String str, Throwable th) {
        if (zzaz(5)) {
            Log.w("Ads", str, th);
        }
    }
}
