package com.yandex.metrica.impl;

import java.util.regex.Pattern;

public final class be {
    static {
        Pattern.compile("[^0-9a-zA-Z,`’\\.\\+\\-'\\s\"]");
        Pattern.compile("\\s+");
    }

    public static boolean a(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean a(String... strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a : strArr) {
            if (a(a)) {
                return true;
            }
        }
        return false;
    }

    public static String b(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static String c(String str, String str2) {
        return a(str) ? str2 : str;
    }

    public static String b(String str) {
        if (a(str)) {
            return "";
        }
        char charAt = str.charAt(0);
        return !Character.isUpperCase(charAt) ? Character.toUpperCase(charAt) + str.substring(1) : str;
    }

    public static byte[] c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
