package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.util.Map;

public class bz extends ca {
    private final ch c = new ch("init_event_pref_key", j());
    private final ch d = new ch("init_event_pref_key");
    private final ch e = new ch("first_event_pref_key", j());
    private final ch f = new ch("fitst_event_description_key", j());

    public bz(Context context, String str) {
        super(context, str);
    }

    public void a() {
        a(this.c.b(), "DONE").k();
    }

    public String a(String str) {
        return this.b.getString(this.d.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.c.b(), str);
    }

    public String c(String str) {
        return this.b.getString(this.e.b(), str);
    }

    public void b() {
        a(this.d);
    }

    public void d(String str) {
        a(new ch("init_event_pref_key", str));
    }

    public void c() {
        a(this.c);
    }

    public void d() {
        a(this.e);
    }

    public String e(String str) {
        return this.b.getString(this.f.b(), str);
    }

    public void e() {
        a(this.f);
    }

    private void a(ch chVar) {
        this.b.edit().remove(chVar.b()).apply();
    }

    protected String f() {
        return "_initpreferences";
    }

    Map<String, ?> g() {
        return this.b.getAll();
    }

    static String f(String str) {
        return new ch("init_event_pref_key", str).b();
    }

    static String g(String str) {
        return str.replace("init_event_pref_key", "");
    }
}
