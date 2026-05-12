package com.appodeal.ads.f;

public enum a {
    ALL("ALL"),
    ANY("ANY");
    
    private final String c;

    private a(String str) {
        this.c = str;
    }

    static a a(String str) {
        for (a aVar : values()) {
            if (aVar.c.equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}
