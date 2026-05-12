package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.appodeal.ads.networks.a;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;

class b implements AdColonyAdListener {
    private final ap a;
    private final int b;

    b(ap apVar, int i) {
        this.a = apVar;
        this.b = i;
    }

    public void onAdColonyAdStarted(AdColonyAd adColonyAd) {
        this.a.g().a(a.a(adColonyAd));
    }

    public void onAdColonyAdAttemptFinished(AdColonyAd adColonyAd) {
        if (adColonyAd.shown() && !adColonyAd.canceled()) {
            aj.b(this.b, this.a);
        }
        aj.d(this.b, this.a);
    }
}
