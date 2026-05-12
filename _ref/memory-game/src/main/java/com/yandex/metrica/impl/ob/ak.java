package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p.a;

public class ak extends v {
    public ak(j jVar) {
        super(jVar);
    }

    public boolean a(h hVar) {
        j a = a();
        if (a.a().a(hVar)) {
            a.b(true);
            a.d(h.a(hVar, a.EVENT_TYPE_START));
        }
        return false;
    }
}
