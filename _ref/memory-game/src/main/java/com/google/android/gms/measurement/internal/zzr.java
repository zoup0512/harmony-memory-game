package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzab;

class zzr extends BroadcastReceiver {
    static final String Z = zzr.class.getName();
    private boolean aa;
    private boolean ab;
    private final zzx ahD;

    zzr(zzx com_google_android_gms_measurement_internal_zzx) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzx);
        this.ahD = com_google_android_gms_measurement_internal_zzx;
    }

    private Context getContext() {
        return this.ahD.getContext();
    }

    private zzp zzbsd() {
        return this.ahD.zzbsd();
    }

    @WorkerThread
    public boolean isRegistered() {
        this.ahD.zzwu();
        return this.aa;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.ahD.zzzg();
        String action = intent.getAction();
        zzbsd().zzbtc().zzj("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zzadj = this.ahD.zzbts().zzadj();
            if (this.ab != zzadj) {
                this.ab = zzadj;
                this.ahD.zzbsc().zzm(new Runnable(this) {
                    final /* synthetic */ zzr ajM;

                    public void run() {
                        this.ajM.ahD.zzas(zzadj);
                    }
                });
                return;
            }
            return;
        }
        zzbsd().zzbsx().zzj("NetworkBroadcastReceiver received unknown action", action);
    }

    @WorkerThread
    public void unregister() {
        this.ahD.zzzg();
        this.ahD.zzwu();
        if (isRegistered()) {
            zzbsd().zzbtc().log("Unregistering connectivity change receiver");
            this.aa = false;
            this.ab = false;
            try {
                getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                zzbsd().zzbsv().zzj("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @WorkerThread
    public void zzadg() {
        this.ahD.zzzg();
        this.ahD.zzwu();
        if (!this.aa) {
            getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.ab = this.ahD.zzbts().zzadj();
            zzbsd().zzbtc().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.ab));
            this.aa = true;
        }
    }
}
