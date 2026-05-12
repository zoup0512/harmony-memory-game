package com.amazonaws.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ProgressListenerCallbackExecutor {
    static ExecutorService executor = createNewExecutorService();
    private final ProgressListener listener;

    public static Future<?> progressChanged(final ProgressListener listener, final ProgressEvent progressEvent) {
        if (listener == null) {
            return null;
        }
        return executor.submit(new Runnable() {
            public void run() {
                listener.progressChanged(progressEvent);
            }
        });
    }

    public ProgressListenerCallbackExecutor(ProgressListener listener) {
        this.listener = listener;
    }

    public ProgressListenerCallbackExecutor() {
        this.listener = null;
    }

    public void progressChanged(final ProgressEvent progressEvent) {
        if (this.listener != null) {
            executor.submit(new Runnable() {
                public void run() {
                    ProgressListenerCallbackExecutor.this.listener.progressChanged(progressEvent);
                }
            });
        }
    }

    protected ProgressListener getListener() {
        return this.listener;
    }

    protected static ExecutorService getExecutorService() {
        return executor;
    }

    public static ProgressListenerCallbackExecutor wrapListener(ProgressListener listener) {
        return listener == null ? null : new ProgressListenerCallbackExecutor(listener);
    }

    static ExecutorService createNewExecutorService() {
        return Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("android-sdk-progress-listener-callback-thread");
                t.setDaemon(true);
                return t;
            }
        });
    }
}
