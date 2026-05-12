package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.my.target.ads.InterstitialAd;
import com.my.target.ads.InterstitialAd.InterstitialAdListener;

class v implements InterstitialAdListener {
    private final o a;
    private final int b;
    private final int c;

    v(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onLoad(InterstitialAd interstitialAd) {
        q.a(this.b, this.c, this.a);
    }

    public void onNoAd(String str, InterstitialAd interstitialAd) {
        q.b(this.b, this.c, this.a);
    }

    public void onDisplay(InterstitialAd interstitialAd) {
        q.a(this.b, this.a);
    }

    public void onClick(InterstitialAd interstitialAd) {
        q.b(this.b, this.a);
    }

    public void onDismiss(InterstitialAd interstitialAd) {
        q.c(this.b, this.a);
    }

    public void onVideoCompleted(InterstitialAd interstitialAd) {
    }
}
