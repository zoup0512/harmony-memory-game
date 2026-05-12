package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p.a;

public class ab extends v {
    public ab(j jVar) {
        super(jVar);
    }

    public boolean a(h hVar) {
        String b = b();
        if (!be.a(b)) {
            c();
            a().a(new h(hVar).c("").b(b).a(a.EVENT_TYPE_REFERRER_DEPRECATED.a()));
        }
        return false;
    }

    String b() {
        return a().r();
    }

    void c() {
        a().s();
    }
}
