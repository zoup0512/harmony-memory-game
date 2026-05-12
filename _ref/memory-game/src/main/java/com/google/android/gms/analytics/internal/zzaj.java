package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvw;

public final class zzaj {
    static Object zzamr = new Object();
    static zzvw zzcrw;
    static Boolean zzcrx;

    public static boolean zzav(Context context) {
        zzab.zzy(context);
        if (zzcrx != null) {
            return zzcrx.booleanValue();
        }
        boolean zzb = zzao.zzb(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzcrx = Boolean.valueOf(zzb);
        return zzb;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onReceive(Context context, Intent intent) {
        zzf zzay = zzf.zzay(context);
        zzaf zzyx = zzay.zzyx();
        if (intent == null) {
            zzyx.zzek("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (zzay.zzyy().zzabc()) {
            zzyx.zza("Device AnalyticsReceiver got", action);
        } else {
            zzyx.zza("Local AnalyticsReceiver got", action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zzaw = zzak.zzaw(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (zzamr) {
                context.startService(intent2);
                if (zzaw) {
                    try {
                        if (zzcrw == null) {
                            zzcrw = new zzvw(context, 1, "Analytics WakeLock");
                            zzcrw.setReferenceCounted(false);
                        }
                        zzcrw.acquire(1000);
                    } catch (SecurityException e) {
                        zzyx.zzek("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                    return;
                }
            }
        }
    }
}
