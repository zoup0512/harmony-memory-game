package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgk.zza;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzin
public final class zzgq extends zza {
    private final MediationAdapter zzbpi;
    private zzgr zzbpj;

    public zzgq(MediationAdapter mediationAdapter) {
        this.zzbpi = mediationAdapter;
    }

    private Bundle zza(String str, int i, String str2) throws RemoteException {
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    valueOf = (String) keys.next();
                    bundle2.putString(valueOf, jSONObject.getString(valueOf));
                }
                bundle = bundle2;
            }
            if (this.zzbpi instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            zzb.zzd("Could not get Server Parameters Bundle.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void destroy() throws RemoteException {
        try {
            this.zzbpi.onDestroy();
        } catch (Throwable th) {
            zzb.zzd("Could not destroy adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        if (this.zzbpi instanceof zzlt) {
            return ((zzlt) this.zzbpi).getInterstitialAdapterInfo();
        }
        String str = "MediationAdapter is not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
        zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public zzd getView() throws RemoteException {
        if (this.zzbpi instanceof MediationBannerAdapter) {
            try {
                return zze.zzac(((MediationBannerAdapter) this.zzbpi).getBannerView());
            } catch (Throwable th) {
                zzb.zzd("Could not get banner view from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public boolean isInitialized() throws RemoteException {
        if (this.zzbpi instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzcv("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzbpi).isInitialized();
            } catch (Throwable th) {
                zzb.zzd("Could not check if adapter is initialized.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void pause() throws RemoteException {
        try {
            this.zzbpi.onPause();
        } catch (Throwable th) {
            zzb.zzd("Could not pause adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void resume() throws RemoteException {
        try {
            this.zzbpi.onResume();
        } catch (Throwable th) {
            zzb.zzd("Could not resume adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void showInterstitial() throws RemoteException {
        if (this.zzbpi instanceof MediationInterstitialAdapter) {
            zzb.zzcv("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbpi).showInterstitial();
            } catch (Throwable th) {
                zzb.zzd("Could not show interstitial from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void showVideo() throws RemoteException {
        if (this.zzbpi instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzcv("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzbpi).showVideo();
            } catch (Throwable th) {
                zzb.zzd("Could not show rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void zza(AdRequestParcel adRequestParcel, String str, String str2) throws RemoteException {
        if (this.zzbpi instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzcv("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbpi;
                mediationRewardedVideoAdAdapter.loadAd(new zzgp(adRequestParcel.zzatm == -1 ? null : new Date(adRequestParcel.zzatm), adRequestParcel.zzatn, adRequestParcel.zzato != null ? new HashSet(adRequestParcel.zzato) : null, adRequestParcel.zzatu, adRequestParcel.zzatp, adRequestParcel.zzatq, adRequestParcel.zzaub), zza(str, adRequestParcel.zzatq, str2), adRequestParcel.zzatw != null ? adRequestParcel.zzatw.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not load rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, com.google.android.gms.ads.internal.reward.mediation.client.zza com_google_android_gms_ads_internal_reward_mediation_client_zza, String str2) throws RemoteException {
        if (this.zzbpi instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzcv("Initialize rewarded video adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbpi;
                mediationRewardedVideoAdAdapter.initialize((Context) zze.zzad(com_google_android_gms_dynamic_zzd), new zzgp(adRequestParcel.zzatm == -1 ? null : new Date(adRequestParcel.zzatm), adRequestParcel.zzatn, adRequestParcel.zzato != null ? new HashSet(adRequestParcel.zzato) : null, adRequestParcel.zzatu, adRequestParcel.zzatp, adRequestParcel.zzatq, adRequestParcel.zzaub), str, new com.google.android.gms.ads.internal.reward.mediation.client.zzb(com_google_android_gms_ads_internal_reward_mediation_client_zza), zza(str2, adRequestParcel.zzatq, null), adRequestParcel.zzatw != null ? adRequestParcel.zzatw.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        zza(com_google_android_gms_dynamic_zzd, adRequestParcel, str, null, com_google_android_gms_internal_zzgl);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        if (this.zzbpi instanceof MediationInterstitialAdapter) {
            zzb.zzcv("Requesting interstitial ad from adapter.");
            try {
                MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzbpi;
                mediationInterstitialAdapter.requestInterstitialAd((Context) zze.zzad(com_google_android_gms_dynamic_zzd), new zzgr(com_google_android_gms_internal_zzgl), zza(str, adRequestParcel.zzatq, str2), new zzgp(adRequestParcel.zzatm == -1 ? null : new Date(adRequestParcel.zzatm), adRequestParcel.zzatn, adRequestParcel.zzato != null ? new HashSet(adRequestParcel.zzato) : null, adRequestParcel.zzatu, adRequestParcel.zzatp, adRequestParcel.zzatq, adRequestParcel.zzaub), adRequestParcel.zzatw != null ? adRequestParcel.zzatw.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not request interstitial ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgl com_google_android_gms_internal_zzgl, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) throws RemoteException {
        if (this.zzbpi instanceof MediationNativeAdapter) {
            try {
                MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzbpi;
                zzgu com_google_android_gms_internal_zzgu = new zzgu(adRequestParcel.zzatm == -1 ? null : new Date(adRequestParcel.zzatm), adRequestParcel.zzatn, adRequestParcel.zzato != null ? new HashSet(adRequestParcel.zzato) : null, adRequestParcel.zzatu, adRequestParcel.zzatp, adRequestParcel.zzatq, nativeAdOptionsParcel, list, adRequestParcel.zzaub);
                Bundle bundle = adRequestParcel.zzatw != null ? adRequestParcel.zzatw.getBundle(mediationNativeAdapter.getClass().getName()) : null;
                this.zzbpj = new zzgr(com_google_android_gms_internal_zzgl);
                mediationNativeAdapter.requestNativeAd((Context) zze.zzad(com_google_android_gms_dynamic_zzd), this.zzbpj, zza(str, adRequestParcel.zzatq, str2), com_google_android_gms_internal_zzgu, bundle);
            } catch (Throwable th) {
                zzb.zzd("Could not request native ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        zza(com_google_android_gms_dynamic_zzd, adSizeParcel, adRequestParcel, str, null, com_google_android_gms_internal_zzgl);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, String str2, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        if (this.zzbpi instanceof MediationBannerAdapter) {
            zzb.zzcv("Requesting banner ad from adapter.");
            try {
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbpi;
                mediationBannerAdapter.requestBannerAd((Context) zze.zzad(com_google_android_gms_dynamic_zzd), new zzgr(com_google_android_gms_internal_zzgl), zza(str, adRequestParcel.zzatq, str2), com.google.android.gms.ads.zza.zza(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzaur), new zzgp(adRequestParcel.zzatm == -1 ? null : new Date(adRequestParcel.zzatm), adRequestParcel.zzatn, adRequestParcel.zzato != null ? new HashSet(adRequestParcel.zzato) : null, adRequestParcel.zzatu, adRequestParcel.zzatp, adRequestParcel.zzatq, adRequestParcel.zzaub), adRequestParcel.zzatw != null ? adRequestParcel.zzatw.getBundle(mediationBannerAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not request banner ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zzc(AdRequestParcel adRequestParcel, String str) throws RemoteException {
        zza(adRequestParcel, str, null);
    }

    public void zzj(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzbpi).onContextChanged((Context) zze.zzad(com_google_android_gms_dynamic_zzd));
        } catch (Throwable th) {
            zzb.zza("Could not inform adapter of changed context", th);
        }
    }

    public zzgn zzmo() {
        NativeAdMapper zzms = this.zzbpj.zzms();
        return zzms instanceof NativeAppInstallAdMapper ? new zzgs((NativeAppInstallAdMapper) zzms) : null;
    }

    public zzgo zzmp() {
        NativeAdMapper zzms = this.zzbpj.zzms();
        return zzms instanceof NativeContentAdMapper ? new zzgt((NativeContentAdMapper) zzms) : null;
    }

    public Bundle zzmq() {
        if (this.zzbpi instanceof zzls) {
            return ((zzls) this.zzbpi).zzmq();
        }
        String str = "MediationAdapter is not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzbpi.getClass().getCanonicalName());
        zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public Bundle zzmr() {
        return new Bundle();
    }
}
