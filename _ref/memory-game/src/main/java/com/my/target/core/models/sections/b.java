package com.my.target.core.models.sections;

import com.my.target.core.enums.a;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.e;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: AppwallSection */
public final class b extends a<com.my.target.core.models.banners.b> {
    private String i;
    private boolean j = false;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private ArrayList<e> t = new ArrayList();

    public final void c(String str) {
        this.i = str;
    }

    public final void d(String str) {
        this.k = str;
    }

    public final void e(String str) {
        this.l = str;
    }

    public final void f(String str) {
        this.m = str;
    }

    public final String i() {
        return this.n;
    }

    public final void g(String str) {
        this.n = str;
    }

    public final void h(String str) {
        this.r = str;
    }

    public final String j() {
        return this.s;
    }

    public final void i(String str) {
        this.s = str;
    }

    public final void j(String str) {
        this.o = str;
    }

    public final String k() {
        return this.p;
    }

    public final void k(String str) {
        this.p = str;
    }

    public final String l() {
        return this.q;
    }

    public final void l(String str) {
        this.q = str;
    }

    public b(String str, int i) {
        super(a.c, str, i);
    }

    public final boolean a(c cVar) {
        if (!(cVar instanceof com.my.target.core.models.banners.b) || b(cVar.getId()) != null) {
            return false;
        }
        com.my.target.core.models.banners.b bVar = (com.my.target.core.models.banners.b) cVar;
        this.f.add(bVar);
        this.d++;
        if (!bVar.isHasNotification() || this.j) {
            return true;
        }
        this.j = true;
        return true;
    }

    public final boolean a(int i, c cVar) {
        return a(cVar);
    }

    public final boolean m(String str) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            com.my.target.core.models.banners.b bVar = (com.my.target.core.models.banners.b) it.next();
            if (bVar.getId().equals(str)) {
                return bVar.isHasNotification();
            }
        }
        return false;
    }

    public final boolean n(String str) {
        Iterator it = this.f.iterator();
        boolean z = false;
        while (it.hasNext()) {
            boolean z2;
            com.my.target.core.models.banners.b bVar = (com.my.target.core.models.banners.b) it.next();
            if (bVar.getId().equals(str)) {
                bVar.c(false);
            }
            if (z) {
                z2 = z;
            } else {
                z2 = bVar.isHasNotification();
            }
            z = z2;
        }
        if (z == this.j) {
            return false;
        }
        this.j = z;
        return true;
    }

    public final boolean a(e eVar) {
        if (o(eVar.a()) != null) {
            return false;
        }
        this.t.add(eVar);
        return true;
    }

    public final e o(String str) {
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a().equals(str)) {
                return eVar;
            }
        }
        return null;
    }
}
