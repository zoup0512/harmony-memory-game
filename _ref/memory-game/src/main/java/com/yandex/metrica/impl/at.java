package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.au.a;
import com.yandex.metrica.impl.au.b;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.utils.f;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class at implements a, s {
    private final Context a;
    private ac b;
    private final NativeCrashesHelper c;
    private final ExecutorService d;
    private z e;
    private u f;
    private cp g;
    private final au h = new au(this);

    class AnonymousClass1 implements a {
        final /* synthetic */ Map a;
        final /* synthetic */ ar b;

        AnonymousClass1(Map map, ar arVar) {
            this.a = map;
            this.b = arVar;
        }

        public h a(h hVar) {
            return at.c(hVar.c(bg.b(this.a)), this.b);
        }
    }

    at(ExecutorService executorService, Context context, Handler handler) {
        this.b = new ac(context, handler);
        this.b.a((a) this);
        this.d = executorService;
        this.a = context;
        this.c = new NativeCrashesHelper(context);
        this.f = new u(context);
    }

    void a(z zVar) {
        this.e = zVar;
    }

    void a(cp cpVar) {
        this.g = cpVar;
        this.f.b(cpVar);
    }

    void a(j jVar) {
        this.f.a(jVar);
    }

    void a(boolean z, ar arVar) {
        arVar.b().b(z);
        this.c.a(z);
    }

    void a(String str, ar arVar) {
        f.e().a("Error received: native", new Object[0]);
        a(p.a(p.a.EVENT_TYPE_NATIVE_CRASH, str), arVar);
    }

    void a(String str) {
        a(str, this.e.d());
    }

    private static h c(h hVar, ar arVar) {
        if (hVar.c() == p.a.EVENT_TYPE_EXCEPTION_USER.a()) {
            hVar.e(arVar.f());
        }
        return hVar;
    }

    void a(h hVar, ar arVar) {
        a(c(hVar, arVar), arVar, null);
    }

    public void a(h hVar, ar arVar, Map<String, Object> map) {
        this.b.c();
        b bVar = new b(hVar, arVar);
        if (!bg.a((Map) map)) {
            bVar.a(new AnonymousClass1(map, arVar));
        }
        a(bVar);
    }

    public void e() {
        a(p.d(p.a.EVENT_TYPE_STARTUP), this.f);
    }

    public void b(String str) {
        a(p.d(str), this.f);
    }

    public void a(ar arVar) {
        a(p.a(arVar.g()), arVar);
    }

    public void c(String str) {
        this.f.b().g(str);
    }

    public void a(Map<String, String> map) {
        this.f.b().a((Map) map);
    }

    public void d(String str) {
        this.f.b().i(str);
    }

    void a(Throwable th, ar arVar) {
        String str;
        if (arVar.b().A()) {
            f.e().a("Error received: uncaught", new Object[0]);
        }
        this.b.c();
        String a = bg.a(null, th);
        if (th == null) {
            str = "";
        } else {
            str = th.getClass().getName();
        }
        h c = p.c(str, a);
        c.e(arVar.f());
        a(new b(c, arVar).a(true));
    }

    void f() {
        this.b.c();
    }

    void g() {
        this.b.b();
    }

    public void c() {
    }

    public void d() {
        a(false);
    }

    public void a(IMetricaService iMetricaService, h hVar, ar arVar) throws RemoteException {
        a(true);
        c(arVar);
        if (arVar.b().l()) {
            this.c.a(this, this.d);
        }
        iMetricaService.reportData(hVar.a(arVar.c()));
        if (this.e == null || this.e.e()) {
            this.b.b();
        }
    }

    public void a(String str, String str2, ar arVar) {
        a(new b(new h().a(p.a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED.a()).a(str, str2), arVar));
    }

    public void b(ar arVar) {
        a(new b(new h().a(p.a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED.a()), arVar));
    }

    void a(boolean z) {
        if (z) {
            y.a(this.a).a((Object) this);
        } else {
            y.a(this.a).b((Object) this);
        }
    }

    void c(ar arVar) {
        if (arVar.b().A()) {
            arVar.b().e(f.e().b());
        }
    }

    private void a(b bVar) {
        bVar.a().a(this.g);
        this.h.a(bVar);
    }

    public ac a() {
        return this.b;
    }

    public Context b() {
        return this.a;
    }
}
