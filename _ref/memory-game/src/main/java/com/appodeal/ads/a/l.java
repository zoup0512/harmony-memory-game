package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;

class l implements AdListener {
    private final h a;
    private final int b;
    private final int c;

    l(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(Ad ad) {
        j.a(this.b, this.c, this.a);
    }

    public void onError(Ad ad, AdError adError) {
        ad.destroy();
        j.b(this.b, this.c, this.a);
    }

    public void onAdClicked(Ad ad) {
        j.c(this.b, this.a);
    }
}
