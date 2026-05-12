package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.util.Map;

public class cb extends ca {
    private static final ch c = new ch("UUID");
    private static final ch d = new ch("DEVICEID");
    private static final ch e = new ch("DEVICEID_2");
    private static final ch f = new ch("DEVICEID_3");
    private static final ch g = new ch("AD_URL_GET");
    private static final ch h = new ch("AD_URL_REPORT");
    private static final ch i = new ch("HOST_URL");
    private static final ch j = new ch("SERVER_TIME_OFFSET");
    private static final ch k = new ch("STARTUP_REQUEST_TIME");
    private static final ch l = new ch("CLIDS");
    private ch m;
    private ch n;
    private ch o;
    private ch p;
    private ch q;
    private ch r;
    private ch s;
    private ch t;
    private ch u;
    private ch v;

    static {
        ch chVar = new ch("UUID_SOURCE");
    }

    public cb(Context context) {
        super(context, null);
    }

    protected void h() {
        super.h();
        this.m = new ch(c.a());
        this.n = new ch(d.a());
        this.o = new ch(e.a());
        this.p = new ch(f.a());
        this.q = new ch(g.a());
        this.r = new ch(h.a());
        this.s = new ch(i.a());
        this.t = new ch(j.a());
        this.u = new ch(k.a());
        this.v = new ch(l.a());
    }

    protected String f() {
        return "_startupinfopreferences";
    }

    public String a(String str) {
        return this.b.getString(this.m.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.p.b(), str);
    }

    public String a() {
        return this.b.getString(this.o.b(), this.b.getString(this.n.b(), ""));
    }

    public String c(String str) {
        return this.b.getString(this.q.b(), str);
    }

    public String d(String str) {
        return this.b.getString(this.r.b(), str);
    }

    public String e(String str) {
        return this.b.getString(this.s.b(), str);
    }

    public long a(long j) {
        return this.b.getLong(this.t.a(), j);
    }

    public long b(long j) {
        return this.b.getLong(this.u.b(), j);
    }

    public String f(String str) {
        return this.b.getString(this.v.b(), str);
    }

    public cb b() {
        return (cb) i();
    }

    public Map<String, ?> c() {
        return this.b.getAll();
    }
}
