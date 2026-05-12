package com.yandex.metrica.impl.ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.IIdentifierCallback.Reason;
import com.yandex.metrica.impl.at;
import com.yandex.metrica.impl.ob.cq.a;
import com.yandex.metrica.impl.utils.e;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class co implements cp {
    static final Map<cn, Reason> a = Collections.unmodifiableMap(new HashMap<cn, Reason>() {
        {
            put(cn.UNKNOWN, Reason.UNKNOWN);
            put(cn.NETWORK, Reason.NETWORK);
            put(cn.PARSE, Reason.INVALID_RESPONSE);
        }
    });
    private final at b;
    private final cq c;
    private final bi d;
    private final Object e = new Object();
    private final Map<IIdentifierCallback, Object> f = new WeakHashMap();
    private final Map<IIdentifierCallback, Object> g = new WeakHashMap();

    public co(at atVar, String str, bi biVar) {
        this.b = atVar;
        this.d = biVar;
        this.c = new cq(this.d, str);
        e();
    }

    public String a() {
        return this.c.c();
    }

    public String b() {
        return this.d.a();
    }

    public String c() {
        return this.c.d();
    }

    public void a(IIdentifierCallback iIdentifierCallback) {
        synchronized (this.e) {
            this.g.put(iIdentifierCallback, null);
            this.g.size();
            if (!this.c.a(a.ALL)) {
                this.b.e();
            }
        }
        e();
    }

    public void a(Bundle bundle) {
        synchronized (this.e) {
            this.c.a(bundle);
            this.c.a(System.currentTimeMillis() / 1000);
        }
        e();
    }

    public void d() {
        if (!this.c.a(a.ALL) || this.c.a()) {
            this.b.e();
        }
    }

    public void a(String str) {
        String b = this.c.b();
        if (TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(b)) {
                this.c.a(null);
                this.c.a(0);
            }
        } else if (str.equals(b)) {
            this.b.c(b);
        } else {
            this.c.a(str);
            this.c.a(0);
            this.b.c(str);
        }
    }

    public void a(Map<String, String> map) {
        Map hashMap = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object obj;
                String str = (String) entry.getKey();
                if (TextUtils.isEmpty(str) || str.contains(":") || str.contains(",") || str.contains("&")) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    str = (String) entry.getValue();
                    if (TextUtils.isEmpty(str) || e.a(str, -1) == -1) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        this.b.a(hashMap);
    }

    public void b(String str) {
        this.b.d(str);
    }

    void e() {
        Map weakHashMap = new WeakHashMap();
        Map hashMap = new HashMap();
        Map weakHashMap2 = new WeakHashMap();
        Map hashMap2 = new HashMap();
        synchronized (this.e) {
            if (this.c.a(a.IDENTIFIERS)) {
                weakHashMap.putAll(this.f);
                this.f.clear();
                this.c.b(hashMap);
                weakHashMap.size();
            }
            if (this.c.a(a.ALL)) {
                weakHashMap2.putAll(this.g);
                this.g.clear();
                this.c.a(hashMap2);
                weakHashMap2.size();
            }
        }
        for (IIdentifierCallback onReceive : weakHashMap.keySet()) {
            onReceive.onReceive(new HashMap(hashMap));
        }
        for (IIdentifierCallback onReceive2 : weakHashMap2.keySet()) {
            onReceive2.onReceive(new HashMap(hashMap2));
        }
        weakHashMap.clear();
        hashMap.clear();
        weakHashMap2.clear();
        hashMap2.clear();
    }

    public void b(Bundle bundle) {
        Reason reason = (Reason) a.get(cn.b(bundle));
        Map weakHashMap = new WeakHashMap();
        Map weakHashMap2 = new WeakHashMap();
        synchronized (this.e) {
            weakHashMap.putAll(this.f);
            weakHashMap2.putAll(this.g);
            this.f.clear();
            this.g.clear();
        }
        for (IIdentifierCallback onRequestError : weakHashMap.keySet()) {
            onRequestError.onRequestError(reason);
        }
        for (IIdentifierCallback onRequestError2 : weakHashMap2.keySet()) {
            onRequestError2.onRequestError(reason);
        }
        weakHashMap.clear();
        weakHashMap2.clear();
    }
}
