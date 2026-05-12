package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Libraries.g.e;

public interface a {
    public static final com.chartboost.sdk.Libraries.g.a a = g.b(g.b(), new e() {
        public boolean a(Object obj) {
            int intValue = ((Number) obj).intValue();
            return intValue >= 200 && intValue < 300;
        }

        public String a() {
            return "Must be a valid status code (>=200 && <300)";
        }
    });
}
