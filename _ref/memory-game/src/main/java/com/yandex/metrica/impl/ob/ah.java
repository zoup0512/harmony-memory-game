package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.d;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.utils.j;
import com.yandex.metrica.impl.utils.j.a;

public class ah extends v {
    public ah(j jVar) {
        super(jVar);
    }

    public boolean a(h hVar) {
        b(hVar);
        return true;
    }

    void b(h hVar) {
        Object obj = 1;
        String k = hVar.k();
        d a = j.a(k);
        String g = a().g();
        d a2 = j.a(g);
        if (!a.equals(a2)) {
            Object obj2;
            if (!TextUtils.isEmpty(a.a()) || TextUtils.isEmpty(a2.a())) {
                obj2 = null;
            } else {
                obj2 = 1;
            }
            if (obj2 != null) {
                hVar.a(g);
                a(hVar, a.LOGOUT);
            } else {
                if (TextUtils.isEmpty(a.a()) || !TextUtils.isEmpty(a2.a())) {
                    obj2 = null;
                } else {
                    obj2 = 1;
                }
                if (obj2 != null) {
                    a(hVar, a.LOGIN);
                } else {
                    if (TextUtils.isEmpty(a.a()) || a.a().equals(a2.a())) {
                        obj = null;
                    }
                    if (obj != null) {
                        a(hVar, a.SWITCH);
                    } else {
                        a(hVar, a.UPDATE);
                    }
                }
            }
            a().a(k);
        }
        if (!a().j().A()) {
            a().c();
        }
    }

    private void a(h hVar, a aVar) {
        hVar.c(j.a(aVar));
        a().d(hVar);
    }
}
