package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.IMetricaService.Stub;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ac {
    public static final long a = TimeUnit.SECONDS.toMillis(10);
    private final Context b;
    private final Handler c;
    private final List<a> d = new CopyOnWriteArrayList();
    private volatile IMetricaService e = null;
    private final Runnable f = new Runnable(this) {
        final /* synthetic */ ac a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.f();
        }
    };
    private final ServiceConnection g = new ServiceConnection(this) {
        final /* synthetic */ ac a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            this.a.e = Stub.asInterface(service);
            ac.b(this.a);
        }

        public void onServiceDisconnected(ComponentName name) {
            this.a.e = null;
            this.a.g();
        }
    };

    interface a {
        void c();

        void d();
    }

    public ac(Context context, Handler handler) {
        this.b = context.getApplicationContext();
        this.c = handler;
    }

    public synchronized void a() {
        if (this.e == null) {
            try {
                this.b.bindService(ba.c(this.b), this.g, 1);
            } catch (Exception e) {
            }
        }
    }

    public void b() {
        this.c.removeCallbacks(this.f);
        this.c.postDelayed(this.f, a);
    }

    void c() {
        this.c.removeCallbacks(this.f);
    }

    public boolean d() {
        return this.e != null;
    }

    public IMetricaService e() {
        return this.e;
    }

    private synchronized void f() {
        if (this.b != null && d()) {
            try {
                this.b.unbindService(this.g);
                this.e = null;
            } catch (Exception e) {
            }
        }
        this.e = null;
        g();
    }

    private void g() {
        for (a d : this.d) {
            d.d();
        }
    }

    public void a(a aVar) {
        this.d.add(aVar);
    }

    static /* synthetic */ void b(ac acVar) {
        for (a c : acVar.d) {
            c.c();
        }
    }
}
