package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.google.android.gms.ads.AdListener;

class b extends AdListener {
    private final o a;
    private final int b;
    private final int c;

    b(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded() {
        q.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(int i) {
        q.b(this.b, this.c, this.a);
    }

    public void onAdOpened() {
        q.a(this.b, this.a);
    }

    public void onAdLeftApplication() {
        q.b(this.b, this.a);
    }

    public void onAdClosed() {
        q.c(this.b, this.a);
    }
}
