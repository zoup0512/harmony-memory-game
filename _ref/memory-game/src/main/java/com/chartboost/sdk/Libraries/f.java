package com.chartboost.sdk.Libraries;

public enum f {
    PORTRAIT,
    LANDSCAPE,
    PORTRAIT_REVERSE,
    LANDSCAPE_REVERSE;
    
    public static final f e = null;
    public static final f f = null;
    public static final f g = null;
    public static final f h = null;

    static {
        e = PORTRAIT_REVERSE;
        f = PORTRAIT;
        g = LANDSCAPE;
        h = LANDSCAPE_REVERSE;
    }

    public boolean a() {
        return this == PORTRAIT || this == PORTRAIT_REVERSE;
    }

    public boolean b() {
        return this == LANDSCAPE || this == LANDSCAPE_REVERSE;
    }
}
