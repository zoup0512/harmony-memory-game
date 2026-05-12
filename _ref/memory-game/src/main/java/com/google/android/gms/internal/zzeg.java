package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.internal.zzeb.zza;

@zzin
public class zzeg extends zza {
    private final OnAppInstallAdLoadedListener zzbhi;

    public zzeg(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzbhi = onAppInstallAdLoadedListener;
    }

    public void zza(zzdv com_google_android_gms_internal_zzdv) {
        this.zzbhi.onAppInstallAdLoaded(zzb(com_google_android_gms_internal_zzdv));
    }

    zzdw zzb(zzdv com_google_android_gms_internal_zzdv) {
        return new zzdw(com_google_android_gms_internal_zzdv);
    }
}
