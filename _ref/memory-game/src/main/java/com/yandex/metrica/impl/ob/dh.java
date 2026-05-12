package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.Set;

public class dh extends dd {
    private du a;

    public dh(du duVar) {
        super(duVar);
        this.a = duVar;
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        Set b = this.a.b();
        if (b.isEmpty()) {
            return false;
        }
        return b.contains(dz.a(x509CertificateArr[0]));
    }
}
