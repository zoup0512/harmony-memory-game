package com.cmcm.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadHelper {
    static final /* synthetic */ boolean $assertionsDisabled = (!ThreadHelper.class.desiredAssertionStatus());
    private static HandlerThread mHandlerThread;
    private static Handler mhandler;
    private static final Object sLock = new Object();
    private static Handler sUiThreadHandler = null;
    private static boolean sWillOverride = false;
    private static boolean serialUIExcutorPermitted = true;

    private static void ensureThreadLocked() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("ResumeThread");
            mHandlerThread.start();
        }
        if (mhandler == null) {
            mhandler = new Handler(mHandlerThread.getLooper());
        }
    }

    public static void post(Runnable runnable) {
        ensureThreadLocked();
        mhandler.post(runnable);
    }

    public static void postDelay(Runnable runnable, long delayMillis) {
        ensureThreadLocked();
        mhandler.postDelayed(runnable, delayMillis);
    }

    public static Handler getUiThreadHandler() {
        Handler handler;
        synchronized (sLock) {
            if (sUiThreadHandler == null) {
                if (sWillOverride) {
                    throw new RuntimeException("Did not yet override the UI thread");
                }
                sUiThreadHandler = new Handler(Looper.getMainLooper());
            }
            handler = sUiThreadHandler;
        }
        return handler;
    }

    public static void runOnUiThreadBlocking(Runnable r) {
        if (runningOnUiThread()) {
            r.run();
            return;
        }
        FutureTask futureTask = new FutureTask(r, null);
        postOnUiThread(futureTask);
        try {
            futureTask.get();
        } catch (Throwable e) {
            throw new RuntimeException("Exception occured while waiting for runnable", e);
        }
    }

    public static <T> T runOnUiThreadBlockingNoException(Callable<T> c) {
        try {
            return runOnUiThreadBlocking((Callable) c);
        } catch (Throwable e) {
            throw new RuntimeException("Error occured waiting for callable", e);
        }
    }

    public static <T> T runOnUiThreadBlocking(Callable<T> c) throws ExecutionException {
        FutureTask futureTask = new FutureTask(c);
        runOnUiThread(futureTask);
        try {
            return futureTask.get();
        } catch (Throwable e) {
            throw new RuntimeException("Interrupted waiting for callable", e);
        }
    }

    public static <T> FutureTask<T> runOnUiThread(FutureTask<T> task) {
        if (runningOnUiThread()) {
            task.run();
        } else {
            postOnUiThread((FutureTask) task);
        }
        return task;
    }

    public static void runOnUiThread(Runnable r) {
        if (runningOnUiThread()) {
            r.run();
        } else {
            getUiThreadHandler().post(r);
        }
    }

    public static <T> FutureTask<T> postOnUiThread(FutureTask<T> task) {
        getUiThreadHandler().post(task);
        return task;
    }

    public static void postOnUiThread(Runnable r) {
        getUiThreadHandler().post(r);
    }

    public static void postOnUiThreadDelayed(Runnable r, long delayMillis) {
        getUiThreadHandler().postDelayed(r, delayMillis);
    }

    public static void assertOnUiThread() {
        if (!$assertionsDisabled && !runningOnUiThread()) {
            throw new AssertionError();
        }
    }

    public static boolean runningOnUiThread() {
        return getUiThreadHandler().getLooper() == Looper.myLooper();
    }

    public static void revokeOnUiThread(Runnable r) {
        getUiThreadHandler().removeCallbacks(r);
    }
}
