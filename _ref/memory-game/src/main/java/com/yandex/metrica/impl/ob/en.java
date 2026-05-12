package com.yandex.metrica.impl.ob;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class en<T> {
    private final int a;
    private final String b;
    private ep c;
    private volatile b<T> d;
    private volatile a e;

    public interface b<T> {
        void a(T t);
    }

    public interface a {
        void a(ek ekVar);
    }

    protected abstract T b(em emVar) throws ek;

    public en(int i, String str) {
        this.a = i;
        this.b = str;
        a(new ep());
    }

    public int d() {
        return this.a;
    }

    public en<?> a(ep epVar) {
        this.c = epVar;
        return this;
    }

    public String a() {
        return this.b;
    }

    protected b<T> e() {
        return this.d;
    }

    protected void a(b<T> bVar) {
        this.d = bVar;
    }

    protected a f() {
        return this.e;
    }

    protected void a(a aVar) {
        this.e = aVar;
    }

    public Map<String, String> b() throws ek {
        return Collections.emptyMap();
    }

    protected Map<String, String> g() throws ek {
        return k();
    }

    protected String h() {
        return l();
    }

    public String i() {
        return m();
    }

    public byte[] j() throws ek {
        Map g = g();
        if (g == null || g.size() <= 0) {
            return null;
        }
        return a(g, h());
    }

    protected Map<String, String> k() throws ek {
        return null;
    }

    protected String l() {
        return "UTF-8";
    }

    public String m() {
        return "application/x-www-form-urlencoded; charset=" + l();
    }

    public byte[] c() throws ek {
        Map k = k();
        if (k == null || k.size() <= 0) {
            return null;
        }
        return a(k, l());
    }

    private static byte[] a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final int n() {
        return this.c.a();
    }

    public ep o() {
        return this.c;
    }
}
