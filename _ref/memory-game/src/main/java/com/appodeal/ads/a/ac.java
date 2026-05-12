package com.appodeal.ads.a;

import android.view.View;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.startapp.android.publish.banner.BannerListener;

class ac implements BannerListener {
    private final h a;
    private final int b;
    private final int c;

    ac(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onReceiveAd(View view) {
        j.a(this.b, this.c, this.a);
    }

    public void onFailedToReceiveAd(View view) {
        j.b(this.b, this.c, this.a);
    }

    public void onClick(View view) {
        j.c(this.b, this.a);
    }
}
