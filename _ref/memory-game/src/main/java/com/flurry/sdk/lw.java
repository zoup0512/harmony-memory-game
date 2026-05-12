package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

public final class lw {
    public static boolean a() {
        return ((KeyguardManager) jy.a().a.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    @SuppressLint({"NewApi"})
    public static DisplayMetrics b() {
        Display defaultDisplay = ((WindowManager) jy.a().a.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            return displayMetrics;
        }
        if (VERSION.SDK_INT >= 14) {
            try {
                displayMetrics = new DisplayMetrics();
                Display.class.getMethod("getRealMetrics", new Class[0]).invoke(defaultDisplay, new Object[]{displayMetrics});
                return displayMetrics;
            } catch (Exception e) {
            }
        }
        return c();
    }

    public static DisplayMetrics c() {
        Display defaultDisplay = ((WindowManager) jy.a().a.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int a(int i) {
        return Math.round(((float) i) / b().density);
    }

    public static int b(int i) {
        return Math.round(b().density * ((float) i));
    }

    public static int d() {
        Point e = e();
        if (e.x == e.y) {
            return 3;
        }
        if (e.x < e.y) {
            return 1;
        }
        return 2;
    }

    @SuppressLint({"NewApi"})
    public static Point e() {
        Display defaultDisplay = ((WindowManager) jy.a().a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else if (VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                point.x = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                point.y = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Throwable th) {
                defaultDisplay.getSize(point);
            }
        } else if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        return point;
    }
}
