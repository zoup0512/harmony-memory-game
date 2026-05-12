package com.appodeal.ads;

import com.appodeal.ads.d.g;
import java.util.ArrayList;
import org.json.JSONObject;

public class s extends e {
    public boolean A;
    public boolean B;
    g C;
    private final String D;
    private long E;
    public ArrayList<JSONObject> c = new ArrayList();
    public ArrayList<JSONObject> d = new ArrayList();
    final ArrayList<String> e = new ArrayList();
    final ArrayList<String> f = new ArrayList();
    boolean g = false;
    boolean h = false;
    boolean i = false;
    boolean j = false;
    boolean k = false;
    long l;
    public JSONObject m;
    public String n;
    String o;
    String p;
    o q;
    boolean r = false;
    boolean s = false;
    boolean t = false;
    boolean u = false;
    String v;
    boolean w = false;
    boolean x = false;
    boolean y = false;
    int z = -1;

    s(String str) {
        this.D = str;
    }

    boolean a() {
        return this.D.equals("debug");
    }

    boolean b() {
        return !a() && (!(this.s || c()) || this.h);
    }

    boolean c() {
        return this.r && System.currentTimeMillis() - this.l <= 120000;
    }

    boolean d() {
        return !this.h && (this.s || this.t);
    }

    boolean e() {
        return (this.h || this.s || !this.t) ? false : true;
    }

    boolean f() {
        return this.x;
    }

    int g() {
        return this.d.size() + this.c.size();
    }

    long h() {
        if (this.E == 0) {
            this.E = System.currentTimeMillis() / 1000;
        }
        return this.E;
    }

    void a(int i) {
        if (this.y) {
            this.c.clear();
            this.d.clear();
            this.e.clear();
            this.f.clear();
            this.p = null;
            this.q = null;
            this.C = null;
            this.w = true;
        }
    }
}
