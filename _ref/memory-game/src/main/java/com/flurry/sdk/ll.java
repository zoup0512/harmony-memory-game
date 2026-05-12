package com.flurry.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;

public final class ll extends kg {
    public WeakReference<Context> a;
    public lk b;
    public int c;
    public long d;

    public enum a {
        ;

        public static int[] a() {
            return (int[]) f.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = new int[]{a, b, c, d, e};
        }
    }

    public ll() {
        super("com.flurry.android.sdk.FlurrySessionEvent");
    }
}
