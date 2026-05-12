package com.flurry.sdk;

import java.util.HashMap;
import java.util.Map;

public class ka {
    private static final String a = ka.class.getSimpleName();
    private static final HashMap<String, Map<String, String>> b = new HashMap();
    private static ka c;

    public static synchronized ka a() {
        ka kaVar;
        synchronized (ka.class) {
            if (c == null) {
                c = new ka();
            }
            kaVar = c;
        }
        return kaVar;
    }

    public final synchronized void a(String str, String str2, Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        if (map.size() >= 10) {
            km.e(a, "MaxOriginParams exceeded: " + map.size());
        } else {
            map.put("flurryOriginVersion", str2);
            synchronized (b) {
                if (b.size() < 10 || b.containsKey(str)) {
                    b.put(str, map);
                } else {
                    km.e(a, "MaxOrigins exceeded: " + b.size());
                }
            }
        }
    }

    public final synchronized HashMap<String, Map<String, String>> b() {
        HashMap<String, Map<String, String>> hashMap;
        synchronized (b) {
            hashMap = new HashMap(b);
        }
        return hashMap;
    }
}
