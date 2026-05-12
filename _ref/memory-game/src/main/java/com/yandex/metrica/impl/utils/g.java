package com.yandex.metrica.impl.utils;

import android.content.Context;
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bl;

public class g {
    private volatile long a;
    private bl b;

    private static class a {
        static g a = new g();
    }

    public static g a() {
        return a.a;
    }

    private g() {
    }

    public synchronized long b() {
        return this.a;
    }

    public synchronized void a(Context context) {
        if (context != null) {
            this.b = new bl(bc.a(context).b());
            this.a = this.b.c(0);
        }
    }

    public synchronized void a(long j) {
        this.a = (j - System.currentTimeMillis()) / 1000;
        if (this.b != null) {
            this.b.a(this.a);
            this.b.h();
        }
    }
}
