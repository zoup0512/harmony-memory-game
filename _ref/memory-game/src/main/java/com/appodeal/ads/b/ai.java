package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.yandex.mobile.ads.AdRequestError;
import com.yandex.mobile.ads.InterstitialEventListener;

class ai implements InterstitialEventListener {
    private final o a;
    private final int b;
    private final int c;

    ai(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onInterstitialLoaded() {
        q.a(this.b, this.c, this.a);
    }

    public void onInterstitialFailedToLoad(AdRequestError adRequestError) {
        q.b(this.b, this.c, this.a);
    }

    public void onInterstitialShown() {
        q.a(this.b, this.a);
    }

    public void onAdLeftApplication() {
        q.b(this.b, this.a);
    }

    public void onInterstitialDismissed() {
        q.c(this.b, this.a);
    }

    public void onAdOpened() {
    }

    public void onAdClosed() {
    }
}
