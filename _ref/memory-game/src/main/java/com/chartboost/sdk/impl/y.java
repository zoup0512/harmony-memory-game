package com.chartboost.sdk.impl;

public class y<T> {
    public final T a;
    public final Exception b;

    public boolean a() {
        return this.b == null;
    }

    public static <T> y<T> a(T t) {
        return new y(t, null);
    }

    public static <T> y<T> b() {
        return new y(null, null);
    }

    public static <T> y<T> a(Exception exception) {
        return new y(null, exception);
    }

    private y(T t, Exception exception) {
        this.a = t;
        this.b = exception;
    }
}
