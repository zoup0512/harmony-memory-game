package com.appodeal.ads.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import java.util.Map.Entry;

public class n {
    public static boolean a(Context context, String str) {
        if (context == null || str.isEmpty()) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("install_tracking", 0);
        if (!sharedPreferences.contains(str)) {
            return false;
        }
        if (sharedPreferences.getLong(str, 0) > System.currentTimeMillis()) {
            return true;
        }
        sharedPreferences.edit().remove(str).apply();
        return false;
    }

    public static void a(Context context, String str, long j) {
        if (context != null && !str.isEmpty()) {
            if (j == 0) {
                j = 180;
            }
            context.getSharedPreferences("install_tracking", 0).edit().putLong(str, ((60 * j) * 1000) + System.currentTimeMillis()).apply();
            a(context);
        }
    }

    private static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("install_tracking", 0);
        Map all = sharedPreferences.getAll();
        long currentTimeMillis = System.currentTimeMillis();
        for (Entry entry : all.entrySet()) {
            if (((Long) entry.getValue()).longValue() < currentTimeMillis) {
                sharedPreferences.edit().remove((String) entry.getKey()).apply();
            }
        }
    }
}
