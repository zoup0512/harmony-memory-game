package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.ac;
import com.yandex.metrica.impl.av;
import com.yandex.metrica.impl.bf;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.f;
import java.util.concurrent.Executor;

public class j implements k {
    private boolean a;
    private boolean b;
    private final HandlerThread c;
    private final Handler d;
    private final Context e;
    private final h f;
    private bj g;
    private bl h;
    private bh i;
    private bm j;
    private CounterConfiguration k;
    private final av l;
    private bf m;
    private ba n;
    private l o;
    private a p;
    private g q;
    private long r;
    private volatile av s;
    private final f t;
    private Runnable u;

    public av a() {
        return this.s;
    }

    public j(Context context, Executor executor, h hVar, CounterConfiguration counterConfiguration, g gVar) {
        this(context, executor, hVar, counterConfiguration, gVar, new av());
    }

    j(Context context, Executor executor, h hVar, CounterConfiguration counterConfiguration, g gVar, av avVar) {
        this.a = false;
        this.b = false;
        this.t = new f();
        this.u = new Runnable(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        };
        this.l = avVar;
        this.e = context.getApplicationContext();
        this.f = hVar;
        this.k = counterConfiguration;
        if (B()) {
            bd b = bc.a(this.e).b(l());
            this.g = new bj(b);
            this.i = new bh(b);
        }
        bc a = bc.a(this.e);
        this.h = new bl(a.b());
        this.j = new bm(a.c(), l().b());
        int libraryApiLevel = YandexMetrica.getLibraryApiLevel();
        if (B() && this.g.c() < ((long) libraryApiLevel)) {
            new i(this, new by(w())).a();
            this.g.s((long) libraryApiLevel).h();
        }
        if (B()) {
            this.n = new ba(this, bc.a(this.e).a(l()));
            this.r = this.g.c(0);
            this.s = new av(this, this.g);
            this.q = gVar;
            this.p = this.q.a(this, this.g);
        }
        this.c = new HandlerThread("TaskHandler [" + hVar.b() + "]");
        this.c.start();
        this.d = new Handler(this.c.getLooper());
        this.l.a(this);
        this.m = new bf(this, executor);
        if (this.n != null) {
            this.n.b((k) this);
        }
        this.o = new r(new o(this));
    }

    public void a(h hVar) {
        if (p().b()) {
            p().a(hVar, "Event received");
        }
        if (bg.c(this.l.a())) {
            this.l.b(this);
            this.o.a(hVar);
        }
    }

    public void b(h hVar) {
        this.o.a(hVar);
    }

    private boolean B() {
        return !this.f.c();
    }

    public void c(h hVar) {
        b(hVar, this.s.e());
    }

    public void d(h hVar) {
        if (this.s.b(hVar)) {
            if (this.i.d()) {
                b(h.a(hVar, p.a.EVENT_TYPE_START), this.s.d());
            } else if (hVar.c() == p.a.EVENT_TYPE_FIRST_ACTIVATION.a()) {
                b(hVar, this.s.d());
                b(h.a(hVar, p.a.EVENT_TYPE_START), this.s.d());
                return;
            }
        }
        b(hVar, this.s.d());
    }

    private void b(h hVar, aw awVar) {
        if (TextUtils.isEmpty(hVar.k())) {
            hVar.a(g());
        }
        this.n.a(hVar, awVar, this.p.b());
        this.m.b();
    }

    public synchronized void a(CounterConfiguration counterConfiguration) {
        this.k = counterConfiguration;
        this.l.d(this);
    }

    public void b() {
        if (((this.n.a() >= ((long) this.k.c()) ? 1 : 0) | this.a) != 0) {
            f();
            this.a = false;
        }
    }

    public synchronized void c() {
        this.b = true;
        bg.a(this.m);
        bg.a(this.n);
        this.d.removeCallbacksAndMessages(null);
        this.c.quit();
    }

    public void d() {
        this.d.postDelayed(this.u, ac.a);
    }

    public synchronized void e() {
        this.m.c();
    }

    public synchronized void f() {
        this.n.a();
        this.m.a();
    }

    public String g() {
        return this.g.a(null);
    }

    public av h() {
        return this.l;
    }

    public ba i() {
        return this.n;
    }

    public CounterConfiguration j() {
        return this.k;
    }

    public ResultReceiver k() {
        return this.k != null ? this.k.a() : null;
    }

    public h l() {
        return this.f;
    }

    public Context m() {
        return this.e;
    }

    public Handler n() {
        return this.d;
    }

    public synchronized boolean o() {
        return this.b;
    }

    public void a(CounterConfiguration.a aVar) {
        this.g.a(aVar).h();
        if (this.e.getPackageName().equals(this.f.b())) {
            this.h.a(aVar).h();
        }
    }

    public f p() {
        if (!(this.t.b() || this.k == null || !this.k.s())) {
            this.t.a();
        }
        return this.t;
    }

    public void e(h hVar) {
        b(true);
        d(hVar);
        t();
    }

    public void f(h hVar) {
        this.p.a(hVar.j());
        this.q.a(this.p.b(), this.g);
    }

    public void q() {
        this.p.a();
        this.q.b(this.p.b(), this.g);
    }

    public void a(String str) {
        this.g.b(str).h();
    }

    public void b(String str) {
        this.h.b(str).h();
        this.j.q(str).h();
    }

    public String r() {
        return this.h.a(null);
    }

    public void s() {
        this.h.b().h();
    }

    public boolean u() {
        boolean z;
        if ((System.currentTimeMillis() / 1000) - this.r > 86400) {
            z = true;
        } else {
            z = false;
        }
        return z && h().I();
    }

    public bh v() {
        return this.i;
    }

    public bz w() {
        return new bz(this.e, this.f.a());
    }

    public bm x() {
        return this.j;
    }

    public bj y() {
        return this.g;
    }

    public boolean z() {
        CounterConfiguration.a a = this.h.a();
        CounterConfiguration.a b = this.g.b();
        if (a == CounterConfiguration.a.TRUE && b == CounterConfiguration.a.TRUE) {
            return true;
        }
        return false;
    }

    public void a(boolean z) {
        this.j.b(z).h();
    }

    public boolean A() {
        return (this.k.w() && this.j.b()) ? false : true;
    }

    public void b(boolean z) {
        this.a = z;
    }

    public void b(CounterConfiguration counterConfiguration) {
        this.k.a(counterConfiguration);
    }

    public void a(h hVar, aw awVar) {
        b(h.a(hVar, p.a.EVENT_TYPE_ALIVE), awVar);
    }

    public void t() {
        this.r = System.currentTimeMillis() / 1000;
        this.g.l(this.r).h();
    }
}
