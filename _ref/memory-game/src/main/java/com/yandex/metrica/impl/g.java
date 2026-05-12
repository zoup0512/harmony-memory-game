package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.bi;
import com.yandex.metrica.impl.ob.cm;
import com.yandex.metrica.impl.utils.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;

public class g {
    private final bi a;
    private final Executor b;
    private final c c;
    private volatile Map<String, Long> d = null;

    public g(bi biVar, c cVar, Executor executor) {
        this.a = biVar;
        this.b = executor;
        this.c = cVar;
        b();
        this.b.execute(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                Map a = this.a.c.a();
                Map hashMap = new HashMap();
                if (!bg.a(a)) {
                    for (Entry entry : a.entrySet()) {
                        hashMap.put(entry.getKey(), Long.valueOf(e.a((String) entry.getValue(), 0)));
                    }
                }
                this.a.d = hashMap;
            }
        });
    }

    private void b() {
        String n = this.a.n(null);
        cm cmVar = new cm();
        Map d = bg.d(n);
        if (!bg.a(d)) {
            for (Entry entry : d.entrySet()) {
                cmVar.a((String) entry.getKey(), e.a((String) entry.getValue(), Integer.MAX_VALUE));
            }
        }
        this.a.b(null);
        this.a.a(null);
        this.a.p(null);
    }

    public void a() {
        b();
    }
}
