package com.appodeal.ads;

import android.os.Build.VERSION;

public class o extends c {
    private final String a;
    private final String b;
    private final String[] c;
    private boolean d = true;
    private boolean e = false;
    private int f = 0;
    private final r g;

    public o(String str, String[] strArr, r rVar) {
        this.a = str;
        this.b = str;
        this.c = strArr;
        this.g = rVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String[] c() {
        return this.c;
    }

    public o d() {
        this.d = false;
        return this;
    }

    public boolean e() {
        if (!this.d) {
            if (!an.a("org.apache.http.HttpResponse")) {
                return false;
            }
        }
        return true;
    }

    public o a(int i) {
        this.e = true;
        this.f = i;
        return this;
    }

    public boolean f() {
        return this.e && VERSION.SDK_INT <= this.f;
    }

    public r g() {
        return this.g;
    }
}
