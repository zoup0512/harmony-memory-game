package com.google.android.gms.analytics.internal;

public enum zzo {
    NONE,
    GZIP;

    public static zzo zzer(String str) {
        return "GZIP".equalsIgnoreCase(str) ? GZIP : NONE;
    }
}
