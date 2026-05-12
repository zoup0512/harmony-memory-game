package com.yandex.metrica.impl.ob;

import java.util.ArrayList;
import java.util.List;

class bo {
    private final String a;
    private final bs b;
    private int c;
    private final List<bn> d = new ArrayList();
    private final List<bn> e = new ArrayList();
    private final List<bn> f = new ArrayList();

    bo(String str, bs bsVar) {
        this.a = str;
        this.b = bsVar;
    }

    public void a(bn bnVar) {
        this.c += bnVar.c().b;
        this.d.add(bnVar);
        switch (bnVar.a(this.b)) {
            case THIS:
                this.e.add(bnVar);
                return;
            case OTHER:
                this.f.add(bnVar);
                return;
            default:
                return;
        }
    }

    public boolean a() {
        return !this.f.isEmpty();
    }

    public int b() {
        return this.d.size();
    }

    public String c() {
        return this.a;
    }

    public List<bn> d() {
        return this.d;
    }

    public Long e() {
        Long valueOf = Long.valueOf(Long.MAX_VALUE);
        Long l = valueOf;
        for (bn c : this.d) {
            valueOf = Long.valueOf(c.c().c);
            if (valueOf.compareTo(l) >= 0) {
                valueOf = l;
            }
            l = valueOf;
        }
        return l;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.a.equals(((bo) o).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
