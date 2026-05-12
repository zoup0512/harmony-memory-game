package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzu;

@zzin
public class zzfn extends zza {
    private String zzaln;
    private zzfh zzbkq;
    @Nullable
    private zzl zzbkv;
    private zzfj zzblc;
    @Nullable
    private zzhs zzbld;
    private String zzble;

    public zzfn(Context context, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this(str, new zzfh(context, com_google_android_gms_internal_zzgj, versionInfoParcel, com_google_android_gms_ads_internal_zzd));
    }

    zzfn(String str, zzfh com_google_android_gms_internal_zzfh) {
        this.zzaln = str;
        this.zzbkq = com_google_android_gms_internal_zzfh;
        this.zzblc = new zzfj();
        zzu.zzgb().zza(com_google_android_gms_internal_zzfh);
    }

    private void zzlw() {
        if (this.zzbkv != null && this.zzbld != null) {
            this.zzbkv.zza(this.zzbld, this.zzble);
        }
    }

    static boolean zzn(AdRequestParcel adRequestParcel) {
        Bundle zzi = zzfk.zzi(adRequestParcel);
        return zzi != null && zzi.containsKey("gw");
    }

    static boolean zzo(AdRequestParcel adRequestParcel) {
        Bundle zzi = zzfk.zzi(adRequestParcel);
        return zzi != null && zzi.containsKey("_ad");
    }

    void abort() {
        if (this.zzbkv == null) {
            this.zzbkv = this.zzbkq.zzbc(this.zzaln);
            this.zzblc.zzc(this.zzbkv);
            zzlw();
        }
    }

    public void destroy() throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.destroy();
        }
    }

    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        return this.zzbkv != null ? this.zzbkv.getMediationAdapterClassName() : null;
    }

    public boolean isLoading() throws RemoteException {
        return this.zzbkv != null && this.zzbkv.isLoading();
    }

    public boolean isReady() throws RemoteException {
        return this.zzbkv != null && this.zzbkv.isReady();
    }

    public void pause() throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.pause();
        }
    }

    public void resume() throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.resume();
        }
    }

    public void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        if (this.zzbkv != null) {
            this.zzbkv.setManualImpressionsEnabled(z);
        }
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.showInterstitial();
        } else {
            zzb.zzcx("Interstitial ad must be loaded before showInterstitial().");
        }
    }

    public void stopLoading() throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.stopLoading();
        }
    }

    public void zza(AdSizeParcel adSizeParcel) throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.zza(adSizeParcel);
        }
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) throws RemoteException {
        this.zzblc.zzbkk = com_google_android_gms_ads_internal_client_zzp;
        if (this.zzbkv != null) {
            this.zzblc.zzc(this.zzbkv);
        }
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) throws RemoteException {
        this.zzblc.zzalf = com_google_android_gms_ads_internal_client_zzq;
        if (this.zzbkv != null) {
            this.zzblc.zzc(this.zzbkv);
        }
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) throws RemoteException {
        this.zzblc.zzbkh = com_google_android_gms_ads_internal_client_zzw;
        if (this.zzbkv != null) {
            this.zzblc.zzc(this.zzbkv);
        }
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) throws RemoteException {
        abort();
        if (this.zzbkv != null) {
            this.zzbkv.zza(com_google_android_gms_ads_internal_client_zzy);
        }
    }

    public void zza(com.google.android.gms.ads.internal.reward.client.zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        this.zzblc.zzbkl = com_google_android_gms_ads_internal_reward_client_zzd;
        if (this.zzbkv != null) {
            this.zzblc.zzc(this.zzbkv);
        }
    }

    public void zza(zzdo com_google_android_gms_internal_zzdo) throws RemoteException {
        this.zzblc.zzbkj = com_google_android_gms_internal_zzdo;
        if (this.zzbkv != null) {
            this.zzblc.zzc(this.zzbkv);
        }
    }

    public void zza(zzho com_google_android_gms_internal_zzho) throws RemoteException {
        this.zzblc.zzbki = com_google_android_gms_internal_zzho;
        if (this.zzbkv != null) {
            this.zzblc.zzc(this.zzbkv);
        }
    }

    public void zza(zzhs com_google_android_gms_internal_zzhs, String str) throws RemoteException {
        this.zzbld = com_google_android_gms_internal_zzhs;
        this.zzble = str;
        zzlw();
    }

    public boolean zzb(AdRequestParcel adRequestParcel) throws RemoteException {
        if (!zzn(adRequestParcel)) {
            abort();
        }
        if (zzfk.zzk(adRequestParcel)) {
            abort();
        }
        if (adRequestParcel.zzatt != null) {
            abort();
        }
        if (this.zzbkv != null) {
            return this.zzbkv.zzb(adRequestParcel);
        }
        zzfk zzgb = zzu.zzgb();
        if (zzo(adRequestParcel)) {
            zzgb.zzb(adRequestParcel, this.zzaln);
        }
        zzfm.zza zza = zzgb.zza(adRequestParcel, this.zzaln);
        if (zza != null) {
            if (!zza.zzbkz) {
                zza.zzlv();
            }
            this.zzbkv = zza.zzbkv;
            zza.zzbkx.zza(this.zzblc);
            this.zzblc.zzc(this.zzbkv);
            zzlw();
            return zza.zzbla;
        }
        abort();
        return this.zzbkv.zzb(adRequestParcel);
    }

    @Nullable
    public com.google.android.gms.dynamic.zzd zzdm() throws RemoteException {
        return this.zzbkv != null ? this.zzbkv.zzdm() : null;
    }

    @Nullable
    public AdSizeParcel zzdn() throws RemoteException {
        return this.zzbkv != null ? this.zzbkv.zzdn() : null;
    }

    public void zzdp() throws RemoteException {
        if (this.zzbkv != null) {
            this.zzbkv.zzdp();
        } else {
            zzb.zzcx("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public zzab zzdq() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
}
