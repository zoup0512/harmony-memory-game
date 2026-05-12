package com.google.android.gms.analytics.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.internal.zzab;

public class zzv extends zzd {
    private boolean zzczi;
    private boolean zzczj;
    private AlarmManager zzczk = ((AlarmManager) getContext().getSystemService("alarm"));

    protected zzv(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    private PendingIntent zzacn() {
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public void cancel() {
        zzzg();
        this.zzczj = false;
        this.zzczk.cancel(zzacn());
    }

    public void schedule() {
        zzzg();
        zzab.zza(zzacm(), (Object) "Receiver not registered");
        long zzabl = zzyy().zzabl();
        if (zzabl > 0) {
            cancel();
            long elapsedRealtime = zzyw().elapsedRealtime() + zzabl;
            this.zzczj = true;
            this.zzczk.setInexactRepeating(2, elapsedRealtime, 0, zzacn());
        }
    }

    public boolean zzacm() {
        return this.zzczi;
    }

    public boolean zzfc() {
        return this.zzczj;
    }

    protected void zzwv() {
        try {
            this.zzczk.cancel(zzacn());
            if (zzyy().zzabl() > 0) {
                ActivityInfo receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2);
                if (receiverInfo != null && receiverInfo.enabled) {
                    zzeh("Receiver registered. Using alarm for local dispatch.");
                    this.zzczi = true;
                }
            }
        } catch (NameNotFoundException e) {
        }
    }
}
