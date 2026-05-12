package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class dg {
    private static final dg a = new dg();
    private final Object b = new Object();
    private final Map c = new HashMap(2);

    private dg() {
    }

    static dg a() {
        return a;
    }

    di a(String str) {
        di diVar;
        synchronized (this.b) {
            diVar = (di) this.c.remove(str);
        }
        return diVar;
    }

    void a(String str, long j, String str2, boolean z) {
        di diVar = new di(this, str2, j, z);
        synchronized (this.b) {
            this.c.put(str, diVar);
        }
    }
}
