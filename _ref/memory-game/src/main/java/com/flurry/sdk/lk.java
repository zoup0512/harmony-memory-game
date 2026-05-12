package com.flurry.sdk;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class lk {
    private static final List<Class<?>> a = new ArrayList();
    private final String b = lk.class.getSimpleName();
    private final Map<Class<?>, Object> c = new LinkedHashMap();

    public lk() {
        synchronized (a) {
            List<Class> arrayList = new ArrayList(a);
        }
        for (Class cls : arrayList) {
            try {
                Object newInstance = cls.newInstance();
                synchronized (this.c) {
                    this.c.put(cls, newInstance);
                }
            } catch (Throwable e) {
                km.a(5, this.b, "Module data " + cls + " is not available:", e);
            }
        }
    }

    public static void a(Class<?> cls) {
        synchronized (a) {
            a.add(cls);
        }
    }

    public final Object b(Class<?> cls) {
        Object obj;
        synchronized (this.c) {
            obj = this.c.get(cls);
        }
        return obj;
    }
}
