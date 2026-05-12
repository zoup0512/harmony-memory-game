package com.my.target;

import android.util.Log;
import com.amazonaws.services.s3.internal.Constants;

public class Tracer {
    private static final String TAG = "[myTarget]";
    public static boolean enabled = false;

    public static void d(String str) {
        if (enabled) {
            String str2 = TAG;
            if (str == null) {
                str = Constants.NULL_VERSION_ID;
            }
            Log.d(str2, str);
        }
    }

    public static void e(String str) {
        if (enabled) {
            String str2 = TAG;
            if (str == null) {
                str = Constants.NULL_VERSION_ID;
            }
            Log.e(str2, str);
        }
    }

    public static void i(String str) {
        String str2 = TAG;
        if (str == null) {
            str = Constants.NULL_VERSION_ID;
        }
        Log.i(str2, str);
    }

    private Tracer() {
    }
}
