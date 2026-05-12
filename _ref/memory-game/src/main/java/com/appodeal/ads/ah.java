package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import com.applovin.sdk.AppLovinSdk;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;
import com.appodeal.ads.f.g;
import com.appodeal.ads.g.aa;
import com.appodeal.ads.g.ac;
import com.appodeal.ads.g.e;
import com.appodeal.ads.g.f;
import com.appodeal.ads.g.i;
import com.appodeal.ads.g.k;
import com.appodeal.ads.g.o;
import com.appodeal.ads.g.q;
import com.appodeal.ads.g.r;
import com.appodeal.ads.g.s;
import com.appodeal.ads.g.u;
import com.appodeal.ads.g.w;
import com.appodeal.ads.g.y;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.m;
import com.mopub.common.AdType;
import com.mopub.common.FullAdType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class ah {
    private static Integer A = null;
    static Set<ap> a = null;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    static SkippableVideoCallbacks e;
    static int f = 0;
    public static long g = 0;
    static final ArrayList<JSONObject> h = new ArrayList();
    static final ArrayList<JSONObject> i = new ArrayList();
    public static boolean j;
    static boolean k = true;
    static int l = 5000;
    public static final ArrayList<ar> m = new ArrayList();
    public static int n;
    public static int o;
    public static int p;
    static c q = d.a();
    static boolean r = false;
    static boolean s = true;
    static int t = 0;
    static ArrayList<String> u = new ArrayList();
    public static int v = 90000;
    public static boolean w = true;
    static m x;
    private static boolean y = false;
    private static String z;

    private static class a implements com.appodeal.ads.t.a {
        private a() {
        }

        public void a(int i) {
            aj.a(true);
        }

        public void a(JSONObject jSONObject, int i, String str) {
            try {
                if (ah.c || jSONObject.optBoolean("video_disabled")) {
                    ah.c = true;
                    Appodeal.a("Video disabled");
                } else if (jSONObject.has("ads") && jSONObject.has("main_id")) {
                    ah.g = System.currentTimeMillis();
                    ah.h.clear();
                    JSONArray optJSONArray = jSONObject.optJSONArray("ads");
                    if (optJSONArray != null) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            ah.h.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    if (jSONObject.has("main_id")) {
                        ah.z = jSONObject.getString("main_id");
                    }
                    if (jSONObject.has("rri")) {
                        ah.r = jSONObject.optBoolean("rri");
                    }
                    if (jSONObject.has("ad_watch")) {
                        ah.s = jSONObject.optBoolean("ad_watch", true);
                    }
                    if (jSONObject.has("max_duration")) {
                        ah.v = jSONObject.optInt("max_duration", 0);
                    }
                    if (jSONObject.has("disable_long_video")) {
                        ah.w = jSONObject.optBoolean("disable_long_video", true);
                    }
                    if (ah.A == null && jSONObject.has("waterfall_cache_timeout")) {
                        ah.A = Integer.valueOf(jSONObject.getInt("waterfall_cache_timeout"));
                    }
                    if (ao.a == null && jSONObject.has("video_wo_banners")) {
                        ao.a = Boolean.valueOf(jSONObject.getBoolean("video_wo_banners"));
                    }
                    if (jSONObject.has("fraud_detector")) {
                        ah.x = new m(jSONObject.getJSONObject("fraud_detector"));
                        ah.x.a(2);
                        ah.x.b(ah.z);
                    }
                    AppodealSettings.a(jSONObject);
                    b.a(ah.h, ah.i, 2);
                    ((ar) ah.m.get(i)).c = new ArrayList(ah.i);
                    an.a("Video", null, ah.i);
                    ((ar) ah.m.get(i)).m = ah.z;
                    ((ar) ah.m.get(i)).a = Long.valueOf(g.a().c());
                    if (!AppodealSettings.i) {
                        try {
                            ((ar) ah.m.get(i)).A = new com.appodeal.ads.d.g(jSONObject.optJSONObject("rtb"));
                            ((ar) ah.m.get(i)).A.a(jSONObject.optBoolean("disable_rtb"));
                            ((ar) ah.m.get(i)).A.b(jSONObject.optBoolean("for_kids"));
                            ((ar) ah.m.get(i)).A.a(jSONObject.optJSONObject("user_data"));
                            ((ar) ah.m.get(i)).A.b(jSONObject.optJSONObject("app_data"));
                            ((ar) ah.m.get(i)).y = jSONObject.optBoolean("disable_rtb");
                            if (!((ar) ah.m.get(i)).y) {
                                ((ar) ah.m.get(i)).z = ((ar) ah.m.get(i)).A.c().size() > 0;
                            }
                            if (AppodealSettings.g) {
                                ah.a(i, Double.valueOf(1.0E-4d));
                                return;
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                    if (((ar) ah.m.get(i)).a()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(2);
                        }
                        f.g().g().a(Appodeal.b, i);
                    } else if (((ar) ah.m.get(i)).c.isEmpty()) {
                        aj.a(true);
                    } else {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(2);
                        }
                        ah.a(i);
                    }
                } else {
                    if (jSONObject.has("message")) {
                        Appodeal.a(jSONObject.getString("message"));
                    }
                    aj.a(true);
                }
            } catch (Throwable e2) {
                Appodeal.a(e2);
                aj.a(true);
            }
        }
    }

    static class b implements com.appodeal.ads.d.i.a {
        b() {
        }

        public void a(int i, com.appodeal.ads.d.a aVar, h hVar) {
            ((ar) ah.m.get(i)).y = true;
            ((ar) ah.m.get(i)).x = -2;
            if (aVar == null) {
                aj.a(i, ((ar) ah.m.get(i)).f(), ((ar) ah.m.get(i)).p, true);
            } else if (aVar.b() != null) {
                try {
                    if (!(Appodeal.e == null || ((ar) ah.m.get(i)).p == null)) {
                        Appodeal.e.a(2, ((ar) ah.m.get(i)).p.a(), false);
                    }
                    int size = ((ar) ah.m.get(i)).c.size();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", aVar.i().c());
                    jSONObject.put("ecpm", aVar.b());
                    if (aVar.i().m() == null || !aVar.i().m().equals(AdType.MRAID)) {
                        jSONObject.put("status", "rtbvast");
                        jSONObject.put("vast_xml", aVar.e());
                        jSONObject.put("vpaid_url", aVar.c());
                        jSONObject.put("video_auto_close", aVar.i().j());
                        jSONObject.put("video_wo_banners", ao.a() ? ao.a() : aVar.i().i().booleanValue());
                    } else {
                        jSONObject.put("status", "rtbmraid");
                        jSONObject.put(AdType.HTML, aVar.e());
                        jSONObject.put("width", 0);
                        jSONObject.put("height", 0);
                        jSONObject.put("ext", aVar.g());
                    }
                    ((ar) ah.m.get(i)).c.add(jSONObject);
                    ah.a(size, false, i);
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        }

        public void a(com.appodeal.ads.d.f fVar) {
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

    public static Set<ap> a(Context context) {
        if (a == null) {
            a = new HashSet();
            a(context, "adcolony", com.appodeal.ads.g.a.class, true, "com.jirbo.adcolony.AdColony");
            a(context, AppLovinSdk.URI_SCHEME, com.appodeal.ads.g.c.class, false, "com.applovin.sdk.AppLovinSdk");
            a(context, "chartboost", e.class, false, "com.chartboost.sdk.Chartboost");
            a(context, "flurry", com.appodeal.ads.g.g.class, true, "com.flurry.android.ads.FlurryAdInterstitial", "com.flurry.android.FlurryAgent");
            a(context, "mailru", com.appodeal.ads.g.m.class, false, "com.my.target.ads.InterstitialAd");
            a(context, "mopub", o.class, false, "com.mopub.mobileads.MoPubInterstitial");
            a(context, AdType.MRAID, i.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "rtbmraid", q.class, false, "org.nexage.sourcekit.mraid.MRAIDInterstitial");
            a(context, "mraid_va", k.class, false, "org.nexage.sourcekit.mraid.MRAIDVideoAddendumInterstitial");
            a(context, "revmob", s.class, true, "com.revmob.ads.interstitial.RevMobFullscreen");
            a(context, "spotx", u.class, false, "com.appodeal.ads.networks.spotx.SpotXVPAIDView", "org.nexage.sourcekit.vast.VASTPlayer");
            a(context, "unity_ads", w.class, false, "com.unity3d.ads2.UnityAds");
            a(context, FullAdType.VAST, y.class, false, "org.nexage.sourcekit.vast.VASTPlayer");
            a(context, "rtbvast", r.class, false, "org.nexage.sourcekit.vast.VASTPlayer");
            a(context, "vpaid", aa.class, false, "com.appodeal.ads.networks.vpaid.VPAIDView");
            a(context, "vungle", ac.class, true, "com.vungle.publisher.VunglePub");
        }
        return a;
    }

    private static boolean d() {
        if (a == null || a.size() == t) {
            return false;
        }
        t = a.size();
        return true;
    }

    static void a(Activity activity) {
        if (!y && !b) {
            y = true;
            try {
                for (ap apVar : a((Context) activity)) {
                    if (apVar.g() == null && (VERSION.SDK_INT >= 14 || !an.b((Context) activity, apVar.b()))) {
                        String format = String.format("ERROR: %s not found", new Object[]{an.a(apVar.a())});
                        Appodeal.a(format);
                        an.b(activity, format);
                    }
                }
                com.appodeal.ads.utils.c.c(activity);
                if (k) {
                    b(activity);
                }
                Appodeal.a("Video Initialized");
                b = true;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            y = false;
        }
    }

    public static ap a(Context context, String str) {
        for (ap apVar : a(context)) {
            if (apVar.a().equals(str)) {
                return apVar;
            }
        }
        return null;
    }

    static ar a() {
        if (m.isEmpty()) {
            return null;
        }
        return (ar) m.get(m.size() - 1);
    }

    static void b(Activity activity) {
        new a(activity).a();
    }

    static void a(Activity activity, String str, boolean z) {
        try {
            if (!an.a((Context) activity)) {
                aj.a();
            } else if (!Appodeal.a && !c && !g.a().b().e()) {
                if (a() == null) {
                    Appodeal.a(String.format("Caching Video (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false)}));
                } else {
                    Appodeal.a(String.format("Caching Video (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(a().r), Boolean.valueOf(a().q)}));
                }
                ar arVar = new ar(str);
                if (m.size() > 0) {
                    arVar.z = ((ar) m.get(f)).z;
                    arVar.y = ((ar) m.get(f)).y;
                    arVar.A = new com.appodeal.ads.d.g(((ar) m.get(f)).A);
                }
                m.add(arVar);
                int size = m.size() - 1;
                f = size;
                arVar.k = System.currentTimeMillis();
                arVar.q = true;
                arVar.m = z;
                arVar.v = z;
                g.d();
                arVar.a = Long.valueOf(g.a().c());
                for (int i = 0; i < m.size() - 2; i++) {
                    ar arVar2 = (ar) m.get(i);
                    if (!arVar2.u) {
                        arVar2.a(i);
                    }
                }
                if (d() || arVar.a() || g == 0 || System.currentTimeMillis() - g > ((long) AppodealSettings.a(A))) {
                    new t.c(activity, size, str).a(new a()).a(arVar.a).a().a();
                    return;
                }
                if (j) {
                    b.a(h, i, 2);
                }
                arVar.c = new ArrayList(i);
                j = false;
                if (!AppodealSettings.g) {
                    if (arVar.c.isEmpty()) {
                        aj.a(true);
                        return;
                    }
                    if (Appodeal.e != null) {
                        Appodeal.e.a(2);
                    }
                    a(size);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            aj.a(true);
        }
    }

    static void a(int i, Double d) {
        ar arVar = (ar) m.get(i);
        Double valueOf = Double.valueOf(0.0d);
        String str = "";
        if (AppodealSettings.g) {
            valueOf = Double.valueOf(0.001d);
        } else if (i.size() > 0) {
            try {
                str = arVar.l.getString("id");
                valueOf = d;
            } catch (Throwable e) {
                Appodeal.a(e);
                valueOf = d;
            }
        }
        com.appodeal.ads.d.i iVar = new com.appodeal.ads.d.i(Appodeal.b, 2, i, ((ar) m.get(i)).A, new b(), valueOf, str, arVar.m);
    }

    static void a(int i) {
        a(0, false, i);
    }

    public static void a(int i, boolean z, final int i2) {
        try {
            int f;
            String str;
            if (((ar) m.get(i2)).c.size() == 1 && ((ar) m.get(i2)).d != null && ((ar) m.get(i2)).d == ((ar) m.get(i2)).c.get(i)) {
                ap a = a(Appodeal.b, ((ar) m.get(i2)).d.getString("status"));
                if (!(a == null || a.g() == null || !a.g().d())) {
                    return;
                }
            }
            ((ar) m.get(i2)).l = (JSONObject) ((ar) m.get(i2)).c.get(i);
            ((ar) m.get(i2)).c.remove(i);
            if (z) {
                ((ar) m.get(i2)).c.clear();
            }
            String string = ((ar) m.get(i2)).l.getString("status");
            final String string2 = ((ar) m.get(i2)).l.getString("id");
            ap a2 = a(Appodeal.b, string);
            if (a2 == null || !(a2.g() instanceof r)) {
                f = ((ar) m.get(i2)).f();
            } else {
                f = -2;
            }
            if (z || a2 == null || a2.g() == null || !a2.g().d()) {
                str = string;
            } else {
                ((ar) m.get(i2)).d = ((ar) m.get(i2)).l;
                ((ar) m.get(i2)).c.add(((ar) m.get(i2)).l);
                if (((ar) m.get(i2)).f() > 1) {
                    ((ar) m.get(i2)).l = (JSONObject) ((ar) m.get(i2)).c.get(i);
                    ((ar) m.get(i2)).c.remove(i);
                    str = ((ar) m.get(i2)).l.getString("status");
                } else {
                    return;
                }
            }
            final ap a3 = a(Appodeal.b, str);
            if (((ar) m.get(i2)).s && (((ar) m.get(i2)).l.optBoolean("offer") || (a3 != null && (a3.g() instanceof y)))) {
                aj.b(i2, f, a3);
            } else if (a3 == null) {
                aj.a(i2, f);
            } else if (a3.g() == null || ((AppodealSettings.a && !a3.g().e()) || ((ao.a() && !a3.g().f()) || ((VERSION.SDK_INT > 22 && !a3.e()) || (!com.appodeal.ads.utils.c.a(Appodeal.b) && a3.f()))))) {
                aj.b(i2, f, a3);
            } else {
                ((ar) m.get(i2)).e.add(((ar) m.get(i2)).l.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (!(Appodeal.e == null || ((ar) ah.m.get(i2)).s)) {
                                Appodeal.e.a(2, a3.a(), string2);
                            }
                            a3.g().a(Appodeal.getLogLevel() == LogLevel.verbose);
                            a3.g().a(Appodeal.b, i2, f);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            aj.b(i2, f, a3);
                        }
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                aj.b(i2, f, a3);
                            }
                        }, 35000);
                    }
                });
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            aj.a(i2, ((ar) m.get(i2)).f());
        }
    }

    static boolean a(Activity activity, c cVar) {
        return new b(activity).a(cVar).a();
    }

    static boolean a(final Activity activity, final c cVar, String str) {
        try {
            q = cVar;
            if (!an.a((Context) activity)) {
                return false;
            }
            if (Appodeal.a || c || g.a().b().e()) {
                return false;
            }
            boolean equals = str.equals("debug_video");
            if (m.isEmpty()) {
                Appodeal.a(String.format("Showing Video (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false), cVar.x()}));
                if (!cVar.a(2, null)) {
                    return false;
                }
                if (!equals && k) {
                    new a(activity).b().a();
                    an.m(activity);
                }
                return true;
            }
            final int size = m.size() - 1;
            e eVar = (ar) m.get(size);
            Appodeal.a(String.format("Showing Video (debugType: %s, isLoaded: %s, isLoading: %s, placement: '%s')", new Object[]{str, Boolean.valueOf(eVar.r), Boolean.valueOf(eVar.q), cVar.x()}));
            if (!cVar.a(2, eVar)) {
                return false;
            }
            if (eVar.r) {
                final ap a = a((Context) activity, eVar.o);
                if (a != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                AudioManager audioManager = (AudioManager) activity.getSystemService("audio");
                                if (AppodealSettings.e && audioManager.getStreamVolume(2) == 0) {
                                    AppodealSettings.f = audioManager.getStreamVolume(3);
                                    audioManager.setStreamVolume(3, 0, 0);
                                }
                                cVar.b(2);
                                a.g().a(activity, size);
                            } catch (Throwable e) {
                                Appodeal.a(e);
                            }
                        }
                    });
                    return true;
                }
            } else if (eVar.c()) {
                eVar.v = true;
                an.m(activity);
                return true;
            } else if (!equals && k) {
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
        if (!u.contains(str)) {
            if (z) {
                com.appodeal.ads.utils.i.a(context, String.format("%s.dex", new Object[]{str}), strArr[0], new Runnable() {
                    public void run() {
                        if (!ah.u.contains(str)) {
                            try {
                                ap a = ah.b(cls, str, strArr);
                                if (a != null) {
                                    ah.a.add(a);
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
                ap b = b(cls, str, strArr);
                if (b != null) {
                    a.add(b);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private static ap b(Class<?> cls, String str, String[] strArr) {
        try {
            return (ap) cls.getDeclaredMethod("getInstance", new Class[]{String.class, String[].class}).invoke(cls, new Object[]{str, strArr});
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
