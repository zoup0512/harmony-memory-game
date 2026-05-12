package com.flurry.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ki {
    private static ki a = null;
    private final kd<String, kv<kh<?>>> b = new kd();
    private final kd<kv<kh<?>>, String> c = new kd();

    private ki() {
    }

    public static synchronized ki a() {
        ki kiVar;
        synchronized (ki.class) {
            if (a == null) {
                a = new ki();
            }
            kiVar = a;
        }
        return kiVar;
    }

    public final synchronized void a(String str, kh<?> khVar) {
        boolean z = false;
        synchronized (this) {
            if (!(TextUtils.isEmpty(str) || khVar == null)) {
                Object kvVar = new kv(khVar);
                List a = this.b.a((Object) str, false);
                if (a != null) {
                    z = a.contains(kvVar);
                }
                if (!z) {
                    this.b.a((Object) str, kvVar);
                    this.c.a(kvVar, (Object) str);
                }
            }
        }
    }

    public final synchronized void b(String str, kh<?> khVar) {
        if (!TextUtils.isEmpty(str)) {
            kv kvVar = new kv(khVar);
            this.b.b(str, kvVar);
            this.c.b(kvVar, str);
        }
    }

    public final synchronized void a(kh<?> khVar) {
        if (khVar != null) {
            Object kvVar = new kv(khVar);
            for (String b : this.c.a(kvVar)) {
                this.b.b(b, kvVar);
            }
            this.c.b(kvVar);
        }
    }

    public final synchronized int a(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            i = 0;
        } else {
            i = this.b.a((Object) str).size();
        }
        return i;
    }

    public final void a(final kg kgVar) {
        if (kgVar != null) {
            for (final kh khVar : b(kgVar.a())) {
                jy.a().b(new ma(this) {
                    final /* synthetic */ ki c;

                    public final void a() {
                        khVar.a(kgVar);
                    }
                });
            }
        }
    }

    private synchronized List<kh<?>> b(String str) {
        List<kh<?>> emptyList;
        if (TextUtils.isEmpty(str)) {
            emptyList = Collections.emptyList();
        } else {
            List<kh<?>> arrayList = new ArrayList();
            Iterator it = this.b.a((Object) str).iterator();
            while (it.hasNext()) {
                kh khVar = (kh) ((kv) it.next()).get();
                if (khVar == null) {
                    it.remove();
                } else {
                    arrayList.add(khVar);
                }
            }
            emptyList = arrayList;
        }
        return emptyList;
    }
}
