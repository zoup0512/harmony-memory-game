package com.yandex.metrica.impl;

import android.content.ContentValues;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

abstract class l extends ag {
    static final ContentValues a = new ContentValues();
    final Map<String, String> b = new LinkedHashMap();
    final av c = new av();

    l() {
    }

    l a(ContentValues contentValues) {
        this.b.clear();
        for (Entry entry : contentValues.valueSet()) {
            this.b.put(entry.getKey(), entry.getValue().toString());
        }
        b(contentValues);
        return this;
    }

    void b(ContentValues contentValues) {
        String asString = contentValues.getAsString("report_request_parameters");
        if (!be.a(asString)) {
            try {
                a aVar = new a(asString);
                this.c.b(aVar.a("dId"));
                this.c.a(aVar.a("uId"));
                this.c.e(aVar.a("kitVer"));
                this.c.f(aVar.a("clientKitVer"));
                this.c.g(aVar.a("kitBuildNumber"));
                this.c.h(aVar.a("kitBuildType"));
                this.c.k(aVar.a("appVer"));
                this.c.m(aVar.a("appBuild"));
                this.c.i(aVar.a("osVer"));
                this.c.a(aVar.optInt("osApiLev", -1));
                this.c.j(aVar.a("lang"));
                this.c.q(aVar.a("root"));
            } catch (Exception e) {
            }
        }
    }

    public String a() {
        return super.a() + " [" + this.b.toString() + "]";
    }
}
