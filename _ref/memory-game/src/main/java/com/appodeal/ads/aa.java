package com.appodeal.ads;

import com.appodeal.ads.d.g;
import java.util.ArrayList;
import org.json.JSONObject;

public class aa extends e {
    public boolean A;
    g B;
    private final String C;
    private long D;
    public ArrayList<JSONObject> c = new ArrayList();
    public ArrayList<JSONObject> d = new ArrayList();
    final ArrayList<String> e = new ArrayList();
    final ArrayList<String> f = new ArrayList();
    boolean g = false;
    boolean h = false;
    boolean i = false;
    boolean j = false;
    long k;
    public JSONObject l;
    public String m;
    String n;
    String o;
    w p;
    boolean q = false;
    boolean r = false;
    boolean s = false;
    boolean t = false;
    String u;
    boolean v = false;
    boolean w = false;
    boolean x = false;
    int y = -1;
    public boolean z;

    aa(String str) {
        this.C = str;
    }

    boolean a() {
        return this.C.equals("debug_mrec");
    }

    boolean b() {
        return !a() && (!(this.r || c()) || this.h);
    }

    boolean c() {
        return this.q && System.currentTimeMillis() - this.k <= 120000;
    }

    boolean d() {
        return !this.h && (this.r || this.s);
    }

    boolean e() {
        return (this.h || this.r || !this.s) ? false : true;
    }

    boolean f() {
        return this.w;
    }

    int g() {
        return this.d.size() + this.c.size();
    }

    long h() {
        if (this.D == 0) {
            this.D = System.currentTimeMillis() / 1000;
        }
        return this.D;
    }

    void a(int i) {
        if (this.x) {
            this.c.clear();
            this.d.clear();
            this.e.clear();
            this.f.clear();
            this.o = null;
            this.p = null;
            this.B = null;
            this.v = true;
        }
    }
}
