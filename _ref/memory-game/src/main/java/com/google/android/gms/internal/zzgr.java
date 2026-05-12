package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.common.internal.zzab;
import com.yalantis.ucrop.util.FileUtils;

@zzin
public final class zzgr implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzgl zzbpk;
    private NativeAdMapper zzbpl;

    public zzgr(zzgl com_google_android_gms_internal_zzgl) {
        this.zzbpk = com_google_android_gms_internal_zzgl;
    }

    public void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        zzab.zzhi("onAdClicked must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdClicked.");
        try {
            this.zzbpk.onAdClicked();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzab.zzhi("onAdClicked must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdClicked.");
        try {
            this.zzbpk.onAdClicked();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        zzab.zzhi("onAdClicked must be called on the main UI thread.");
        NativeAdMapper zzms = zzms();
        if (zzms == null) {
            zzb.zzcx("Could not call onAdClicked since NativeAdMapper is null.");
        } else if (zzms.getOverrideClickHandling()) {
            zzb.zzcv("Adapter called onAdClicked.");
            try {
                this.zzbpk.onAdClicked();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClicked.", e);
            }
        } else {
            zzb.zzcv("Could not call onAdClicked since setOverrideClickHandling is not set to true");
        }
    }

    public void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        zzab.zzhi("onAdClosed must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdClosed.");
        try {
            this.zzbpk.onAdClosed();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzab.zzhi("onAdClosed must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdClosed.");
        try {
            this.zzbpk.onAdClosed();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        zzab.zzhi("onAdClosed must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdClosed.");
        try {
            this.zzbpk.onAdClosed();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdClosed.", e);
        }
    }

    public void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        zzab.zzhi("onAdFailedToLoad must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdFailedToLoad with error. " + i);
        try {
            this.zzbpk.onAdFailedToLoad(i);
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        zzab.zzhi("onAdFailedToLoad must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdFailedToLoad with error " + i + FileUtils.HIDDEN_PREFIX);
        try {
            this.zzbpk.onAdFailedToLoad(i);
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        zzab.zzhi("onAdFailedToLoad must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdFailedToLoad with error " + i + FileUtils.HIDDEN_PREFIX);
        try {
            this.zzbpk.onAdFailedToLoad(i);
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        zzab.zzhi("onAdImpression must be called on the main UI thread.");
        NativeAdMapper zzms = zzms();
        if (zzms == null) {
            zzb.zzcx("Could not call onAdImpression since NativeAdMapper is null. ");
        } else if (zzms.getOverrideImpressionRecording()) {
            zzb.zzcv("Adapter called onAdImpression.");
            try {
                this.zzbpk.onAdImpression();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdImpression.", e);
            }
        } else {
            zzb.zzcv("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
        }
    }

    public void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        zzab.zzhi("onAdLeftApplication must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdLeftApplication.");
        try {
            this.zzbpk.onAdLeftApplication();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzab.zzhi("onAdLeftApplication must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdLeftApplication.");
        try {
            this.zzbpk.onAdLeftApplication();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        zzab.zzhi("onAdLeftApplication must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdLeftApplication.");
        try {
            this.zzbpk.onAdLeftApplication();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        zzab.zzhi("onAdLoaded must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdLoaded.");
        try {
            this.zzbpk.onAdLoaded();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzab.zzhi("onAdLoaded must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdLoaded.");
        try {
            this.zzbpk.onAdLoaded();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        zzab.zzhi("onAdLoaded must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdLoaded.");
        this.zzbpl = nativeAdMapper;
        try {
            this.zzbpk.onAdLoaded();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdLoaded.", e);
        }
    }

    public void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        zzab.zzhi("onAdOpened must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdOpened.");
        try {
            this.zzbpk.onAdOpened();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzab.zzhi("onAdOpened must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdOpened.");
        try {
            this.zzbpk.onAdOpened();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        zzab.zzhi("onAdOpened must be called on the main UI thread.");
        zzb.zzcv("Adapter called onAdOpened.");
        try {
            this.zzbpk.onAdOpened();
        } catch (Throwable e) {
            zzb.zzd("Could not call onAdOpened.", e);
        }
    }

    public NativeAdMapper zzms() {
        return this.zzbpl;
    }
}
