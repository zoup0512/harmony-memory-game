package com.yandex.metrica.impl.ob;

import android.util.Base64;
import java.util.HashSet;
import java.util.Set;

class du {
    private do a;
    private String b;

    du(do doVar, String str) {
        this(doVar, str, null);
    }

    du(do doVar, String str, String[] strArr) {
        this.a = doVar;
        this.b = str;
        if (strArr != null) {
            this.a.a(this.b, strArr);
        }
    }

    public void a() {
        this.a.a(this.b, new HashSet());
    }

    Set<String> b() {
        Set<String> a = this.a.a(this.b);
        if (a == null) {
            return new HashSet();
        }
        return a;
    }

    long c() {
        return this.a.a();
    }

    void d() {
        this.a.b();
    }

    public boolean a(String str) {
        if (Base64.decode(str, 2).length == 32) {
            return this.a.a(this.b, str);
        }
        throw new IllegalArgumentException("pin has bad length");
    }
}
