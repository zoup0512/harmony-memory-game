package com.appodeal.ads.c;

import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequestError;

class s implements AdEventListener {
    private final w a;
    private final int b;
    private final int c;

    s(w wVar, int i, int i2) {
        this.a = wVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded() {
        y.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(AdRequestError adRequestError) {
        y.b(this.b, this.c, this.a);
    }

    public void onAdOpened() {
    }

    public void onAdLeftApplication() {
        y.c(this.b, this.a);
    }

    public void onAdClosed() {
    }
}
