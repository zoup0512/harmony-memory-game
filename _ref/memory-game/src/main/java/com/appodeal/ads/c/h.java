package com.appodeal.ads.c;

import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.cmcm.adsdk.banner.CMAdView;
import com.cmcm.adsdk.banner.CMBannerAdListener;

class h implements CMBannerAdListener {
    private final w a;
    private final int b;
    private final int c;

    h(w wVar, int i, int i2) {
        this.a = wVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(CMAdView cMAdView) {
        y.a(this.b, this.c, this.a);
    }

    public void adFailedToLoad(CMAdView cMAdView, int i) {
        y.b(this.b, this.c, this.a);
    }

    public void onAdClicked(CMAdView cMAdView) {
        y.c(this.b, this.a);
    }
}
