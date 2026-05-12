package com.appodeal.ads.c;

import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;

class k implements AdListener {
    private final w a;
    private final int b;
    private final int c;

    k(w wVar, int i, int i2) {
        this.a = wVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(Ad ad) {
        y.a(this.b, this.c, this.a);
    }

    public void onError(Ad ad, AdError adError) {
        ad.destroy();
        y.b(this.b, this.c, this.a);
    }

    public void onAdClicked(Ad ad) {
        y.c(this.b, this.a);
    }
}
