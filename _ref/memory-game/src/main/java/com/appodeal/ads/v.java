package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.ViewManager;
import android.view.ViewParent;
import com.appodeal.ads.c.e;
import com.appodeal.ads.c.i;
import com.appodeal.ads.c.j;
import com.appodeal.ads.c.l;
import com.appodeal.ads.c.o;
import com.appodeal.ads.c.q;
import com.appodeal.ads.c.r;
import com.appodeal.ads.d.f;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.d;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.m;
import com.appodeal.ads.utils.t;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class v {
    static ArrayList<String> A = new ArrayList();
    static int B = 0;
    static m C;
    private static boolean D = false;
    private static String E;
    private static Integer F = null;
    private static Integer G = null;
    static Set<w> a = null;
    static boolean b = false;
    public static boolean c = false;
    static MrecCallbacks d;
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
    public static MrecView r;
    public static c s = c.NEVER_SHOWN;
    public static final ArrayList<aa> t = new ArrayList();
    public static int u;
    public static int v;
    static com.appodeal.ads.f.c w = d.a();
    static boolean x = false;
    static boolean y = true;
    static int z = 0;

    private static class a implements com.appodeal.ads.t.a {
        private a() {
        }

        public void a(int i) {
            y.a(true);
        }

        public void a(JSONObject jSONObject, int i, String str) {
            try {
                if (v.c || jSONObject.optBoolean("mrec_disabled")) {
                    v.c = true;
                    Appodeal.a("Mrec disabled");
                } else if (jSONObject.has("ads") && jSONObject.has("main_id")) {
                    int i2;
                    v.f = System.currentTimeMillis();
                    v.g.clear();
                    v.h.clear();
                    JSONArray optJSONArray = jSONObject.optJSONArray("precache");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            v.g.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    optJSONArray = jSONObject.optJSONArray("ads");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            v.h.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    if (jSONObject.has("main_id")) {
                        v.E = jSONObject.getString("main_id");
                    }
                    if (jSONObject.has("rri")) {
                        v.x = jSONObject.optBoolean("rri");
                    }
                    if (jSONObject.has("ad_watch")) {
                        v.y = jSONObject.optBoolean("ad_watch", true);
                    }
                    v.B = jSONObject.optInt("afd", 0);
                    if (jSONObject.has("refresh_period")) {
                        v.F = Integer.valueOf(jSONObject.optInt("refresh_period") * 1000);
                    }
                    if (v.G == null && jSONObject.has("waterfall_cache_timeout")) {
                        v.G = Integer.valueOf(jSONObject.getInt("waterfall_cache_timeout"));
                    }
                    if (jSONObject.has("fraud_detector")) {
                        v.C = new m(jSONObject.getJSONObject("fraud_detector"));
                        v.C.a(256);
                        v.C.b(v.E);
                    }
                    AppodealSettings.a(jSONObject);
                    b.a(v.g, v.i, 256);
                    ((aa) v.t.get(i)).c = new ArrayList(v.i);
                    b.a(v.h, v.j, 256);
                    ((aa) v.t.get(i)).d = new ArrayList(v.j);
                    an.a("MREC", v.i, v.j);
                    ((aa) v.t.get(i)).m = v.E;
                    ((aa) v.t.get(i)).a = Long.valueOf(g.a().c());
                    if (!AppodealSettings.i) {
                        try {
                            ((aa) v.t.get(i)).B = new com.appodeal.ads.d.g(jSONObject.optJSONObject("rtb"));
                            ((aa) v.t.get(i)).B.a(jSONObject.optBoolean("disable_rtb"));
                            ((aa) v.t.get(i)).B.b(jSONObject.optBoolean("for_kids"));
                            ((aa) v.t.get(i)).B.a(jSONObject.optJSONObject("user_data"));
                            ((aa) v.t.get(i)).B.b(jSONObject.optJSONObject("app_data"));
                            ((aa) v.t.get(i)).z = jSONObject.optBoolean("disable_rtb");
                            if (!((aa) v.t.get(i)).z) {
                                ((aa) v.t.get(i)).A = ((aa) v.t.get(i)).B.c().size() > 0;
                            }
                            if (AppodealSettings.g) {
                                v.a(i, Double.valueOf(1.0E-4d));
                                return;
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                    if (((aa) v.t.get(i)).a()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(256);
                        }
                        i.h().f().a(Appodeal.b, i.h(), i, false);
                    } else if (!((aa) v.t.get(i)).c.isEmpty()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(256);
                        }
                        v.a(i);
                    } else if (((aa) v.t.get(i)).d.isEmpty()) {
                        y.a(true);
                    } else {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(256);
                        }
                        v.b(i);
                    }
                } else {
                    if (jSONObject.has("message")) {
                        Appodeal.a(jSONObject.getString("message"));
                    }
                    y.a(true);
                }
            } catch (Throwable e2) {
                Appodeal.a(e2);
                y.a(true);
            }
        }
    }

    static class b implements com.appodeal.ads.d.i.a {
        b() {
        }

        public void a(int i, com.appodeal.ads.d.a aVar, h hVar) {
            ((aa) v.t.get(i)).z = true;
            ((aa) v.t.get(i)).y = -2;
            if (aVar == null) {
                y.a(i, ((aa) v.t.get(i)).g(), ((aa) v.t.get(i)).p, ((aa) v.t.get(i)).r ? false : ((aa) v.t.get(i)).s, true);
            } else if (aVar.b() != null) {
                try {
                    if (!(Appodeal.e == null || ((aa) v.t.get(i)).p == null)) {
                        Appodeal.e.a(256, ((aa) v.t.get(i)).p.a(), false);
                    }
                    int size = ((aa) v.t.get(i)).d.size();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("status", "rtbmraid");
                    jSONObject.put("id", aVar.i().c());
                    jSONObject.put("ecpm", aVar.b());
                    jSONObject.put(AdType.HTML, aVar.e());
                    jSONObject.put("width", 300);
                    jSONObject.put("height", Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                    ((aa) v.t.get(i)).d.add(jSONObject);
                    v.b(size, false, i);
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

    public enum c {
        VISIBLE,
        HIDDEN,
        NEVER_SHOWN
    }

    public static Set<w> a(Context context) {
        if (a == null) {
            a = new HashSet();
            a(context, "admob", com.appodeal.ads.c.a.class, false, "com.google.android.gms.ads.AdView");
            a(context, "amazon_ads", e.class, false, "com.amazon.device.ads.AdLayout");
            a(context, "cheetah", com.appodeal.ads.c.g.class, false, "com.cmcm.adsdk.banner.CMNativeBannerView");
            a(context, "facebook", j.class, true, "com.facebook.ads.AdView");
            a(context, "inner-active", l.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "mopub", o.class, false, "com.mopub.mobileads.MoPubView");
            a(context, AdType.MRAID, com.appodeal.ads.c.m.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "rtbmraid", q.class, false, "org.nexage.sourcekit.mraid.MRAIDView");
            a(context, "yandex", r.class, true, "com.yandex.mobile.ads.AdView");
        }
        return a;
    }

    private static boolean e() {
        if (a == null || a.size() == z) {
            return false;
        }
        z = a.size();
        return true;
    }

    static void a(Activity activity) {
        if (!D && !b) {
            D = true;
            try {
                for (w wVar : a((Context) activity)) {
                    if (wVar.f() == null && (VERSION.SDK_INT >= 14 || !an.b((Context) activity, wVar.b()))) {
                        String format = String.format("ERROR: %s not found", new Object[]{an.a(wVar.a())});
                        Appodeal.a(format);
                        an.b(activity, format);
                    }
                }
                if (l) {
                    b(activity);
                }
                Appodeal.a("Mrec Initialized");
                b = true;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            D = false;
        }
    }

    static w a(Context context, String str) {
        for (w wVar : a(context)) {
            if (wVar.a().equals(str)) {
                return wVar;
            }
        }
        return null;
    }

    static aa a() {
        if (t.isEmpty()) {
            return null;
        }
        return (aa) t.get(t.size() - 1);
    }

    public static Integer b() {
        if (w != null && w.t() > 0) {
            F = Integer.valueOf(w.t());
        } else if (F == null) {
            F = Integer.valueOf(15000);
        }
        return F;
    }

    static void b(Activity activity) {
        new a(activity).a();
    }

    static void a(Activity activity, String str, boolean z, boolean z2) {
        try {
            if (!an.a((Context) activity)) {
                y.a();
            } else if (!Appodeal.a && !c && !g.a().b().g()) {
                if (a() == null) {
                    Appodeal.a(String.format("Caching Mrec (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false)}));
                } else {
                    Appodeal.a(String.format("Caching Mrec (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(a().r), Boolean.valueOf(a().q)}));
                }
                aa aaVar = new aa(str);
                if (t.size() > 0) {
                    aaVar.A = ((aa) t.get(e)).A;
                    aaVar.z = ((aa) t.get(e)).z;
                    aaVar.B = new com.appodeal.ads.d.g(((aa) t.get(e)).B);
                }
                t.add(aaVar);
                int size = t.size() - 1;
                e = size;
                aaVar.k = System.currentTimeMillis();
                aaVar.q = true;
                aaVar.m = E;
                if (z) {
                    aaVar.w = true;
                }
                g.d();
                aaVar.a = Long.valueOf(g.a().c());
                for (int i = 0; i < t.size() - 3; i++) {
                    aa aaVar2 = (aa) t.get(i);
                    if (!aaVar2.v) {
                        aaVar2.a(i);
                    }
                }
                if (e() || aaVar.a() || f == 0 || System.currentTimeMillis() - f > ((long) AppodealSettings.a(G))) {
                    new com.appodeal.ads.t.c(activity, size, str).a(new a()).a(aaVar.a).a().a();
                    return;
                }
                if (k) {
                    b.a(g, i, 256);
                }
                aaVar.c = new ArrayList(i);
                if (!z2 || size <= 0 || ((aa) t.get(size - 1)).d.isEmpty() || k) {
                    if (k) {
                        b.a(h, j, 256);
                    }
                    aaVar.d = new ArrayList(j);
                } else {
                    aaVar.d = new ArrayList(((aa) t.get(size - 1)).d);
                }
                k = false;
                if (!aaVar.c.isEmpty()) {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(256);
                    }
                    a(size);
                } else if (aaVar.d.isEmpty()) {
                    y.a(true);
                } else {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(256);
                    }
                    b(size);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            y.a(true);
        }
    }

    static void a(int i, Double d) {
        aa aaVar = (aa) t.get(i);
        Double valueOf = Double.valueOf(0.0d);
        String str = "";
        if (AppodealSettings.g) {
            valueOf = Double.valueOf(5.0E-5d);
        } else if (j.size() > 0) {
            try {
                str = aaVar.l.getString("id");
                valueOf = d;
            } catch (Throwable e) {
                Appodeal.a(e);
                valueOf = d;
            }
        }
        com.appodeal.ads.d.i iVar = new com.appodeal.ads.d.i(Appodeal.b, 7, i, ((aa) t.get(i)).B, new b(), valueOf, str, aaVar.m);
    }

    static void a(int i) {
        a(0, false, i);
    }

    public static void a(int i, boolean z, final int i2) {
        try {
            ((aa) t.get(i2)).l = (JSONObject) ((aa) t.get(i2)).c.get(i);
            ((aa) t.get(i2)).c.remove(i);
            if (z) {
                ((aa) t.get(i2)).c.clear();
                ((aa) t.get(i2)).d.clear();
            }
            final String string = ((aa) t.get(i2)).l.getString("id");
            final int g = ((aa) t.get(i2)).g();
            if (A.contains("admob")) {
                y.b(i2, g, com.appodeal.ads.c.c.h(), true);
            }
            if (com.appodeal.ads.c.c.h().f() != null) {
                ((aa) t.get(i2)).e.add(((aa) t.get(i2)).l.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (Appodeal.e != null) {
                                Appodeal.e.a(256, com.appodeal.ads.c.c.h().a(), string);
                            }
                            com.appodeal.ads.c.c.h().f().a(Appodeal.b, i2, g);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            y.b(i2, g, com.appodeal.ads.c.c.h(), true);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                y.b(i2, g, com.appodeal.ads.c.c.h(), true);
                            }
                        }, 5000);
                    }
                });
                return;
            }
            y.b(i2, g, com.appodeal.ads.c.c.h(), true);
        } catch (Throwable e) {
            Appodeal.a(e);
            y.a(i2, ((aa) t.get(i2)).g());
        }
    }

    static void b(int i) {
        b(0, false, i);
    }

    public static void b(int i, boolean z, final int i2) {
        try {
            int g;
            ((aa) t.get(i2)).l = (JSONObject) ((aa) t.get(i2)).d.get(i);
            ((aa) t.get(i2)).d.remove(i);
            if (z) {
                ((aa) t.get(i2)).c.clear();
                ((aa) t.get(i2)).d.clear();
            }
            String string = ((aa) t.get(i2)).l.getString("status");
            final String string2 = ((aa) t.get(i2)).l.getString("id");
            final w a = a(Appodeal.b, string);
            if (a == null || !(a.f() instanceof q)) {
                g = ((aa) t.get(i2)).g();
            } else {
                g = -2;
            }
            if (((aa) t.get(i2)).t && (((aa) t.get(i2)).l.optBoolean("offer") || (a != null && (a.f() instanceof com.appodeal.ads.c.m)))) {
                y.b(i2, g, a);
            } else if (a == null) {
                y.a(i2, g);
            } else if (a.f() == null || ((AppodealSettings.a && !a.f().g()) || ((VERSION.SDK_INT > 22 && !a.d()) || (!com.appodeal.ads.utils.c.a(Appodeal.b) && a.e())))) {
                y.b(i2, g, a);
            } else {
                ((aa) t.get(i2)).f.add(((aa) t.get(i2)).l.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (!(Appodeal.e == null || ((aa) v.t.get(i2)).t)) {
                                Appodeal.e.a(256, a.a(), string2);
                            }
                            a.f().a(Appodeal.getLogLevel() == LogLevel.verbose);
                            a.f().a(Appodeal.b, i2, g);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            y.b(i2, g, a);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                y.b(i2, g, a);
                            }
                        }, 10000);
                    }
                });
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            y.a(i2, ((aa) t.get(i2)).g());
        }
    }

    static boolean a(Activity activity, com.appodeal.ads.f.c cVar) {
        return new b(activity).a(cVar).a();
    }

    static boolean a(final Activity activity, String str, boolean z, final com.appodeal.ads.f.c cVar) {
        try {
            w = cVar;
            if (!an.a((Context) activity)) {
                return false;
            }
            if (Appodeal.a || c || ((z && s == c.HIDDEN) || g.a().b().g())) {
                return false;
            }
            boolean equals = str.equals("debug_mrec");
            if (t.isEmpty()) {
                Appodeal.a(String.format("Showing Mrec (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false), cVar.x()}));
                if (!cVar.a(256, null)) {
                    return false;
                }
                if (equals || !l) {
                    return false;
                }
                new a(activity).b().a();
                s = c.VISIBLE;
                return true;
            }
            final int size = t.size() - 1;
            e eVar = (aa) t.get(size);
            Appodeal.a(String.format("Showing Mrec (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(eVar.r), Boolean.valueOf(eVar.q), cVar.x()}));
            if (!cVar.a(256, eVar)) {
                return false;
            }
            if (eVar.r) {
                final w a = a((Context) activity, eVar.o);
                if (a != null) {
                    if (activity.findViewById(o) == null && r == null) {
                        Appodeal.a("MrecView not found");
                        return false;
                    }
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                cVar.b(256);
                                a.f().a(activity, a, size, false);
                            } catch (Throwable e) {
                                Appodeal.a(e);
                            }
                        }
                    });
                    s = c.VISIBLE;
                    return true;
                }
            } else if (eVar.s) {
                if (activity.findViewById(o) == null && r == null) {
                    Appodeal.a("MrecView not found");
                    return false;
                }
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cVar.b(256);
                            com.appodeal.ads.c.c.h().f().a(activity, com.appodeal.ads.c.c.h(), size, false);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                });
                s = c.VISIBLE;
                return true;
            } else if (eVar.c()) {
                eVar.w = true;
                if (!(q == -1 || p == null)) {
                    aa aaVar = (aa) t.get(q);
                    final w a2 = a((Context) activity, aaVar.o);
                    if (!(!aaVar.r || aaVar.v || a2 == null)) {
                        if (activity.findViewById(o) == null && r == null) {
                            Appodeal.a("MRECView not found");
                            return false;
                        } else if (p == null) {
                            return false;
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    try {
                                        a2.f().a(activity, a2, v.q, true);
                                    } catch (Throwable e) {
                                        Appodeal.a(e);
                                    }
                                }
                            });
                        }
                    }
                }
                s = c.VISIBLE;
                return true;
            } else if (!equals && l) {
                new a(activity).b().a();
                s = c.VISIBLE;
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
                    if (v.p != null) {
                        t.a(v.p);
                        v.p.setVisibility(8);
                        ViewParent parent = v.p.getParent();
                        if (parent != null) {
                            if (parent instanceof ViewManager) {
                                ((ViewManager) parent).removeView(v.p);
                            }
                            if (parent instanceof MrecView) {
                                ((MrecView) parent).setVisibility(8);
                            }
                        }
                        aa a = v.a();
                        if (v.l && a != null && !a.c() && a.h) {
                            v.b(activity);
                        }
                    }
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        });
        s = c.HIDDEN;
    }

    public static void a(Context context, final String str, final Class cls, boolean z, final String... strArr) {
        if (!A.contains(str)) {
            if (z) {
                com.appodeal.ads.utils.i.a(context, String.format("%s.dex", new Object[]{str}), strArr[0], new Runnable() {
                    public void run() {
                        if (!v.A.contains(str)) {
                            try {
                                w a = v.b(cls, str, strArr);
                                if (a != null) {
                                    v.a.add(a);
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
                w b = b(cls, str, strArr);
                if (b != null) {
                    a.add(b);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private static w b(Class<?> cls, String str, String[] strArr) {
        try {
            return (w) cls.getDeclaredMethod("getInstance", new Class[]{String.class, String[].class}).invoke(cls, new Object[]{str, strArr});
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
