package com.appodeal.ads.a;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.appodeal.ads.h;
import com.appodeal.ads.j;

public class f implements AdListener {
    private final h a;
    private final int b;
    private final int c;

    f(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        j.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        j.b(this.b, this.c, this.a);
    }

    public void onAdExpanded(Ad ad) {
    }

    public void onAdCollapsed(Ad ad) {
    }

    public void a() {
        j.c(this.b, this.a);
    }

    public void onAdDismissed(Ad ad) {
    }
}
