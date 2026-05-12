package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.internal.zzec.zza;

@zzin
public class zzeh extends zza {
    private final OnContentAdLoadedListener zzbhj;

    public zzeh(OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzbhj = onContentAdLoadedListener;
    }

    public void zza(zzdx com_google_android_gms_internal_zzdx) {
        this.zzbhj.onContentAdLoaded(zzb(com_google_android_gms_internal_zzdx));
    }

    zzdy zzb(zzdx com_google_android_gms_internal_zzdx) {
        return new zzdy(com_google_android_gms_internal_zzdx);
    }
}
