package com.flurry.sdk;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.Toast;
import com.flurry.sdk.it.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class is {
    public static final String a = is.class.getSimpleName();
    public static int b;
    public static int c;
    public static AtomicInteger d;
    static kf<List<it>> e;
    private static is f;
    private static Map<Integer, it> g;
    private final AtomicInteger h;
    private long i;
    private kh<jq> j = new kh<jq>(this) {
        final /* synthetic */ is a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            jq jqVar = (jq) kgVar;
            km.a(4, is.a, "onNetworkStateChanged : isNetworkEnable = " + jqVar.a);
            if (jqVar.a) {
                jy.a().b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        iu.a().b();
                    }
                });
            }
        }
    };

    private is() {
        g = new HashMap();
        this.h = new AtomicInteger(0);
        d = new AtomicInteger(0);
        if (c == 0) {
            c = 600000;
        }
        if (b == 0) {
            b = 15;
        }
        this.i = jy.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).getLong("timeToSendNextPulseReport", 0);
        if (e == null) {
            f();
        }
        ki.a().a("com.flurry.android.sdk.NetworkStateEvent", this.j);
    }

    private static void f() {
        e = new kf(jy.a().a.getFileStreamPath(".yflurryanongoingpulsecallbackreporter"), ".yflurryanongoingpulsecallbackreporter", 2, new lj<List<it>>() {
            public final lg<List<it>> a(int i) {
                return new lf(new a());
            }
        });
    }

    public static void a(int i) {
        b = i;
    }

    public static void b(int i) {
        c = i;
    }

    public final synchronized void a(it itVar) {
        if (itVar == null) {
            km.a(3, a, "Must add valid PulseCallbackAsyncReportInfo");
        } else {
            km.a(3, a, "Adding and sending " + itVar.c + " report to PulseCallbackManager.");
            if (itVar.a().size() != 0) {
                if (this.i == 0) {
                    this.i = System.currentTimeMillis() + ((long) c);
                    jy.a().b(new Runnable(this) {
                        final /* synthetic */ is a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            this.a.g();
                        }
                    });
                }
                int h = h();
                itVar.b = h;
                g.put(Integer.valueOf(h), itVar);
                for (ip b : itVar.a()) {
                    hr.a().c.b((kw) b);
                }
            }
        }
    }

    private void g() {
        Editor edit = jy.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).edit();
        edit.putLong("timeToSendNextPulseReport", this.i);
        edit.apply();
    }

    private synchronized int h() {
        return this.h.incrementAndGet();
    }

    public final synchronized void b(it itVar) {
        if (itVar == null) {
            km.a(3, a, "Must add valid PulseCallbackAsyncReportInfo");
        } else {
            if (this.i == 0) {
                this.i = System.currentTimeMillis() + ((long) c);
                jy.a().b(new Runnable(this) {
                    final /* synthetic */ is a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        this.a.g();
                    }
                });
            }
            int h = h();
            itVar.b = h;
            g.put(Integer.valueOf(h), itVar);
            for (ip ipVar : itVar.a()) {
                Iterator it = ipVar.f.iterator();
                while (it.hasNext()) {
                    it.next();
                    d.incrementAndGet();
                    if (j()) {
                        km.a(3, a, "Max Callback Attempts threshold reached. Sending callback logging reports");
                        l();
                    }
                }
            }
            if (k()) {
                km.a(3, a, "Time threshold reached. Sending callback logging reports");
                l();
            }
            km.a(3, a, "Restoring " + itVar.c + " report to PulseCallbackManager. Number of stored completed callbacks: " + d.get());
        }
    }

    public final synchronized void a(final iq iqVar) {
        km.a(3, a, iqVar.l.g.c + " report sent successfully to " + iqVar.l.l);
        iqVar.f = ir.COMPLETE;
        iqVar.g = "";
        c(iqVar);
        if (km.c() <= 3 && km.d()) {
            jy.a().a(new Runnable(this) {
                final /* synthetic */ is b;

                public final void run() {
                    Toast.makeText(jy.a().a, "PulseCallbackReportInfo HTTP Response Code: " + iqVar.e + " for url: " + iqVar.l.r, 1).show();
                }
            });
        }
    }

    private void c(iq iqVar) {
        iqVar.d = true;
        iqVar.a();
        d.incrementAndGet();
        iqVar.l.c();
        km.a(3, a, iqVar.l.g.c + " report to " + iqVar.l.l + " finalized.");
        a();
        i();
    }

    public final void a() {
        jy.a().b(new Runnable(this) {
            final /* synthetic */ is a;

            {
                this.a = r1;
            }

            public final void run() {
                is.c();
                List b = is.b();
                if (is.e == null) {
                    is.f();
                }
                is.e.a(b);
            }
        });
    }

    private void i() {
        if (j() || k()) {
            km.a(3, a, "Threshold reached. Sending callback logging reports");
            l();
        }
    }

    public static List<it> b() {
        return new ArrayList(g.values());
    }

    public static synchronized is c() {
        is isVar;
        synchronized (is.class) {
            if (f == null) {
                f = new is();
            }
            isVar = f;
        }
        return isVar;
    }

    private static boolean j() {
        return d.intValue() >= b;
    }

    private boolean k() {
        return System.currentTimeMillis() > this.i;
    }

    private void l() {
        Iterator it;
        for (it itVar : b()) {
            int i = 0;
            for (ip ipVar : itVar.a()) {
                Iterator it2 = ipVar.f.iterator();
                while (it2.hasNext()) {
                    iq iqVar = (iq) it2.next();
                    if (iqVar.j) {
                        it2.remove();
                    } else if (!iqVar.f.equals(ir.PENDING_COMPLETION)) {
                        iqVar.j = true;
                        i = true;
                    }
                }
            }
            if (i != 0) {
                iu.a().a(itVar);
            }
        }
        iu.a().b();
        this.i = System.currentTimeMillis() + ((long) c);
        g();
        for (it itVar2 : b()) {
            if (itVar2.b()) {
                c(itVar2.b);
            } else {
                for (ip ipVar2 : itVar2.a()) {
                    if (ipVar2.m) {
                        itVar2.d.remove(Long.valueOf(ipVar2.a));
                    } else {
                        it = ipVar2.f.iterator();
                        while (it.hasNext()) {
                            if (((iq) it.next()).j) {
                                it.remove();
                            }
                        }
                    }
                }
            }
        }
        d = new AtomicInteger(0);
        a();
    }

    private synchronized void c(int i) {
        km.a(3, a, "Removing report " + i + " from PulseCallbackManager");
        g.remove(Integer.valueOf(i));
    }

    public final synchronized boolean a(iq iqVar, String str) {
        boolean z = true;
        synchronized (this) {
            boolean z2;
            iqVar.h++;
            iqVar.i = System.currentTimeMillis();
            if (iqVar.h > iqVar.l.c) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 || TextUtils.isEmpty(str)) {
                km.a(3, a, "Maximum number of redirects attempted. Aborting: " + iqVar.l.g.c + " report to " + iqVar.l.l);
                iqVar.f = ir.INVALID_RESPONSE;
                iqVar.g = "";
                c(iqVar);
                z = false;
            } else {
                km.a(3, a, "Report to " + iqVar.l.l + " redirecting to url: " + str);
                iqVar.l.r = str;
                a();
            }
        }
        return z;
    }

    public final synchronized void b(iq iqVar) {
        km.a(3, a, "Maximum number of attempts reached. Aborting: " + iqVar.l.g.c);
        iqVar.f = ir.TIMEOUT;
        iqVar.i = System.currentTimeMillis();
        iqVar.g = "";
        c(iqVar);
    }

    public final synchronized boolean b(iq iqVar, String str) {
        boolean z = false;
        synchronized (this) {
            boolean z2;
            iqVar.f = ir.INVALID_RESPONSE;
            iqVar.i = System.currentTimeMillis();
            if (str == null) {
                str = "";
            }
            iqVar.g = str;
            kw kwVar = iqVar.l;
            if (kwVar.p >= kwVar.b) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                km.a(3, a, "Maximum number of attempts reached. Aborting: " + iqVar.l.g.c + " report to " + iqVar.l.l);
                c(iqVar);
            } else if (mc.h(iqVar.l.r)) {
                km.a(3, a, "Retrying callback to " + iqVar.l.g.c + " in: " + (iqVar.l.h / 1000) + " seconds.");
                iqVar.a();
                d.incrementAndGet();
                a();
                i();
                z = true;
            } else {
                km.a(3, a, "Url: " + iqVar.l.r + " is invalid.");
                c(iqVar);
            }
        }
        return z;
    }

    public static List<it> d() {
        if (e == null) {
            f();
        }
        return (List) e.a();
    }
}
