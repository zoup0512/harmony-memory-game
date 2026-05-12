package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.zzu;

@zzin
public class zzlj {
    public zzlh zza(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzas com_google_android_gms_internal_zzas, VersionInfoParcel versionInfoParcel) {
        return zza(context, adSizeParcel, z, z2, com_google_android_gms_internal_zzas, versionInfoParcel, null, null, null);
    }

    public zzlh zza(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzas com_google_android_gms_internal_zzas, VersionInfoParcel versionInfoParcel, zzdk com_google_android_gms_internal_zzdk, zzs com_google_android_gms_ads_internal_zzs, zzd com_google_android_gms_ads_internal_zzd) {
        zzlh com_google_android_gms_internal_zzlk = new zzlk(zzll.zzb(context, adSizeParcel, z, z2, com_google_android_gms_internal_zzas, versionInfoParcel, com_google_android_gms_internal_zzdk, com_google_android_gms_ads_internal_zzs, com_google_android_gms_ads_internal_zzd));
        com_google_android_gms_internal_zzlk.setWebViewClient(zzu.zzfs().zzb(com_google_android_gms_internal_zzlk, z2));
        com_google_android_gms_internal_zzlk.setWebChromeClient(zzu.zzfs().zzk(com_google_android_gms_internal_zzlk));
        return com_google_android_gms_internal_zzlk;
    }
}
