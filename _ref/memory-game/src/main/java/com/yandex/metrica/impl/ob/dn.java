package com.yandex.metrica.impl.ob;

import android.util.Log;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class dn {
    private static final String a = dn.class.getSimpleName();
    private ArrayList<Object> b = new ArrayList();
    private dd c;
    private final Lock d;
    private final Lock e;
    private final Condition f;
    private dy g;

    dn(du duVar) {
        this.c = new dh(duVar);
        this.d = new ReentrantLock();
        this.e = new ReentrantLock();
        this.f = this.d.newCondition();
    }

    void a(X509Certificate[] x509CertificateArr) {
        this.e.lock();
        try {
            if (this.c.a(x509CertificateArr)) {
                this.e.unlock();
                return;
            }
            this.g = new dy(x509CertificateArr);
            Object obj = null;
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                it.next();
                obj = 1;
            }
            if (obj != null) {
                Log.i(a, "waiting for trust issue resolve");
                this.d.lock();
                while (!this.g.b()) {
                    try {
                        this.f.await(30000, TimeUnit.MILLISECONDS);
                        this.g.c();
                    } catch (InterruptedException e) {
                    }
                }
                this.d.unlock();
            }
            this.e.unlock();
        } catch (Throwable th) {
            this.e.unlock();
        }
    }
}
