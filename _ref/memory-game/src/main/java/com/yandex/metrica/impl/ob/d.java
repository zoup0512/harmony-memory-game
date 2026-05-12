package com.yandex.metrica.impl.ob;

import java.io.IOException;

public abstract class d {
    protected volatile int a = -1;

    public int a() {
        if (this.a < 0) {
            b();
        }
        return this.a;
    }

    public int b() {
        int c = c();
        this.a = c;
        return c;
    }

    protected int c() {
        return 0;
    }

    public void a(b bVar) throws IOException {
    }

    public static final byte[] a(d dVar) {
        byte[] bArr = new byte[dVar.b()];
        try {
            b a = b.a(bArr, 0, bArr.length);
            dVar.a(a);
            a.b();
            return bArr;
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return e.a(this);
    }
}
