package com.appodeal.ads.b;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.appodeal.ads.o;
import com.appodeal.ads.q;

class h implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdLoadListener {
    private final o a;
    private final int b;
    private final int c;

    h(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        if (appLovinAd.isVideoAd()) {
            q.b(this.b, this.c, this.a);
            return;
        }
        ((g) this.a.g()).b = appLovinAd;
        q.a(this.b, this.c, this.a);
    }

    public void failedToReceiveAd(int i) {
        q.b(this.b, this.c, this.a);
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        q.a(this.b, this.a);
    }

    public void adClicked(AppLovinAd appLovinAd) {
        q.b(this.b, this.a);
    }

    public void adHidden(AppLovinAd appLovinAd) {
        q.c(this.b, this.a);
    }
}
