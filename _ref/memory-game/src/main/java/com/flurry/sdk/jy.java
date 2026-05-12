package com.flurry.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

public class jy {
    private static final String e = jy.class.getSimpleName();
    private static jy f;
    public final Context a;
    public final Handler b = new Handler(Looper.getMainLooper());
    public final Handler c;
    public final String d;
    private final HandlerThread g = new HandlerThread("FlurryAgent");
    private final ko h;

    private jy(Context context, String str) {
        this.a = context.getApplicationContext();
        this.g.start();
        this.c = new Handler(this.g.getLooper());
        this.d = str;
        this.h = new ko();
    }

    public static jy a() {
        return f;
    }

    public static synchronized void a(Context context, String str) {
        synchronized (jy.class) {
            if (f != null) {
                if (f.d.equals(str)) {
                    km.e(e, "Flurry is already initialized");
                } else {
                    throw new IllegalStateException("Only one API key per application is supported!");
                }
            } else if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            } else if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("API key must be specified");
            } else {
                jy jyVar = new jy(context, str);
                f = jyVar;
                jyVar.h.a(context);
            }
        }
    }

    public final void a(Runnable runnable) {
        this.b.post(runnable);
    }

    public final void b(Runnable runnable) {
        this.c.post(runnable);
    }

    public final void a(Runnable runnable, long j) {
        if (runnable != null) {
            this.c.postDelayed(runnable, j);
        }
    }

    public final kp a(Class<? extends kp> cls) {
        return this.h.b(cls);
    }
}
