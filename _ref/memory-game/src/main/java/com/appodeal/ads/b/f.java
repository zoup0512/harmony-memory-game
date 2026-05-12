package com.appodeal.ads.b;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.appodeal.ads.o;
import com.appodeal.ads.q;

public class f implements AdListener {
    private final o a;
    private final int b;
    private final int c;

    f(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        q.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        q.b(this.b, this.c, this.a);
    }

    public void a() {
        q.a(this.b, this.a);
    }

    public void b() {
        q.b(this.b, this.a);
    }

    public void onAdDismissed(Ad ad) {
        q.c(this.b, this.a);
    }

    public void onAdExpanded(Ad ad) {
    }

    public void onAdCollapsed(Ad ad) {
    }
}
