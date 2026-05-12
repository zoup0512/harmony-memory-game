package com.mopub.common.util;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    private static final AtomicLong sNextGeneratedId = new AtomicLong(1);

    public static String sha1(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            int length = instance.digest().length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(r2[i])}));
            }
            return stringBuilder.toString().toLowerCase(Locale.US);
        } catch (Exception e) {
            return "";
        }
    }

    public static long generateUniqueId() {
        long j;
        long j2;
        do {
            j = sNextGeneratedId.get();
            j2 = j + 1;
            if (j2 > 9223372036854775806L) {
                j2 = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(j, j2));
        return j;
    }

    public static boolean bitMaskContainsFlag(int i, int i2) {
        return (i & i2) != 0;
    }
}
