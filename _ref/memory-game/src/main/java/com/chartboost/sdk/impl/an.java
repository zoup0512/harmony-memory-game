package com.chartboost.sdk.impl;

import android.graphics.Bitmap;
import com.chartboost.sdk.impl.w.a;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class an implements z {
    private final al a;
    private final Set<w<?>> b = new HashSet();
    private final PriorityBlockingQueue<w<?>> c = new PriorityBlockingQueue();
    private final Set<ak> d = new HashSet();
    private final int e;

    public an(al alVar, int i) {
        this.a = alVar;
        this.e = i;
        a();
    }

    public void a() {
        if (this.d.isEmpty()) {
            for (int i = 0; i < this.e; i++) {
                ak a = this.a.a(this, this.c);
                this.d.add(a);
                a.start();
            }
        }
    }

    public <T> void a(w<T> wVar) {
        synchronized (this.b) {
            this.b.add(wVar);
        }
        this.c.add(wVar);
    }

    void b(w<?> wVar) {
        synchronized (this.b) {
            this.b.remove(wVar);
        }
    }

    public void a(String str, aa<Bitmap> aaVar, v vVar) {
        a(new aj(str, aaVar, vVar));
    }

    public void a(String str) {
        a(new w<String>(this, a.b, str, null) {
            final /* synthetic */ an a;

            public y<String> a(ab abVar) {
                return y.b();
            }

            public void a(String str) {
            }

            public Map<String, String> b() {
                return Collections.emptyMap();
            }
        });
    }

    public void a(Object obj) {
        synchronized (this.b) {
            for (w wVar : this.b) {
                if (wVar.a() == obj) {
                    wVar.f();
                }
            }
        }
    }
}
