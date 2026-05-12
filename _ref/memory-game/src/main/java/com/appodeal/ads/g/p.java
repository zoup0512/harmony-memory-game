package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;

class p implements InterstitialAdListener {
    private final ap a;
    private final int b;
    private final int c;

    p(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
        this.a.g().a(moPubInterstitial.getmInterstitialView().getmAdViewController().getmAdResponse().getStringBody());
        aj.a(this.b, this.c, this.a);
    }

    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
        aj.b(this.b, this.c, this.a);
    }

    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
        aj.a(this.b, this.a);
    }

    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
    }

    public void onInterstitialFinished(MoPubInterstitial moPubInterstitial) {
        aj.b(this.b, this.a);
    }

    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
        aj.d(this.b, this.a);
    }
}
