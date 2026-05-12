package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvw;

public final class zzae {
    private static Boolean zzcry;
    private final zza amc;
    private final Context mContext;
    private final Handler mHandler = new Handler();

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzae(zza com_google_android_gms_measurement_internal_zzae_zza) {
        this.mContext = com_google_android_gms_measurement_internal_zzae_zza.getContext();
        zzab.zzy(this.mContext);
        this.amc = com_google_android_gms_measurement_internal_zzae_zza;
    }

    public static boolean zzaw(Context context) {
        zzab.zzy(context);
        if (zzcry != null) {
            return zzcry.booleanValue();
        }
        boolean zzj = zzal.zzj(context, "com.google.android.gms.measurement.AppMeasurementService");
        zzcry = Boolean.valueOf(zzj);
        return zzj;
    }

    private zzp zzbsd() {
        return zzx.zzdo(this.mContext).zzbsd();
    }

    private void zzvw() {
        try {
            synchronized (zzu.zzamr) {
                zzvw com_google_android_gms_internal_zzvw = zzu.zzcrw;
                if (com_google_android_gms_internal_zzvw != null && com_google_android_gms_internal_zzvw.isHeld()) {
                    com_google_android_gms_internal_zzvw.release();
                }
            }
        } catch (SecurityException e) {
        }
    }

    @MainThread
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            zzbsd().zzbsv().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzy(zzx.zzdo(this.mContext));
        }
        zzbsd().zzbsx().zzj("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public void onCreate() {
        zzx zzdo = zzx.zzdo(this.mContext);
        zzp zzbsd = zzdo.zzbsd();
        if (zzdo.zzbsf().zzabc()) {
            zzbsd.zzbtc().log("Device AppMeasurementService is starting up");
        } else {
            zzbsd.zzbtc().log("Local AppMeasurementService is starting up");
        }
    }

    @MainThread
    public void onDestroy() {
        zzx zzdo = zzx.zzdo(this.mContext);
        zzp zzbsd = zzdo.zzbsd();
        if (zzdo.zzbsf().zzabc()) {
            zzbsd.zzbtc().log("Device AppMeasurementService is shutting down");
        } else {
            zzbsd.zzbtc().log("Local AppMeasurementService is shutting down");
        }
    }

    @MainThread
    public void onRebind(Intent intent) {
        if (intent == null) {
            zzbsd().zzbsv().log("onRebind called with null intent");
            return;
        }
        zzbsd().zzbtc().zzj("onRebind called. action", intent.getAction());
    }

    @MainThread
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzvw();
        final zzx zzdo = zzx.zzdo(this.mContext);
        final zzp zzbsd = zzdo.zzbsd();
        if (intent == null) {
            zzbsd.zzbsx().log("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            if (zzdo.zzbsf().zzabc()) {
                zzbsd.zzbtc().zze("Device AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            } else {
                zzbsd.zzbtc().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            }
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zzdo.zzbsc().zzm(new Runnable(this) {
                    final /* synthetic */ zzae amf;

                    public void run() {
                        zzdo.zzbuh();
                        zzdo.zzbuc();
                        this.amf.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 amg;

                            {
                                this.amg = r1;
                            }

                            public void run() {
                                if (!this.amg.amf.amc.callServiceStopSelfResult(i2)) {
                                    return;
                                }
                                if (zzdo.zzbsf().zzabc()) {
                                    zzbsd.zzbtc().log("Device AppMeasurementService processed last upload request");
                                } else {
                                    zzbsd.zzbtc().log("Local AppMeasurementService processed last upload request");
                                }
                            }
                        });
                    }
                });
            }
        }
        return 2;
    }

    @MainThread
    public boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzbsd().zzbsv().log("onUnbind called with null intent");
        } else {
            zzbsd().zzbtc().zzj("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }
}
