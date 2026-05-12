package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.flurry.android.ads.FlurryAdBanner;
import com.flurry.android.ads.FlurryAdBannerListener;
import com.flurry.android.ads.FlurryAdErrorType;

public class n implements FlurryAdBannerListener {
    private final h a;
    private final int b;
    private final int c;

    n(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onFetched(FlurryAdBanner flurryAdBanner) {
        j.a(this.b, this.c, this.a);
    }

    public void onError(FlurryAdBanner flurryAdBanner, FlurryAdErrorType flurryAdErrorType, int i) {
        j.b(this.b, this.c, this.a);
    }

    public void onClicked(FlurryAdBanner flurryAdBanner) {
        j.c(this.b, this.a);
    }

    public void onRendered(FlurryAdBanner flurryAdBanner) {
    }

    public void onShowFullscreen(FlurryAdBanner flurryAdBanner) {
    }

    public void onCloseFullscreen(FlurryAdBanner flurryAdBanner) {
    }

    public void onAppExit(FlurryAdBanner flurryAdBanner) {
    }

    public void onVideoCompleted(FlurryAdBanner flurryAdBanner) {
    }
}
