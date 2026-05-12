package com.appodeal.ads.e;

import com.appodeal.ads.am;
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
        am.a(this.b, this.a);
    }

    public void onAdColonyAdAttemptFinished(AdColonyAd adColonyAd) {
        if (adColonyAd.shown() && !adColonyAd.canceled()) {
            am.b(this.b, this.a);
        }
        am.d(this.b, this.a);
    }
}
