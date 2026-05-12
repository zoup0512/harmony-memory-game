package com.flurry.sdk;

import android.text.TextUtils;
import java.util.Arrays;

public class jj {
    public static String a = jj.class.getName();

    public static String a(String str) {
        String str2 = "a=" + jy.a().d;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String str3 = "cid=" + b(str);
        return String.format("%s&%s", new Object[]{str2, str3});
    }

    private static String b(String str) {
        byte[] bArr;
        if (str == null || str.trim().length() <= 0) {
            bArr = null;
        } else {
            try {
                bArr = ly.e(str);
                if (bArr == null || bArr.length != 20) {
                    km.a(6, a, "sha1 is not 20 bytes long: " + Arrays.toString(bArr));
                    bArr = null;
                } else {
                    try {
                        km.a(5, a, "syndication hashedId is:" + new String(bArr));
                    } catch (Exception e) {
                        km.a(6, a, "Exception in getHashedSyndicationIdString()");
                        return ly.a(bArr);
                    }
                }
            } catch (Exception e2) {
                bArr = null;
                km.a(6, a, "Exception in getHashedSyndicationIdString()");
                return ly.a(bArr);
            }
        }
        return ly.a(bArr);
    }
}
