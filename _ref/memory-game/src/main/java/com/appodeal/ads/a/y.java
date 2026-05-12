package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.revmob.RevMobAdsListener;

class y extends RevMobAdsListener {
    private final h a;
    private final int b;
    private final int c;

    y(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onRevMobAdReceived() {
        j.a(this.b, this.c, this.a);
    }

    public void onRevMobAdNotReceived(String str) {
        j.b(this.b, this.c, this.a);
    }

    public void onRevMobAdClicked() {
        j.c(this.b, this.a);
    }

    public void onRevMobSessionStarted() {
        super.onRevMobSessionStarted();
    }
}
