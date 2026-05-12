package com.amazon.device.ads;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringUtils {
    private static final String LOGTAG = StringUtils.class.getSimpleName();
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    private StringUtils() {
    }

    public static boolean containsRegEx(String str, String str2) {
        return Pattern.compile(str).matcher(str2).find();
    }

    public static String getFirstMatch(String str, String str2) {
        Matcher matcher = Pattern.compile(str).matcher(str2);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static final boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    public static final boolean isNullOrWhiteSpace(String str) {
        return isNullOrEmpty(str) || str.trim().equals("");
    }

    protected static boolean doesExceptionContainLockedDatabaseMessage(Exception exception) {
        String str = "database is locked";
        return (exception == null || exception.getMessage() == null) ? false : exception.getMessage().contains("database is locked");
    }

    public static String sha1(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toHexString((b & 255) | 256).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readStringFromInputStream(java.io.InputStream r5) {
        /*
        if (r5 != 0) goto L_0x0004;
    L_0x0002:
        r0 = 0;
    L_0x0003:
        return r0;
    L_0x0004:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r1 = new byte[r1];
    L_0x000d:
        r2 = r5.read(r1);	 Catch:{ IOException -> 0x001e }
        r3 = -1;
        if (r2 == r3) goto L_0x002e;
    L_0x0014:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x001e }
        r4 = 0;
        r3.<init>(r1, r4, r2);	 Catch:{ IOException -> 0x001e }
        r0.append(r3);	 Catch:{ IOException -> 0x001e }
        goto L_0x000d;
    L_0x001e:
        r1 = move-exception;
        r1 = logger;	 Catch:{ all -> 0x0044 }
        r2 = "Unable to read the stream.";
        r1.e(r2);	 Catch:{ all -> 0x0044 }
        r5.close();	 Catch:{ IOException -> 0x003b }
    L_0x0029:
        r0 = r0.toString();
        goto L_0x0003;
    L_0x002e:
        r5.close();	 Catch:{ IOException -> 0x0032 }
        goto L_0x0029;
    L_0x0032:
        r1 = move-exception;
        r1 = logger;
        r2 = "IOException while trying to close the stream.";
        r1.e(r2);
        goto L_0x0029;
    L_0x003b:
        r1 = move-exception;
        r1 = logger;
        r2 = "IOException while trying to close the stream.";
        r1.e(r2);
        goto L_0x0029;
    L_0x0044:
        r0 = move-exception;
        r5.close();	 Catch:{ IOException -> 0x0049 }
    L_0x0048:
        throw r0;
    L_0x0049:
        r1 = move-exception;
        r1 = logger;
        r2 = "IOException while trying to close the stream.";
        r1.e(r2);
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.StringUtils.readStringFromInputStream(java.io.InputStream):java.lang.String");
    }
}
