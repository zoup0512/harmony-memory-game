package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.flurry.sdk.lq.a;

public class ju implements a {
    private static final String a = ju.class.getSimpleName();
    private static ju b;
    private String c;
    private String d;

    private ju() {
        lq a = lp.a();
        this.c = (String) a.a("VersionName");
        a.a("VersionName", (a) this);
        km.a(4, a, "initSettings, VersionName = " + this.c);
    }

    public static synchronized ju a() {
        ju juVar;
        synchronized (ju.class) {
            if (b == null) {
                b = new ju();
            }
            juVar = b;
        }
        return juVar;
    }

    public static String b() {
        return VERSION.RELEASE;
    }

    public static String c() {
        return Build.DEVICE;
    }

    public static String d() {
        return Build.ID;
    }

    public static String e() {
        return Build.MANUFACTURER;
    }

    public static String f() {
        return Build.MODEL;
    }

    public static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "unknown";
        }
    }

    public final synchronized String g() {
        String str;
        if (!TextUtils.isEmpty(this.c)) {
            str = this.c;
        } else if (TextUtils.isEmpty(this.d)) {
            this.d = h();
            str = this.d;
        } else {
            str = this.d;
        }
        return str;
    }

    private static String h() {
        try {
            Context context = jy.a().a;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                return packageInfo.versionName;
            }
            if (packageInfo.versionCode != 0) {
                return Integer.toString(packageInfo.versionCode);
            }
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        } catch (Throwable th) {
            km.a(6, a, "", th);
        }
    }

    public final void a(String str, Object obj) {
        if (str.equals("VersionName")) {
            this.c = (String) obj;
            km.a(4, a, "onSettingUpdate, VersionName = " + this.c);
            return;
        }
        km.a(6, a, "onSettingUpdate internal error!");
    }
}
