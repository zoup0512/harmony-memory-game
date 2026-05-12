package com.yandex.metrica.impl.ob;

import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.a.a;

public class bj extends bk {
    private static final ch a = new ch("SESSION_SLEEP_START");
    private static final ch b = new ch("SESSION_ID");
    private static final ch c = new ch("SESSION_COUNTER_ID");
    private static final ch d = new ch("SESSION_INIT_TIME");
    private static final ch e = new ch("SESSION_IS_ALIVE_REPORT_NEEDED");
    private static final ch f = new ch("BG_SESSION_ID");
    private static final ch g = new ch("BG_SESSION_SLEEP_START");
    private static final ch h = new ch("BG_SESSION_COUNTER_ID");
    private static final ch i = new ch("BG_SESSION_INIT_TIME");
    private static final ch j = new ch("BG_SESSION_IS_ALIVE_REPORT_NEEDED");
    private static final ch k = new ch("COLLECT_INSTALLED_APPS");
    private static final ch l = new ch("IDENTITY_SEND_TIME");
    private static final ch m = new ch("USER_INFO");
    private static final ch n = new ch("APP_ENVIRONMENT");
    private static final ch o = new ch("APP_ENVIRONMENT_REVISION");
    private static final ch p = new ch("LAST_MIGRATION_VERSION");

    static {
        ch chVar = new ch("SESSION_ALIVE_TIME");
    }

    public bj(bd bdVar) {
        super(bdVar);
    }

    public long a(long j) {
        return b(d.b(), j);
    }

    public long b(long j) {
        return b(i.b(), j);
    }

    public long c(long j) {
        return b(l.b(), j);
    }

    public long d(long j) {
        return b(b.b(), j);
    }

    public long e(long j) {
        return b(f.b(), j);
    }

    public long f(long j) {
        return b(c.b(), j);
    }

    public long g(long j) {
        return b(h.b(), j);
    }

    public a a() {
        a aVar;
        synchronized (this) {
            aVar = new a(b(n.b(), "{}"), b(o.b(), 0));
        }
        return aVar;
    }

    public long h(long j) {
        return b(a.b(), j);
    }

    public long i(long j) {
        return b(g.b(), j);
    }

    public boolean a(boolean z) {
        return b(e.b(), z);
    }

    public CounterConfiguration.a b() {
        return CounterConfiguration.a.a(Long.valueOf(b(k.b(), (long) CounterConfiguration.a.UNDEFINED.d)).intValue());
    }

    public String a(String str) {
        return b(m.b(), str);
    }

    public bj j(long j) {
        return (bj) a(d.b(), j);
    }

    public bj k(long j) {
        return (bj) a(i.b(), j);
    }

    public bj a(a aVar) {
        synchronized (this) {
            a(n.b(), aVar.a);
            a(o.b(), aVar.b);
        }
        return this;
    }

    public bj l(long j) {
        return (bj) a(l.b(), j);
    }

    public bj m(long j) {
        return (bj) a(b.b(), j);
    }

    public bj n(long j) {
        return (bj) a(f.b(), j);
    }

    public bj o(long j) {
        return (bj) a(c.b(), j);
    }

    public bj p(long j) {
        return (bj) a(h.b(), j);
    }

    public bj q(long j) {
        return (bj) a(a.b(), j);
    }

    public bj r(long j) {
        return (bj) a(g.b(), j);
    }

    public bj a(CounterConfiguration.a aVar) {
        return (bj) a(k.b(), (long) aVar.d);
    }

    public bj b(String str) {
        return (bj) a(m.b(), str);
    }

    public bj b(boolean z) {
        return (bj) a(e.b(), z);
    }

    public long c() {
        return b(p.b(), 0);
    }

    public bj s(long j) {
        return (bj) a(p.b(), j);
    }

    public boolean c(boolean z) {
        return b(j.b(), z);
    }

    public bj d(boolean z) {
        return (bj) a(j.b(), z);
    }
}
