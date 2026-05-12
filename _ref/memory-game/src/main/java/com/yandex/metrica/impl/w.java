package com.yandex.metrica.impl;

import android.os.Handler;
import android.os.SystemClock;

class w {
    private final Handler a;
    private final b b;
    private final x c;

    w(Handler handler, b bVar) {
        this.a = handler;
        this.b = bVar;
        this.c = new x(handler, bVar);
    }

    void a() {
        b(this.a, this.b, this.c);
    }

    void b() {
        a(this.a, this.b, this.c);
    }

    static void a(Handler handler, b bVar, Runnable runnable) {
        b(handler, bVar, runnable);
        handler.postAtTime(runnable, a(bVar), SystemClock.uptimeMillis() + ((long) (bVar.d().b().d() * 500)));
    }

    private static void b(Handler handler, b bVar, Runnable runnable) {
        handler.removeCallbacks(runnable, a(bVar));
    }

    private static String a(b bVar) {
        return bVar.d().b().j();
    }
}
