package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import com.applovin.sdk.AppLovinSdk;
import com.appodeal.ads.b.aa;
import com.appodeal.ads.b.ab;
import com.appodeal.ads.b.ad;
import com.appodeal.ads.b.ae;
import com.appodeal.ads.b.af;
import com.appodeal.ads.b.ah;
import com.appodeal.ads.b.e;
import com.appodeal.ads.b.i;
import com.appodeal.ads.b.j;
import com.appodeal.ads.b.k;
import com.appodeal.ads.b.p;
import com.appodeal.ads.b.r;
import com.appodeal.ads.b.s;
import com.appodeal.ads.b.u;
import com.appodeal.ads.b.w;
import com.appodeal.ads.b.y;
import com.appodeal.ads.b.z;
import com.appodeal.ads.d.f;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.m;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class n {
    private static Integer A = null;
    static Set<o> a = null;
    static ArrayList<String> b = new ArrayList();
    static boolean c = false;
    public static boolean d = false;
    static InterstitialCallbacks e;
    static int f = 0;
    public static long g = 0;
    static final ArrayList<JSONObject> h = new ArrayList();
    static final ArrayList<JSONObject> i = new ArrayList();
    static final ArrayList<JSONObject> j = new ArrayList();
    static final ArrayList<JSONObject> k = new ArrayList();
    public static boolean l;
    static boolean m = true;
    static boolean n = false;
    static int o = 5000;
    public static final ArrayList<s> p = new ArrayList();
    public static int q;
    public static int r;
    static c s = d.a();
    static boolean t = false;
    static boolean u = true;
    static int v = 0;
    static int w = 0;
    static m x;
    private static boolean y = false;
    private static String z;

    private static class a implements com.appodeal.ads.t.a {
        private a() {
        }

        public void a(int i) {
            q.a(true);
        }

        public void a(JSONObject jSONObject, int i, String str) {
            try {
                if (n.d || jSONObject.optBoolean("interstitials_disabled")) {
                    n.d = true;
                    Appodeal.a("Interstitials disabled");
                } else if (jSONObject.has("ads") && jSONObject.has("main_id")) {
                    int i2;
                    n.g = System.currentTimeMillis();
                    n.h.clear();
                    n.i.clear();
                    JSONArray optJSONArray = jSONObject.optJSONArray("precache");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            n.h.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    optJSONArray = jSONObject.optJSONArray("ads");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            n.i.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    if (jSONObject.has("main_id")) {
                        n.z = jSONObject.getString("main_id");
                    }
                    if (jSONObject.has("rri")) {
                        n.t = jSONObject.optBoolean("rri");
                    }
                    if (jSONObject.has("ad_watch")) {
                        n.u = jSONObject.optBoolean("ad_watch", true);
                    }
                    n.w = jSONObject.optInt("afd", 0);
                    if (n.A == null && jSONObject.has("waterfall_cache_timeout")) {
                        n.A = Integer.valueOf(jSONObject.getInt("waterfall_cache_timeout"));
                    }
                    if (jSONObject.has("fraud_detector")) {
                        n.x = new m(jSONObject.getJSONObject("fraud_detector"));
                        n.x.a(1);
                        n.x.b(n.z);
                    }
                    AppodealSettings.a(jSONObject);
                    b.a(n.h, n.j, 1);
                    ((s) n.p.get(i)).c = new ArrayList(n.j);
                    b.a(n.i, n.k, 1);
                    ((s) n.p.get(i)).d = new ArrayList(n.k);
                    an.a("Interstitial", n.j, n.k);
                    ((s) n.p.get(i)).n = n.z;
                    ((s) n.p.get(i)).a = Long.valueOf(g.a().c());
                    if (!AppodealSettings.i) {
                        try {
                            ((s) n.p.get(i)).C = new com.appodeal.ads.d.g(jSONObject.optJSONObject("rtb"));
                            ((s) n.p.get(i)).C.a(jSONObject.optBoolean("disable_rtb"));
                            ((s) n.p.get(i)).C.b(jSONObject.optBoolean("for_kids"));
                            ((s) n.p.get(i)).C.a(jSONObject.optJSONObject("user_data"));
                            ((s) n.p.get(i)).C.b(jSONObject.optJSONObject("app_data"));
                            ((s) n.p.get(i)).A = jSONObject.optBoolean("disable_rtb");
                            if (!((s) n.p.get(i)).A) {
                                ((s) n.p.get(i)).B = ((s) n.p.get(i)).C.c().size() > 0;
                            }
                            if (AppodealSettings.g) {
                                n.a(i, Double.valueOf(1.0E-4d));
                                return;
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                    if (((s) n.p.get(i)).a()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(1);
                        }
                        com.appodeal.ads.b.m.f().g().a(Appodeal.b, i);
                    } else if (!((s) n.p.get(i)).c.isEmpty()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(1);
                        }
                        n.a(i);
                    } else if (((s) n.p.get(i)).d.isEmpty()) {
                        q.a(true);
                    } else {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(1);
                        }
                        n.b(i);
                    }
                } else {
                    if (jSONObject.has("message")) {
                        Appodeal.a(jSONObject.getString("message"));
                    }
                    q.a(true);
                }
            } catch (Throwable e2) {
                Appodeal.a(e2);
                q.a(true);
            }
        }
    }

    static class b implements com.appodeal.ads.d.i.a {
        b() {
        }

        public void a(int i, com.appodeal.ads.d.a aVar, h hVar) {
            ((s) n.p.get(i)).A = true;
            ((s) n.p.get(i)).z = -2;
            if (aVar == null) {
                q.a(i, ((s) n.p.get(i)).g(), ((s) n.p.get(i)).q, ((s) n.p.get(i)).s ? false : ((s) n.p.get(i)).t, true);
            } else if (aVar.b() != null) {
                try {
                    if (!(Appodeal.e == null || ((s) n.p.get(i)).q == null)) {
                        Appodeal.e.a(1, ((s) n.p.get(i)).q.a(), false);
                    }
                    int size = ((s) n.p.get(i)).d.size();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("status", "rtbmraid");
                    jSONObject.put("id", aVar.i().c());
                    jSONObject.put("ecpm", aVar.b());
                    jSONObject.put(AdType.HTML, aVar.e());
                    jSONObject.put("width", 0);
                    jSONObject.put("height", 0);
                    jSONObject.put("ext", aVar.g());
                    ((s) n.p.get(i)).d.add(jSONObject);
                    n.b(size, false, i);
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

    public static Set<o> a(Context context) {
        if (a == null) {
            a = new HashSet();
            a(context, "admob", com.appodeal.ads.b.a.class, false, "com.google.android.gms.ads.InterstitialAd");
            a(context, "amazon_ads", e.class, false, "com.amazon.device.ads.AdRegistration");
            a(context, AppLovinSdk.URI_SCHEME, com.appodeal.ads.b.g.class, false, "com.applovin.sdk.AppLovinSdk");
            a(context, "appnexus", i.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "cheetah", k.class, false, "com.cmcm.adsdk.interstitial.InterstitialAdManager");
            a(context, "chartboost", j.class, false, "com.chartboost.sdk.Chartboost");
            a(context, "facebook", com.appodeal.ads.b.n.class, true, "com.facebook.ads.InterstitialAd");
            a(context, "flurry", p.class, true, "com.flurry.android.ads.FlurryAdInterstitial", "com.flurry.android.FlurryAgent");
            a(context, "inner-active", r.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "mailru", u.class, false, "com.my.target.ads.InterstitialAd");
            a(context, "mopub", w.class, false, "com.mopub.mobileads.MoPubInterstitial");
            a(context, AdType.MRAID, s.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "revmob", ab.class, true, "com.revmob.ads.interstitial.RevMobFullscreen");
            a(context, "rtbmraid", aa.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "nexage", y.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "openx", z.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "rubicon", ad.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "smaato", ae.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "startapp", af.class, true, "com.startapp.android.publish.StartAppAd");
            a(context, "yandex", ah.class, true, "com.yandex.mobile.ads.InterstitialAd");
        }
        return a;
    }

    private static boolean d() {
        if (a == null || a.size() == v) {
            return false;
        }
        v = a.size();
        return true;
    }

    static void a(Activity activity) {
        if (!y && !c) {
            y = true;
            try {
                for (o oVar : a((Context) activity)) {
                    if (oVar.g() == null && (VERSION.SDK_INT >= 14 || !an.b((Context) activity, oVar.b()))) {
                        String format = String.format("ERROR: %s not found", new Object[]{an.a(oVar.a())});
                        Appodeal.a(format);
                        an.b(activity, format);
                    }
                }
                com.appodeal.ads.utils.c.b(activity);
                if (m) {
                    b(activity);
                }
                Appodeal.a("Interstitials Initialized");
                c = true;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            y = false;
        }
    }

    static o a(Context context, String str) {
        for (o oVar : a(context)) {
            if (oVar.a().equals(str)) {
                return oVar;
            }
        }
        return null;
    }

    static s a() {
        if (p.isEmpty()) {
            return null;
        }
        return (s) p.get(p.size() - 1);
    }

    static void b(Activity activity) {
        new a(activity).a();
    }

    static void a(Activity activity, String str, boolean z, boolean z2) {
        try {
            if (!an.a((Context) activity)) {
                q.a();
            } else if (!Appodeal.a && !d && !g.a().b().c()) {
                if (a() == null) {
                    Appodeal.a(String.format("Caching Interstitial (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false)}));
                } else {
                    Appodeal.a(String.format("Caching Interstitial (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(a().s), Boolean.valueOf(a().r)}));
                }
                s sVar = new s(str);
                if (p.size() > 0) {
                    sVar.B = ((s) p.get(f)).B;
                    sVar.A = ((s) p.get(f)).A;
                    sVar.C = new com.appodeal.ads.d.g(((s) p.get(f)).C);
                }
                p.add(sVar);
                int size = p.size() - 1;
                f = size;
                sVar.l = System.currentTimeMillis();
                sVar.r = true;
                sVar.n = z;
                if (z) {
                    sVar.x = true;
                }
                g.d();
                sVar.a = Long.valueOf(g.a().c());
                for (int i = 0; i < p.size() - 2; i++) {
                    s sVar2 = (s) p.get(i);
                    if (!sVar2.w) {
                        sVar2.a(i);
                    }
                }
                if (d() || sVar.a() || g == 0 || System.currentTimeMillis() - g > ((long) AppodealSettings.a(A))) {
                    new t.c(activity, size, str).a(new a()).a(sVar.a).a().a();
                    return;
                }
                if (l) {
                    b.a(h, j, 1);
                }
                sVar.c = new ArrayList(j);
                if (!z2 || size <= 0 || ((s) p.get(size - 1)).d.isEmpty() || l) {
                    if (l) {
                        b.a(i, k, 1);
                    }
                    sVar.d = new ArrayList(k);
                } else {
                    sVar.d = new ArrayList(((s) p.get(size - 1)).d);
                }
                l = false;
                if (!sVar.c.isEmpty()) {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(1);
                    }
                    a(size);
                } else if (sVar.d.isEmpty()) {
                    q.a(true);
                } else {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(1);
                    }
                    b(size);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            q.a(true);
        }
    }

    static void a(int i, Double d) {
        s sVar = (s) p.get(i);
        Double valueOf = Double.valueOf(0.0d);
        String str = "";
        if (AppodealSettings.g) {
            valueOf = Double.valueOf(5.0E-4d);
        } else if (k.size() > 0) {
            try {
                str = sVar.m.getString("id");
                valueOf = d;
            } catch (Throwable e) {
                Appodeal.a(e);
                valueOf = d;
            }
        }
        com.appodeal.ads.d.i iVar = new com.appodeal.ads.d.i(Appodeal.b, 3, i, ((s) p.get(i)).C, new b(), valueOf, str, sVar.n);
    }

    static void a(int i) {
        a(0, false, i);
    }

    public static void a(int i, boolean z, final int i2) {
        try {
            ((s) p.get(i2)).m = (JSONObject) ((s) p.get(i2)).c.get(i);
            ((s) p.get(i2)).c.remove(i);
            if (z) {
                ((s) p.get(i2)).c.clear();
                ((s) p.get(i2)).d.clear();
            }
            final String string = ((s) p.get(i2)).m.getString("id");
            final int g = ((s) p.get(i2)).g();
            if (b.contains("admob")) {
                q.b(i2, g, com.appodeal.ads.b.c.f(), true);
            }
            if (com.appodeal.ads.b.c.f().g() != null) {
                ((s) p.get(i2)).e.add(((s) p.get(i2)).m.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (Appodeal.e != null) {
                                Appodeal.e.a(1, com.appodeal.ads.b.c.f().a(), string);
                            }
                            com.appodeal.ads.b.c.f().g().a(Appodeal.b, i2, g);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            q.b(i2, g, com.appodeal.ads.b.c.f(), true);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                q.b(i2, g, com.appodeal.ads.b.c.f(), true);
                            }
                        }, 5000);
                    }
                });
                return;
            }
            q.b(i2, g, com.appodeal.ads.b.c.f(), true);
        } catch (Throwable e) {
            Appodeal.a(e);
            q.a(i2, ((s) p.get(i2)).g());
        }
    }

    static void b(int i) {
        b(0, false, i);
    }

    public static void b(int i, boolean z, final int i2) {
        try {
            int g;
            ((s) p.get(i2)).m = (JSONObject) ((s) p.get(i2)).d.get(i);
            ((s) p.get(i2)).d.remove(i);
            if (z) {
                ((s) p.get(i2)).c.clear();
                ((s) p.get(i2)).d.clear();
            }
            String string = ((s) p.get(i2)).m.getString("status");
            final String string2 = ((s) p.get(i2)).m.getString("id");
            final o a = a(Appodeal.b, string);
            if (a == null || !(a.g() instanceof aa)) {
                g = ((s) p.get(i2)).g();
            } else {
                g = -2;
            }
            if (((s) p.get(i2)).u && (((s) p.get(i2)).m.optBoolean("offer") || (a != null && (a.g() instanceof s)))) {
                q.b(i2, g, a);
            } else if (a == null) {
                q.a(i2, g);
            } else if (a.g() == null || ((AppodealSettings.a && !a.g().e()) || ((VERSION.SDK_INT > 22 && !a.e()) || (!com.appodeal.ads.utils.c.a(Appodeal.b) && a.f())))) {
                q.b(i2, g, a);
            } else {
                ((s) p.get(i2)).f.add(((s) p.get(i2)).m.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (!(Appodeal.e == null || ((s) n.p.get(i2)).u)) {
                                Appodeal.e.a(1, a.a(), string2);
                            }
                            a.g().a(Appodeal.getLogLevel() == LogLevel.verbose);
                            a.g().a(Appodeal.b, i2, g);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            q.b(i2, g, a);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                q.b(i2, g, a);
                            }
                        }, 10000);
                    }
                });
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            q.a(i2, ((s) p.get(i2)).g());
        }
    }

    static boolean a(Activity activity, c cVar) {
        return new b(activity).a(cVar).a();
    }

    static boolean a(final Activity activity, final c cVar, String str) {
        try {
            s = cVar;
            if (!an.a((Context) activity)) {
                return false;
            }
            if (Appodeal.a || d || g.a().b().c()) {
                return false;
            }
            boolean equals = str.equals("debug");
            if (p.isEmpty()) {
                Appodeal.a(String.format("Showing Interstitial (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false), cVar.x()}));
                if (!cVar.a(1, null)) {
                    return false;
                }
                if (equals || !m) {
                    return false;
                }
                new a(activity).b().a();
                an.m(activity);
                return true;
            }
            final int size = p.size() - 1;
            e eVar = (s) p.get(size);
            Appodeal.a(String.format("Showing Interstitial (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(eVar.s), Boolean.valueOf(eVar.r), cVar.x()}));
            if (!cVar.a(1, eVar)) {
                return false;
            }
            if (eVar.s) {
                final o a = a(Appodeal.b, eVar.p);
                if (a != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                cVar.b(1);
                                a.g().a(activity, size);
                            } catch (Throwable e) {
                                Appodeal.a(e);
                            }
                        }
                    });
                    return true;
                }
            } else if (eVar.t) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cVar.b(1);
                            com.appodeal.ads.b.c.f().g().a(activity, size);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                });
                return true;
            } else if (eVar.c()) {
                eVar.x = true;
                an.m(activity);
                return true;
            } else if (!equals && m) {
                new a(activity).b().a();
                an.m(activity);
                return true;
            }
            return false;
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void a(Context context, final String str, final Class cls, boolean z, final String... strArr) {
        if (!b.contains(str)) {
            if (z) {
                com.appodeal.ads.utils.i.a(context, String.format("%s.dex", new Object[]{str}), strArr[0], new Runnable() {
                    public void run() {
                        if (!n.b.contains(str)) {
                            try {
                                o a = n.b(cls, str, strArr);
                                if (a != null) {
                                    n.a.add(a);
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
                o b = b(cls, str, strArr);
                if (b != null) {
                    a.add(b);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private static o b(Class<?> cls, String str, String[] strArr) {
        try {
            return (o) cls.getDeclaredMethod("getInstance", new Class[]{String.class, String[].class}).invoke(cls, new Object[]{str, strArr});
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
