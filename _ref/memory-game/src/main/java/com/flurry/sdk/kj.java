package com.flurry.sdk;

import java.util.Comparator;

public class kj implements Comparator<Runnable> {
    private static final String a = kj.class.getSimpleName();

    public /* synthetic */ int compare(Object obj, Object obj2) {
        Runnable runnable = (Runnable) obj2;
        int a = a((Runnable) obj);
        int a2 = a(runnable);
        if (a < a2) {
            return -1;
        }
        if (a > a2) {
            return 1;
        }
        return 0;
    }

    private static int a(Runnable runnable) {
        if (runnable == null) {
            return Integer.MAX_VALUE;
        }
        if (runnable instanceof kk) {
            int i;
            mb mbVar = (mb) ((kk) runnable).a();
            if (mbVar != null) {
                i = mbVar.u;
            } else {
                i = Integer.MAX_VALUE;
            }
            return i;
        } else if (runnable instanceof mb) {
            return ((mb) runnable).u;
        } else {
            km.a(6, a, "Unknown runnable class: " + runnable.getClass().getName());
            return Integer.MAX_VALUE;
        }
    }
}
