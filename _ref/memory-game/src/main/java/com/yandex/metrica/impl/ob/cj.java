package com.yandex.metrica.impl.ob;

import java.util.Random;

public class cj {
    private int a;
    private int b;
    private Random c;
    private int d;

    public cj(int i) {
        if (i <= 0 || i > 31) {
            this.a = 31;
        } else {
            this.a = i;
        }
        this.c = new Random();
    }

    public int a() {
        if (this.b < this.a) {
            this.b++;
            this.d = 1 << this.b;
        }
        return this.c.nextInt(this.d);
    }
}
