package com.yandex.metrica.impl.ob;

import android.content.Context;

public class cd extends ca {
    private static final ch c = new ch("SERVICE_API_LEVEL");
    private static final ch d = new ch("CLIENT_API_LEVEL");
    private ch e;
    private ch f;

    public cd(Context context) {
        super(context, null);
    }

    protected void h() {
        super.h();
        this.e = new ch(c.a());
        this.f = new ch(d.a());
    }

    public int a() {
        return this.b.getInt(this.e.b(), -1);
    }

    protected String f() {
        return "_migrationpreferences";
    }

    public cd b() {
        h(this.e.b());
        return this;
    }

    public cd c() {
        h(this.f.b());
        return this;
    }
}
