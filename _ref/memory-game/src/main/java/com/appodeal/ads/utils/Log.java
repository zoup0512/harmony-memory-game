package com.appodeal.ads.utils;

import com.appodeal.ads.Appodeal;

public class Log {

    public enum LogLevel {
        none(0),
        debug(1),
        verbose(2);
        
        private int a;

        private LogLevel(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }

        public static String[] names() {
            LogLevel[] values = values();
            String[] strArr = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                strArr[i] = values[i].name();
            }
            return strArr;
        }

        public static LogLevel fromInteger(Integer num) {
            if (num == null) {
                return none;
            }
            switch (num.intValue()) {
                case 0:
                    return none;
                case 1:
                    return debug;
                case 2:
                    return verbose;
                default:
                    return none;
            }
        }
    }

    public static void a(String str, LogLevel logLevel) {
        int i = 0;
        if (Appodeal.getLogLevel().getValue() >= logLevel.getValue() && str != null) {
            if (str.length() > 1000) {
                int length = ((str.length() + 1000) - 1) / 1000;
                int i2 = 0;
                while (i2 < length) {
                    android.util.Log.d("Appodeal", str.substring(i, Math.min(str.length(), i + 1000)));
                    i2++;
                    i += 1000;
                }
                return;
            }
            android.util.Log.d("Appodeal", str);
        }
    }

    public static void a(Throwable th) {
        if (Appodeal.getLogLevel().getValue() >= LogLevel.debug.getValue() && th != null) {
            android.util.Log.d("Appodeal", "Exception", th);
        }
    }
}
