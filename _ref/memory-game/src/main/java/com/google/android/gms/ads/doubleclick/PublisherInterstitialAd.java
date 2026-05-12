package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.internal.client.zzaf;

public final class PublisherInterstitialAd {
    private final zzaf zzaij;

    public PublisherInterstitialAd(Context context) {
        this.zzaij = new zzaf(context, this);
    }

    public AdListener getAdListener() {
        return this.zzaij.getAdListener();
    }

    public String getAdUnitId() {
        return this.zzaij.getAdUnitId();
    }

    public AppEventListener getAppEventListener() {
        return this.zzaij.getAppEventListener();
    }

    public String getMediationAdapterClassName() {
        return this.zzaij.getMediationAdapterClassName();
    }

    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzaij.getOnCustomRenderedAdLoadedListener();
    }

    public boolean isLoaded() {
        return this.zzaij.isLoaded();
    }

    public boolean isLoading() {
        return this.zzaij.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzaij.zza(publisherAdRequest.zzdc());
    }

    public void setAdListener(AdListener adListener) {
        this.zzaij.setAdListener(adListener);
    }

    public void setAdUnitId(String str) {
        this.zzaij.setAdUnitId(str);
    }

    public void setAppEventListener(AppEventListener appEventListener) {
        this.zzaij.setAppEventListener(appEventListener);
    }

    public void setCorrelator(Correlator correlator) {
        this.zzaij.setCorrelator(correlator);
    }

    public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzaij.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    public void show() {
        this.zzaij.show();
    }
}
