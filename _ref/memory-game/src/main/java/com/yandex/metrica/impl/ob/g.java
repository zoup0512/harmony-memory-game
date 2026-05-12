package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.a;
import java.util.HashMap;

public class g {
    private final HashMap<String, a> a = new HashMap();

    public synchronized a a(j jVar, bj bjVar) {
        a aVar;
        aVar = (a) this.a.get(jVar.l().toString());
        if (aVar == null) {
            a.a a = bjVar.a();
            aVar = new a(a.a, a.b);
            this.a.put(jVar.l().toString(), aVar);
        }
        return aVar;
    }

    public synchronized void a(a.a aVar, bj bjVar) {
        if (aVar.b > bjVar.a().b) {
            bjVar.a(aVar).h();
        }
    }

    public synchronized void b(a.a aVar, bj bjVar) {
        bjVar.a(aVar).h();
    }
}
