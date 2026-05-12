package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzgw$9 implements Runnable {
    final /* synthetic */ zzgw zzbpq;

    zzgw$9(zzgw com_google_android_gms_internal_zzgw) {
        this.zzbpq = com_google_android_gms_internal_zzgw;
    }

    public void run() {
        try {
            zzgw.zza(this.zzbpq).onAdClosed();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClosed.", e);
        }
    }
}
