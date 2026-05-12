package com.yandex.metrica.impl.ob;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class ds {
    private ed a;

    public ds(ed edVar) {
        this.a = edVar;
    }

    public SSLContext a() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, new TrustManager[]{this.a}, null);
        return instance;
    }
}
