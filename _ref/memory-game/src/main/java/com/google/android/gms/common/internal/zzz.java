package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzrp;

public class zzz {
    private static String ze;
    private static int zf;
    private static Object zzamr = new Object();
    private static boolean zzbyu;

    public static String zzcf(Context context) {
        zzch(context);
        return ze;
    }

    public static int zzcg(Context context) {
        zzch(context);
        return zf;
    }

    private static void zzch(Context context) {
        synchronized (zzamr) {
            if (zzbyu) {
                return;
            }
            zzbyu = true;
            try {
                Bundle bundle = zzrp.zzcq(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle == null) {
                    return;
                }
                ze = bundle.getString("com.google.app.id");
                zf = bundle.getInt("com.google.android.gms.version");
            } catch (Throwable e) {
                Log.wtf("MetadataValueReader", "This should never happen.", e);
            }
        }
    }
}
