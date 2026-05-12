package com.chartboost.sdk.Libraries;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.c;
import com.chartboost.sdk.impl.a;
import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class CBUtility {
    private CBUtility() {
    }

    public static String a(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            stringBuilder.append("?");
        }
        for (String str : map.keySet()) {
            String str2;
            if (stringBuilder.length() > 1) {
                stringBuilder.append("&");
            }
            String obj = map.get(str2).toString();
            if (str2 != null) {
                try {
                    str2 = URLEncoder.encode(str2, "UTF-8");
                } catch (Throwable e) {
                    CBLogging.b("CBUtility", "This method requires UTF-8 encoding support", e);
                    return null;
                }
            }
            str2 = "";
            stringBuilder.append(str2);
            stringBuilder.append("=");
            stringBuilder.append(obj != null ? URLEncoder.encode(obj, "UTF-8") : "");
        }
        return stringBuilder.toString();
    }

    public static float a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int a(int i, Context context) {
        return Math.round(((float) i) * a(context));
    }

    public static float a(float f, Context context) {
        return a(context) * f;
    }

    public static f a() {
        Object obj;
        Object obj2 = 1;
        Context x = c.x();
        Display defaultDisplay = ((WindowManager) x.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            obj = x.getResources().getConfiguration().orientation != 2 ? 1 : null;
        } else if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            int i = 1;
        } else {
            obj = null;
        }
        if (rotation == 0 || rotation == 2) {
            obj2 = obj;
        } else if (obj != null) {
            obj2 = null;
        }
        if (obj2 != null) {
            switch (rotation) {
                case 1:
                    return f.g;
                case 2:
                    return f.PORTRAIT_REVERSE;
                case 3:
                    return f.h;
                default:
                    return f.PORTRAIT;
            }
        }
        switch (rotation) {
            case 1:
                return f.e;
            case 2:
                return f.LANDSCAPE_REVERSE;
            case 3:
                return f.f;
            default:
                return f.LANDSCAPE;
        }
    }

    public static void throwProguardError(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            CBLogging.b("CBUtility", "Chartboost library error! Have you used proguard on your application? Make sure to add the line '-keep class com.chartboost.sdk.** { *; }' to your proguard config file.");
        } else if (ex == null || ex.getMessage() == null) {
            CBLogging.b("CBUtility", "Unknown Proguard error");
        } else {
            CBLogging.b("CBUtility", ex.getMessage());
        }
    }

    public static String b() {
        String str = "%s %s %s";
        Object[] objArr = new Object[3];
        objArr[0] = "Chartboost-Android-SDK";
        objArr[1] = c.b() == null ? "" : c.b();
        objArr[2] = "6.5.1";
        return String.format(str, objArr);
    }

    public static Handler c() {
        return a.a().a;
    }

    public static boolean d() {
        return f() || g() || h();
    }

    public static String e() {
        SimpleDateFormat simpleDateFormat;
        if (VERSION.SDK_INT >= 18) {
            simpleDateFormat = new SimpleDateFormat("ZZZZ", Locale.US);
        } else {
            simpleDateFormat = new SimpleDateFormat("'GMT'ZZZZ", Locale.US);
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date());
    }

    private static boolean f() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean g() {
        return new File("/system/app/Superuser.apk").exists();
    }

    private static boolean h() {
        for (String file : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    public static void a(Activity activity, b bVar) {
        if (activity != null) {
            if ((bVar == b.WEB && c.G().booleanValue() && c.L()) || (bVar == b.NATIVE && c.M() && c.R())) {
                f a = a();
                if (a == f.PORTRAIT) {
                    activity.setRequestedOrientation(1);
                } else if (a == f.PORTRAIT_REVERSE) {
                    activity.setRequestedOrientation(9);
                } else if (a == f.LANDSCAPE) {
                    activity.setRequestedOrientation(0);
                } else {
                    activity.setRequestedOrientation(8);
                }
            }
        }
    }

    public static void b(Activity activity, b bVar) {
        if (activity != null) {
            if ((bVar == b.WEB && c.G().booleanValue() && c.L()) || (bVar == b.NATIVE && c.M() && c.R())) {
                activity.setRequestedOrientation(-1);
            }
        }
    }

    @TargetApi(19)
    public static void a(Activity activity) {
        if (activity != null && c.a) {
            a a = a.a();
            if (a.a(11)) {
                int i = 0;
                if (a.a(14)) {
                    i = 2;
                    if (a.a(16)) {
                        i = 1798;
                        if (a.a(19)) {
                            i = 5894;
                        }
                    }
                }
                activity.getWindow().getDecorView().setSystemUiVisibility(i);
            }
        } else if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
            CBLogging.d("CBUtility", "Attempting to show Status and Navigation bars on a fullscreen activity. Please change your Chartboost activity theme to: \"@android:style/Theme.Translucent\"` in your Manifest file");
        }
    }

    public static boolean a(CBFramework cBFramework) {
        return c.b() != null && c.b() == cBFramework;
    }
}
