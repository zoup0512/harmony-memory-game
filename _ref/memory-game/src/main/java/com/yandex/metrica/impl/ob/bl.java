package com.yandex.metrica.impl.ob;

import com.yandex.metrica.CounterConfiguration.a;

public class bl extends bk {
    static final ch a = new ch("LOCATION_TRACKING_ENABLED");
    static final ch b = new ch("COLLECT_INSTALLED_APPS");
    static final ch c = new ch("REFERRER");
    static final ch d = new ch("PREF_KEY_OFFSET");
    private static final ch e = new ch("LAST_MIGRATION_VERSION");

    public bl(bd bdVar) {
        super(bdVar);
    }

    public a a() {
        return a.a(Long.valueOf(b(b.b(), (long) a.UNDEFINED.d)).intValue());
    }

    public String a(String str) {
        return b(c.b(), str);
    }

    public bl a(a aVar) {
        return (bl) a(b.b(), (long) aVar.d);
    }

    public bl b(String str) {
        return (bl) a(c.b(), str);
    }

    public bl b() {
        return (bl) s(c.b());
    }

    public int a(int i) {
        return b(e.b(), i);
    }

    public bl b(int i) {
        return (bl) a(e.b(), i);
    }

    public void a(boolean z) {
        a(a.b(), z).h();
    }

    public boolean c() {
        return b(a.b(), false);
    }

    public long c(int i) {
        return b(d.b(), (long) i);
    }

    public bl a(long j) {
        return (bl) a(d.b(), j);
    }
}
