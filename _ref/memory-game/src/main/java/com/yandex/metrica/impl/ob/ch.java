package com.yandex.metrica.impl.ob;

public class ch {
    private final String a;
    private final String b;

    public ch(String str) {
        this(str, null);
    }

    public ch(String str, String str2) {
        this.a = str;
        this.b = a(str2);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String a(String str) {
        return str != null ? this.a + str : this.a;
    }
}
