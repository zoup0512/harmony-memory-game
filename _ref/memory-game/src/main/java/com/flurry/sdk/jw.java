package com.flurry.sdk;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class jw extends kl<ku> {
    private static jw a = null;

    protected jw() {
        super(jw.class.getName(), TimeUnit.MILLISECONDS, new PriorityBlockingQueue(11, new kj()));
    }

    public static synchronized jw a() {
        jw jwVar;
        synchronized (jw.class) {
            if (a == null) {
                a = new jw();
            }
            jwVar = a;
        }
        return jwVar;
    }
}
