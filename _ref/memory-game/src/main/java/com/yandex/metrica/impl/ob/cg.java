package com.yandex.metrica.impl.ob;

import android.content.Context;

public class cg extends ca {
    private ch c;

    public cg(Context context) {
        this(context, null);
    }

    public cg(Context context, String str) {
        super(context, str);
    }

    protected void h() {
        super.h();
        this.c = new ch("LOCATION_TRACKING_ENABLED");
    }

    protected String f() {
        return "_serviceproviderspreferences";
    }

    public boolean a() {
        return this.b.getBoolean(this.c.b(), false);
    }

    public void b() {
        h(this.c.b()).k();
    }
}
