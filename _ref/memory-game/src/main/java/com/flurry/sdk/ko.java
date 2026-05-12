package com.flurry.sdk;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ko {
    private static final String a = ko.class.getSimpleName();
    private static final Map<Class<? extends kp>, kn> b = new LinkedHashMap();
    private final Map<Class<? extends kp>, kp> c = new LinkedHashMap();

    public static void a(Class<? extends kp> cls) {
        if (cls != null) {
            synchronized (b) {
                b.put(cls, new kn(cls));
            }
        }
    }

    public final synchronized void a(Context context) {
        if (context == null) {
            km.a(5, a, "Null context.");
        } else {
            synchronized (b) {
                List<kn> arrayList = new ArrayList(b.values());
            }
            for (kn knVar : arrayList) {
                try {
                    Object obj;
                    if (knVar.a == null || VERSION.SDK_INT < knVar.b) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        kp kpVar = (kp) knVar.a.newInstance();
                        kpVar.a(context);
                        this.c.put(knVar.a, kpVar);
                    }
                } catch (Throwable e) {
                    km.a(5, a, "Flurry Module for class " + knVar.a + " is not available:", e);
                }
            }
            lm.a().a(context);
            kc.a();
        }
    }

    public final kp b(Class<? extends kp> cls) {
        if (cls == null) {
            return null;
        }
        synchronized (this.c) {
            kp kpVar = (kp) this.c.get(cls);
        }
        if (kpVar != null) {
            return kpVar;
        }
        throw new IllegalStateException("Module was not registered/initialized. " + cls);
    }
}
