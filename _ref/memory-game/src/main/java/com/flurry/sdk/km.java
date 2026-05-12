package com.flurry.sdk;

import android.text.TextUtils;
import android.util.Log;

public final class km {
    private static boolean a = false;
    private static int b = 5;
    private static boolean c = false;

    public static void a() {
        a = true;
    }

    public static void b() {
        a = false;
    }

    public static int c() {
        return b;
    }

    public static void a(int i) {
        b = i;
    }

    public static boolean d() {
        return c;
    }

    private static void b(int i, String str, String str2) {
        if (!a && b <= i) {
            c(i, str, str2);
        }
    }

    private static void c(int i, String str, String str2) {
        if (!c) {
            str = "FlurryAgent";
        }
        int length = TextUtils.isEmpty(str2) ? 0 : str2.length();
        int i2 = 0;
        while (i2 < length) {
            int i3 = 4000 > length - i2 ? length : i2 + 4000;
            if (Log.println(i, str, str2.substring(i2, i3)) > 0) {
                i2 = i3;
            } else {
                return;
            }
        }
    }

    public static void a(String str, String str2) {
        b(3, str, str2);
    }

    public static void b(String str, String str2) {
        b(6, str, str2);
    }

    public static void c(String str, String str2) {
        b(4, str, str2);
    }

    public static void d(String str, String str2) {
        b(2, str, str2);
    }

    public static void e(String str, String str2) {
        b(5, str, str2);
    }

    private static void d(int i, String str, String str2) {
        if (c) {
            c(i, str, str2);
        }
    }

    public static void a(int i, String str, String str2) {
        d(i, str, str2);
    }

    public static void a(String str, String str2, Throwable th) {
        b(6, str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static void a(int i, String str, String str2, Throwable th) {
        d(i, str, str2 + '\n' + Log.getStackTraceString(th));
    }
}
