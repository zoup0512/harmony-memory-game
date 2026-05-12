package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.internal.zzdo.zza;

@zzin
public final class zzdp extends zza {
    private final OnCustomRenderedAdLoadedListener zzawi;

    public zzdp(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzawi = onCustomRenderedAdLoadedListener;
    }

    public void zza(zzdn com_google_android_gms_internal_zzdn) {
        this.zzawi.onCustomRenderedAdLoaded(new zzdm(com_google_android_gms_internal_zzdn));
    }
}
