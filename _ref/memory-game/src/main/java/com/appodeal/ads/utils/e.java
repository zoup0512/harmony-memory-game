package com.appodeal.ads.utils;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class e implements X509TrustManager {
    protected static final char[] a = "0123456789ABCDEF".toCharArray();
    private List<byte[]> b = new ArrayList();
    private long c;
    private final TrustManager[] d = a();

    public e(List<String> list, long j) {
        this.c = j;
        for (String a : list) {
            this.b.add(a(a));
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        int length;
        for (TrustManager trustManager : this.d) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
        if (this.c == 0 || System.currentTimeMillis() <= this.c) {
            length = x509CertificateArr.length;
            int i = 0;
            while (i < length) {
                if (!a(x509CertificateArr[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    private boolean a(X509Certificate x509Certificate) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.b) {
                if (Arrays.equals(equals, digest)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            throw new CertificateException(e);
        }
    }

    private byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private TrustManager[] a() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }
}
