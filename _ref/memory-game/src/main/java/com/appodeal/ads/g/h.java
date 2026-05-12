package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.flurry.android.ads.FlurryAdErrorType;
import com.flurry.android.ads.FlurryAdInterstitial;
import com.flurry.android.ads.FlurryAdInterstitialListener;

public class h implements FlurryAdInterstitialListener {
    private final ap a;
    private final int b;
    private final int c;

    h(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void onFetched(FlurryAdInterstitial flurryAdInterstitial) {
        aj.a(this.b, this.c, this.a);
    }

    public void onError(FlurryAdInterstitial flurryAdInterstitial, FlurryAdErrorType flurryAdErrorType, int i) {
        aj.b(this.b, this.c, this.a);
    }

    public void onDisplay(FlurryAdInterstitial flurryAdInterstitial) {
        aj.a(this.b, this.a);
    }

    public void onVideoCompleted(FlurryAdInterstitial flurryAdInterstitial) {
        aj.b(this.b, this.a);
    }

    public void onClose(FlurryAdInterstitial flurryAdInterstitial) {
        aj.d(this.b, this.a);
    }

    public void onClicked(FlurryAdInterstitial flurryAdInterstitial) {
    }

    public void onRendered(FlurryAdInterstitial flurryAdInterstitial) {
    }

    public void onAppExit(FlurryAdInterstitial flurryAdInterstitial) {
    }
}
