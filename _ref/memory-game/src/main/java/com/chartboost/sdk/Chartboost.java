package com.chartboost.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.c;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Libraries.l;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.impl.ad;
import java.util.HashSet;

public class Chartboost {
    protected boolean a;
    protected boolean b;
    protected l c;
    private CBImpressionActivity d;
    private com.chartboost.sdk.Model.a e;
    private boolean f;
    private final HashSet<Integer> g;
    private boolean h;
    private boolean i;
    private final ActivityLifecycleCallbacks j;
    private boolean k;
    private Runnable l;
    private final Runnable m;

    public enum CBFramework {
        CBFrameworkUnity("Unity"),
        CBFrameworkCorona("Corona"),
        CBFrameworkAir("AIR"),
        CBFrameworkGameSalad("GameSalad"),
        CBFrameworkCordova("Cordova"),
        CBFrameworkCocoonJS("CocoonJS"),
        CBFrameworkCocos2dx("Cocos2dx"),
        CBFrameworkPrime31Unreal("Prime31Unreal"),
        CBFrameworkWeeby("Weeby"),
        CBFrameworkOther("Other");
        
        private final String a;

        private CBFramework(String s) {
            this.a = s;
        }

        public String toString() {
            return this.a;
        }

        public boolean doesWrapperUseCustomShouldDisplayBehavior() {
            return this == CBFrameworkAir || this == CBFrameworkCocos2dx;
        }

        public boolean doesWrapperUseCustomBackgroundingBehavior() {
            return this == CBFrameworkAir;
        }
    }

    public enum CBMediation {
        CBMediationAdMarvel("AdMarvel"),
        CBMediationAdMob("AdMob"),
        CBMediationFuse("Fuse"),
        CBMediationFyber("Fyber"),
        CBMediationHeyZap("HeyZap"),
        CBMediationMoPub("MoPub"),
        CBMediationSupersonic("Supersonic"),
        CBMediationHyperMX("HyprMX"),
        CBMediationOther("Other");
        
        private final String a;

        private CBMediation(String s) {
            this.a = s;
        }

        public String toString() {
            return this.a;
        }
    }

    @TargetApi(14)
    class a implements ActivityLifecycleCallbacks {
        final /* synthetic */ Chartboost a;

        a(Chartboost chartboost) {
            this.a = chartboost;
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            CBLogging.a("Chartboost", "######## onActivityCreated callback called");
            if (!(activity instanceof CBImpressionActivity)) {
                this.a.c(activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityStarted callback called from CBImpressionactivity");
                this.a.a(activity);
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityStarted callback called from developer side");
            this.a.e(activity);
        }

        public void onActivityResumed(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityResumed callback called from CBImpressionactivity");
                this.a.a(l.a(activity));
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityResumed callback called from developer side");
            this.a.f(activity);
        }

        public void onActivityPaused(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityPaused callback called from CBImpressionactivity");
                this.a.b(l.a(activity));
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityPaused callback called from developer side");
            this.a.g(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityStopped callback called from CBImpressionactivity");
                this.a.c(l.a(activity));
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityStopped callback called from developer side");
            this.a.h(activity);
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityDestroyed callback called from CBImpressionactivity");
                this.a.b(activity);
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityDestroyed callback called from developer side");
            this.a.i(activity);
        }
    }

    private class b implements Runnable {
        final /* synthetic */ Chartboost a;
        private final int b;
        private final int c;
        private final int d;

        private a a() {
            return c.h();
        }

        private b(Chartboost chartboost) {
            int i = -1;
            this.a = chartboost;
            a a = a();
            this.b = chartboost.d == null ? -1 : chartboost.d.hashCode();
            this.c = chartboost.c == null ? -1 : chartboost.c.hashCode();
            if (a != null) {
                i = a.hashCode();
            }
            this.d = i;
        }

        public void run() {
            a a = a();
            if (this.a.c != null && this.a.c.hashCode() == this.c) {
                this.a.c = null;
            }
            if (a != null && a.hashCode() == this.d) {
                c.a(null);
            }
        }
    }

    private Chartboost(Activity app, String appId, String appSignature) {
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = new HashSet();
        this.a = true;
        this.b = false;
        this.h = true;
        this.i = false;
        this.k = false;
        this.c = null;
        this.m = new Runnable(this) {
            final /* synthetic */ Chartboost a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        };
        if (com.chartboost.sdk.impl.a.a().a(14)) {
            this.j = new a(this);
        } else {
            this.j = null;
        }
        c.a(app.getApplicationContext());
        c.b(appId);
        c.c(appSignature);
        this.c = l.a(app);
        f.h().a(c.x());
        f.n().f();
        this.l = new b();
        c.a();
        c.b(true);
    }

    public static void startWithAppId(final Activity activity, final String appId, final String appSignature) {
        a(new Runnable() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r18 = this;
                r1 = com.chartboost.sdk.f.q();
                if (r1 != 0) goto L_0x001d;
            L_0x0006:
                r16 = com.chartboost.sdk.Chartboost.class;
                monitor-enter(r16);
                r1 = com.chartboost.sdk.f.q();	 Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x0106;
            L_0x000f:
                r0 = r18;
                r1 = r1;	 Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x001e;
            L_0x0015:
                r1 = "Chartboost";
                r2 = "Activity object is null. Please pass a valid activity object";
                com.chartboost.sdk.Libraries.CBLogging.b(r1, r2);	 Catch:{ all -> 0x0031 }
                monitor-exit(r16);	 Catch:{ all -> 0x0031 }
            L_0x001d:
                return;
            L_0x001e:
                r0 = r18;
                r1 = r1;	 Catch:{ all -> 0x0031 }
                r1 = com.chartboost.sdk.c.b(r1);	 Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x0034;
            L_0x0028:
                r1 = "Chartboost";
                r2 = "Permissions not set correctly";
                com.chartboost.sdk.Libraries.CBLogging.b(r1, r2);	 Catch:{ all -> 0x0031 }
                monitor-exit(r16);	 Catch:{ all -> 0x0031 }
                goto L_0x001d;
            L_0x0031:
                r1 = move-exception;
                monitor-exit(r16);	 Catch:{ all -> 0x0031 }
                throw r1;
            L_0x0034:
                r0 = r18;
                r1 = r1;	 Catch:{ all -> 0x0031 }
                r1 = com.chartboost.sdk.c.c(r1);	 Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x0045;
            L_0x003e:
                r1 = "Chartboost";
                r2 = "CBImpression Activity not added in your manifest.xml";
                com.chartboost.sdk.Libraries.CBLogging.b(r1, r2);	 Catch:{ all -> 0x0031 }
            L_0x0045:
                r0 = r18;
                r1 = r2;	 Catch:{ all -> 0x0031 }
                r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x0059;
            L_0x004f:
                r0 = r18;
                r1 = r3;	 Catch:{ all -> 0x0031 }
                r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0031 }
                if (r1 == 0) goto L_0x0062;
            L_0x0059:
                r1 = "Chartboost";
                r2 = "AppId or AppSignature is null. Please pass a valid id's";
                com.chartboost.sdk.Libraries.CBLogging.b(r1, r2);	 Catch:{ all -> 0x0031 }
                monitor-exit(r16);	 Catch:{ all -> 0x0031 }
                goto L_0x001d;
            L_0x0062:
                r1 = com.chartboost.sdk.f.a();	 Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x00e7;
            L_0x0068:
                r6 = new com.chartboost.sdk.impl.an;	 Catch:{ all -> 0x0031 }
                r1 = new com.chartboost.sdk.impl.al;	 Catch:{ all -> 0x0031 }
                r1.<init>();	 Catch:{ all -> 0x0031 }
                r2 = 4;
                r6.<init>(r1, r2);	 Catch:{ all -> 0x0031 }
                com.chartboost.sdk.impl.u.b();	 Catch:{ Throwable -> 0x0109 }
                r6.a();	 Catch:{ Throwable -> 0x0109 }
                r0 = r18;
                r1 = r1;	 Catch:{ all -> 0x0031 }
                r1 = r1.getApplicationContext();	 Catch:{ all -> 0x0031 }
                r2 = "cbPrefs";
                r3 = 0;
                r15 = r1.getSharedPreferences(r2, r3);	 Catch:{ all -> 0x0031 }
                com.chartboost.sdk.Libraries.h.a(r1);	 Catch:{ all -> 0x0031 }
                r3 = new com.chartboost.sdk.InPlay.a;	 Catch:{ all -> 0x0031 }
                r3.<init>();	 Catch:{ all -> 0x0031 }
                r4 = new com.chartboost.sdk.impl.b;	 Catch:{ all -> 0x0031 }
                r4.<init>();	 Catch:{ all -> 0x0031 }
                r5 = new com.chartboost.sdk.impl.s;	 Catch:{ all -> 0x0031 }
                r5.<init>();	 Catch:{ all -> 0x0031 }
                r7 = new com.chartboost.sdk.impl.ac;	 Catch:{ all -> 0x0031 }
                r7.<init>();	 Catch:{ all -> 0x0031 }
                r8 = new com.chartboost.sdk.impl.as;	 Catch:{ all -> 0x0031 }
                r0 = r18;
                r2 = r2;	 Catch:{ all -> 0x0031 }
                r8.<init>(r1, r2);	 Catch:{ all -> 0x0031 }
                r1 = new com.chartboost.sdk.impl.ae;	 Catch:{ all -> 0x0031 }
                r1.<init>(r6, r7);	 Catch:{ all -> 0x0031 }
                r9 = new com.chartboost.sdk.impl.c;	 Catch:{ all -> 0x0031 }
                r9.<init>();	 Catch:{ all -> 0x0031 }
                r10 = new com.chartboost.sdk.Tracking.a;	 Catch:{ all -> 0x0031 }
                r10.<init>();	 Catch:{ all -> 0x0031 }
                r11 = new com.chartboost.sdk.g;	 Catch:{ all -> 0x0031 }
                r11.<init>();	 Catch:{ all -> 0x0031 }
                r12 = new com.chartboost.sdk.impl.ag;	 Catch:{ all -> 0x0031 }
                r2 = new com.chartboost.sdk.Libraries.h;	 Catch:{ all -> 0x0031 }
                r13 = 1;
                r2.<init>(r13);	 Catch:{ all -> 0x0031 }
                r12.<init>(r6, r2);	 Catch:{ all -> 0x0031 }
                r13 = new com.chartboost.sdk.b;	 Catch:{ all -> 0x0031 }
                r13.<init>(r6);	 Catch:{ all -> 0x0031 }
                r14 = new com.chartboost.sdk.j;	 Catch:{ all -> 0x0031 }
                r14.<init>(r6, r7);	 Catch:{ all -> 0x0031 }
                r2 = new com.chartboost.sdk.d$b;	 Catch:{ all -> 0x0031 }
                r2.<init>();	 Catch:{ all -> 0x0031 }
                r17 = new com.chartboost.sdk.impl.af;	 Catch:{ all -> 0x0031 }
                r0 = r17;
                r0.<init>(r2);	 Catch:{ all -> 0x0031 }
                r2 = new com.chartboost.sdk.d;	 Catch:{ all -> 0x0031 }
                r0 = r17;
                r2.<init>(r0);	 Catch:{ all -> 0x0031 }
                com.chartboost.sdk.f.a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);	 Catch:{ all -> 0x0031 }
            L_0x00e7:
                r1 = new com.chartboost.sdk.Chartboost;	 Catch:{ all -> 0x0031 }
                r0 = r18;
                r2 = r1;	 Catch:{ all -> 0x0031 }
                r0 = r18;
                r3 = r2;	 Catch:{ all -> 0x0031 }
                r0 = r18;
                r4 = r3;	 Catch:{ all -> 0x0031 }
                r5 = 0;
                r1.<init>(r2, r3, r4);	 Catch:{ all -> 0x0031 }
                com.chartboost.sdk.f.a(r1);	 Catch:{ all -> 0x0031 }
                r2 = new com.chartboost.sdk.Chartboost$1$1;	 Catch:{ all -> 0x0031 }
                r0 = r18;
                r2.<init>(r0, r1);	 Catch:{ all -> 0x0031 }
                com.chartboost.sdk.c.a(r2);	 Catch:{ all -> 0x0031 }
            L_0x0106:
                monitor-exit(r16);	 Catch:{ all -> 0x0031 }
                goto L_0x001d;
            L_0x0109:
                r1 = move-exception;
                r2 = "Chartboost";
                r3 = "Unable to start threads";
                com.chartboost.sdk.Libraries.CBLogging.b(r2, r3, r1);	 Catch:{ all -> 0x0031 }
                monitor-exit(r16);	 Catch:{ all -> 0x0031 }
                goto L_0x001d;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Chartboost.1.run():void");
            }
        });
    }

    public static void onCreate(Activity activity) {
        Chartboost q = f.q();
        if (q != null && !q.i()) {
            q.c(activity);
        }
    }

    private void c(final Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    this.b.d(activity);
                }
            });
        }
    }

    private void d(Activity activity) {
        if (!(this.c == null || this.c.b(activity) || !o())) {
            d(this.c);
            b(this.c, false);
        }
        com.chartboost.sdk.impl.a.a().a.removeCallbacks(this.l);
        this.c = l.a(activity);
        f.b().c();
    }

    public static void onStart(Activity activity) {
        Chartboost q = f.q();
        if (q != null && !q.i()) {
            q.e(activity);
        }
    }

    private void e(final Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    com.chartboost.sdk.impl.a.a().a.removeCallbacks(this.b.l);
                    if (!(this.b.c == null || this.b.c.b(activity) || !this.b.o())) {
                        this.b.d(this.b.c);
                        this.b.b(this.b.c, false);
                    }
                    this.b.a(activity, true);
                    this.b.c = l.a(activity);
                    this.b.a();
                    if (c.b) {
                        c.b(activity);
                    }
                    if (!this.b.h) {
                        g l = f.l();
                        if (!(l == null || l.c())) {
                            f.n().f();
                        }
                        f.n().a(h.b());
                        f.e().c();
                        f.j().c();
                        f.e().g();
                        f.j().g();
                        f.f().g();
                    }
                    this.b.a(activity);
                }
            });
        }
    }

    protected void a(Activity activity) {
        boolean z;
        f.h().b(c.x());
        if (!(activity instanceof CBImpressionActivity)) {
            f.b().d();
        }
        c.a(activity.getApplicationContext());
        if (activity instanceof CBImpressionActivity) {
            a((CBImpressionActivity) activity);
        } else {
            this.c = l.a(activity);
            b(this.c, true);
        }
        com.chartboost.sdk.impl.a.a().a.removeCallbacks(this.l);
        if (c.b() == null || !c.b().doesWrapperUseCustomBackgroundingBehavior()) {
            z = false;
        } else {
            z = true;
        }
        if (activity == null) {
            return;
        }
        if (z || j(activity)) {
            a(l.a(activity), true);
            if (activity instanceof CBImpressionActivity) {
                this.k = false;
            }
            d c = f.c();
            if (c.a(activity, this.e)) {
                this.e = null;
            }
            com.chartboost.sdk.Model.a c2 = c.c();
            if (c2 != null) {
                c2.u();
            }
        }
    }

    protected void a() {
        if (c.x() == null) {
            CBLogging.b("Chartboost", "The context must be set through the Chartboost method onCreate() before calling startSession().");
        } else {
            l();
        }
    }

    protected void b() {
        if (c.i()) {
            com.chartboost.sdk.impl.a.a().a.postDelayed(this.m, 500);
        } else {
            c();
        }
    }

    private void l() {
        f.k().c();
        com.chartboost.sdk.Tracking.a.a();
        if (!this.h) {
            c.a(new com.chartboost.sdk.c.a(this) {
                final /* synthetic */ Chartboost a;

                {
                    this.a = r1;
                }

                public void a() {
                    ad adVar = new ad("api/install");
                    adVar.a(true);
                    adVar.a(g.a("status", com.chartboost.sdk.Libraries.a.a));
                    adVar.t();
                    Chartboost.m();
                }
            });
        }
    }

    private static void m() {
        try {
            if (c.G().booleanValue()) {
                f.n().a();
            } else if (c.M()) {
                f.o().a();
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(Chartboost.class, "executePrefetch", e);
        }
    }

    protected void c() {
        f.k().b();
    }

    public static void onResume(Activity activity) {
        Chartboost q = f.q();
        if (q != null && !q.i()) {
            q.f(activity);
        }
    }

    private void f(final Activity activity) {
        if (c.t() && c.a(activity)) {
            if (!this.b) {
                if (c.h() != null) {
                    c.h().didInitialize();
                }
                this.b = true;
            }
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    l a = l.a(activity);
                    if (this.b.f(a)) {
                        this.b.a(a);
                    } else if (CBUtility.a(CBFramework.CBFrameworkUnity)) {
                        this.b.a();
                    }
                }
            });
        }
    }

    protected void a(l lVar) {
        com.chartboost.sdk.Model.a c = f.c().c();
        if (CBUtility.a(CBFramework.CBFrameworkUnity)) {
            a();
        }
        if (c != null) {
            c.t();
        }
    }

    public static void onPause(Activity activity) {
        Chartboost q = f.q();
        if (q != null && !q.i()) {
            q.g(activity);
        }
    }

    private void g(final Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    l a = l.a(activity);
                    if (this.b.f(a)) {
                        this.b.b(a);
                    }
                }
            });
        }
    }

    protected void b(l lVar) {
        com.chartboost.sdk.Model.a c = f.c().c();
        if (c != null) {
            c.v();
        }
    }

    public static void onStop(Activity activity) {
        Chartboost q = f.q();
        if (q != null && !q.i()) {
            q.h(activity);
        }
    }

    private void h(final Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    l a = l.a(activity);
                    if (this.b.f(a)) {
                        this.b.d(a);
                    }
                }
            });
        }
    }

    private void d(l lVar) {
        if (!c.i()) {
            c(lVar);
        }
        if (!(lVar.get() instanceof CBImpressionActivity)) {
            b(lVar, false);
        }
        b();
    }

    protected void c(l lVar) {
        com.chartboost.sdk.Model.a c = f.c().c();
        if (c != null && c.a == com.chartboost.sdk.Model.a.b.NATIVE) {
            g h = h();
            if (e(lVar) && h != null) {
                h.c(c);
                this.e = c;
                a(lVar, false);
            }
            if (!(lVar.get() instanceof CBImpressionActivity)) {
                b(lVar, false);
            }
        }
        f.h().c(c.x());
        if (!(lVar.get() instanceof CBImpressionActivity)) {
            f.b().e();
        }
    }

    public static boolean onBackPressed() {
        Chartboost q = f.q();
        if (q == null) {
            return false;
        }
        return q.n();
    }

    private boolean n() {
        if (!c.t()) {
            return false;
        }
        if (this.c == null) {
            CBLogging.b("Chartboost", "The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
            return false;
        } else if (!c.i()) {
            return d();
        } else {
            if (!this.k) {
                return false;
            }
            this.k = false;
            d();
            return true;
        }
    }

    protected boolean d() {
        return e();
    }

    protected boolean e() {
        final d c = f.c();
        com.chartboost.sdk.Model.a c2 = c.c();
        if (c2 == null || c2.c != e.DISPLAYED) {
            final g h = h();
            if (h == null || !h.b()) {
                return false;
            }
            a(new Runnable(this) {
                final /* synthetic */ Chartboost c;

                public void run() {
                    h.a(c.c(), true);
                }
            });
            return true;
        } else if (c2.s()) {
            return true;
        } else {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    c.b();
                }
            });
            return true;
        }
    }

    public static void onDestroy(Activity activity) {
        Chartboost q = f.q();
        if (q != null && !q.i()) {
            q.i(activity);
        }
    }

    private void i(final Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    if (this.b.c == null || this.b.c.b(activity)) {
                        this.b.l = new b();
                        this.b.l.run();
                    }
                    this.b.b(activity);
                }
            });
        }
    }

    protected void b(Activity activity) {
        a(l.a(activity), false);
        com.chartboost.sdk.Model.a c = f.c().c();
        if (c == null && activity == this.d && this.e != null) {
            c = this.e;
        }
        g h = h();
        if (!(h == null || c == null)) {
            h.d(c);
        }
        this.e = null;
    }

    public static boolean hasRewardedVideo(String location) {
        if (c.r()) {
            return f.j().d(location);
        }
        return false;
    }

    public static void cacheRewardedVideo(final String location) {
        if (c.r() && p()) {
            Runnable anonymousClass4 = new Runnable() {
                public void run() {
                    if (com.chartboost.sdk.impl.a.a().a(location)) {
                        CBLogging.b("Chartboost", "cacheRewardedVideo location cannot be empty");
                        if (c.h() != null) {
                            c.h().didFailToLoadRewardedVideo(location, CBImpressionError.INVALID_LOCATION);
                            return;
                        }
                        return;
                    }
                    f.j().b(location);
                }
            };
            if (c.G().booleanValue() && c.I()) {
                a(anonymousClass4);
            } else if (c.M() && c.O()) {
                a(anonymousClass4);
            } else {
                c.h().didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void showRewardedVideo(final String location) {
        if (c.r() && p()) {
            Runnable anonymousClass5 = new Runnable() {
                public void run() {
                    if (com.chartboost.sdk.impl.a.a().a(location)) {
                        CBLogging.b("Chartboost", "showRewardedVideo location cannot be empty");
                        if (c.h() != null) {
                            c.h().didFailToLoadRewardedVideo(location, CBImpressionError.INVALID_LOCATION);
                            return;
                        }
                        return;
                    }
                    f.j().a(location);
                }
            };
            if (c.G().booleanValue() && c.I()) {
                a(anonymousClass5);
            } else if (c.M() && c.O()) {
                a(anonymousClass5);
            } else {
                c.h().didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static boolean hasInterstitial(String location) {
        if (c.r()) {
            return f.e().d(location);
        }
        return false;
    }

    public static void cacheInterstitial(final String location) {
        if (c.r() && p()) {
            Runnable anonymousClass6 = new Runnable() {
                public void run() {
                    if (com.chartboost.sdk.impl.a.a().a(location)) {
                        CBLogging.b("Chartboost", "cacheInterstitial location cannot be empty");
                        if (c.h() != null) {
                            c.h().didFailToLoadInterstitial(location, CBImpressionError.INVALID_LOCATION);
                            return;
                        }
                        return;
                    }
                    f.e().b(location);
                }
            };
            if (c.G().booleanValue() && c.H()) {
                a(anonymousClass6);
            } else if (c.M() && c.N()) {
                a(anonymousClass6);
            } else {
                c.h().didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void showInterstitial(final String location) {
        if (c.r() && p()) {
            Runnable anonymousClass7 = new Runnable() {
                public void run() {
                    if (com.chartboost.sdk.impl.a.a().a(location)) {
                        CBLogging.b("Chartboost", "showInterstitial location cannot be empty");
                        if (c.h() != null) {
                            c.h().didFailToLoadInterstitial(location, CBImpressionError.INVALID_LOCATION);
                            return;
                        }
                        return;
                    }
                    f.e().a(location);
                }
            };
            if (c.G().booleanValue() && c.H()) {
                a(anonymousClass7);
            } else if (c.M() && c.N()) {
                a(anonymousClass7);
            } else {
                c.h().didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void closeImpression() {
        a(new Runnable() {
            public void run() {
                if (c.r() && f.q() != null) {
                    f.q().e();
                }
            }
        });
    }

    public static boolean hasMoreApps(String location) {
        if (c.r()) {
            return f.f().d(location);
        }
        return false;
    }

    public static void cacheMoreApps(final String location) {
        if (c.r() && p()) {
            Runnable anonymousClass9 = new Runnable() {
                public void run() {
                    if (com.chartboost.sdk.impl.a.a().a(location)) {
                        CBLogging.b("Chartboost", "cacheMoreApps location cannot be empty");
                        if (c.h() != null) {
                            c.h().didFailToLoadMoreApps(location, CBImpressionError.INVALID_LOCATION);
                            return;
                        }
                        return;
                    }
                    f.f().b(location);
                }
            };
            if (c.G().booleanValue() && c.J()) {
                a(anonymousClass9);
            } else if (c.M() && c.P()) {
                a(anonymousClass9);
            } else {
                c.h().didFailToLoadMoreApps(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void showMoreApps(final String location) {
        if (c.r() && p()) {
            Runnable anonymousClass10 = new Runnable() {
                public void run() {
                    if (com.chartboost.sdk.impl.a.a().a(location)) {
                        CBLogging.b("Chartboost", "showMoreApps location cannot be empty");
                        if (c.h() != null) {
                            c.h().didFailToLoadMoreApps(location, CBImpressionError.INVALID_LOCATION);
                            return;
                        }
                        return;
                    }
                    f.f().a(location);
                }
            };
            if (c.G().booleanValue() && c.J()) {
                a(anonymousClass10);
            } else if (c.M() && c.P()) {
                a(anonymousClass10);
            } else {
                c.h().didFailToLoadMoreApps(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static boolean isAnyViewVisible() {
        Chartboost q = f.q();
        if (q == null) {
            return false;
        }
        g h = q.h();
        if (h != null) {
            return h.c();
        }
        return false;
    }

    public static void setMediation(final CBMediation mediation, final String libraryVersion) {
        a(new Runnable() {
            public void run() {
                c.a(mediation, libraryVersion);
            }
        });
    }

    public static void setFramework(final CBFramework framework, final String version) {
        a(new Runnable() {
            public void run() {
                c.a(framework, version);
            }
        });
    }

    @Deprecated
    public static void setFrameworkVersion(final String version) {
        a(new Runnable() {
            public void run() {
                c.a(version);
            }
        });
    }

    public static void setChartboostWrapperVersion(final String version) {
        a(new Runnable() {
            public void run() {
                c.a(version);
            }
        });
    }

    public static String getCustomId() {
        return c.p();
    }

    public static void setCustomId(final String customID) {
        a(new Runnable() {
            public void run() {
                c.d(customID);
            }
        });
    }

    public static void setLoggingLevel(final Level lvl) {
        a(new Runnable() {
            public void run() {
                c.a(lvl);
            }
        });
    }

    public static Level getLoggingLevel() {
        return c.o();
    }

    public static a getDelegate() {
        return c.h();
    }

    public static void setDelegate(final ChartboostDelegate delegate) {
        a(new Runnable() {
            public void run() {
                c.a(delegate);
            }
        });
    }

    public static boolean getAutoCacheAds() {
        return c.k();
    }

    public static void setAutoCacheAds(final boolean autoCacheAds) {
        a(new Runnable() {
            public void run() {
                c.a(autoCacheAds);
            }
        });
    }

    public static void setShouldRequestInterstitialsInFirstSession(final boolean shouldRequest) {
        a(new Runnable() {
            public void run() {
                c.c(shouldRequest);
            }
        });
    }

    public static void setShouldDisplayLoadingViewForMoreApps(final boolean shouldDisplay) {
        a(new Runnable() {
            public void run() {
                c.d(shouldDisplay);
            }
        });
    }

    public static void setShouldPrefetchVideoContent(final boolean shouldPrefetch) {
        if (c.r()) {
            a(new Runnable() {
                public void run() {
                    c.e(shouldPrefetch);
                    boolean booleanValue = c.G().booleanValue();
                    if (shouldPrefetch && Chartboost.p()) {
                        if (booleanValue) {
                            f.n().a();
                        } else {
                            f.o().a();
                        }
                    } else if (booleanValue) {
                        f.n().d();
                    } else {
                        f.o().d();
                    }
                }
            });
        }
    }

    public static String getSDKVersion() {
        return "6.5.1";
    }

    public static void setShouldHideSystemUI(Boolean hide) {
        c.a(hide);
    }

    public static boolean isWebViewEnabled() {
        return c.G().booleanValue();
    }

    @TargetApi(14)
    public static void setActivityCallbacks(boolean enabled) {
        Chartboost q = f.q();
        if (q != null) {
            Activity hostActivity = q.getHostActivity();
            if (hostActivity != null) {
                ActivityLifecycleCallbacks activityLifecycleCallbacks = q.j;
                if (activityLifecycleCallbacks == null) {
                    return;
                }
                if (!q.i && enabled) {
                    hostActivity.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
                    q.i = true;
                } else if (q.i && !enabled) {
                    hostActivity.getApplication().unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
                    q.i = false;
                }
            }
        }
    }

    protected Activity f() {
        if (c.i()) {
            return this.d;
        }
        return getHostActivity();
    }

    private boolean j(Activity activity) {
        if (c.i()) {
            if (this.d == activity) {
                return true;
            }
            return false;
        } else if (this.c != null) {
            return this.c.b(activity);
        } else {
            if (activity != null) {
                return false;
            }
            return true;
        }
    }

    private boolean e(l lVar) {
        if (c.i()) {
            if (lVar != null) {
                return lVar.b(this.d);
            }
            if (this.d == null) {
                return true;
            }
            return false;
        } else if (this.c != null) {
            return this.c.a(lVar);
        } else {
            if (lVar != null) {
                return false;
            }
            return true;
        }
    }

    protected void a(CBImpressionActivity cBImpressionActivity) {
        if (!this.f) {
            c.a(cBImpressionActivity.getApplicationContext());
            this.d = cBImpressionActivity;
            this.f = true;
        }
        com.chartboost.sdk.impl.a.a().a.removeCallbacks(this.l);
    }

    protected void g() {
        if (this.f) {
            this.d = null;
            this.f = false;
        }
    }

    protected void a(com.chartboost.sdk.Model.a aVar) {
        boolean z = false;
        g h = h();
        if (h != null && h.c()) {
            aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        } else if (!c.i()) {
            h = h();
            if (h == null || !o()) {
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else {
                h.a(aVar);
            }
        } else if (this.f) {
            if (f() != null && h != null) {
                h.a(aVar);
            } else if (f() == null) {
                CBLogging.b("Chartboost", "Activity not found. Cannot display the view");
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else {
                CBLogging.b("Chartboost", "Missing view controller to manage the impression activity");
                aVar.a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            }
        } else if (o()) {
            Context hostActivity = getHostActivity();
            if (hostActivity == null) {
                CBLogging.b("Chartboost", "Failed to display impression as the host activity reference has been lost!");
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else if (this.e == null || this.e == aVar) {
                this.e = aVar;
                Intent intent = new Intent(hostActivity, CBImpressionActivity.class);
                boolean z2 = (hostActivity.getWindow().getAttributes().flags & 1024) != 0;
                boolean z3;
                if ((hostActivity.getWindow().getAttributes().flags & 2048) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                String str = "paramFullscreen";
                if (z2 && !r3) {
                    z = true;
                }
                intent.putExtra(str, z);
                intent.putExtra("isChartboost", true);
                try {
                    hostActivity.startActivity(intent);
                    this.k = true;
                } catch (ActivityNotFoundException e) {
                    CBLogging.b("Chartboost", "Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
                    this.e = null;
                    CBLogging.b("Chartboost", "CBImpression Activity is missing in the manifest");
                    aVar.a(CBImpressionError.ACTIVITY_MISSING_IN_MANIFEST);
                    f.k().a(aVar.q().e(), aVar.e, aVar.p(), CBImpressionError.ACTIVITY_MISSING_IN_MANIFEST);
                }
            } else {
                aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            }
        } else {
            aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
        }
    }

    protected Activity getHostActivity() {
        return this.c != null ? (Activity) this.c.get() : null;
    }

    protected static void a(Runnable runnable) {
        com.chartboost.sdk.impl.a a = com.chartboost.sdk.impl.a.a();
        if (a.f()) {
            runnable.run();
        } else {
            a.a.post(runnable);
        }
    }

    protected Context getValidContext() {
        return this.c != null ? this.c.b() : c.x();
    }

    private static void a(l lVar, boolean z) {
    }

    private boolean o() {
        return f(this.c);
    }

    private boolean f(l lVar) {
        if (lVar == null) {
            return false;
        }
        return this.g.contains(Integer.valueOf(lVar.a()));
    }

    private void a(Activity activity, boolean z) {
        if (activity != null) {
            a(activity.hashCode(), z);
        }
    }

    private void b(l lVar, boolean z) {
        if (lVar != null) {
            a(lVar.a(), z);
        }
    }

    private void a(int i, boolean z) {
        if (z) {
            this.g.add(Integer.valueOf(i));
        } else {
            this.g.remove(Integer.valueOf(i));
        }
    }

    protected g h() {
        if (f() == null) {
            return null;
        }
        return f.l();
    }

    private static boolean p() {
        if (!c.S().booleanValue()) {
            return true;
        }
        try {
            throw new Exception("Chartboost Integration Warning: your account has been set to advertiser only. This function has been disabled. Please contact support if you expect this call to function.");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void showInterstitialAIR(final String location, final boolean show) {
        if (c.r() && p()) {
            Runnable anonymousClass24 = new Runnable() {
                public void run() {
                    f.e().b(location, show);
                }
            };
            if (c.G().booleanValue() && c.H()) {
                a(anonymousClass24);
            } else if (c.M() && c.N()) {
                a(anonymousClass24);
            } else {
                c.h().didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    private static void showMoreAppsAIR(final String location, final boolean show) {
        if (c.r() && p()) {
            Runnable anonymousClass25 = new Runnable() {
                public void run() {
                    f.f().b(location, show);
                }
            };
            if (c.G().booleanValue() && c.J()) {
                a(anonymousClass25);
            } else if (c.M() && c.P()) {
                a(anonymousClass25);
            } else {
                c.h().didFailToLoadMoreApps(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    private static void showRewardedVideoAIR(final String location, final boolean show) {
        if (c.r() && p()) {
            Runnable anonymousClass26 = new Runnable() {
                public void run() {
                    f.j().b(location, show);
                }
            };
            if (c.G().booleanValue() && c.I()) {
                a(anonymousClass26);
            } else if (c.M() && c.O()) {
                a(anonymousClass26);
            } else {
                c.h().didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    private static void forwardTouchEventsAIR(final boolean forward) {
        final Chartboost q = f.q();
        if (q != null) {
            a(new Runnable() {
                public void run() {
                    if (q.d == null) {
                        return;
                    }
                    if (forward) {
                        q.d.forwardTouchEvents(q.getHostActivity());
                    } else {
                        q.d.forwardTouchEvents(null);
                    }
                }
            });
        }
    }

    protected boolean i() {
        return this.i;
    }
}
