package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzin
public final class zzkd extends zzb {
    public static void v(String str) {
        if (zztb()) {
            Log.v("Ads", str);
        }
    }

    public static boolean zzta() {
        return ((Boolean) zzdc.zzbap.get()).booleanValue();
    }

    private static boolean zztb() {
        return zzb.zzaz(2) && zzta();
    }
}
