package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzlh;

@zzin
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final zzf CREATOR = new zzf();
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final VersionInfoParcel zzaow;
    public final AdLauncherIntentInfoParcel zzbtj;
    public final zza zzbtk;
    public final zzg zzbtl;
    public final zzlh zzbtm;
    public final zzel zzbtn;
    public final String zzbto;
    public final boolean zzbtp;
    public final String zzbtq;
    public final zzp zzbtr;
    public final int zzbts;
    public final zzer zzbtt;
    public final String zzbtu;
    public final InterstitialAdParameterParcel zzbtv;

    AdOverlayInfoParcel(int i, AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, VersionInfoParcel versionInfoParcel, IBinder iBinder6, String str4, InterstitialAdParameterParcel interstitialAdParameterParcel) {
        this.versionCode = i;
        this.zzbtj = adLauncherIntentInfoParcel;
        this.zzbtk = (zza) zze.zzad(zzd.zza.zzfc(iBinder));
        this.zzbtl = (zzg) zze.zzad(zzd.zza.zzfc(iBinder2));
        this.zzbtm = (zzlh) zze.zzad(zzd.zza.zzfc(iBinder3));
        this.zzbtn = (zzel) zze.zzad(zzd.zza.zzfc(iBinder4));
        this.zzbto = str;
        this.zzbtp = z;
        this.zzbtq = str2;
        this.zzbtr = (zzp) zze.zzad(zzd.zza.zzfc(iBinder5));
        this.orientation = i2;
        this.zzbts = i3;
        this.url = str3;
        this.zzaow = versionInfoParcel;
        this.zzbtt = (zzer) zze.zzad(zzd.zza.zzfc(iBinder6));
        this.zzbtu = str4;
        this.zzbtv = interstitialAdParameterParcel;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlh com_google_android_gms_internal_zzlh, int i, VersionInfoParcel versionInfoParcel, String str, InterstitialAdParameterParcel interstitialAdParameterParcel) {
        this.versionCode = 4;
        this.zzbtj = null;
        this.zzbtk = com_google_android_gms_ads_internal_client_zza;
        this.zzbtl = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbtm = com_google_android_gms_internal_zzlh;
        this.zzbtn = null;
        this.zzbto = null;
        this.zzbtp = false;
        this.zzbtq = null;
        this.zzbtr = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbts = 1;
        this.url = null;
        this.zzaow = versionInfoParcel;
        this.zzbtt = null;
        this.zzbtu = str;
        this.zzbtv = interstitialAdParameterParcel;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlh com_google_android_gms_internal_zzlh, boolean z, int i, VersionInfoParcel versionInfoParcel) {
        this.versionCode = 4;
        this.zzbtj = null;
        this.zzbtk = com_google_android_gms_ads_internal_client_zza;
        this.zzbtl = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbtm = com_google_android_gms_internal_zzlh;
        this.zzbtn = null;
        this.zzbto = null;
        this.zzbtp = z;
        this.zzbtq = null;
        this.zzbtr = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbts = 2;
        this.url = null;
        this.zzaow = versionInfoParcel;
        this.zzbtt = null;
        this.zzbtu = null;
        this.zzbtv = null;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzel com_google_android_gms_internal_zzel, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlh com_google_android_gms_internal_zzlh, boolean z, int i, String str, VersionInfoParcel versionInfoParcel, zzer com_google_android_gms_internal_zzer) {
        this.versionCode = 4;
        this.zzbtj = null;
        this.zzbtk = com_google_android_gms_ads_internal_client_zza;
        this.zzbtl = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbtm = com_google_android_gms_internal_zzlh;
        this.zzbtn = com_google_android_gms_internal_zzel;
        this.zzbto = null;
        this.zzbtp = z;
        this.zzbtq = null;
        this.zzbtr = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbts = 3;
        this.url = str;
        this.zzaow = versionInfoParcel;
        this.zzbtt = com_google_android_gms_internal_zzer;
        this.zzbtu = null;
        this.zzbtv = null;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzel com_google_android_gms_internal_zzel, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlh com_google_android_gms_internal_zzlh, boolean z, int i, String str, String str2, VersionInfoParcel versionInfoParcel, zzer com_google_android_gms_internal_zzer) {
        this.versionCode = 4;
        this.zzbtj = null;
        this.zzbtk = com_google_android_gms_ads_internal_client_zza;
        this.zzbtl = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbtm = com_google_android_gms_internal_zzlh;
        this.zzbtn = com_google_android_gms_internal_zzel;
        this.zzbto = str2;
        this.zzbtp = z;
        this.zzbtq = str;
        this.zzbtr = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbts = 3;
        this.url = null;
        this.zzaow = versionInfoParcel;
        this.zzbtt = com_google_android_gms_internal_zzer;
        this.zzbtu = null;
        this.zzbtv = null;
    }

    public AdOverlayInfoParcel(AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzp com_google_android_gms_ads_internal_overlay_zzp, VersionInfoParcel versionInfoParcel) {
        this.versionCode = 4;
        this.zzbtj = adLauncherIntentInfoParcel;
        this.zzbtk = com_google_android_gms_ads_internal_client_zza;
        this.zzbtl = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbtm = null;
        this.zzbtn = null;
        this.zzbto = null;
        this.zzbtp = false;
        this.zzbtq = null;
        this.zzbtr = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = -1;
        this.zzbts = 4;
        this.url = null;
        this.zzaow = versionInfoParcel;
        this.zzbtt = null;
        this.zzbtu = null;
        this.zzbtv = null;
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzb(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    IBinder zzoc() {
        return zze.zzac(this.zzbtk).asBinder();
    }

    IBinder zzod() {
        return zze.zzac(this.zzbtl).asBinder();
    }

    IBinder zzoe() {
        return zze.zzac(this.zzbtm).asBinder();
    }

    IBinder zzof() {
        return zze.zzac(this.zzbtn).asBinder();
    }

    IBinder zzog() {
        return zze.zzac(this.zzbtt).asBinder();
    }

    IBinder zzoh() {
        return zze.zzac(this.zzbtr).asBinder();
    }
}
