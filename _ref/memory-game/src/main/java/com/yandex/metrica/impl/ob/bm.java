package com.yandex.metrica.impl.ob;

public class bm extends bk {
    static final ch a = new ch("PREF_KEY_UID_");
    static final ch b = new ch("PREF_KEY_DEVICE_ID_");
    private static final ch c = new ch("PREF_KEY_HOST_URL_");
    private static final ch d = new ch("PREF_KEY_REPORT_URL_");
    private static final ch e = new ch("PREF_KEY_GET_AD_URL");
    private static final ch f = new ch("PREF_KEY_REPORT_AD_URL");
    private static final ch g = new ch("PREF_KEY_STARTUP_OBTAIN_TIME_");
    private static final ch h = new ch("PREF_KEY_STARTUP_ENCODED_CLIDS_");
    private static final ch i = new ch("PREF_KEY_DISTRIBUTION_REFERRER_");
    private static final ch j = new ch("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");
    private static final ch k = new ch("PREF_KEY_PINNING_UPDATE_URL");
    private static final ch l = new ch("PREF_KEY_EASY_COLLECTING_ENABLED_");
    private static final ch m = new ch("LAST_STARTUP_REQUEST_CLIDS");
    private static final ch n = new ch("LAST_STARTUP_CLIDS_SAVE_TIME");
    private ch A;
    private ch o;
    private ch p;
    private ch q;
    private ch r;
    private ch s;
    private ch t;
    private ch u;
    private ch v;
    private ch w;
    private ch x;
    private ch y;
    private ch z;

    public bm(bd bdVar, String str) {
        super(bdVar, str);
    }

    protected void f() {
        super.f();
        this.o = new ch(b.a());
        this.p = r(a.a());
        this.q = r(c.a());
        this.r = r(d.a());
        this.s = r(e.a());
        this.t = r(f.a());
        this.u = r(g.a());
        this.v = r(h.a());
        this.w = r(i.a());
        this.x = r(j.a());
        this.y = r(l.a());
        this.z = r(m.a());
        this.A = r(n.a());
    }

    public long a(long j) {
        return b(this.u.b(), j);
    }

    public String a(String str) {
        return b(this.o.b(), str);
    }

    public String b(String str) {
        return b(this.p.b(), str);
    }

    public String c(String str) {
        return b(this.q.b(), str);
    }

    public String d(String str) {
        return b(this.v.b(), str);
    }

    public String e(String str) {
        return b(this.r.b(), str);
    }

    public String f(String str) {
        return b(this.s.b(), str);
    }

    public String g(String str) {
        return b(this.t.b(), str);
    }

    public String a() {
        return b(this.w.b(), null);
    }

    public boolean b() {
        return b(this.x.b(), true);
    }

    public boolean c() {
        return b(this.y.b(), false);
    }

    public String h(String str) {
        return b(k.b(), str);
    }

    public bm i(String str) {
        return (bm) a(k.b(), str);
    }

    public bm j(String str) {
        return (bm) a(this.p.b(), str);
    }

    public bm k(String str) {
        return (bm) a(this.o.b(), str);
    }

    public bm l(String str) {
        return (bm) a(this.r.b(), str);
    }

    public bm m(String str) {
        return (bm) a(this.t.b(), str);
    }

    public bm n(String str) {
        return (bm) a(this.s.b(), str);
    }

    public bm o(String str) {
        return (bm) a(this.q.b(), str);
    }

    public bm a(boolean z) {
        return (bm) a(this.y.b(), z);
    }

    public bm b(long j) {
        return (bm) a(this.u.b(), j);
    }

    public bm p(String str) {
        return (bm) a(this.v.b(), str);
    }

    public bm q(String str) {
        return (bm) a(this.w.b(), str);
    }

    public bm b(boolean z) {
        return (bm) a(this.x.b(), z);
    }

    public String d() {
        return b(this.z.b(), null);
    }

    public long e() {
        return b(this.A.b(), -1);
    }

    public bm t(String str) {
        return (bm) a(this.z.b(), str).a(this.A.b(), System.currentTimeMillis());
    }
}
