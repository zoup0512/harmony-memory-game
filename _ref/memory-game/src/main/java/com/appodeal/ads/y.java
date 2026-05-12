package com.appodeal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.c.q;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.g;
import com.appodeal.ads.t.c;
import com.appodeal.ads.utils.v;
import com.appodeal.ads.utils.w;
import com.appodeal.ads.utils.w.a;
import com.cube.memorygames.Games;
import com.facebook.GraphResponse;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class y {
    public static void a(int i, int i2, w wVar) {
        a(i, i2, wVar, false, false);
    }

    public static void a(int i, int i2, w wVar, boolean z) {
        a(i, i2, wVar, z, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r10, int r11, com.appodeal.ads.w r12, final boolean r13, boolean r14) {
        /*
        r3 = 0;
        r2 = 1;
        r0 = com.appodeal.ads.v.t;	 Catch:{ Exception -> 0x0152 }
        r0 = r0.get(r10);	 Catch:{ Exception -> 0x0152 }
        r0 = (com.appodeal.ads.aa) r0;	 Catch:{ Exception -> 0x0152 }
        r1 = com.appodeal.ads.v.e;	 Catch:{ Exception -> 0x0152 }
        if (r10 != r1) goto L_0x0012;
    L_0x000e:
        r1 = r0.h;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r1 = r0.y;	 Catch:{ Exception -> 0x0152 }
        r4 = -1;
        if (r1 == r4) goto L_0x0021;
    L_0x0018:
        r1 = r0.y;	 Catch:{ Exception -> 0x0152 }
        r4 = -2;
        if (r1 == r4) goto L_0x0021;
    L_0x001d:
        r1 = r0.y;	 Catch:{ Exception -> 0x0152 }
        if (r11 >= r1) goto L_0x0012;
    L_0x0021:
        r0.y = r11;	 Catch:{ Exception -> 0x0152 }
        r1 = r0.t;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x003b;
    L_0x0027:
        r1 = r0.l;	 Catch:{ Exception -> 0x0152 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Exception -> 0x0152 }
        r0.u = r1;	 Catch:{ Exception -> 0x0152 }
        r1 = r12.f();	 Catch:{ Exception -> 0x0152 }
        r1 = r1 instanceof com.appodeal.ads.c.q;	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x003b;
    L_0x0039:
        if (r14 == 0) goto L_0x0012;
    L_0x003b:
        r1 = r0.l;	 Catch:{ Exception -> 0x0152 }
        r4 = "ecpm";
        r1 = r1.has(r4);	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x004f;
    L_0x0045:
        r1 = r0.l;	 Catch:{ Exception -> 0x0152 }
        r4 = "ecpm";
        r4 = r1.getDouble(r4);	 Catch:{ Exception -> 0x0152 }
        r0.b = r4;	 Catch:{ Exception -> 0x0152 }
    L_0x004f:
        r1 = java.util.Locale.ENGLISH;	 Catch:{ Exception -> 0x0152 }
        r4 = "%s onMrecLoaded, eCPM: %.2f";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0152 }
        r6 = 0;
        r7 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r7 = com.appodeal.ads.an.a(r7);	 Catch:{ Exception -> 0x0152 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0152 }
        r6 = 1;
        r8 = r0.b;	 Catch:{ Exception -> 0x0152 }
        r7 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0152 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0152 }
        r1 = java.lang.String.format(r1, r4, r5);	 Catch:{ Exception -> 0x0152 }
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0152 }
        if (r13 == 0) goto L_0x015b;
    L_0x0073:
        r1 = 1;
        r0.s = r1;	 Catch:{ Exception -> 0x0152 }
    L_0x0076:
        r1 = r0.l;	 Catch:{ Exception -> 0x0152 }
        r4 = "offer";
        r1 = r1.optBoolean(r4);	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0083;
    L_0x0080:
        r1 = 1;
        r0.t = r1;	 Catch:{ Exception -> 0x0152 }
    L_0x0083:
        r1 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r0.o = r1;	 Catch:{ Exception -> 0x0152 }
        r1 = r12.f();	 Catch:{ Exception -> 0x0152 }
        r1 = r1 instanceof com.appodeal.ads.c.q;	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x0160;
    L_0x0091:
        r1 = r0.l;	 Catch:{ Exception -> 0x0152 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Exception -> 0x0152 }
        r0.n = r1;	 Catch:{ Exception -> 0x0152 }
    L_0x009b:
        r0.p = r12;	 Catch:{ Exception -> 0x0152 }
        if (r13 == 0) goto L_0x00a5;
    L_0x009f:
        r1 = r0.f();	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x00a8;
    L_0x00a5:
        r1 = 0;
        r0.q = r1;	 Catch:{ Exception -> 0x0152 }
    L_0x00a8:
        r6 = r0.f();	 Catch:{ Exception -> 0x0152 }
        r1 = r0.a();	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x0174;
    L_0x00b2:
        r1 = r0.d;	 Catch:{ Exception -> 0x0152 }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x0174;
    L_0x00ba:
        if (r6 != 0) goto L_0x0174;
    L_0x00bc:
        r5 = r2;
    L_0x00bd:
        if (r5 == 0) goto L_0x0177;
    L_0x00bf:
        if (r13 == 0) goto L_0x0177;
    L_0x00c1:
        r4 = r2;
    L_0x00c2:
        if (r4 != 0) goto L_0x017a;
    L_0x00c4:
        r1 = r0.t;	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x017a;
    L_0x00c8:
        r1 = r0.z;	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x017a;
    L_0x00cc:
        r1 = r0.A;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x017a;
    L_0x00d0:
        r1 = r2;
    L_0x00d1:
        r7 = com.appodeal.ads.AppodealSettings.i;	 Catch:{ Exception -> 0x0152 }
        if (r7 != 0) goto L_0x01b6;
    L_0x00d5:
        r7 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0152 }
        r7 = r7.b();	 Catch:{ Exception -> 0x0152 }
        r8 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r7 = r7.b(r8);	 Catch:{ Exception -> 0x0152 }
        if (r7 != 0) goto L_0x01b6;
    L_0x00e5:
        if (r1 == 0) goto L_0x0186;
    L_0x00e7:
        r1 = r0.l;	 Catch:{ Exception -> 0x017d }
        r4 = "ecpm";
        r8 = r1.getDouble(r4);	 Catch:{ Exception -> 0x017d }
        r1 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x017d }
        com.appodeal.ads.v.a(r10, r1);	 Catch:{ Exception -> 0x017d }
    L_0x00f6:
        if (r13 == 0) goto L_0x00fc;
    L_0x00f8:
        r1 = com.appodeal.ads.v.m;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0111;
    L_0x00fc:
        r1 = r0.g;	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x0111;
    L_0x0100:
        r1 = 1;
        r0.g = r1;	 Catch:{ Exception -> 0x0152 }
        r1 = com.appodeal.ads.v.d;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0111;
    L_0x0107:
        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0152 }
        r4 = new com.appodeal.ads.y$1;	 Catch:{ Exception -> 0x0152 }
        r4.<init>(r13);	 Catch:{ Exception -> 0x0152 }
        r1.runOnUiThread(r4);	 Catch:{ Exception -> 0x0152 }
    L_0x0111:
        if (r5 == 0) goto L_0x01e6;
    L_0x0113:
        r1 = r0.t;	 Catch:{ Exception -> 0x0152 }
        if (r1 != 0) goto L_0x0119;
    L_0x0117:
        if (r13 == 0) goto L_0x01e6;
    L_0x0119:
        r1 = r2;
    L_0x011a:
        if (r1 == 0) goto L_0x011f;
    L_0x011c:
        com.appodeal.ads.v.b(r10);	 Catch:{ Exception -> 0x0152 }
    L_0x011f:
        if (r6 == 0) goto L_0x014c;
    L_0x0121:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0138;
    L_0x0125:
        r1 = r0.r;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x01e9;
    L_0x0129:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r4 = r0.p;	 Catch:{ Exception -> 0x0152 }
        r4 = r4.a();	 Catch:{ Exception -> 0x0152 }
        r0 = r0.r;	 Catch:{ Exception -> 0x0152 }
        r1.a(r3, r4, r0);	 Catch:{ Exception -> 0x0152 }
    L_0x0138:
        r0 = new com.appodeal.ads.x$b;	 Catch:{ Exception -> 0x0152 }
        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0152 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0152 }
        r0 = r0.b();	 Catch:{ Exception -> 0x0152 }
        r1 = com.appodeal.ads.v.w;	 Catch:{ Exception -> 0x0152 }
        r0 = r0.a(r1);	 Catch:{ Exception -> 0x0152 }
        r0.a();	 Catch:{ Exception -> 0x0152 }
    L_0x014c:
        r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x0152 }
        goto L_0x0012;
    L_0x0152:
        r0 = move-exception;
        com.appodeal.ads.Appodeal.a(r0);
        a(r2);
        goto L_0x0012;
    L_0x015b:
        r1 = 1;
        r0.r = r1;	 Catch:{ Exception -> 0x0152 }
        goto L_0x0076;
    L_0x0160:
        r1 = r0.B;	 Catch:{ Exception -> 0x0152 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x009b;
    L_0x0168:
        r1 = r0.B;	 Catch:{ Exception -> 0x0152 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0152 }
        r4 = 1;
        r1.a(r4);	 Catch:{ Exception -> 0x0152 }
        goto L_0x009b;
    L_0x0174:
        r5 = r3;
        goto L_0x00bd;
    L_0x0177:
        r4 = r3;
        goto L_0x00c2;
    L_0x017a:
        r1 = r3;
        goto L_0x00d1;
    L_0x017d:
        r1 = move-exception;
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0152 }
        r1 = 1;
        r0.z = r1;	 Catch:{ Exception -> 0x0152 }
        goto L_0x00f6;
    L_0x0186:
        if (r4 != 0) goto L_0x01a3;
    L_0x0188:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0198;
    L_0x018c:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        r4 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r7 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r8 = 1;
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0152 }
    L_0x0198:
        r1 = r0.B;	 Catch:{ Exception -> 0x0152 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0152 }
        a(r10, r1);	 Catch:{ Exception -> 0x0152 }
        goto L_0x00f6;
    L_0x01a3:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x00f6;
    L_0x01a7:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        r4 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r7 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r8 = r0.s;	 Catch:{ Exception -> 0x0152 }
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0152 }
        goto L_0x00f6;
    L_0x01b6:
        if (r13 == 0) goto L_0x01be;
    L_0x01b8:
        r1 = r0.f();	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x01d3;
    L_0x01be:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x01ce;
    L_0x01c2:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        r4 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r7 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r8 = 1;
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0152 }
    L_0x01ce:
        a(r10);	 Catch:{ Exception -> 0x0152 }
        goto L_0x00f6;
    L_0x01d3:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x00f6;
    L_0x01d7:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        r4 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r7 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r8 = r0.s;	 Catch:{ Exception -> 0x0152 }
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0152 }
        goto L_0x00f6;
    L_0x01e6:
        r1 = r3;
        goto L_0x011a;
    L_0x01e9:
        r1 = r0.s;	 Catch:{ Exception -> 0x0152 }
        if (r1 == 0) goto L_0x0138;
    L_0x01ed:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0152 }
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r4 = r12.a();	 Catch:{ Exception -> 0x0152 }
        r0 = r0.s;	 Catch:{ Exception -> 0x0152 }
        r1.a(r3, r4, r0);	 Catch:{ Exception -> 0x0152 }
        goto L_0x0138;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.y.a(int, int, com.appodeal.ads.w, boolean, boolean):void");
    }

    public static void a() {
        a(false);
    }

    public static void a(boolean z) {
        try {
            Appodeal.a("onMrecFailedToLoad");
            if (z && !v.t.isEmpty()) {
                aa aaVar = (aa) v.t.get(v.t.size() - 1);
                aaVar.r = false;
                aaVar.s = false;
                aaVar.q = false;
            }
            if (v.l) {
                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r5 = this;
                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        r0 = com.appodeal.ads.v.t;	 Catch:{ Exception -> 0x006e }
                        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x006e }
                        if (r0 != 0) goto L_0x0078;
                    L_0x000b:
                        r0 = com.appodeal.ads.v.t;	 Catch:{ Exception -> 0x006e }
                        r1 = com.appodeal.ads.v.t;	 Catch:{ Exception -> 0x006e }
                        r1 = r1.size();	 Catch:{ Exception -> 0x006e }
                        r1 = r1 + -1;
                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x006e }
                        r0 = (com.appodeal.ads.aa) r0;	 Catch:{ Exception -> 0x006e }
                        r0 = r0.b();	 Catch:{ Exception -> 0x006e }
                        if (r0 == 0) goto L_0x0032;
                    L_0x0021:
                        r0 = com.appodeal.ads.v.s;	 Catch:{ Exception -> 0x006e }
                        r1 = com.appodeal.ads.v.c.HIDDEN;	 Catch:{ Exception -> 0x006e }
                        if (r0 == r1) goto L_0x002d;
                    L_0x0027:
                        r0 = com.appodeal.ads.v.s;	 Catch:{ Exception -> 0x006e }
                        r1 = com.appodeal.ads.v.c.NEVER_SHOWN;	 Catch:{ Exception -> 0x006e }
                        if (r0 != r1) goto L_0x005f;
                    L_0x002d:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x006e }
                        com.appodeal.ads.v.b(r0);	 Catch:{ Exception -> 0x006e }
                    L_0x0032:
                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x006e }
                        r0 = r0.b();	 Catch:{ Exception -> 0x006e }
                        r0 = r0.k();	 Catch:{ Exception -> 0x006e }
                        r2 = 0;
                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                        if (r0 <= 0) goto L_0x0085;
                    L_0x0044:
                        r0 = com.appodeal.ads.v.n;	 Catch:{ Exception -> 0x006e }
                        r0 = (double) r0;	 Catch:{ Exception -> 0x006e }
                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                        r0 = r0 * r2;
                        r0 = (int) r0;	 Catch:{ Exception -> 0x006e }
                        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x006e }
                    L_0x0050:
                        r0 = com.appodeal.ads.v.n;	 Catch:{ Exception -> 0x006e }
                        if (r0 < r4) goto L_0x0059;
                    L_0x0054:
                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x006e }
                    L_0x0059:
                        r0 = r0;
                        r0.quit();
                    L_0x005e:
                        return;
                    L_0x005f:
                        r0 = new com.appodeal.ads.x$a;	 Catch:{ Exception -> 0x006e }
                        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x006e }
                        r0.<init>(r1);	 Catch:{ Exception -> 0x006e }
                        r0 = r0.b();	 Catch:{ Exception -> 0x006e }
                        r0.a();	 Catch:{ Exception -> 0x006e }
                        goto L_0x0032;
                    L_0x006e:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x007e }
                        r0 = r0;
                        r0.quit();
                        goto L_0x005e;
                    L_0x0078:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x006e }
                        com.appodeal.ads.v.b(r0);	 Catch:{ Exception -> 0x006e }
                        goto L_0x0032;
                    L_0x007e:
                        r0 = move-exception;
                        r1 = r0;
                        r1.quit();
                        throw r0;
                    L_0x0085:
                        r0 = com.appodeal.ads.v.n;	 Catch:{ Exception -> 0x006e }
                        r0 = r0 * 2;
                        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x006e }
                        goto L_0x0050;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.y.3.run():void");
                    }
                }, (long) v.n);
                if (v.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (v.d != null) {
                                v.d.onMrecFailedToLoad();
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
        b(i, i2, null, false);
    }

    public static void b(int i, int i2, w wVar) {
        b(i, i2, wVar, false);
    }

    public static void b(int i, int i2, w wVar, boolean z) {
        try {
            aa aaVar = (aa) v.t.get(i);
            if (i == v.e && !aaVar.h) {
                if (!AppodealSettings.i && aaVar.y == -2 && !aaVar.x && i2 == -2) {
                    a(i, aaVar.B.h());
                } else if (aaVar.y == -1 || i2 < aaVar.y) {
                    aaVar.y = i2;
                    boolean a = aaVar.a();
                    if (!aaVar.t) {
                        if (wVar == null) {
                            Appodeal.a("onMrecFailedToLoad");
                        } else {
                            Appodeal.a(String.format(Locale.ENGLISH, "%s onMrecFailedToLoad, eCPM: %.2f", new Object[]{an.a(wVar.a()), Double.valueOf(aaVar.l.optDouble("ecpm", 0.0d))}));
                            if (Appodeal.e != null) {
                                Appodeal.e.a(256, wVar.a(), false);
                            }
                        }
                        aaVar.r = false;
                        if (z) {
                            if (!a) {
                                if (!aaVar.c.isEmpty()) {
                                    v.a(i);
                                } else if (!aaVar.d.isEmpty()) {
                                    v.b(i);
                                }
                            }
                        } else if (aaVar.d.isEmpty()) {
                            aaVar.q = false;
                            a(i);
                            if (!a && v.l) {
                                int i3;
                                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                                handlerThread.start();
                                Handler handler = new Handler(handlerThread.getLooper());
                                if (aaVar.s) {
                                    i3 = 30000;
                                } else {
                                    i3 = v.n;
                                }
                                handler.postDelayed(new Runnable() {
                                    /* JADX WARNING: inconsistent code. */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void run() {
                                        /*
                                        r5 = this;
                                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        r0 = com.appodeal.ads.v.t;	 Catch:{ Exception -> 0x0066 }
                                        r1 = com.appodeal.ads.v.t;	 Catch:{ Exception -> 0x0066 }
                                        r1 = r1.size();	 Catch:{ Exception -> 0x0066 }
                                        r1 = r1 + -1;
                                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0066 }
                                        r0 = (com.appodeal.ads.aa) r0;	 Catch:{ Exception -> 0x0066 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0066 }
                                        if (r0 == 0) goto L_0x002a;
                                    L_0x0019:
                                        r0 = com.appodeal.ads.v.s;	 Catch:{ Exception -> 0x0066 }
                                        r1 = com.appodeal.ads.v.c.HIDDEN;	 Catch:{ Exception -> 0x0066 }
                                        if (r0 == r1) goto L_0x0025;
                                    L_0x001f:
                                        r0 = com.appodeal.ads.v.s;	 Catch:{ Exception -> 0x0066 }
                                        r1 = com.appodeal.ads.v.c.NEVER_SHOWN;	 Catch:{ Exception -> 0x0066 }
                                        if (r0 != r1) goto L_0x0057;
                                    L_0x0025:
                                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0066 }
                                        com.appodeal.ads.v.b(r0);	 Catch:{ Exception -> 0x0066 }
                                    L_0x002a:
                                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0066 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0066 }
                                        r0 = r0.k();	 Catch:{ Exception -> 0x0066 }
                                        r2 = 0;
                                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                                        if (r0 <= 0) goto L_0x0070;
                                    L_0x003c:
                                        r0 = com.appodeal.ads.v.n;	 Catch:{ Exception -> 0x0066 }
                                        r0 = (double) r0;	 Catch:{ Exception -> 0x0066 }
                                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                                        r0 = r0 * r2;
                                        r0 = (int) r0;	 Catch:{ Exception -> 0x0066 }
                                        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x0066 }
                                    L_0x0048:
                                        r0 = com.appodeal.ads.v.n;	 Catch:{ Exception -> 0x0066 }
                                        if (r0 < r4) goto L_0x0051;
                                    L_0x004c:
                                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x0066 }
                                    L_0x0051:
                                        r0 = r2;
                                        r0.quit();
                                    L_0x0056:
                                        return;
                                    L_0x0057:
                                        r0 = new com.appodeal.ads.x$a;	 Catch:{ Exception -> 0x0066 }
                                        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0066 }
                                        r0.<init>(r1);	 Catch:{ Exception -> 0x0066 }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x0066 }
                                        r0.a();	 Catch:{ Exception -> 0x0066 }
                                        goto L_0x002a;
                                    L_0x0066:
                                        r0 = move-exception;
                                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x0077 }
                                        r0 = r2;
                                        r0.quit();
                                        goto L_0x0056;
                                    L_0x0070:
                                        r0 = com.appodeal.ads.v.n;	 Catch:{ Exception -> 0x0066 }
                                        r0 = r0 * 2;
                                        com.appodeal.ads.v.n = r0;	 Catch:{ Exception -> 0x0066 }
                                        goto L_0x0048;
                                    L_0x0077:
                                        r0 = move-exception;
                                        r1 = r2;
                                        r1.quit();
                                        throw r0;
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.y.5.run():void");
                                    }
                                }, (long) i3);
                            }
                            if (v.d == null) {
                                return;
                            }
                            if (aaVar.s) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (v.d != null) {
                                            v.d.onMrecLoaded(true);
                                        }
                                    }
                                });
                            } else {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (v.d != null) {
                                            v.d.onMrecFailedToLoad();
                                        }
                                    }
                                });
                            }
                        } else if (!a) {
                            v.b(i);
                        }
                    } else if (aaVar.d.isEmpty()) {
                        aaVar.u = Games.SMART_PROMO_GAME_ID;
                    } else if (!a) {
                        v.b(i);
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            a(true);
        }
    }

    public static void a(final int i, w wVar) {
        try {
            final aa aaVar = (aa) v.t.get(i);
            if (!aaVar.h) {
                String a;
                aaVar.h = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onMrecShown, eCPM: %.2f", new Object[]{an.a(wVar.a()), Double.valueOf(aaVar.b)}));
                h hVar = null;
                if (!(AppodealSettings.i || aaVar.B == null)) {
                    hVar = aaVar.B.h();
                    if (!aaVar.x) {
                        a(i, hVar);
                    }
                }
                aaVar.r = false;
                aaVar.s = false;
                wVar.f().f();
                v.u++;
                new c(Appodeal.b, i, "show").a(v.w).a(new v()).a(aaVar.n).b(aaVar.m).c(aaVar.u).a(hVar).a(aaVar.h()).d("banner_mrec").a(aaVar.a).a(g.a().b().k()).a().a();
                wVar.f().a(Appodeal.b, i);
                if (v.C != null) {
                    v.C.d(aaVar.o);
                    if (hVar == null || !hVar.f()) {
                        v.C.a(aaVar.n);
                    } else {
                        v.C.a(hVar.e());
                    }
                    a = wVar.f().a();
                    if (a != null) {
                        v.C.c(a);
                    }
                    v.C.a(System.currentTimeMillis());
                }
                if (v.y) {
                    a = wVar.f().a();
                    if (a != null) {
                        b(hVar, aaVar, a);
                    } else {
                        w.a(wVar.f().c(), hVar, new a() {
                            public void a(String str, h hVar) {
                                y.b(hVar, aaVar, str);
                            }
                        });
                    }
                }
                if (!aaVar.a() && v.l) {
                    aaVar = v.a();
                    if (aaVar == null || aaVar.b()) {
                        if (wVar.a().equals(com.appodeal.ads.c.c.h().a())) {
                            new a(Appodeal.b).c().a();
                        } else {
                            v.b(Appodeal.b);
                        }
                    }
                }
                if (v.l) {
                    final HandlerThread handlerThread = new HandlerThread("CachingThread");
                    handlerThread.start();
                    new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                        /* JADX WARNING: inconsistent code. */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                            r2 = this;
                            r0 = r8;	 Catch:{ Exception -> 0x003f }
                            r0 = r0 + 1;
                            r1 = com.appodeal.ads.v.e;	 Catch:{ Exception -> 0x003f }
                            if (r0 != r1) goto L_0x0039;
                        L_0x0008:
                            r0 = com.appodeal.ads.v.s;	 Catch:{ Exception -> 0x003f }
                            r1 = com.appodeal.ads.v.c.HIDDEN;	 Catch:{ Exception -> 0x003f }
                            if (r0 == r1) goto L_0x0039;
                        L_0x000e:
                            r0 = com.appodeal.ads.v.s;	 Catch:{ Exception -> 0x003f }
                            r1 = com.appodeal.ads.v.c.NEVER_SHOWN;	 Catch:{ Exception -> 0x003f }
                            if (r0 == r1) goto L_0x0039;
                        L_0x0014:
                            r0 = com.appodeal.ads.v.p;	 Catch:{ Exception -> 0x003f }
                            r0 = r0.isShown();	 Catch:{ Exception -> 0x003f }
                            if (r0 == 0) goto L_0x0039;
                        L_0x001c:
                            r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x003f }
                            r1 = 14;
                            if (r0 >= r1) goto L_0x0032;
                        L_0x0022:
                            r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x003f }
                            r1 = "keyguard";
                            r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x003f }
                            r0 = (android.app.KeyguardManager) r0;	 Catch:{ Exception -> 0x003f }
                            r0 = r0.inKeyguardRestrictedInputMode();	 Catch:{ Exception -> 0x003f }
                            if (r0 != 0) goto L_0x0039;
                        L_0x0032:
                            r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x003f }
                            r1 = com.appodeal.ads.v.w;	 Catch:{ Exception -> 0x003f }
                            com.appodeal.ads.v.a(r0, r1);	 Catch:{ Exception -> 0x003f }
                        L_0x0039:
                            r0 = r0;
                            r0.quit();
                        L_0x003e:
                            return;
                        L_0x003f:
                            r0 = move-exception;
                            com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x0049 }
                            r0 = r0;
                            r0.quit();
                            goto L_0x003e;
                        L_0x0049:
                            r0 = move-exception;
                            r1 = r0;
                            r1.quit();
                            throw r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.y.9.run():void");
                        }
                    }, (long) v.b().intValue());
                }
                if (v.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (v.d != null) {
                                v.d.onMrecShown();
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void b(int i, w wVar) {
        try {
            aa aaVar = (aa) v.t.get(i);
            if (!aaVar.i) {
                aaVar.i = true;
                wVar.f().b();
                h hVar = null;
                if (!(AppodealSettings.i || !(wVar.f() instanceof q) || aaVar.B == null)) {
                    hVar = aaVar.B.h();
                }
                new c(Appodeal.b, i, "finish").a(v.w).a(new v()).a(aaVar.n).b(aaVar.m).c(aaVar.u).a(hVar).a(aaVar.h()).d("banner_mrec").a(aaVar.a).a().a();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void c(int i, w wVar) {
        a(i, wVar, null);
    }

    public static void a(int i, w wVar, t.a aVar) {
        try {
            aa aaVar = (aa) v.t.get(i);
            if (!aaVar.h) {
                a(i, wVar);
            }
            if (!aaVar.i && v.B > 0) {
                b(i, wVar);
            }
            if (!aaVar.j) {
                aaVar.j = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onMrecClicked, eCPM: %.2f", new Object[]{an.a(wVar.a()), Double.valueOf(aaVar.b)}));
                if (v.C != null) {
                    v.C.b(System.currentTimeMillis());
                }
                h hVar = null;
                if (!(AppodealSettings.i || !(wVar.f() instanceof q) || aaVar.B == null)) {
                    hVar = aaVar.B.h();
                }
                v.v++;
                new c(Appodeal.b, i, "click").a(v.w).a(aVar).a(aaVar.n).b(aaVar.m).c(aaVar.u).a(hVar).a(aaVar.h()).d("banner_mrec").a(aaVar.a).a().a();
                wVar.f().b(Appodeal.b, i);
                if (v.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (v.d != null) {
                                v.d.onMrecClicked();
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
        boolean z = false;
        try {
            aa aaVar = (aa) v.t.get(i);
            if (!aaVar.f.isEmpty() || !aaVar.e.isEmpty()) {
                if (!aaVar.x || hVar != null) {
                    if (Appodeal.e != null) {
                        f fVar = Appodeal.e;
                        if (aaVar.r || aaVar.s) {
                            z = true;
                        }
                        fVar.a(256, z);
                    }
                    if (aaVar.A) {
                        aaVar.z = false;
                    }
                    aaVar.x = true;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    JSONArray jSONArray2 = new JSONArray();
                    if (!(hVar == null || hVar.f() || !aaVar.f.remove(aaVar.n))) {
                        aaVar.f.add(aaVar.n);
                    }
                    Iterator it = aaVar.f.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                    it = aaVar.e.iterator();
                    while (it.hasNext()) {
                        jSONArray2.put((String) it.next());
                    }
                    try {
                        jSONObject.put("requests", jSONArray);
                        jSONObject.put(GraphResponse.SUCCESS_KEY, aaVar.r);
                        jSONObject.put("precache_requests", jSONArray2);
                        jSONObject.put("precache_success", aaVar.s);
                        String jSONObject2 = jSONObject.toString();
                        if (hVar != null) {
                            hVar.b(jSONObject);
                        }
                        new c(Appodeal.b, i, "stats").a(jSONObject2).b(aaVar.m).a(hVar).a(aaVar.a).a().a();
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

    private static void b(h hVar, aa aaVar, String str) {
        if (hVar == null || !hVar.f()) {
            new com.appodeal.ads.utils.a(Appodeal.b, aaVar.m, aaVar.n, aaVar.o, str, 256).b();
        } else {
            new com.appodeal.ads.utils.a(Appodeal.b, aaVar.m, hVar.e(), aaVar.o, str, 256).b();
        }
    }
}
