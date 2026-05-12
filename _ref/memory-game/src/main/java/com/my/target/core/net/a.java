package com.my.target.core.net;

import com.mopub.common.Constants;
import com.my.target.core.utils.g;

/* compiled from: Hosts */
public final class a {
    public static String a = null;
    public static String b = null;
    private static final String c = "https://ad.mail.ru/mobile/";
    private static final String d = "https://r.my.com/mobile/";
    private static final g e = new g(Constants.HTTPS, "8b2824c2cb184ce0ac78b82dba46b78a", "c4d6345aac3a40b58c75761ab14a9ce8", "r.my.com", "6");

    public static String a(String str) {
        if ("appwall".equals(str)) {
            return d;
        }
        return c;
    }

    public static g a() {
        return e;
    }
}
