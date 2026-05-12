package com.cmcm.picks.loader;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.cmcm.utils.b;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MarketConfig */
public class g {
    private static SharedPreferences a;
    private static int b;
    private static Map<Long, Long> c;
    private static String d = null;

    public static int a() {
        if (b < 1800) {
            b = c(b(CMBaseNativeAd.KEY_CACHE_TIME, ""));
            if (b < 1800) {
                b = 3600;
            }
        }
        return b * 1000;
    }

    private static int c(String str) {
        if (TextUtils.isEmpty(str) || !TextUtils.isDigitsOnly(str)) {
            return -1;
        }
        return Integer.parseInt(str);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("cm_softer_cache_time", "");
            b = c(optString);
            a(CMBaseNativeAd.KEY_CACHE_TIME, optString);
            a("request_url", jSONObject.optString("request_url"));
            a("req_timeout_ms", jSONObject.optString("req_timeout_ms"));
            optString = jSONObject.optString("https_request_url", "");
            if (!TextUtils.isEmpty(optString)) {
                URI uri = new URI(optString);
                String scheme = uri.getScheme();
                String host = uri.getHost();
                if (!TextUtils.isEmpty(scheme)) {
                    a("scheme", scheme);
                }
                if (!TextUtils.isEmpty(host)) {
                    a("host", host);
                }
                a("https_request_url", optString);
            }
            optString = jSONObject.optString("https_report_url", "");
            if (!TextUtils.isEmpty(optString)) {
                a("https_report_url", optString);
            }
            JSONArray jSONArray = jSONObject.getJSONArray("pos_cache");
            if (c == null) {
                c = new HashMap();
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                long j = jSONObject2.getLong("posid");
                long j2 = jSONObject2.getLong(CMBaseNativeAd.KEY_CACHE_TIME);
                c.put(Long.valueOf(j), Long.valueOf(j2));
                a(String.valueOf(j) + "_posid_expire_time", Long.valueOf(j2));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String b() {
        return b("scheme", "");
    }

    public static String c() {
        return b("https_report_url", "");
    }

    public static long a(Long l) {
        if (c == null) {
            return (long) a();
        }
        Long l2 = (Long) c.get(l);
        if (l2 == null || l2.longValue() <= 0) {
            return (long) a();
        }
        return l2.longValue() * 1000;
    }

    public static long b(String str) {
        long longValue = b(str + "_posid_expire_time", Long.valueOf(0)).longValue() * 1000;
        if (longValue <= 0) {
            return (long) a();
        }
        return longValue;
    }

    public static boolean a(String str, long j) {
        if (System.currentTimeMillis() - b(str, Long.valueOf(0)).longValue() <= j) {
            return false;
        }
        a(str, Long.valueOf(System.currentTimeMillis()));
        return true;
    }

    public static void a(Editor editor) {
        if (VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void a(String str, String str2) {
        f();
        Editor edit = a.edit();
        edit.putString(str, str2);
        a(edit);
    }

    public static void a(String str, Long l) {
        f();
        Editor edit = a.edit();
        edit.putLong(str, l.longValue());
        a(edit);
    }

    public static String b(String str, String str2) {
        f();
        return a.getString(str, str2);
    }

    public static Long b(String str, Long l) {
        f();
        return Long.valueOf(a.getLong(str, l.longValue()));
    }

    private static void f() {
        if (a == null) {
            a = CMAdManager.getContext().getSharedPreferences("market_config", 0);
        }
    }

    public static void d() {
        if (d == null) {
            try {
                d = b("uer_agent", "");
                if (TextUtils.isEmpty(d)) {
                    d = b.a(CMAdManager.getContext());
                    a("uer_agent", d);
                }
            } catch (Exception e) {
                if (com.cmcm.utils.g.a) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String e() {
        return "ssdk.adkmob.com";
    }
}
