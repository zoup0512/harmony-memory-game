package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzgw$1 implements Runnable {
    final /* synthetic */ zzgw zzbpq;

    zzgw$1(zzgw com_google_android_gms_internal_zzgw) {
        this.zzbpq = com_google_android_gms_internal_zzgw;
    }

    public void run() {
        try {
            zzgw.zza(this.zzbpq).onAdClicked();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClicked.", e);
        }
    }
}
