package com.google.ads.mediation;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

final class AbstractAdViewAdapter$zzd extends AdListener implements zza {
    final AbstractAdViewAdapter zzfl;
    final MediationInterstitialListener zzfn;

    public AbstractAdViewAdapter$zzd(AbstractAdViewAdapter abstractAdViewAdapter, MediationInterstitialListener mediationInterstitialListener) {
        this.zzfl = abstractAdViewAdapter;
        this.zzfn = mediationInterstitialListener;
    }

    public void onAdClicked() {
        this.zzfn.onAdClicked(this.zzfl);
    }

    public void onAdClosed() {
        this.zzfn.onAdClosed(this.zzfl);
    }

    public void onAdFailedToLoad(int i) {
        this.zzfn.onAdFailedToLoad(this.zzfl, i);
    }

    public void onAdLeftApplication() {
        this.zzfn.onAdLeftApplication(this.zzfl);
    }

    public void onAdLoaded() {
        this.zzfn.onAdLoaded(this.zzfl);
    }

    public void onAdOpened() {
        this.zzfn.onAdOpened(this.zzfl);
    }
}
