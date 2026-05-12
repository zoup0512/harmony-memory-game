package com.yandex.metrica.impl.ob;

import com.mopub.volley.DefaultRetryPolicy;

public class ep {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public ep() {
        this(2500, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public ep(int i, int i2, float f) {
        this.a = i;
        this.c = i2;
        this.d = f;
    }

    public int a() {
        return this.a;
    }

    public void a(ek ekVar) throws ek {
        this.b++;
        this.a = (int) (((float) this.a) + (((float) this.a) * this.d));
        if (!b()) {
            throw ekVar;
        }
    }

    protected boolean b() {
        return this.b <= this.c;
    }
}
