package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;

class di implements dk {
    private final dd a;
    private final dd b;

    di(dr drVar) {
        this.a = new dd(drVar.a());
        this.b = new dd(drVar.c());
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        return this.a.a(x509CertificateArr);
    }

    public boolean b(X509Certificate[] x509CertificateArr) {
        return false;
    }

    public boolean c(X509Certificate[] x509CertificateArr) {
        return this.b.a(x509CertificateArr);
    }
}
