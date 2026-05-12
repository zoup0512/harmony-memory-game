package com.appodeal.ads;

import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.g;
import com.appodeal.ads.g.f;
import com.appodeal.ads.g.r;
import com.appodeal.ads.t.c;
import com.appodeal.ads.utils.a;
import com.appodeal.ads.utils.v;
import com.cube.memorygames.Games;
import com.facebook.GraphResponse;
import com.facebook.internal.AnalyticsEvents;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class aj {
    public static void a(int i, int i2, ap apVar) {
        a(i, i2, apVar, false);
    }

    public static void a(int i, int i2, ap apVar, boolean z) {
        try {
            ar arVar = (ar) ah.m.get(i);
            if (i == ah.f && !arVar.g) {
                if (arVar.x == -1 || arVar.x == -2 || i2 < arVar.x) {
                    boolean z2;
                    boolean z3;
                    boolean z4;
                    boolean z5;
                    arVar.x = i2;
                    if (arVar.s) {
                        arVar.t = arVar.l.getString("id");
                        if (!((apVar.g() instanceof r) || z || (arVar.d != null && arVar.d == arVar.l))) {
                            return;
                        }
                    }
                    if (arVar.l.has("ecpm")) {
                        arVar.b = arVar.l.getDouble("ecpm");
                    }
                    Appodeal.a(String.format(Locale.ENGLISH, "%s onSkippableVideoLoaded, eCPM: %.2f", new Object[]{an.a(apVar.a()), Double.valueOf(arVar.b)}));
                    arVar.q = false;
                    arVar.r = true;
                    if (arVar.l.optBoolean("offer")) {
                        arVar.s = true;
                    }
                    arVar.o = apVar.a();
                    if (!(apVar.g() instanceof r)) {
                        arVar.n = arVar.l.getString("id");
                    } else if (!(arVar.A == null || arVar.A.h() == null)) {
                        arVar.A.h().a(true);
                    }
                    arVar.p = apVar;
                    boolean e = arVar.e();
                    if (arVar.a() || arVar.c.isEmpty() || e) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    if (arVar.c.size() <= 0 || arVar.c.get(0) != arVar.d) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (z2 && z3) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4 || arVar.s || arVar.y || !arVar.z) {
                        z5 = false;
                    } else {
                        z5 = true;
                    }
                    if (AppodealSettings.i || g.a().b().b(2)) {
                        if (arVar.d == null || arVar.d == arVar.l || e) {
                            if (Appodeal.e != null) {
                                Appodeal.e.a(2, apVar.a(), true);
                            }
                            a(i);
                        }
                    } else if (z5) {
                        try {
                            ah.a(i, Double.valueOf(arVar.l.getDouble("ecpm")));
                        } catch (Throwable e2) {
                            Appodeal.a(e2);
                            arVar.y = true;
                        }
                    } else if (!z4) {
                        if (Appodeal.e != null) {
                            Appodeal.e.a(2, apVar.a(), true);
                        }
                        a(i, arVar.A.h());
                    }
                    if (!arVar.f) {
                        arVar.f = true;
                        if (ah.e != null) {
                            Appodeal.b.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (ah.e != null) {
                                        ah.e.onSkippableVideoLoaded();
                                    }
                                }
                            });
                        }
                    }
                    if (Appodeal.c != null) {
                        Appodeal.c.finish();
                        Appodeal.c.overridePendingTransition(0, 0);
                        Appodeal.c = null;
                    }
                    boolean z6 = z2 && (arVar.s || z3);
                    if (z6) {
                        ah.a(i);
                    }
                    if (e) {
                        ah.a(Appodeal.b, ah.q);
                    }
                    ah.l = 5000;
                }
            }
        } catch (Throwable e3) {
            Appodeal.a(e3);
            a(true);
        }
    }

    public static void a() {
        a(false);
    }

    public static void a(boolean z) {
        try {
            Appodeal.a("onSkippableVideoFailedToLoad");
            if (z && !ah.m.isEmpty()) {
                ar arVar = (ar) ah.m.get(ah.m.size() - 1);
                arVar.r = false;
                arVar.q = false;
            }
            if (ah.k) {
                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r5 = this;
                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        r0 = com.appodeal.ads.ah.m;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x0059 }
                        if (r0 != 0) goto L_0x0053;
                    L_0x000b:
                        r0 = com.appodeal.ads.ah.m;	 Catch:{ Exception -> 0x0059 }
                        r1 = com.appodeal.ads.ah.m;	 Catch:{ Exception -> 0x0059 }
                        r1 = r1.size();	 Catch:{ Exception -> 0x0059 }
                        r1 = r1 + -1;
                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0059 }
                        r0 = (com.appodeal.ads.ar) r0;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0059 }
                        if (r0 == 0) goto L_0x0026;
                    L_0x0021:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.ah.b(r0);	 Catch:{ Exception -> 0x0059 }
                    L_0x0026:
                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.l();	 Catch:{ Exception -> 0x0059 }
                        r2 = 0;
                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                        if (r0 <= 0) goto L_0x0063;
                    L_0x0038:
                        r0 = com.appodeal.ads.ah.l;	 Catch:{ Exception -> 0x0059 }
                        r0 = (double) r0;	 Catch:{ Exception -> 0x0059 }
                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                        r0 = r0 * r2;
                        r0 = (int) r0;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.ah.l = r0;	 Catch:{ Exception -> 0x0059 }
                    L_0x0044:
                        r0 = com.appodeal.ads.ah.l;	 Catch:{ Exception -> 0x0059 }
                        if (r0 < r4) goto L_0x004d;
                    L_0x0048:
                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        com.appodeal.ads.ah.l = r0;	 Catch:{ Exception -> 0x0059 }
                    L_0x004d:
                        r0 = r0;
                        r0.quit();
                    L_0x0052:
                        return;
                    L_0x0053:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.ah.b(r0);	 Catch:{ Exception -> 0x0059 }
                        goto L_0x0026;
                    L_0x0059:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x006a }
                        r0 = r0;
                        r0.quit();
                        goto L_0x0052;
                    L_0x0063:
                        r0 = com.appodeal.ads.ah.l;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0 * 2;
                        com.appodeal.ads.ah.l = r0;	 Catch:{ Exception -> 0x0059 }
                        goto L_0x0044;
                    L_0x006a:
                        r0 = move-exception;
                        r1 = r0;
                        r1.quit();
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.aj.2.run():void");
                    }
                }, (long) ah.l);
            }
            if (ah.e != null) {
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        if (ah.e != null) {
                            ah.e.onSkippableVideoFailedToLoad();
                        }
                    }
                });
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void a(int i, int i2) {
        b(i, i2, null);
    }

    public static void b(int i, int i2, ap apVar) {
        try {
            ar arVar = (ar) ah.m.get(i);
            if (i == ah.f && !arVar.g) {
                if (!AppodealSettings.i && arVar.x == -2 && !arVar.w && i2 == -2) {
                    a(i, arVar.A.h());
                } else if ((arVar.x == -1 || i2 < arVar.x) && i2 != -2) {
                    arVar.x = i2;
                    if (arVar.r && arVar.d != null && arVar.d.optString("status").equals(apVar.a())) {
                        a(i);
                        if (!arVar.s) {
                            return;
                        }
                    }
                    boolean a = arVar.a();
                    if (!arVar.s) {
                        if (apVar == null) {
                            Appodeal.a("onSkippableVideoFailedToLoad");
                        } else {
                            Appodeal.a(String.format(Locale.ENGLISH, "%s onSkippableVideoFailedToLoad, eCPM: %.2f", new Object[]{an.a(apVar.a()), Double.valueOf(arVar.l.optDouble("ecpm", 0.0d))}));
                            if (Appodeal.e != null) {
                                Appodeal.e.a(2, apVar.a(), false);
                            }
                        }
                        arVar.r = false;
                        if (arVar.c.isEmpty()) {
                            arVar.q = false;
                            if (arVar.d == null) {
                                a(i);
                            }
                            if (!a && ah.k) {
                                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                                handlerThread.start();
                                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                                    /* JADX WARNING: inconsistent code. */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void run() {
                                        /*
                                        r5 = this;
                                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        r0 = com.appodeal.ads.ah.m;	 Catch:{ Exception -> 0x0052 }
                                        r1 = com.appodeal.ads.ah.m;	 Catch:{ Exception -> 0x0052 }
                                        r1 = r1.size();	 Catch:{ Exception -> 0x0052 }
                                        r1 = r1 + -1;
                                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0052 }
                                        r0 = (com.appodeal.ads.ar) r0;	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0052 }
                                        if (r0 == 0) goto L_0x001e;
                                    L_0x0019:
                                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0052 }
                                        com.appodeal.ads.ah.b(r0);	 Catch:{ Exception -> 0x0052 }
                                    L_0x001e:
                                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.l();	 Catch:{ Exception -> 0x0052 }
                                        r2 = 0;
                                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                                        if (r0 <= 0) goto L_0x004b;
                                    L_0x0030:
                                        r0 = com.appodeal.ads.ah.l;	 Catch:{ Exception -> 0x0052 }
                                        r0 = (double) r0;	 Catch:{ Exception -> 0x0052 }
                                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                                        r0 = r0 * r2;
                                        r0 = (int) r0;	 Catch:{ Exception -> 0x0052 }
                                        com.appodeal.ads.ah.l = r0;	 Catch:{ Exception -> 0x0052 }
                                    L_0x003c:
                                        r0 = com.appodeal.ads.ah.l;	 Catch:{ Exception -> 0x0052 }
                                        if (r0 < r4) goto L_0x0045;
                                    L_0x0040:
                                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        com.appodeal.ads.ah.l = r0;	 Catch:{ Exception -> 0x0052 }
                                    L_0x0045:
                                        r0 = r0;
                                        r0.quit();
                                    L_0x004a:
                                        return;
                                    L_0x004b:
                                        r0 = com.appodeal.ads.ah.l;	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0 * 2;
                                        com.appodeal.ads.ah.l = r0;	 Catch:{ Exception -> 0x0052 }
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
                                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.aj.4.run():void");
                                    }
                                }, (long) ah.l);
                            }
                            if (Appodeal.c != null) {
                                Appodeal.c.finish();
                                Appodeal.c.overridePendingTransition(0, 0);
                                Appodeal.c = null;
                            }
                            if (ah.e != null) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (ah.e != null) {
                                            ah.e.onSkippableVideoFailedToLoad();
                                        }
                                    }
                                });
                            }
                        } else if (!a) {
                            ah.a(i);
                        }
                    } else if (arVar.c.isEmpty()) {
                        arVar.t = Games.SMART_PROMO_GAME_ID;
                    } else if (!a) {
                        ah.a(i);
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            a(true);
        }
    }

    public static void a(int i, ap apVar) {
        try {
            ar arVar = (ar) ah.m.get(i);
            if (!arVar.g) {
                h hVar;
                arVar.g = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onSkippableVideoShown, eCPM: %.2f", new Object[]{an.a(apVar.a()), Double.valueOf(arVar.b)}));
                if (AppodealSettings.i || arVar.A == null) {
                    hVar = null;
                } else {
                    h h = arVar.A.h();
                    if (!arVar.w) {
                        a(i, h);
                    }
                    hVar = h;
                }
                apVar.g().b(true);
                arVar.r = false;
                ah.n++;
                new c(Appodeal.b, i, "show").a(ah.q).a(new v()).a(arVar.n).b(arVar.m).c(arVar.t).a(hVar).a(arVar.g()).d(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO).a(arVar.a).a(g.a().b().l()).a().a();
                if (ah.x != null) {
                    ah.x.d(arVar.o);
                    if (hVar == null || !hVar.f()) {
                        ah.x.a(arVar.n);
                    } else {
                        ah.x.a(hVar.e());
                    }
                    String a = apVar.g().a();
                    if (a != null) {
                        ah.x.c(a);
                    }
                    ah.x.a(System.currentTimeMillis());
                }
                if (ah.s) {
                    String a2 = apVar.g().a();
                    if (a2 != null) {
                        if (hVar == null || !hVar.f()) {
                            new a(Appodeal.b, arVar.m, arVar.n, arVar.o, a2, 2).b();
                        } else {
                            new a(Appodeal.b, arVar.m, hVar.e(), arVar.o, a2, 2).b();
                        }
                    }
                }
                if (!arVar.a() && ah.k) {
                    ar a3 = ah.a();
                    if (a3 == null || a3.b()) {
                        ah.b(Appodeal.b);
                    }
                }
                if (ah.e != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (ah.e != null) {
                                ah.e.onSkippableVideoShown();
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void b(int i, ap apVar) {
        try {
            ar arVar = (ar) ah.m.get(i);
            if (!arVar.i) {
                arVar.i = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onSkippableVideoFinished, eCPM: %.2f", new Object[]{an.a(apVar.a()), Double.valueOf(arVar.b)}));
                h hVar = null;
                if (!(AppodealSettings.i || !(apVar.g() instanceof r) || arVar.A == null)) {
                    hVar = arVar.A.h();
                }
                ah.o++;
                new c(Appodeal.b, i, "finish").a(ah.q).a(arVar.n).b(arVar.m).c(arVar.t).a(hVar).a(arVar.g()).d(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO).a(arVar.a).a().a();
                if (ah.e != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (ah.e != null) {
                                ah.e.onSkippableVideoFinished();
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void c(int i, ap apVar) {
        a(i, apVar, null);
    }

    public static void a(int i, ap apVar, t.a aVar) {
        try {
            ar arVar = (ar) ah.m.get(i);
            if (!arVar.h) {
                arVar.h = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onSkippableVideoClicked, eCPM: %.2f", new Object[]{an.a(apVar.a()), Double.valueOf(arVar.b)}));
                if (ah.x != null) {
                    ah.x.b(System.currentTimeMillis());
                }
                h hVar = null;
                if (!(AppodealSettings.i || !(apVar.g() instanceof r) || arVar.A == null)) {
                    hVar = arVar.A.h();
                }
                ah.p++;
                new c(Appodeal.b, i, "click").a(ah.q).a(aVar).a(arVar.n).b(arVar.m).c(arVar.t).a(hVar).a(arVar.g()).d(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO).a(arVar.a).a().a();
            } else if (aVar != null) {
                aVar.a(i);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void d(int i, ap apVar) {
        if (i != -1) {
            try {
                if (!((ar) ah.m.get(i)).j && apVar != f.g()) {
                    final ar arVar = (ar) ah.m.get(i);
                    arVar.j = true;
                    AudioManager audioManager = (AudioManager) Appodeal.b.getSystemService("audio");
                    if (AppodealSettings.e && audioManager.getStreamVolume(3) == 0 && AppodealSettings.f != -1) {
                        audioManager.setStreamVolume(3, AppodealSettings.f, 0);
                    }
                    apVar.g().b(false);
                    if (i + 1 < ah.m.size() && ((ar) ah.m.get(i + 1)).d != null) {
                        ((ar) ah.m.get(i + 1)).c.remove(((ar) ah.m.get(i + 1)).c.size() - 1);
                        ((ar) ah.m.get(i + 1)).c.add(0, ((ar) ah.m.get(i + 1)).d);
                        if (((ar) ah.m.get(i + 1)).c.size() == 1 || ((ar) ah.m.get(i + 1)).r) {
                            ah.a(i + 1);
                        }
                    }
                    Appodeal.a(String.format(Locale.ENGLISH, "%s onSkippableVideoClosed, eCPM: %.2f", new Object[]{an.a(apVar.a()), Double.valueOf(arVar.b)}));
                    if (ah.e != null) {
                        Appodeal.b.runOnUiThread(new Runnable() {
                            public void run() {
                                if (ah.e != null) {
                                    ah.e.onSkippableVideoClosed(arVar.i);
                                }
                            }
                        });
                    }
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private static void a(int i, h hVar) {
        try {
            ar arVar = (ar) ah.m.get(i);
            if (!arVar.e.isEmpty()) {
                if (!arVar.w || hVar != null) {
                    if (Appodeal.e != null) {
                        Appodeal.e.a(2, arVar.r);
                    }
                    if (arVar.z) {
                        arVar.y = false;
                    }
                    arVar.w = true;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    if (arVar.e.remove(arVar.n)) {
                        arVar.e.add(arVar.n);
                    }
                    if (hVar != null && hVar.f() && arVar.e.remove(hVar.e())) {
                        arVar.e.add(hVar.e());
                    }
                    Iterator it = arVar.e.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                    try {
                        jSONObject.put("requests", jSONArray);
                        jSONObject.put(GraphResponse.SUCCESS_KEY, arVar.r);
                        String jSONObject2 = jSONObject.toString();
                        if (hVar != null) {
                            hVar.b(jSONObject);
                        }
                        new c(Appodeal.b, i, "stats").a(jSONObject2).b(arVar.m).a(hVar).a(arVar.a).a().a();
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
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
