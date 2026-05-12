package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;

public abstract class l<T> {
    private final q<T> a;

    protected abstract boolean a(h hVar, n<T> nVar);

    protected l(q<T> qVar) {
        this.a = qVar;
    }

    public boolean a(h hVar) {
        return a(hVar, b(hVar));
    }

    n<T> b(h hVar) {
        return this.a.a(hVar.c());
    }
}
