package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.revmob.RevMobAdsListener;

class t extends RevMobAdsListener {
    private final ap a;
    private final int b;
    private final int c;

    t(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void onRevMobVideoLoaded() {
        aj.a(this.b, this.c, this.a);
    }

    public void onRevMobAdNotReceived(String str) {
        aj.b(this.b, this.c, this.a);
    }

    public void onRevMobAdDismissed() {
        aj.d(this.b, this.a);
    }

    public void onRevMobAdClicked() {
        aj.c(this.b, this.a);
        aj.d(this.b, this.a);
    }

    public void onRevMobVideoStarted() {
        aj.a(this.b, this.a);
    }

    public void onRevMobVideoFinished() {
        aj.b(this.b, this.a);
    }
}
