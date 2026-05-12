package com.my.target.core.models.sections;

import com.my.target.core.enums.a;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.h;

/* compiled from: StandardSection */
public final class g extends a<com.my.target.core.models.banners.g> {
    private final h i = new h();
    private int j = 60;
    private boolean k = true;
    private boolean l = true;
    private int m = 1;

    public final h i() {
        return this.i;
    }

    public g(String str, int i) {
        super(a.a, str, i);
    }

    public final boolean a(c cVar) {
        if (!(cVar instanceof com.my.target.core.models.banners.g) || b(cVar.getId()) != null) {
            return false;
        }
        this.f.add((com.my.target.core.models.banners.g) cVar);
        this.d++;
        return true;
    }

    public final boolean a(int i, c cVar) {
        return a(cVar);
    }

    public final void a(int i) {
        this.j = i;
    }

    public final void a(boolean z) {
        this.k = z;
    }

    public final void b(boolean z) {
        this.l = z;
    }

    public final void b(int i) {
        this.m = i;
    }

    public final int j() {
        return this.j;
    }

    public final boolean k() {
        return this.k;
    }

    public final boolean l() {
        return this.l;
    }

    public final int m() {
        return this.m;
    }
}
