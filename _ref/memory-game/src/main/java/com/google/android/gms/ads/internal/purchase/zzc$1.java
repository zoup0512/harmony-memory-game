package com.google.android.gms.ads.internal.purchase;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.internal.zzkd;

class zzc$1 implements Runnable {
    final /* synthetic */ Intent val$intent;
    final /* synthetic */ zzf zzbww;
    final /* synthetic */ zzc zzbwx;

    zzc$1(zzc com_google_android_gms_ads_internal_purchase_zzc, zzf com_google_android_gms_ads_internal_purchase_zzf, Intent intent) {
        this.zzbwx = com_google_android_gms_ads_internal_purchase_zzc;
        this.zzbww = com_google_android_gms_ads_internal_purchase_zzf;
        this.val$intent = intent;
    }

    public void run() {
        try {
            if (zzc.zza(this.zzbwx).zza(this.zzbww.zzbxg, -1, this.val$intent)) {
                zzc.zzc(this.zzbwx).zza(new zzg(zzc.zzb(this.zzbwx), this.zzbww.zzbxh, true, -1, this.val$intent, this.zzbww));
            } else {
                zzc.zzc(this.zzbwx).zza(new zzg(zzc.zzb(this.zzbwx), this.zzbww.zzbxh, false, -1, this.val$intent, this.zzbww));
            }
        } catch (RemoteException e) {
            zzkd.zzcx("Fail to verify and dispatch pending transaction");
        }
    }
}
