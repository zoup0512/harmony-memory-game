package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzl;
import com.google.android.gms.ads.internal.zzu;

class zzip$5 implements Runnable {
    final /* synthetic */ AdRequestInfoParcel zzcec;
    final /* synthetic */ zzip zzcej;
    final /* synthetic */ zzl zzcek;

    zzip$5(zzip com_google_android_gms_internal_zzip, AdRequestInfoParcel adRequestInfoParcel, zzl com_google_android_gms_ads_internal_request_zzl) {
        this.zzcej = com_google_android_gms_internal_zzip;
        this.zzcec = adRequestInfoParcel;
        this.zzcek = com_google_android_gms_ads_internal_request_zzl;
    }

    public void run() {
        AdResponseParcel zzd;
        try {
            zzd = this.zzcej.zzd(this.zzcec);
        } catch (Throwable e) {
            zzu.zzft().zzb(e, true);
            zzkd.zzd("Could not fetch ad response due to an Exception.", e);
            zzd = null;
        }
        if (zzd == null) {
            zzd = new AdResponseParcel(0);
        }
        try {
            this.zzcek.zzb(zzd);
        } catch (Throwable e2) {
            zzkd.zzd("Fail to forward ad response.", e2);
        }
    }
}
