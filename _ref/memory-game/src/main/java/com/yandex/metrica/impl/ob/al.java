package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;

public class al extends l<v> {
    public al(q<v> qVar) {
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
