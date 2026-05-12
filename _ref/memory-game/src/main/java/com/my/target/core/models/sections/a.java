package com.my.target.core.models.sections;

import com.my.target.core.models.b;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.i;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: AbstractSection */
public abstract class a<T extends c> extends b implements f<T> {
    protected final String a;
    protected String b;
    protected int c;
    protected int d = 0;
    protected String e;
    protected ArrayList<T> f = new ArrayList();
    protected String g;
    protected ArrayList<i> h = new ArrayList();

    public final String a() {
        return this.a;
    }

    public int b() {
        return this.d;
    }

    public final String c() {
        return this.e;
    }

    public final int d() {
        return this.c;
    }

    public final String e() {
        return this.b;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final String f() {
        return this.g;
    }

    public a(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public final T b(String str) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.getId().equals(str)) {
                return cVar;
            }
        }
        return null;
    }

    public final ArrayList<T> g() {
        return new ArrayList(this.f);
    }

    public final synchronized boolean a(i iVar) {
        boolean z;
        if (this.h.contains(iVar)) {
            z = false;
        } else {
            this.h.add(iVar);
            z = true;
        }
        return z;
    }

    public final ArrayList<i> h() {
        return new ArrayList(this.h);
    }
}
