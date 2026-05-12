package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgk.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzin
public final class zzgv<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zza {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbpo;
    private final NETWORK_EXTRAS zzbpp;

    public zzgv(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzbpo = mediationAdapter;
        this.zzbpp = network_extras;
    }

    private SERVER_PARAMETERS zzb(String str, int i, String str2) throws RemoteException {
        Map hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    hashMap.put(str3, jSONObject.getString(str3));
                }
            } catch (Throwable th) {
                zzb.zzd("Could not get MediationServerParameters.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class serverParametersType = this.zzbpo.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        MediationServerParameters mediationServerParameters = (MediationServerParameters) serverParametersType.newInstance();
        mediationServerParameters.load(hashMap);
        return mediationServerParameters;
    }

    public void destroy() throws RemoteException {
        try {
            this.zzbpo.destroy();
        } catch (Throwable th) {
            zzb.zzd("Could not destroy adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public zzd getView() throws RemoteException {
        if (this.zzbpo instanceof MediationBannerAdapter) {
            try {
                return zze.zzac(((MediationBannerAdapter) this.zzbpo).getBannerView());
            } catch (Throwable th) {
                zzb.zzd("Could not get banner view from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbpo.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public boolean isInitialized() {
        return true;
    }

    public void pause() throws RemoteException {
        throw new RemoteException();
    }

    public void resume() throws RemoteException {
        throw new RemoteException();
    }

    public void showInterstitial() throws RemoteException {
        if (this.zzbpo instanceof MediationInterstitialAdapter) {
            zzb.zzcv("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbpo).showInterstitial();
            } catch (Throwable th) {
                zzb.zzd("Could not show interstitial from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbpo.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void showVideo() {
    }

    public void zza(AdRequestParcel adRequestParcel, String str, String str2) {
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, com.google.android.gms.ads.internal.reward.mediation.client.zza com_google_android_gms_ads_internal_reward_mediation_client_zza, String str2) throws RemoteException {
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        zza(com_google_android_gms_dynamic_zzd, adRequestParcel, str, null, com_google_android_gms_internal_zzgl);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        if (this.zzbpo instanceof MediationInterstitialAdapter) {
            zzb.zzcv("Requesting interstitial ad from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbpo).requestInterstitialAd(new zzgw(com_google_android_gms_internal_zzgl), (Activity) zze.zzad(com_google_android_gms_dynamic_zzd), zzb(str, adRequestParcel.zzatq, str2), zzgx.zzp(adRequestParcel), this.zzbpp);
            } catch (Throwable th) {
                zzb.zzd("Could not request interstitial ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbpo.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgl com_google_android_gms_internal_zzgl, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) {
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        zza(com_google_android_gms_dynamic_zzd, adSizeParcel, adRequestParcel, str, null, com_google_android_gms_internal_zzgl);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, String str2, zzgl com_google_android_gms_internal_zzgl) throws RemoteException {
        if (this.zzbpo instanceof MediationBannerAdapter) {
            zzb.zzcv("Requesting banner ad from adapter.");
            try {
                ((MediationBannerAdapter) this.zzbpo).requestBannerAd(new zzgw(com_google_android_gms_internal_zzgl), (Activity) zze.zzad(com_google_android_gms_dynamic_zzd), zzb(str, adRequestParcel.zzatq, str2), zzgx.zzc(adSizeParcel), zzgx.zzp(adRequestParcel), this.zzbpp);
            } catch (Throwable th) {
                zzb.zzd("Could not request banner ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbpo.getClass().getCanonicalName());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zzc(AdRequestParcel adRequestParcel, String str) {
    }

    public void zzj(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
    }

    public zzgn zzmo() {
        return null;
    }

    public zzgo zzmp() {
        return null;
    }

    public Bundle zzmq() {
        return new Bundle();
    }

    public Bundle zzmr() {
        return new Bundle();
    }
}
