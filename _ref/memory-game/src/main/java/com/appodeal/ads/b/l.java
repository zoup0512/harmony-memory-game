package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.cmcm.adsdk.interstitial.InterstitialAdCallBack;

class l implements InterstitialAdCallBack {
    private final o a;
    private final int b;
    private final int c;

    l(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded() {
        q.a(this.b, this.c, this.a);
    }

    public void onAdLoadFailed(int i) {
        q.b(this.b, this.c, this.a);
    }

    public void onAdDisplayed() {
        q.a(this.b, this.a);
    }

    public void onAdClicked() {
        q.b(this.b, this.a);
    }

    public void onAdDismissed() {
        q.c(this.b, this.a);
    }
}
