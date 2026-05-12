package com.appodeal.ads.networks.vpaid;

import android.util.Log;

public class c {
    private static a a = a.error;

    public enum a {
        verbose(1),
        debug(2),
        info(3),
        warning(4),
        error(5),
        none(6);
        
        private int g;

        private a(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }
    }

    public static void a(String str) {
        if (a.a() <= a.warning.a()) {
            Log.w("VPAIDLog", str);
        }
    }

    public static void a(String str, String str2) {
        if (a.a() <= a.debug.a()) {
            Log.d("VPAIDLog", "[" + str + "] " + str2);
        }
    }

    public static void b(String str, String str2) {
        if (a.a() <= a.verbose.a()) {
            Log.v("VPAIDLog", "[" + str + "] " + str2);
        }
    }

    public static void a(a aVar) {
        Log.i("VPAIDLog", "Changing logging level from :" + a + ". To:" + aVar);
        a = aVar;
    }
}
