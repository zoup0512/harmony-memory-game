package com.google.ads.mediation;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.mediation.MediationNativeListener;

final class AbstractAdViewAdapter$zze extends AdListener implements OnAppInstallAdLoadedListener, OnContentAdLoadedListener, zza {
    final AbstractAdViewAdapter zzfl;
    final MediationNativeListener zzfo;

    public AbstractAdViewAdapter$zze(AbstractAdViewAdapter abstractAdViewAdapter, MediationNativeListener mediationNativeListener) {
        this.zzfl = abstractAdViewAdapter;
        this.zzfo = mediationNativeListener;
    }

    public void onAdClicked() {
        this.zzfo.onAdClicked(this.zzfl);
    }

    public void onAdClosed() {
        this.zzfo.onAdClosed(this.zzfl);
    }

    public void onAdFailedToLoad(int i) {
        this.zzfo.onAdFailedToLoad(this.zzfl, i);
    }

    public void onAdLeftApplication() {
        this.zzfo.onAdLeftApplication(this.zzfl);
    }

    public void onAdLoaded() {
    }

    public void onAdOpened() {
        this.zzfo.onAdOpened(this.zzfl);
    }

    public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
        this.zzfo.onAdLoaded(this.zzfl, new AbstractAdViewAdapter$zza(nativeAppInstallAd));
    }

    public void onContentAdLoaded(NativeContentAd nativeContentAd) {
        this.zzfo.onAdLoaded(this.zzfl, new AbstractAdViewAdapter$zzb(nativeContentAd));
    }
}
