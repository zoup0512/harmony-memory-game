package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import com.flurry.sdk.lq.a;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class lm implements a {
    private static final String b = lm.class.getSimpleName();
    private static lm c;
    public long a;
    private final Map<Context, lk> d = new WeakHashMap();
    private final ln e = new ln();
    private final Object f = new Object();
    private long g;
    private lk h;
    private kh<lo> i = new kh<lo>(this) {
        final /* synthetic */ lm a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(kg kgVar) {
            this.a.g();
        }
    };
    private kh<kb> j = new kh<kb>(this) {
        final /* synthetic */ lm a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            kb kbVar = (kb) kgVar;
            Context context = (Activity) kbVar.a.get();
            if (context == null) {
                km.a(lm.b, "Activity has been destroyed, can't pass ActivityLifecycleEvent to adobject.");
                return;
            }
            switch (kbVar.b) {
                case kStarted:
                    km.a(3, lm.b, "Automatic onStartSession for context:" + kbVar.a);
                    this.a.e(context);
                    return;
                case kStopped:
                    km.a(3, lm.b, "Automatic onEndSession for context:" + kbVar.a);
                    this.a.d(context);
                    return;
                case kDestroyed:
                    km.a(3, lm.b, "Automatic onEndSession (destroyed) for context:" + kbVar.a);
                    this.a.d(context);
                    return;
                default:
                    return;
            }
        }
    };

    private lm() {
        lq a = lp.a();
        this.a = 0;
        this.g = ((Long) a.a("ContinueSessionMillis")).longValue();
        a.a("ContinueSessionMillis", (a) this);
        km.a(4, b, "initSettings, ContinueSessionMillis = " + this.g);
        ki.a().a("com.flurry.android.sdk.ActivityLifecycleEvent", this.j);
        ki.a().a("com.flurry.android.sdk.FlurrySessionTimerEvent", this.i);
    }

    public static synchronized lm a() {
        lm lmVar;
        synchronized (lm.class) {
            if (c == null) {
                c = new lm();
            }
            lmVar = c;
        }
        return lmVar;
    }

    public final synchronized void a(Context context) {
        if (context instanceof Activity) {
            if (kc.a().b()) {
                km.a(3, b, "bootstrap for context:" + context);
                e(context);
            }
        }
    }

    private synchronized void e(Context context) {
        if (((lk) this.d.get(context)) == null) {
            ll llVar;
            this.e.a();
            lk b = b();
            if (b == null) {
                b = new lk();
                km.e(b, "Flurry session started for context:" + context);
                llVar = new ll();
                llVar.a = new WeakReference(context);
                llVar.b = b;
                llVar.c = ll.a.a;
                llVar.b();
            }
            this.d.put(context, b);
            synchronized (this.f) {
                this.h = b;
            }
            km.e(b, "Flurry session resumed for context:" + context);
            llVar = new ll();
            llVar.a = new WeakReference(context);
            llVar.b = b;
            llVar.c = ll.a.c;
            llVar.b();
            this.a = 0;
        } else if (kc.a().b()) {
            km.a(3, b, "Session already started with context:" + context);
        } else {
            km.e(b, "Session already started with context:" + context);
        }
    }

    public final lk b() {
        lk lkVar;
        synchronized (this.f) {
            lkVar = this.h;
        }
        return lkVar;
    }

    public final synchronized void b(Context context) {
        if (!(kc.a().b() && (context instanceof Activity))) {
            km.a(3, b, "Manual onStartSession for context:" + context);
            e(context);
        }
    }

    public final synchronized void c(Context context) {
        if (!(kc.a().b() && (context instanceof Activity))) {
            km.a(3, b, "Manual onEndSession for context:" + context);
            d(context);
        }
    }

    final synchronized void d(Context context) {
        lk lkVar = (lk) this.d.remove(context);
        if (lkVar != null) {
            km.e(b, "Flurry session paused for context:" + context);
            ll llVar = new ll();
            llVar.a = new WeakReference(context);
            llVar.b = lkVar;
            jk.a();
            llVar.d = jk.d();
            llVar.c = ll.a.d;
            llVar.b();
            if (f() == 0) {
                this.e.a(this.g);
                this.a = System.currentTimeMillis();
            } else {
                this.a = 0;
            }
        } else if (kc.a().b()) {
            km.a(3, b, "Session cannot be ended, session not found for context:" + context);
        } else {
            km.e(b, "Session cannot be ended, session not found for context:" + context);
        }
    }

    private synchronized int f() {
        return this.d.size();
    }

    public final synchronized boolean c() {
        boolean z;
        if (b() == null) {
            km.a(2, b, "Session not found. No active session");
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public final synchronized void d() {
        for (Entry entry : this.d.entrySet()) {
            ll llVar = new ll();
            llVar.a = new WeakReference(entry.getKey());
            llVar.b = (lk) entry.getValue();
            llVar.c = ll.a.d;
            jk.a();
            llVar.d = jk.d();
            llVar.b();
        }
        this.d.clear();
        jy.a().b(new ma(this) {
            final /* synthetic */ lm a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.g();
            }
        });
    }

    private synchronized void g() {
        int f = f();
        if (f > 0) {
            km.a(5, b, "Session cannot be finalized, sessionContextCount:" + f);
        } else {
            final lk b = b();
            if (b == null) {
                km.a(5, b, "Session cannot be finalized, current session not found");
            } else {
                km.e(b, "Flurry session ended");
                ll llVar = new ll();
                llVar.b = b;
                llVar.c = ll.a.e;
                jk.a();
                llVar.d = jk.d();
                llVar.b();
                jy.a().b(new ma(this) {
                    final /* synthetic */ lm b;

                    public final void a() {
                        lm.a(this.b, b);
                    }
                });
            }
        }
    }

    public final void a(String str, Object obj) {
        if (str.equals("ContinueSessionMillis")) {
            this.g = ((Long) obj).longValue();
            km.a(4, b, "onSettingUpdate, ContinueSessionMillis = " + this.g);
            return;
        }
        km.a(6, b, "onSettingUpdate internal error!");
    }

    static /* synthetic */ void a(lm lmVar, lk lkVar) {
        synchronized (lmVar.f) {
            if (lmVar.h == lkVar) {
                lmVar.h = null;
            }
        }
    }
}
