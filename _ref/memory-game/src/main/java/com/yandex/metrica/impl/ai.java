package com.yandex.metrica.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.lang.reflect.Method;

public final class ai {

    public static final class a {
        private static final String[] a = new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};

        public static boolean a() {
            try {
                if (new File("/system/app/Superuser.apk").exists()) {
                    return true;
                }
            } catch (Throwable th) {
            }
            return false;
        }

        public static boolean b() {
            String[] strArr = a;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                try {
                    if (new File(strArr[i] + "su").exists()) {
                        return true;
                    }
                    i++;
                } catch (Throwable th) {
                }
            }
            return false;
        }

        public static int c() {
            return (a() || b()) ? 1 : 0;
        }
    }

    public static Point a(Context context) {
        int i;
        int i2;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else if (VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                i = defaultDisplay.getWidth();
                i2 = defaultDisplay.getHeight();
            }
        } else {
            i = defaultDisplay.getWidth();
            i2 = defaultDisplay.getHeight();
        }
        return new Point(i, i2);
    }

    public static String b(Context context) {
        String trim = context.getResources().getConfiguration().locale.toString().trim();
        if (2 == trim.indexOf(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)) {
            return trim.replaceFirst(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, "-");
        }
        return trim;
    }
}
