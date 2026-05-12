package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.security.GeneralSecurityException;

class dj implements dr {
    private final dw a;
    private dr b;
    private ea c;

    dj(dx dxVar, dw dwVar) {
        Object obj = null;
        this.a = dwVar;
        boolean f;
        if (dwVar.e()) {
            boolean a = dz.a(dwVar);
            f = dwVar.f();
            this.b = new dm();
            if (f) {
                el a2;
                if (a) {
                    a2 = a(dxVar, dwVar);
                } else {
                    a2 = dt.c(dxVar);
                }
                this.c = new ea(dxVar, this.b, a2, dwVar);
            }
        } else {
            Object obj2;
            boolean a3 = dz.a(dwVar);
            if (86400000 != dwVar.a()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 != null || a3) {
                obj = 1;
            }
            f = dwVar.f();
            if (a3) {
                try {
                    this.b = new df(dxVar, dwVar.b());
                } catch (IOException e) {
                    this.b = new dm();
                }
            } else {
                this.b = dt.b(dxVar);
            }
            if (f) {
                if (obj != null) {
                    this.c = new ea(dxVar, this.b, a(dxVar, dwVar), dwVar);
                } else {
                    this.c = dt.a(dxVar);
                }
            }
        }
        if (this.c != null) {
            this.c.a(dwVar.d());
        }
    }

    ea d() {
        return this.c;
    }

    void e() {
        if (this.a.f()) {
            this.c.c();
        }
    }

    public du a() {
        return this.b.a();
    }

    public du b() {
        return this.b.b();
    }

    public du c() {
        return this.b.c();
    }

    private static el a(dx dxVar, dw dwVar) {
        if (!dz.a(dwVar)) {
            return dxVar.d();
        }
        try {
            return dxVar.a(dwVar.c());
        } catch (GeneralSecurityException e) {
            return null;
        } catch (IOException e2) {
            return null;
        }
    }
}
