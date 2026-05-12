package com.google.android.gms.measurement;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzae;
import com.google.android.gms.measurement.internal.zzae.zza;

public final class AppMeasurementService extends Service implements zza {
    private zzae ahI;

    private zzae zzbpq() {
        if (this.ahI == null) {
            this.ahI = new zzae(this);
        }
        return this.ahI;
    }

    public boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public Context getContext() {
        return this;
    }

    @MainThread
    public IBinder onBind(Intent intent) {
        return zzbpq().onBind(intent);
    }

    @MainThread
    public void onCreate() {
        super.onCreate();
        zzbpq().onCreate();
    }

    @MainThread
    public void onDestroy() {
        zzbpq().onDestroy();
        super.onDestroy();
    }

    @MainThread
    public void onRebind(Intent intent) {
        zzbpq().onRebind(intent);
    }

    @MainThread
    public int onStartCommand(Intent intent, int i, int i2) {
        return zzbpq().onStartCommand(intent, i, i2);
    }

    @MainThread
    public boolean onUnbind(Intent intent) {
        return zzbpq().onUnbind(intent);
    }
}
