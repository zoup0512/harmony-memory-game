package com.yandex.metrica.impl.ob;

import java.io.UnsupportedEncodingException;

public abstract class ei<T> extends en<T> {
    private final String a;

    protected abstract T b(em emVar) throws ek;

    static {
        String.format("application/json; charset=%s", new Object[]{"utf-8"});
    }

    public ei(int i, String str, String str2) {
        super(i, str);
        this.a = str2;
    }

    public byte[] c() {
        byte[] bArr = null;
        try {
            if (this.a != null) {
                bArr = this.a.getBytes("utf-8");
            }
        } catch (UnsupportedEncodingException e) {
        }
        return bArr;
    }
}
