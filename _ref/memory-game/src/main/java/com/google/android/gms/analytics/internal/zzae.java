package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public class zzae {
    private static volatile Logger V;

    static {
        setLogger(new zzs());
    }

    public static Logger getLogger() {
        return V;
    }

    public static void setLogger(Logger logger) {
        V = logger;
    }

    public static void v(String str) {
        zzaf zzadf = zzaf.zzadf();
        if (zzadf != null) {
            zzadf.zzeh(str);
        } else if (zzaz(0)) {
            Log.v((String) zzy.zzczn.get(), str);
        }
        Logger logger = V;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    public static boolean zzaz(int i) {
        return getLogger() != null && getLogger().getLogLevel() <= i;
    }

    public static void zzcw(String str) {
        zzaf zzadf = zzaf.zzadf();
        if (zzadf != null) {
            zzadf.zzej(str);
        } else if (zzaz(1)) {
            Log.i((String) zzy.zzczn.get(), str);
        }
        Logger logger = V;
        if (logger != null) {
            logger.info(str);
        }
    }

    public static void zzcx(String str) {
        zzaf zzadf = zzaf.zzadf();
        if (zzadf != null) {
            zzadf.zzek(str);
        } else if (zzaz(2)) {
            Log.w((String) zzy.zzczn.get(), str);
        }
        Logger logger = V;
        if (logger != null) {
            logger.warn(str);
        }
    }

    public static void zzf(String str, Object obj) {
        zzaf zzadf = zzaf.zzadf();
        if (zzadf != null) {
            zzadf.zze(str, obj);
        } else if (zzaz(3)) {
            String stringBuilder;
            if (obj != null) {
                String valueOf = String.valueOf(obj);
                stringBuilder = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()).append(str).append(":").append(valueOf).toString();
            } else {
                stringBuilder = str;
            }
            Log.e((String) zzy.zzczn.get(), stringBuilder);
        }
        Logger logger = V;
        if (logger != null) {
            logger.error(str);
        }
    }
}
