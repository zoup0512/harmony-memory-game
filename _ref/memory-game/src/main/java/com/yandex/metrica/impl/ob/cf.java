package com.yandex.metrica.impl.ob;

import android.content.Context;

public class cf extends ca {
    static final ch c = new ch("PREF_KEY_DEVICE_ID_");
    static final ch d = new ch("PREF_KEY_UID_");
    static final ch e = new ch("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");
    static final ch f = new ch("PREF_KEY_PINNING_UPDATE_URL");
    private static final ch g = new ch("PREF_KEY_HOST_URL_");
    private static final ch h = new ch("PREF_KEY_REPORT_URL_");
    private static final ch i = new ch("PREF_KEY_GET_AD_URL");
    private static final ch j = new ch("PREF_KEY_REPORT_AD_URL");
    private static final ch k = new ch("PREF_KEY_STARTUP_OBTAIN_TIME_");
    private static final ch l = new ch("PREF_KEY_STARTUP_ENCODED_CLIDS_");
    private static final ch m = new ch("PREF_KEY_DISTRIBUTION_REFERRER_");
    private static final ch n = new ch("PREF_KEY_EASY_COLLECTING_ENABLED_");
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

    public cf(Context context) {
        this(context, null);
    }

    public cf(Context context, String str) {
        super(context, str);
    }

    protected void h() {
        super.h();
        this.o = new ch(c.a());
        this.p = new ch(d.a(), j());
        this.q = new ch(g.a(), j());
        this.r = new ch(h.a(), j());
        this.s = new ch(i.a(), j());
        this.t = new ch(j.a(), j());
        this.u = new ch(k.a(), j());
        this.v = new ch(l.a(), j());
        this.w = new ch(m.a(), j());
        this.x = new ch(n.a(), j());
    }

    protected String f() {
        return "_startupserviceinfopreferences";
    }

    public long a(long j) {
        return this.b.getLong(this.u.b(), j);
    }

    public String a(String str) {
        return this.b.getString(this.o.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.p.b(), str);
    }

    public String c(String str) {
        return this.b.getString(this.q.b(), str);
    }

    public String d(String str) {
        return this.b.getString(this.v.b(), str);
    }

    public String e(String str) {
        return this.b.getString(this.r.b(), str);
    }

    public String f(String str) {
        return this.b.getString(this.s.b(), str);
    }

    public String g(String str) {
        return this.b.getString(this.t.b(), str);
    }

    public String a() {
        return this.b.getString(this.w.a(), null);
    }

    public cf i(String str) {
        return (cf) a(this.p.b(), str);
    }

    public cf j(String str) {
        return (cf) a(this.o.b(), str);
    }

    public static void a(Context context, String str) {
        ci.a(context, "_startupserviceinfopreferences").edit().remove(h.a(str)).apply();
    }

    public static void b(Context context) {
        ci.a(context, "_startupserviceinfopreferences").edit().remove(c.a()).apply();
    }

    public void b() {
        h(this.o.b()).h(this.p.b()).h(this.q.b()).h(this.r.b()).h(this.s.b()).h(this.t.b()).h(this.u.b()).h(this.x.b()).h(this.v.b()).h(this.w.a()).h(e.a()).h(f.a()).k();
    }
}
