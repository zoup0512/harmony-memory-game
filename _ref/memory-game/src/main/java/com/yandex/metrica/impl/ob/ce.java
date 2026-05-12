package com.yandex.metrica.impl.ob;

import android.content.Context;

public class ce extends ca {
    private static final ch c = new ch("PREF_KEY_OFFSET");
    private ch d;

    public ce(Context context, String str) {
        super(context, str);
    }

    protected void h() {
        super.h();
        this.d = new ch(c.a(), null);
    }

    protected String f() {
        return "_servertimeoffset";
    }

    public long a(int i) {
        return this.b.getLong(this.d.b(), (long) i);
    }

    public void a() {
        h(this.d.b()).k();
    }
}
