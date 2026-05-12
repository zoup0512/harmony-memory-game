package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequestError;

class ae implements AdEventListener {
    private final h a;
    private final int b;
    private final int c;

    ae(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded() {
        j.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(AdRequestError adRequestError) {
        j.b(this.b, this.c, this.a);
    }

    public void onAdOpened() {
    }

    public void onAdLeftApplication() {
        j.c(this.b, this.a);
    }

    public void onAdClosed() {
    }
}
