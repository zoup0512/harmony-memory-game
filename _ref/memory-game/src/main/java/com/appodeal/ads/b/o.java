package com.appodeal.ads.b;

import com.appodeal.ads.q;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.InterstitialAdListener;

public class o implements AdListener, InterstitialAdListener {
    private final com.appodeal.ads.o a;
    private final int b;
    private final int c;

    o(com.appodeal.ads.o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onError(Ad ad, AdError adError) {
        ad.destroy();
        q.b(this.b, this.c, this.a);
    }

    public void onAdLoaded(Ad ad) {
        q.a(this.b, this.c, this.a);
    }

    public void onInterstitialDisplayed(Ad ad) {
        q.a(this.b, this.a);
    }

    public void onAdClicked(Ad ad) {
        q.b(this.b, this.a);
    }

    public void onInterstitialDismissed(Ad ad) {
        ad.destroy();
        q.c(this.b, this.a);
    }
}
