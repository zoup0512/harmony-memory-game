package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzhs;

public class zzak extends zza {
    private zzq zzalf;

    public void destroy() {
    }

    public String getMediationAdapterClassName() {
        return null;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void setManualImpressionsEnabled(boolean z) {
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() {
    }

    public void stopLoading() {
    }

    public void zza(AdSizeParcel adSizeParcel) {
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) {
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzalf = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) {
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) {
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
    }

    public void zza(zzdo com_google_android_gms_internal_zzdo) {
    }

    public void zza(zzho com_google_android_gms_internal_zzho) {
    }

    public void zza(zzhs com_google_android_gms_internal_zzhs, String str) {
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new Runnable(this) {
            final /* synthetic */ zzak zzaww;

            {
                this.zzaww = r1;
            }

            public void run() {
                if (this.zzaww.zzalf != null) {
                    try {
                        this.zzaww.zzalf.onAdFailedToLoad(1);
                    } catch (Throwable e) {
                        zzb.zzd("Could not notify onAdFailedToLoad event.", e);
                    }
                }
            }
        });
        return false;
    }

    public com.google.android.gms.dynamic.zzd zzdm() {
        return null;
    }

    public AdSizeParcel zzdn() {
        return null;
    }

    public void zzdp() {
    }

    public zzab zzdq() {
        return null;
    }
}
