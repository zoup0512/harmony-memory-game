package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicInteger;

public class dy {
    private static final AtomicInteger a = new AtomicInteger(0);
    private X509Certificate[] b;
    private boolean c = false;

    dy(X509Certificate[] x509CertificateArr) {
        a.incrementAndGet();
        this.b = x509CertificateArr;
    }

    public X509Certificate[] a() {
        return (X509Certificate[]) this.b.clone();
    }

    public boolean b() {
        return this.c;
    }

    void c() {
        this.c = true;
    }
}
