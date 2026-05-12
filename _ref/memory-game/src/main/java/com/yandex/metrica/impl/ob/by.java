package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class by {
    public static final Map<String, String> a = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    });
    private bz b;

    public by(bz bzVar) {
        this.b = bzVar;
    }

    public void a() {
        if (f()) {
            g();
            h();
        }
    }

    public void b() {
        Object obj;
        String d = d();
        if (TextUtils.isEmpty(d) || !"DONE".equals(this.b.g().get(bz.f(d)))) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            a(d);
        }
    }

    public void c() {
        a(e());
    }

    void a(String str) {
        if (str != null) {
            b(str);
            g();
        }
    }

    String d() {
        return (String) a.get(this.b.j());
    }

    String e() {
        Map g = this.b.g();
        for (String f : a.values()) {
            g.remove(bz.f(f));
        }
        LinkedList linkedList = new LinkedList();
        for (String f2 : g.keySet()) {
            try {
                linkedList.add(Integer.valueOf(Integer.parseInt(bz.g(f2))));
            } catch (Throwable th) {
            }
        }
        if (linkedList.size() == 1) {
            return ((Integer) linkedList.getFirst()).toString();
        }
        return null;
    }

    boolean f() {
        return this.b.a(null) != null;
    }

    void g() {
        this.b.a();
    }

    void b(String str) {
        this.b.d(str);
    }

    void h() {
        this.b.b();
    }
}
