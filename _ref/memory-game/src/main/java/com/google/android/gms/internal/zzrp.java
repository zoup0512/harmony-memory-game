package com.google.android.gms.internal;

import android.content.Context;

public class zzrp {
    private static zzrp Bv = new zzrp();
    private zzro Bu = null;

    public static zzro zzcq(Context context) {
        return Bv.zzcp(context);
    }

    public synchronized zzro zzcp(Context context) {
        if (this.Bu == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.Bu = new zzro(context);
        }
        return this.Bu;
    }
}
