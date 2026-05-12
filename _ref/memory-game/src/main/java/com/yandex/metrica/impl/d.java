package com.yandex.metrica.impl;

import java.util.concurrent.TimeUnit;

public interface d {

    public static class a<T> {
        public static final long a = TimeUnit.SECONDS.toMillis(10);
        private long b;
        private long c;
        private T d;
        private boolean e;

        public a() {
            this(a);
        }

        public a(long j) {
            this.c = 0;
            this.d = null;
            this.e = true;
            this.b = j;
        }

        public final boolean a() {
            return this.e;
        }

        public T b() {
            return this.d;
        }

        public void a(T t) {
            this.d = t;
            this.c = System.currentTimeMillis();
            this.e = false;
        }

        public final boolean c() {
            return this.d == null;
        }

        public final boolean d() {
            long currentTimeMillis = System.currentTimeMillis() - this.c;
            return currentTimeMillis > this.b || currentTimeMillis < 0;
        }
    }
}
