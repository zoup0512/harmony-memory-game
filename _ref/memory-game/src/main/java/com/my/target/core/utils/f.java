package com.my.target.core.utils;

import java.security.MessageDigest;

/* compiled from: EncryptionUtils */
public final class f {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("md5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuilder.toString().toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }
}
