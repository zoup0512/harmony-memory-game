package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;

public class r extends l<v> {
    public r(q<v> qVar) {
        super(qVar);
    }

    protected boolean a(h hVar, n<v> nVar) {
        for (v a : nVar.a()) {
            if (a.a(hVar)) {
                return true;
            }
        }
        return false;
    }
}
