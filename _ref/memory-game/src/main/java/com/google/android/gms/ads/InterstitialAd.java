package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzaf;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public final class InterstitialAd {
    private final zzaf zzaij;

    public InterstitialAd(Context context) {
        this.zzaij = new zzaf(context);
    }

    public AdListener getAdListener() {
        return this.zzaij.getAdListener();
    }

    public String getAdUnitId() {
        return this.zzaij.getAdUnitId();
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzaij.getInAppPurchaseListener();
    }

    public String getMediationAdapterClassName() {
        return this.zzaij.getMediationAdapterClassName();
    }

    public boolean isLoaded() {
        return this.zzaij.isLoaded();
    }

    public boolean isLoading() {
        return this.zzaij.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        this.zzaij.zza(adRequest.zzdc());
    }

    public void setAdListener(AdListener adListener) {
        this.zzaij.setAdListener(adListener);
        if (adListener != null && (adListener instanceof zza)) {
            this.zzaij.zza((zza) adListener);
        } else if (adListener == null) {
            this.zzaij.zza(null);
        }
    }

    public void setAdUnitId(String str) {
        this.zzaij.setAdUnitId(str);
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        this.zzaij.setInAppPurchaseListener(inAppPurchaseListener);
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String str) {
        this.zzaij.setPlayStorePurchaseParams(playStorePurchaseListener, str);
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzaij.setRewardedVideoAdListener(rewardedVideoAdListener);
    }

    public void show() {
        this.zzaij.show();
    }

    public void zzd(boolean z) {
        this.zzaij.zzd(z);
    }
}
