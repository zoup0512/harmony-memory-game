package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.my.target.ads.InterstitialAd;
import com.my.target.ads.InterstitialAd.InterstitialAdListener;

class n implements InterstitialAdListener {
    private final ap a;
    private final int b;
    private final int c;

    n(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void onLoad(InterstitialAd interstitialAd) {
        aj.a(this.b, this.c, this.a);
    }

    public void onNoAd(String str, InterstitialAd interstitialAd) {
        aj.b(this.b, this.c, this.a);
    }

    public void onDisplay(InterstitialAd interstitialAd) {
        this.a.g().a(com.appodeal.ads.networks.n.a(interstitialAd));
        aj.a(this.b, this.a);
    }

    public void onClick(InterstitialAd interstitialAd) {
        aj.c(this.b, this.a);
    }

    public void onDismiss(InterstitialAd interstitialAd) {
        aj.d(this.b, this.a);
    }

    public void onVideoCompleted(InterstitialAd interstitialAd) {
        aj.b(this.b, this.a);
    }
}
