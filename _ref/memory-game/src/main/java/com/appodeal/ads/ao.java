package com.appodeal.ads;

public class ao {
    public static Boolean a = null;

    public enum a {
        AVAILABLE,
        NOT_AVAILABLE,
        NOT_AVAILABLE_AFTER_DELAY
    }

    public enum b {
        NON_REWARDED,
        REWARDED
    }

    public static boolean a() {
        if (a == null) {
            a = Boolean.valueOf(false);
        }
        return a.booleanValue();
    }
}
