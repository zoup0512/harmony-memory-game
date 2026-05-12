package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.utils.c.a;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract class b implements com.yandex.metrica.b {
    public static final Collection<Integer> a;
    protected final ar b;
    protected final at c;
    private w d;

    static {
        Collection hashSet = new HashSet();
        hashSet.add(Integer.valueOf(1));
        hashSet.add(Integer.valueOf(2));
        hashSet.add(Integer.valueOf(3));
        hashSet.add(Integer.valueOf(4));
        hashSet.add(Integer.valueOf(5));
        hashSet.add(Integer.valueOf(6));
        hashSet.add(Integer.valueOf(7));
        hashSet.add(Integer.valueOf(8));
        hashSet.add(Integer.valueOf(11));
        hashSet.add(Integer.valueOf(12));
        hashSet.add(Integer.valueOf(13));
        a = Collections.unmodifiableCollection(hashSet);
    }

    b(Context context, String str, at atVar, ar arVar) {
        context.getApplicationContext();
        this.c = atVar;
        this.b = arVar;
        this.b.b().a(str);
        this.b.b().c(context.getPackageName());
        this.b.a(a.d());
    }

    protected void a() {
        this.c.a(this.b);
    }

    void a(cp cpVar) {
        this.b.b(cpVar);
    }

    void a(j jVar) {
        this.b.a(jVar);
    }

    void a(w wVar) {
        this.d = wVar;
    }

    public void a(String str) {
        this.b.b().h(str);
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.b.a(str, str2);
        }
    }

    public void a(Map<String, String> map) {
        if (!bg.a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                a((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public void b(Map<String, String> map) {
        if (!bg.a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                b((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public void b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.c.a(str, str2, this.b);
        }
    }

    public void b() {
        this.c.b(this.b);
    }

    public void onResumeSession() {
        b(null);
    }

    void b(String str) {
        this.c.f();
        this.d.b();
        this.c.a(p.b(str), this.b);
        if (this.b.e()) {
            this.c.a(p.d(p.a.EVENT_TYPE_PURGE_BUFFER), this.b);
        }
    }

    public void onPauseSession() {
        c(null);
    }

    void c(String str) {
        if (!this.b.a()) {
            this.c.g();
            this.d.a();
            this.c.a(p.c(str), this.b);
            this.b.d();
        }
    }

    public void reportEvent(String eventName) {
        reportEvent(eventName, null);
    }

    public void reportEvent(String eventName, String jsonValue) {
        bg.a((Object) eventName, "Event Name");
        a(p.a(eventName, jsonValue));
    }

    public void reportEvent(String eventName, Map<String, Object> attributes) {
        bg.a((Object) eventName, "Event Name");
        this.c.a(p.a(eventName), d(), bg.a((Map) attributes) ? null : new HashMap(attributes));
    }

    public void a(int i, String str, String str2, Map<String, String> map) {
        if (!a.contains(Integer.valueOf(i))) {
            a(p.a(i, str, str2, map == null ? null : new HashMap(map)));
        }
    }

    public void reportError(String message, Throwable error) {
        bg.a((Object) message, "Message");
        a(p.b(message, bg.a(null, error)));
    }

    public void setSessionTimeout(int sessionTimeOut) {
        this.b.b().c(sessionTimeOut);
    }

    public void reportUnhandledException(Throwable exception) {
        bg.a((Object) exception, "Exception");
        this.c.a(exception, this.b);
    }

    void d(String str) {
        bg.a((Object) str, "Native Crash");
        this.c.a(str, this.b);
    }

    public void a(int i) {
        this.b.b().b(i);
    }

    boolean c() {
        boolean z = !e();
        if (z) {
            this.c.a(p.c(p.a.EVENT_TYPE_ALIVE.b()), this.b);
        }
        return z;
    }

    ar d() {
        return this.b;
    }

    private void a(h hVar) {
        this.c.a(hVar, this.b);
    }

    public boolean e() {
        return this.b.a();
    }
}
