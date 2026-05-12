package com.appodeal.ads.f;

public enum b {
    LESS("<"),
    LESS_EQUALS("<="),
    EQUALS("=="),
    NOT_EQUALS("!="),
    MORE_EQUALS(">="),
    MORE(">"),
    IN("IN");
    
    private final String h;

    private b(String str) {
        this.h = str;
    }

    static b a(String str) {
        for (b bVar : values()) {
            if (bVar.h.equals(str)) {
                return bVar;
            }
        }
        return null;
    }
}
