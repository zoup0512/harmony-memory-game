package com.yandex.metrica;

import com.facebook.places.model.PlaceFields;

public enum a {
    PHONE(PlaceFields.PHONE),
    TABLET("tablet"),
    TV("tv");
    
    private final String d;

    private a(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public static a a(String str) {
        for (a aVar : values()) {
            if (aVar.d.equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}
