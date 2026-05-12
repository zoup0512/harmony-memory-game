package com.google.android.gms.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzab;

class zzag extends BroadcastReceiver {
    static final String Z = zzag.class.getName();
    private boolean aa;
    private boolean ab;
    private final zzf zzcwp;

    zzag(zzf com_google_android_gms_analytics_internal_zzf) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzf);
        this.zzcwp = com_google_android_gms_analytics_internal_zzf;
    }

    private Context getContext() {
        return this.zzcwp.getContext();
    }

    private void zzadh() {
        zzyx();
        zzwd();
    }

    private zzb zzwd() {
        return this.zzcwp.zzwd();
    }

    private zzaf zzyx() {
        return this.zzcwp.zzyx();
    }

    public boolean isConnected() {
        if (!this.aa) {
            this.zzcwp.zzyx().zzek("Connectivity unknown. Receiver not registered");
        }
        return this.ab;
    }

    public boolean isRegistered() {
        return this.aa;
    }

    public void onReceive(Context context, Intent intent) {
        zzadh();
        String action = intent.getAction();
        this.zzcwp.zzyx().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzadj = zzadj();
            if (this.ab != zzadj) {
                this.ab = zzadj;
                zzwd().zzas(zzadj);
            }
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzcwp.zzyx().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else if (!intent.hasExtra(Z)) {
            zzwd().zzys();
        }
    }

    public void unregister() {
        if (isRegistered()) {
            this.zzcwp.zzyx().zzeh("Unregistering connectivity change receiver");
            this.aa = false;
            this.ab = false;
            try {
                getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                zzyx().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public void zzadg() {
        zzadh();
        if (!this.aa) {
            Context context = getContext();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(this, intentFilter);
            this.ab = zzadj();
            this.zzcwp.zzyx().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.ab));
            this.aa = true;
        }
    }

    public void zzadi() {
        if (VERSION.SDK_INT > 10) {
            Context context = getContext();
            Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
            intent.addCategory(context.getPackageName());
            intent.putExtra(Z, true);
            context.sendOrderedBroadcast(intent, null);
        }
    }

    protected boolean zzadj() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (SecurityException e) {
            return false;
        }
    }
}
