package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzkd;

class zzq$4 implements Runnable {
    final /* synthetic */ zzju zzakt;
    final /* synthetic */ zzq zzamx;
    final /* synthetic */ String zzana;

    zzq$4(zzq com_google_android_gms_ads_internal_zzq, String str, zzju com_google_android_gms_internal_zzju) {
        this.zzamx = com_google_android_gms_ads_internal_zzq;
        this.zzana = str;
        this.zzakt = com_google_android_gms_internal_zzju;
    }

    public void run() {
        try {
            ((zzee) this.zzamx.zzajs.zzapn.get(this.zzana)).zza((zzf) this.zzakt.zzcim);
        } catch (Throwable e) {
            zzkd.zzd("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
        }
    }
}
