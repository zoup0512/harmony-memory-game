package com.flurry.sdk;

import android.content.Context;

public class jk implements kp {
    public static synchronized jk a() {
        jk jkVar;
        synchronized (jk.class) {
            jkVar = (jk) jy.a().a(jk.class);
        }
        return jkVar;
    }

    public final void a(Context context) {
        lk.a(jx.class);
        ki.a();
        lt.a();
        lp.a();
        ka.a();
        jr.a();
        jl.a();
        js.a();
        jp.a();
        jl.a();
        ju.a();
        jo.a();
        jw.a();
    }

    public static String b() {
        jx c = c();
        if (c != null) {
            return Long.toString(c.c);
        }
        return null;
    }

    public static jx c() {
        lk b = lm.a().b();
        if (b == null) {
            return null;
        }
        return (jx) b.b(jx.class);
    }

    public static long d() {
        jx c = c();
        if (c != null) {
            return c.c;
        }
        return 0;
    }

    public static long e() {
        jx c = c();
        if (c != null) {
            return c.d;
        }
        return 0;
    }

    public static long f() {
        jx c = c();
        if (c != null) {
            return c.e;
        }
        return -1;
    }

    public static long g() {
        jx c = c();
        if (c != null) {
            return c.c();
        }
        return 0;
    }

    public static int h() {
        return jr.a().b();
    }
}
