package com.yandex.metrica.impl.ob;

public class ck extends cl {
    private cj b;

    public ck(int i) {
        super(i);
        this.b = new cj(i);
    }

    public void a() {
        try {
            Thread.sleep((long) this.b.a());
        } catch (InterruptedException e) {
        }
        super.a();
    }
}
