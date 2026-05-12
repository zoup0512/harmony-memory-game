package com.flurry.sdk;

public class mf {
    private static final String a = mf.class.getSimpleName();
    private static boolean b;

    public static synchronized void a() {
        synchronized (mf.class) {
            if (!b) {
                ko.a(jk.class);
                try {
                    ko.a(hr.class);
                } catch (NoClassDefFoundError e) {
                    km.a(3, a, "Analytics module not available");
                }
                try {
                    ko.a(md.class);
                } catch (NoClassDefFoundError e2) {
                    km.a(3, a, "Crash module not available");
                }
                try {
                    ko.a(Class.forName("com.flurry.sdk.i"));
                } catch (NoClassDefFoundError e3) {
                    km.a(3, a, "Ads module not available");
                    b = true;
                } catch (ClassNotFoundException e4) {
                    km.a(3, a, "Ads module not available");
                    b = true;
                }
                b = true;
            }
        }
    }
}
