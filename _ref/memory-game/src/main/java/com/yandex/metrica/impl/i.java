package com.yandex.metrica.impl;

abstract class i {
    private final a a;

    public interface a {
        boolean a(Throwable th);
    }

    abstract void b(Throwable th);

    i(a aVar) {
        this.a = aVar;
    }

    void a(Throwable th) {
        if (this.a.a(th)) {
            b(th);
        }
    }
}
