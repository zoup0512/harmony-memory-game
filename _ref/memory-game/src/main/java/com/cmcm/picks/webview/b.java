package com.cmcm.picks.webview;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Reflection */
public class b {
    private final Object a;
    private final String b;
    private Class<?> c;
    private List<Class<?>> d = new ArrayList();
    private List<Object> e = new ArrayList();
    private boolean f;
    private boolean g;

    private b(Object obj, String str) {
        this.a = obj;
        this.b = str;
        this.c = obj != null ? obj.getClass() : null;
    }

    public static void a(Object obj, String str) {
        try {
            new b(obj, str).a().b();
        } catch (Exception e) {
        }
    }

    private b a() {
        this.f = true;
        return this;
    }

    private Object b() throws Exception {
        Method a = a(this.c, this.b, (Class[]) this.d.toArray(new Class[this.d.size()]));
        if (this.f) {
            a.setAccessible(true);
        }
        Object[] toArray = this.e.toArray();
        if (this.g) {
            return a.invoke(null, toArray);
        }
        return a.invoke(this.a, toArray);
    }

    private Method a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }
}
