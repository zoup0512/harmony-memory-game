package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.ob.j;
import java.io.Closeable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class bf implements Closeable {
    private j a;
    private final av b;
    private final Object c = new Object();
    private final af d;
    private final bd e;
    private boolean f = false;
    private Runnable g = new Runnable(this) {
        final /* synthetic */ bf a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a();
        }
    };

    public bf(j jVar, Executor executor) {
        this.a = jVar;
        this.b = jVar.h();
        this.d = a(jVar, executor);
        this.d.start();
        this.e = a(this.a);
    }

    bd a(j jVar) {
        return new bd(jVar);
    }

    af a(j jVar, Executor executor) {
        af afVar = new af(executor);
        afVar.setName("NetworkCore [" + jVar.l() + "]");
        return afVar;
    }

    public void close() {
        synchronized (this.c) {
            if (!this.f) {
                d();
                if (this.d.isAlive()) {
                    this.d.a();
                }
                this.f = true;
            }
        }
    }

    public void a() {
        synchronized (this.c) {
            if (!this.f) {
                synchronized (this.c) {
                    if (!this.f) {
                        if (this.e.n()) {
                            this.d.a(this.e);
                        }
                        if (bg.c(this.b.a())) {
                            a(an.p(), Long.valueOf(-2));
                            a(ao.v(), null);
                        }
                    }
                }
                d();
            }
        }
    }

    private void a(a aVar, Long l) {
        List<ContentValues> a = this.a.i().a(l);
        if (a.isEmpty()) {
            a.add(l.a);
        }
        for (ContentValues contentValues : a) {
            try {
                this.d.a(aVar.a(this.a).a(contentValues));
            } catch (Exception e) {
                return;
            }
        }
    }

    private void d() {
        this.a.n().removeCallbacks(this.g);
    }

    public void b() {
        synchronized (this.c) {
            if (!this.f) {
                d();
                if (this.a.j().b() > 0) {
                    this.a.n().postDelayed(this.g, TimeUnit.SECONDS.toMillis((long) this.a.j().b()));
                }
            }
        }
    }

    public void c() {
        synchronized (this.c) {
            if (!(this.f || this.d.b(this.e))) {
                this.e.a(true);
                this.e.a(0);
                this.d.a(this.e);
            }
        }
    }
}
