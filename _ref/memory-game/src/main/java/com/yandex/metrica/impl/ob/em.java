package com.yandex.metrica.impl.ob;

import java.util.Map;

public class em {
    public final byte[] a;
    public final Map<String, String> b;

    public em(byte[] bArr, Map<String, String> map) {
        this.a = bArr;
        this.b = map;
    }

    public em(byte[] bArr, Map<String, String> map, byte b) {
        this(bArr, map);
    }
}
