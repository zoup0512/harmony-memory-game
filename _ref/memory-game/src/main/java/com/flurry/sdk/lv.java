package com.flurry.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public final class lv {
    private static final String a = lv.class.getSimpleName();

    public static String a(Context context) {
        PackageInfo d = d(context);
        if (d == null || d.packageName == null) {
            return "";
        }
        return d.packageName;
    }

    private static PackageInfo d(Context context) {
        PackageInfo packageInfo = null;
        if (context != null) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (NameNotFoundException e) {
                km.a(a, "Cannot find package info for package: " + context.getPackageName());
            }
        }
        return packageInfo;
    }

    public static String b(Context context) {
        PackageInfo d = d(context);
        if (d == null || d.versionName == null) {
            return "";
        }
        return d.versionName;
    }

    public static Bundle c(Context context) {
        ApplicationInfo e = e(context);
        return (e == null || e.metaData == null) ? Bundle.EMPTY : e.metaData;
    }

    private static ApplicationInfo e(Context context) {
        ApplicationInfo applicationInfo = null;
        if (context != null) {
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            } catch (NameNotFoundException e) {
                km.a(a, "Cannot find application info for package: " + context.getPackageName());
            }
        }
        return applicationInfo;
    }
}
