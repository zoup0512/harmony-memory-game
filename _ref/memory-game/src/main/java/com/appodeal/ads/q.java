package com.appodeal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.b.aa;
import com.appodeal.ads.b.m;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.g;
import com.appodeal.ads.t.c;
import com.appodeal.ads.utils.a;
import com.appodeal.ads.utils.v;
import com.cube.memorygames.Games;
import com.facebook.GraphResponse;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class q {
    public static void a(int i, int i2, o oVar) {
        a(i, i2, oVar, false, false);
    }

    public static void a(int i, int i2, o oVar, boolean z) {
        a(i, i2, oVar, z, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r10, int r11, com.appodeal.ads.o r12, final boolean r13, boolean r14) {
        /*
        r3 = 0;
        r2 = 1;
        r0 = com.appodeal.ads.n.p;	 Catch:{ Exception -> 0x0156 }
        r0 = r0.get(r10);	 Catch:{ Exception -> 0x0156 }
        r0 = (com.appodeal.ads.s) r0;	 Catch:{ Exception -> 0x0156 }
        r1 = com.appodeal.ads.n.f;	 Catch:{ Exception -> 0x0156 }
        if (r10 != r1) goto L_0x0012;
    L_0x000e:
        r1 = r0.h;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r1 = r0.z;	 Catch:{ Exception -> 0x0156 }
        r4 = -1;
        if (r1 == r4) goto L_0x0021;
    L_0x0018:
        r1 = r0.z;	 Catch:{ Exception -> 0x0156 }
        r4 = -2;
        if (r1 == r4) goto L_0x0021;
    L_0x001d:
        r1 = r0.z;	 Catch:{ Exception -> 0x0156 }
        if (r11 >= r1) goto L_0x0012;
    L_0x0021:
        r0.z = r11;	 Catch:{ Exception -> 0x0156 }
        r1 = r0.u;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x003b;
    L_0x0027:
        r1 = r0.m;	 Catch:{ Exception -> 0x0156 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Exception -> 0x0156 }
        r0.v = r1;	 Catch:{ Exception -> 0x0156 }
        r1 = r12.g();	 Catch:{ Exception -> 0x0156 }
        r1 = r1 instanceof com.appodeal.ads.b.aa;	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x003b;
    L_0x0039:
        if (r14 == 0) goto L_0x0012;
    L_0x003b:
        r1 = r0.m;	 Catch:{ Exception -> 0x0156 }
        r4 = "ecpm";
        r1 = r1.has(r4);	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x004f;
    L_0x0045:
        r1 = r0.m;	 Catch:{ Exception -> 0x0156 }
        r4 = "ecpm";
        r4 = r1.getDouble(r4);	 Catch:{ Exception -> 0x0156 }
        r0.b = r4;	 Catch:{ Exception -> 0x0156 }
    L_0x004f:
        r1 = java.util.Locale.ENGLISH;	 Catch:{ Exception -> 0x0156 }
        r4 = "%s onInterstitialLoaded, eCPM: %.2f";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0156 }
        r6 = 0;
        r7 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r7 = com.appodeal.ads.an.a(r7);	 Catch:{ Exception -> 0x0156 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0156 }
        r6 = 1;
        r8 = r0.b;	 Catch:{ Exception -> 0x0156 }
        r7 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0156 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0156 }
        r1 = java.lang.String.format(r1, r4, r5);	 Catch:{ Exception -> 0x0156 }
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0156 }
        if (r13 == 0) goto L_0x015f;
    L_0x0073:
        r1 = 1;
        r0.t = r1;	 Catch:{ Exception -> 0x0156 }
    L_0x0076:
        r1 = r0.m;	 Catch:{ Exception -> 0x0156 }
        r4 = "offer";
        r1 = r1.optBoolean(r4);	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0083;
    L_0x0080:
        r1 = 1;
        r0.u = r1;	 Catch:{ Exception -> 0x0156 }
    L_0x0083:
        r1 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r0.p = r1;	 Catch:{ Exception -> 0x0156 }
        r1 = r12.g();	 Catch:{ Exception -> 0x0156 }
        r1 = r1 instanceof com.appodeal.ads.b.aa;	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x0164;
    L_0x0091:
        r1 = r0.m;	 Catch:{ Exception -> 0x0156 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Exception -> 0x0156 }
        r0.o = r1;	 Catch:{ Exception -> 0x0156 }
    L_0x009b:
        r0.q = r12;	 Catch:{ Exception -> 0x0156 }
        if (r13 == 0) goto L_0x00a5;
    L_0x009f:
        r1 = r0.f();	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x00a8;
    L_0x00a5:
        r1 = 0;
        r0.r = r1;	 Catch:{ Exception -> 0x0156 }
    L_0x00a8:
        r6 = r0.f();	 Catch:{ Exception -> 0x0156 }
        r1 = r0.a();	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x0178;
    L_0x00b2:
        r1 = r0.d;	 Catch:{ Exception -> 0x0156 }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x0178;
    L_0x00ba:
        if (r6 != 0) goto L_0x0178;
    L_0x00bc:
        r5 = r2;
    L_0x00bd:
        if (r5 == 0) goto L_0x017b;
    L_0x00bf:
        if (r13 == 0) goto L_0x017b;
    L_0x00c1:
        r4 = r2;
    L_0x00c2:
        if (r4 != 0) goto L_0x017e;
    L_0x00c4:
        r1 = r0.u;	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x017e;
    L_0x00c8:
        r1 = r0.A;	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x017e;
    L_0x00cc:
        r1 = r0.B;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x017e;
    L_0x00d0:
        r1 = r2;
    L_0x00d1:
        r7 = com.appodeal.ads.AppodealSettings.i;	 Catch:{ Exception -> 0x0156 }
        if (r7 != 0) goto L_0x01b8;
    L_0x00d5:
        r7 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0156 }
        r7 = r7.b();	 Catch:{ Exception -> 0x0156 }
        r8 = 1;
        r7 = r7.b(r8);	 Catch:{ Exception -> 0x0156 }
        if (r7 != 0) goto L_0x01b8;
    L_0x00e4:
        if (r1 == 0) goto L_0x018a;
    L_0x00e6:
        r1 = r0.m;	 Catch:{ Exception -> 0x0181 }
        r4 = "ecpm";
        r8 = r1.getDouble(r4);	 Catch:{ Exception -> 0x0181 }
        r1 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0181 }
        com.appodeal.ads.n.a(r10, r1);	 Catch:{ Exception -> 0x0181 }
    L_0x00f5:
        if (r13 == 0) goto L_0x00fb;
    L_0x00f7:
        r1 = com.appodeal.ads.n.n;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0110;
    L_0x00fb:
        r1 = r0.g;	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x0110;
    L_0x00ff:
        r1 = 1;
        r0.g = r1;	 Catch:{ Exception -> 0x0156 }
        r1 = com.appodeal.ads.n.e;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0110;
    L_0x0106:
        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0156 }
        r4 = new com.appodeal.ads.q$1;	 Catch:{ Exception -> 0x0156 }
        r4.<init>(r13);	 Catch:{ Exception -> 0x0156 }
        r1.runOnUiThread(r4);	 Catch:{ Exception -> 0x0156 }
    L_0x0110:
        r1 = com.appodeal.ads.Appodeal.c;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0123;
    L_0x0114:
        r1 = com.appodeal.ads.Appodeal.c;	 Catch:{ Exception -> 0x0156 }
        r1.finish();	 Catch:{ Exception -> 0x0156 }
        r1 = com.appodeal.ads.Appodeal.c;	 Catch:{ Exception -> 0x0156 }
        r4 = 0;
        r7 = 0;
        r1.overridePendingTransition(r4, r7);	 Catch:{ Exception -> 0x0156 }
        r1 = 0;
        com.appodeal.ads.Appodeal.c = r1;	 Catch:{ Exception -> 0x0156 }
    L_0x0123:
        if (r5 == 0) goto L_0x01e6;
    L_0x0125:
        r1 = r0.u;	 Catch:{ Exception -> 0x0156 }
        if (r1 != 0) goto L_0x012b;
    L_0x0129:
        if (r13 == 0) goto L_0x01e6;
    L_0x012b:
        r1 = r2;
    L_0x012c:
        if (r1 == 0) goto L_0x0131;
    L_0x012e:
        com.appodeal.ads.n.b(r10);	 Catch:{ Exception -> 0x0156 }
    L_0x0131:
        if (r6 == 0) goto L_0x0150;
    L_0x0133:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0149;
    L_0x0137:
        r1 = r0.s;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x01e9;
    L_0x013b:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        r3 = 1;
        r4 = r0.q;	 Catch:{ Exception -> 0x0156 }
        r4 = r4.a();	 Catch:{ Exception -> 0x0156 }
        r0 = r0.s;	 Catch:{ Exception -> 0x0156 }
        r1.a(r3, r4, r0);	 Catch:{ Exception -> 0x0156 }
    L_0x0149:
        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0156 }
        r1 = com.appodeal.ads.n.s;	 Catch:{ Exception -> 0x0156 }
        com.appodeal.ads.n.a(r0, r1);	 Catch:{ Exception -> 0x0156 }
    L_0x0150:
        r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0156 }
        goto L_0x0012;
    L_0x0156:
        r0 = move-exception;
        com.appodeal.ads.Appodeal.a(r0);
        a(r2);
        goto L_0x0012;
    L_0x015f:
        r1 = 1;
        r0.s = r1;	 Catch:{ Exception -> 0x0156 }
        goto L_0x0076;
    L_0x0164:
        r1 = r0.C;	 Catch:{ Exception -> 0x0156 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x009b;
    L_0x016c:
        r1 = r0.C;	 Catch:{ Exception -> 0x0156 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0156 }
        r4 = 1;
        r1.a(r4);	 Catch:{ Exception -> 0x0156 }
        goto L_0x009b;
    L_0x0178:
        r5 = r3;
        goto L_0x00bd;
    L_0x017b:
        r4 = r3;
        goto L_0x00c2;
    L_0x017e:
        r1 = r3;
        goto L_0x00d1;
    L_0x0181:
        r1 = move-exception;
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0156 }
        r1 = 1;
        r0.A = r1;	 Catch:{ Exception -> 0x0156 }
        goto L_0x00f5;
    L_0x018a:
        if (r4 != 0) goto L_0x01a6;
    L_0x018c:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x019b;
    L_0x0190:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        r4 = 1;
        r7 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r8 = 1;
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0156 }
    L_0x019b:
        r1 = r0.C;	 Catch:{ Exception -> 0x0156 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0156 }
        a(r10, r1);	 Catch:{ Exception -> 0x0156 }
        goto L_0x00f5;
    L_0x01a6:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x00f5;
    L_0x01aa:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        r4 = 1;
        r7 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r8 = r0.t;	 Catch:{ Exception -> 0x0156 }
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0156 }
        goto L_0x00f5;
    L_0x01b8:
        if (r13 == 0) goto L_0x01c0;
    L_0x01ba:
        r1 = r0.f();	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x01d4;
    L_0x01c0:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x01cf;
    L_0x01c4:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        r4 = 1;
        r7 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r8 = 1;
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0156 }
    L_0x01cf:
        a(r10);	 Catch:{ Exception -> 0x0156 }
        goto L_0x00f5;
    L_0x01d4:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x00f5;
    L_0x01d8:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        r4 = 1;
        r7 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r8 = r0.t;	 Catch:{ Exception -> 0x0156 }
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0156 }
        goto L_0x00f5;
    L_0x01e6:
        r1 = r3;
        goto L_0x012c;
    L_0x01e9:
        r1 = r0.t;	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x0149;
    L_0x01ed:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0156 }
        r3 = 1;
        r4 = r12.a();	 Catch:{ Exception -> 0x0156 }
        r0 = r0.t;	 Catch:{ Exception -> 0x0156 }
        r1.a(r3, r4, r0);	 Catch:{ Exception -> 0x0156 }
        goto L_0x0149;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.q.a(int, int, com.appodeal.ads.o, boolean, boolean):void");
    }

    public static void a() {
        a(false);
    }

    public static void a(boolean z) {
        try {
            Appodeal.a("onInterstitialFailedToLoad");
            if (z && !n.p.isEmpty()) {
                s sVar = (s) n.p.get(n.p.size() - 1);
                sVar.s = false;
                sVar.t = false;
                sVar.r = false;
            }
            if (n.m) {
                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r5 = this;
                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        r0 = com.appodeal.ads.n.p;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x0059 }
                        if (r0 != 0) goto L_0x0053;
                    L_0x000b:
                        r0 = com.appodeal.ads.n.p;	 Catch:{ Exception -> 0x0059 }
                        r1 = com.appodeal.ads.n.p;	 Catch:{ Exception -> 0x0059 }
                        r1 = r1.size();	 Catch:{ Exception -> 0x0059 }
                        r1 = r1 + -1;
                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0059 }
                        r0 = (com.appodeal.ads.s) r0;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0059 }
                        if (r0 == 0) goto L_0x0026;
                    L_0x0021:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.n.b(r0);	 Catch:{ Exception -> 0x0059 }
                    L_0x0026:
                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0059 }
                        r0 = r0.i();	 Catch:{ Exception -> 0x0059 }
                        r2 = 0;
                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                        if (r0 <= 0) goto L_0x0063;
                    L_0x0038:
                        r0 = com.appodeal.ads.n.o;	 Catch:{ Exception -> 0x0059 }
                        r0 = (double) r0;	 Catch:{ Exception -> 0x0059 }
                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                        r0 = r0 * r2;
                        r0 = (int) r0;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0059 }
                    L_0x0044:
                        r0 = com.appodeal.ads.n.o;	 Catch:{ Exception -> 0x0059 }
                        if (r0 < r4) goto L_0x004d;
                    L_0x0048:
                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0059 }
                    L_0x004d:
                        r0 = r0;
                        r0.quit();
                    L_0x0052:
                        return;
                    L_0x0053:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0059 }
                        com.appodeal.ads.n.b(r0);	 Catch:{ Exception -> 0x0059 }
                        goto L_0x0026;
                    L_0x0059:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x006a }
                        r0 = r0;
                        r0.quit();
                        goto L_0x0052;
                    L_0x0063:
                        r0 = com.appodeal.ads.n.o;	 Catch:{ Exception -> 0x0059 }
                        r0 = r0 * 2;
                        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0059 }
                        goto L_0x0044;
                    L_0x006a:
                        r0 = move-exception;
                        r1 = r0;
                        r1.quit();
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.q.2.run():void");
                    }
                }, (long) n.o);
            }
            if (n.e != null) {
                Appodeal.b.runOnUiThread(new Runnable() {
                    public void run() {
                        if (n.e != null) {
                            n.e.onInterstitialFailedToLoad();
                        }
                    }
                });
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void a(int i, int i2) {
        b(i, i2, null, false);
    }

    public static void b(int i, int i2, o oVar) {
        b(i, i2, oVar, false);
    }

    public static void b(int i, int i2, o oVar, boolean z) {
        try {
            s sVar = (s) n.p.get(i);
            if (i == n.f && !sVar.h) {
                if (!AppodealSettings.i && sVar.z == -2 && !sVar.y && i2 == -2) {
                    a(i, sVar.C.h());
                } else if ((sVar.z == -1 || i2 < sVar.z) && i2 != -2) {
                    sVar.z = i2;
                    boolean a = sVar.a();
                    if (!sVar.u) {
                        if (oVar == null) {
                            Appodeal.a("onInterstitialFailedToLoad");
                        } else {
                            Appodeal.a(String.format(Locale.ENGLISH, "%s onInterstitialFailedToLoad, eCPM: %.2f", new Object[]{an.a(oVar.a()), Double.valueOf(sVar.m.optDouble("ecpm", 0.0d))}));
                            if (Appodeal.e != null) {
                                Appodeal.e.a(1, oVar.a(), false);
                            }
                        }
                        sVar.s = false;
                        if (z) {
                            if (!a) {
                                if (!sVar.c.isEmpty()) {
                                    n.a(i);
                                } else if (!sVar.d.isEmpty()) {
                                    n.b(i);
                                }
                            }
                        } else if (sVar.d.isEmpty()) {
                            sVar.r = false;
                            a(i);
                            if (!a && n.m) {
                                int i3;
                                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                                handlerThread.start();
                                Handler handler = new Handler(handlerThread.getLooper());
                                if (sVar.t) {
                                    i3 = 30000;
                                } else {
                                    i3 = n.o;
                                }
                                handler.postDelayed(new Runnable() {
                                    /* JADX WARNING: inconsistent code. */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void run() {
                                        /*
                                        r5 = this;
                                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        r0 = com.appodeal.ads.n.p;	 Catch:{ Exception -> 0x0052 }
                                        r1 = com.appodeal.ads.n.p;	 Catch:{ Exception -> 0x0052 }
                                        r1 = r1.size();	 Catch:{ Exception -> 0x0052 }
                                        r1 = r1 + -1;
                                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0052 }
                                        r0 = (com.appodeal.ads.s) r0;	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0052 }
                                        if (r0 == 0) goto L_0x001e;
                                    L_0x0019:
                                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0052 }
                                        com.appodeal.ads.n.b(r0);	 Catch:{ Exception -> 0x0052 }
                                    L_0x001e:
                                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0.i();	 Catch:{ Exception -> 0x0052 }
                                        r2 = 0;
                                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                                        if (r0 <= 0) goto L_0x004b;
                                    L_0x0030:
                                        r0 = com.appodeal.ads.n.o;	 Catch:{ Exception -> 0x0052 }
                                        r0 = (double) r0;	 Catch:{ Exception -> 0x0052 }
                                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                                        r0 = r0 * r2;
                                        r0 = (int) r0;	 Catch:{ Exception -> 0x0052 }
                                        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0052 }
                                    L_0x003c:
                                        r0 = com.appodeal.ads.n.o;	 Catch:{ Exception -> 0x0052 }
                                        if (r0 < r4) goto L_0x0045;
                                    L_0x0040:
                                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0052 }
                                    L_0x0045:
                                        r0 = r2;
                                        r0.quit();
                                    L_0x004a:
                                        return;
                                    L_0x004b:
                                        r0 = com.appodeal.ads.n.o;	 Catch:{ Exception -> 0x0052 }
                                        r0 = r0 * 2;
                                        com.appodeal.ads.n.o = r0;	 Catch:{ Exception -> 0x0052 }
                                        goto L_0x003c;
                                    L_0x0052:
                                        r0 = move-exception;
                                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x0060 }
                                        r0 = 1;
                                        com.appodeal.ads.q.a(r0);	 Catch:{ all -> 0x0060 }
                                        r0 = r2;
                                        r0.quit();
                                        goto L_0x004a;
                                    L_0x0060:
                                        r0 = move-exception;
                                        r1 = r2;
                                        r1.quit();
                                        throw r0;
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.q.4.run():void");
                                    }
                                }, (long) i3);
                            }
                            if (Appodeal.c != null) {
                                Appodeal.c.finish();
                                Appodeal.c.overridePendingTransition(0, 0);
                                Appodeal.c = null;
                            }
                            if (n.e == null) {
                                return;
                            }
                            if (sVar.t) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (n.e != null) {
                                            n.e.onInterstitialLoaded(true);
                                        }
                                    }
                                });
                            } else {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (n.e != null) {
                                            n.e.onInterstitialFailedToLoad();
                                        }
                                    }
                                });
                            }
                        } else if (!a) {
                            n.b(i);
                        }
                    } else if (sVar.d.isEmpty()) {
                        sVar.v = Games.SMART_PROMO_GAME_ID;
                    } else if (!a) {
                        n.b(i);
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            a(true);
        }
    }

    public static void a(int i, o oVar) {
        try {
            s sVar = (s) n.p.get(i);
            if (!sVar.h) {
                h hVar;
                sVar.h = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onInterstitialShown, eCPM: %.2f", new Object[]{an.a(oVar.a()), Double.valueOf(sVar.b)}));
                if (AppodealSettings.i || sVar.C == null) {
                    hVar = null;
                } else {
                    h h = sVar.C.h();
                    if (!sVar.y) {
                        a(i, h);
                    }
                    hVar = h;
                }
                sVar.s = false;
                sVar.t = false;
                oVar.g().d();
                n.q++;
                new c(Appodeal.b, i, "show").a(n.s).a(new v()).a(sVar.o).b(sVar.n).c(sVar.v).a(hVar).a(sVar.h()).d("banner").a(sVar.a).a(g.a().b().i()).a().a();
                oVar.g().b(Appodeal.b, i);
                if (n.x != null) {
                    n.x.d(sVar.p);
                    if (hVar == null || !hVar.f()) {
                        n.x.a(sVar.o);
                    } else {
                        n.x.a(hVar.e());
                    }
                    String a = oVar.g().a();
                    if (a != null) {
                        n.x.c(a);
                    }
                    n.x.a(System.currentTimeMillis());
                }
                if (n.u) {
                    String a2 = oVar.g().a();
                    if (a2 != null) {
                        if (hVar == null || !hVar.f()) {
                            new a(Appodeal.b, sVar.n, sVar.o, sVar.p, a2, 1).b();
                        } else {
                            new a(Appodeal.b, sVar.n, hVar.e(), sVar.p, a2, 1).b();
                        }
                    }
                }
                if (!sVar.a() && n.m) {
                    s a3 = n.a();
                    if (a3 == null || a3.b()) {
                        if (oVar.a().equals(com.appodeal.ads.b.c.f().a())) {
                            new a(Appodeal.b).c().a();
                        } else {
                            n.b(Appodeal.b);
                        }
                    }
                }
                if (n.e != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (n.e != null) {
                                n.e.onInterstitialShown();
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void b(int i, o oVar) {
        a(i, oVar, null);
    }

    public static void a(int i, o oVar, t.a aVar) {
        try {
            s sVar = (s) n.p.get(i);
            if (!sVar.j) {
                sVar.j = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onInterstitialClicked, eCPM: %.2f", new Object[]{an.a(oVar.a()), Double.valueOf(sVar.b)}));
                if (n.x != null) {
                    n.x.b(System.currentTimeMillis());
                }
                if (!sVar.i && n.w > 0) {
                    d(i, oVar);
                }
                h hVar = null;
                if (!(AppodealSettings.i || !(oVar.g() instanceof aa) || sVar.C == null)) {
                    hVar = sVar.C.h();
                }
                n.r++;
                new c(Appodeal.b, i, "click").a(n.s).a(aVar).a(sVar.o).b(sVar.n).c(sVar.v).a(hVar).a(sVar.h()).d("banner").a(sVar.a).a().a();
                oVar.g().c(Appodeal.b, i);
                if (n.e != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (n.e != null) {
                                n.e.onInterstitialClicked();
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

    public static void c(int i, o oVar) {
        if (i != -1) {
            try {
                if (!((s) n.p.get(i)).k && oVar != m.f()) {
                    s sVar = (s) n.p.get(i);
                    sVar.k = true;
                    Appodeal.a(String.format(Locale.ENGLISH, "%s onInterstitialClosed, eCPM: %.2f", new Object[]{an.a(oVar.a()), Double.valueOf(sVar.b)}));
                    if (!sVar.i && n.w > 0 && System.currentTimeMillis() - (sVar.h() * 1000) >= ((long) n.w)) {
                        d(i, oVar);
                    }
                    if (n.e != null) {
                        Appodeal.b.runOnUiThread(new Runnable() {
                            public void run() {
                                if (n.e != null) {
                                    n.e.onInterstitialClosed();
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

    static void d(int i, o oVar) {
        s sVar = (s) n.p.get(i);
        if (!sVar.i) {
            sVar.i = true;
            h hVar = null;
            if (!(AppodealSettings.i || !(oVar.g() instanceof aa) || sVar.C == null)) {
                hVar = sVar.C.h();
            }
            new c(Appodeal.b, i, "finish").a(n.s).a(new v()).a(sVar.o).b(sVar.n).c(sVar.v).a(hVar).a(sVar.h()).d("banner").a(sVar.a).a().a();
        }
    }

    private static void a(int i, h hVar) {
        boolean z = false;
        try {
            s sVar = (s) n.p.get(i);
            if (!sVar.f.isEmpty() || !sVar.e.isEmpty()) {
                if (!sVar.y || hVar != null) {
                    if (Appodeal.e != null) {
                        f fVar = Appodeal.e;
                        if (sVar.s || sVar.t) {
                            z = true;
                        }
                        fVar.a(1, z);
                    }
                    if (sVar.B) {
                        sVar.A = false;
                    }
                    sVar.y = true;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    JSONArray jSONArray2 = new JSONArray();
                    if (!(hVar == null || hVar.f() || !sVar.f.remove(sVar.o))) {
                        sVar.f.add(sVar.o);
                    }
                    Iterator it = sVar.f.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                    it = sVar.e.iterator();
                    while (it.hasNext()) {
                        jSONArray2.put((String) it.next());
                    }
                    try {
                        jSONObject.put("requests", jSONArray);
                        jSONObject.put(GraphResponse.SUCCESS_KEY, sVar.s);
                        jSONObject.put("precache_requests", jSONArray2);
                        jSONObject.put("precache_success", sVar.t);
                        String jSONObject2 = jSONObject.toString();
                        if (hVar != null) {
                            hVar.b(jSONObject);
                        }
                        new c(Appodeal.b, i, "stats").a(jSONObject2).b(sVar.n).a(hVar).a(sVar.a).a().a();
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
