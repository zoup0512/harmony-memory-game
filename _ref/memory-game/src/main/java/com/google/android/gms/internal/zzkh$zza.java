package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzkh$zza extends BroadcastReceiver {
    final /* synthetic */ zzkh zzclh;

    private zzkh$zza(zzkh com_google_android_gms_internal_zzkh) {
        this.zzclh = com_google_android_gms_internal_zzkh;
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            zzkh.zza(this.zzclh, true);
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            zzkh.zza(this.zzclh, false);
        }
    }
}
