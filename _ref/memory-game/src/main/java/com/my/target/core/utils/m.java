package com.my.target.core.utils;

/* compiled from: UrlUtils */
public final class m {
    private static final String[] a = new String[]{"http://play.google.com", "https://play.google.com", "http://market.android.com", "https://market.android.com", "market://", "samsungapps://"};

    public static boolean a(String str) {
        for (String startsWith : a) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str) {
        return str.startsWith("samsungapps://");
    }
}
