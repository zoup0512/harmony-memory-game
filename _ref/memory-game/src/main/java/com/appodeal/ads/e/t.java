package com.appodeal.ads.e;

import com.appodeal.ads.am;
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

    public void onRevMobRewardedVideoLoaded() {
        am.a(this.b, this.c, this.a);
    }

    public void onRevMobAdNotReceived(String str) {
        am.b(this.b, this.c, this.a);
    }

    public void onRevMobAdClicked() {
        am.c(this.b, this.a);
        am.d(this.b, this.a);
    }

    public void onRevMobRewardedVideoStarted() {
        am.a(this.b, this.a);
    }

    public void onRevMobRewardedVideoFinished() {
        am.b(this.b, this.a);
    }

    public void onRevMobRewardedVideoCompleted() {
        am.d(this.b, this.a);
    }
}
