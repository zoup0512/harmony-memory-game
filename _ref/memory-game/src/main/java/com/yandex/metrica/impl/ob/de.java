package com.yandex.metrica.impl.ob;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class de extends CertificateException {
    public de(dy dyVar) {
        super("There is not pinned certificates among chain " + a(dyVar.a()));
    }

    private static String a(X509Certificate[] x509CertificateArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (X509Certificate issuerDN : x509CertificateArr) {
            stringBuilder.append("ISSUER=" + issuerDN.getIssuerDN().toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
