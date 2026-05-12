package com.google.android.gms.internal;

import com.google.ads.AdRequest$ErrorCode;
import com.google.android.gms.ads.internal.util.client.zzb;

class zzgw$5 implements Runnable {
    final /* synthetic */ zzgw zzbpq;
    final /* synthetic */ AdRequest$ErrorCode zzbpr;

    zzgw$5(zzgw com_google_android_gms_internal_zzgw, AdRequest$ErrorCode adRequest$ErrorCode) {
        this.zzbpq = com_google_android_gms_internal_zzgw;
        this.zzbpr = adRequest$ErrorCode;
    }

    public void run() {
        try {
            zzgw.zza(this.zzbpq).onAdFailedToLoad(zzgx.zza(this.zzbpr));
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdFailedToLoad.", e);
        }
    }
}
