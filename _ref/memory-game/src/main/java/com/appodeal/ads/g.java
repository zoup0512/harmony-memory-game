package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.appodeal.ads.a.aa;
import com.appodeal.ads.a.ab;
import com.appodeal.ads.a.ad;
import com.appodeal.ads.a.e;
import com.appodeal.ads.a.j;
import com.appodeal.ads.a.k;
import com.appodeal.ads.a.o;
import com.appodeal.ads.a.p;
import com.appodeal.ads.a.r;
import com.appodeal.ads.a.t;
import com.appodeal.ads.a.v;
import com.appodeal.ads.a.w;
import com.appodeal.ads.a.x;
import com.appodeal.ads.a.z;
import com.appodeal.ads.d.f;
import com.appodeal.ads.d.h;
import com.appodeal.ads.d.i;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.m;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {
    static com.appodeal.ads.f.c A = com.appodeal.ads.f.d.a();
    static boolean B = false;
    static boolean C = true;
    static int D = 0;
    static ArrayList<String> E = new ArrayList();
    static int F = 0;
    static boolean G = true;
    static m H;
    private static boolean I = false;
    private static String J;
    private static Integer K = null;
    private static Integer L = null;
    static Set<h> a = null;
    static boolean b = false;
    public static boolean c = false;
    static BannerCallbacks d;
    static int e = 0;
    public static long f = 0;
    static final ArrayList<JSONObject> g = new ArrayList();
    static final ArrayList<JSONObject> h = new ArrayList();
    static final ArrayList<JSONObject> i = new ArrayList();
    static final ArrayList<JSONObject> j = new ArrayList();
    public static boolean k;
    static boolean l = true;
    static boolean m = false;
    static int n = 5000;
    public static int o = -1;
    public static View p;
    public static int q = -1;
    public static b r = b.BOTTOM;
    public static boolean s = true;
    public static boolean t = false;
    public static boolean u = false;
    public static BannerView v;
    public static d w = d.NEVER_SHOWN;
    public static final ArrayList<l> x = new ArrayList();
    public static int y;
    public static int z;

    private static class a implements com.appodeal.ads.t.a {
        private a() {
        }

        public void a(int i) {
            j.a(true);
        }

        public void a(JSONObject jSONObject, int i, String str) {
            try {
                if (g.c || jSONObject.optBoolean("banners_disabled")) {
                    g.c = true;
                    Appodeal.a("Banners disabled");
                } else if (jSONObject.has("ads") && jSONObject.has("main_id")) {
                    int i2;
                    g.f = System.currentTimeMillis();
                    g.g.clear();
                    g.h.clear();
                    JSONArray optJSONArray = jSONObject.optJSONArray("precache");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            g.g.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    optJSONArray = jSONObject.optJSONArray("ads");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            g.h.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    if (jSONObject.has("main_id")) {
                        g.J = jSONObject.getString("main_id");
                    }
                    if (jSONObject.has("rri")) {
                        g.B = jSONObject.optBoolean("rri");
                    }
                    if (jSONObject.has("ad_watch")) {
                        g.C = jSONObject.optBoolean("ad_watch", true);
                    }
                    g.F = jSONObject.optInt("afd", 0);
                    if (jSONObject.has("refresh_period")) {
                        g.K = Integer.valueOf(jSONObject.optInt("refresh_period") * 1000);
                    }
                    if (g.L == null && jSONObject.has("waterfall_cache_timeout")) {
                        g.L = Integer.valueOf(jSONObject.getInt("waterfall_cache_timeout"));
                    }
                    if (jSONObject.has("fraud_detector")) {
                        g.H = new m(jSONObject.getJSONObject("fraud_detector"));
                        g.H.a(4);
                        g.H.b(g.J);
                    }
                    AppodealSettings.a(jSONObject);
                    b.a(g.g, g.i, 4);
                    ((l) g.x.get(i)).c = new ArrayList(g.i);
                    b.a(g.h, g.j, 4);
                    ((l) g.x.get(i)).d = new ArrayList(g.j);
                    an.a("Banner", g.i, g.j);
                    ((l) g.x.get(i)).m = g.J;
                    ((l) g.x.get(i)).a = Long.valueOf(com.appodeal.ads.f.g.a().c());
                    if (!AppodealSettings.i) {
                        try {
                            ((l) g.x.get(i)).E = new com.appodeal.ads.d.g(jSONObject.optJSONObject("rtb"));
                            ((l) g.x.get(i)).E.a(jSONObject.optBoolean("disable_rtb"));
                            ((l) g.x.get(i)).E.b(jSONObject.optBoolean("for_kids"));
                            ((l) g.x.get(i)).E.a(jSONObject.optJSONObject("user_data"));
                            ((l) g.x.get(i)).E.b(jSONObject.optJSONObject("app_data"));
                            ((l) g.x.get(i)).C = jSONObject.optBoolean("disable_rtb");
                            if (!((l) g.x.get(i)).C) {
                                ((l) g.x.get(i)).D = ((l) g.x.get(i)).E.c().size() > 0;
                            }
                            if (AppodealSettings.g) {
                                g.a(i, Double.valueOf(1.0E-4d));
                                return;
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                    if (((l) g.x.get(i)).a()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(4);
                        }
                        j.h().f().a(Appodeal.b, j.h(), i, b.VIEW, false, b.VIEW);
                    } else if (!((l) g.x.get(i)).c.isEmpty()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(4);
                        }
                        g.a(i);
                    } else if (((l) g.x.get(i)).d.isEmpty()) {
                        j.a(true);
                    } else {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(4);
                        }
                        g.b(i);
                    }
                } else {
                    if (jSONObject.has("message")) {
                        Appodeal.a(jSONObject.getString("message"));
                    }
                    j.a(true);
                }
            } catch (Throwable e2) {
                Appodeal.a(e2);
                j.a(true);
            }
        }
    }

    public enum b {
        BOTTOM,
        TOP,
        VIEW
    }

    static class c implements com.appodeal.ads.d.i.a {
        c() {
        }

        public void a(int i, com.appodeal.ads.d.a aVar, h hVar) {
            boolean z = false;
            ((l) g.x.get(i)).C = true;
            ((l) g.x.get(i)).B = -2;
            int g;
            if (aVar == null) {
                g = ((l) g.x.get(i)).g();
                h hVar2 = ((l) g.x.get(i)).q;
                boolean z2 = ((l) g.x.get(i)).z;
                if (!((l) g.x.get(i)).s) {
                    z = ((l) g.x.get(i)).t;
                }
                j.a(i, g, hVar2, z2, z, true);
            } else if (aVar.b() != null) {
                try {
                    if (!(Appodeal.e == null || ((l) g.x.get(i)).q == null)) {
                        Appodeal.e.a(4, ((l) g.x.get(i)).q.a(), false);
                    }
                    g = ((l) g.x.get(i)).d.size();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("status", "rtbmraid");
                    jSONObject.put("id", aVar.i().c());
                    jSONObject.put("ecpm", aVar.b());
                    jSONObject.put(AdType.HTML, aVar.e());
                    jSONObject.put("width", 320);
                    jSONObject.put("height", 50);
                    ((l) g.x.get(i)).d.add(jSONObject);
                    g.b(g, false, i);
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        }

        public void a(f fVar) {
            if (AppodealSettings.g) {
                Appodeal.a((Throwable) fVar);
            }
        }

        public void a(com.appodeal.ads.d.c cVar) {
            if (AppodealSettings.g) {
                Appodeal.a(cVar.toString());
            }
        }
    }

    public enum d {
        VISIBLE,
        HIDDEN,
        NEVER_SHOWN
    }

    public static Set<h> a(Context context) {
        if (a == null) {
            a = new HashSet();
            a(context, "admob", com.appodeal.ads.a.a.class, false, "com.google.android.gms.ads.AdView");
            a(context, "amazon_ads", e.class, false, "com.amazon.device.ads.AdLayout");
            a(context, "appnexus", com.appodeal.ads.a.g.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "cheetah", com.appodeal.ads.a.h.class, false, "com.cmcm.adsdk.banner.CMNativeBannerView");
            a(context, "facebook", k.class, true, "com.facebook.ads.AdView");
            a(context, "flurry", com.appodeal.ads.a.m.class, true, "com.flurry.android.ads.FlurryAdBanner", "com.flurry.android.FlurryAgent");
            a(context, "inner-active", o.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "mailru", r.class, false, "com.my.target.ads.MyTargetView");
            a(context, "mopub", t.class, false, "com.mopub.mobileads.MoPubView");
            a(context, AdType.MRAID, p.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "revmob", x.class, true, "com.revmob.ads.banner.RevMobBanner");
            a(context, "rtbmraid", w.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "openx", v.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "rubicon", z.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "smaato", aa.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "startapp", ab.class, true, "com.startapp.android.publish.banner.Banner");
            a(context, "yandex", ad.class, true, "com.yandex.mobile.ads.AdView");
        }
        return a;
    }

    private static boolean g() {
        if (a == null || a.size() == D) {
            return false;
        }
        D = a.size();
        return true;
    }

    static void a(Activity activity) {
        if (!I && !b) {
            I = true;
            try {
                for (h hVar : a((Context) activity)) {
                    if (hVar.f() == null && (VERSION.SDK_INT >= 14 || !an.b((Context) activity, hVar.b()))) {
                        String format = String.format("ERROR: %s not found", new Object[]{an.a(hVar.a())});
                        Appodeal.a(format);
                        an.b(activity, format);
                    }
                }
                if (l) {
                    b(activity);
                }
                Appodeal.a("Banners Initialized");
                b = true;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            I = false;
        }
    }

    static h a(Context context, String str) {
        for (h hVar : a(context)) {
            if (hVar.a().equals(str)) {
                return hVar;
            }
        }
        return null;
    }

    static l a() {
        if (x.isEmpty()) {
            return null;
        }
        return (l) x.get(x.size() - 1);
    }

    public static Integer b() {
        if (A != null && A.c() > 0) {
            K = Integer.valueOf(A.c());
        } else if (K == null) {
            K = Integer.valueOf(15000);
        }
        return K;
    }

    static void b(Activity activity) {
        new a(activity).a();
    }

    static void a(Activity activity, String str, boolean z, b bVar, boolean z2) {
        try {
            if (!an.a((Context) activity)) {
                j.a();
            } else if (!Appodeal.a && !c && !com.appodeal.ads.f.g.a().b().b()) {
                if (a() == null) {
                    Appodeal.a(String.format("Caching Banner (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false)}));
                } else {
                    Appodeal.a(String.format("Caching Banner (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(a().s), Boolean.valueOf(a().r)}));
                }
                l lVar = new l(str);
                if (x.size() > 0) {
                    lVar.D = ((l) x.get(e)).D;
                    lVar.C = ((l) x.get(e)).C;
                    lVar.E = new com.appodeal.ads.d.g(((l) x.get(e)).E);
                }
                x.add(lVar);
                int size = x.size() - 1;
                e = size;
                lVar.k = System.currentTimeMillis();
                lVar.r = true;
                lVar.y = bVar;
                lVar.m = J;
                if (z) {
                    lVar.x = true;
                }
                com.appodeal.ads.f.g.d();
                lVar.a = Long.valueOf(com.appodeal.ads.f.g.a().c());
                for (int i = 0; i < x.size() - 3; i++) {
                    l lVar2 = (l) x.get(i);
                    if (!lVar2.w) {
                        lVar2.a(i);
                    }
                }
                if (g() || lVar.a() || f == 0 || System.currentTimeMillis() - f > ((long) AppodealSettings.a(L))) {
                    new com.appodeal.ads.t.c(activity, size, str).a(new a()).a(lVar.a).a().a();
                    return;
                }
                if (k) {
                    b.a(g, i, 4);
                }
                lVar.c = new ArrayList(i);
                if (!z2 || size <= 0 || ((l) x.get(size - 1)).d.isEmpty() || k) {
                    if (k) {
                        b.a(h, j, 4);
                    }
                    lVar.d = new ArrayList(j);
                } else {
                    lVar.d = new ArrayList(((l) x.get(size - 1)).d);
                }
                k = false;
                if (!lVar.c.isEmpty()) {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(4);
                    }
                    a(size);
                } else if (lVar.d.isEmpty()) {
                    j.a(true);
                } else {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(4);
                    }
                    b(size);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            j.a(true);
        }
    }

    static void a(int i, Double d) {
        l lVar = (l) x.get(i);
        Double valueOf = Double.valueOf(0.0d);
        String str = "";
        if (AppodealSettings.g) {
            valueOf = Double.valueOf(0.005d);
        } else if (j.size() > 0) {
            try {
                str = lVar.l.getString("id");
                valueOf = d;
            } catch (Throwable e) {
                Appodeal.a(e);
                valueOf = d;
            }
        }
        i iVar = new i(Appodeal.b, 1, i, ((l) x.get(i)).E, new c(), valueOf, str, lVar.m);
    }

    static void a(int i) {
        a(0, false, i);
    }

    public static void a(int i, boolean z, final int i2) {
        try {
            ((l) x.get(i2)).l = (JSONObject) ((l) x.get(i2)).c.get(i);
            ((l) x.get(i2)).c.remove(i);
            if (z) {
                ((l) x.get(i2)).c.clear();
                ((l) x.get(i2)).d.clear();
            }
            final String string = ((l) x.get(i2)).l.getString("id");
            final int g = ((l) x.get(i2)).g();
            if (E.contains("admob")) {
                j.b(i2, g, com.appodeal.ads.a.c.h(), true);
            }
            if (com.appodeal.ads.a.c.h().f() != null) {
                ((l) x.get(i2)).e.add(((l) x.get(i2)).l.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (Appodeal.e != null) {
                                Appodeal.e.a(4, com.appodeal.ads.a.c.h().a(), string);
                            }
                            com.appodeal.ads.a.c.h().f().a(Appodeal.b, i2, g);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            j.b(i2, g, com.appodeal.ads.a.c.h(), true);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                j.b(i2, g, com.appodeal.ads.a.c.h(), true);
                            }
                        }, 5000);
                    }
                });
                return;
            }
            j.b(i2, g, com.appodeal.ads.a.c.h(), true);
        } catch (Throwable e) {
            Appodeal.a(e);
            j.a(i2, ((l) x.get(i2)).g());
        }
    }

    static void b(int i) {
        b(0, false, i);
    }

    public static void b(int i, boolean z, final int i2) {
        try {
            int g;
            ((l) x.get(i2)).l = (JSONObject) ((l) x.get(i2)).d.get(i);
            ((l) x.get(i2)).d.remove(i);
            if (z) {
                ((l) x.get(i2)).c.clear();
                ((l) x.get(i2)).d.clear();
            }
            String string = ((l) x.get(i2)).l.getString("status");
            final String string2 = ((l) x.get(i2)).l.getString("id");
            final h a = a(Appodeal.b, string);
            if (a == null || !(a.f() instanceof w)) {
                g = ((l) x.get(i2)).g();
            } else {
                g = -2;
            }
            if (((l) x.get(i2)).u && (((l) x.get(i2)).l.optBoolean("offer") || (a != null && (a.f() instanceof p)))) {
                j.b(i2, g, a);
            } else if (a == null) {
                j.a(i2, g);
            } else if (a.f() == null || ((AppodealSettings.a && !a.f().g()) || ((VERSION.SDK_INT > 22 && !a.d()) || (!com.appodeal.ads.utils.c.a(Appodeal.b) && a.e())))) {
                j.b(i2, g, a);
            } else {
                ((l) x.get(i2)).f.add(((l) x.get(i2)).l.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (!(Appodeal.e == null || ((l) g.x.get(i2)).u)) {
                                Appodeal.e.a(4, a.a(), string2);
                            }
                            a.f().a(Appodeal.getLogLevel() == LogLevel.verbose);
                            a.f().a(Appodeal.b, i2, g);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            j.b(i2, g, a);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                j.b(i2, g, a);
                            }
                        }, 10000);
                    }
                });
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            j.a(i2, ((l) x.get(i2)).g());
        }
    }

    static boolean a(Activity activity, com.appodeal.ads.f.c cVar, b bVar) {
        return new b(activity).a(bVar).a(cVar).a();
    }

    static boolean a(final Activity activity, com.appodeal.ads.f.c cVar, String str, final b bVar, boolean z) {
        try {
            A = cVar;
            if (!an.a((Context) activity)) {
                return false;
            }
            if (Appodeal.a || c || ((z && w == d.HIDDEN) || com.appodeal.ads.f.g.a().b().b())) {
                return false;
            }
            boolean equals = str.equals("debug_banner_320");
            if (x.isEmpty()) {
                Appodeal.a(String.format("Showing Banner (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false), cVar.x()}));
                if (!cVar.a(4, null)) {
                    return false;
                }
                if (equals || !l) {
                    return false;
                }
                new a(activity).b().a(bVar).a();
                w = d.VISIBLE;
                return true;
            }
            final int size = x.size() - 1;
            e eVar = (l) x.get(size);
            Appodeal.a(String.format("Showing Banner (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(eVar.s), Boolean.valueOf(eVar.r), cVar.x()}));
            if (!cVar.a(4, eVar)) {
                return false;
            }
            final b bVar2 = r;
            r = bVar;
            View findViewById;
            final Activity activity2;
            final b bVar3;
            if (eVar.s) {
                final h a = a((Context) activity, eVar.o);
                if (a != null) {
                    findViewById = activity.findViewById(o);
                    if (bVar == b.VIEW && findViewById == null && v == null) {
                        Appodeal.a("BannerView not found");
                        return false;
                    }
                    final com.appodeal.ads.f.c cVar2 = cVar;
                    activity2 = activity;
                    bVar3 = bVar;
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                cVar2.b(4);
                                a.f().a(activity2, a, size, bVar3, false, bVar2);
                            } catch (Throwable e) {
                                Appodeal.a(e);
                            }
                        }
                    });
                    w = d.VISIBLE;
                    return true;
                }
            } else if (eVar.t) {
                findViewById = activity.findViewById(o);
                if (bVar == b.VIEW && findViewById == null && v == null) {
                    Appodeal.a("BannerView not found");
                    return false;
                }
                final com.appodeal.ads.f.c cVar3 = cVar;
                activity2 = activity;
                bVar3 = bVar;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cVar3.b(4);
                            com.appodeal.ads.a.c.h().f().a(activity2, com.appodeal.ads.a.c.h(), size, bVar3, false, bVar2);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                });
                w = d.VISIBLE;
                return true;
            } else if (eVar.c()) {
                eVar.x = true;
                eVar.y = bVar;
                if (!(q == -1 || p == null)) {
                    l lVar = (l) x.get(q);
                    final h a2 = a((Context) activity, lVar.o);
                    if (!(!lVar.s || lVar.w || a2 == null)) {
                        findViewById = activity.findViewById(o);
                        if (bVar == b.VIEW && findViewById == null && v == null) {
                            Appodeal.a("BannerView not found");
                            return false;
                        } else if (p == null) {
                            return false;
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    try {
                                        a2.f().a(activity, a2, g.q, bVar, true, bVar2);
                                    } catch (Throwable e) {
                                        Appodeal.a(e);
                                    }
                                }
                            });
                        }
                    }
                }
                w = d.VISIBLE;
                return true;
            } else if (!equals && l) {
                new a(activity).b().a(bVar).a();
                w = d.VISIBLE;
                return true;
            }
            return false;
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    static void c(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (g.p != null) {
                        g.p.setVisibility(8);
                        g.a(g.p, true, true);
                        l a = g.a();
                        if (g.l && a != null && !a.c() && a.h) {
                            g.b(activity);
                        }
                    }
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        });
        w = d.HIDDEN;
    }

    static void a(View view, boolean z, boolean z2) {
        View view2 = null;
        if (view != null) {
            View view3;
            com.appodeal.ads.utils.t.a(view);
            if (view.getParent() == null || !(view.getParent() instanceof ViewGroup)) {
                view3 = null;
            } else {
                view3 = (ViewGroup) view.getParent();
            }
            if (view3 != null) {
                if ((view3 instanceof BannerView) && z) {
                    view3.setVisibility(8);
                }
                view3.removeView(view);
            }
            if (!(view3 == null || view3.getTag() == null || !view3.getTag().equals("Appodeal"))) {
                view2 = view3;
            }
            if (view2 != null && z2) {
                ViewParent parent = view2.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(view2);
                }
            }
        }
    }

    public static int c() {
        if ((s || t) && an.h(Appodeal.b) > 720.0f) {
            return 90;
        }
        return 50;
    }

    public static int d() {
        if (s) {
            return Math.round(an.g(Appodeal.b));
        }
        if (!t || an.g(Appodeal.b) < 728.0f) {
            return 320;
        }
        return Math.min(Math.round(an.g(Appodeal.b)), 728);
    }

    public static void a(Context context, final String str, final Class cls, boolean z, final String... strArr) {
        if (!E.contains(str)) {
            if (z) {
                com.appodeal.ads.utils.i.a(context, String.format("%s.dex", new Object[]{str}), strArr[0], new Runnable() {
                    public void run() {
                        if (!g.E.contains(str)) {
                            try {
                                h a = g.b(cls, str, strArr);
                                if (a != null) {
                                    g.a.add(a);
                                }
                            } catch (Throwable e) {
                                Appodeal.a(e);
                            }
                        }
                    }
                });
                return;
            }
            try {
                h b = b(cls, str, strArr);
                if (b != null) {
                    a.add(b);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private static h b(Class<?> cls, String str, String[] strArr) {
        try {
            return (h) cls.getDeclaredMethod("getInstance", new Class[]{String.class, String[].class}).invoke(cls, new Object[]{str, strArr});
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
