package com.flurry.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.flurry.android.FlurryEventRecordStatus;
import com.flurry.sdk.lq.a;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class jh implements a {
    static final String a = jh.class.getSimpleName();
    static int b = 100;
    static int c = 10;
    static int d = 1000;
    static int e = 160000;
    static int f = 50;
    private String A;
    private int B = -1;
    private boolean C = true;
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private final kh<ll> G = new kh<ll>(this) {
        final /* synthetic */ jh a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            ll llVar = (ll) kgVar;
            if (this.a.g == null || llVar.b == this.a.g.get()) {
                jh jhVar;
                switch (AnonymousClass9.a[llVar.c - 1]) {
                    case 1:
                        a aVar = this.a;
                        Context context = (Context) llVar.a.get();
                        aVar.g = new WeakReference(llVar.b);
                        lq a = lp.a();
                        aVar.k = ((Boolean) a.a("LogEvents")).booleanValue();
                        a.a("LogEvents", aVar);
                        km.a(4, jh.a, "initSettings, LogEvents = " + aVar.k);
                        aVar.l = (String) a.a("UserId");
                        a.a("UserId", aVar);
                        km.a(4, jh.a, "initSettings, UserId = " + aVar.l);
                        aVar.m = ((Byte) a.a("Gender")).byteValue();
                        a.a("Gender", aVar);
                        km.a(4, jh.a, "initSettings, Gender = " + aVar.m);
                        aVar.n = (Long) a.a("Age");
                        a.a("Age", aVar);
                        km.a(4, jh.a, "initSettings, BirthDate = " + aVar.n);
                        aVar.o = ((Boolean) a.a("analyticsEnabled")).booleanValue();
                        a.a("analyticsEnabled", aVar);
                        km.a(4, jh.a, "initSettings, AnalyticsEnabled = " + aVar.o);
                        aVar.h = context.getFileStreamPath(".flurryagent." + Integer.toString(jy.a().d.hashCode(), 16));
                        aVar.i = new kf(context.getFileStreamPath(".yflurryreport." + Long.toString(ly.i(jy.a().d), 16)), ".yflurryreport.", 1, new lj<List<jf>>(aVar) {
                            final /* synthetic */ jh a;

                            {
                                this.a = r1;
                            }

                            public final lg<List<jf>> a(int i) {
                                return new lf(new jf.a());
                            }
                        });
                        aVar.a(context);
                        aVar.a(true);
                        if (hr.a().a != null) {
                            jy.a().b(new ma(aVar) {
                                final /* synthetic */ jh a;

                                {
                                    this.a = r1;
                                }

                                public final void a() {
                                    hr.a().a.a();
                                }
                            });
                        }
                        jy.a().b(new ma(aVar) {
                            final /* synthetic */ jh a;

                            {
                                this.a = r1;
                            }

                            public final void a() {
                                this.a.e();
                            }
                        });
                        jy.a().b(new ma(aVar) {
                            final /* synthetic */ jh a;

                            {
                                this.a = r1;
                            }

                            public final void a() {
                                jh.d(this.a);
                            }
                        });
                        if (jl.a().b()) {
                            jy.a().b(new ma(aVar) {
                                final /* synthetic */ jh a;

                                {
                                    this.a = r1;
                                }

                                public final void a() {
                                    jh jhVar = this.a;
                                    jk.a();
                                    jhVar.a(true, jk.d());
                                }
                            });
                            return;
                        } else {
                            ki.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", aVar.p);
                            return;
                        }
                    case 2:
                        jhVar = this.a;
                        llVar.a.get();
                        jhVar.b();
                        return;
                    case 3:
                        jhVar = this.a;
                        llVar.a.get();
                        jhVar.c();
                        return;
                    case 4:
                        ki.a().b("com.flurry.android.sdk.FlurrySessionEvent", this.a.G);
                        this.a.a(llVar.d);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    WeakReference<lk> g;
    File h;
    kf<List<jf>> i;
    public boolean j;
    boolean k;
    String l;
    byte m;
    Long n;
    boolean o = true;
    final kh<jm> p = new kh<jm>(this) {
        final /* synthetic */ jh a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            jy.a().b(new ma(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public final void a() {
                    jh jhVar = this.a.a;
                    jk.a();
                    jhVar.a(true, jk.d());
                }
            });
        }
    };
    private final AtomicInteger q = new AtomicInteger(0);
    private final AtomicInteger r = new AtomicInteger(0);
    private final List<jf> s = new ArrayList();
    private final Map<String, List<String>> t = new HashMap();
    private final Map<String, String> u = new HashMap();
    private final Map<String, jb> v = new HashMap();
    private final List<jc> w = new ArrayList();
    private final List<ja> x = new ArrayList();
    private final hs y = new hs();
    private long z;

    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] a = new int[ll.a.a().length];

        static {
            try {
                a[ll.a.a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ll.a.c - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ll.a.d - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ll.a.e - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public jh() {
        ki.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.G);
    }

    final void a(Context context) {
        if (context instanceof Activity) {
            Bundle extras = ((Activity) context).getIntent().getExtras();
            if (extras != null) {
                km.a(3, a, "Launch Options Bundle is present " + extras.toString());
                for (String str : extras.keySet()) {
                    if (str != null) {
                        Object obj = extras.get(str);
                        String obj2 = obj != null ? obj.toString() : Constants.NULL_VERSION_ID;
                        this.t.put(str, Collections.singletonList(obj2));
                        km.a(3, a, "Launch options Key: " + str + ". Its value: " + obj2);
                    }
                }
            }
        }
    }

    @TargetApi(18)
    final void a(boolean z) {
        boolean z2;
        Exception exception;
        int i;
        Object obj;
        float f;
        int i2 = -1;
        if (z) {
            this.u.put("boot.time", Long.toString(System.currentTimeMillis() - SystemClock.elapsedRealtime()));
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (VERSION.SDK_INT >= 18) {
                this.u.put("disk.size.total.internal", Long.toString(statFs.getAvailableBlocksLong()));
                this.u.put("disk.size.available.internal", Long.toString(statFs.getAvailableBlocksLong()));
                this.u.put("disk.size.total.external", Long.toString(statFs2.getAvailableBlocksLong()));
                this.u.put("disk.size.available.external", Long.toString(statFs2.getAvailableBlocksLong()));
            } else {
                this.u.put("disk.size.total.internal", Long.toString((long) statFs.getAvailableBlocks()));
                this.u.put("disk.size.available.internal", Long.toString((long) statFs.getAvailableBlocks()));
                this.u.put("disk.size.total.external", Long.toString((long) statFs2.getAvailableBlocks()));
                this.u.put("disk.size.available.external", Long.toString((long) statFs2.getAvailableBlocks()));
            }
            js.a();
            this.u.put("carrier.name", js.b());
            js.a();
            this.u.put("carrier.details", js.c());
        }
        ActivityManager activityManager = (ActivityManager) jy.a().a.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        this.u.put("memory.available" + (z ? ".start" : ".end"), Long.toString(memoryInfo.availMem));
        if (VERSION.SDK_INT >= 16) {
            this.u.put("memory.total" + (z ? ".start" : ".end"), Long.toString(memoryInfo.availMem));
        }
        try {
            Intent registerReceiver = jy.a().a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                int intExtra;
                int intExtra2 = registerReceiver.getIntExtra("status", -1);
                z2 = intExtra2 == 2 || intExtra2 == 5;
                try {
                    intExtra = registerReceiver.getIntExtra("level", -1);
                } catch (Exception e) {
                    exception = e;
                    i = -1;
                    km.a(5, a, "Error getting battery status: " + obj);
                    i2 = i;
                    i = -1;
                    f = ((float) i2) / ((float) i);
                    this.u.put("battery.charging" + (z ? ".end" : ".start"), Boolean.toString(z2));
                    this.u.put("battery.remaining" + (z ? ".end" : ".start"), Float.toString(f));
                }
                try {
                    i = registerReceiver.getIntExtra("scale", -1);
                    i2 = intExtra;
                } catch (Exception e2) {
                    Exception exception2 = e2;
                    i = intExtra;
                    exception = exception2;
                    km.a(5, a, "Error getting battery status: " + obj);
                    i2 = i;
                    i = -1;
                    f = ((float) i2) / ((float) i);
                    if (z) {
                    }
                    this.u.put("battery.charging" + (z ? ".end" : ".start"), Boolean.toString(z2));
                    if (z) {
                    }
                    this.u.put("battery.remaining" + (z ? ".end" : ".start"), Float.toString(f));
                }
            }
            z2 = false;
            i = -1;
        } catch (Exception e3) {
            obj = e3;
            z2 = false;
            i = -1;
            km.a(5, a, "Error getting battery status: " + obj);
            i2 = i;
            i = -1;
            f = ((float) i2) / ((float) i);
            if (z) {
            }
            this.u.put("battery.charging" + (z ? ".end" : ".start"), Boolean.toString(z2));
            if (z) {
            }
            this.u.put("battery.remaining" + (z ? ".end" : ".start"), Float.toString(f));
        }
        f = ((float) i2) / ((float) i);
        if (z) {
        }
        this.u.put("battery.charging" + (z ? ".end" : ".start"), Boolean.toString(z2));
        if (z) {
        }
        this.u.put("battery.remaining" + (z ? ".end" : ".start"), Float.toString(f));
    }

    private synchronized void e() {
        km.a(4, a, "Loading persistent session report data.");
        List list = (List) this.i.a();
        if (list != null) {
            this.s.addAll(list);
        } else if (this.h.exists()) {
            km.a(4, a, "Legacy persistent agent data found, converting.");
            ji a = hu.a(this.h);
            if (a != null) {
                boolean z = a.b;
                long j = a.c;
                if (j <= 0) {
                    jk.a();
                    j = jk.d();
                }
                this.j = z;
                this.z = j;
                f();
                Collection unmodifiableList = Collections.unmodifiableList(a.a);
                if (unmodifiableList != null) {
                    this.s.addAll(unmodifiableList);
                }
            }
            this.h.delete();
            a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean r21, long r22) {
        /*
        r20 = this;
        monitor-enter(r20);
        r0 = r20;
        r2 = r0.o;	 Catch:{ all -> 0x008c }
        if (r2 != 0) goto L_0x0011;
    L_0x0007:
        r2 = 3;
        r3 = a;	 Catch:{ all -> 0x008c }
        r4 = "Analytics disabled, not sending agent report.";
        com.flurry.sdk.km.a(r2, r3, r4);	 Catch:{ all -> 0x008c }
    L_0x000f:
        monitor-exit(r20);
        return;
    L_0x0011:
        if (r21 != 0) goto L_0x001d;
    L_0x0013:
        r0 = r20;
        r2 = r0.s;	 Catch:{ all -> 0x008c }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x008c }
        if (r2 != 0) goto L_0x000f;
    L_0x001d:
        r2 = 3;
        r3 = a;	 Catch:{ all -> 0x008c }
        r4 = "generating agent report";
        com.flurry.sdk.km.a(r2, r3, r4);	 Catch:{ all -> 0x008c }
        r19 = 0;
        r3 = new com.flurry.sdk.jd;	 Catch:{ Exception -> 0x008f }
        r2 = com.flurry.sdk.jy.a();	 Catch:{ Exception -> 0x008f }
        r4 = r2.d;	 Catch:{ Exception -> 0x008f }
        r2 = com.flurry.sdk.ju.a();	 Catch:{ Exception -> 0x008f }
        r5 = r2.g();	 Catch:{ Exception -> 0x008f }
        r0 = r20;
        r6 = r0.j;	 Catch:{ Exception -> 0x008f }
        r2 = com.flurry.sdk.jl.a();	 Catch:{ Exception -> 0x008f }
        r7 = r2.c();	 Catch:{ Exception -> 0x008f }
        r0 = r20;
        r8 = r0.z;	 Catch:{ Exception -> 0x008f }
        r0 = r20;
        r12 = r0.s;	 Catch:{ Exception -> 0x008f }
        r2 = com.flurry.sdk.jl.a();	 Catch:{ Exception -> 0x008f }
        r2 = r2.a;	 Catch:{ Exception -> 0x008f }
        r13 = java.util.Collections.unmodifiableMap(r2);	 Catch:{ Exception -> 0x008f }
        r0 = r20;
        r2 = r0.y;	 Catch:{ Exception -> 0x008f }
        r14 = r2.a();	 Catch:{ Exception -> 0x008f }
        r0 = r20;
        r15 = r0.t;	 Catch:{ Exception -> 0x008f }
        r2 = com.flurry.sdk.ka.a();	 Catch:{ Exception -> 0x008f }
        r16 = r2.b();	 Catch:{ Exception -> 0x008f }
        r17 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x008f }
        r10 = r22;
        r3.<init>(r4, r5, r6, r7, r8, r10, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x008f }
        r2 = r3.a;	 Catch:{ Exception -> 0x008f }
    L_0x0074:
        if (r2 != 0) goto L_0x00a7;
    L_0x0076:
        r2 = a;	 Catch:{ all -> 0x008c }
        r3 = "Error generating report";
        com.flurry.sdk.km.e(r2, r3);	 Catch:{ all -> 0x008c }
    L_0x007d:
        r0 = r20;
        r2 = r0.s;	 Catch:{ all -> 0x008c }
        r2.clear();	 Catch:{ all -> 0x008c }
        r0 = r20;
        r2 = r0.i;	 Catch:{ all -> 0x008c }
        r2.b();	 Catch:{ all -> 0x008c }
        goto L_0x000f;
    L_0x008c:
        r2 = move-exception;
        monitor-exit(r20);
        throw r2;
    L_0x008f:
        r2 = move-exception;
        r3 = a;	 Catch:{ all -> 0x008c }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008c }
        r5 = "Exception while generating report: ";
        r4.<init>(r5);	 Catch:{ all -> 0x008c }
        r2 = r4.append(r2);	 Catch:{ all -> 0x008c }
        r2 = r2.toString();	 Catch:{ all -> 0x008c }
        com.flurry.sdk.km.e(r3, r2);	 Catch:{ all -> 0x008c }
        r2 = r19;
        goto L_0x0074;
    L_0x00a7:
        r3 = 3;
        r4 = a;	 Catch:{ all -> 0x008c }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008c }
        r6 = "generated report of size ";
        r5.<init>(r6);	 Catch:{ all -> 0x008c }
        r6 = r2.length;	 Catch:{ all -> 0x008c }
        r5 = r5.append(r6);	 Catch:{ all -> 0x008c }
        r6 = " with ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x008c }
        r0 = r20;
        r6 = r0.s;	 Catch:{ all -> 0x008c }
        r6 = r6.size();	 Catch:{ all -> 0x008c }
        r5 = r5.append(r6);	 Catch:{ all -> 0x008c }
        r6 = " reports.";
        r5 = r5.append(r6);	 Catch:{ all -> 0x008c }
        r5 = r5.toString();	 Catch:{ all -> 0x008c }
        com.flurry.sdk.km.a(r3, r4, r5);	 Catch:{ all -> 0x008c }
        r3 = com.flurry.sdk.hr.a();	 Catch:{ all -> 0x008c }
        r3 = r3.b;	 Catch:{ all -> 0x008c }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008c }
        r4.<init>();	 Catch:{ all -> 0x008c }
        r5 = com.flurry.sdk.jz.b();	 Catch:{ all -> 0x008c }
        r4 = r4.append(r5);	 Catch:{ all -> 0x008c }
        r4 = r4.toString();	 Catch:{ all -> 0x008c }
        r5 = com.flurry.sdk.jy.a();	 Catch:{ all -> 0x008c }
        r5 = r5.d;	 Catch:{ all -> 0x008c }
        r3.b(r2, r5, r4);	 Catch:{ all -> 0x008c }
        goto L_0x007d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.jh.a(boolean, long):void");
    }

    private void f() {
        Editor edit = jy.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).edit();
        edit.putBoolean("com.flurry.sdk.previous_successful_report", this.j);
        edit.putLong("com.flurry.sdk.initial_run_time", this.z);
        edit.putString("com.flurry.sdk.api_key", jy.a().d);
        edit.apply();
    }

    public final synchronized void a() {
        km.a(4, a, "Saving persistent agent data.");
        this.i.a(this.s);
    }

    public final synchronized void b() {
        this.B = lw.d();
        if (hr.a().c != null) {
            jy.a().b(new ma(this) {
                final /* synthetic */ jh a;

                {
                    this.a = r1;
                }

                public final void a() {
                    hr.a().c.c();
                }
            });
        }
        if (this.o && hr.a().a != null) {
            jy.a().b(new ma(this) {
                final /* synthetic */ jh a;

                {
                    this.a = r1;
                }

                public final void a() {
                    hr.a().a.b();
                }
            });
        }
    }

    public final synchronized void c() {
        a(true, false);
    }

    private synchronized void a(boolean z, boolean z2) {
        a(false);
        jk.a();
        final long d = jk.d();
        jk.a();
        final long f = jk.f();
        jk.a();
        long j = 0;
        jx c = jk.c();
        if (c != null) {
            j = c.f;
        }
        jk.a();
        final int h = jk.h() - 1;
        if (z && this.o && hr.a().a != null) {
            jy.a().b(new ma(this) {
                final /* synthetic */ jh b;

                public final void a() {
                    hr.a().a.a(d);
                }
            });
        }
        jy.a().b(new ma(this) {
            final /* synthetic */ jh a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.f();
            }
        });
        if (jl.a().b()) {
            jy.a().b(new ma(this) {
                final /* synthetic */ jh e;

                public final void a() {
                    jf a = this.e.a(d, f, j, h);
                    this.e.s.clear();
                    this.e.s.add(a);
                    this.e.a();
                }
            });
        }
        if (z2) {
            jk.a();
            b(jk.f());
            if (jl.a().b()) {
                jy.a().b(new ma(this) {
                    final /* synthetic */ jh b;

                    public final void a() {
                        this.b.a(false, d);
                    }
                });
            }
        }
    }

    final synchronized jf a(long j, long j2, long j3, int i) {
        jf jfVar;
        String d;
        Map f;
        jg jgVar = new jg();
        jgVar.a = ju.a().g();
        jgVar.b = j;
        jgVar.c = j2;
        jgVar.d = j3;
        jgVar.e = this.u;
        jk.a();
        jx c = jk.c();
        if (c != null) {
            d = c.d();
        } else {
            d = null;
        }
        jgVar.f = d;
        jk.a();
        c = jk.c();
        if (c != null) {
            d = c.e();
        } else {
            d = null;
        }
        jgVar.g = d;
        jk.a();
        c = jk.c();
        if (c != null) {
            f = c.f();
        } else {
            f = null;
        }
        jgVar.h = f;
        jo.a();
        jgVar.i = jo.b();
        jo.a();
        jgVar.j = TimeZone.getDefault().getID();
        jgVar.k = i;
        jgVar.l = this.B != -1 ? this.B : lw.d();
        if (this.l == null) {
            d = "";
        } else {
            d = this.l;
        }
        jgVar.m = d;
        jgVar.n = jp.a().g();
        jgVar.o = this.F;
        jgVar.p = this.m;
        jgVar.q = this.n;
        jgVar.r = this.v;
        jgVar.s = this.w;
        jgVar.t = this.C;
        jgVar.v = this.x;
        jgVar.u = this.E;
        try {
            jfVar = new jf(jgVar);
        } catch (IOException e) {
            km.a(5, a, "Error creating analytics session report: " + e);
            jfVar = null;
        }
        if (jfVar == null) {
            km.e(a, "New session report wasn't created");
        }
        return jfVar;
    }

    private synchronized void b(long j) {
        for (jc jcVar : this.w) {
            if (jcVar.b && !jcVar.c) {
                jcVar.a(j);
            }
        }
    }

    public final synchronized void a(final long j) {
        ki.a().a(this.p);
        jk.a();
        b(jk.f());
        jy.a().b(new ma(this) {
            final /* synthetic */ jh a;

            {
                this.a = r1;
            }

            public final void a() {
                if (this.a.o && hr.a().a != null) {
                    hr.a().a.c();
                }
                if (hr.a().c != null) {
                    jy.a().b(new ma(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            hr.a().c.c = true;
                        }
                    });
                }
            }
        });
        if (jl.a().b()) {
            jy.a().b(new ma(this) {
                final /* synthetic */ jh b;

                public final void a() {
                    this.b.a(false, j);
                }
            });
        }
        lp.a().b("Gender", this);
        lp.a().b("UserId", this);
        lp.a().b("Age", this);
        lp.a().b("LogEvents", this);
    }

    public final void a(String str, Object obj) {
        int i = -1;
        switch (str.hashCode()) {
            case -1752163738:
                if (str.equals("UserId")) {
                    i = 1;
                    break;
                }
                break;
            case -1720015653:
                if (str.equals("analyticsEnabled")) {
                    i = 4;
                    break;
                }
                break;
            case -738063011:
                if (str.equals("LogEvents")) {
                    i = 0;
                    break;
                }
                break;
            case 65759:
                if (str.equals("Age")) {
                    i = 3;
                    break;
                }
                break;
            case 2129321697:
                if (str.equals("Gender")) {
                    i = 2;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                this.k = ((Boolean) obj).booleanValue();
                km.a(4, a, "onSettingUpdate, LogEvents = " + this.k);
                return;
            case 1:
                this.l = (String) obj;
                km.a(4, a, "onSettingUpdate, UserId = " + this.l);
                return;
            case 2:
                this.m = ((Byte) obj).byteValue();
                km.a(4, a, "onSettingUpdate, Gender = " + this.m);
                return;
            case 3:
                this.n = (Long) obj;
                km.a(4, a, "onSettingUpdate, Birthdate = " + this.n);
                return;
            case 4:
                this.o = ((Boolean) obj).booleanValue();
                km.a(4, a, "onSettingUpdate, AnalyticsEnabled = " + this.o);
                return;
            default:
                km.a(6, a, "onSettingUpdate internal error!");
                return;
        }
    }

    public final synchronized void d() {
        this.F++;
    }

    public final synchronized FlurryEventRecordStatus a(String str, String str2, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus;
        flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (map != null) {
            if (!TextUtils.isEmpty(str2)) {
                map.put("sid+Tumblr", str2);
                flurryEventRecordStatus = a(str, (Map) map, false);
                km.a(5, a, "logEvent status for syndication:" + flurryEventRecordStatus);
            }
        }
        return flurryEventRecordStatus;
    }

    public final synchronized FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus;
        FlurryEventRecordStatus flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventRecorded;
        if (this.o) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            jk.a();
            long e = elapsedRealtime - jk.e();
            final String b = ly.b(str);
            if (b.length() == 0) {
                flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
            } else {
                jb jbVar = (jb) this.v.get(b);
                if (jbVar != null) {
                    jbVar.a++;
                    km.e(a, "Event count incremented: " + b);
                    flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventRecorded;
                } else if (this.v.size() < b) {
                    jbVar = new jb();
                    jbVar.a = 1;
                    this.v.put(b, jbVar);
                    km.e(a, "Event count started: " + b);
                    flurryEventRecordStatus = flurryEventRecordStatus2;
                } else {
                    km.e(a, "Too many different events. Event not counted: " + b);
                    flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventUniqueCountExceeded;
                }
                if (!this.k || this.w.size() >= d || this.D >= e) {
                    this.C = false;
                } else {
                    Map emptyMap;
                    if (map == null) {
                        emptyMap = Collections.emptyMap();
                    } else {
                        Map<String, String> map2 = map;
                    }
                    if (emptyMap.size() + 0 > c) {
                        km.e(a, "MaxEventParams exceeded: " + (emptyMap.size() + 0));
                        flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventParamsCountExceeded;
                    } else {
                        jc jcVar = new jc(this.q.incrementAndGet(), b, emptyMap, e, z);
                        if (jcVar.b().length + this.D <= e) {
                            this.w.add(jcVar);
                            this.D = jcVar.b().length + this.D;
                            flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventRecorded;
                            if (this.o && hr.a().a != null) {
                                jy.a().b(new Runnable(this) {
                                    final /* synthetic */ jh c;

                                    public final void run() {
                                        hr.a().a.a(b, emptyMap);
                                    }
                                });
                            }
                        } else {
                            this.D = e;
                            this.C = false;
                            km.e(a, "Event Log size exceeded. No more event details logged.");
                            flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventLogCountExceeded;
                        }
                    }
                }
                if (b.equals("Flurry.purchase")) {
                    a(false, true);
                }
            }
        } else {
            flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventAnalyticsDisabled;
            km.e(a, "Analytics has been disabled, not logging event.");
        }
        return flurryEventRecordStatus;
    }

    public final synchronized void a(String str, Map<String, String> map) {
        for (jc jcVar : this.w) {
            Object obj;
            if (jcVar.b && jcVar.d == 0 && jcVar.a.equals(str)) {
                obj = 1;
                continue;
            } else {
                obj = null;
                continue;
            }
            if (obj != null) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                jk.a();
                elapsedRealtime -= jk.e();
                if (map != null && map.size() > 0 && this.D < e) {
                    int length = this.D - jcVar.b().length;
                    Map hashMap = new HashMap(jcVar.a());
                    jcVar.a((Map) map);
                    if (jcVar.b().length + length > e) {
                        jcVar.b(hashMap);
                        this.C = false;
                        this.D = e;
                        km.e(a, "Event Log size exceeded. No more event details logged.");
                    } else if (jcVar.a().size() > c) {
                        km.e(a, "MaxEventParams exceeded on endEvent: " + jcVar.a().size());
                        jcVar.b(hashMap);
                    } else {
                        this.D = length + jcVar.b().length;
                    }
                }
                jcVar.a(elapsedRealtime);
            }
        }
    }

    public final synchronized void a(String str, String str2, String str3, Throwable th) {
        Object obj;
        ja jaVar;
        int i;
        if (str != null) {
            if ("uncaught".equals(str)) {
                obj = 1;
                this.E++;
                if (this.x.size() < f) {
                    jaVar = new ja(this.r.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th);
                    this.x.add(jaVar);
                    km.e(a, "Error logged: " + jaVar.a);
                } else if (obj == null) {
                    for (i = 0; i < this.x.size(); i++) {
                        jaVar = (ja) this.x.get(i);
                        if (jaVar.a == null && !"uncaught".equals(jaVar.a)) {
                            this.x.set(i, new ja(this.r.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th));
                            break;
                        }
                    }
                } else {
                    km.e(a, "Max errors logged. No more errors logged.");
                }
            }
        }
        obj = null;
        this.E++;
        if (this.x.size() < f) {
            jaVar = new ja(this.r.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th);
            this.x.add(jaVar);
            km.e(a, "Error logged: " + jaVar.a);
        } else if (obj == null) {
            km.e(a, "Max errors logged. No more errors logged.");
        } else {
            while (i < this.x.size()) {
                jaVar = (ja) this.x.get(i);
                if (jaVar.a == null) {
                }
            }
        }
    }

    static /* synthetic */ void d(jh jhVar) {
        SharedPreferences sharedPreferences = jy.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
        jhVar.j = sharedPreferences.getBoolean("com.flurry.sdk.previous_successful_report", false);
        jk.a();
        jhVar.z = sharedPreferences.getLong("com.flurry.sdk.initial_run_time", jk.d());
        jhVar.A = sharedPreferences.getString("com.flurry.sdk.api_key", "");
        if (TextUtils.isEmpty(jhVar.A) && jhVar.z > 0) {
            jhVar.A = jy.a().d;
        } else if (!jhVar.A.equals(jy.a().d)) {
            jk.a();
            jhVar.z = jk.d();
        }
    }
}
