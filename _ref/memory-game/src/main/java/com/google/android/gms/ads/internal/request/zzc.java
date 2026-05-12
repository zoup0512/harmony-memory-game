package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.request.zzd.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzla;

@zzin
public final class zzc {
    public static zzkj zza(Context context, VersionInfoParcel versionInfoParcel, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza) {
        return zza(context, versionInfoParcel, com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza, new 1(context));
    }

    static zzkj zza(Context context, VersionInfoParcel versionInfoParcel, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza, zzb com_google_android_gms_ads_internal_request_zzc_zzb) {
        return com_google_android_gms_ads_internal_request_zzc_zzb.zza(versionInfoParcel) ? zza(context, com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza) : zzb(context, versionInfoParcel, com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
    }

    private static zzkj zza(Context context, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza) {
        zzb.zzcv("Fetching ad response from local ad request service.");
        zzkj com_google_android_gms_ads_internal_request_zzd_zza = new zza(context, com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
        Void voidR = (Void) com_google_android_gms_ads_internal_request_zzd_zza.zzpy();
        return com_google_android_gms_ads_internal_request_zzd_zza;
    }

    private static zzkj zzb(Context context, VersionInfoParcel versionInfoParcel, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza) {
        zzb.zzcv("Fetching ad response from remote ad request service.");
        if (zzm.zziw().zzar(context)) {
            return new zzd.zzb(context, versionInfoParcel, com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
        }
        zzb.zzcx("Failed to connect to remote ad request service.");
        return null;
    }
}
