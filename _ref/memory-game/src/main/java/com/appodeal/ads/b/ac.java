package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.revmob.RevMobAdsListener;

class ac extends RevMobAdsListener {
    private final o a;
    private final int b;
    private final int c;

    ac(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onRevMobAdReceived() {
        q.a(this.b, this.c, this.a);
    }

    public void onRevMobAdNotReceived(String str) {
        q.b(this.b, this.c, this.a);
    }

    public void onRevMobAdDismissed() {
        q.c(this.b, this.a);
    }

    public void onRevMobAdClicked() {
        q.b(this.b, this.a);
        q.c(this.b, this.a);
    }

    public void onRevMobAdDisplayed() {
        q.a(this.b, this.a);
    }
}
