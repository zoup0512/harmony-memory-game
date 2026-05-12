package org.nexage.sourcekit.util;

import android.util.Log;

public class VASTLog {
    private static LOG_LEVEL LEVEL = LOG_LEVEL.error;
    private static String TAG = "VAST";

    public enum LOG_LEVEL {
        verbose(1),
        debug(2),
        info(3),
        warning(4),
        error(5),
        none(6);
        
        private int value;

        private LOG_LEVEL(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void v(String str, String str2) {
        if (LEVEL.getValue() <= LOG_LEVEL.verbose.getValue()) {
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (LEVEL.getValue() <= LOG_LEVEL.debug.getValue()) {
            Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (LEVEL.getValue() <= LOG_LEVEL.info.getValue()) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (LEVEL.getValue() <= LOG_LEVEL.warning.getValue()) {
            Log.w(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (LEVEL.getValue() <= LOG_LEVEL.error.getValue()) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (LEVEL.getValue() <= LOG_LEVEL.error.getValue()) {
            Log.e(str, str2, th);
        }
    }

    public static void setLoggingLevel(LOG_LEVEL log_level) {
        Log.i(TAG, "Changing logging level from :" + LEVEL + ". To:" + log_level);
        LEVEL = log_level;
    }

    public static LOG_LEVEL getLoggingLevel() {
        return LEVEL;
    }
}
