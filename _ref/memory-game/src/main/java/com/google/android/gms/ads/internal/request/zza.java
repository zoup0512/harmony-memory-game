package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.internal.zzas;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkc;
import java.util.concurrent.Future;

@zzin
public class zza {
    public zzkc zza(Context context, com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, zzas com_google_android_gms_internal_zzas, zza com_google_android_gms_ads_internal_request_zza_zza) {
        zzkc com_google_android_gms_ads_internal_request_zzn = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcar.extras.getBundle("sdk_less_server_data") != null ? new zzn(context, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, com_google_android_gms_ads_internal_request_zza_zza) : new zzb(context, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, com_google_android_gms_internal_zzas, com_google_android_gms_ads_internal_request_zza_zza);
        Future future = (Future) com_google_android_gms_ads_internal_request_zzn.zzpy();
        return com_google_android_gms_ads_internal_request_zzn;
    }
}
