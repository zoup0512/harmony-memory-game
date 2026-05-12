package com.my.target.core.enums;

import com.facebook.internal.AnalyticsEvents;

/* compiled from: Sections */
public abstract class a {
    public static String a = "standard";
    public static String b = "showcase";
    public static String c = "appwall";
    public static String d = "fullscreen";
    public static String e = "nativeads";
    public static String f = "instreamads";
    public static String g = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
    private static String[] h = new String[]{a, b, c, d, e, f, g};

    public static String a(String str) {
        for (String str2 : h) {
            if (str.indexOf(str2) == 0) {
                return str2;
            }
        }
        return null;
    }
}
