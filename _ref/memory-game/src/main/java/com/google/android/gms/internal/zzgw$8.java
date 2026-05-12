package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzgw$8 implements Runnable {
    final /* synthetic */ zzgw zzbpq;

    zzgw$8(zzgw com_google_android_gms_internal_zzgw) {
        this.zzbpq = com_google_android_gms_internal_zzgw;
    }

    public void run() {
        try {
            zzgw.zza(this.zzbpq).onAdLoaded();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLoaded.", e);
        }
    }
}
