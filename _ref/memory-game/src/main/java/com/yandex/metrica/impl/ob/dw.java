package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.List;

public class dw {
    private ec a;
    private boolean b;
    private boolean c;
    private long d;
    private String e;
    private List<X509Certificate> f;

    dw() {
        this.b = true;
        this.c = false;
        this.d = 86400000;
        this.e = "https://certificate.mobile.yandex.net/api/v1/pins";
    }

    public dw(ec ecVar) {
        this.b = true;
        this.c = false;
        this.d = 86400000;
        this.e = "https://certificate.mobile.yandex.net/api/v1/pins";
        this.a = ecVar;
    }

    public dw(ec ecVar, boolean z, boolean z2) {
        this(ecVar);
        this.b = z;
        this.c = z2;
    }

    public void a(String str, List<X509Certificate> list) {
        this.e = str;
        this.f = list;
    }

    long a() {
        return this.d;
    }

    String b() {
        return this.e;
    }

    List<X509Certificate> c() {
        return this.f;
    }

    ec d() {
        return this.a;
    }

    boolean e() {
        return this.c;
    }

    boolean f() {
        return this.b;
    }
}
