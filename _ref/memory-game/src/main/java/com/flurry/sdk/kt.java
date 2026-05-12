package com.flurry.sdk;

import java.util.Timer;
import java.util.TimerTask;

public class kt {
    private static final String a = kt.class.getSimpleName();
    private Timer b;
    private a c;
    private ku d;

    class a extends TimerTask {
        final /* synthetic */ kt a;

        private a(kt ktVar) {
            this.a = ktVar;
        }

        public final void run() {
            km.a(3, kt.a, "HttpRequest timed out. Cancelling.");
            ku a = this.a.d;
            km.a(3, ku.e, "Timeout (" + (System.currentTimeMillis() - a.n) + "MS) for url: " + a.g);
            a.q = 629;
            a.t = true;
            a.e();
            a.f();
        }
    }

    public kt(ku kuVar) {
        this.d = kuVar;
    }

    public final synchronized void a(long j) {
        Object obj = null;
        synchronized (this) {
            if (this.b != null) {
                obj = 1;
            }
            if (obj != null) {
                a();
            }
            this.b = new Timer("HttpRequestTimeoutTimer");
            this.c = new a();
            this.b.schedule(this.c, j);
            km.a(3, a, "HttpRequestTimeoutTimer started: " + j + "MS");
        }
    }

    public final synchronized void a() {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
            km.a(3, a, "HttpRequestTimeoutTimer stopped.");
        }
        this.c = null;
    }
}
