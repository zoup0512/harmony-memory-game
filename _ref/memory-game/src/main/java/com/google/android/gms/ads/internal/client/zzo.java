package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.zzin;

@zzin
public class zzo extends AdListener {
    private final Object lock = new Object();
    private AdListener zzavr;

    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzavr != null) {
                this.zzavr.onAdClosed();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.lock) {
            if (this.zzavr != null) {
                this.zzavr.onAdFailedToLoad(i);
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzavr != null) {
                this.zzavr.onAdLeftApplication();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzavr != null) {
                this.zzavr.onAdLoaded();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzavr != null) {
                this.zzavr.onAdOpened();
            }
        }
    }

    public void zza(AdListener adListener) {
        synchronized (this.lock) {
            this.zzavr = adListener;
        }
    }
}
