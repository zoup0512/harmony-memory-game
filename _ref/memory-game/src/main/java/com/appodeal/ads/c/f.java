package com.appodeal.ads.c;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.appodeal.ads.w;
import com.appodeal.ads.y;

public class f implements AdListener {
    private final w a;
    private final int b;
    private final int c;

    f(w wVar, int i, int i2) {
        this.a = wVar;
        this.b = i;
        this.c = i2;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        y.a(this.b, this.c, this.a);
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        y.b(this.b, this.c, this.a);
    }

    public void onAdExpanded(Ad ad) {
    }

    public void onAdCollapsed(Ad ad) {
    }

    public void a() {
        y.c(this.b, this.a);
    }

    public void onAdDismissed(Ad ad) {
    }
}
