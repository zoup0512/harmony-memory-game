package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.concurrent.Future;

@zzin
public class zzfq {
    private zzfp zza(Context context, VersionInfoParcel versionInfoParcel, zza<zzfp> com_google_android_gms_internal_zzfq_zza_com_google_android_gms_internal_zzfp, zzas com_google_android_gms_internal_zzas) {
        zzfp com_google_android_gms_internal_zzfr = new zzfr(context, versionInfoParcel, com_google_android_gms_internal_zzas);
        com_google_android_gms_internal_zzfq_zza_com_google_android_gms_internal_zzfp.zzblk = com_google_android_gms_internal_zzfr;
        com_google_android_gms_internal_zzfr.zza(new 2(this, com_google_android_gms_internal_zzfq_zza_com_google_android_gms_internal_zzfp));
        return com_google_android_gms_internal_zzfr;
    }

    public Future<zzfp> zza(Context context, VersionInfoParcel versionInfoParcel, String str, zzas com_google_android_gms_internal_zzas) {
        Future com_google_android_gms_internal_zzfq_zza = new zza(null);
        zzkh.zzclc.post(new 1(this, context, versionInfoParcel, com_google_android_gms_internal_zzfq_zza, com_google_android_gms_internal_zzas, str));
        return com_google_android_gms_internal_zzfq_zza;
    }
}
