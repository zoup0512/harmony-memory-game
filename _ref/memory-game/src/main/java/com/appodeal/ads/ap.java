package com.appodeal.ads;

import android.os.Build.VERSION;

public class ap extends c {
    private final String a;
    private final String b;
    private final String[] c;
    private boolean d = true;
    private boolean e = false;
    private int f = 0;
    private final aq g;

    public ap(String str, String[] strArr, aq aqVar) {
        this.a = str;
        this.b = str;
        this.c = strArr;
        this.g = aqVar;
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

    public ap d() {
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

    public ap a(int i) {
        this.e = true;
        this.f = i;
        return this;
    }

    public boolean f() {
        return this.e && VERSION.SDK_INT <= this.f;
    }

    public aq g() {
        return this.g;
    }
}
