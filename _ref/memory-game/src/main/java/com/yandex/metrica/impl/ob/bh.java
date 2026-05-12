package com.yandex.metrica.impl.ob;

public class bh extends bk {
    private final ch a = new ch("init_event_pref_key");
    private final ch b = new ch("first_event_pref_key");
    private final ch c = new ch("first_event_description_key");

    public bh(bd bdVar) {
        super(bdVar);
    }

    public void a() {
        a(this.a.b(), "DONE").h();
    }

    public void b() {
        a(this.b.b(), "DONE").h();
    }

    public String a(String str) {
        return b(this.a.b(), str);
    }

    public String b(String str) {
        return b(this.b.b(), str);
    }

    public boolean c() {
        return a(null) != null;
    }

    public boolean d() {
        return b(null) != null;
    }

    public void c(String str) {
        a(this.c.b(), str).h();
    }

    public String d(String str) {
        return b(this.c.b(), str);
    }

    public void e() {
        s(this.c.b()).h();
    }
}
