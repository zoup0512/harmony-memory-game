package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.internal.zzlh;

class zzk$1 implements Runnable {
    final /* synthetic */ zzi zzbgz;
    final /* synthetic */ zzk zzbha;

    zzk$1(zzk com_google_android_gms_ads_internal_formats_zzk, zzi com_google_android_gms_ads_internal_formats_zzi) {
        this.zzbha = com_google_android_gms_ads_internal_formats_zzk;
        this.zzbgz = com_google_android_gms_ads_internal_formats_zzi;
    }

    public void run() {
        zzlh zzlb = this.zzbgz.zzlb();
        if (zzlb != null && zzk.zza(this.zzbha) != null) {
            zzk.zza(this.zzbha).addView(zzlb.getView());
        }
    }
}
