package com.google.android.gms.ads.internal;

import java.lang.ref.WeakReference;

class zzr$1 implements Runnable {
    final /* synthetic */ WeakReference zzang;
    final /* synthetic */ zzr zzanh;

    zzr$1(zzr com_google_android_gms_ads_internal_zzr, WeakReference weakReference) {
        this.zzanh = com_google_android_gms_ads_internal_zzr;
        this.zzang = weakReference;
    }

    public void run() {
        zzr.zza(this.zzanh, false);
        zza com_google_android_gms_ads_internal_zza = (zza) this.zzang.get();
        if (com_google_android_gms_ads_internal_zza != null) {
            com_google_android_gms_ads_internal_zza.zzd(zzr.zza(this.zzanh));
        }
    }
}
