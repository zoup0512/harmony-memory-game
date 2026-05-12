package com.yandex.metrica.impl.ob;

import java.io.IOException;

class df implements dr {
    private final du a;
    private final du b;

    public df(dx dxVar, String str) throws IOException {
        do dgVar = new dg(dxVar.b(), Integer.toString(str.hashCode()));
        this.a = new du(dgVar, "LIB-BLACK");
        this.b = new du(dgVar, "LIB-TRUST");
    }

    public du a() {
        return this.a;
    }

    public du b() {
        throw new UnsupportedOperationException("white list isn't supported in custom container");
    }

    public du c() {
        return this.b;
    }
}
