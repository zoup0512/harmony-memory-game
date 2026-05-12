package com.yandex.metrica.impl;

import android.os.SystemClock;

class q {
    private long a = (SystemClock.elapsedRealtime() - 2000000);
    private boolean b = true;

    q() {
    }

    boolean a() {
        boolean z = this.b;
        this.b = false;
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.a;
        if (!z || elapsedRealtime <= 1000) {
            return false;
        }
        return true;
    }

    void b() {
        this.b = true;
        this.a = SystemClock.elapsedRealtime();
    }

    boolean c() {
        return this.b;
    }
}
