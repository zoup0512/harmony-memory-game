package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p.a;

public class ac extends v {
    private bh a;

    public ac(j jVar) {
        super(jVar);
        this.a = jVar.v();
    }

    public boolean a(h hVar) {
        j a = a();
        if (!this.a.c()) {
            a.d(h.a(hVar, a.EVENT_TYPE_INIT).c(this.a.d("")));
            a.b(true);
            this.a.a();
            this.a.e();
        }
        return false;
    }
}
