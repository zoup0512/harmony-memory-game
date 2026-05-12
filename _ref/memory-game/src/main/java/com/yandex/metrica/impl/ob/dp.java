package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.locks.ReentrantLock;

public class dp implements ed {
    private final a a;

    private static class a {
        private final dx a;
        private final dw b;
        private volatile dk[] c;
        private volatile dv d;
        private volatile dn e;
        private volatile dc f;
        private volatile dj g;

        public a(dx dxVar, dw dwVar) {
            this.a = dxVar;
            this.b = dwVar;
        }

        private dj a() {
            if (this.g == null) {
                synchronized (this) {
                    if (this.g == null) {
                        this.g = new dj(this.a, this.b);
                    }
                }
            }
            return this.g;
        }

        private dc b() {
            if (this.f == null) {
                synchronized (this) {
                    if (this.f == null) {
                        this.f = new dc();
                    }
                }
            }
            return this.f;
        }

        private dn c() {
            if (this.e == null) {
                synchronized (this) {
                    if (this.e == null) {
                        this.e = new dn(b().b());
                    }
                }
            }
            return this.e;
        }

        private dv d() {
            if (this.d == null) {
                synchronized (this) {
                    if (this.d == null) {
                        try {
                            this.d = new dv();
                        } catch (Throwable e) {
                            throw new IllegalStateException("Can't get system trust manager", e);
                        }
                    }
                }
            }
            return this.d;
        }

        private dk[] e() {
            if (this.c == null) {
                synchronized (this) {
                    if (this.c == null) {
                        di diVar = new di(a());
                        db dbVar = new db(b());
                        this.c = new dk[]{dbVar, diVar};
                    }
                }
            }
            return this.c;
        }
    }

    public dp(Context context, dw dwVar) {
        this(new dx(context), dwVar);
    }

    dp(dx dxVar, dw dwVar) {
        if (dwVar.d() == null) {
            throw new IllegalArgumentException("UUID provider must be set");
        }
        this.a = new a(dxVar, dwVar);
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new UnsupportedOperationException();
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null || chain.length == 0 || authType == null || authType.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        } else if (!b(a(chain))) {
            throw new CertificateException("Can't trust certificate chain");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.a.d().a();
    }

    private boolean c(X509Certificate[] x509CertificateArr) {
        ea d = this.a.a().d();
        if (d != null) {
            ReentrantLock a = d.a();
            a.lock();
            try {
                if (d(x509CertificateArr)) {
                    a.unlock();
                    return true;
                }
            } catch (CertificateException e) {
            }
            try {
                if (d.b()) {
                    try {
                        boolean d2 = d(x509CertificateArr);
                        return d2;
                    } catch (CertificateException e2) {
                    }
                }
                a.unlock();
            } finally {
                a.unlock();
            }
        }
        return false;
    }

    private boolean d(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean z;
        for (dk b : this.a.e()) {
            if (b.b(x509CertificateArr)) {
                z = true;
                break;
            }
        }
        z = false;
        if (!z) {
            for (dk b2 : this.a.e()) {
                if (b2.a(x509CertificateArr)) {
                    throw new CertificateException("There is blacklisted certificate in chain");
                }
            }
            if (!e(x509CertificateArr)) {
                return false;
            }
        }
        return true;
    }

    private boolean e(X509Certificate[] x509CertificateArr) throws CertificateException {
        for (dk c : this.a.e()) {
            if (c.c(x509CertificateArr)) {
                return true;
            }
        }
        this.a.c();
        throw new de(new dy(x509CertificateArr));
    }

    X509Certificate[] a(X509Certificate[] x509CertificateArr) {
        int i = 0;
        X509Certificate[] x509CertificateArr2 = x509CertificateArr;
        while (i < x509CertificateArr2.length) {
            X509Certificate[] x509CertificateArr3;
            int i2;
            for (int i3 = i + 1; i3 < x509CertificateArr2.length; i3++) {
                if (x509CertificateArr2[i].getIssuerDN().equals(x509CertificateArr2[i3].getSubjectDN())) {
                    if (i3 != i + 1) {
                        if (x509CertificateArr2 == x509CertificateArr) {
                            x509CertificateArr2 = (X509Certificate[]) x509CertificateArr.clone();
                        }
                        X509Certificate x509Certificate = x509CertificateArr2[i3];
                        x509CertificateArr2[i3] = x509CertificateArr2[i + 1];
                        x509CertificateArr2[i + 1] = x509Certificate;
                        x509CertificateArr3 = x509CertificateArr2;
                        i2 = 1;
                    } else {
                        x509CertificateArr3 = x509CertificateArr2;
                        i2 = 1;
                    }
                    if (i2 == 0) {
                        i++;
                        x509CertificateArr2 = x509CertificateArr3;
                    } else if (i + 1 != x509CertificateArr3.length) {
                        return x509CertificateArr3;
                    } else {
                        Object obj = new X509Certificate[(i + 1)];
                        System.arraycopy(x509CertificateArr3, 0, obj, 0, i + 1);
                        return obj;
                    }
                }
            }
            x509CertificateArr3 = x509CertificateArr2;
            i2 = 0;
            if (i2 == 0) {
                i++;
                x509CertificateArr2 = x509CertificateArr3;
            } else if (i + 1 != x509CertificateArr3.length) {
                return x509CertificateArr3;
            } else {
                Object obj2 = new X509Certificate[(i + 1)];
                System.arraycopy(x509CertificateArr3, 0, obj2, 0, i + 1);
                return obj2;
            }
        }
        return x509CertificateArr2;
    }

    private boolean b(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean d;
        try {
            if (this.a.d().a(x509CertificateArr)) {
                d = d(x509CertificateArr);
                this.a.a().e();
                return d;
            }
            throw new CertificateException("System doesn't trust certificate chain");
        } catch (de e) {
            d = c(x509CertificateArr);
            if (d) {
                return d;
            }
            this.a.c().a(x509CertificateArr);
            return d(x509CertificateArr);
        }
    }
}
