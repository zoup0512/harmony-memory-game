package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ba.a;

public class bp extends bn {
    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public /* bridge */ /* synthetic */ bq b() {
        return super.b();
    }

    public /* bridge */ /* synthetic */ a c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    bp(a aVar, bq bqVar, bq bqVar2) {
        if (bqVar != null) {
            bqVar2 = bqVar;
        }
        super(aVar, bqVar2);
    }

    public a a(bs bsVar) {
        bq b = b();
        if (bsVar.equals(b.d())) {
            return a.THIS;
        }
        if (b.d() != null) {
            return a.OTHER;
        }
        if (b.b()) {
            return a.OTHER;
        }
        return a.UNKNOWN;
    }
}
