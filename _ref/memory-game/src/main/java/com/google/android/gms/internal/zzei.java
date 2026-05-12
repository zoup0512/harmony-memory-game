package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.internal.zzed.zza;

@zzin
public class zzei extends zza {
    private final OnCustomClickListener zzbhk;

    public zzei(OnCustomClickListener onCustomClickListener) {
        this.zzbhk = onCustomClickListener;
    }

    public void zza(zzdz com_google_android_gms_internal_zzdz, String str) {
        this.zzbhk.onCustomClick(new zzea(com_google_android_gms_internal_zzdz), str);
    }
}
