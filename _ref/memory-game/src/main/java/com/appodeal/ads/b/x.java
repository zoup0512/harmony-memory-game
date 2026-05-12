package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;

class x implements InterstitialAdListener {
    private final o a;
    private final int b;
    private final int c;

    x(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
        this.a.g().a(moPubInterstitial.getmInterstitialView().getmAdViewController().getmAdResponse().getStringBody());
        q.a(this.b, this.c, this.a);
    }

    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
        moPubInterstitial.destroy();
        q.b(this.b, this.c, this.a);
    }

    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
        q.a(this.b, this.a);
    }

    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
        q.b(this.b, this.a);
    }

    public void onInterstitialFinished(MoPubInterstitial moPubInterstitial) {
    }

    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
        moPubInterstitial.destroy();
        q.c(this.b, this.a);
    }
}
