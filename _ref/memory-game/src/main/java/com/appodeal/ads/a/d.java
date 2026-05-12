package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;

class d extends AdListener {
    private final h a;
    private final int b;
    private final int c;
    private final AdSize d;

    d(h hVar, int i, int i2, AdSize adSize) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
        this.d = adSize;
    }

    public void onAdLoaded() {
        if (this.d == AdSize.SMART_BANNER) {
            j.a(this.b, this.c, this.a, true, true);
        } else {
            j.a(this.b, this.c, this.a, false, true);
        }
    }

    public void onAdFailedToLoad(int i) {
        j.b(this.b, this.c, this.a, true);
    }

    public void onAdOpened() {
    }

    public void onAdLeftApplication() {
        j.c(this.b, this.a);
    }

    public void onAdClosed() {
    }
}
