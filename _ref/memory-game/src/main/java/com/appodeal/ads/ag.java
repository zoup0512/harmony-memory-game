package com.appodeal.ads;

import com.appodeal.ads.d.g;
import java.util.ArrayList;
import org.json.JSONObject;

public class ag extends e {
    g A;
    public int B;
    private final String C;
    private long D;
    public ArrayList<JSONObject> c = new ArrayList();
    final ArrayList<String> d = new ArrayList();
    public ArrayList<Integer> e = new ArrayList();
    public ArrayList<Integer> f = new ArrayList();
    public ArrayList<Integer> g = new ArrayList();
    boolean h = false;
    boolean i = false;
    boolean j = false;
    boolean k = false;
    long l;
    public JSONObject m;
    String n;
    String o;
    String p;
    ac q;
    boolean r = false;
    boolean s = false;
    boolean t = false;
    String u;
    boolean v = false;
    boolean w = false;
    int x = -1;
    public boolean y;
    public boolean z;

    public ag(String str) {
        this.C = str;
    }

    boolean a() {
        return this.C.equals("debug_native");
    }

    boolean b() {
        return !a() && (!(this.s || c()) || this.i);
    }

    public boolean c() {
        return this.r && System.currentTimeMillis() - this.l <= 120000;
    }

    boolean d() {
        return !this.i && this.s;
    }

    int e() {
        return this.c.size();
    }

    long f() {
        if (this.D == 0) {
            this.D = System.currentTimeMillis() / 1000;
        }
        return this.D;
    }

    void a(int i) {
        if (this.w) {
            this.c.clear();
            this.d.clear();
            this.p = null;
            this.q = null;
            this.A = null;
            this.v = true;
            this.e.clear();
            this.f.clear();
            this.g.clear();
        }
    }
}
