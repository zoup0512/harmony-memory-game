package com.yandex.metrica.impl.utils;

public class e {
    public static int a(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return i;
    }

    public static long a(String str, long j) {
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException e) {
        }
        return j;
    }

    public static Long a(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer b(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
