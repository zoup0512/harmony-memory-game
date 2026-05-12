package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvw;

public class CampaignTrackingService extends Service {
    private static Boolean zzcry;
    private Handler mHandler;

    private Handler getHandler() {
        Handler handler = this.mHandler;
        if (handler != null) {
            return handler;
        }
        handler = new Handler(getMainLooper());
        this.mHandler = handler;
        return handler;
    }

    public static boolean zzaw(Context context) {
        zzab.zzy(context);
        if (zzcry != null) {
            return zzcry.booleanValue();
        }
        boolean zzj = zzao.zzj(context, "com.google.android.gms.analytics.CampaignTrackingService");
        zzcry = Boolean.valueOf(zzj);
        return zzj;
    }

    private void zzvw() {
        try {
            synchronized (CampaignTrackingReceiver.zzamr) {
                zzvw com_google_android_gms_internal_zzvw = CampaignTrackingReceiver.zzcrw;
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
        super.onCreate();
        zzf.zzay(this).zzyx().zzeh("CampaignTrackingService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzf.zzay(this).zzyx().zzeh("CampaignTrackingService is shutting down");
        super.onDestroy();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzvw();
        zzf zzay = zzf.zzay(this);
        final zzaf zzyx = zzay.zzyx();
        String str = null;
        if (zzay.zzyy().zzabc()) {
            zzyx.zzel("Unexpected installation campaign (package side)");
        } else {
            str = intent.getStringExtra("referrer");
        }
        final Handler handler = getHandler();
        if (TextUtils.isEmpty(str)) {
            if (!zzay.zzyy().zzabc()) {
                zzyx.zzek("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
            }
            zzay.zzyz().zzg(new Runnable(this) {
                final /* synthetic */ CampaignTrackingService zzcsb;

                public void run() {
                    this.zzcsb.zza(zzyx, handler, i2);
                }
            });
        } else {
            int zzabg = zzay.zzyy().zzabg();
            if (str.length() > zzabg) {
                zzyx.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(str.length()), Integer.valueOf(zzabg));
                str = str.substring(0, zzabg);
            }
            zzyx.zza("CampaignTrackingService called. startId, campaign", Integer.valueOf(i2), str);
            zzay.zzwd().zza(str, new Runnable(this) {
                final /* synthetic */ CampaignTrackingService zzcsb;

                public void run() {
                    this.zzcsb.zza(zzyx, handler, i2);
                }
            });
        }
        return 2;
    }

    protected void zza(final zzaf com_google_android_gms_analytics_internal_zzaf, Handler handler, final int i) {
        handler.post(new Runnable(this) {
            final /* synthetic */ CampaignTrackingService zzcsb;

            public void run() {
                boolean stopSelfResult = this.zzcsb.stopSelfResult(i);
                if (stopSelfResult) {
                    com_google_android_gms_analytics_internal_zzaf.zza("Install campaign broadcast processed", Boolean.valueOf(stopSelfResult));
                }
            }
        });
    }
}
