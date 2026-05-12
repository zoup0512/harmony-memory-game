package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.ads.internal.client.zzx.zza;
import com.google.android.gms.ads.internal.client.zzz;
import com.google.android.gms.ads.internal.formats.zzk;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzfn;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzhi;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzje;

@Keep
@DynamiteApi
@zzin
public class ClientApi extends zza {
    public zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzgj com_google_android_gms_internal_zzgj, int i) {
        return new zzk((Context) zze.zzad(com_google_android_gms_dynamic_zzd), str, com_google_android_gms_internal_zzgj, new VersionInfoParcel(com.google.android.gms.common.internal.zze.xM, i, true), zzd.zzek());
    }

    public zzhi createAdOverlay(zzd com_google_android_gms_dynamic_zzd) {
        return new com.google.android.gms.ads.internal.overlay.zzd((Activity) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
        return new zzf((Context) zze.zzad(com_google_android_gms_dynamic_zzd), adSizeParcel, str, com_google_android_gms_internal_zzgj, new VersionInfoParcel(com.google.android.gms.common.internal.zze.xM, i, true), zzd.zzek());
    }

    public zzhp createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) {
        return new com.google.android.gms.ads.internal.purchase.zze((Activity) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
        Context context = (Context) zze.zzad(com_google_android_gms_dynamic_zzd);
        zzdc.initialize(context);
        VersionInfoParcel versionInfoParcel = new VersionInfoParcel(com.google.android.gms.common.internal.zze.xM, i, true);
        boolean equals = "reward_mb".equals(adSizeParcel.zzaur);
        Object obj = ((equals || !((Boolean) zzdc.zzbae.get()).booleanValue()) && !(equals && ((Boolean) zzdc.zzbaf.get()).booleanValue())) ? null : 1;
        if (obj != null) {
            return new zzfn(context, str, com_google_android_gms_internal_zzgj, versionInfoParcel, zzd.zzek());
        }
        return new zzl(context, adSizeParcel, str, com_google_android_gms_internal_zzgj, versionInfoParcel, zzd.zzek());
    }

    public zzdt createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) {
        return new zzk((FrameLayout) zze.zzad(com_google_android_gms_dynamic_zzd), (FrameLayout) zze.zzad(com_google_android_gms_dynamic_zzd2));
    }

    public zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzgj com_google_android_gms_internal_zzgj, int i) {
        return new zzje((Context) zze.zzad(com_google_android_gms_dynamic_zzd), zzd.zzek(), com_google_android_gms_internal_zzgj, new VersionInfoParcel(com.google.android.gms.common.internal.zze.xM, i, true));
    }

    public zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) throws RemoteException {
        return new zzt((Context) zze.zzad(com_google_android_gms_dynamic_zzd), adSizeParcel, str, new VersionInfoParcel(com.google.android.gms.common.internal.zze.xM, i, true));
    }

    @Nullable
    public zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) {
        return null;
    }

    public zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) {
        return zzo.zza((Context) zze.zzad(com_google_android_gms_dynamic_zzd), new VersionInfoParcel(com.google.android.gms.common.internal.zze.xM, i, true));
    }
}
