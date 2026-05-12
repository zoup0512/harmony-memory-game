package com.google.ads.mediation;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.mediation.MediationBannerListener;

final class AbstractAdViewAdapter$zzc extends AdListener implements zza {
    final AbstractAdViewAdapter zzfl;
    final MediationBannerListener zzfm;

    public AbstractAdViewAdapter$zzc(AbstractAdViewAdapter abstractAdViewAdapter, MediationBannerListener mediationBannerListener) {
        this.zzfl = abstractAdViewAdapter;
        this.zzfm = mediationBannerListener;
    }

    public void onAdClicked() {
        this.zzfm.onAdClicked(this.zzfl);
    }

    public void onAdClosed() {
        this.zzfm.onAdClosed(this.zzfl);
    }

    public void onAdFailedToLoad(int i) {
        this.zzfm.onAdFailedToLoad(this.zzfl, i);
    }

    public void onAdLeftApplication() {
        this.zzfm.onAdLeftApplication(this.zzfl);
    }

    public void onAdLoaded() {
        this.zzfm.onAdLoaded(this.zzfl);
    }

    public void onAdOpened() {
        this.zzfm.onAdOpened(this.zzfl);
    }
}
