package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Chartboost.CBMediation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.w.b;
import com.facebook.internal.AnalyticsEvents;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class c {
    private static boolean A = true;
    private static boolean B = true;
    public static boolean a = true;
    public static final boolean b = (VERSION.SDK_INT >= 23);
    private static final String c = c.class.getSimpleName();
    private static String d;
    private static String e;
    private static a f;
    private static boolean g = false;
    private static boolean h = false;
    private static CBFramework i = null;
    private static String j = null;
    private static String k = null;
    private static String l = null;
    private static CBMediation m = null;
    private static String n = null;
    private static String o = null;
    private static boolean p = true;
    private static volatile boolean q = false;
    private static Context r = null;
    private static boolean s = true;
    private static boolean t = false;
    private static boolean u = true;
    private static float v = 250.0f;
    private static boolean w = false;
    private static boolean x = true;
    private static boolean y = true;
    private static boolean z = true;

    public interface a {
        void a();
    }

    private c() {
    }

    public static boolean a() {
        return x;
    }

    public static void a(Boolean bool) {
        a = bool.booleanValue();
    }

    public static void a(CBFramework cBFramework) {
        if (cBFramework == null) {
            CBLogging.b(c, "Pass a valid CBFramework enum value");
        } else {
            i = cBFramework;
        }
    }

    public static void a(CBFramework cBFramework, String str) {
        a(cBFramework);
        j = str;
    }

    public static CBFramework b() {
        return i == null ? null : i;
    }

    public static String c() {
        if (i == null) {
            return "";
        }
        return String.format("%s %s", new Object[]{i, j});
    }

    public static void a(String str) {
        if (i == null) {
            CBLogging.b(c, "Set a valid CBFramework first");
        } else if (TextUtils.isEmpty(str)) {
            CBLogging.b(c, "Invalid Version String");
        } else {
            k = str;
        }
    }

    public static String d() {
        return k;
    }

    public static void a(CBMediation cBMediation, String str) {
        m = cBMediation;
        n = str;
        l = m + " " + n;
    }

    public static String e() {
        t();
        return l;
    }

    public static String f() {
        if (t()) {
            return d;
        }
        return "";
    }

    public static void b(String str) {
        d = str;
    }

    public static String g() {
        if (t()) {
            return e;
        }
        return "";
    }

    public static void c(String str) {
        e = str;
    }

    public static a h() {
        return f;
    }

    public static void a(a aVar) {
        f = aVar;
    }

    public static boolean i() {
        return true;
    }

    public static boolean j() {
        if (t()) {
            return h;
        }
        return false;
    }

    public static boolean k() {
        return p;
    }

    public static void a(boolean z) {
        if (t()) {
            p = z;
        }
    }

    public static List<String> l() {
        Object string = f.p().getString("webview", "");
        try {
            if (!TextUtils.isEmpty(string)) {
                com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
                if (k.c() && !TextUtils.isEmpty(k.e("directories"))) {
                    return k.a("directories").h();
                }
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(c.class, "getDirectoryList", e);
        }
        return null;
    }

    public static JSONObject m() {
        if (!t()) {
            return null;
        }
        Object string = f.p().getString("trackingLevels", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
        if (k.c()) {
            return k.e();
        }
        return null;
    }

    public static boolean n() {
        t();
        return f.p().getBoolean("retriesEnabled", true);
    }

    public static void a(Level level) {
        if (t()) {
            CBLogging.a = level;
        }
    }

    public static Level o() {
        t();
        return CBLogging.a;
    }

    public static String p() {
        if (t()) {
            return o;
        }
        return "";
    }

    public static void d(String str) {
        o = str;
    }

    public static void a(com.chartboost.sdk.Libraries.e.a aVar) {
        try {
            if (aVar.c()) {
                Map f = aVar.f();
                if (f != null) {
                    Editor edit = f.p().edit();
                    for (String str : f.keySet()) {
                        Object obj = f.get(str);
                        if (obj instanceof String) {
                            edit.putString(str, (String) obj);
                        } else if (obj instanceof Integer) {
                            edit.putInt(str, ((Integer) obj).intValue());
                        } else if (obj instanceof Float) {
                            edit.putFloat(str, ((Float) obj).floatValue());
                        } else if (obj instanceof Long) {
                            edit.putLong(str, ((Long) obj).longValue());
                        } else if (obj instanceof Boolean) {
                            edit.putBoolean(str, ((Boolean) obj).booleanValue());
                        } else if (obj != null) {
                            edit.putString(str, com.chartboost.sdk.Libraries.e.a.a((HashMap) obj).toString());
                        }
                    }
                    edit.apply();
                }
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(c.class, "processConfig", e);
        }
    }

    public static void a(final a aVar) {
        final Chartboost q = f.q();
        q.a = true;
        ad adVar = new ad("/api/config");
        adVar.a(false);
        adVar.b(false);
        adVar.a(b.HIGH);
        adVar.a(g.a(g.a("status", com.chartboost.sdk.Libraries.a.a)));
        adVar.a(new com.chartboost.sdk.impl.ad.c() {
            public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
                q.a = false;
                if (aVar.c()) {
                    com.chartboost.sdk.Libraries.e.a a = aVar.a("response");
                    if (a.c()) {
                        c.a(a);
                    }
                }
                if (aVar != null) {
                    aVar.a();
                }
                if (!q.b) {
                    if (c.f != null) {
                        c.f.didInitialize();
                    }
                    q.b = true;
                }
            }

            public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError) {
                q.a = false;
                if (aVar != null) {
                    aVar.a();
                }
                if (!q.b) {
                    if (c.f != null) {
                        c.f.didInitialize();
                    }
                    q.b = true;
                }
            }
        });
    }

    public static boolean q() {
        return q;
    }

    public static void b(boolean z) {
        q = z;
    }

    public static boolean r() {
        if (t() && s() && Y()) {
            return true;
        }
        return false;
    }

    private static boolean Y() {
        if (w) {
            return true;
        }
        try {
            throw new Exception("CBImpression Activity is missing in the manifest. Please add the CBImpresssionActivity in the manifest to show ads");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean s() {
        Chartboost q = f.q();
        if (q == null) {
            return false;
        }
        if (q.c != null) {
            return true;
        }
        try {
            throw new Exception("Chartboost Weak Activity reference is null");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(Activity activity) {
        if (activity != null) {
            return true;
        }
        try {
            throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean t() {
        try {
            if (f.q() == null) {
                throw new Exception("SDK Initialization error. SDK seems to be not initialized properly, check for any integration issues");
            } else if (x() == null) {
                throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
            } else if (TextUtils.isEmpty(d)) {
                throw new Exception("SDK Initialization error. AppId is missing");
            } else if (!TextUtils.isEmpty(e)) {
                return true;
            } else {
                throw new Exception("SDK Initialization error. AppSignature is missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void c(boolean z) {
        if (t()) {
            s = z;
        }
    }

    public static boolean u() {
        return s;
    }

    public static void d(boolean z) {
        if (t()) {
            t = z;
        }
    }

    public static boolean v() {
        return t;
    }

    public static void e(boolean z) {
        if (t()) {
            u = z;
        }
    }

    public static boolean w() {
        return u && !f.p().getBoolean("prefetchDisable", false);
    }

    public static void a(Context context) {
        r = context;
    }

    public static Context x() {
        return r;
    }

    public static boolean b(Activity activity) {
        if (activity == null) {
            try {
                throw new RuntimeException("Invalid activity context passed during intitalization");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        int checkSelfPermission;
        int checkSelfPermission2;
        int checkSelfPermission3;
        int checkSelfPermission4;
        int checkSelfPermission5;
        if (b) {
            checkSelfPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = activity.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = activity.checkSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = activity.checkSelfPermission("android.permission.READ_PHONE_STATE");
            checkSelfPermission5 = activity.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
        } else {
            checkSelfPermission = activity.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = activity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = activity.checkCallingOrSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = activity.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
            checkSelfPermission5 = activity.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
        }
        if (checkSelfPermission != 0) {
            x = true;
        } else {
            x = false;
        }
        if (checkSelfPermission3 != 0) {
            y = true;
            throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
        }
        y = false;
        if (checkSelfPermission2 != 0) {
            z = true;
            throw new RuntimeException("Please add the permission :  android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
        }
        z = false;
        if (checkSelfPermission4 == 0) {
            A = false;
        } else {
            A = true;
        }
        if (checkSelfPermission5 == 0) {
            B = false;
            return true;
        }
        B = true;
        return true;
    }

    public static boolean c(Activity activity) {
        try {
            if (activity.getPackageManager().queryIntentActivities(new Intent(activity, CBImpressionActivity.class), 65536).size() > 0) {
                w = true;
            } else {
                w = false;
            }
            if (w) {
                return true;
            }
            throw new RuntimeException("Please add             <activity android:name=\"com.chartboost.sdk.CBImpressionActivity\"\n                  android:excludeFromRecents=\"true\"\n                  android:theme=\"@android:style/Theme.Translucent.NoTitleBar.Fullscreen\"\n                  android:configChanges=\"keyboardHidden|orientation|screenSize\"/> in your android manifest.xml");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String y() {
        if (!G().booleanValue()) {
            return "/interstitial/get";
        }
        return String.format("%s/%s%s", new Object[]{"webview", B(), "/interstitial/get"});
    }

    public static String z() {
        if (!G().booleanValue()) {
            return "/reward/get";
        }
        return String.format("%s/%s%s", new Object[]{"webview", B(), "/reward/get"});
    }

    public static String A() {
        if (!G().booleanValue()) {
            return "/api/video-prefetch";
        }
        return String.format("%s/%s/%s", new Object[]{"webview", B(), "prefetch"});
    }

    public static String B() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && !k.a("version").b()) {
                return k.e("version");
            }
        }
        return "v2";
    }

    public static int C() {
        Float e = e("cacheTTLs");
        if (e != null) {
            return (int) TimeUnit.SECONDS.toDays(e.longValue());
        }
        return 7;
    }

    public static int D() {
        Float e = e("cacheMaxUnits");
        if (e == null || e.floatValue() <= 0.0f) {
            return 10;
        }
        return e.intValue();
    }

    public static int E() {
        Float e = e("invalidatePendingImpression");
        if (e == null || e.floatValue() <= 0.0f) {
            return 3;
        }
        return e.intValue();
    }

    public static int F() {
        Float e = e("cacheMaxBytes");
        if (e != null) {
            return e.intValue();
        }
        return 104857600;
    }

    private static Float e(String str) {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && !k.a(str).b()) {
                return Float.valueOf(k.g(str));
            }
        }
        return null;
    }

    public static Boolean G() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("enabled").c() && com.chartboost.sdk.impl.a.a().a(14)) {
                return Boolean.valueOf(k.j("enabled"));
            }
        }
        return Boolean.valueOf(false);
    }

    public static boolean H() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("interstitialEnabled").c() && com.chartboost.sdk.impl.a.a().a(14)) {
                return k.j("interstitialEnabled");
            }
        }
        return true;
    }

    public static boolean I() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("rewardVideoEnabled").c() && com.chartboost.sdk.impl.a.a().a(14)) {
                return k.j("rewardVideoEnabled");
            }
        }
        return true;
    }

    public static boolean J() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("moreAppsEnabled").c() && com.chartboost.sdk.impl.a.a().a(14)) {
                return k.j("moreAppsEnabled");
            }
        }
        return true;
    }

    public static boolean K() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("inplayEnabled").c() && VERSION.SDK_INT >= 14) {
                return k.j("inplayEnabled");
            }
        }
        return true;
    }

    public static boolean L() {
        Object string = f.p().getString("webview", "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("lockOrientation").c() && VERSION.SDK_INT >= 14) {
                return k.j("lockOrientation");
            }
        }
        return true;
    }

    public static boolean M() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("enabled").c()) {
                return k.j("enabled");
            }
        }
        return true;
    }

    public static boolean N() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("interstitialEnabled").c()) {
                return k.j("interstitialEnabled");
            }
        }
        return true;
    }

    public static boolean O() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("rewardVideoEnabled").c()) {
                return k.j("rewardVideoEnabled");
            }
        }
        return true;
    }

    public static boolean P() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("moreAppsEnabled").c()) {
                return k.j("moreAppsEnabled");
            }
        }
        return true;
    }

    public static boolean Q() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("inplayEnabled").c()) {
                return k.j("inplayEnabled");
            }
        }
        return true;
    }

    public static boolean R() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("lockOrientation").c()) {
                return k.j("lockOrientation");
            }
        }
        return false;
    }

    public static Boolean S() {
        return Boolean.valueOf(f.p().getBoolean("publisherDisable", false));
    }

    public static String T() {
        return f.p().getString("configVariant", "");
    }

    public static int U() {
        Float e = e("prefetchSession");
        if (e == null || e.floatValue() <= 0.0f) {
            return 3;
        }
        return e.intValue();
    }

    public static int V() {
        Object string = f.p().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("prefetchSession").c()) {
                return k.f("prefetchSession");
            }
        }
        return 3;
    }

    public static String W() {
        if (G().booleanValue()) {
            return "web";
        }
        return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
    }
}
