package com.appodeal.ads;

import android.os.Handler;
import android.os.HandlerThread;
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

public class j {
    public static void a(int i, int i2, h hVar) {
        a(i, i2, hVar, false, false, false);
    }

    public static void a(int i, int i2, h hVar, boolean z) {
        a(i, i2, hVar, z, false, false);
    }

    public static void a(int i, int i2, h hVar, boolean z, boolean z2) {
        a(i, i2, hVar, z, z2, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r10, int r11, final com.appodeal.ads.h r12, boolean r13, final boolean r14, boolean r15) {
        /*
        r3 = 0;
        r2 = 1;
        r0 = com.appodeal.ads.g.x;	 Catch:{ Exception -> 0x0166 }
        r0 = r0.get(r10);	 Catch:{ Exception -> 0x0166 }
        r0 = (com.appodeal.ads.l) r0;	 Catch:{ Exception -> 0x0166 }
        r1 = com.appodeal.ads.g.e;	 Catch:{ Exception -> 0x0166 }
        if (r10 != r1) goto L_0x0012;
    L_0x000e:
        r1 = r0.h;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r1 = r0.B;	 Catch:{ Exception -> 0x0166 }
        r4 = -1;
        if (r1 == r4) goto L_0x0021;
    L_0x0018:
        r1 = r0.B;	 Catch:{ Exception -> 0x0166 }
        r4 = -2;
        if (r1 == r4) goto L_0x0021;
    L_0x001d:
        r1 = r0.B;	 Catch:{ Exception -> 0x0166 }
        if (r11 >= r1) goto L_0x0012;
    L_0x0021:
        r0.B = r11;	 Catch:{ Exception -> 0x0166 }
        r1 = r0.u;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x003b;
    L_0x0027:
        r1 = r0.l;	 Catch:{ Exception -> 0x0166 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Exception -> 0x0166 }
        r0.v = r1;	 Catch:{ Exception -> 0x0166 }
        r1 = r12.f();	 Catch:{ Exception -> 0x0166 }
        r1 = r1 instanceof com.appodeal.ads.a.w;	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x003b;
    L_0x0039:
        if (r15 == 0) goto L_0x0012;
    L_0x003b:
        r1 = r0.l;	 Catch:{ Exception -> 0x0166 }
        r4 = "ecpm";
        r1 = r1.has(r4);	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x004f;
    L_0x0045:
        r1 = r0.l;	 Catch:{ Exception -> 0x0166 }
        r4 = "ecpm";
        r4 = r1.getDouble(r4);	 Catch:{ Exception -> 0x0166 }
        r0.b = r4;	 Catch:{ Exception -> 0x0166 }
    L_0x004f:
        r1 = java.util.Locale.ENGLISH;	 Catch:{ Exception -> 0x0166 }
        r4 = "%s onBannerLoaded, eCPM: %.2f";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0166 }
        r6 = 0;
        r7 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r7 = com.appodeal.ads.an.a(r7);	 Catch:{ Exception -> 0x0166 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0166 }
        r6 = 1;
        r8 = r0.b;	 Catch:{ Exception -> 0x0166 }
        r7 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0166 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0166 }
        r1 = java.lang.String.format(r1, r4, r5);	 Catch:{ Exception -> 0x0166 }
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0166 }
        if (r14 == 0) goto L_0x016f;
    L_0x0073:
        r1 = 1;
        r0.t = r1;	 Catch:{ Exception -> 0x0166 }
    L_0x0076:
        r1 = r0.l;	 Catch:{ Exception -> 0x0166 }
        r4 = "offer";
        r1 = r1.optBoolean(r4);	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0083;
    L_0x0080:
        r1 = 1;
        r0.u = r1;	 Catch:{ Exception -> 0x0166 }
    L_0x0083:
        r1 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r0.o = r1;	 Catch:{ Exception -> 0x0166 }
        r1 = r12.f();	 Catch:{ Exception -> 0x0166 }
        r1 = r1 instanceof com.appodeal.ads.a.w;	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x0174;
    L_0x0091:
        r1 = r0.l;	 Catch:{ Exception -> 0x0166 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Exception -> 0x0166 }
        r0.n = r1;	 Catch:{ Exception -> 0x0166 }
    L_0x009b:
        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0188 }
        r1 = r1.getResources();	 Catch:{ Exception -> 0x0188 }
        r1 = r1.getConfiguration();	 Catch:{ Exception -> 0x0188 }
        r1 = r1.orientation;	 Catch:{ Exception -> 0x0188 }
        r0.p = r1;	 Catch:{ Exception -> 0x0188 }
    L_0x00a9:
        r0.z = r13;	 Catch:{ Exception -> 0x0166 }
        r0.q = r12;	 Catch:{ Exception -> 0x0166 }
        if (r14 == 0) goto L_0x00b5;
    L_0x00af:
        r1 = r0.f();	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x00b8;
    L_0x00b5:
        r1 = 0;
        r0.r = r1;	 Catch:{ Exception -> 0x0166 }
    L_0x00b8:
        r6 = r0.f();	 Catch:{ Exception -> 0x0166 }
        r1 = r0.a();	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x018e;
    L_0x00c2:
        r1 = r0.d;	 Catch:{ Exception -> 0x0166 }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x018e;
    L_0x00ca:
        if (r6 != 0) goto L_0x018e;
    L_0x00cc:
        r5 = r2;
    L_0x00cd:
        if (r5 == 0) goto L_0x0191;
    L_0x00cf:
        if (r14 == 0) goto L_0x0191;
    L_0x00d1:
        r4 = r2;
    L_0x00d2:
        if (r4 != 0) goto L_0x0194;
    L_0x00d4:
        r1 = r0.u;	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x0194;
    L_0x00d8:
        r1 = r0.C;	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x0194;
    L_0x00dc:
        r1 = r0.D;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0194;
    L_0x00e0:
        r1 = r2;
    L_0x00e1:
        r7 = com.appodeal.ads.AppodealSettings.i;	 Catch:{ Exception -> 0x0166 }
        if (r7 != 0) goto L_0x01ce;
    L_0x00e5:
        r7 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0166 }
        r7 = r7.b();	 Catch:{ Exception -> 0x0166 }
        r8 = 4;
        r7 = r7.b(r8);	 Catch:{ Exception -> 0x0166 }
        if (r7 != 0) goto L_0x01ce;
    L_0x00f4:
        if (r1 == 0) goto L_0x01a0;
    L_0x00f6:
        r1 = r0.l;	 Catch:{ Exception -> 0x0197 }
        r4 = "ecpm";
        r8 = r1.getDouble(r4);	 Catch:{ Exception -> 0x0197 }
        r1 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0197 }
        com.appodeal.ads.g.a(r10, r1);	 Catch:{ Exception -> 0x0197 }
    L_0x0105:
        if (r14 == 0) goto L_0x010b;
    L_0x0107:
        r1 = com.appodeal.ads.g.m;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0120;
    L_0x010b:
        r1 = r0.g;	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x0120;
    L_0x010f:
        r1 = 1;
        r0.g = r1;	 Catch:{ Exception -> 0x0166 }
        r1 = com.appodeal.ads.g.d;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0120;
    L_0x0116:
        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0166 }
        r4 = new com.appodeal.ads.j$1;	 Catch:{ Exception -> 0x0166 }
        r4.<init>(r12, r14);	 Catch:{ Exception -> 0x0166 }
        r1.runOnUiThread(r4);	 Catch:{ Exception -> 0x0166 }
    L_0x0120:
        if (r5 == 0) goto L_0x01fc;
    L_0x0122:
        r1 = r0.u;	 Catch:{ Exception -> 0x0166 }
        if (r1 != 0) goto L_0x0128;
    L_0x0126:
        if (r14 == 0) goto L_0x01fc;
    L_0x0128:
        r1 = r2;
    L_0x0129:
        if (r1 == 0) goto L_0x012e;
    L_0x012b:
        com.appodeal.ads.g.b(r10);	 Catch:{ Exception -> 0x0166 }
    L_0x012e:
        if (r6 == 0) goto L_0x0160;
    L_0x0130:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0146;
    L_0x0134:
        r1 = r0.s;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x01ff;
    L_0x0138:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        r3 = 4;
        r4 = r0.q;	 Catch:{ Exception -> 0x0166 }
        r4 = r4.a();	 Catch:{ Exception -> 0x0166 }
        r5 = r0.s;	 Catch:{ Exception -> 0x0166 }
        r1.a(r3, r4, r5);	 Catch:{ Exception -> 0x0166 }
    L_0x0146:
        r1 = new com.appodeal.ads.i$b;	 Catch:{ Exception -> 0x0166 }
        r3 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0166 }
        r1.<init>(r3);	 Catch:{ Exception -> 0x0166 }
        r0 = r0.y;	 Catch:{ Exception -> 0x0166 }
        r0 = r1.a(r0);	 Catch:{ Exception -> 0x0166 }
        r1 = com.appodeal.ads.g.A;	 Catch:{ Exception -> 0x0166 }
        r0 = r0.a(r1);	 Catch:{ Exception -> 0x0166 }
        r0 = r0.b();	 Catch:{ Exception -> 0x0166 }
        r0.a();	 Catch:{ Exception -> 0x0166 }
    L_0x0160:
        r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x0166 }
        goto L_0x0012;
    L_0x0166:
        r0 = move-exception;
        com.appodeal.ads.Appodeal.a(r0);
        a(r2);
        goto L_0x0012;
    L_0x016f:
        r1 = 1;
        r0.s = r1;	 Catch:{ Exception -> 0x0166 }
        goto L_0x0076;
    L_0x0174:
        r1 = r0.E;	 Catch:{ Exception -> 0x0166 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x009b;
    L_0x017c:
        r1 = r0.E;	 Catch:{ Exception -> 0x0166 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0166 }
        r4 = 1;
        r1.a(r4);	 Catch:{ Exception -> 0x0166 }
        goto L_0x009b;
    L_0x0188:
        r1 = move-exception;
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0166 }
        goto L_0x00a9;
    L_0x018e:
        r5 = r3;
        goto L_0x00cd;
    L_0x0191:
        r4 = r3;
        goto L_0x00d2;
    L_0x0194:
        r1 = r3;
        goto L_0x00e1;
    L_0x0197:
        r1 = move-exception;
        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ Exception -> 0x0166 }
        r1 = 1;
        r0.C = r1;	 Catch:{ Exception -> 0x0166 }
        goto L_0x0105;
    L_0x01a0:
        if (r4 != 0) goto L_0x01bc;
    L_0x01a2:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x01b1;
    L_0x01a6:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        r4 = 4;
        r7 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r8 = 1;
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0166 }
    L_0x01b1:
        r1 = r0.E;	 Catch:{ Exception -> 0x0166 }
        r1 = r1.h();	 Catch:{ Exception -> 0x0166 }
        a(r10, r1);	 Catch:{ Exception -> 0x0166 }
        goto L_0x0105;
    L_0x01bc:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0105;
    L_0x01c0:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        r4 = 4;
        r7 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r8 = r0.t;	 Catch:{ Exception -> 0x0166 }
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0166 }
        goto L_0x0105;
    L_0x01ce:
        if (r14 == 0) goto L_0x01d6;
    L_0x01d0:
        r1 = r0.f();	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x01ea;
    L_0x01d6:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x01e5;
    L_0x01da:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        r4 = 4;
        r7 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r8 = 1;
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0166 }
    L_0x01e5:
        a(r10);	 Catch:{ Exception -> 0x0166 }
        goto L_0x0105;
    L_0x01ea:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0105;
    L_0x01ee:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        r4 = 4;
        r7 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r8 = r0.t;	 Catch:{ Exception -> 0x0166 }
        r1.a(r4, r7, r8);	 Catch:{ Exception -> 0x0166 }
        goto L_0x0105;
    L_0x01fc:
        r1 = r3;
        goto L_0x0129;
    L_0x01ff:
        r1 = r0.t;	 Catch:{ Exception -> 0x0166 }
        if (r1 == 0) goto L_0x0146;
    L_0x0203:
        r1 = com.appodeal.ads.Appodeal.e;	 Catch:{ Exception -> 0x0166 }
        r3 = 4;
        r4 = r12.a();	 Catch:{ Exception -> 0x0166 }
        r5 = r0.t;	 Catch:{ Exception -> 0x0166 }
        r1.a(r3, r4, r5);	 Catch:{ Exception -> 0x0166 }
        goto L_0x0146;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.j.a(int, int, com.appodeal.ads.h, boolean, boolean, boolean):void");
    }

    public static void a() {
        a(false);
    }

    public static void a(boolean z) {
        try {
            Appodeal.a("onBannerFailedToLoad");
            if (z && !g.x.isEmpty()) {
                l lVar = (l) g.x.get(g.x.size() - 1);
                lVar.s = false;
                lVar.t = false;
                lVar.r = false;
            }
            if (g.l) {
                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r5 = this;
                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        r0 = com.appodeal.ads.g.x;	 Catch:{ Exception -> 0x0074 }
                        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x0074 }
                        if (r0 != 0) goto L_0x007e;
                    L_0x000b:
                        r0 = com.appodeal.ads.g.x;	 Catch:{ Exception -> 0x0074 }
                        r1 = com.appodeal.ads.g.x;	 Catch:{ Exception -> 0x0074 }
                        r1 = r1.size();	 Catch:{ Exception -> 0x0074 }
                        r1 = r1 + -1;
                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0074 }
                        r0 = (com.appodeal.ads.l) r0;	 Catch:{ Exception -> 0x0074 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0074 }
                        if (r0 == 0) goto L_0x0032;
                    L_0x0021:
                        r0 = com.appodeal.ads.g.w;	 Catch:{ Exception -> 0x0074 }
                        r1 = com.appodeal.ads.g.d.HIDDEN;	 Catch:{ Exception -> 0x0074 }
                        if (r0 == r1) goto L_0x002d;
                    L_0x0027:
                        r0 = com.appodeal.ads.g.w;	 Catch:{ Exception -> 0x0074 }
                        r1 = com.appodeal.ads.g.d.NEVER_SHOWN;	 Catch:{ Exception -> 0x0074 }
                        if (r0 != r1) goto L_0x005f;
                    L_0x002d:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0074 }
                        com.appodeal.ads.g.b(r0);	 Catch:{ Exception -> 0x0074 }
                    L_0x0032:
                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x0074 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0074 }
                        r0 = r0.h();	 Catch:{ Exception -> 0x0074 }
                        r2 = 0;
                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                        if (r0 <= 0) goto L_0x008b;
                    L_0x0044:
                        r0 = com.appodeal.ads.g.n;	 Catch:{ Exception -> 0x0074 }
                        r0 = (double) r0;	 Catch:{ Exception -> 0x0074 }
                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                        r0 = r0 * r2;
                        r0 = (int) r0;	 Catch:{ Exception -> 0x0074 }
                        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x0074 }
                    L_0x0050:
                        r0 = com.appodeal.ads.g.n;	 Catch:{ Exception -> 0x0074 }
                        if (r0 < r4) goto L_0x0059;
                    L_0x0054:
                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x0074 }
                    L_0x0059:
                        r0 = r0;
                        r0.quit();
                    L_0x005e:
                        return;
                    L_0x005f:
                        r0 = new com.appodeal.ads.i$a;	 Catch:{ Exception -> 0x0074 }
                        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0074 }
                        r0.<init>(r1);	 Catch:{ Exception -> 0x0074 }
                        r0 = r0.b();	 Catch:{ Exception -> 0x0074 }
                        r1 = com.appodeal.ads.g.r;	 Catch:{ Exception -> 0x0074 }
                        r0 = r0.a(r1);	 Catch:{ Exception -> 0x0074 }
                        r0.a();	 Catch:{ Exception -> 0x0074 }
                        goto L_0x0032;
                    L_0x0074:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x0084 }
                        r0 = r0;
                        r0.quit();
                        goto L_0x005e;
                    L_0x007e:
                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0074 }
                        com.appodeal.ads.g.b(r0);	 Catch:{ Exception -> 0x0074 }
                        goto L_0x0032;
                    L_0x0084:
                        r0 = move-exception;
                        r1 = r0;
                        r1.quit();
                        throw r0;
                    L_0x008b:
                        r0 = com.appodeal.ads.g.n;	 Catch:{ Exception -> 0x0074 }
                        r0 = r0 * 2;
                        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x0074 }
                        goto L_0x0050;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.j.3.run():void");
                    }
                }, (long) g.n);
                if (g.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (g.d != null) {
                                g.d.onBannerFailedToLoad();
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

    public static void b(int i, int i2, h hVar) {
        b(i, i2, hVar, false);
    }

    public static void b(int i, int i2, final h hVar, boolean z) {
        try {
            l lVar = (l) g.x.get(i);
            if (i == g.e && !lVar.h) {
                if (!AppodealSettings.i && lVar.B == -2 && !lVar.A && i2 == -2) {
                    a(i, lVar.E.h());
                } else if ((lVar.B == -1 || i2 < lVar.B) && i2 != -2) {
                    lVar.B = i2;
                    boolean a = lVar.a();
                    if (!lVar.u) {
                        if (hVar == null) {
                            Appodeal.a("onBannerFailedToLoad");
                        } else {
                            Appodeal.a(String.format(Locale.ENGLISH, "%s onBannerFailedToLoad, eCPM: %.2f", new Object[]{an.a(hVar.a()), Double.valueOf(lVar.l.optDouble("ecpm", 0.0d))}));
                            if (Appodeal.e != null) {
                                Appodeal.e.a(4, hVar.a(), false);
                            }
                        }
                        lVar.s = false;
                        if (z) {
                            if (!a) {
                                if (!lVar.c.isEmpty()) {
                                    g.a(i);
                                } else if (!lVar.d.isEmpty()) {
                                    g.b(i);
                                }
                            }
                        } else if (lVar.d.isEmpty()) {
                            lVar.r = false;
                            a(i);
                            if (!a && g.l) {
                                int i3;
                                final HandlerThread handlerThread = new HandlerThread("CachingThread");
                                handlerThread.start();
                                Handler handler = new Handler(handlerThread.getLooper());
                                if (lVar.t) {
                                    i3 = 30000;
                                } else {
                                    i3 = g.n;
                                }
                                handler.postDelayed(new Runnable() {
                                    /* JADX WARNING: inconsistent code. */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void run() {
                                        /*
                                        r5 = this;
                                        r4 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        r0 = com.appodeal.ads.g.x;	 Catch:{ Exception -> 0x006c }
                                        r1 = com.appodeal.ads.g.x;	 Catch:{ Exception -> 0x006c }
                                        r1 = r1.size();	 Catch:{ Exception -> 0x006c }
                                        r1 = r1 + -1;
                                        r0 = r0.get(r1);	 Catch:{ Exception -> 0x006c }
                                        r0 = (com.appodeal.ads.l) r0;	 Catch:{ Exception -> 0x006c }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x006c }
                                        if (r0 == 0) goto L_0x002a;
                                    L_0x0019:
                                        r0 = com.appodeal.ads.g.w;	 Catch:{ Exception -> 0x006c }
                                        r1 = com.appodeal.ads.g.d.HIDDEN;	 Catch:{ Exception -> 0x006c }
                                        if (r0 == r1) goto L_0x0025;
                                    L_0x001f:
                                        r0 = com.appodeal.ads.g.w;	 Catch:{ Exception -> 0x006c }
                                        r1 = com.appodeal.ads.g.d.NEVER_SHOWN;	 Catch:{ Exception -> 0x006c }
                                        if (r0 != r1) goto L_0x0057;
                                    L_0x0025:
                                        r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x006c }
                                        com.appodeal.ads.g.b(r0);	 Catch:{ Exception -> 0x006c }
                                    L_0x002a:
                                        r0 = com.appodeal.ads.f.g.a();	 Catch:{ Exception -> 0x006c }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x006c }
                                        r0 = r0.h();	 Catch:{ Exception -> 0x006c }
                                        r2 = 0;
                                        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                                        if (r0 <= 0) goto L_0x0076;
                                    L_0x003c:
                                        r0 = com.appodeal.ads.g.n;	 Catch:{ Exception -> 0x006c }
                                        r0 = (double) r0;	 Catch:{ Exception -> 0x006c }
                                        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
                                        r0 = r0 * r2;
                                        r0 = (int) r0;	 Catch:{ Exception -> 0x006c }
                                        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x006c }
                                    L_0x0048:
                                        r0 = com.appodeal.ads.g.n;	 Catch:{ Exception -> 0x006c }
                                        if (r0 < r4) goto L_0x0051;
                                    L_0x004c:
                                        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
                                        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x006c }
                                    L_0x0051:
                                        r0 = r2;
                                        r0.quit();
                                    L_0x0056:
                                        return;
                                    L_0x0057:
                                        r0 = new com.appodeal.ads.i$a;	 Catch:{ Exception -> 0x006c }
                                        r1 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x006c }
                                        r0.<init>(r1);	 Catch:{ Exception -> 0x006c }
                                        r0 = r0.b();	 Catch:{ Exception -> 0x006c }
                                        r1 = com.appodeal.ads.g.r;	 Catch:{ Exception -> 0x006c }
                                        r0 = r0.a(r1);	 Catch:{ Exception -> 0x006c }
                                        r0.a();	 Catch:{ Exception -> 0x006c }
                                        goto L_0x002a;
                                    L_0x006c:
                                        r0 = move-exception;
                                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x007d }
                                        r0 = r2;
                                        r0.quit();
                                        goto L_0x0056;
                                    L_0x0076:
                                        r0 = com.appodeal.ads.g.n;	 Catch:{ Exception -> 0x006c }
                                        r0 = r0 * 2;
                                        com.appodeal.ads.g.n = r0;	 Catch:{ Exception -> 0x006c }
                                        goto L_0x0048;
                                    L_0x007d:
                                        r0 = move-exception;
                                        r1 = r2;
                                        r1.quit();
                                        throw r0;
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.j.5.run():void");
                                    }
                                }, (long) i3);
                            }
                            if (g.d == null) {
                                return;
                            }
                            if (lVar.t) {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (g.d != null) {
                                            g.d.onBannerLoaded(hVar.f().b, true);
                                        }
                                    }
                                });
                            } else {
                                Appodeal.b.runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (g.d != null) {
                                            g.d.onBannerFailedToLoad();
                                        }
                                    }
                                });
                            }
                        } else if (!a) {
                            g.b(i);
                        }
                    } else if (lVar.d.isEmpty()) {
                        lVar.v = Games.SMART_PROMO_GAME_ID;
                    } else if (!a) {
                        g.b(i);
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            a(true);
        }
    }

    public static void a(final int i, h hVar) {
        try {
            final l lVar = (l) g.x.get(i);
            if (!lVar.h) {
                String a;
                lVar.h = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onBannerShown, eCPM: %.2f", new Object[]{an.a(hVar.a()), Double.valueOf(lVar.b)}));
                h hVar2 = null;
                if (!(AppodealSettings.i || lVar.E == null)) {
                    hVar2 = lVar.E.h();
                    if (!lVar.A) {
                        a(i, hVar2);
                    }
                }
                lVar.s = false;
                lVar.t = false;
                hVar.f().f();
                g.y++;
                new c(Appodeal.b, i, "show").a(g.A).a(new v()).a(lVar.n).b(lVar.m).c(lVar.v).a(hVar2).a(lVar.h()).d("banner_320").a(lVar.a).a(g.a().b().h()).a().a();
                hVar.f().a(Appodeal.b, i);
                if (g.H != null) {
                    g.H.d(lVar.o);
                    if (hVar2 == null || !hVar2.f()) {
                        g.H.a(lVar.n);
                    } else {
                        g.H.a(hVar2.e());
                    }
                    a = hVar.f().a();
                    if (a != null) {
                        g.H.c(a);
                    }
                    g.H.a(System.currentTimeMillis());
                }
                if (g.C) {
                    a = hVar.f().a();
                    if (a != null) {
                        b(hVar2, lVar, a);
                    } else {
                        w.a(hVar.f().c(), hVar2, new a() {
                            public void a(String str, h hVar) {
                                j.b(hVar, lVar, str);
                            }
                        });
                    }
                }
                if (!lVar.a() && g.l) {
                    lVar = g.a();
                    if (lVar == null || lVar.b()) {
                        if (hVar.a().equals(com.appodeal.ads.a.c.h().a())) {
                            new a(Appodeal.b).c().a();
                        } else {
                            g.b(Appodeal.b);
                        }
                    }
                }
                if (g.l) {
                    final HandlerThread handlerThread = new HandlerThread("CachingThread");
                    handlerThread.start();
                    new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
                        /* JADX WARNING: inconsistent code. */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                            r3 = this;
                            r0 = r8;	 Catch:{ Exception -> 0x0041 }
                            r0 = r0 + 1;
                            r1 = com.appodeal.ads.g.e;	 Catch:{ Exception -> 0x0041 }
                            if (r0 != r1) goto L_0x003b;
                        L_0x0008:
                            r0 = com.appodeal.ads.g.w;	 Catch:{ Exception -> 0x0041 }
                            r1 = com.appodeal.ads.g.d.HIDDEN;	 Catch:{ Exception -> 0x0041 }
                            if (r0 == r1) goto L_0x003b;
                        L_0x000e:
                            r0 = com.appodeal.ads.g.w;	 Catch:{ Exception -> 0x0041 }
                            r1 = com.appodeal.ads.g.d.NEVER_SHOWN;	 Catch:{ Exception -> 0x0041 }
                            if (r0 == r1) goto L_0x003b;
                        L_0x0014:
                            r0 = com.appodeal.ads.g.p;	 Catch:{ Exception -> 0x0041 }
                            r0 = r0.isShown();	 Catch:{ Exception -> 0x0041 }
                            if (r0 == 0) goto L_0x003b;
                        L_0x001c:
                            r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0041 }
                            r1 = 14;
                            if (r0 >= r1) goto L_0x0032;
                        L_0x0022:
                            r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0041 }
                            r1 = "keyguard";
                            r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x0041 }
                            r0 = (android.app.KeyguardManager) r0;	 Catch:{ Exception -> 0x0041 }
                            r0 = r0.inKeyguardRestrictedInputMode();	 Catch:{ Exception -> 0x0041 }
                            if (r0 != 0) goto L_0x003b;
                        L_0x0032:
                            r0 = com.appodeal.ads.Appodeal.b;	 Catch:{ Exception -> 0x0041 }
                            r1 = com.appodeal.ads.g.A;	 Catch:{ Exception -> 0x0041 }
                            r2 = com.appodeal.ads.g.r;	 Catch:{ Exception -> 0x0041 }
                            com.appodeal.ads.g.a(r0, r1, r2);	 Catch:{ Exception -> 0x0041 }
                        L_0x003b:
                            r0 = r0;
                            r0.quit();
                        L_0x0040:
                            return;
                        L_0x0041:
                            r0 = move-exception;
                            com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x004b }
                            r0 = r0;
                            r0.quit();
                            goto L_0x0040;
                        L_0x004b:
                            r0 = move-exception;
                            r1 = r0;
                            r1.quit();
                            throw r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.j.9.run():void");
                        }
                    }, (long) g.b().intValue());
                }
                if (g.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (g.d != null) {
                                g.d.onBannerShown();
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void b(int i, h hVar) {
        try {
            l lVar = (l) g.x.get(i);
            if (!lVar.i) {
                lVar.i = true;
                hVar.f().b();
                h hVar2 = null;
                if (!(AppodealSettings.i || !(hVar.f() instanceof com.appodeal.ads.a.w) || lVar.E == null)) {
                    hVar2 = lVar.E.h();
                }
                new c(Appodeal.b, i, "finish").a(g.A).a(new v()).a(lVar.n).b(lVar.m).c(lVar.v).a(hVar2).a(lVar.h()).d("banner_320").a(lVar.a).a().a();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void c(int i, h hVar) {
        a(i, hVar, null);
    }

    public static void a(int i, h hVar, t.a aVar) {
        try {
            l lVar = (l) g.x.get(i);
            if (!lVar.h) {
                a(i, hVar);
            }
            if (!lVar.i && g.F > 0) {
                b(i, hVar);
            }
            if (!lVar.j) {
                lVar.j = true;
                Appodeal.a(String.format(Locale.ENGLISH, "%s onBannerClicked, eCPM: %.2f", new Object[]{an.a(hVar.a()), Double.valueOf(lVar.b)}));
                if (g.H != null) {
                    g.H.b(System.currentTimeMillis());
                }
                h hVar2 = null;
                if (!(AppodealSettings.i || !(hVar.f() instanceof com.appodeal.ads.a.w) || lVar.E == null)) {
                    hVar2 = lVar.E.h();
                }
                g.z++;
                new c(Appodeal.b, i, "click").a(g.A).a(aVar).a(lVar.n).b(lVar.m).c(lVar.v).a(hVar2).a(lVar.h()).d("banner_320").a(lVar.a).a().a();
                hVar.f().b(Appodeal.b, i);
                if (g.d != null) {
                    Appodeal.b.runOnUiThread(new Runnable() {
                        public void run() {
                            if (g.d != null) {
                                g.d.onBannerClicked();
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
            l lVar = (l) g.x.get(i);
            if (!lVar.f.isEmpty() || !lVar.e.isEmpty()) {
                if (!lVar.A || hVar != null) {
                    if (Appodeal.e != null) {
                        f fVar = Appodeal.e;
                        if (lVar.s || lVar.t) {
                            z = true;
                        }
                        fVar.a(4, z);
                    }
                    if (lVar.D) {
                        lVar.C = false;
                    }
                    lVar.A = true;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    JSONArray jSONArray2 = new JSONArray();
                    if (!(hVar == null || hVar.f() || !lVar.f.remove(lVar.n))) {
                        lVar.f.add(lVar.n);
                    }
                    Iterator it = lVar.f.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                    it = lVar.e.iterator();
                    while (it.hasNext()) {
                        jSONArray2.put((String) it.next());
                    }
                    try {
                        jSONObject.put("requests", jSONArray);
                        jSONObject.put(GraphResponse.SUCCESS_KEY, lVar.s);
                        jSONObject.put("precache_requests", jSONArray2);
                        jSONObject.put("precache_success", lVar.t);
                        String jSONObject2 = jSONObject.toString();
                        if (hVar != null) {
                            hVar.b(jSONObject);
                        }
                        new c(Appodeal.b, i, "stats").a(jSONObject2).b(lVar.m).a(hVar).a(lVar.a).a().a();
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

    private static void b(h hVar, l lVar, String str) {
        if (hVar == null || !hVar.f()) {
            new com.appodeal.ads.utils.a(Appodeal.b, lVar.m, lVar.n, lVar.o, str, 4).b();
        } else {
            new com.appodeal.ads.utils.a(Appodeal.b, lVar.m, hVar.e(), lVar.o, str, 4).b();
        }
    }
}
