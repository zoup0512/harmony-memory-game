package com.yandex.metrica.impl.utils;

import android.util.Log;

public abstract class a {
    private volatile boolean a = false;

    abstract String c();

    abstract String d();

    abstract String d(String str, Object[] objArr);

    public void a() {
        this.a = true;
    }

    public boolean b() {
        return this.a;
    }

    public a(boolean z) {
        this.a = z;
    }

    public void a(String str, Object... objArr) {
        a(4, str, objArr);
    }

    public void b(String str, Object... objArr) {
        a(5, str, objArr);
    }

    public void c(String str, Object... objArr) {
        a(6, str, objArr);
    }

    void a(int i, String str, Object... objArr) {
        if (this.a) {
            String c = c();
            StringBuilder append = new StringBuilder().append(d());
            if (str == null) {
                str = "";
            }
            Log.println(i, c, append.append(d(str, objArr)).toString());
        }
    }
}
