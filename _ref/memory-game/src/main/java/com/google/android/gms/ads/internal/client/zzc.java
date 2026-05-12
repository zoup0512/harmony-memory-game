package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.internal.client.zzq.zza;
import com.google.android.gms.internal.zzin;

@zzin
public final class zzc extends zza {
    private final AdListener zzatl;

    public zzc(AdListener adListener) {
        this.zzatl = adListener;
    }

    public void onAdClosed() {
        this.zzatl.onAdClosed();
    }

    public void onAdFailedToLoad(int i) {
        this.zzatl.onAdFailedToLoad(i);
    }

    public void onAdLeftApplication() {
        this.zzatl.onAdLeftApplication();
    }

    public void onAdLoaded() {
        this.zzatl.onAdLoaded();
    }

    public void onAdOpened() {
        this.zzatl.onAdOpened();
    }
}
