package com.my.target.core.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.WeakHashMap;

/* compiled from: AdShowHandler */
public final class a {
    private static final a a = new a();
    private final Handler b = new Handler(Looper.getMainLooper());
    private final WeakHashMap<a, Boolean> c = new WeakHashMap();
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.b();
        }
    };

    /* compiled from: AdShowHandler */
    public interface a {
        boolean a();
    }

    public static a a() {
        return a;
    }

    public final synchronized void a(a aVar) {
        int size = this.c.size();
        if (this.c.put(aVar, Boolean.valueOf(true)) == null && size == 0) {
            c();
        }
    }

    public final synchronized void b(a aVar) {
        this.c.remove(aVar);
        if (this.c.size() == 0) {
            this.b.removeCallbacks(this.d);
        }
    }

    private synchronized void b() {
        Iterator it = this.c.keySet().iterator();
        while (it.hasNext()) {
            if (((a) it.next()).a()) {
                it.remove();
            }
        }
        if (this.c.size() > 0) {
            c();
        }
    }

    private void c() {
        this.b.postDelayed(this.d, 1000);
    }
}
