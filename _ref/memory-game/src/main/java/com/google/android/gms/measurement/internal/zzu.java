package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvw;

public final class zzu {
    static final Object zzamr = new Object();
    static zzvw zzcrw;
    static Boolean zzcrx;

    public static boolean zzav(Context context) {
        zzab.zzy(context);
        if (zzcrx != null) {
            return zzcrx.booleanValue();
        }
        boolean zzb = zzal.zzb(context, "com.google.android.gms.measurement.AppMeasurementReceiver", false);
        zzcrx = Boolean.valueOf(zzb);
        return zzb;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzx zzdo = zzx.zzdo(context);
        zzp zzbsd = zzdo.zzbsd();
        if (intent == null) {
            zzbsd.zzbsx().log("AppMeasurementReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (zzdo.zzbsf().zzabc()) {
            zzbsd.zzbtc().zzj("Device AppMeasurementReceiver got", action);
        } else {
            zzbsd.zzbtc().zzj("Local AppMeasurementReceiver got", action);
        }
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            boolean zzaw = zzae.zzaw(context);
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            synchronized (zzamr) {
                context.startService(className);
                if (zzaw) {
                    try {
                        if (zzcrw == null) {
                            zzcrw = new zzvw(context, 1, "AppMeasurement WakeLock");
                            zzcrw.setReferenceCounted(false);
                        }
                        zzcrw.acquire(1000);
                    } catch (SecurityException e) {
                        zzbsd.zzbsx().log("AppMeasurementService at risk of not starting. For more reliable app measurements, add the WAKE_LOCK permission to your manifest.");
                    }
                    return;
                }
            }
        }
    }
}
