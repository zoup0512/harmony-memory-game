package com.my.target.core.models.banners;

import com.my.target.core.models.b;
import com.my.target.core.models.i;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: AbstractBanner */
public abstract class a extends b implements c {
    protected String a;
    protected final String b;
    protected String c;
    protected String d;
    protected boolean e;
    protected String f;
    protected String g;
    protected int h;
    protected int i;
    protected int j;
    protected String k;
    protected String l;
    protected String m;
    protected String n;
    protected String o;
    protected boolean p;
    protected ArrayList<i> q = new ArrayList();
    private boolean r;

    public String getId() {
        return this.a;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final String c() {
        return this.d;
    }

    public final void b(String str) {
        this.d = str;
    }

    public final void d() {
        this.e = true;
    }

    public boolean isAppInstalled() {
        return this.e;
    }

    public final String e() {
        return this.f;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final int f() {
        return this.h;
    }

    public final void a(int i) {
        this.h = i;
    }

    public final void d(String str) {
        this.g = str;
    }

    public final void b(int i) {
        this.i = i;
    }

    public final void c(int i) {
        this.j = i;
    }

    public String getAgeRestrictions() {
        return this.k;
    }

    public final void e(String str) {
        this.k = str;
    }

    public final void f(String str) {
        this.l = str;
    }

    public String getNavigationType() {
        return this.l;
    }

    public final void g(String str) {
        this.m = str;
    }

    public String getCtaText() {
        return this.m;
    }

    public String getAdvertisingLabel() {
        return this.n;
    }

    public final void h(String str) {
        this.n = str;
    }

    public final boolean g() {
        return this.p;
    }

    public final void a(boolean z) {
        this.p = z;
    }

    public final String h() {
        return this.o;
    }

    public final void i(String str) {
        this.o = str;
    }

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public synchronized boolean a(i iVar) {
        boolean z;
        if (this.q.contains(iVar)) {
            z = false;
        } else {
            this.q.add(iVar);
            z = true;
        }
        return z;
    }

    public final ArrayList<i> i() {
        return new ArrayList(this.q);
    }

    public final void a(ArrayList<i> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a((i) it.next());
        }
    }

    public final void b(boolean z) {
        this.r = z;
    }

    public final boolean j() {
        return this.r;
    }
}
