package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import java.util.LinkedList;
import java.util.List;

public class i {
    private final j a;
    private final by b;
    private List<e> c = new LinkedList();

    private static abstract class e {
        private final j a;

        protected abstract boolean a();

        protected abstract void b();

        e(j jVar) {
            this.a = jVar;
        }

        j c() {
            return this.a;
        }

        void d() {
            if (a()) {
                b();
            }
        }
    }

    static class a extends e {
        private final cc a;
        private final bj b;

        a(j jVar) {
            super(jVar);
            this.a = new cc(jVar.m(), jVar.l().toString());
            this.b = jVar.y();
        }

        protected boolean a() {
            return this.a.g();
        }

        protected void b() {
            long e = this.a.e(-1);
            if (e != -1 && this.b.e(-1) == -1) {
                this.b.n(e);
            }
            e = this.a.b(Long.MIN_VALUE);
            if (e != Long.MIN_VALUE && this.b.b(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.b.k(e);
            }
            e = this.a.g(0);
            if (e != 0 && this.b.g(0) == 0) {
                this.b.p(e);
            }
            e = this.a.i(0);
            if (e != 0 && this.b.i(0) == 0) {
                this.b.r(e);
            }
            e = this.a.d(-1);
            if (-1 != e && this.b.d(-1) == -1) {
                this.b.m(e);
            }
            boolean booleanValue = this.a.a(true).booleanValue();
            if (!this.b.a(false) && booleanValue) {
                this.b.b(booleanValue);
            }
            e = this.a.a(Long.MIN_VALUE);
            if (e != Long.MIN_VALUE && this.b.a(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.b.j(e);
            }
            e = this.a.f(0);
            if (e != 0 && this.b.f(0) == 0) {
                this.b.o(e);
            }
            e = this.a.h(0);
            if (e != 0 && this.b.h(0) == 0) {
                this.b.q(e);
            }
            com.yandex.metrica.impl.a.a a = this.a.a();
            if (a != null) {
                this.b.a(a);
            }
            String a2 = this.a.a(null);
            if (!TextUtils.isEmpty(a2) && TextUtils.isEmpty(this.b.a(null))) {
                this.b.b(a2);
            }
            com.yandex.metrica.CounterConfiguration.a b = this.a.b();
            if (b != com.yandex.metrica.CounterConfiguration.a.UNDEFINED && this.b.b() == com.yandex.metrica.CounterConfiguration.a.UNDEFINED) {
                this.b.a(b);
            }
            e = this.a.c(Long.MIN_VALUE);
            if (e != Long.MIN_VALUE && this.b.c(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.b.l(e);
            }
            this.b.h();
            this.a.l();
        }
    }

    private static abstract class f extends e {
        private by a;

        f(j jVar, by byVar) {
            super(jVar);
            this.a = byVar;
        }

        public by e() {
            return this.a;
        }
    }

    static class b extends f {
        b(j jVar, by byVar) {
            super(jVar, byVar);
        }

        protected boolean a() {
            return c().j().A();
        }

        protected void b() {
            e().a();
        }
    }

    static class c extends e {
        private final bz a;
        private final bh b;

        c(j jVar) {
            super(jVar);
            this.a = jVar.w();
            this.b = jVar.v();
        }

        protected boolean a() {
            return "DONE".equals(this.a.c(null)) || "DONE".equals(this.a.b(null));
        }

        protected void b() {
            if ("DONE".equals(this.a.c(null))) {
                this.b.b();
            }
            Object e = this.a.e(null);
            if (!TextUtils.isEmpty(e)) {
                this.b.c(e);
            }
            if ("DONE".equals(this.a.b(null))) {
                this.b.a();
            }
            this.a.d();
            this.a.e();
            this.a.c();
        }
    }

    static class d extends f {
        d(j jVar, by byVar) {
            super(jVar, byVar);
        }

        protected boolean a() {
            return c().v().a(null) == null;
        }

        protected void b() {
            CounterConfiguration j = c().j();
            by e = e();
            if (j.A()) {
                e.c();
            } else {
                e.b();
            }
        }
    }

    i(j jVar, by byVar) {
        this.a = jVar;
        this.b = byVar;
        this.c.add(new b(this.a, this.b));
        this.c.add(new d(this.a, this.b));
        this.c.add(new c(this.a));
        this.c.add(new a(this.a));
    }

    void a() {
        if (!by.a.values().contains(this.a.l().a())) {
            for (e d : this.c) {
                d.d();
            }
        }
    }
}
