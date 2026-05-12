package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.zzak;
import com.google.android.gms.analytics.internal.zzak.zza;

public final class AnalyticsService extends Service implements zza {
    private zzak zzcrq;

    private zzak zzvt() {
        if (this.zzcrq == null) {
            this.zzcrq = new zzak(this);
        }
        return this.zzcrq;
    }

    public boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public Context getContext() {
        return this;
    }

    public IBinder onBind(Intent intent) {
        return zzvt().onBind(intent);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onCreate() {
        super.onCreate();
        zzvt().onCreate();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzvt().onDestroy();
        super.onDestroy();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, int i2) {
        return zzvt().onStartCommand(intent, i, i2);
    }
}
