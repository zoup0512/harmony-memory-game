package com.chartboost.sdk.impl;

import java.util.Map;

public abstract class w<T> implements Comparable<w<T>> {
    private volatile boolean a = false;
    public final a b;
    public final String c;
    public final v d;

    public enum a {
        a,
        b
    }

    public enum b {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public abstract y<T> a(ab abVar);

    public abstract void a(T t);

    public abstract Map<String, String> b();

    public /* synthetic */ int compareTo(Object x0) {
        return a((w) x0);
    }

    public w(a aVar, String str, v vVar) {
        this.b = aVar;
        this.c = str;
        this.d = vVar;
    }

    public void a(x xVar) {
        if (this.d != null) {
            this.d.a(xVar);
        }
    }

    public b c() {
        return b.NORMAL;
    }

    public Object a() {
        return null;
    }

    public String d() {
        return null;
    }

    public byte[] e() {
        return null;
    }

    public int a(w<T> wVar) {
        return -c().compareTo(wVar.c());
    }

    public void f() {
        this.a = true;
    }

    public boolean g() {
        return this.a;
    }
}
