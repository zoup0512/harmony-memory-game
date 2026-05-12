package com.appodeal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.d;
import com.appodeal.ads.f.g;
import com.appodeal.ads.native_ad.m;
import com.appodeal.ads.t.a;
import com.appodeal.ads.t.c;
import com.appodeal.ads.utils.v;
import com.cube.memorygames.Games;
import com.facebook.GraphResponse;
import com.facebook.internal.AnalyticsEvents;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class ae {
    public static void a(int i, int i2, ac acVar, int i3) {
        a(i, i2, acVar, i3, false);
    }

    public static void a(int i, int i2, final ac acVar, int i3, boolean z) {
        try {
            ag agVar = (ag) Native.l.get(i);
            if (i == Native.e && !agVar.i) {
                if (agVar.x == -1 || agVar.x == -2 || i2 < agVar.x) {
                    Object obj;
                    Object obj2;
                    Object obj3;
                    agVar.x = i2;
                    if (agVar.t) {
                        agVar.u = agVar.m.getString("id");
                        if (!((acVar.f() instanceof m) || z)) {
                            return;
                        }
                    }
                    agVar.B = i3;
                    if (agVar.m.has("ecpm")) {
                        agVar.b = agVar.m.getDouble("ecpm");
                    }
                    Appodeal.a(String.format(Locale.ENGLISH, "%s onNativeLoaded, eCPM: %.2f", new Object[]{an.a(acVar.a()), Double.valueOf(agVar.b)}));
                    agVar.s = true;
                    if (agVar.m.optBoolean("offer")) {
                        agVar.t = true;
                    }
                    agVar.p = acVar.a();
                    if (!(acVar.f() instanceof m)) {
                        agVar.o = agVar.m.getString("id");
                    } else if (agVar.A.h() != null) {
                        agVar.A.h().a(true);
                    }
                    agVar.q = acVar;
                    agVar.r = false;
                    if (agVar.a() || agVar.c.isEmpty()) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (agVar.t || agVar.y || !agVar.z) {
                        obj2 = null;
                    } else {
                        obj2 = 1;
                    }
                    if (AppodealSettings.i || g.a().b().b(512)) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(512, acVar.a(), true);
                        }
                        a(i);
                        if (!agVar.h) {
                            agVar.h = true;
                            if (Native.d != null) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (Native.d != null) {
                                            Native.d.onNativeLoaded(acVar.f().a());
                                        }
                                    }
                                });
                            }
                        }
                    } else if (obj2 != null) {
                        try {
                            Native.a(i, Double.valueOf(agVar.m.getDouble("ecpm")));
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            agVar.y = true;
                        }
                    } else {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(512, acVar.a(), true);
                        }
                        a(i, agVar.A.h());
                        if (!agVar.h) {
                            agVar.h = true;
                            if (Native.d != null) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (Native.d != null) {
                                            Native.d.onNativeLoaded(acVar.f().a());
                                        }
                                    }
                                });
                            }
                        }
                    }
                    if (obj != null) {
                        if (agVar.t) {
                            obj3 = 1;
                            if (obj3 != null) {
                                Native.a(i);
                            }
                            Native.k = 5000;
                        }
                    }
                    obj3 = null;
                    if (obj3 != null) {
                        Native.a(i);
                    }
                    Native.k = 5000;
                }
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
            a();
        }
    }

    static void a() {
        a(false);
    }

    static void a(boolean z) {
        try {
            Appodeal.a("onNativeFailedToLoad");
            if (z && !Native.l.isEmpty()) {
                ag agVar = (ag) Native.l.get(Native.l.size() - 1);
                agVar.s = false;
                agVar.r = false;
            }
            if (Native.j) {
                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r5 = this;
                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        r0 = com.appodeal.ads.Native.l;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x0059 }
                        if (r0 != 0) goto L_0x0053;
                    L_0x000b:
                        r0 = com.appodeal.ads.Native.l;	 Catch:{ Exception -> 0x0059 }
                        r1 = com.appodeal.ads.Native.l;	 Catch:{ Exception -> 0x0059 }
                        r1 = r1.size();	 Catch:{ Exception -> 0x0059 }
                        r1 = r1 + -1;
                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0059 }
                        r0 = (com.appodeal.ads.ag) r0;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0059 }
                        if (r0 == 0) goto L_0x0026;
                    L_0x0021:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.Native.b(r0);	 Catch:{ Exception -> 0x0059 }
                    L_0x0026:
                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.m();	 Catch:{ Exception -> 0x0059 }
                        r2 = 0;
                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                        if (r0 <= 0) goto L_0x0063;
                    L_0x0038:
                        r0 = com.appodeal.ads.Native.k;	 Catch:{ Exception -> 0x0059 }
                        r0 = (double) r0;	 Catch:{ Exception -> 0x0059 }
                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                        r0 = r0 * r2;
                        r0 = (int) r0;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.Native.k = r0;	 Catch:{ Exception -> 0x0059 }
                    L_0x0044:
                        r0 = com.appodeal.ads.Native.k;	 Catch:{ Exception -> 0x0059 }
                        if (r0 < r4) goto L_0x004d;
                    L_0x0048:
                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        com.appodeal.ads.Native.k = r0;	 Catch:{ Exception -> 0x0059 }
                    L_0x004d:
                        r0 = r0;
                        r0.quit();
                    L_0x0052:
                        return;
                    L_0x0053:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.Native.b(r0);	 Catch:{ Exception -> 0x0059 }
                        goto L_0x0026;
                    L_0x0059:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x006a }
                        r0 = r0;
                        r0.quit();
                        goto L_0x0052;
                    L_0x0063:
                        r0 = com.appodeal.ads.Native.k;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0 * 2;
                        com.appodeal.ads.Native.k = r0;	 Catch:{ Exception -> 0x0059 }
                        goto L_0x0044;
                    L_0x006a:
                        r0 = move-exception;
                        r1 = r0;
                        r1.quit();
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.ae.3.run():void");
                    }
                }, (long) Native.k);
                if (Native.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (Native.d != null) {
                                Native.d.onNativeFailedToLoad();
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void a(int i, int i2) {
        a(i, i2, null);
    }

    public static void a(int i, int i2, ac acVar) {
        try {
            final ag agVar = (ag) Native.l.get(i);
            if (i == Native.e && !agVar.i) {
                if (!AppodealSettings.i && agVar.x == -2 && !agVar.w && i2 == -2) {
                    h hVar = null;
                    if (agVar.A != null) {
                        hVar = agVar.A.h();
                    }
                    a(i, hVar);
                    if (!agVar.h && agVar.q != null) {
                        agVar.h = true;
                        if (Native.d != null) {
                            Appodeal.b.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (Native.d != null) {
                                        Native.d.onNativeLoaded(agVar.q.f().a());
                                    }
                                }
                            });
                        }
                    }
                } else if ((agVar.x == -1 || i2 < agVar.x) && i2 != -2) {
                    agVar.x = i2;
                    boolean a = agVar.a();
                    if (!agVar.t) {
                        if (acVar == null) {
                            Appodeal.a("onNativeFailedToLoad");
                        } else {
                            Appodeal.a(String.format(Locale.ENGLISH, "%s onNativeFailedToLoad, eCPM: %.2f", new Object[]{an.a(acVar.a()), Double.valueOf(agVar.m.optDouble("ecpm", 0.0d))}));
                            if (Appodeal.e != null) {
                                Appodeal.e.a(512, acVar.a(), false);
                            }
                        }
                        agVar.s = false;
                        if (agVar.c.isEmpty()) {
                            agVar.r = false;
                            a(i);
                            if (!a && Native.j) {
                                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                                handlerThread.start();
                                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                                    /* JADX WARNING: inconsistent code. */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void run() {
                                        /*
                                        r5 = this;
                                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        r0 = com.appodeal.ads.Native.l;	 Catch:{ Exception -> 0x0052 }
                                        r1 = com.appodeal.ads.Native.l;	 Catch:{ Exception -> 0x0052 }
                                        r1 = r1.size();	 Catch:{ Exception -> 0x0052 }
                                        r1 = r1 + -1;
                                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0052 }
                                        r0 = (com.appodeal.ads.ag) r0;	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0052 }
                                        if (r0 == 0) goto L_0x001e;
                                    L_0x0019:
                                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0052 }
                                        com.appodeal.ads.Native.b(r0);	 Catch:{ Exception -> 0x0052 }
                                    L_0x001e:
                                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.m();	 Catch:{ Exception -> 0x0052 }
                                        r2 = 0;
                                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                                        if (r0 <= 0) goto L_0x004b;
                                    L_0x0030:
                                        r0 = com.appodeal.ads.Native.k;	 Catch:{ Exception -> 0x0052 }
                                        r0 = (double) r0;	 Catch:{ Exception -> 0x0052 }
                                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                                        r0 = r0 * r2;
                                        r0 = (int) r0;	 Catch:{ Exception -> 0x0052 }
                                        com.appodeal.ads.Native.k = r0;	 Catch:{ Exception -> 0x0052 }
                                    L_0x003c:
                                        r0 = com.appodeal.ads.Native.k;	 Catch:{ Exception -> 0x0052 }
                                        if (r0 < r4) goto L_0x0045;
                                    L_0x0040:
                                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        com.appodeal.ads.Native.k = r0;	 Catch:{ Exception -> 0x0052 }
                                    L_0x0045:
                                        r0 = r0;
                                        r0.quit();
                                    L_0x004a:
                                        return;
                                    L_0x004b:
                                        r0 = com.appodeal.ads.Native.k;	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0 * 2;
                                        com.appodeal.ads.Native.k = r0;	 Catch:{ Exception -> 0x0052 }
                                        goto L_0x003c;
                                    L_0x0052:
                                        r0 = move-exception;
                                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x005c }
                                        r0 = r0;
                                        r0.quit();
                                        goto L_0x004a;
                                    L_0x005c:
                                        r0 = move-exception;
                                        r1 = r0;
                                        r1.quit();
                                        throw r0;
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.ae.6.run():void");
                                    }
                                }, (long) Native.k);
                            }
                            if (Native.d != null) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (Native.d != null) {
                                            Native.d.onNativeFailedToLoad();
                                        }
                                    }
                                });
                            }
                        } else if (!a) {
                            Native.a(i);
                        }
                    } else if (agVar.c.isEmpty()) {
                        agVar.u = Games.SMART_PROMO_GAME_ID;
                    } else if (!a) {
                        Native.a(i);
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            a(true);
        }
    }

    public static void a(int i, ac acVar, final NativeAd nativeAd) {
        try {
            ag agVar = (ag) Native.l.get(i);
            if (!agVar.e.contains(Integer.valueOf(((ab) nativeAd).m()))) {
                agVar.i = true;
                agVar.e.add(Integer.valueOf(((ab) nativeAd).m()));
                Appodeal.a(String.format(Locale.ENGLISH, "%s onNativeShown, eCPM: %.2f", new Object[]{an.a(acVar.a()), Double.valueOf(agVar.b)}));
                h hVar = null;
                if (!(AppodealSettings.i || agVar.A == null)) {
                    hVar = agVar.A.h();
                    if (!agVar.w) {
                        a(i, hVar);
                    }
                }
                Native.p++;
                new c(Appodeal.b, i, "show").a(d.a()).a(new v()).a(agVar.o).b(agVar.n).c(agVar.u).a(hVar).a(agVar.f()).d(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a(agVar.a).a(g.a().b().m()).a().a();
                if (Native.y != null) {
                    Native.y.d(agVar.p);
                    if (hVar == null || !hVar.f()) {
                        Native.y.a(agVar.o);
                    } else {
                        Native.y.a(hVar.e());
                    }
                    String f = ((ab) nativeAd).f();
                    if (f != null) {
                        Native.y.c(f);
                    }
                    Native.y.a(System.currentTimeMillis());
                }
                if (Native.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (Native.d != null) {
                                Native.d.onNativeShown(nativeAd);
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void b(int i, ac acVar, NativeAd nativeAd) {
        try {
            ag agVar = (ag) Native.l.get(i);
            if (!agVar.g.contains(Integer.valueOf(((ab) nativeAd).m()))) {
                agVar.g.add(Integer.valueOf(((ab) nativeAd).m()));
                agVar.j = true;
                ((ab) nativeAd).b();
                h hVar = null;
                if (!(AppodealSettings.i || !(acVar.f() instanceof m) || agVar.A == null)) {
                    hVar = agVar.A.h();
                }
                new c(Appodeal.b, i, "finish").a(d.a()).a(new v()).a(agVar.o).b(agVar.n).c(agVar.u).a(hVar).a(agVar.f()).d(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a(agVar.a).a().a();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void c(int i, ac acVar, NativeAd nativeAd) {
        a(i, acVar, nativeAd, null);
    }

    public static void a(int i, ac acVar, final NativeAd nativeAd, a aVar) {
        try {
            ag agVar = (ag) Native.l.get(i);
            if (!agVar.e.contains(Integer.valueOf(((ab) nativeAd).m()))) {
                a(i, acVar, nativeAd);
            }
            if (!agVar.g.contains(Integer.valueOf(((ab) nativeAd).m())) && Native.v > 0) {
                b(i, acVar, nativeAd);
            }
            if (!agVar.f.contains(Integer.valueOf(((ab) nativeAd).m()))) {
                agVar.k = true;
                agVar.f.add(Integer.valueOf(((ab) nativeAd).m()));
                Appodeal.a(String.format(Locale.ENGLISH, "%s onNativeClicked, eCPM: %.2f", new Object[]{an.a(acVar.a()), Double.valueOf(agVar.b)}));
                if (Native.y != null) {
                    Native.y.b(System.currentTimeMillis());
                }
                h hVar = null;
                if (!(AppodealSettings.i || !(acVar.f() instanceof m) || agVar.A == null)) {
                    hVar = agVar.A.h();
                }
                Native.q++;
                new c(Appodeal.b, i, "click").a(d.a()).a(aVar).a(agVar.o).b(agVar.n).c(agVar.u).a(hVar).a(agVar.f()).d(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a(agVar.a).a().a();
                if (Native.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (Native.d != null) {
                                Native.d.onNativeClicked(nativeAd);
                            }
                        }
                    });
                }
            } else if (aVar != null) {
                aVar.a(i);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void a(int i, h hVar) {
        try {
            ag agVar = (ag) Native.l.get(i);
            if (!agVar.d.isEmpty() && !agVar.w) {
                if (Appodeal.e != null) {
                    Appodeal.e.a(512, agVar.s);
                }
                if (agVar.z) {
                    agVar.y = false;
                }
                agVar.w = true;
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                if (!(hVar == null || hVar.f() || !agVar.d.remove(agVar.o))) {
                    agVar.d.add(agVar.o);
                }
                Iterator it = agVar.d.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
                try {
                    jSONObject.put("requests", jSONArray);
                    jSONObject.put(GraphResponse.SUCCESS_KEY, agVar.s);
                    String jSONObject2 = jSONObject.toString();
                    if (hVar != null) {
                        hVar.b(jSONObject);
                    }
                    new c(Appodeal.b, i, "stats").a(jSONObject2).b(agVar.n).a(hVar).a(agVar.a).a(agVar.B).a().a();
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
        }
    }

    private static void a(int i) {
        a(i, null);
    }
}
