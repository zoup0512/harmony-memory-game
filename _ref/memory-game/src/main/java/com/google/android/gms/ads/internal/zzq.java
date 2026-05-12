package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzgn;
import com.google.android.gms.internal.zzgo;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzju.zza;
import com.google.android.gms.internal.zzkh;
import java.util.List;

@zzin
public class zzq extends zzb {
    public zzq(Context context, zzd com_google_android_gms_ads_internal_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgj, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private static zzd zza(zzgn com_google_android_gms_internal_zzgn) throws RemoteException {
        return new zzd(com_google_android_gms_internal_zzgn.getHeadline(), com_google_android_gms_internal_zzgn.getImages(), com_google_android_gms_internal_zzgn.getBody(), com_google_android_gms_internal_zzgn.zzku() != null ? com_google_android_gms_internal_zzgn.zzku() : null, com_google_android_gms_internal_zzgn.getCallToAction(), com_google_android_gms_internal_zzgn.getStarRating(), com_google_android_gms_internal_zzgn.getStore(), com_google_android_gms_internal_zzgn.getPrice(), null, com_google_android_gms_internal_zzgn.getExtras());
    }

    private static zze zza(zzgo com_google_android_gms_internal_zzgo) throws RemoteException {
        return new zze(com_google_android_gms_internal_zzgo.getHeadline(), com_google_android_gms_internal_zzgo.getImages(), com_google_android_gms_internal_zzgo.getBody(), com_google_android_gms_internal_zzgo.zzky() != null ? com_google_android_gms_internal_zzgo.zzky() : null, com_google_android_gms_internal_zzgo.getCallToAction(), com_google_android_gms_internal_zzgo.getAdvertiser(), null, com_google_android_gms_internal_zzgo.getExtras());
    }

    private void zza(zzd com_google_android_gms_ads_internal_formats_zzd) {
        zzkh.zzclc.post(new 2(this, com_google_android_gms_ads_internal_formats_zzd));
    }

    private void zza(zze com_google_android_gms_ads_internal_formats_zze) {
        zzkh.zzclc.post(new 3(this, com_google_android_gms_ads_internal_formats_zze));
    }

    private void zza(zzju com_google_android_gms_internal_zzju, String str) {
        zzkh.zzclc.post(new 4(this, str, com_google_android_gms_internal_zzju));
    }

    public void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public void zza(SimpleArrayMap<String, zzee> simpleArrayMap) {
        zzab.zzhi("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        this.zzajs.zzapn = simpleArrayMap;
    }

    public void zza(zzh com_google_android_gms_ads_internal_formats_zzh) {
        if (this.zzajs.zzapb.zzcie != null) {
            zzu.zzft().zzsu().zza(this.zzajs.zzapa, this.zzajs.zzapb, com_google_android_gms_ads_internal_formats_zzh);
        }
    }

    public void zza(zzdo com_google_android_gms_internal_zzdo) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public void zza(zzho com_google_android_gms_internal_zzho) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public void zza(zza com_google_android_gms_internal_zzju_zza, zzdk com_google_android_gms_internal_zzdk) {
        if (com_google_android_gms_internal_zzju_zza.zzapa != null) {
            this.zzajs.zzapa = com_google_android_gms_internal_zzju_zza.zzapa;
        }
        if (com_google_android_gms_internal_zzju_zza.errorCode != -2) {
            zzkh.zzclc.post(new 1(this, com_google_android_gms_internal_zzju_zza));
            return;
        }
        this.zzajs.zzapw = 0;
        this.zzajs.zzaoz = zzu.zzfp().zza(this.zzajs.zzagf, this, com_google_android_gms_internal_zzju_zza, this.zzajs.zzaov, null, this.zzajz, this, com_google_android_gms_internal_zzdk);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(this.zzajs.zzaoz.getClass().getName());
        zzb.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzju com_google_android_gms_internal_zzju, boolean z) {
        return this.zzajr.zzfc();
    }

    protected boolean zza(zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        zzgo com_google_android_gms_internal_zzgo = null;
        zzb(null);
        if (this.zzajs.zzgp()) {
            if (com_google_android_gms_internal_zzju2.zzcby) {
                try {
                    zzgn zzmo = com_google_android_gms_internal_zzju2.zzboo != null ? com_google_android_gms_internal_zzju2.zzboo.zzmo() : null;
                    if (com_google_android_gms_internal_zzju2.zzboo != null) {
                        com_google_android_gms_internal_zzgo = com_google_android_gms_internal_zzju2.zzboo.zzmp();
                    }
                    if (zzmo == null || this.zzajs.zzapk == null) {
                        if (com_google_android_gms_internal_zzgo != null) {
                            if (this.zzajs.zzapl != null) {
                                zze zza = zza(com_google_android_gms_internal_zzgo);
                                zza.zzb(new zzg(this.zzajs.zzagf, this, this.zzajs.zzaov, com_google_android_gms_internal_zzgo));
                                zza(zza);
                            }
                        }
                        zzb.zzcx("No matching mapper/listener for retrieved native ad template.");
                        zzh(0);
                        return false;
                    }
                    zzd zza2 = zza(zzmo);
                    zza2.zzb(new zzg(this.zzajs.zzagf, this, this.zzajs.zzaov, zzmo));
                    zza(zza2);
                } catch (Throwable e) {
                    zzb.zzd("Failed to get native ad mapper", e);
                }
            } else {
                zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza = com_google_android_gms_internal_zzju2.zzcim;
                if ((com_google_android_gms_ads_internal_formats_zzh_zza instanceof zze) && this.zzajs.zzapl != null) {
                    zza((zze) com_google_android_gms_internal_zzju2.zzcim);
                } else if ((com_google_android_gms_ads_internal_formats_zzh_zza instanceof zzd) && this.zzajs.zzapk != null) {
                    zza((zzd) com_google_android_gms_internal_zzju2.zzcim);
                } else if (!(com_google_android_gms_ads_internal_formats_zzh_zza instanceof zzf) || this.zzajs.zzapn == null || this.zzajs.zzapn.get(((zzf) com_google_android_gms_ads_internal_formats_zzh_zza).getCustomTemplateId()) == null) {
                    zzb.zzcx("No matching listener for retrieved native ad template.");
                    zzh(0);
                    return false;
                } else {
                    zza(com_google_android_gms_internal_zzju2, ((zzf) com_google_android_gms_ads_internal_formats_zzh_zza).getCustomTemplateId());
                }
            }
            return super.zza(com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzju2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    public void zzb(SimpleArrayMap<String, zzed> simpleArrayMap) {
        zzab.zzhi("setOnCustomClickListener must be called on the main UI thread.");
        this.zzajs.zzapm = simpleArrayMap;
    }

    public void zzb(NativeAdOptionsParcel nativeAdOptionsParcel) {
        zzab.zzhi("setNativeAdOptions must be called on the main UI thread.");
        this.zzajs.zzapo = nativeAdOptionsParcel;
    }

    public void zzb(zzeb com_google_android_gms_internal_zzeb) {
        zzab.zzhi("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        this.zzajs.zzapk = com_google_android_gms_internal_zzeb;
    }

    public void zzb(zzec com_google_android_gms_internal_zzec) {
        zzab.zzhi("setOnContentAdLoadedListener must be called on the main UI thread.");
        this.zzajs.zzapl = com_google_android_gms_internal_zzec;
    }

    public void zzb(@Nullable List<String> list) {
        zzab.zzhi("setNativeTemplates must be called on the main UI thread.");
        this.zzajs.zzaps = list;
    }

    public SimpleArrayMap<String, zzee> zzfb() {
        zzab.zzhi("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzajs.zzapn;
    }

    @Nullable
    public zzed zzv(String str) {
        zzab.zzhi("getOnCustomClickListener must be called on the main UI thread.");
        return (zzed) this.zzajs.zzapm.get(str);
    }
}
