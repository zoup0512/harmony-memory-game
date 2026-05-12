package com.appodeal.ads.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class d {
    private static long a;
    private static long b;
    private static long c;
    private static long d;

    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appodeal", 0);
        d = sharedPreferences.getLong("last_session_start", 0);
        Editor edit = sharedPreferences.edit();
        if (sharedPreferences.contains("session_id")) {
            edit.putLong("session_id", sharedPreferences.getLong("session_id", 0) + 1);
        } else {
            edit.putLong("session_id", 1);
        }
        edit.putLong("app_uptime", sharedPreferences.getLong("app_uptime", 0) + sharedPreferences.getLong("session_uptime", 0));
        edit.putLong("session_uptime", 0);
        edit.putLong("last_session_start", System.currentTimeMillis());
        edit.apply();
        a = System.currentTimeMillis();
    }

    public static void a() {
        a = System.currentTimeMillis();
    }

    public static void b(Context context) {
        b += System.currentTimeMillis() - a;
        c(context);
    }

    public static long a(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong("session_id", 0);
    }

    public static long b() {
        if (a == 0) {
            return 0;
        }
        return ((b + System.currentTimeMillis()) - a) / 1000;
    }

    public static long b(SharedPreferences sharedPreferences) {
        return (sharedPreferences.getLong("app_uptime", 0) / 1000) + b();
    }

    public static void c(Context context) {
        if (System.currentTimeMillis() - c >= 10000) {
            Editor edit = context.getSharedPreferences("appodeal", 0).edit();
            edit.putLong("session_uptime", b);
            edit.apply();
            c = System.currentTimeMillis();
        }
    }

    public static long c() {
        return d;
    }
}
