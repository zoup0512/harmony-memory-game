package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.internal.zzee.zza;

@zzin
public class zzej extends zza {
    private final OnCustomTemplateAdLoadedListener zzbhl;

    public zzej(OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzbhl = onCustomTemplateAdLoadedListener;
    }

    public void zza(zzdz com_google_android_gms_internal_zzdz) {
        this.zzbhl.onCustomTemplateAdLoaded(new zzea(com_google_android_gms_internal_zzdz));
    }
}
