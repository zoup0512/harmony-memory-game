package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.a.a;

public class cc extends ca {
    public static final ch c = new ch("APP_ENVIRONMENT");
    public static final ch d = new ch("APP_ENVIRONMENT_REVISION");
    private static final ch e = new ch("SESSION_SLEEP_START_");
    private static final ch f = new ch("SESSION_ID_");
    private static final ch g = new ch("SESSION_COUNTER_ID_");
    private static final ch h = new ch("SESSION_INIT_TIME_");
    private static final ch i = new ch("SESSION_ALIVE_TIME_");
    private static final ch j = new ch("SESSION_IS_ALIVE_REPORT_NEEDED_");
    private static final ch k = new ch("BG_SESSION_ID_");
    private static final ch l = new ch("BG_SESSION_SLEEP_START_");
    private static final ch m = new ch("BG_SESSION_COUNTER_ID_");
    private static final ch n = new ch("BG_SESSION_INIT_TIME_");
    private static final ch o = new ch("COLLECT_INSTALLED_APPS_");
    private static final ch p = new ch("IDENTITY_SEND_TIME_");
    private static final ch q = new ch("USER_INFO_");
    private static final ch r = new ch("REFERRER_");
    private static final ch s = new ch("APP_ENVIRONMENT_");
    private static final ch t = new ch("APP_ENVIRONMENT_REVISION_");
    private ch A;
    private ch B;
    private ch C;
    private ch D;
    private ch E;
    private ch F;
    private ch G;
    private ch H;
    private ch I;
    private ch J;
    private ch u;
    private ch v;
    private ch w;
    private ch x;
    private ch y;
    private ch z;

    public cc(Context context, String str) {
        super(context, str);
        d();
        a(-1);
        b(0);
        c(0);
    }

    protected void h() {
        super.h();
        this.u = new ch(e.a(), j());
        this.v = new ch(f.a(), j());
        this.w = new ch(g.a(), j());
        this.x = new ch(h.a(), j());
        this.y = new ch(i.a(), j());
        this.z = new ch(j.a(), j());
        this.A = new ch(k.a(), j());
        this.B = new ch(l.a(), j());
        this.C = new ch(m.a(), j());
        this.D = new ch(n.a(), j());
        this.E = new ch(p.a(), j());
        this.F = new ch(o.a(), j());
        this.G = new ch(q.a(), j());
        this.H = new ch(r.a(), j());
        this.I = new ch(s.a(), j());
        this.J = new ch(t.a(), j());
    }

    protected String f() {
        return "_boundentrypreferences";
    }

    public long a(long j) {
        return a(this.x.b(), j);
    }

    public long b(long j) {
        return a(this.D.b(), j);
    }

    public long c(long j) {
        return a(this.E.b(), j);
    }

    public long d(long j) {
        return a(this.v.b(), j);
    }

    public long e(long j) {
        return a(this.A.b(), j);
    }

    public long f(long j) {
        return a(this.w.b(), j);
    }

    private long a(String str, long j) {
        return this.b.getLong(str, j);
    }

    public long g(long j) {
        return a(this.C.b(), j);
    }

    public a a() {
        a aVar;
        synchronized (this) {
            if (this.b.contains(this.I.b()) && this.b.contains(this.J.b())) {
                aVar = new a(this.b.getString(this.I.b(), "{}"), this.b.getLong(this.J.b(), 0));
            } else {
                aVar = null;
            }
        }
        return aVar;
    }

    public long h(long j) {
        return a(this.u.b(), j);
    }

    public long i(long j) {
        return a(this.B.b(), j);
    }

    public Boolean a(boolean z) {
        return Boolean.valueOf(this.b.getBoolean(this.z.b(), z));
    }

    public CounterConfiguration.a b() {
        return CounterConfiguration.a.a(this.b.getInt(this.F.b(), CounterConfiguration.a.UNDEFINED.d));
    }

    public String a(String str) {
        return this.b.getString(this.G.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.H.b(), str);
    }

    public cc a(a aVar) {
        synchronized (this) {
            a(this.I.b(), aVar.a);
            a(this.J.b(), Long.valueOf(aVar.b));
        }
        return this;
    }

    public cc c() {
        return (cc) h(this.H.b());
    }

    public void a(int i) {
        ci.a(this.b, this.y.b(), i);
    }

    public void b(int i) {
        ci.a(this.b, this.u.b(), i);
    }

    public void c(int i) {
        ci.a(this.b, this.w.b(), i);
    }

    public void d() {
        SharedPreferences sharedPreferences = this.b;
        String b = this.F.b();
        if (sharedPreferences != null && sharedPreferences.contains(b)) {
            try {
                sharedPreferences.getBoolean(b, false);
                sharedPreferences.edit().remove(b).putInt(b, CounterConfiguration.a.UNDEFINED.d).apply();
            } catch (ClassCastException e) {
            }
        }
    }

    public cc e() {
        return (cc) h(this.F.b());
    }

    public boolean g() {
        return this.b.contains(this.x.b()) || this.b.contains(this.y.b()) || this.b.contains(this.z.b()) || this.b.contains(this.u.b()) || this.b.contains(this.v.b()) || this.b.contains(this.w.b()) || this.b.contains(this.D.b()) || this.b.contains(this.B.b()) || this.b.contains(this.A.b()) || this.b.contains(this.C.b()) || this.b.contains(this.I.b()) || this.b.contains(this.F.b()) || this.b.contains(this.G.b()) || this.b.contains(this.H.b()) || this.b.contains(this.E.b());
    }

    public void l() {
        this.b.edit().remove(this.D.b()).remove(this.C.b()).remove(this.A.b()).remove(this.B.b()).remove(this.x.b()).remove(this.w.b()).remove(this.v.b()).remove(this.u.b()).remove(this.z.b()).remove(this.y.b()).remove(this.G.b()).remove(this.F.b()).remove(this.I.b()).remove(this.J.b()).remove(this.H.b()).remove(this.E.b()).apply();
    }
}
