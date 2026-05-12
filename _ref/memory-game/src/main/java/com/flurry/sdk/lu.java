package com.flurry.sdk;

public final class lu {
    long a = 1000;
    boolean b = true;
    boolean c = false;
    ma d = new ma(this) {
        final /* synthetic */ lu a;

        {
            this.a = r1;
        }

        public final void a() {
            ki.a().a(new ls());
            if (this.a.b && this.a.c) {
                jy.a().a(this.a.d, this.a.a);
            }
        }
    };

    public final synchronized void a() {
        if (!this.c) {
            jy.a().a(this.d, this.a);
            this.c = true;
        }
    }

    public final synchronized void b() {
        if (this.c) {
            jy a = jy.a();
            Runnable runnable = this.d;
            if (runnable != null) {
                a.c.removeCallbacks(runnable);
            }
            this.c = false;
        }
    }
}
