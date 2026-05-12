package com.yandex.metrica.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class ag {
    protected String d;
    protected String e;
    protected int f = 1;
    protected byte[] g;
    protected int h;
    protected byte[] i;
    protected Map<String, List<String>> j;
    protected boolean k = false;

    static final class a {
        static final int a = ((int) TimeUnit.SECONDS.toMillis(30));
    }

    static final class b {
        static final long a = TimeUnit.SECONDS.toMillis(5);
        static final long b = TimeUnit.SECONDS.toMillis(15);
    }

    public abstract boolean b();

    public abstract boolean c();

    public abstract boolean d();

    public void e() {
    }

    public void f() {
    }

    public String g() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public int h() {
        return this.f;
    }

    public byte[] i() {
        return this.g;
    }

    public void a(byte[] bArr) {
        this.f = 2;
        this.g = bArr;
    }

    public int j() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public void b(byte[] bArr) {
        this.i = bArr;
    }

    Map<String, List<String>> k() {
        return this.j;
    }

    void a(Map<String, List<String>> map) {
        this.j = map;
    }

    public String l() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String a() {
        return getClass().getName();
    }

    public boolean m() {
        return false;
    }
}
