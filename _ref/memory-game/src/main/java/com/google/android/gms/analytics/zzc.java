package com.google.android.gms.analytics;

import com.cmcm.adsdk.Const;
import com.google.android.gms.analytics.internal.zzae;
import io.branch.indexing.ContentDiscoveryManifest;

public final class zzc {
    private static String zzb(String str, int i) {
        if (i >= 1) {
            return new StringBuilder(String.valueOf(str).length() + 11).append(str).append(i).toString();
        }
        zzae.zzf("index out of range for prefix", str);
        return "";
    }

    public static String zzbc(int i) {
        return zzb("&cd", i);
    }

    public static String zzbd(int i) {
        return zzb(ContentDiscoveryManifest.CONTENT_DISCOVER_KEY, i);
    }

    public static String zzbe(int i) {
        return zzb("&cm", i);
    }

    public static String zzbf(int i) {
        return zzb(Const.KEY_CM, i);
    }

    public static String zzbg(int i) {
        return zzb("&pr", i);
    }

    public static String zzbh(int i) {
        return zzb("pr", i);
    }

    public static String zzbi(int i) {
        return zzb("&promo", i);
    }

    public static String zzbj(int i) {
        return zzb("promo", i);
    }

    public static String zzbk(int i) {
        return zzb("pi", i);
    }

    public static String zzbl(int i) {
        return zzb("&il", i);
    }

    public static String zzbm(int i) {
        return zzb("il", i);
    }

    public static String zzbn(int i) {
        return zzb(ContentDiscoveryManifest.CONTENT_DISCOVER_KEY, i);
    }

    public static String zzbo(int i) {
        return zzb(Const.KEY_CM, i);
    }
}
