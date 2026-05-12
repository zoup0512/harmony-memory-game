package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.cmcm.adsdk.banner.CMAdView;
import com.cmcm.adsdk.banner.CMBannerAdListener;

class i implements CMBannerAdListener {
    private final h a;
    private final int b;
    private final int c;

    i(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(CMAdView cMAdView) {
        j.a(this.b, this.c, this.a);
    }

    public void adFailedToLoad(CMAdView cMAdView, int i) {
        j.b(this.b, this.c, this.a);
    }

    public void onAdClicked(CMAdView cMAdView) {
        j.c(this.b, this.a);
    }
}
