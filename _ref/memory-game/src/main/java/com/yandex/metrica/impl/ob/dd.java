package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.Set;

class dd {
    private du a;

    public dd(du duVar) {
        this.a = duVar;
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        Set b = this.a.b();
        if (b.isEmpty()) {
            return false;
        }
        for (X509Certificate a : x509CertificateArr) {
            if (b.contains(dz.a(a))) {
                return true;
            }
        }
        return false;
    }
}
