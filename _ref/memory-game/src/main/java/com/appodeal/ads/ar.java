package com.appodeal.ads;

import com.appodeal.ads.d.g;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import java.util.ArrayList;
import org.json.JSONObject;

public class ar extends e {
    g A;
    public String B;
    private final String C;
    private long D;
    public ArrayList<JSONObject> c = new ArrayList();
    public JSONObject d = null;
    final ArrayList<String> e = new ArrayList();
    boolean f = false;
    boolean g = false;
    boolean h = false;
    boolean i = false;
    boolean j = false;
    long k;
    public JSONObject l;
    public String m;
    String n;
    String o;
    ap p;
    boolean q = false;
    boolean r = false;
    boolean s = false;
    String t;
    boolean u = false;
    boolean v = false;
    boolean w = false;
    int x = -1;
    public boolean y;
    public boolean z;

    ar(String str) {
        this.C = str;
    }

    boolean a() {
        return this.C.equals("debug_rewarded_video") || this.C.equals("debug_video");
    }

    boolean b() {
        return !a() && (!(this.r || c()) || this.g);
    }

    boolean c() {
        return this.q && System.currentTimeMillis() - this.k <= 120000;
    }

    boolean d() {
        return !this.g && this.r;
    }

    boolean e() {
        if (this.C.equals(AdType.REWARDED_VIDEO) || this.C.equals("debug_rewarded_video")) {
            return this.v;
        }
        if (this.C.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) || this.C.equals("debug_video")) {
            return this.v;
        }
        return this.v;
    }

    int f() {
        return this.c.size();
    }

    long g() {
        if (this.D == 0) {
            this.D = System.currentTimeMillis() / 1000;
        }
        return this.D;
    }

    void a(int i) {
        if (this.w) {
            this.c.clear();
            this.e.clear();
            this.o = null;
            this.p = null;
            this.A = null;
            this.u = true;
        }
    }
}
