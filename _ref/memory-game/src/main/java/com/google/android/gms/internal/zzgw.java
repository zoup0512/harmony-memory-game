package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.yalantis.ucrop.util.FileUtils;

@zzin
public final class zzgw<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzgl zzbpk;

    public zzgw(zzgl com_google_android_gms_internal_zzgl) {
        this.zzbpk = com_google_android_gms_internal_zzgl;
    }

    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzcv("Adapter called onClick.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdClicked();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClicked.", e);
                return;
            }
        }
        zzb.zzcx("onClick must be called on the main UI thread.");
        zza.zzcnb.post(new 1(this));
    }

    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzcv("Adapter called onDismissScreen.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdClosed();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
                return;
            }
        }
        zzb.zzcx("onDismissScreen must be called on the main UI thread.");
        zza.zzcnb.post(new 4(this));
    }

    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzcv("Adapter called onDismissScreen.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdClosed();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
                return;
            }
        }
        zzb.zzcx("onDismissScreen must be called on the main UI thread.");
        zza.zzcnb.post(new 9(this));
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzb.zzcv(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdFailedToLoad(zzgx.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        zzb.zzcx("onFailedToReceiveAd must be called on the main UI thread.");
        zza.zzcnb.post(new 5(this, errorCode));
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzb.zzcv(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(FileUtils.HIDDEN_PREFIX).toString());
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdFailedToLoad(zzgx.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        zzb.zzcx("onFailedToReceiveAd must be called on the main UI thread.");
        zza.zzcnb.post(new 10(this, errorCode));
    }

    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzcv("Adapter called onLeaveApplication.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
                return;
            }
        }
        zzb.zzcx("onLeaveApplication must be called on the main UI thread.");
        zza.zzcnb.post(new 6(this));
    }

    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzcv("Adapter called onLeaveApplication.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
                return;
            }
        }
        zzb.zzcx("onLeaveApplication must be called on the main UI thread.");
        zza.zzcnb.post(new 11(this));
    }

    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzcv("Adapter called onPresentScreen.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdOpened();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
                return;
            }
        }
        zzb.zzcx("onPresentScreen must be called on the main UI thread.");
        zza.zzcnb.post(new 7(this));
    }

    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzcv("Adapter called onPresentScreen.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdOpened();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
                return;
            }
        }
        zzb.zzcx("onPresentScreen must be called on the main UI thread.");
        zza.zzcnb.post(new 2(this));
    }

    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzcv("Adapter called onReceivedAd.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
                return;
            }
        }
        zzb.zzcx("onReceivedAd must be called on the main UI thread.");
        zza.zzcnb.post(new 8(this));
    }

    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzcv("Adapter called onReceivedAd.");
        if (zzm.zziw().zztx()) {
            try {
                this.zzbpk.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
                return;
            }
        }
        zzb.zzcx("onReceivedAd must be called on the main UI thread.");
        zza.zzcnb.post(new 3(this));
    }
}
