package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.amazonaws.services.s3.internal.Constants;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.zzs;

@zzin
public class zzic {
    public zzkj zza(Context context, zza com_google_android_gms_ads_internal_zza, zzju.zza com_google_android_gms_internal_zzju_zza, zzas com_google_android_gms_internal_zzas, @Nullable zzlh com_google_android_gms_internal_zzlh, zzgj com_google_android_gms_internal_zzgj, zza com_google_android_gms_internal_zzic_zza, zzdk com_google_android_gms_internal_zzdk) {
        zzkj com_google_android_gms_internal_zzif;
        AdResponseParcel adResponseParcel = com_google_android_gms_internal_zzju_zza.zzciq;
        if (adResponseParcel.zzcby) {
            com_google_android_gms_internal_zzif = new zzif(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzgj, com_google_android_gms_internal_zzic_zza, com_google_android_gms_internal_zzdk, com_google_android_gms_internal_zzlh);
        } else if (!adResponseParcel.zzauu) {
            com_google_android_gms_internal_zzif = adResponseParcel.zzcce ? new zzia(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzic_zza) : (((Boolean) zzdc.zzazs.get()).booleanValue() && zzs.zzavu() && !zzs.zzavw() && com_google_android_gms_internal_zzlh != null && com_google_android_gms_internal_zzlh.zzdn().zzaus) ? new zzie(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzic_zza) : new zzid(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzic_zza);
        } else if (com_google_android_gms_ads_internal_zza instanceof zzq) {
            com_google_android_gms_internal_zzif = new zzig(context, (zzq) com_google_android_gms_ads_internal_zza, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzas, com_google_android_gms_internal_zzic_zza);
        } else {
            String valueOf = String.valueOf(com_google_android_gms_ads_internal_zza != null ? com_google_android_gms_ads_internal_zza.getClass().getName() : Constants.NULL_VERSION_ID);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 65).append("Invalid NativeAdManager type. Found: ").append(valueOf).append("; Required: NativeAdManager.").toString());
        }
        String str = "AdRenderer: ";
        String valueOf2 = String.valueOf(com_google_android_gms_internal_zzif.getClass().getName());
        zzb.zzcv(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        com_google_android_gms_internal_zzif.zzpy();
        return com_google_android_gms_internal_zzif;
    }

    public zzkj zza(Context context, zzju.zza com_google_android_gms_internal_zzju_zza, zzjf com_google_android_gms_internal_zzjf) {
        zzkj com_google_android_gms_internal_zzjl = new zzjl(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzjf);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(com_google_android_gms_internal_zzjl.getClass().getName());
        zzb.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        com_google_android_gms_internal_zzjl.zzpy();
        return com_google_android_gms_internal_zzjl;
    }
}
