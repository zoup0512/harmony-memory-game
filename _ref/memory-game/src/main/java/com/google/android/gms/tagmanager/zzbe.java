package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzbe {
    private static String awf;
    static Map<String, String> awg = new HashMap();

    public static String zzbg(String str, String str2) {
        if (str2 == null) {
            return str.length() > 0 ? str : null;
        } else {
            String str3 = "http://hostname/?";
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).getQueryParameter(str2);
        }
    }

    public static String zzh(Context context, String str, String str2) {
        String str3 = (String) awg.get(str);
        if (str3 == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str3 = sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
            awg.put(str, str3);
        }
        return zzbg(str3, str2);
    }

    public static void zzow(String str) {
        synchronized (zzbe.class) {
            awf = str;
        }
    }

    static void zzw(Context context, String str) {
        zzdc.zzb(context, "gtm_install_referrer", "referrer", str);
        zzy(context, str);
    }

    public static String zzx(Context context, String str) {
        if (awf == null) {
            synchronized (zzbe.class) {
                if (awf == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        awf = sharedPreferences.getString("referrer", "");
                    } else {
                        awf = "";
                    }
                }
            }
        }
        return zzbg(awf, str);
    }

    public static void zzy(Context context, String str) {
        String zzbg = zzbg(str, "conv");
        if (zzbg != null && zzbg.length() > 0) {
            awg.put(zzbg, str);
            zzdc.zzb(context, "gtm_click_referrers", zzbg, str);
        }
    }
}
