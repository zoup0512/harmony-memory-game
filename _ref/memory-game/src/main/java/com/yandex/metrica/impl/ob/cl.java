package com.yandex.metrica.impl.ob;

public class cl {
    protected int a = 0;
    private final int b;
    private boolean c;

    public cl(int i) {
        this.b = i;
    }

    public boolean b() {
        return this.c && this.a < this.b;
    }

    public void a() {
        this.a++;
        this.c = false;
    }

    public void c() {
        this.c = true;
    }
}
