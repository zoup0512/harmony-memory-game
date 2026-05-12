package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvw;

public final class zzak {
    private static Boolean zzcry;
    private final zza an;
    private final Context mContext;
    private final Handler mHandler = new Handler();

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzak(zza com_google_android_gms_analytics_internal_zzak_zza) {
        this.mContext = com_google_android_gms_analytics_internal_zzak_zza.getContext();
        zzab.zzy(this.mContext);
        this.an = com_google_android_gms_analytics_internal_zzak_zza;
    }

    public static boolean zzaw(Context context) {
        zzab.zzy(context);
        if (zzcry != null) {
            return zzcry.booleanValue();
        }
        boolean zzj = zzao.zzj(context, "com.google.android.gms.analytics.AnalyticsService");
        zzcry = Boolean.valueOf(zzj);
        return zzj;
    }

    private void zzvw() {
        try {
            synchronized (zzaj.zzamr) {
                zzvw com_google_android_gms_internal_zzvw = zzaj.zzcrw;
                if (com_google_android_gms_internal_zzvw != null && com_google_android_gms_internal_zzvw.isHeld()) {
                    com_google_android_gms_internal_zzvw.release();
                }
            }
        } catch (SecurityException e) {
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onCreate() {
        zzf zzay = zzf.zzay(this.mContext);
        zzaf zzyx = zzay.zzyx();
        if (zzay.zzyy().zzabc()) {
            zzyx.zzeh("Device AnalyticsService is starting up");
        } else {
            zzyx.zzeh("Local AnalyticsService is starting up");
        }
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzf zzay = zzf.zzay(this.mContext);
        zzaf zzyx = zzay.zzyx();
        if (zzay.zzyy().zzabc()) {
            zzyx.zzeh("Device AnalyticsService is shutting down");
        } else {
            zzyx.zzeh("Local AnalyticsService is shutting down");
        }
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzvw();
        final zzf zzay = zzf.zzay(this.mContext);
        final zzaf zzyx = zzay.zzyx();
        if (intent == null) {
            zzyx.zzek("AnalyticsService started with null intent");
        } else {
            String action = intent.getAction();
            if (zzay.zzyy().zzabc()) {
                zzyx.zza("Device AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            } else {
                zzyx.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            }
            if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
                zzay.zzwd().zza(new zzw(this) {
                    final /* synthetic */ zzak ap;

                    public void zzd(Throwable th) {
                        this.ap.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 aq;

                            {
                                this.aq = r1;
                            }

                            public void run() {
                                if (!this.aq.ap.an.callServiceStopSelfResult(i2)) {
                                    return;
                                }
                                if (zzay.zzyy().zzabc()) {
                                    zzyx.zzeh("Device AnalyticsService processed last dispatch request");
                                } else {
                                    zzyx.zzeh("Local AnalyticsService processed last dispatch request");
                                }
                            }
                        });
                    }
                });
            }
        }
        return 2;
    }
}
