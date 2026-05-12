package com.amazon.device.ads;

class NumberUtils {
    private NumberUtils() {
    }

    public static int parseInt(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return i;
    }

    public static final long convertToMillisecondsFromNanoseconds(long j) {
        return j / 1000000;
    }

    public static final long convertToMillisecondsFromSeconds(long j) {
        return 1000 * j;
    }
}
