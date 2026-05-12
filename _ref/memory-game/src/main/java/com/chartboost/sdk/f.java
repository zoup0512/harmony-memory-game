package com.chartboost.sdk;

import android.content.SharedPreferences;
import com.chartboost.sdk.InPlay.a;
import com.chartboost.sdk.impl.ac;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.ag;
import com.chartboost.sdk.impl.as;
import com.chartboost.sdk.impl.b;
import com.chartboost.sdk.impl.c;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.z;

public class f {
    private static boolean a = false;
    private static b b;
    private static d c;
    private static a d;
    private static b e;
    private static s f;
    private static z g;
    private static c h;
    private static com.chartboost.sdk.Tracking.a i;
    private static ac j;
    private static as k;
    private static ae l;
    private static g m;
    private static ag n;
    private static SharedPreferences o;
    private static j p;
    private static Chartboost q;

    public static void a(ae aeVar, d dVar, a aVar, b bVar, s sVar, z zVar, ac acVar, as asVar, c cVar, com.chartboost.sdk.Tracking.a aVar2, g gVar, ag agVar, b bVar2, j jVar, SharedPreferences sharedPreferences) {
        a = true;
        l = aeVar;
        c = dVar;
        d = aVar;
        e = bVar;
        f = sVar;
        g = zVar;
        k = asVar;
        h = cVar;
        i = aVar2;
        m = gVar;
        n = agVar;
        b = bVar2;
        p = jVar;
        o = sharedPreferences;
        j = acVar;
    }

    public static void a(Chartboost chartboost) {
        q = chartboost;
    }

    public static boolean a() {
        return a;
    }

    public static ae b() {
        return l;
    }

    public static d c() {
        return c;
    }

    public static a d() {
        return d;
    }

    public static b e() {
        return e;
    }

    public static s f() {
        return f;
    }

    public static z g() {
        return g;
    }

    public static ac h() {
        return j;
    }

    public static as i() {
        return k;
    }

    public static c j() {
        return h;
    }

    public static com.chartboost.sdk.Tracking.a k() {
        return i;
    }

    public static g l() {
        return m;
    }

    public static ag m() {
        return n;
    }

    public static b n() {
        return b;
    }

    public static j o() {
        return p;
    }

    public static SharedPreferences p() {
        return o;
    }

    public static Chartboost q() {
        return q;
    }
}
