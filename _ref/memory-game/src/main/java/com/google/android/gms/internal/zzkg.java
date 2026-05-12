package com.google.android.gms.internal;

import com.chartboost.sdk.CBLocation;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@zzin
public final class zzkg {
    private static final ExecutorService zzcku = Executors.newFixedThreadPool(10, zzcn(CBLocation.LOCATION_DEFAULT));
    private static final ExecutorService zzckv = Executors.newFixedThreadPool(5, zzcn("Loader"));

    public static zzky<Void> zza(int i, Runnable runnable) {
        return i == 1 ? zza(zzckv, new 1(runnable)) : zza(zzcku, new 2(runnable));
    }

    public static zzky<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzky<T> zza(Callable<T> callable) {
        return zza(zzcku, (Callable) callable);
    }

    public static <T> zzky<T> zza(ExecutorService executorService, Callable<T> callable) {
        Object com_google_android_gms_internal_zzkv = new zzkv();
        try {
            com_google_android_gms_internal_zzkv.zzd(new 4(com_google_android_gms_internal_zzkv, executorService.submit(new 3(com_google_android_gms_internal_zzkv, callable))));
        } catch (Throwable e) {
            zzb.zzd("Thread execution is rejected.", e);
            com_google_android_gms_internal_zzkv.cancel(true);
        }
        return com_google_android_gms_internal_zzkv;
    }

    private static ThreadFactory zzcn(String str) {
        return new 5(str);
    }
}
