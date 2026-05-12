package com.yandex.metrica.impl.ob;

class bn {
    private final bq a;
    private final com.yandex.metrica.impl.ba.a b;

    enum a {
        THIS,
        OTHER,
        UNKNOWN
    }

    bn(com.yandex.metrica.impl.ba.a aVar, bq bqVar) {
        this.a = bqVar;
        this.b = aVar;
    }

    public String a() {
        return this.a.c();
    }

    public a a(bs bsVar) {
        return a.THIS;
    }

    public bq b() {
        return this.a;
    }

    public String toString() {
        return "Bid{mCredentials='" + this.a + '\'' + ", mDescriptor=" + this.b + '}';
    }

    public com.yandex.metrica.impl.ba.a c() {
        return this.b;
    }
}
