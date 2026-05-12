package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.applovin.sdk.AppLovinSdk;
import com.appodeal.ads.d.c;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.g;
import com.appodeal.ads.native_ad.d;
import com.appodeal.ads.native_ad.e;
import com.appodeal.ads.native_ad.f;
import com.appodeal.ads.native_ad.i;
import com.appodeal.ads.native_ad.j;
import com.appodeal.ads.native_ad.k;
import com.appodeal.ads.native_ad.l;
import com.appodeal.ads.native_ad.n;
import com.appodeal.ads.native_ad.o;
import com.appodeal.ads.utils.m;
import com.cmcm.adsdk.Const;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class Native {
    public static NativeAdType A = NativeAdType.Auto;
    public static boolean B = false;
    static NativeAdBox C;
    private static boolean D = false;
    private static String E;
    private static Integer F = null;
    static Set<ac> a;
    static boolean b = false;
    public static boolean c = false;
    public static NativeCallbacks d;
    static int e = 0;
    public static long f = 0;
    static final ArrayList<JSONObject> g = new ArrayList();
    static final ArrayList<JSONObject> h = new ArrayList();
    public static boolean i;
    static boolean j = true;
    public static int k = 5000;
    public static final ArrayList<ag> l = new ArrayList();
    public static int m = 1;
    public static boolean n = true;
    public static boolean o = true;
    public static int p;
    public static int q;
    static boolean r = false;
    public static boolean s = true;
    static int t = 0;
    static ArrayList<String> u = new ArrayList();
    static int v = 0;
    public static int w = 90000;
    public static boolean x = true;
    static m y;
    static String z;

    public enum NativeAdType {
        Auto,
        NoVideo,
        Video
    }

    private static class a implements com.appodeal.ads.t.a {
        private a() {
        }

        public void a(int i) {
            ae.a(true);
        }

        public void a(JSONObject jSONObject, int i, String str) {
            try {
                if (Native.c || jSONObject.optBoolean("native_disabled")) {
                    Native.c = true;
                    Appodeal.a("Native disabled");
                } else if (jSONObject.has("ads") && jSONObject.has("main_id")) {
                    Native.f = System.currentTimeMillis();
                    Native.g.clear();
                    JSONArray optJSONArray = jSONObject.optJSONArray("ads");
                    if (optJSONArray != null) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            Native.g.add(optJSONArray.getJSONObject(i2));
                        }
                    }
                    if (jSONObject.has("main_id")) {
                        Native.E = jSONObject.getString("main_id");
                    }
                    if (jSONObject.has("rri")) {
                        Native.r = jSONObject.optBoolean("rri");
                    }
                    if (jSONObject.has("ad_watch")) {
                        Native.s = jSONObject.optBoolean("ad_watch", true);
                    }
                    Native.v = jSONObject.optInt("afd", 0);
                    if (jSONObject.has("video_native_autostart")) {
                        Native.B = jSONObject.getBoolean("video_native_autostart");
                    }
                    if (jSONObject.has("max_duration")) {
                        Native.w = jSONObject.optInt("max_duration", 0);
                    }
                    if (jSONObject.has("disable_long_video")) {
                        Native.x = jSONObject.optBoolean("disable_long_video", true);
                    }
                    if (Native.F == null && jSONObject.has("waterfall_cache_timeout")) {
                        Native.F = Integer.valueOf(jSONObject.getInt("waterfall_cache_timeout"));
                    }
                    if (jSONObject.has("fraud_detector")) {
                        Native.y = new m(jSONObject.getJSONObject("fraud_detector"));
                        Native.y.a(512);
                        Native.y.b(Native.E);
                    }
                    if (jSONObject.has("diu")) {
                        Native.z = jSONObject.getString("diu");
                    }
                    AppodealSettings.a(jSONObject);
                    b.a(Native.g, Native.h, 512);
                    ((ag) Native.l.get(i)).c = new ArrayList(Native.h);
                    an.a("Native", null, Native.h);
                    ((ag) Native.l.get(i)).n = Native.E;
                    ((ag) Native.l.get(i)).a = Long.valueOf(g.a().c());
                    if (!AppodealSettings.i) {
                        try {
                            ((ag) Native.l.get(i)).A = new com.appodeal.ads.d.g(jSONObject.optJSONObject("rtb"));
                            ((ag) Native.l.get(i)).A.a(jSONObject.optBoolean("disable_rtb"));
                            ((ag) Native.l.get(i)).A.b(jSONObject.optBoolean("for_kids"));
                            ((ag) Native.l.get(i)).A.a(jSONObject.optJSONObject("user_data"));
                            ((ag) Native.l.get(i)).A.b(jSONObject.optJSONObject("app_data"));
                            ((ag) Native.l.get(i)).y = jSONObject.optBoolean("disable_rtb");
                            if (!((ag) Native.l.get(i)).y) {
                                ((ag) Native.l.get(i)).z = ((ag) Native.l.get(i)).A.c().size() > 0;
                            }
                            if (AppodealSettings.g) {
                                Native.a(i, Double.valueOf(1.0E-4d));
                                return;
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                    if (((ag) Native.l.get(i)).a()) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(512);
                        }
                        f.c().f().a(Appodeal.b, i);
                    } else if (((ag) Native.l.get(i)).c.isEmpty()) {
                        ae.a(true);
                    } else {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(512);
                        }
                        Native.a(i);
                    }
                } else {
                    if (jSONObject.has("message")) {
                        Appodeal.a(jSONObject.getString("message"));
                    }
                    ae.a(true);
                }
            } catch (Throwable e2) {
                Appodeal.a(e2);
                ae.a(true);
            }
        }
    }

    static class b implements com.appodeal.ads.d.i.a {
        b() {
        }

        public void a(int i, com.appodeal.ads.d.a aVar, h hVar) {
            ((ag) Native.l.get(i)).y = true;
            ((ag) Native.l.get(i)).x = -2;
            if (aVar == null) {
                ae.a(i, ((ag) Native.l.get(i)).e(), ((ag) Native.l.get(i)).q, ((ag) Native.l.get(i)).B, true);
            } else if (aVar.b() != null) {
                try {
                    JSONObject j = aVar.j();
                    if (j != null) {
                        if (!(Appodeal.e == null || ((ag) Native.l.get(i)).q == null)) {
                            Appodeal.e.a(512, ((ag) Native.l.get(i)).q.a(), false);
                        }
                        int size = ((ag) Native.l.get(i)).c.size();
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("status", "rtbnative");
                        jSONObject.put("rtb_report", hVar.b());
                        jSONObject.put("id", aVar.i().c());
                        jSONObject.put(Const.KEY_JUHE, j);
                        jSONObject.put("ecpm", aVar.b());
                        ((ag) Native.l.get(i)).c.add(jSONObject);
                        Native.a(size, false, i);
                        return;
                    }
                    Appodeal.a("RTB native ad not valid");
                    ae.a(i, ((ag) Native.l.get(i)).e(), ((ag) Native.l.get(i)).q, ((ag) Native.l.get(i)).B, true);
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

        public void a(c cVar) {
            if (AppodealSettings.g) {
                Appodeal.a(cVar.toString());
            }
        }
    }

    public static Set<ac> a(Context context) {
        if (a == null) {
            a = new HashSet();
            a(context, "adcolony", com.appodeal.ads.native_ad.a.class, true, "com.jirbo.adcolony.AdColony", "com.jirbo.adcolony.AdColonyNativeAdView");
            a(context, AppLovinSdk.URI_SCHEME, com.appodeal.ads.native_ad.b.class, false, new String[0]);
            a(context, "appodeal", com.appodeal.ads.native_ad.c.class, false, new String[0]);
            a(context, "avocarrot", d.class, true, "com.avocarrot.androidsdk.AvocarrotCustom");
            a(context, "cheetah", e.class, false, new String[0]);
            a(context, "facebook", com.appodeal.ads.native_ad.g.class, true, "com.facebook.ads.NativeAd");
            a(context, "flurry", com.appodeal.ads.native_ad.h.class, true, "com.flurry.android.ads.FlurryAdNative", "com.flurry.android.FlurryAgent");
            a(context, "inner-active", i.class, false, new String[0]);
            a(context, "mailru", j.class, false, "com.my.target.nativeads.NativeAd");
            a(context, "mopub", k.class, false, "com.mopub.nativeads.NativeAd");
            a(context, "pubnative", l.class, false, new String[0]);
            a(context, "rtbnative", com.appodeal.ads.native_ad.m.class, false, new String[0]);
            a(context, "smaato", n.class, false, new String[0]);
            a(context, "startapp", o.class, true, "com.startapp.android.publish.Ad");
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
        if (!D && !b) {
            D = true;
            try {
                for (ac acVar : a((Context) activity)) {
                    if (acVar.f() == null && (VERSION.SDK_INT >= 14 || !an.b((Context) activity, acVar.b()))) {
                        String format = String.format("ERROR: %s not found", new Object[]{an.a(acVar.a())});
                        Appodeal.a(format);
                        an.b(activity, format);
                    }
                }
                com.appodeal.ads.utils.c.g(activity);
                if (j) {
                    b(activity);
                }
                Appodeal.a("Native Initialized");
                b = true;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            D = false;
        }
    }

    static ac a(Context context, String str) {
        for (ac acVar : a(context)) {
            if (acVar.a().equals(str)) {
                return acVar;
            }
        }
        return null;
    }

    static ag a() {
        if (l.isEmpty()) {
            return null;
        }
        return (ag) l.get(l.size() - 1);
    }

    static void b(Activity activity) {
        new a(activity).a();
    }

    static void a(Activity activity, String str) {
        try {
            if (!an.a((Context) activity)) {
                ae.a();
            } else if (!Appodeal.a && !c && !g.a().b().f()) {
                if (a() == null) {
                    Appodeal.a(String.format("Caching Native (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(false), Boolean.valueOf(false)}));
                } else {
                    Appodeal.a(String.format("Caching Native (debugType: %s, isLoaded: %s, isLoading: %s)", new Object[]{str, Boolean.valueOf(a().s), Boolean.valueOf(a().r)}));
                }
                ag agVar = new ag(str);
                if (l.size() > 0) {
                    agVar.z = ((ag) l.get(e)).z;
                    agVar.y = ((ag) l.get(e)).y;
                    agVar.A = new com.appodeal.ads.d.g(((ag) l.get(e)).A);
                }
                l.add(agVar);
                int size = l.size() - 1;
                e = size;
                agVar.l = System.currentTimeMillis();
                agVar.r = true;
                agVar.n = E;
                g.d();
                agVar.a = Long.valueOf(g.a().c());
                for (int i = 0; i < l.size() - 3; i++) {
                    ag agVar2 = (ag) l.get(i);
                    if (!agVar2.v) {
                        agVar2.a(i);
                    }
                }
                if (d() || agVar.a() || f == 0 || System.currentTimeMillis() - f > ((long) AppodealSettings.a(F))) {
                    new t.c(activity, size, str).a(new a()).a(agVar.a).a().a();
                    return;
                }
                if (i) {
                    b.a(g, h, 512);
                }
                agVar.c = new ArrayList(h);
                i = false;
                if (!AppodealSettings.g) {
                    if (agVar.c.isEmpty()) {
                        ae.a(true);
                        return;
                    }
                    if (Appodeal.e != null) {
                        Appodeal.e.a(512);
                    }
                    a(size);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            ae.a(true);
        }
    }

    static void a(int i, Double d) {
        ag agVar = (ag) l.get(i);
        Double valueOf = Double.valueOf(0.0d);
        String str = "";
        if (AppodealSettings.g) {
            valueOf = Double.valueOf(5.0E-5d);
        } else if (h.size() > 0) {
            try {
                str = agVar.m.getString("id");
                valueOf = d;
            } catch (Throwable e) {
                Appodeal.a(e);
                valueOf = d;
            }
        }
        com.appodeal.ads.d.i iVar = new com.appodeal.ads.d.i(Appodeal.b, 5, i, ((ag) l.get(i)).A, new b(), valueOf, str, agVar.n);
    }

    static void a(int i) {
        a(0, false, i);
    }

    public static void a(int i, boolean z, final int i2) {
        try {
            int e;
            ((ag) l.get(i2)).m = (JSONObject) ((ag) l.get(i2)).c.get(i);
            ((ag) l.get(i2)).c.remove(i);
            if (z) {
                ((ag) l.get(i2)).c.clear();
            }
            String string = ((ag) l.get(i2)).m.getString("status");
            final String string2 = ((ag) l.get(i2)).m.getString("id");
            final ac a = a(Appodeal.b, string);
            if (a == null || !(a.f() instanceof com.appodeal.ads.native_ad.m)) {
                e = ((ag) l.get(i2)).e();
            } else {
                e = -2;
            }
            if (((ag) l.get(i2)).t && (((ag) l.get(i2)).m.optBoolean("offer") || (a != null && (a.f() instanceof com.appodeal.ads.native_ad.c)))) {
                ae.a(i2, e, a);
            } else if (a == null) {
                ae.a(i2, e);
            } else if (a.f() == null || ((AppodealSettings.a && !a.f().b()) || ((VERSION.SDK_INT > 22 && !a.d()) || (!com.appodeal.ads.utils.c.a(Appodeal.b) && a.e())))) {
                ae.a(i2, e, a);
            } else {
                ((ag) l.get(i2)).d.add(((ag) l.get(i2)).m.getString("id"));
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        int i;
                        try {
                            if (!(Appodeal.e == null || ((ag) Native.l.get(i2)).t)) {
                                Appodeal.e.a(512, a.a(), string2);
                            }
                            a.f().a(Appodeal.b, i2, e, Native.m);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            ae.a(i2, e, a);
                        }
                        Handler handler = new Handler();
                        Runnable anonymousClass1 = new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                ae.a(i2, e, a);
                            }
                        };
                        long round = Math.round(7000.0d * Math.sqrt((double) Math.abs((Native.m * 2) - 1)));
                        if (Native.A == NativeAdType.Video) {
                            i = 3;
                        } else {
                            i = 1;
                        }
                        handler.postDelayed(anonymousClass1, round * ((long) i));
                    }
                });
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
            ae.a(i2, ((ag) l.get(i2)).e());
        }
    }

    public static ImageView b(Context context) {
        ImageView imageView = new ImageView(context);
        int round = Math.round(((float) 16) * an.i(context));
        imageView.setLayoutParams(new LayoutParams(round, round));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAMAAAD04JH5AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AEXCTIRoi+4uAAAAB1pVFh0Q29tbWVudAAAAAAAQ3JlYXRlZCB3aXRoIEdJTVBkLmUHAAAANlBMVEUAAAAbsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM0bsM3///+RYoH0AAAAEHRSTlMAECAwQFBgcICPn6+/z9/vIxqCigAAAAFiS0dEEeK1PboAAALmSURBVHja7ZrdtqMgDIVBUFER8v5POxen02oPQf4SZq1h33a1fJYdIkmEGBoaGhoaGvo3JdfDWmvmXuuvHn7kt6nH+jt85I1kX9/ATX5lXn+Cb7mFFWCD37KaEcBBSHbqtwMv7UwIGlDxBITBAcCbzgAsAREHADh1ZwAAqzoDEAdECgBpQHwBHMZsjjUgbgD+x3GLZwyIG8DfRCiDG+M0NYC7nNAHMCWpK8B+O6NPnoC4AnwZbQkmyk2yAQhpfDAgJBeAENNOniHiAEJoSxwQTwCYFZoFxDMAYoVWAZEAgFkBdskFgFmhRUAkAmBW8AsbAGYFN3MBoFaoC4gHgMlYu30WUEErwDFRASy/stTsGgdEFGB+Z6DrN3zTgIgCfJ72+h/LsBUKAyIGoH6/K0WtUJSkYgAa/wixQkFAFAJgVsi/10c98FkjcDtCrJCbpKIA7w9t8LuIFfICIn4OvB7yxH4RsULO8fxwEi4ngIs90Rq2wkaSC8I5agubUXIBCDEFrXDyAQixVuxCHEAaB3A+W2oJEUz1APIM3NmCCu2CqQd4HzVzySbY+qtZuqMWEoBLLnj6mZ1kC9IBNI0JkwGUL/4DWgCECzq7YALAbo6CCQB5L0nv+9QBLPXtjhoAZPm86kU5ALZ85qtxKUC4ildQ0i0D0C3eBssBkOXLijb5AMi7cGmnLxcAqRGUFwnyALDlK8okOQDo8jWFonQAaaCZ9QsAkJxT305KvR17qoZaIgBdS7ECoE29uhigVdOgEMA361kUAbTs2pQANO1b5QM07tyZyIVac/QuTaSmoDjGGW4HvEZLtXT96xvAqxqmtNb3YjXhjNc9xZ2zUKsDAHDqfusmm3JDJyj8dH3/opviwEc4Xrc7ZYxZCYdIcAAvWBQZYvnvAY7eAExDjctDEJBLd14fmaj0jHOtjnxMJbvGufOOVyve8cGADvp5sbjk2Wum+qvW6XYtuklrLcXQ0NDQ0NDQEIf+APO1za6n5oaAAAAAAElFTkSuQmCC", 0);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
        return imageView;
    }

    public static void a(Context context, final String str, final Class cls, boolean z, final String... strArr) {
        if (!u.contains(str)) {
            if (z) {
                com.appodeal.ads.utils.i.a(context, String.format("%s.dex", new Object[]{str}), strArr[0], new Runnable() {
                    public void run() {
                        if (!Native.u.contains(str)) {
                            try {
                                ac a = Native.b(cls, str, strArr);
                                if (a != null) {
                                    Native.a.add(a);
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
                ac b = b(cls, str, strArr);
                if (b != null) {
                    a.add(b);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private static ac b(Class<?> cls, String str, String[] strArr) {
        try {
            return (ac) cls.getDeclaredMethod("getInstance", new Class[]{String.class, String[].class}).invoke(cls, new Object[]{str, strArr});
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
