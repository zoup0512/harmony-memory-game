package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzgw$7 implements Runnable {
    final /* synthetic */ zzgw zzbpq;

    zzgw$7(zzgw com_google_android_gms_internal_zzgw) {
        this.zzbpq = com_google_android_gms_internal_zzgw;
    }

    public void run() {
        try {
            zzgw.zza(this.zzbpq).onAdOpened();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdOpened.", e);
        }
    }
}
