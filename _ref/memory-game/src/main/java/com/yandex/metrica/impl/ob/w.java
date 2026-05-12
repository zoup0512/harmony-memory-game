package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p.a;

public class w extends v {
    private bh a = a().v();

    public w(j jVar) {
        super(jVar);
    }

    public boolean a(h hVar) {
        j a = a();
        if (!this.a.d()) {
            if (!this.a.c()) {
                String b = hVar.b();
                this.a.c(b);
                a.d(h.a(hVar, a.EVENT_TYPE_FIRST_ACTIVATION).c(b));
                a.b(true);
            }
            this.a.b();
        }
        return false;
    }
}
