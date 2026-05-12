package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.zzh;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdn.zza;

@zzin
public final class zzdl extends zza {
    private final zzh zzbek;
    @Nullable
    private final String zzbel;
    private final String zzbem;

    public zzdl(zzh com_google_android_gms_ads_internal_zzh, @Nullable String str, String str2) {
        this.zzbek = com_google_android_gms_ads_internal_zzh;
        this.zzbel = str;
        this.zzbem = str2;
    }

    public String getContent() {
        return this.zzbem;
    }

    public void recordClick() {
        this.zzbek.zzeh();
    }

    public void recordImpression() {
        this.zzbek.zzei();
    }

    public void zzi(@Nullable zzd com_google_android_gms_dynamic_zzd) {
        if (com_google_android_gms_dynamic_zzd != null) {
            this.zzbek.zzc((View) zze.zzad(com_google_android_gms_dynamic_zzd));
        }
    }

    public String zzkk() {
        return this.zzbel;
    }
}
