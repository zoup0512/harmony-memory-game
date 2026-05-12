package com.appodeal.ads;

import android.os.Build.VERSION;

public class h extends c {
    private final String a;
    private final String b;
    private boolean c = true;
    private boolean d = false;
    private int e = 0;
    private final k f;

    public h(String str, k kVar) {
        this.a = str;
        this.b = str;
        this.f = kVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public h c() {
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

    public k f() {
        return this.f;
    }
}
