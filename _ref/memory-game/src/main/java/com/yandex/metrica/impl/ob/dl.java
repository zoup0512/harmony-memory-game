package com.yandex.metrica.impl.ob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class dl implements do {
    private Map<String, Set<String>> a = new ConcurrentHashMap();
    private volatile AtomicLong b = new AtomicLong();

    public void a(String str, String[] strArr) {
        if (!this.a.keySet().contains(str)) {
            this.a.put(str, new HashSet(Arrays.asList(strArr)));
            d();
        }
    }

    public Map<String, Set<String>> c() {
        Map<String, Set<String>> hashMap = new HashMap();
        for (String str : this.a.keySet()) {
            hashMap.put(str, a(str));
        }
        return hashMap;
    }

    public void a(Map<String, Set<String>> map) {
        this.a = new ConcurrentHashMap(map);
        d();
    }

    public Set<String> a(String str) {
        Set set = (Set) this.a.get(str);
        return set == null ? null : new HashSet(set);
    }

    public boolean a(String str, String str2) {
        Set set = (Set) this.a.get(str);
        if (set == null) {
            set = new HashSet();
            this.a.put(str, set);
        }
        d();
        return set.add(str2);
    }

    public void a(String str, Set<String> set) {
        this.a.put(str, new HashSet(set));
        d();
    }

    public long a() {
        return this.b.get();
    }

    public void b() {
        d();
    }

    private void d() {
        this.b.set(System.currentTimeMillis());
    }
}
