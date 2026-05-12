package com.yandex.metrica.impl.ob;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class dv {
    private Collection<X509TrustManager> a = new ArrayList();

    public dv() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(null);
        for (TrustManager trustManager : instance.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                this.a.add((X509TrustManager) trustManager);
            }
        }
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        try {
            for (X509TrustManager checkServerTrusted : this.a) {
                checkServerTrusted.checkServerTrusted(x509CertificateArr, "RSA");
            }
            return true;
        } catch (CertificateException e) {
            return false;
        }
    }

    public X509Certificate[] a() {
        ArrayList arrayList = new ArrayList();
        for (X509TrustManager acceptedIssuers : this.a) {
            arrayList.addAll(Arrays.asList(acceptedIssuers.getAcceptedIssuers()));
        }
        return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
    }
}
