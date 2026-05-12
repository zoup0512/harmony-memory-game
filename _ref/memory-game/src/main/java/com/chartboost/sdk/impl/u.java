package com.chartboost.sdk.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class u {
    private static ExecutorService a = null;
    private static ThreadFactory b = null;

    public static ExecutorService a() {
        if (b == null) {
            b = new ThreadFactory() {
                private final AtomicInteger a = new AtomicInteger(1);

                public Thread newThread(Runnable r) {
                    return new Thread(r, "Chartboost Thread #" + this.a.getAndIncrement());
                }
            };
        }
        if (a == null) {
            a = Executors.newFixedThreadPool(2, b);
        }
        return a;
    }

    public static void b() {
        int i = 0;
        final Semaphore semaphore = new Semaphore(0);
        final Semaphore semaphore2 = new Semaphore(0);
        Runnable anonymousClass2 = new Runnable() {
            public void run() {
                semaphore.release(1);
                semaphore2.acquireUninterruptibly();
            }
        };
        ExecutorService a = a();
        while (i < 2) {
            try {
                a.execute(anonymousClass2);
                i++;
            } catch (Throwable th) {
                semaphore2.release(2);
            }
        }
        semaphore.acquireUninterruptibly(2);
        semaphore2.release(2);
    }
}
