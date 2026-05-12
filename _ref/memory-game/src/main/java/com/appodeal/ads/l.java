package com.appodeal.ads;

import android.content.res.Configuration;
import com.appodeal.ads.d.g;
import com.appodeal.ads.g.b;
import java.util.ArrayList;
import org.json.JSONObject;

public class l extends e {
    boolean A = false;
    int B = -1;
    public boolean C;
    public boolean D;
    g E;
    private final String F;
    private long G;
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
    int p = -1;
    h q;
    boolean r = false;
    boolean s = false;
    boolean t = false;
    boolean u = false;
    String v;
    boolean w = false;
    boolean x = false;
    b y = b.BOTTOM;
    boolean z = false;

    l(String str) {
        this.F = str;
    }

    boolean a() {
        return this.F.equals("debug_banner_320");
    }

    boolean b() {
        return !a() && (!(this.s || c()) || this.h);
    }

    boolean c() {
        return this.r && System.currentTimeMillis() - this.k <= 120000;
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

    boolean a(Configuration configuration) {
        return (!this.z || this.p == -1 || configuration.orientation == this.p) ? false : true;
    }

    int g() {
        return this.d.size() + this.c.size();
    }

    long h() {
        if (this.G == 0) {
            this.G = System.currentTimeMillis() / 1000;
        }
        return this.G;
    }

    void a(int i) {
        if (this.A) {
            this.c.clear();
            this.d.clear();
            this.e.clear();
            this.f.clear();
            this.o = null;
            this.q = null;
            this.E = null;
            this.w = true;
        }
    }
}
