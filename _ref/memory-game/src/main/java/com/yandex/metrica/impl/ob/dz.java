package com.yandex.metrica.impl.ob;

import android.os.Build.VERSION;
import android.util.Base64;
import com.mopub.common.Constants;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

class dz {
    static String a(X509Certificate x509Certificate) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded()), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    static el a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        TrustManager[] trustManagerArr;
        ef egVar;
        if (list == null || list.isEmpty()) {
            trustManagerArr = null;
        } else {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            for (int i = 0; i < list.size(); i++) {
                instance.setCertificateEntry("ca" + i, (Certificate) list.get(i));
            }
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            trustManagerArr = instance2.getTrustManagers();
        }
        SSLContext instance3 = SSLContext.getInstance("TLS");
        instance3.init(null, trustManagerArr, null);
        if (VERSION.SDK_INT >= 9) {
            egVar = new eg(instance3.getSocketFactory());
        } else {
            SocketFactory aVar = new a(instance3);
            SocketFactory socketFactory = PlainSocketFactory.getSocketFactory();
            HttpParams basicHttpParams = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(Constants.HTTP, socketFactory, 80));
            schemeRegistry.register(new Scheme(Constants.HTTPS, aVar, 443));
            egVar = new ee(new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams));
        }
        return new el(new ej(egVar));
    }

    static boolean a(dw dwVar) {
        return !"https://certificate.mobile.yandex.net/api/v1/pins".equals(dwVar.b());
    }
}
