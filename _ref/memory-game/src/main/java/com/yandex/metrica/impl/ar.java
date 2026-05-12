package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.utils.c.a;
import java.util.HashMap;

class ar {
    static final HashMap<String, String> a = new HashMap<String, String>() {
        {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    };
    protected final CounterConfiguration b = new CounterConfiguration();
    protected o c;
    protected aj d;
    private q e = new q();

    protected ar() {
    }

    void a(a aVar) {
        this.c = new o(aVar);
    }

    CounterConfiguration b() {
        return this.b;
    }

    Bundle c() {
        return this.b.D();
    }

    void a(cp cpVar) {
        b(cpVar);
    }

    void d() {
        this.e.b();
    }

    boolean e() {
        return this.e.a();
    }

    boolean a() {
        return this.e.c();
    }

    void b(cp cpVar) {
        if (cpVar != null) {
            this.b.d(cpVar.a());
            this.b.e(cpVar.c());
            this.b.f(cpVar.b());
        }
    }

    void a(j jVar) {
        this.b.a((ResultReceiver) jVar);
    }

    void a(String str, String str2) {
        this.c.a(str, str2);
    }

    String f() {
        return this.c.a();
    }

    aj g() {
        return this.d;
    }

    void a(aj ajVar) {
        this.d = ajVar;
    }
}
