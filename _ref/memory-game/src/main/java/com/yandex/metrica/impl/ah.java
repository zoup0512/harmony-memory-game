package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.PackageManager;

public final class ah {
    public static boolean a(PackageManager packageManager, String str, String str2) {
        return str2 == null || packageManager.checkPermission(str2, str) == 0;
    }

    public static boolean a(Context context, String str) {
        if (str != null) {
            try {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
