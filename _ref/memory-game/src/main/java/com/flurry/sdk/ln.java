package com.flurry.sdk;

import java.util.Timer;
import java.util.TimerTask;

final class ln {
    private Timer a;
    private a b;

    class a extends TimerTask {
        final /* synthetic */ ln a;

        a(ln lnVar) {
            this.a = lnVar;
        }

        public final void run() {
            this.a.a();
            ki.a().a(new lo());
        }
    }

    ln() {
    }

    public final synchronized void a(long j) {
        Object obj;
        if (this.a != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a();
        }
        this.a = new Timer("FlurrySessionTimer");
        this.b = new a(this);
        this.a.schedule(this.b, j);
    }

    public final synchronized void a() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
        this.b = null;
    }
}
