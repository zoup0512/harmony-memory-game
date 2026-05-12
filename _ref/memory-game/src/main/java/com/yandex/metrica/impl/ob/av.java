package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p.a;

public class av {
    private ar a;
    private ap b;
    private j c;

    public av(j jVar, bj bjVar) {
        this.c = jVar;
        this.a = new ar(jVar, new as(bjVar));
        this.b = new ap(jVar, new aq(bjVar));
    }

    public void a() {
        this.a.i();
    }

    public void b() {
        this.a.h();
        this.b.h();
    }

    public boolean a(h hVar) {
        return a(hVar, this.a, this.b);
    }

    public boolean b(h hVar) {
        if (this.a.f()) {
            return false;
        }
        return a(hVar, this.b, this.a);
    }

    private boolean a(h hVar, at atVar, at atVar2) {
        if (atVar.f()) {
            atVar.i();
            return false;
        }
        if (atVar.k()) {
            this.c.a(h.a(hVar, a.EVENT_TYPE_ALIVE), a(atVar));
            atVar.a(false);
        } else if (atVar2.k()) {
            this.c.a(h.a(hVar, a.EVENT_TYPE_ALIVE), a(atVar2));
            atVar2.a(false);
        }
        atVar2.h();
        atVar.d();
        return true;
    }

    public void a(boolean z) {
        f().a(z);
    }

    public long c() {
        return f().c();
    }

    public aw d() {
        at f = f();
        return new aw().a(f.c()).b(f.j()).c(f.g()).a(f.a());
    }

    private at f() {
        return this.a.f() ? this.a : this.b;
    }

    aw a(at atVar) {
        return new aw().a(atVar.c()).a(atVar.a()).b(atVar.j()).c(atVar.e());
    }

    public aw e() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.c.i().a(currentTimeMillis, ay.BACKGROUND);
        return new aw().a(currentTimeMillis).a(ay.BACKGROUND).b(0).c(0);
    }
}
