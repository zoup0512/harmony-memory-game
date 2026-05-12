package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.b;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.i.a;
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bi;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.utils.f;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class bk implements a {
    private static bk a;
    private static n b = new n();
    private final Context c;
    private final as d;
    private z e;
    private ad f;
    private final ExecutorService g = Executors.newSingleThreadExecutor();
    private final co h;
    private final am i;
    private g j;
    private i k;

    private bk(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder("Initializing of Metrica");
        stringBuilder.append(", Debug type");
        stringBuilder.append(", Version 2.51");
        stringBuilder.append(", API Level 48");
        stringBuilder.append(", Dated 29.09.2016.");
        Log.i(f.e().c(), stringBuilder.toString());
        f.a(context);
        this.c = context.getApplicationContext();
        b.a.a(this.c);
        Handler handler = new Handler(Looper.getMainLooper());
        at atVar = new at(this.g, this.c, handler);
        bi biVar = new bi(bc.a(this.c).d());
        new f(biVar).a(this.c);
        this.h = new co(atVar, str, biVar);
        atVar.a(this.h);
        this.i = new am(atVar);
        j jVar = new j(handler);
        jVar.a(this);
        atVar.a(jVar);
        this.d = new a().a(this.c).a(this.h).a(atVar).a(handler).a(jVar).a();
        if (ax.b()) {
            this.j = new g(biVar, new c(this.c), this.g);
        }
    }

    void a() {
        ad adVar = new ad(Thread.getDefaultUncaughtExceptionHandler());
        adVar.a(new aq(this.d.a("20799a27-fa80-4b36-b2db-0f8141f24180"), new a() {
            public boolean a(Throwable th) {
                return bg.a(th);
            }
        }));
        this.f = adVar;
        Thread.setDefaultUncaughtExceptionHandler(this.f);
    }

    public static synchronized void a(Context context, e eVar) {
        synchronized (bk.class) {
            boolean i = b.i();
            e a = b.a(eVar);
            b(context, a);
            if (a.e == null) {
                if (Boolean.TRUE.equals(a.isLogEnabled())) {
                    f.e().a();
                }
                bk bkVar = a;
                bkVar.e = bkVar.d.a(a, i);
                a(bkVar.e.d().b().k());
            } else {
                a.e.a(a, i);
            }
            ((b) YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180")).a(1);
        }
    }

    public static synchronized void a(Context context) {
        synchronized (bk.class) {
            b(context, null);
        }
    }

    public static synchronized void b(Context context, e eVar) {
        synchronized (bk.class) {
            bg.a((Object) context, "App Context");
            if (a == null) {
                bk bkVar = new bk(context.getApplicationContext(), eVar != null ? eVar.a() : null);
                a = bkVar;
                v.a(bkVar.c);
                if (eVar != null) {
                    bkVar.h.a(eVar.b());
                    bkVar.h.a(eVar.e());
                    bkVar.h.b(eVar.f());
                }
                bkVar.h.d();
                bkVar.g.execute(new b(bkVar.c));
                a.a();
            }
        }
    }

    public static synchronized bk b() {
        bk bkVar;
        synchronized (bk.class) {
            if (a == null) {
                throw bh.a;
            }
            bkVar = a;
        }
        return bkVar;
    }

    static synchronized boolean c() {
        boolean z;
        synchronized (bk.class) {
            z = (a == null || a.e == null) ? false : true;
        }
        return z;
    }

    public b a(String str) {
        return this.d.a(str);
    }

    public void a(Application application) {
        this.e.a(application);
    }

    public void a(Activity activity) {
        this.e.a(activity);
    }

    public void b(Activity activity) {
        this.e.b(activity);
    }

    public void b(String str) {
        this.e.reportEvent(str);
    }

    public void a(String str, String str2) {
        this.e.reportEvent(str, str2);
    }

    public void a(String str, Map<String, Object> map) {
        this.e.reportEvent(str, (Map) map);
    }

    public void a(String str, Throwable th) {
        this.e.reportError(str, th);
    }

    public void a(Throwable th) {
        this.e.reportUnhandledException(th);
    }

    public void c(String str) {
        this.e.d(str);
    }

    public void d(String str) {
        this.i.a(str);
    }

    private static ab f() {
        return c() ? b().e : b;
    }

    public static void a(int i) {
        f().setSessionTimeout(i);
    }

    public static void a(boolean z) {
        if (c()) {
            bk b = b();
            if (z) {
                if (b.k == null) {
                    b.k = new aq(b.e, new a(b) {
                        final /* synthetic */ bk a;

                        {
                            this.a = r1;
                        }

                        public boolean a(Throwable th) {
                            return this.a.e.f();
                        }
                    });
                }
                b.f.a(b.k);
            } else {
                b.f.b(b.k);
            }
            b.e.c(z);
            return;
        }
        b.c(z);
    }

    public static void b(boolean z) {
        f().d(z);
    }

    public static void a(Location location) {
        f().a(location);
    }

    public static void c(boolean z) {
        f().b(z);
    }

    public static void e(String str) {
        f().a(str);
    }

    public static void d(boolean z) {
        f().a(z);
    }

    public static boolean d() {
        return f().h();
    }

    public static void b(String str, String str2) {
        f().a(str, str2);
    }

    public String e() {
        return this.h.a();
    }

    public void a(IIdentifierCallback iIdentifierCallback) {
        this.h.a(iIdentifierCallback);
    }

    public void a(int i, Bundle bundle) {
        switch (i) {
            case 1:
                this.h.a(bundle);
                if (this.j != null) {
                    this.j.a();
                    return;
                }
                return;
            case 2:
                this.h.b(bundle);
                return;
            default:
                return;
        }
    }

    public void a(int i, String str, String str2, Map<String, String> map) {
        this.e.a(i, str, str2, map);
    }
}
