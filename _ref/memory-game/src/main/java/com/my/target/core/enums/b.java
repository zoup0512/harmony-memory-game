package com.my.target.core.enums;

/* compiled from: VideoSectionNames */
public enum b {
    PREROLL("preroll"),
    POSTROLL("postroll"),
    PAUSEROLL("pauseroll"),
    MIDROLL("midroll");
    
    private static String[] f;
    private final String e;

    static {
        f = new String[]{PREROLL.toString(), POSTROLL.toString(), PAUSEROLL.toString(), MIDROLL.toString()};
    }

    private b(String str) {
        this.e = str;
    }

    public final String toString() {
        return this.e;
    }

    public static String[] a() {
        return (String[]) f.clone();
    }
}
