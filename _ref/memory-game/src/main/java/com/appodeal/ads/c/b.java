package com.appodeal.ads.c;

import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.google.android.gms.ads.AdListener;

class b extends AdListener {
    private final w a;
    private final int b;
    private final int c;

    b(w wVar, int i, int i2) {
        this.a = wVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded() {
        y.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(int i) {
        y.b(this.b, this.c, this.a);
    }

    public void onAdOpened() {
    }

    public void onAdLeftApplication() {
        y.c(this.b, this.a);
    }

    public void onAdClosed() {
    }
}
