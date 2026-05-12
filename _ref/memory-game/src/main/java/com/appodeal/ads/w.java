package com.appodeal.ads;

import android.os.Build.VERSION;

public class w extends c {
    private final String a;
    private final String b;
    private boolean c = true;
    private boolean d = false;
    private int e = 0;
    private final z f;

    public w(String str, z zVar) {
        this.a = str;
        this.b = str;
        this.f = zVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public w c() {
        this.c = false;
        return this;
    }

    public boolean d() {
        if (!this.c) {
            if (!an.a("org.apache.http.HttpResponse")) {
                return false;
            }
        }
        return true;
    }

    public boolean e() {
        return this.d && VERSION.SDK_INT <= this.e;
    }

    public z f() {
        return this.f;
    }
}
