package com.appodeal.ads.utils;

import android.app.Activity;
import android.os.Environment;
import com.appodeal.ads.Appodeal;
import java.io.File;

public class f {
    public static void a(Activity activity) {
        b(activity);
        c(activity);
        d(activity);
        e(activity);
        f(activity);
    }

    private static void b(Activity activity) {
        try {
            a(new File(activity.getFilesDir(), "adc/media"));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void c(Activity activity) {
        try {
            a(new File(activity.getExternalFilesDir(null), "al"));
            a(new File(activity.getCacheDir(), "al"));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void d(Activity activity) {
        try {
            a(new File(activity.getCacheDir(), ".chartboost"));
            a(new File(Environment.getExternalStorageDirectory(), ".chartboost"));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void e(Activity activity) {
        try {
            a(new File(activity.getExternalCacheDir(), "UnityAdsVideoCache"));
            a(new File(activity.getExternalCacheDir(), "UnityAdsCache"));
            a(new File(Environment.getExternalStorageDirectory(), "UnityAdsVideoCache"));
            a(new File(activity.getFilesDir(), "UnityAdsVideoCache"));
            a(new File(activity.getFilesDir(), "UnityAdsCache"));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void f(Activity activity) {
        try {
            a(new File(activity.getExternalFilesDir(null), ".vungle"));
            a(new File(activity.getExternalCacheDir(), ".vungle"));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void a(File file) {
        try {
            if (!b(file)) {
                d(file);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static boolean b(File file) {
        return c(file) < 5242880;
    }

    private static long c(File file) {
        long j = 0;
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        int i = 0;
        while (i < listFiles.length) {
            long c = c(listFiles[i]) + j;
            i++;
            j = c;
        }
        return j;
    }

    private static void d(File file) {
        if (file.isDirectory()) {
            for (File d : file.listFiles()) {
                d(d);
            }
            file.delete();
        } else if (file.exists()) {
            file.delete();
        }
    }
}
