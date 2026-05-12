package com.flurry.sdk;

import android.content.Context;
import android.os.SystemClock;
import com.flurry.sdk.ll.a;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class jx {
    static final String a = jx.class.getSimpleName();
    WeakReference<lk> b;
    public volatile long c = 0;
    public volatile long d = 0;
    public volatile long e = -1;
    public volatile long f = 0;
    private final kh<ll> g = new kh<ll>(this) {
        final /* synthetic */ jx a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            ll llVar = (ll) kgVar;
            if (this.a.b == null || llVar.b == this.a.b.get()) {
                jx jxVar;
                switch (AnonymousClass4.a[llVar.c - 1]) {
                    case 1:
                        jx jxVar2 = this.a;
                        lk lkVar = llVar.b;
                        Context context = (Context) llVar.a.get();
                        jxVar2.b = new WeakReference(lkVar);
                        jxVar2.c = System.currentTimeMillis();
                        jxVar2.d = SystemClock.elapsedRealtime();
                        if (lkVar == null || context == null) {
                            km.a(3, jx.a, "Flurry session id cannot be created.");
                        } else {
                            km.a(3, jx.a, "Flurry session id started:" + jxVar2.c);
                            ll llVar2 = new ll();
                            llVar2.a = new WeakReference(context);
                            llVar2.b = lkVar;
                            llVar2.c = a.b;
                            llVar2.b();
                        }
                        jy.a().b(new ma(jxVar2) {
                            final /* synthetic */ jx a;

                            {
                                this.a = r1;
                            }

                            public final void a() {
                                jp.a().e();
                            }
                        });
                        return;
                    case 2:
                        jxVar = this.a;
                        llVar.a.get();
                        jxVar.a();
                        return;
                    case 3:
                        jxVar = this.a;
                        llVar.a.get();
                        jxVar.e = SystemClock.elapsedRealtime() - jxVar.d;
                        return;
                    case 4:
                        ki.a().b("com.flurry.android.sdk.FlurrySessionEvent", this.a.g);
                        jx.b();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private volatile long h = 0;
    private String i;
    private String j;
    private Map<String, String> k;

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[a.a().length];

        static {
            try {
                a[a.a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.c - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.d - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.e - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public jx() {
        ki.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.g);
        this.k = new LinkedHashMap<String, String>(this) {
            final /* synthetic */ jx a;

            {
                this.a = r1;
            }

            protected final boolean removeEldestEntry(Entry<String, String> entry) {
                return size() > 10;
            }
        };
    }

    public final synchronized void a() {
        long j = lm.a().a;
        if (j > 0) {
            this.f = (System.currentTimeMillis() - j) + this.f;
        }
    }

    public static void b() {
    }

    public final synchronized long c() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.d;
        if (elapsedRealtime <= this.h) {
            elapsedRealtime = this.h + 1;
            this.h = elapsedRealtime;
        }
        this.h = elapsedRealtime;
        return this.h;
    }

    public final synchronized String d() {
        return this.i;
    }

    public final synchronized void a(String str) {
        this.i = str;
    }

    public final synchronized String e() {
        return this.j;
    }

    public final synchronized void b(String str) {
        this.j = str;
    }

    public final synchronized void a(String str, String str2) {
        this.k.put(str, str2);
    }

    public final synchronized Map<String, String> f() {
        return this.k;
    }
}
