package com.google.android.gms.internal;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.concurrent.Callable;

@zzin
public class zzkt {
    public static <T> T zzb(Callable<T> callable) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        T call;
        try {
            StrictMode.setThreadPolicy(new Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            call = callable.call();
            return call;
        } catch (Throwable th) {
            call = th;
            zzb.zzb("Unexpected exception.", call);
            zzu.zzft().zzb((Throwable) call, true);
            return null;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
