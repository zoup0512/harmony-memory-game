package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter.zza;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzlt;
import java.util.Date;
import java.util.Set;

@zzin
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter, zzlt {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    protected AdView zzfb;
    protected InterstitialAd zzfc;
    private AdLoader zzfd;
    private Context zzfe;
    private InterstitialAd zzff;
    private MediationRewardedVideoAdListener zzfg;
    final RewardedVideoAdListener zzfh = new 1(this);

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    public View getBannerView() {
        return this.zzfb;
    }

    public Bundle getInterstitialAdapterInfo() {
        return new zza().zzbb(1).zzvp();
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.zzfe = context.getApplicationContext();
        this.zzfg = mediationRewardedVideoAdListener;
        this.zzfg.onInitializationSucceeded(this);
    }

    public boolean isInitialized() {
        return this.zzfg != null;
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.zzfe == null || this.zzfg == null) {
            zzb.e("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        this.zzff = new InterstitialAd(this.zzfe);
        this.zzff.zzd(true);
        this.zzff.setAdUnitId(getAdUnitId(bundle));
        this.zzff.setRewardedVideoAdListener(this.zzfh);
        this.zzff.loadAd(zza(this.zzfe, mediationAdRequest, bundle2, bundle));
    }

    public void onDestroy() {
        if (this.zzfb != null) {
            this.zzfb.destroy();
            this.zzfb = null;
        }
        if (this.zzfc != null) {
            this.zzfc = null;
        }
        if (this.zzfd != null) {
            this.zzfd = null;
        }
        if (this.zzff != null) {
            this.zzff = null;
        }
    }

    public void onPause() {
        if (this.zzfb != null) {
            this.zzfb.pause();
        }
    }

    public void onResume() {
        if (this.zzfb != null) {
            this.zzfb.resume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzfb = new AdView(context);
        this.zzfb.setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zzfb.setAdUnitId(getAdUnitId(bundle));
        this.zzfb.setAdListener(new zzc(this, mediationBannerListener));
        this.zzfb.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzfc = new InterstitialAd(context);
        this.zzfc.setAdUnitId(getAdUnitId(bundle));
        this.zzfc.setAdListener(new zzd(this, mediationInterstitialListener));
        this.zzfc.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        OnContentAdLoadedListener com_google_ads_mediation_AbstractAdViewAdapter_zze = new zze(this, mediationNativeListener);
        Builder withAdListener = zza(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(com_google_ads_mediation_AbstractAdViewAdapter_zze);
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            withAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            withAdListener.forAppInstallAd(com_google_ads_mediation_AbstractAdViewAdapter_zze);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            withAdListener.forContentAd(com_google_ads_mediation_AbstractAdViewAdapter_zze);
        }
        this.zzfd = withAdListener.build();
        this.zzfd.loadAd(zza(context, nativeMediationAdRequest, bundle2, bundle));
    }

    public void showInterstitial() {
        this.zzfc.show();
    }

    public void showVideo() {
        this.zzff.show();
    }

    protected abstract Bundle zza(Bundle bundle, Bundle bundle2);

    Builder zza(Context context, String str) {
        return new Builder(context, str);
    }

    AdRequest zza(Context context, MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            for (String addKeyword : keywords) {
                builder.addKeyword(addKeyword);
            }
        }
        Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            builder.addTestDevice(zzm.zziw().zzaq(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.tagForChildDirectedTreatment(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, zza(bundle, bundle2));
        return builder.build();
    }
}
