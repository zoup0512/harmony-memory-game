package com.yandex.metrica.impl;

import com.yandex.metrica.impl.utils.c;
import java.util.HashMap;

public class e extends h {
    private final HashMap<a, Integer> f;
    private final c g;

    private enum a {
        NAME,
        VALUE,
        USER_INFO
    }

    public e() {
        this.f = new HashMap();
        this.g = new c();
    }

    public e(String str, int i) {
        this("", str, i);
    }

    public e(String str, String str2, int i) {
        this(str, str2, i, 0);
    }

    public e(String str, String str2, int i, int i2) {
        this.f = new HashMap();
        this.g = new c();
        this.b = i(str);
        this.a = h(str2);
        this.c = i;
        this.d = i2;
    }

    private void a(String str, String str2, a aVar) {
        if (this.g.a(str, str2)) {
            this.f.put(aVar, Integer.valueOf(be.c(str).length - be.c(str2).length));
        } else {
            this.f.remove(aVar);
        }
        this.e = 0;
        for (Integer num : this.f.values()) {
            this.e = num.intValue() + this.e;
        }
    }

    private String h(String str) {
        String a = this.g.a(str, 1000, "event name");
        a(str, a, a.NAME);
        return a;
    }

    private String i(String str) {
        String a = this.g.a(str, 245760);
        a(str, a, a.VALUE);
        return a;
    }

    public h a(String str) {
        String a = this.g.a(str, 10000, "UserInfo");
        a(str, a, a.USER_INFO);
        return super.a(a);
    }

    public h b(String str) {
        return super.b(h(str));
    }

    public h c(String str) {
        return super.c(i(str));
    }
}
