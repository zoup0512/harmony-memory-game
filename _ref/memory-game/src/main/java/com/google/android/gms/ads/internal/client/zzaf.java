package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.reward.client.zzg;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzdp;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzin;

@zzin
public class zzaf {
    private final Context mContext;
    private final zzh zzahz;
    private String zzaln;
    private zza zzatk;
    private AdListener zzatl;
    private AppEventListener zzaux;
    private final zzgi zzawb;
    private Correlator zzawf;
    private zzu zzawg;
    private InAppPurchaseListener zzawh;
    private OnCustomRenderedAdLoadedListener zzawi;
    private PlayStorePurchaseListener zzawj;
    private String zzawl;
    private PublisherInterstitialAd zzawp;
    private boolean zzawq;
    private RewardedVideoAdListener zzfh;

    public zzaf(Context context) {
        this(context, zzh.zzih(), null);
    }

    public zzaf(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzh.zzih(), publisherInterstitialAd);
    }

    public zzaf(Context context, zzh com_google_android_gms_ads_internal_client_zzh, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzawb = new zzgi();
        this.mContext = context;
        this.zzahz = com_google_android_gms_ads_internal_client_zzh;
        this.zzawp = publisherInterstitialAd;
    }

    private void zzam(String str) throws RemoteException {
        if (this.zzaln == null) {
            zzan(str);
        }
        this.zzawg = zzm.zzix().zzb(this.mContext, this.zzawq ? AdSizeParcel.zzii() : new AdSizeParcel(), this.zzaln, this.zzawb);
        if (this.zzatl != null) {
            this.zzawg.zza(new zzc(this.zzatl));
        }
        if (this.zzatk != null) {
            this.zzawg.zza(new zzb(this.zzatk));
        }
        if (this.zzaux != null) {
            this.zzawg.zza(new zzj(this.zzaux));
        }
        if (this.zzawh != null) {
            this.zzawg.zza(new zzht(this.zzawh));
        }
        if (this.zzawj != null) {
            this.zzawg.zza(new zzhx(this.zzawj), this.zzawl);
        }
        if (this.zzawi != null) {
            this.zzawg.zza(new zzdp(this.zzawi));
        }
        if (this.zzawf != null) {
            this.zzawg.zza(this.zzawf.zzdd());
        }
        if (this.zzfh != null) {
            this.zzawg.zza(new zzg(this.zzfh));
        }
    }

    private void zzan(String str) {
        if (this.zzawg == null) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 63).append("The ad unit ID must be set on InterstitialAd before ").append(str).append(" is called.").toString());
        }
    }

    public AdListener getAdListener() {
        return this.zzatl;
    }

    public String getAdUnitId() {
        return this.zzaln;
    }

    public AppEventListener getAppEventListener() {
        return this.zzaux;
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzawh;
    }

    public String getMediationAdapterClassName() {
        try {
            if (this.zzawg != null) {
                return this.zzawg.getMediationAdapterClassName();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to get the mediation adapter class name.", e);
        }
        return null;
    }

    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzawi;
    }

    public boolean isLoaded() {
        boolean z = false;
        try {
            if (this.zzawg != null) {
                z = this.zzawg.isReady();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is ready.", e);
        }
        return z;
    }

    public boolean isLoading() {
        boolean z = false;
        try {
            if (this.zzawg != null) {
                z = this.zzawg.isLoading();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is loading.", e);
        }
        return z;
    }

    public void setAdListener(AdListener adListener) {
        try {
            this.zzatl = adListener;
            if (this.zzawg != null) {
                this.zzawg.zza(adListener != null ? new zzc(adListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AdListener.", e);
        }
    }

    public void setAdUnitId(String str) {
        if (this.zzaln != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzaln = str;
    }

    public void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzaux = appEventListener;
            if (this.zzawg != null) {
                this.zzawg.zza(appEventListener != null ? new zzj(appEventListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AppEventListener.", e);
        }
    }

    public void setCorrelator(Correlator correlator) {
        this.zzawf = correlator;
        try {
            if (this.zzawg != null) {
                this.zzawg.zza(this.zzawf == null ? null : this.zzawf.zzdd());
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set correlator.", e);
        }
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        if (this.zzawj != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.zzawh = inAppPurchaseListener;
            if (this.zzawg != null) {
                this.zzawg.zza(inAppPurchaseListener != null ? new zzht(inAppPurchaseListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the InAppPurchaseListener.", e);
        }
    }

    public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzawi = onCustomRenderedAdLoadedListener;
            if (this.zzawg != null) {
                this.zzawg.zza(onCustomRenderedAdLoadedListener != null ? new zzdp(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the OnCustomRenderedAdLoadedListener.", e);
        }
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String str) {
        if (this.zzawh != null) {
            throw new IllegalStateException("In app purchase parameter has already been set.");
        }
        try {
            this.zzawj = playStorePurchaseListener;
            this.zzawl = str;
            if (this.zzawg != null) {
                this.zzawg.zza(playStorePurchaseListener != null ? new zzhx(playStorePurchaseListener) : null, str);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the play store purchase parameter.", e);
        }
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzfh = rewardedVideoAdListener;
            if (this.zzawg != null) {
                this.zzawg.zza(rewardedVideoAdListener != null ? new zzg(rewardedVideoAdListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AdListener.", e);
        }
    }

    public void show() {
        try {
            zzan("show");
            this.zzawg.showInterstitial();
        } catch (Throwable e) {
            zzb.zzd("Failed to show interstitial.", e);
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_client_zza) {
        try {
            this.zzatk = com_google_android_gms_ads_internal_client_zza;
            if (this.zzawg != null) {
                this.zzawg.zza(com_google_android_gms_ads_internal_client_zza != null ? new zzb(com_google_android_gms_ads_internal_client_zza) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AdClickListener.", e);
        }
    }

    public void zza(zzad com_google_android_gms_ads_internal_client_zzad) {
        try {
            if (this.zzawg == null) {
                zzam("loadAd");
            }
            if (this.zzawg.zzb(this.zzahz.zza(this.mContext, com_google_android_gms_ads_internal_client_zzad))) {
                this.zzawb.zzh(com_google_android_gms_ads_internal_client_zzad.zzjg());
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to load ad.", e);
        }
    }

    public void zzd(boolean z) {
        this.zzawq = z;
    }
}
