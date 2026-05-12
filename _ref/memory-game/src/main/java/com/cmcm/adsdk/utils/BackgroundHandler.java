package com.cmcm.adsdk.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.cmcm.utils.g;

public class BackgroundHandler {
    public static final Handler sBackgroudHandler = new Handler(getLooper());
    private static HandlerThread sLooperThread = new HandlerThread("BackgroundHandler", 1);

    static {
        sLooperThread.start();
    }

    public static <T> void executeAsyncTask(AsyncTask<T, ?, ?> task, T... params) {
        if (VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, params);
        } else {
            task.execute(params);
        }
    }

    public static Looper getLooper() {
        if (!sLooperThread.isAlive()) {
            g.c("BackgroundHandler", "sLooperThread has died, renew a HandlerThread instance");
            sLooperThread.interrupt();
            sLooperThread = new HandlerThread("BackgroundHandler", 1);
            sLooperThread.start();
        }
        return sLooperThread.getLooper();
    }

    private BackgroundHandler() {
    }
}
