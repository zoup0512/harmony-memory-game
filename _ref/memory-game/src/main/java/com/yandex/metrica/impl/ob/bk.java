package com.yandex.metrica.impl.ob;

public abstract class bk {
    private final bd a;
    private final String b;

    static {
        bk.class.getSimpleName();
    }

    public bk(bd bdVar) {
        this(bdVar, null);
    }

    public bk(bd bdVar, String str) {
        this.a = bdVar;
        this.b = str;
        f();
    }

    protected void f() {
    }

    public String g() {
        return this.b;
    }

    protected ch r(String str) {
        return new ch(str, g());
    }

    protected <T extends bk> T a(String str, String str2) {
        synchronized (this) {
            this.a.b(str, str2);
        }
        return this;
    }

    protected <T extends bk> T a(String str, long j) {
        synchronized (this) {
            this.a.b(str, j);
        }
        return this;
    }

    protected <T extends bk> T a(String str, int i) {
        synchronized (this) {
            this.a.b(str, i);
        }
        return this;
    }

    protected <T extends bk> T a(String str, boolean z) {
        synchronized (this) {
            this.a.b(str, z);
        }
        return this;
    }

    protected <T extends bk> T s(String str) {
        synchronized (this) {
            this.a.a(str);
        }
        return this;
    }

    public void h() {
        synchronized (this) {
            this.a.b();
        }
    }

    long b(String str, long j) {
        return this.a.a(str, j);
    }

    int b(String str, int i) {
        return this.a.a(str, i);
    }

    String b(String str, String str2) {
        return this.a.a(str, str2);
    }

    boolean b(String str, boolean z) {
        return this.a.a(str, z);
    }
}
